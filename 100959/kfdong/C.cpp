#include <bits/stdc++.h>
#define db double
#define uint unsigned int
#define ll long long
#define ull unsigned long long
#define vint vector <int>
#define fir first
#define sec second
#define mkp make_pair
#define pb push_back
#define pii pair <int, int>
#define rep(i, a, b) for (int i = (a), __end = (b); i <= __end; ++i) 
#define repdown(i, a, b) for (int i = (a), __end = (b); i >= __end; --i) 
#define RG register
#define clr(a) memset(a, 0, sizeof(a))
#define SZ(a) int(a.size())
using namespace std;

const int mod = 998244353;
const int gox[4] = {-1, 1, 0, 0};
const int goy[4] = {0, 0, 1, -1};

int iabs(int x) { return x < 0 ? -x : x; }
ll labs(ll x) { return x < 0 ? -x : x; }
int dec(int x, int v) { x -= v; return x < 0 ? x + mod : x; }
int inc(int x, int v) { x += v; return x >= mod ? x - mod : x; }
inline int power(ll x, ll l) {
	ll ret = 1;
	for (; l; l >>= 1, x = x * x % mod)
		if (l & 1) ret = ret * x % mod;
	return ret;
}
int cmax(int &x, int v) { return x < v ? x = v : x; }
int cmin(int &x, int v) { return x > v ? x = v : x; }
int bits(int x, int v) { return (x >> v) & 1; }

const int MaxN = 1000010, pls = 500000;
const int inf = 1 << 25;
int n, a[MaxN];
int dist[MaxN][2], q[MaxN * 2];

void bfs() {
	memset(dist, 63, sizeof(dist));
	int l = 0, r = 0;
	q[++r] = pls * 2;
	dist[0 + pls][0] = 0;
	while (l != r) {
		int tmp = q[++l];
		int u = tmp / 2, k = tmp % 2;
		u -= pls;
		rep(i, 1, n) {
			int v = 2 * a[i] - u;
			if (-pls <= v && v <= pls) {
				if (dist[v + pls][!k] > dist[u + pls][k] + 1) {
					dist[v + pls][!k] = dist[u + pls][k] + 1;
					q[++r] = (v + pls) * 2 + !k;
				}
			}
		}
	}
}

int main() {
	scanf("%d", &n);
	rep(i, 1, n) scanf("%d", a + i);
	bfs();
	int q;
	scanf("%d", &q);
	while (q--) {
		int x, y;
		scanf("%d%d", &x, &y);
		int ans = min(dist[y + x + pls][1], dist[y - x + pls][0]);
		if (ans > inf) puts("-1");
		else printf("%d\n", ans);
	}
	return 0;
}

