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

const int MaxN = 25;
int n;
pair <int, int> card[MaxN];
vector <int> h;

bool used[MaxN];
bool check(int m) {
	clr(used);
	rep(i, 1, n) {
		if (i > 1) {
			if (card[i].fir / m != card[i - 1].fir / m) {
				if (used[card[i].sec]) return 0;
			} else {
				if (card[i].sec != card[i - 1].sec) return 0;
			}
		}
		used[card[i].sec] = 1;
	}
	return 1;
}

void solve() {
	int ans = 0;
	sort(h.begin(), h.end());
	h.erase(unique(h.begin(), h.end()), h.end());
	int s = SZ(h);
	rep(i, 0, s - 2)
		if (check(h[i])) ans += h[i + 1] - h[i];
	cout << ans << endl;
}

int main() {
	scanf("%d", &n);
	h.pb(1);
	rep(i, 1, n) {
		scanf("%d%d", &card[i].fir, &card[i].sec);
		--card[i].fir;
		for (int l = 1, r, t; l <= card[i].fir; l = r + 1) {
			t = card[i].fir / l;
			r = card[i].fir / t;
			h.pb(l);
		}
		h.pb(card[i].fir + 1);
	}
	sort(card + 1, card + n + 1);

	bool flag = 1;
	rep(i, 1, n - 1)
		if (card[i].sec != card[i + 1].sec) flag = 0;
	if (flag) puts("-1");
	else solve();
	return 0;
}

