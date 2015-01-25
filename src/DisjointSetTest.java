/**
 * Test the DisjointSet class
 *
 * @author Michael Goheen
 * @version Apr 10, 2012
 */
public class DisjointSetTest
{
    // ----------------------------------------------------------
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String args[])
    {
        //int testInt;
        boolean testBool;
        DisjointSet d = new DisjointSet(20);
        /*testInt = d.getLeader(2);
        System.out.println(testInt);
        testBool = d.inSameSet(1, 2);
        System.out.println(testBool);
        d.union(1, 2);
        testInt = d.getLeader(2);
        System.out.println(testInt);
        d.union(5, 6);
        testInt = d.getLeader(6);
        System.out.println(testInt);
        testBool = d.inSameSet(5, 2);
        System.out.println(testBool);
        d.union(6, 1); // Get Leader first before
// unioning
        testInt = d.getLeader(2);
        System.out.println(testInt);
        testBool = d.inSameSet(5, 2);
        System.out.println(testBool);*/
        d.union(2, 3);
        d.union(0, 2);
        d.union(0, 1);
        System.out.println(d);
        testBool = d.inSameSet(1, 3);
        System.out.println(testBool);
    }
}
