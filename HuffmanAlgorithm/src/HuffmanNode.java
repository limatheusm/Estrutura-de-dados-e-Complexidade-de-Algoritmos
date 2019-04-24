import java.util.Map;

class HuffmanNode implements Comparable<HuffmanNode> {
  private int count;
  private char symbol;

  private HuffmanNode left;
  private HuffmanNode right;

  // MARK: Constructors

  public HuffmanNode(char symbol) {
    this.symbol = symbol;
  }

  public HuffmanNode(HuffmanNode left, HuffmanNode right) {
    this.symbol = '+';
    this.left = left;
    this.right = right;
  }

  // MARK: Methods

  /**
   * Update frequency of symbol
   */
  public void addOccurrence() {
    count++;
  }

  /**
   * Calculate frequency of node
   * 
   * @return
   */
  public int getFrequency() {
    if (isLeaf())
      return count;
    return left.getFrequency() + right.getFrequency();
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }

  /**
   * Traverse the tree in depth, adding the bit zero when we left or 1 when we go
   * to the right. If it's leaf, just return the generated sequence
   * 
   * @param codemap
   * @param work
   */
  public void fillCodeMap(Map<Character, String> codemap, String work) {
    if (isLeaf()) {
      codemap.put(getSymbol(), work);
      return;
    }

    left.fillCodeMap(codemap, work + "0");
    right.fillCodeMap(codemap, work + "1");
  }

  // MARK: Getters

  public char getSymbol() {
    return symbol;
  }

  public HuffmanNode getLeft() {
    return left;
  }

  public HuffmanNode getRight() {
    return right;
  }

  // MARK: Overrides

  @Override
  public int compareTo(HuffmanNode o) {
    return getFrequency() - o.getFrequency();
  }

  @Override
  public String toString() {
    String ch = symbol == '\n' ? "\\n" : "" + symbol;
    return String.format("'%s': %d", ch, getFrequency());
  }
}