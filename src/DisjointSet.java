// -------------------------------------------------------------------------
/**
 * Disjoint Set
 *
 * @author Michael Goheen
 * @version Apr 10, 2012
 */
public class DisjointSet
{
    private int[] set;


    // ----------------------------------------------------------
    /**
     * Create a new DisjointSet object.
     *
     * @param numElements
     */
    public DisjointSet(int numElements)
    {
        set = new int[numElements];
        for (int i = 0; i < set.length; i++)
        {
            set[i] = -1;
        }
    }


    // ----------------------------------------------------------
    /**
     * Union two items by height - union leaders only
     *
     * @param root1
     * @param root2
     */
    public void union(int root1, int root2)
    {
        // change all of root1 to a and root2 to b
        root1 = getLeader(root1);
        root2 = getLeader(root2);

        if (set[root2] < set[root1])
        {
            set[root1] = root2;
        }
        else
        {
            if (set[root1] == set[root2])
            {
                set[root1]--;
            }
            set[root2] = root1;
        }
    }


    // ----------------------------------------------------------
    /**
     * Get the leader of a set. Path compression.
     *
     * @param x
     * @return the leader in the set
     */
    public int getLeader(int x)
    {
        if (set[x] < 0)
        {
            return x;
        }
        else
        {
            return set[x] = getLeader(set[x]);
        }
    }


    // ----------------------------------------------------------
    /**
     * Determine if two elements are in the same set *
     *
     * @param a
     * @param b
     * @return true if in same set
     */
    public boolean inSameSet(int a, int b)
    {
        return getLeader(a) == getLeader(b);
    }


    public String toString()
    {
        String info = "";
        for (int i = 0; i < set.length; i++)
        {
            info = info + set[i];
        }
        return info;
    }
}
