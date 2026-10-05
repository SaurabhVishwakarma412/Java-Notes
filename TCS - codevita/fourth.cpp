#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string target;
    cin >> target;

    int N;
    cin >> N;

    vector<string> pieces(N);
    vector<int> cost(N);

    for (int i = 0; i < N; i++) {
        cin >> pieces[i];
    }

    for (int i = 0; i < N; i++) {
        cin >> cost[i];
    }

    int n = target.size();

    /*
        dp1[i] = minimum cost when rearrangement IS allowed
        dp2[i] = minimum cost when rearrangement is NOT allowed
    */

    vector<int> dp1(n + 1, INF);
    vector<int> dp2(n + 1, INF);

    dp1[0] = 0;
    dp2[0] = 0;

    // --------------------------------------------------
    // CASE 1: Rearrangement allowed
    // --------------------------------------------------

    for (int i = 0; i < n; i++) {

        if (dp1[i] == INF)
            continue;

        for (int p = 0; p < N; p++) {

            int len = pieces[p].size();

            // Frequency of characters in the wooden piece
            int freq[26] = {};

            for (char c : pieces[p]) {
                freq[c - 'a']++;
            }

            int used[26] = {};

            int j = i;

            /*
                Try to match a PREFIX of target starting
                from position i.

                Since one piece is used before the next piece,
                we cannot skip a target character and then
                match a later target character.
            */

            while (j < n && j - i < len) {

                int x = target[j] - 'a';

                if (used[x] < freq[x]) {
                    used[x]++;
                    j++;
                }
                else {
                    break;
                }
            }

            // This piece matched target[i ... j-1]
            if (j > i) {
                dp1[j] = min(
                    dp1[j],
                    dp1[i] + cost[p]
                );
            }
        }
    }

    // --------------------------------------------------
    // CASE 2: Rearrangement NOT allowed
    // --------------------------------------------------

    for (int i = 0; i < n; i++) {

        if (dp2[i] == INF)
            continue;

        for (int p = 0; p < N; p++) {

            int j = i;

            /*
                The characters of the piece must remain
                in their original order.
            */

            for (char c : pieces[p]) {

                if (j < n && c == target[j]) {
                    j++;
                }
            }

            // Piece successfully matched target[i ... j-1]
            if (j > i) {
                dp2[j] = min(
                    dp2[j],
                    dp2[i] + cost[p]
                );
            }
        }
    }

    int rearrangedCost = dp1[n];
    int normalCost = dp2[n];

    cout << normalCost - rearrangedCost << '\n';

    return 0;
}