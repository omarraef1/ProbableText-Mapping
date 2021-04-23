
// Omar R. Gebril 	SID: 23323978 	CSC210

import java.util.Iterator;
import java.util.LinkedList;

// OurMap has the major methods if java.util.Map: put<K,V> get<K> containsKey(K) remove(K)
public class OurMap<K, V> {

	// Map a key to a value as one object.
	public class HashNode {
		private K key;
		private V value;

		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	// Instance variables
	private static final int TABLE_SIZE = 20000;
	private LinkedList<HashNode>[] lists;
	int n;

	// Construct an empty map as an array of LinkedLists
	@SuppressWarnings("unchecked")
	public OurMap() {
		n = 0;
		// Build the array of LinkedList<E> objects
		lists = new LinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			lists[i] = new LinkedList<HashNode>();
	}

	// Precondition: Type K must override hashCode() like String
	// and Integer already do. This methods returns 0..TABLE_SIZE-1
	private int hashCode(K key) {
		// String's hashCode can have arithmetic overflow so it can be negative
		return Math.abs(key.hashCode()) % TABLE_SIZE;
	}

	// Return the number of Mappings
	public int size() {
		return n;
	}

	// If the key is not in use, Map the key to the value and add the HashNode
	// to the correct LinkedList and return null.
	//
	// If there was a mapping to the key, destroy it and return
	// the value that used to be mapped to the key.
	public V put(K key, V value) {
		LinkedList<HashNode> hash = lists[hashCode(key)];
		for (int i = 0; i < hash.size(); i++) {
			if (hash.get(i).key.equals(key)) {
				V val = hash.get(i).value;
				hash.get(i).value = value;
				return val;
			}
		}
		hash.add(new HashNode(key, value));
		n++;
		return null;
	}

	// Return the value to which key is mapped or null when the key is not found
	public V get(K key) {
		LinkedList<HashNode> hash = lists[hashCode(key)];
		for (int i = 0; i < hash.size(); i++) {
			if (hash.get(i).key.equals(key)) {
				return hash.get(i).value;
			}
		}
		return null;
	}

	// Return true if the mapping already exists in this Map.
	public boolean containsKey(K key) {
		LinkedList<HashNode> hash = lists[hashCode(key)];
		for (int i = 0; i < hash.size(); i++) {
			if (hash.get(i).key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	// If the key exists, remove the mapping and return the value associated
	// with specified key. If the key is not found, return null.
	public V remove(K key) {
		LinkedList<HashNode> hash = lists[hashCode(key)];
		for (int i = 0; i < hash.size(); i++) {
			if (hash.get(i).key.equals(key)) {
				V val = hash.get(i).value;
				hash.remove(i);
				n--;
				return val;
			}
		}
		return null;
	}
}