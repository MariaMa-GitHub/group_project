import java.util.Scanner;

public class LinkList {
    
    Node head;
    int length;
    
    Scanner sc = new Scanner(System.in);
    
    public LinkList() {
        this.head = null;
        this.length = 0;
    }
    
    public LinkList(Node h, int l) {
        this.head = h;
        this.length = l;
    }
    
    public String toString() {
        String message = "";
        for (Node n = head; n != null; n=n.next) {
            message += n.toString() + "\n";
        }
        return message;
    }
    
    public Person get(int index) {
        Node node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        return node.cargo;
    }
    
    public Person removeFirst() {
        Person person = head.cargo;
        this.length--;
        head = head.next;
        return person;
    }
    
    public void set(int index, Person cargo) {
        Node node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        node.cargo = cargo;
    }
    
    public void add(int index, Person cargo) { 
        Node node = head;
        if ((index < 1) || (index > length + 1)) {
            System.out.print("The link list does not have position " + index + ". Please enter another number: ");
            add(sc.nextInt(), cargo);
        }
        else if (index == 1) {
            head = new Node(cargo, node);
            length++;
        }
        else {
            for (int i = 1; i < index - 1; i++) {
                node = node.next;
            }
            node.next = new Node(cargo, node.next);
            length++;
        }
        
    }
    
    public void addLast(Person cargo) { 
        if (head != null) {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(cargo, null);
        }
        else {
            head = new Node(cargo, null);
        }
        this.length++;
    }
    
    public void reverse() { 
        for (int i = 1; i <= length / 2; i++) {
                Person cargo1 = get(i);
                Person cargo2 = get(length + 1 - i);
                set(i, cargo2);
                set(length + 1 - i, cargo1);
        }
    }
    
    public void append(LinkList list) {
        for (Node n = list.head; n != null; n=n.next) {
            addLast(n.cargo);
        }
    }
    
    public boolean checkLength() {
        int count = 0;
        for (Node n = head; n != null; n=n.next) {
            count++;
        }
        if (count == length) {
            return true;
        }
        else {
            return false;
        }
    }

}