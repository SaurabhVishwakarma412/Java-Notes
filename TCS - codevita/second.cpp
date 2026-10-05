#include <iostream>
#include <string>
#include <cmath>
#include <algorithm>

using namespace std;

const int segments[10] = {
    0b1111110,
    0b0110000,
    0b1101101,
    0b1111001,
    0b0110011,
    0b1011011,
    0b1011111,
    0b1110000,
    0b1111111,
    0b1111011
};

int countToggles(char c1, char c2) {
    int d1 = c1 - '0';
    int d2 = c2 - '0';
    return __builtin_popcount(segments[d1] ^ segments[d2]);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string initial_time;
    if (!(cin >> initial_time)) return 0;

    int cost_x, cost_y;
    cin >> cost_x >> cost_y;

    int init_h = stoi(initial_time.substr(0, 2));
    int init_m = stoi(initial_time.substr(3, 2));

    string best_time = "";
    long long min_total_cost = 1e18;

    for (int h = 1; h <= 12; h++) {
        for (int m = 0; m < 60; m++) {
            string h_str = (h < 10 ? "0" : "") + to_string(h);
            string m_str = (m < 10 ? "0" : "") + to_string(m);
            string target_time = h_str + ":" + m_str;

            int total_toggles = 0;
            total_toggles += countToggles(initial_time[0], target_time[0]);
            total_toggles += countToggles(initial_time[1], target_time[1]);
            total_toggles += countToggles(initial_time[3], target_time[3]);
            total_toggles += countToggles(initial_time[4], target_time[4]);

            if (total_toggles == 1) {
                int h_diff = abs(h - init_h);
                int hour_dist = min(h_diff, 12 - h_diff);

                int m_diff = abs(m - init_m);
                int min_dist = min(m_diff, 60 - m_diff);

                long long current_cost = (long long)hour_dist * cost_x + (long long)min_dist * cost_y;

                if (current_cost < min_total_cost) {
                    min_total_cost = current_cost;
                    best_time = target_time;
                }
            }
        }
    }

    if (best_time == "") {
        cout << "No closest valid time possible\n";
    } else {
        cout << best_time << "\n";
    }

    return 0;
}