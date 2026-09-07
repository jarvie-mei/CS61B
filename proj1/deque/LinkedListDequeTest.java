package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    public void getTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertEquals("Should have the same value", (Integer) 1, (Integer) lld1.get(0));
        assertEquals("Should have the same value", (Integer) 3, (Integer) lld1.get(2));

        assertNull("The value should be null", lld1.get(3));
        assertNull("The value should be null", lld1.get(-2));

        assertEquals("Should have the same value", (Integer) 1, (Integer) lld1.getRecursive(0));
        assertEquals("Should have the same value", (Integer) 3, (Integer) lld1.getRecursive(2));

        assertNull("The value should be null", lld1.getRecursive(3));
        assertNull("The value should be null", lld1.getRecursive(-2));
    }

    @Test
    public void equalTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        LinkedListDeque<String> lld3 = new LinkedListDeque<>();

        lld1.addFirst("Hi, ");
        lld1.addLast("My");
        lld1.addLast("Friend");

        lld2.addFirst("Hi, ");
        lld2.addLast("My");
        lld2.addLast("Friend");

        lld3.addFirst("Hi, ");
        lld3.addLast("My");
        lld3.addLast("love");

        assertTrue("lld1 and lld2 should be equal", lld1.equals(lld2));
        assertFalse("lld1 should not be equal to ldd3",lld1.equals(lld3));
        assertEquals("lld1 and lld2 should be equal", lld1, lld2);
    }

    @Test
    public void randomTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        int N = 50000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 7);

            switch (operationNumber) {
                case 0:
                    int randVal1=  StdRandom.uniform(0, 1000);
                    ad.addLast(randVal1);
                    lld1.addLast(randVal1);
                    break;
                case 1:
                    int randVal2=  StdRandom.uniform(0, 1000);
                    ad.addFirst(randVal2);
                    lld1.addFirst(randVal2);
                    break;
                case 2:
                    assertEquals(lld1.size(), ad.size());
                    break;
                case 3:
                    if (!ad.isEmpty() && !lld1.isEmpty()) {
                        int randIndex = StdRandom.uniform(0, ad.size());
                        assertEquals(lld1.get(randIndex), ad.get(randIndex));
                    }
                    break;
                case 4:
                    assertEquals(lld1.removeFirst(), ad.removeFirst());
                    break;
                case 5:
                    assertEquals(lld1.removeLast(), ad.removeLast());
                    break;
                case 6:
                    assertEquals(lld1.isEmpty(), ad.isEmpty());
            }
        }

    }
}
