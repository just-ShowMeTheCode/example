package rdis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2517:43
 */
public class RedisLRU<k, v> {
    private int capacity;

    private int count;

    private Map<k, Node<k, v>> nodeMap;

    private Node<k, v> head;

    private Node<k, v> tail;

    public RedisLRU(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        Node headNode = new Node(null,null);
        Node tailNode = new Node(null,null);
        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(k key,v value){
        Node<k,v> node = nodeMap.get(key);
        if(node == null) {
            if (count >= capacity) {
                removeNode();
            }
            node = new Node<>(key,value);
            addNode(node);
        }else {
            moveNodeToHead(node);
        }
    }


    public Node<k,v> get(k key){
        Node<k,v> node = nodeMap.get(key);
        if(node != null){
            moveNodeToHead(node);
        }
        return node;
    }

    public void removeNode(){
        Node node = tail.pre;
        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromList(Node<k,v> node){
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    private void addNode(Node<k,v> node){
        addToHead(node);
        nodeMap.put(node.key,node);
        count++;
    }

    public void moveNodeToHead(Node<k,v> node){
        removeFromList(node);
        addToHead(node);
    }

    public void addToHead(Node<k,v> node){
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }


    class Node<k, v> {
        k key;
        v value;
        Node pre;
        Node next;

        public Node(k key,v value){
            this.key = key;
            this.value = value;
        }

    }
}


