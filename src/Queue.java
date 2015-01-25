// -------------------------------------------------------------------------
/**
 * Uses nodes to make a queue
 *
 * @author Michael Goheen
 * @version Apr 11, 2011
 * @param <Item>
 */
public class Queue<Item>
{
    // ~ Data Fields ...........................................................
    private Node<Item> front;
    private Node<Item> rear;
    private int        size;


    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Construct the queue
     */
    public Queue()
    {
        size = 0;
        front = new Node<Item>(null);
        rear = new Node<Item>(null);
        front.setNext(rear);
        rear.setPrevious(front);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Get the size of the queue
     *
     * @return size
     */
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Add an item to the end of the queue
     *
     * @param newItem
     *            An item
     */
    public void enqueue(Item newItem)
    {
        Node<Item> item = new Node<Item>(newItem);
        if (size == 0)
        {
            item.setNext(rear);
            item.setPrevious(front);
            front.setNext(item);
            rear.setPrevious(item);
        }
        else
        {
            item.setNext(rear);
            item.setPrevious(rear.getPrevious());
            rear.getPrevious().setNext(item);
            rear.setPrevious(item);
        }
        size++;

    }


    /**
     * Remove an item from the beginning of the queue
     *
     * @return Item
     */
    public Item dequeue()
    {
        Item x = front.getNext().getElement();
        front.setNext(front.getNext().getNext());
        front.getNext().setPrevious(front);
        size--;
        return x;
    }


    // ----------------------------------------------------------
    /**
     * Reverse this queue
     *
     * @return reversed queue
     */
    public Queue<Item> reverseQueue()
    {
        Queue<Item> reverse = new Queue<Item>();
        while (!(this.isEmpty()))
        {
            reverse.enqueue(this.removeRear());
        }

        return reverse;
    }


    /**
     * Remove an item from the end of the queue
     *
     * @return Item
     */
    public Item removeRear()
    {
        Item x = rear.getPrevious().getElement();
        rear.setPrevious(rear.getPrevious().getPrevious());
        rear.getPrevious().setNext(rear);
        size--;
        return x;
    }


    // ----------------------------------------------------------
    /**
     * Get the item at the rear of the queue
     *
     * @return Item
     */
    public Item rear()
    {
        return rear.getPrevious().getElement();
    }


    // ----------------------------------------------------------
    /**
     * Get the item at the front of the queue
     *
     * @return Item
     */
    public Item peek()
    {
        return front.getNext().getElement();
    }


    // ----------------------------------------------------------
    /**
     * Remove all items from queue
     */
    public void clear()
    {
        size = 0;
        front = new Node<Item>(null);
        rear = new Node<Item>(null);
        front.setNext(rear);
    }


    /**
     * Determine if queue is empty
     *
     * @return true if empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * Get the item at the front of the queue
     *
     * @return String Items in queue
     */
    public String toString()
    {
        String st = "";
        st += "<";
        if (size > 0)
        {
            Node<Item> temp = new Node<Item>(null);
            temp = front.getNext();
            while (temp != rear)
            {
                if (temp == rear.getPrevious())
                {
                    st += temp.getElement() + ">";
                    return st;
                }
                st += temp.getElement() + ", ";
                temp = temp.getNext();
            }
        }
        return st;
    }


    // ~ Private helper class ..................................................

    // -------------------------------------------------------------------------
    /**
     * A basic node in a linked list, storing a single data value and a single
     * pointer to the next node in the linked chain.
     *
     * @author Michael Goheen
     * @version Apr 11, 2011
     * @param <Item>
     */
    private static class Node<Item>
    {
        // ~ Instance/static variables .........................................

        private Item       element;
        private Node<Item> next;
        private Node<Item> previous;


        // ~ Constructors ......................................................

        // ----------------------------------------------------------
        /**
         * Creates a node with a null next pointer.
         *
         * @param theElement
         *            the element to be stored.
         */
        public Node(Item theElement)
        {
            this(theElement, null, null);
        }


        // ----------------------------------------------------------
        /**
         * Creates a node with a link to the specified node.
         *
         * @param theElement
         *            the element to be stored.
         * @param n
         *            the node to follow this one in the list.
         * @param p
         *            the node previous in the list
         */
        public Node(Item theElement, Node<Item> n, Node<Item> p)
        {
            setElement(theElement);
            setNext(n);
            setPrevious(p);
        }


        // ~ Public methods ....................................................

        // ----------------------------------------------------------
        /**
         * Get the current data value stored in this node.
         *
         * @return the element
         */
        public Item getElement()
        {
            return element;
        }


        // ----------------------------------------------------------
        /**
         * Set the data value stored in this node.
         *
         * @param value
         *            the new data value to set
         */
        public void setElement(Item value)
        {
            element = value;
        }


        // ----------------------------------------------------------
        /**
         * Get the next node in this chain.
         *
         * @return a reference to the next node in the chain.
         */
        public Node<Item> getNext()
        {
            return next;
        }


        // ----------------------------------------------------------
        /**
         * Set the value of this node's next pointer.
         *
         * @param value
         *            the node to point to as the next one in the chain.
         */
        public void setNext(Node<Item> value)
        {
            next = value;
        }


        // ----------------------------------------------------------
        /**
         * Get the previous node in this chain.
         *
         * @return a reference to the previous node in the chain.
         */
        public Node<Item> getPrevious()
        {
            return previous;
        }


        // ----------------------------------------------------------
        /**
         * Set the value of this node's previous pointer.
         *
         * @param value
         *            the node to point to as the previous one in the chain.
         */
        public void setPrevious(Node<Item> value)
        {
            previous = value;
        }
    }
}
