
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
    public void enqueue(Object cargo) {
        q.addLast(cargo);
    }
    public Object dequeue() {
        return q.removeFirst();
    }
    public Object peek() {
        return q.get(1);
    }

}
