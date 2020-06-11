import java.util.HashSet;
import java.util.Set;

/**
 * Key Value class I made to keep track of coordinates
 * @param <U> key
 * @param <V> value
 */
public class Pair <U, V> {

    /**
     * key of pair
     */
    private U key;

    /**
     * value of pair
     */
    private V value;

    /**
     * Constructs a pair with a key and value.
     *
     * @param key  the first element
     * @param value the second element
     */
    public Pair(U key, V value) {

        this.key = key;
        this.value = value;
    }


    /**
     * returns value of pair
     * @return
     */
    public V getValue(){
        return this.value;
    }

    /**
     * returns key of pair
     * @return
     */
    public U getKey(){
        return this.key;
    }

    /**
     * Returns whether a pair is inside a set of pairs.
     * @param visited
     * @return
     */
    public boolean isInside(Set<Pair<Integer, Integer>> visited){
            for(Pair pair: visited){
                if(this.getValue() == pair.getValue() && this.getKey() == pair.getKey()){
                    return true;
                }
            }
        return false;
    }

    }
