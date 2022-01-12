public class Queue {
    
    LinkList q;
    
    public Queue() {
        this.q = new LinkList();
    }
    public Queue(LinkList l) {
        this.q = l;
    }
    
    public boolean isEmpty() {
        return q.head == null;
    }
    public void enQueue(Person cargo) {
        q.addLast(cargo);
    }
    public Person deQueue() {
        return q.removeFirst();
    }
    public Person peek() {
        return q.get(1);
    }

}