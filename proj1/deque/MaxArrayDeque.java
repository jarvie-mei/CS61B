package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max() {
        return max(comp);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 0; i < size(); i++) {
            T candidate = get(i);
            if (c.compare(candidate, maxItem) > 0) {
                maxItem = candidate;
            }
        }
        return maxItem;
    }
}
