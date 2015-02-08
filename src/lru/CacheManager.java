package lru;

import java.util.HashMap;

/**
 * Created by gbhatt on 2/6/15.
 */
public class CacheManager implements EdcastCache {

    private static CacheManager cacheManager;

    /* The HashMap to hold the Cached objects */
    private HashMap<Object, Node> cacheMap = new HashMap<Object, Node>();

    /* Node head to indicate the head of the list, the most recently used object */
    private Node head;

    /* Node head to indicate the end of the list, the least recently used object */
    private Node end;

    /* determines the size of the CacheMap */
    private int size;

    /* counter to keep track of elements added in the CacheMap */
    private int length;


    /**
     * Get the CacheManager instance
     * @return CacheManager instance
     * */
    public static CacheManager getInstance() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }


    @Override
    public void put(Object id, Object o) {
        // item to be inserted already exists, so update the item with new value and make it the head
        if (cacheMap.containsKey(id)) {
            Node existNode = cacheMap.get(id);
            existNode.value = o;
            evict(existNode);
            setHead(existNode);
        } else { // item does not exist so create a new Node and add it to the CacheMap and make it the head
            Node newNode = new Node(id, o);

            // if the CacheMap has space to include new item, set the new item as head, and increment length
            if (length < size) {
                setHead(newNode);
                cacheMap.put(id, newNode);
                length++;
            } else { // else, remove the end to make space, put it in the CacheMap, and make it head
                cacheMap.remove(end.key);
                end = end.previous;

                if (end != null) {
                    end.next = null;
                }

                setHead(newNode);
                cacheMap.put(id, newNode);
            }
        }
    }

    @Override
    public Object get(Object id) {
        //return the item if it exists in the CacheMap
        if (cacheMap.containsKey(id)) {
            Node latest = cacheMap.get(id);
            evict(latest);
            setHead(latest);
            return latest.value;
        } else {
            //TODO: bonus?
            return null;
        }
    }

    @Override
    public void setSize(int size ) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return cacheMap.size();
    }

    /**
     * Evict the given node from the list
     * @param node to be evicted
     * */
    private void evict(Node node) {
        Node current = node;
        Node previous = current.previous;
        Node next = current.next;

        if (previous != null) {
            previous.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.previous = previous;
        } else {
            end = previous;
        }
    }

    /**
     * Set the given node as the head
     * @param node to be set as head
     * */
    private void setHead(Node node) {
        node.next = head;
        node.previous = null;
        if (head != null) {
            head.previous = node;
        }

        head = node;

        if (end == null) {
            end = node;
        }

    }

    public void printCache() {
        System.out.println("-------");
        Node node = head;
        while (node != null) {
            System.out.println(node.key + " - " + node.value);
            node = node.next;
        }
        System.out.println("-------");
    }

    /**
     * Node data structure to hold the objects to be cached.
     * head is to indicate the most recently used item
     * end is to indicate least recently used item
     * */
    class Node {
        public Object value;
        public Object key;
        public Node previous;
        public Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
