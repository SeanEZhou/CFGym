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

const int MaxN = 10010;
int n;
int a[MaxN];

int main() {
	scanf("%d", &n);
	rep(i, 1, n) scanf("%d", a + i);
	sort(a + 1, a + n + 1);
	ll sum = 0;
	int ans = 0;
	rep(i, 1, n)
		if (a[i] > sum) {
			++ans;
			sum += a[i];
		}
	cout << ans << endl;
	return 0;
}

