import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V> implements Iterable<KVPair<K,V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private Random rng;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        this.rng = new TestableRandom();
    }

    /** returns a random level (using geometric distribution), minimum of 1 */
	// TODO Ideally, you should call this method inside other methods 
	// keep this method private. Since, we do not have any methods to call
	// this method at this time, we keep this publicly accessible and testable.  
	public int randomLevel() {
		int level = 0;
		while (rng.nextBoolean())
			level++;
		return level;
	}


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        // ADAPTED FROM OPEN DSA CODE

        SkipNode x = head; // Dummy header node
        int level = head.level;
        for (int i = level; i >= 0; i--) { // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) { // go forward
                x = x.forward[i]; // Go one last step
            }
        }
        
        //array list to b returned
        ArrayList<KVPair<K, V>> temp = new ArrayList<KVPair<K, V>>();
        //x is the next node
        x = x.forward[0];
        
        // add every proceeding node to the array list until the end or no
        // longer in range of the right name.
        while((x != null) && (x.element().getKey().compareTo(key) == 0)){
            temp.add(x.element());
            x = x.forward[0];
        }
        
        //return either an empty or complete list.
        return temp;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        // ADAPTED FROM OPEN DSA CODE

        int newLevel = randomLevel(); // New node's level
        int level = head.level;
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(SkipNode.class, level
            + 1);
        SkipNode x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {
        // ADAPTED FROM OPEN DSA CODE
        
        SkipNode temp = head;
        int level = head.level;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        return null;
    }
  
    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
  
        return null;
    }

    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
  
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            //pair = tempPair;
          forward = (SkipNode[])Array.newInstance(SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }
    
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;
		
        public SkipListIterator() {
        	current = head;
        }
        @Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.forward[0] != null;
		}

		@Override
		public KVPair<K, V> next() {
			// TODO Auto-generated method stub
			KVPair<K, V> elem = current.forward[0].element();
			current = current.forward[0];
			return elem;
		}
    	
    }

	@Override
	public Iterator<KVPair<K,V>> iterator() {
		// TODO Auto-generated method stub
		return new SkipListIterator();
	}

}
