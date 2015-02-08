package lru;

/**
 * Created by gbhatt on 2/6/15.
 */
public interface EdcastCache {

    public void put(Object id, Object o);
    public Object get(Object id);
    public void setSize(int size);
    public int getSize();

}
