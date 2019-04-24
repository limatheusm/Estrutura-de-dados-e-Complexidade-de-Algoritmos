import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.HashMap;

public class Huffman {
  private HuffmanNode root;

  /**
   * Get array of characters from text
   * 
   * @param text Text
   * @return Array of characters from text
   */
  private char[] getChars(String text) {
    char[] letters = new char[text.length()];
    text.getChars(0, text.length(), letters, 0);
    return letters;
  }

  /**
   * Responsible for frenquency counting of symbols from the letter array. Min
   * Heap is used as a priority queue. The value of frequency field is used to
   * compare two nodes in min heap. Initially, the least frequent character is at
   * root
   * 
   * @param letters
   * @return Prioritized list containing nodes in ascending order of frequency
   */
  private PriorityQueue<HuffmanNode> buildMinHeap(char[] letters) {
    Map<Character, HuffmanNode> count = new HashMap<>();

    for (char ch : letters) {
      if (!count.containsKey(ch)) {
        count.put(ch, new HuffmanNode(ch));
      }
      // Update frequency
      count.get(ch).addOccurrence();
    }

    return new PriorityQueue<>(count.values());
  }

  /**
   * Responsible for creating huffman tree. The most frequent symbols will be
   * closer to the root. The nodes with the letters will always be on the leafs
   * 
   * @param nodes
   * @return Root node of the huffman tree
   */
  private HuffmanNode buildTree(PriorityQueue<HuffmanNode> nodes) {
    while (true) {
      // 1. Take the two lowest frequent nodes
      HuffmanNode firstNode = nodes.poll();
      HuffmanNode secondNode = nodes.poll();

      // 2. Group them (lower left frequency)
      HuffmanNode parent = new HuffmanNode(firstNode, secondNode);

      // 3. If the queue is empty, the group is the root
      if (nodes.isEmpty())
        return parent;

      // 4. Otherwise, insert back the group in the tree
      nodes.add(parent);
    }
  }

  /**
   * Responsible for creating the table that relates the symbol to its new binary
   * sequency
   * 
   * @return Map with symbol and new binary symbol representation
   */
  private Map<Character, String> buildCodeMap() {
    Map<Character, String> result = new TreeMap<>();
    root.fillCodeMap(result, "");
    return result;
  }

  public String encode(String text) {
    // 1. Get array of symbols from text
    char[] letters = getChars(text);

    // 2. Build MinHeap based on frequency
    PriorityQueue<HuffmanNode> minHeap = buildMinHeap(letters);

    // 3. Build Huffman Tree with Min heap
    root = buildTree(minHeap);

    // 4. Build code map
    Map<Character, String> codemap = buildCodeMap();

    // 5. Replace each letters with the correspondet symbol
    StringBuilder data = new StringBuilder();
    for (char ch : letters) {
      data.append(codemap.get(ch));
    }

    return data.toString();
  }

  public String decode(String data) {
    // 1. Start at the root
    HuffmanNode current = root;

    // 2. Get array of bits from data
    char[] bits = getChars(data);

    // 3. Iterate array of bits
    StringBuilder result = new StringBuilder();
    for (char bit : bits) {
      // 3.1. If bit is equal to '0', then go to the left node,
      if (bit == '0') {
        current = current.getLeft();
      }
      // 3.2. Otherwise, go to the right node
      else {
        current = current.getRight();
      }

      // 3.3. If current is the leaf, print correspondent symbol
      if (current.isLeaf()) {
        result.append(current.getSymbol());
        // 3.4. Restart: Back to the root
        current = root;
      }
    }

    return result.toString();
  }
}
