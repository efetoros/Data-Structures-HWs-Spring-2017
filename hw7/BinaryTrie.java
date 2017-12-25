import java.io.Serializable;
import java.util.*;

/**
 * Created by efetoros on 5/3/17.
 */

public class BinaryTrie implements Serializable {
    Node complete;

    private class Node implements Comparable<Node>, Serializable {
        Character c;
        Node left;
        Node right;
        Integer frequency;
        boolean isLeaf;

        Node(Character ch, Node left, Node right, Integer frequency, Boolean isLeaf) {
            this.c = ch;
            this.left = left;
            this.right = right;
            this.frequency = frequency;
            this.isLeaf = isLeaf;
        }

        @Override
        public int compareTo(Node n) {
            return this.frequency - n.frequency;

        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        PriorityQueue<Node> ordered = new PriorityQueue<>();
        Iterator<Character> keys = frequencyTable.keySet().iterator();
        while (keys.hasNext()) {
            Character keyName = keys.next();
            Node temp = new Node(keyName,null,null,frequencyTable.get(keyName), true);
            ordered.add(temp);
        }

        while (ordered.size() != 1) {
            Node temp1 = ordered.poll();
            Node temp2 = ordered.poll();
            Node newNode;
            if (temp1.isLeaf && !temp2.isLeaf) {
                newNode = new Node(null, temp2, temp1, temp1.frequency + temp2.frequency, false);
            } else {
                newNode = new Node(null, temp1, temp2, temp1.frequency + temp2.frequency, false);
            }
            ordered.add(newNode);

        }
        this.complete = ordered.poll();

    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        Node pointer = complete;
        int i = 0;
//        String bit = "";
        while (!pointer.isLeaf) {
            if (0 == querySequence.bitAt(i)) {
                pointer = pointer.left;
            } else {
                pointer = pointer.right;
            }
//            bit += temp;
            i ++;
        }

        Match answer = new Match(querySequence.firstNBits(i), pointer.c);
        return answer;
    }

    public void helperBuildLookup(Map<Character, BitSequence> container, Node currentNode, String sequence) {
        if (currentNode.isLeaf) {
            BitSequence bit = new BitSequence(sequence);
            container.put(currentNode.c, bit);
        } else {
            helperBuildLookup(container, currentNode.right, sequence + "1");
            helperBuildLookup(container, currentNode.left, sequence + "0");
        }
    }

    public Map<Character, BitSequence> buildLookupTable() {
        HashMap<Character, BitSequence> container = new HashMap<>();
        helperBuildLookup(container, complete, "");
        return container;

    }

}

