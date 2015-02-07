package lru;


/**
 * Created by gbhatt on 2/7/15.
 */
public class EdcastMain {

    public static void main(String[] args) {

        CacheManager cm = CacheManager.getInstance();
        cm.size(5);

        cm.put(1, "a");
        cm.printCache();

        cm.put(2, "b");
        cm.printCache();

        Object one = cm.get(1);
        System.out.println(String.valueOf(one));

        Object two = CacheManager.getInstance().get(3);
        System.out.println(String.valueOf(two));

        CacheManager.getInstance().put(3, "c");
        CacheManager.getInstance().printCache();

    }
}
