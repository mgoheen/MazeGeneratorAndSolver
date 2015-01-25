/**
 * Test the queue
 *
 * @author Michael
 */
public class QueueTest
{
    /**
     * Test method
     *
     * @param args
     */
    public static void main(String args[])
    {
        Queue<String> q = new Queue<String>();
        Queue<String> reversed = new Queue<String>();
        System.out.println(q.isEmpty());
        q.enqueue("A");
        System.out.println(q.isEmpty());
        q.enqueue("B");
        q.enqueue("C");
        q.enqueue("D");
        q.enqueue("E");
        q.enqueue("F");
        q.enqueue("G");
        q.enqueue("H");
        q.enqueue("I");
        q.enqueue("J");
        q.enqueue("K");
        System.out.println(q.isEmpty());
        q.enqueue("L");
        System.out.println(q);
        reversed = q.reverseQueue();
        System.out.println(reversed);
        q.enqueue("I");
        q.enqueue("J");
        q.enqueue("K");
        q.enqueue("I");
        q.enqueue("J");
        q.enqueue("K");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println(q.isEmpty());
        q.dequeue();
        System.out.println(q);
        System.out.println(q.isEmpty());
        q.clear();
        System.out.println(q.isEmpty());
        System.out.println(q);
    }
}
