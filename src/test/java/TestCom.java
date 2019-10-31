import org.assertj.core.util.Maps;

import java.util.HashMap;
import java.util.Map;

public class TestCom {
    public static void main(String[] args) {
        System.out.println(777);
        Map<Object, Object> map = Maps.newHashMap(null,"");
        //noinspection StatementWithEmptyBody
        HashMap<Object, Object> objectHashMap = new HashMap<>();
        int aa =4;
        if (aa> 010) {
        }
    }
    public static <K, V> Map<K, V> newHashMap(K key, V value) {
        Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
