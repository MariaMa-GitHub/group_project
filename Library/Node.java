/**
 * Node class
 *
 * @author Max Wang
 * @version January 12, 2022
 * I don't know if i did it right someone look it over lmao
 */
public class Node {
    
    Person cargo;
    Node next;
    //default constructor
    public Node() {
        this.cargo = new Person();
        this.next = null;
    }
    
    public Node(Person c, Node n) {
        this.cargo = c;
        this.next = n;
    }
    //toString
    public String toString() {
        return this.cargo.toString();
    }
    
}
