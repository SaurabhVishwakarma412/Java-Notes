#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    cin.ignore();

    // state = (hanging spot, tree number)
    map<pair<int, int>, int> stateId;

    // For every spot, store all states in which it occurs
    map<int, vector<int>> spotStates;

    vector<vector<pair<int, int>>> graph;
    // graph[u] = {v, cost}

    int tree = 0;

    auto getState = [&](int spot, int t) -> int {
        pair<int, int> key = {spot, t};

        if (stateId.find(key) == stateId.end()) {
            int id = graph.size();
            stateId[key] = id;
            graph.push_back({});
            spotStates[spot].push_back(id);
        }

        return stateId[key];
    };

    for (int i = 0; i < N; i++) {
        string line;
        getline(cin, line);

        if (line == "break") {
            tree++;
            continue;
        }

        stringstream ss(line);

        vector<int> nodes;
        int x;

        while (ss >> x) {
            nodes.push_back(x);
        }

        if (nodes.empty())
            continue;

        int parent = nodes[0];

        int parentState = getState(parent, tree);

        for (size_t j = 1; j < nodes.size(); j++) {
            int child = nodes[j];

            int childState = getState(child, tree);

            // child -> parent : climbing UP
            // cost = 1
            graph[childState].push_back({parentState, 1});

            // parent -> child : climbing DOWN
            // cost = 0
            graph[parentState].push_back({childState, 0});
        }
    }

    int source, destination;
    cin >> source >> destination;

    /*
       Connect the same hanging spot belonging to
       different trees.

       Switching trees costs 1.
    */
    for (map<int, vector<int>>::iterator it = spotStates.begin(); it != spotStates.end(); ++it) {
        vector<int> &states = it->second;

        for (size_t i = 0; i < states.size(); i++) {
            for (size_t j = i + 1; j < states.size(); j++) {

                int u = states[i];
                int v = states[j];

                graph[u].push_back({v, 1});
                graph[v].push_back({u, 1});
            }
        }
    }

    const int INF = 1e9;

    vector<int> dist(graph.size(), INF);
    deque<int> dq;

    /*
       Source is guaranteed to belong to only one tree.
    */
    int sourceState = -1;

    for (map<pair<int, int>, int>::iterator it = stateId.begin(); it != stateId.end(); ++it) {
        const pair<int, int> &key = it->first;
        int id = it->second;

        if (key.first == source) {
            sourceState = id;
            break;
        }
    }

    if (sourceState == -1) {
        cout << -1 << '\n';
        return 0;
    }

    dist[sourceState] = 0;
    dq.push_front(sourceState);

    // 0-1 BFS
    while (!dq.empty()) {
        int u = dq.front();
        dq.pop_front();

        for (size_t i = 0; i < graph[u].size(); i++) {
            int v = graph[u][i].first;
            int cost = graph[u][i].second;

            if (dist[u] + cost < dist[v]) {
                dist[v] = dist[u] + cost;

                if (cost == 0) {
                    dq.push_front(v);
                } else {
                    dq.push_back(v);
                }
            }
        }
    }

    int answer = INF;

    // Destination can belong to ANY tree
    for (map<pair<int, int>, int>::iterator it = stateId.begin(); it != stateId.end(); ++it) {
        const pair<int, int> &key = it->first;
        int id = it->second;

        if (key.first == destination) {
            answer = min(answer, dist[id]);
        }
    }

    cout << answer << '\n';

    return 0;
}
