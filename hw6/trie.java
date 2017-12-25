import java.util.HashMap;

/**
 * Created by efetoros on 5/1/17.
 */
public class trie {
    Node root;

    private class Node {
        char name;
        HashMap<Character, Node> adjList = new HashMap<Character, Node>();
        boolean isIsword;

        Node() {}

        Node(char c) {
            this.name = c;
        }

    }

    trie() {
        root = new Node();
    }


    public void add(String word) {
        HashMap<Character, Node> neighbors = root.adjList;
        for (int i = 0; i < word.length(); i++) {
            Node N;
            char temp = word.charAt(i);
//            if (!Character.isLetter(temp)) continue;
            if (neighbors.containsKey(temp)) {
                N = neighbors.get(temp);
            } else {
                N = new Node(temp);
                neighbors.put(temp, N);
            }

            neighbors = N.adjList;
            if (word.length() - 1 == i && word.length() > 2) {
                N.isIsword = true;
            }
            }
        }

    public boolean checkForWord(String word) {
        HashMap<Character, Node> adjlist = root.adjList;
        HashMap<Character, Node> lastadjlist = root.adjList;
        int lastdigit = 0;
        for (int i = 0; i < word.length(); i ++) {
            if (!adjlist.containsKey(word.charAt(i))) {
                return false;
            }
            if (adjlist.get(word.charAt(i)).adjList.isEmpty() && adjlist.get(word.charAt(i)).isIsword != true) {
                return false;
            }
            lastadjlist = adjlist;
            adjlist = adjlist.get(word.charAt(i)).adjList;
            lastdigit ++;
        }
        boolean answer = lastadjlist.get(word.charAt(word.length() - 1)).isIsword;
        return answer;
    }

    public boolean checkifPrefix(String word) {
        HashMap<Character, Node> tracker = root.adjList;
        for (char c : word.toCharArray()) {
            if (!tracker.containsKey(c)) {
                return false;
            }
            tracker = tracker.get(c).adjList;
        }
        return true;
    }

    }


