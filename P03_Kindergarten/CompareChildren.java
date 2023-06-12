package kindergarten;

import java.util.Comparator;

public class CompareChildren implements Comparator<Child> {
    @Override
    public int compare(Child o1, Child o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
