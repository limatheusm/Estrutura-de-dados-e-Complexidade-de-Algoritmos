public class Main {
  public static void main(String[] args) {
    String defaultText = "Prefix Codes, means the codes (bit sequences) are "
        + "assigned in such a way that the code assigned to one character is not the "
        + "prefix of code assigned to any other character. This is how Huffman Coding "
        + "makes sure that there is no ambiguity when decoding the generated bitstream.";
    Huffman huffman = new Huffman();

    String encodedText = huffman.encode(defaultText);
    System.out.println("Encoded data: \n" + encodedText);

    String decodedText = huffman.decode(encodedText);
    System.out.println("\nDecoded text: \n" + decodedText);

    int normalSize = defaultText.length() * 8;
    int compressedSize = encodedText.length();
    double rate = 100.0 - (compressedSize * 100.0 / normalSize);

    System.out.println("\nNormal size: " + normalSize);
    System.out.println("Compressed size: " + compressedSize);
    System.out.printf("\nCompressed is %.2f%% smaller than the original. %n", rate);
  }
}