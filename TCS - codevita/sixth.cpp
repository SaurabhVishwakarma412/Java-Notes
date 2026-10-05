#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <map>
#include <set>

using namespace std;

struct Point {
    long long x, y;
    bool operator<(const Point& other) const {
        if (x != other.x) return x < other.x;
        return y < other.y;
    }
};

map<Point, vector<Point>> adj;
set<vector<Point>> unique_paths;
Point house;

void dfs(Point curr, vector<Point>& path, set<pair<Point, Point>>& visited_edges) {
    if (curr.x == house.x && curr.y == house.y && path.size() > 1) {
        vector<Point> normalized = path;
        vector<Point> reversed_path = path;
        reverse(reversed_path.begin(), reversed_path.end());
        
        if (reversed_path < normalized) {
            normalized = reversed_path;
        }
        unique_paths.insert(normalized);
        return;
    }

    for (Point next : adj[curr]) {
        pair<Point, Point> edge = {min(curr, next), max(curr, next)};
        if (visited_edges.find(edge) == visited_edges.end()) {
            visited_edges.insert(edge);
            path.push_back(next);
            
            dfs(next, path, visited_edges);
            
            path.pop_back();
            visited_edges.erase(edge);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    for (int i = 0; i < n; ++i) {
        long long x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        Point p1 = {x1, y1};
        Point p2 = {x2, y2};
        adj[p1].push_back(p2);
        adj[p2].push_back(p1);
    }

    cin >> house.x >> house.y;

    vector<Point> path;
    path.push_back(house);
    set<pair<Point, Point>> visited_edges;

    dfs(house, path, visited_edges);

    cout << unique_paths.size() << "\n";

    return 0;
}