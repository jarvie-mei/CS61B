package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> madStringInt = new MaxArrayDeque<>(intComparator);
        MaxArrayDeque<String> madString = new MaxArrayDeque<>(stringComparator);

        for (int i = 0; i < 10; i++) {
            madStringInt.addFirst(i);
        }
        int maxValue = madStringInt.max();
        assertEquals("The max item should be 9", 9, maxValue, 0.0);

        madString.addFirst("Michael");
        madString.addFirst("Johnson");
        madString.addLast("Sandy");
        madString.addFirst("Genie");

        assertEquals("Sandy", madString.max());
        assertEquals("Johnson", madString.max(stringByLengthComparator));
    }

    Comparator<Integer> intComparator = (a, b) -> a - b;
    Comparator<String> stringComparator = (s1, s2) -> s1.compareTo(s2);
    Comparator<String> stringByLengthComparator = (s1, s2) -> s1.length() - s2.length();
}
