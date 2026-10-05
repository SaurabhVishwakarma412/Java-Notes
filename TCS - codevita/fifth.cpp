#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Event {
    long long start;
    long long end;
    long long weight;
};

bool compareEvents(const Event& a, const Event& b) {
    if (a.end != b.end)
        return a.end < b.end;
    return a.start < b.start;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    vector<Event> events(n);
    vector<long long> ends(n);

    for (int i = 0; i < n; ++i) {
        cin >> events[i].start >> events[i].end >> events[i].weight;
    }

    sort(events.begin(), events.end(), compareEvents);

    for (int i = 0; i < n; ++i) {
        ends[i] = events[i].end;
    }

    vector<long long> dp(n);
    dp[0] = events[0].weight;

    for (int i = 1; i < n; ++i) {
        long long incl = events[i].weight;
        
        auto it = upper_bound(ends.begin(), ends.begin() + i, events[i].start);
        int idx = distance(ends.begin(), it) - 1;

        if (idx != -1) {
            incl += dp[idx];
        }

        dp[i] = max(dp[i - 1], incl);
    }

    cout << dp[n - 1] << "\n";

    return 0;
}