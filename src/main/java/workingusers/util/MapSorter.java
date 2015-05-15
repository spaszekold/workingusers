package workingusers.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Tomek on 2015-05-08.
 */
public class MapSorter implements Comparator<String> {

    Map<String,Long> base;

    public MapSorter(Map<String, Long> base) {
        this.base = base;
    }

    @Override
    public int compare(String o1, String o2) {
        if (base.get(o1) >= base.get(o2)) {
            return 1;
        } else {
            return -1;
        }
    }
}
