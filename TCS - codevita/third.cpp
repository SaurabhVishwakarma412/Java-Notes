#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

struct State {
    int sheet;
    int rot;
    int side;
    int dist;
};

/*
    sides:
        0 = UP
        1 = RIGHT
        2 = DOWN
        3 = LEFT

    -1 = S
    -2 = D
*/

struct Orientation {
    vector<string> a;

    // distance[from][to]
    // from/to:
    // 0..3 = sides
    // 4 = S
    // 5 = D
    int dist[6][6];

    bool hasS = false;
    bool hasD = false;

    Orientation() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                dist[i][j] = INF;
    }
};

vector<string> rotate90(const vector<string>& a) {
    int M = a.size();
    vector<string> b(M, string(M, 'L'));

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {
            b[j][M - 1 - i] = a[i][j];
        }
    }

    return b;
}

bool isTrack(char c) {
    return c == 'T' || c == 'S' || c == 'D';
}

/*
    Calculate shortest paths inside one sheet.

    A boundary point is represented by a track cell
    touching that boundary.
*/
Orientation analyse(const vector<string>& a) {

    int M = a.size();

    Orientation res;
    res.a = a;

    vector<pair<int,int>> special(6, {-1,-1});

    // Find S and D
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {

            if (a[i][j] == 'S') {
                special[4] = {i,j};
                res.hasS = true;
            }

            if (a[i][j] == 'D') {
                special[5] = {i,j};
                res.hasD = true;
            }
        }
    }

    /*
        For each side, there can be multiple track cells.
        Since the track has no branches, we only need to
        know the closest useful boundary cells.
    */

    vector<pair<int,int>> sideCells[4];

    for (int i = 0; i < M; i++) {

        if (isTrack(a[i][0]))
            sideCells[3].push_back({i,0});

        if (isTrack(a[i][M-1]))
            sideCells[1].push_back({i,M-1});
    }

    for (int j = 0; j < M; j++) {

        if (isTrack(a[0][j]))
            sideCells[0].push_back({0,j});

        if (isTrack(a[M-1][j]))
            sideCells[2].push_back({M-1,j});
    }

    /*
        Multi-source BFS from every important point.
    */

    vector<pair<int,int>> points[6];

    for (int s = 0; s < 4; s++) {
        for (auto p : sideCells[s])
            points[s].push_back(p);
    }

    if (res.hasS)
        points[4].push_back(special[4]);

    if (res.hasD)
        points[5].push_back(special[5]);

    for (int src = 0; src < 6; src++) {

        for (auto start : points[src]) {

            vector<vector<int>> d(
                M, vector<int>(M, INF)
            );

            queue<pair<int,int>> q;

            d[start.first][start.second] = 0;
            q.push(start);

            while (!q.empty()) {

                auto [r,c] = q.front();
                q.pop();

                int dr[] = {-1,1,0,0};
                int dc[] = {0,0,-1,1};

                for (int k = 0; k < 4; k++) {

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= M ||
                        nc < 0 || nc >= M)
                        continue;

                    if (!isTrack(a[nr][nc]))
                        continue;

                    if (d[nr][nc] >
                        d[r][c] + 1) {

                        d[nr][nc] =
                            d[r][c] + 1;

                        q.push({nr,nc});
                    }
                }
            }

            /*
                Check which important points can be reached.
            */

            for (int dst = 0; dst < 6; dst++) {

                for (auto p : points[dst]) {

                    if (d[p.first][p.second] != INF) {

                        res.dist[src][dst] =
                            min(
                                res.dist[src][dst],
                                d[p.first][p.second]
                            );
                    }
                }
            }
        }
    }

    return res;
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<string> grid(N);

    for (int i = 0; i < N; i++)
        cin >> grid[i];

    int K = N / M;

    /*
        Split the big grid into K x K sheets.
    */

    vector<vector<string>> sheets;

    for (int br = 0; br < K; br++) {

        for (int bc = 0; bc < K; bc++) {

            vector<string> sheet(M);

            for (int i = 0; i < M; i++) {

                for (int j = 0; j < M; j++) {

                    sheet[i] +=
                        grid[br*M+i][bc*M+j];
                }
            }

            sheets.push_back(sheet);
        }
    }

    int Ssheet = -1;
    int Dsheet = -1;

    for (int s = 0; s < sheets.size(); s++) {

        for (auto &row : sheets[s]) {

            if (row.find('S') != string::npos)
                Ssheet = s;

            if (row.find('D') != string::npos)
                Dsheet = s;
        }
    }

    /*
        Generate rotations for every sheet.
    */

    vector<vector<Orientation>> all(
        sheets.size(),
        vector<Orientation>(4)
    );

    for (int s = 0; s < sheets.size(); s++) {

        vector<string> cur = sheets[s];

        for (int r = 0; r < 4; r++) {

            all[s][r] = analyse(cur);

            cur = rotate90(cur);
        }
    }

    /*
        IMPORTANT:
        S sheet must be top-left.
        D sheet must be bottom-right.

        We enumerate monotonic paths through the
        sheet-grid.

        A monotonic path can only move:
            RIGHT
            DOWN
    */

    int answer = INF;

    /*
        State used[sheet] prevents using the same
        physical sheet twice.
    */

    vector<bool> used(sheets.size(), false);

    /*
        Convert sheet-grid position to sheet index
        after rearrangement.

        path contains the positions of sheets.
    */

    vector<pair<int,int>> path;

    function<void(int,int)> generatePaths;

    /*
        For each possible monotonic sheet path,
        solve which physical sheets/orientations
        should occupy the positions.
    */

    function<void(
        int,int,
        int,
        int,
        int,
        vector<bool>&
    )> solve;

    /*
        Simpler recursive search.

        pos = current sheet-grid position
        prevSide = side through which we entered
        current sheet = physical sheet
        current orientation = orientation
    */

    function<void(
        int,int,int,int,
        vector<bool>&,
        int
    )> dfs;

    /*
        Because the source and destination sheets are
        fixed at the two corners, try every monotonic
        route.
    */

    vector<vector<pair<int,int>>> routes;

    function<void(int,int,vector<pair<int,int>>)> makeRoutes =
        [&](int r, int c, vector<pair<int,int>> p) {

            p.push_back({r,c});

            if (r == K-1 && c == K-1) {
                routes.push_back(p);
                return;
            }

            if (r+1 < K)
                makeRoutes(r+1,c,p);

            if (c+1 < K)
                makeRoutes(r,c+1,p);
        };

    makeRoutes(0,0,{});

    /*
        Number of monotonic routes is at most
        C(14,7) = 3432, which is small.
    */

    for (auto &route : routes) {

        int L = route.size();

        /*
            DP over path positions.

            At every position:
              choose a sheet
              choose rotation
              choose entry/exit side
        */

        struct Node {
            int sheet;
            int rot;
            int enter;
            int exit;
            int cost;
        };

        vector<Node> current;

        /*
            Source sheet is fixed at route[0].
        */

        for (int rot = 0; rot < 4; rot++) {

            auto &o = all[Ssheet][rot];

            if (!o.hasS)
                continue;

            /*
                First move must be RIGHT or DOWN.
            */

            for (int dir = 0; dir < 2; dir++) {

                int nextSide;

                if (dir == 0)
                    nextSide = 1; // RIGHT

                else
                    nextSide = 2; // DOWN

                int d = o.dist[4][nextSide];

                if (d == INF)
                    continue;

                current.push_back({
                    Ssheet,
                    rot,
                    4,
                    nextSide,
                    d
                });
            }
        }

        /*
            If route has only one sheet.
        */

        if (L == 1) {

            for (auto &x : current) {

                auto &o = all[x.sheet][x.rot];

                if (o.dist[x.exit][5] != INF) {

                    answer = min(
                        answer,
                        x.cost + o.dist[x.exit][5]
                    );
                }
            }

            continue;
        }

        /*
            Used sheets for this route.
        */

        vector<bool> usedLocal(sheets.size(), false);
        usedLocal[Ssheet] = true;

        /*
            We perform DFS over sheet assignments.
            Since the path has <= 15 sheets, and the
            actual tile has very few possible ports,
            this remains manageable.
        */

        function<void(int,int,int,int)> search =
            [&](int pos,
                int prevExit,
                int prevSheet,
                int cost) {

            if (cost >= answer)
                return;

            if (pos == L-1) {

                /*
                    D sheet must be used here.
                */

                if (usedLocal[Dsheet])
                    return;

                int r0 = route[pos-1].first;
                int c0 = route[pos-1].second;

                int r1 = route[pos].first;
                int c1 = route[pos].second;

                int enterSide;

                if (r1 == r0+1)
                    enterSide = 0; // enter from UP
                else
                    enterSide = 3; // enter from LEFT

                for (int rot = 0; rot < 4; rot++) {

                    auto &o = all[Dsheet][rot];

                    int inside =
                        o.dist[enterSide][5];

                    if (inside == INF)
                        continue;

                    answer = min(
                        answer,
                        cost + inside
                    );
                }

                return;
            }

            int r0 = route[pos-1].first;
            int c0 = route[pos-1].second;

            int r1 = route[pos].first;
            int c1 = route[pos].second;

            /*
                Side of current sheet where we enter.
            */

            int enterSide;

            if (r1 == r0+1)
                enterSide = 0; // from UP

            else
                enterSide = 3; // from LEFT

            /*
                Side through which we leave.

                Since route is monotonic, next move
                is either RIGHT or DOWN.
            */

            for (int nextDir = 0; nextDir < 2; nextDir++) {

                int nr = r1;
                int nc = c1;

                if (nextDir == 0)
                    nc++;

                else
                    nr++;

                if (nr >= K || nc >= K)
                    continue;

                int exitSide;

                if (nextDir == 0)
                    exitSide = 1; // RIGHT

                else
                    exitSide = 2; // DOWN

                /*
                    Try every unused sheet.
                */

                for (int s = 0; s < sheets.size(); s++) {

                    if (usedLocal[s])
                        continue;

                    if (s == Dsheet)
                        continue;

                    for (int rot = 0; rot < 4; rot++) {

                        auto &o = all[s][rot];

                        int d =
                            o.dist[
                                enterSide
                            ][
                                exitSide
                            ];

                        if (d == INF)
                            continue;

                        usedLocal[s] = true;

                        search(
                            pos + 1,
                            exitSide,
                            s,
                            cost + d + 1
                        );

                        usedLocal[s] = false;
                    }
                }
            }
        };

        /*
            Start DFS after source sheet.

            We need to account for the source -> first
            sheet connection.

            Instead of keeping the individual source
            states above, directly try every source
            orientation and first direction.
        */

        for (int rot = 0; rot < 4; rot++) {

            auto &o = all[Ssheet][rot];

            for (int firstDir = 0; firstDir < 2; firstDir++) {

                int exitSide =
                    (firstDir == 0 ? 1 : 2);

                int initial =
                    o.dist[4][exitSide];

                if (initial == INF)
                    continue;

                /*
                    The DFS currently starts at the second
                    sheet, so manually handle it.
                */

                int r0 = route[0].first;
                int c0 = route[0].second;

                int r1 = route[1].first;
                int c1 = route[1].second;

                if (firstDir == 0 && c1 != c0+1)
                    continue;

                if (firstDir == 1 && r1 != r0+1)
                    continue;

                int enterSide =
                    (firstDir == 0 ? 3 : 0);

                int pos = 1;

                function<void(int,int,int)> go =
                    [&](int p,
                        int cost,
                        int prevExit) {

                    if (cost >= answer)
                        return;

                    if (p == L-1) {

                        if (usedLocal[Dsheet])
                            return;

                        for (int rotD = 0; rotD < 4; rotD++) {

                            auto &dSheet =
                                all[Dsheet][rotD];

                            int x =
                                dSheet.dist[
                                    prevExit == 1 ? 3 : 0
                                ][5];

                            if (x != INF) {

                                answer = min(
                                    answer,
                                    cost + x
                                );
                            }
                        }

                        return;
                    }

                    int cr = route[p].first;
                    int cc = route[p].second;

                    int pr = route[p-1].first;
                    int pc = route[p-1].second;

                    int enter =
                        (cr == pr+1 ? 0 : 3);

                    /*
                        Determine next direction.
                    */

                    for (int nd = 0; nd < 2; nd++) {

                        int nr = cr;
                        int nc = cc;

                        if (nd == 0)
                            nc++;
                        else
                            nr++;

                        if (nr >= K || nc >= K)
                            continue;

                        int exit =
                            (nd == 0 ? 1 : 2);

                        for (int s = 0;
                             s < sheets.size();
                             s++) {

                            if (usedLocal[s])
                                continue;

                            if (s == Dsheet)
                                continue;

                            for (int rr = 0; rr < 4; rr++) {

                                auto &o2 =
                                    all[s][rr];

                                int d =
                                    o2.dist[
                                        enter
                                    ][
                                        exit
                                    ];

                                if (d == INF)
                                    continue;

                                usedLocal[s] = true;

                                go(
                                    p+1,
                                    cost + d + 1,
                                    exit
                                );

                                usedLocal[s] = false;
                            }
                        }
                    }
                };

                usedLocal[Ssheet] = true;

                /*
                    Choose second sheet.
                */

                for (int s = 0;
                     s < sheets.size();
                     s++) {

                    if (s == Ssheet ||
                        s == Dsheet)
                        continue;

                    for (int rr = 0; rr < 4; rr++) {

                        auto &o2 = all[s][rr];

                        /*
                            Second sheet must connect
                            source exit -> its exit.
                        */

                        for (int nd = 0; nd < 2; nd++) {

                            int er =
                                (nd == 0 ? 1 : 2);

                            int d =
                                o2.dist[
                                    enterSide
                                ][er];

                            if (d == INF)
                                continue;

                            usedLocal[s] = true;

                            go(
                                2,
                                initial + d + 1,
                                er
                            );

                            usedLocal[s] = false;
                        }
                    }
                }

                usedLocal[Ssheet] = false;
            }
        }
    }


    if (answer == INF)
        cout << -1 << '\n';
    else
        cout << answer + 1 << '\n';

    return 0;
}