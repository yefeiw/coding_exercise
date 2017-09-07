/*
 * [535] Encode and Decode TinyURL
 *
 * https://leetcode.com/problems/encode-and-decode-tinyurl
 *
 * algorithms
 * Medium (74.39%)
 * Total Accepted:    16.6K
 * Total Submissions: 22.3K
 * Testcase Example:  '"https://leetcode.com/problems/design-tinyurl"'
 *
 * Note: This is a companion problem to the System Design problem: Design
 * TinyURL.
 * 
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need
 * to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 */
public class Codec {
	private static int counter = 0;
	private HashMap<Integer, String> revMap;
	private HashMap<String, Integer> fwdMap;
	public Codec() {
		revMap = new HashMap<>();
		fwdMap = new HashMap<>();
	}

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (fwdMap.containsKey(longUrl)) {
        	return Integer.toHexString(fwdMap.get(longUrl));
        } else {
			int ans = counter++;
			revMap.put(ans, longUrl);
			fwdMap.put(longUrl, ans);
			return Integer.toHexString(ans);        	
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl, 16);
        if (!revMap.containsKey(key)) {
        	return "";
        }
        return revMap.get(key);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
