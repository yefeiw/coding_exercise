public class FileSystem {
    //node information
    class Node {
        Node parent;
        //constructor
       public Node(Node parent) {
            this.parent = parent;
        }
        public Node() {
            this.parent = null;
        }
    }
    class File extends Node {
        String Content;
        public void SetContent(String content) {
            this.content = content;
        }
        public String getContent() {
            if (this.content == null) {
                return "";
            } else {
                return this.content;
            }
        }
        public File() {
            this.content = null;
        }
        public File(String content) {
            this.setContent(content);
        }
    }
    class Folder extends Node {
        Map<String, Node> children;
        public void AddChildren(String name, Node child) {
            this.children.put(name,child);
        }
        public Folder() {
            children = new Map<String, Node>();
        }
        public List<
    }
    //state variables
    //root is always going to be a folder
    Folder root;

    public FileSystem() {
       root = new Folder();
    }
    
    public List<String> ls(String path) {
        String[] paths = path.split("/");
        Node iter = root;
        for(String path : paths) {
            iter = iter.getChild(path);
            if (iter == null) {
                //child is not found, return null
                return new ArrayList<String>();
            }
        }
        return iter.getChildren();
    }
    
    public void mkdir(String path) {
        
    }
    
    public void addContentToFile(String filePath, String content) {
        
    }
    
    public String readContentFromFile(String filePath) {
        
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
