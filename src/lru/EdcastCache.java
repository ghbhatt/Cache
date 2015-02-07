package lru;

/**
 * Created by gbhatt on 2/6/15.
 */
public interface EdcastCache {

    public void put(int id, Object o);
    public Object get(int id);
    public void size(int size);

}
