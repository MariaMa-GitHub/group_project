
public class Node {
    
    Object cargo;
    Node next;
    
    public Node() {
        this.cargo = new Object();
        this.next = null;
    }
    
    public Node(Object c, Node n) {
        this.cargo = c;
        this.next = n;
    }
    
    public String toString() {
        return this.cargo.toString();
    }
    
}
