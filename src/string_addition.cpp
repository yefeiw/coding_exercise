#include <iostream>


using namespace std;

bool isValid(string input);

string addition(string l, string r) {
	if (!isValid(l) || !isValid(r)) {
		return "0";
	}
	string ret;
	int carry = 0;//carrying bit
	int lSize = l.size();
	int rSize = r.size();
	int indexl = lSize -1;
	int indexr = rSize - 1;
	while(indexl >= 0 || indexr >= 0) {
		int bl = indexl >= 0 ? l[indexl] - '0' : 0;
		int br = indexr >= 0 ? r[indexr] - '0' : 0;
		int sum = bl + br + carry;
		char cand = '0' + sum % 10;
		carry = sum / 10;
		ret.insert(ret.begin(),cand);
		indexl --;
		indexr --;
	}
	return ret;
}

bool isValid(string input) {
	//assumption: empty strings will be treated invalid.
	// If you mean 0, input "0";
	//assumption: multiple 0's will be treated valid.
	//e.g. 000 is valid and evaluates to 0;
	for (char c : input) {
		int diff = c - '0';
		if (diff < 0) return false;
		if (diff > 9) return false;
		return true;
	}
}

int main(void) {
	string l = "121";
	string r = "000";
	cout << addition(l,r)<<endl;
	return 0 ;
}