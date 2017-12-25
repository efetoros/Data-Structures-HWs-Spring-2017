import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by efetoros on 5/4/17.
 */
public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        HashMap<Character, Integer> container = new HashMap<>();
        for (Character cha : inputSymbols) {
            if (container.containsKey(cha)) {
                container.put(cha, container.get(cha) + 1);
            } else {
                container.put(cha, 1);
            }
        }
        return container;
    }
    public static void main(String[] args) {

            String name = args[0];

            char[] input = FileUtils.readFile(name);

            Map<Character, Integer> frequencyTable = buildFrequencyTable(input);
            BinaryTrie trie = new BinaryTrie(frequencyTable);

            Map<Character, BitSequence> lookUpTable = trie.buildLookupTable();

            LinkedList<BitSequence> bitList = new LinkedList<>();
            Integer size = new Integer(0);

            for (Character ch : input) {
                bitList.add(lookUpTable.get(ch));
                size += 1;
            }
            BitSequence write = BitSequence.assemble(bitList);
            ObjectWriter ow = new ObjectWriter(name + ".huf");

            ow.writeObject(size);
            ow.writeObject(trie);
            ow.writeObject(write);

    }
}
