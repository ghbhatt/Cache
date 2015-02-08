package lru;


/**
 * Created by gbhatt on 2/7/15.
 */
public class EdcastMain {

    public static void main(String[] args) {

        CacheManager cm = CacheManager.getInstance();
        cm.setSize(5);

        cm.put(5.6, "a");
        cm.printCache();

        cm.put("string", "b");
        cm.printCache();

        Object one = cm.get("string");
        System.out.println(String.valueOf(one));

        Object two = cm.get(3);
        System.out.println(String.valueOf(two));

        cm.put(true, "c");
        cm.printCache();

        System.out.println(cm.getSize());

    }
}
