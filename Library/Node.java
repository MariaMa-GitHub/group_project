public class Node {
    
    Person cargo;
    Node next;
    
    public Node() {
        this.cargo = new Person();
        this.next = null;
    }
    
    public Node(Person c, Node n) {
        this.cargo = c;
        this.next = n;
    }
    
    public String toString() {
        return this.cargo.toString();
    }
}