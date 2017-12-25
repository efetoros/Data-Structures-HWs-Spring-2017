/**
 * Created by efetoros on 5/4/17.
 */
public class HuffmanDecoder {
    public static void main(String[] args) {
        String name = args[0];

        ObjectReader or = new ObjectReader(name);

        Integer size = (Integer) or.readObject();
        BinaryTrie trie = (BinaryTrie) or.readObject();
        BitSequence bitSequence = (BitSequence) or.readObject();


//        String finalString = "";

        char[] chars = new char[size];
        int i = 0;
        while (bitSequence.length() != 0) {
            Match match = trie.longestPrefixMatch(bitSequence);
//            finalString += match.getSymbol();
            chars[i] = match.getSymbol();

            bitSequence = bitSequence.allButFirstNBits(match.getSequence().length());
            i ++;
        }
//        char[] chars = finalString.toCharArray();
        FileUtils.writeCharArray(args[1], chars);

    }
}
