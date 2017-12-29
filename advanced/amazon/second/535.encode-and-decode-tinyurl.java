/*
 * [535] Encode and Decode TinyURL
 *
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 *
 * algorithms
 * Medium (73.91%)
 * Total Accepted:    27K
 * Total Submissions: 36.6K
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
    int cnt;
    Map<String, String> forwardIndex = new HashMap();
    Map<String, String> backwardIndex = new HashMap();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (forwardIndex.containsKey(longUrl)) {
            return forwardIndex.get(longUrl);
        }
        int key = cnt++;
        forwardIndex.put(longUrl,Integer.toString(key));
        backwardIndex.put(Integer.toString(key),longUrl);
        return Integer.toString(key);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (!backwardIndex.containsKey(shortUrl)) {
            return "";
        }
        return backwardIndex.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
