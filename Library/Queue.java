/**
 * Queue class with helper methods
 *
 * @author Max Wang
 * @version January 12, 2022
 * I don't know if i did it right someone look it over lmao
 */
public class Queue {
    
    LinkList q;
    //default constructor
    public Queue() {
        this.q = new LinkList();
    }
    public Queue(LinkList l) {
        this.q = l;
    }
    //checks if the queue is empty
    public boolean isEmpty() {
        return q.head == null;
    }
    //adds a person to the queue
    public void enQueue(Person cargo) {
        q.addLast(cargo);
    }
    //removes a person from the queue
    public Person deQueue() {
        return q.removeFirst();
    }
    public Person peek() {
        return q.get(1);
    }

}
