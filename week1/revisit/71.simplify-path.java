public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() < 1) {
            return "/";
        }
        String[] paths = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for(String p : paths) {
            if (p.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (p.equals(".")||p.length() == 0) {
                continue;
            } else {
                stack.push(p);
            }
        }
        //construct the final path;
        StringBuffer sb =  new StringBuffer();
        while (!stack.isEmpty()) {
            String top  = stack.removeLast();
            sb.append("/");
            sb.append(top);
        };
        if (sb.length() == 0) return "/";
        else return sb.toString();
    }
}
