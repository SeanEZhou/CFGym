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

const int MaxN = 1000010;
ll x[MaxN], y[MaxN];
int xmn, xmx, ymn, ymx;
int n;

int uf[MaxN];

int getf(int x) { return uf[x] == x ? x : uf[x] = getf(uf[x]); }
void merge(int x, int y) {
	uf[getf(x)] = getf(y);
}

bool check(ll d) {
	rep(i, 1, n) uf[i] = i;
	if (x[xmx] - x[xmn] >= d) merge(xmx, xmn);
	if (y[ymx] - y[ymx] >= d) merge(ymx, ymn);
	rep(i, 1, n) {
		if (x[i] - x[xmn] >= d) merge(i, xmn);
		if (x[xmx] - x[i] >= d) merge(i, xmx);
		if (y[i] - y[ymn] >= d) merge(i, ymn);
		if (y[ymx] - y[i] >= d) merge(i, ymx);
	}
	int root = getf(1);
	rep(i, 2, n)
		if (getf(i) != root) return 0;
	return 1;
}

int main() {
	scanf("%d", &n);
	rep(i, 1, n) {
		int tx, ty;
		scanf("%d%d", &tx, &ty);
		x[i] = tx + ty;
		y[i] = tx - ty;
	}
	xmn = xmx = ymn = ymx = 1;
	rep(i, 1, n) {
		if (x[i] < x[xmn]) xmn = i;
		if (x[i] > x[xmx]) xmx = i;
		if (y[i] < y[ymn]) ymn = i;
		if (y[i] > y[ymx]) ymx = i;
	}
	ll l = 0, r = 1ll << 32;
	while (l != r) {
		ll mid = (l + r + 1) / 2;
		if (check(mid)) l = mid;
		else r = mid - 1;
	}
	cout << l << endl;
	return 0;
}

