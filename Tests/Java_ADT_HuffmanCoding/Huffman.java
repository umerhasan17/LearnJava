import java.util.HashMap;
import java.util.Map;

public class Huffman {

	private HuffmanData[] leafEntries;
	private final static int SIZE = 50;
	private PriorityQueueInterface<BinaryTreeInterface<HuffmanData>> pq;
	private BinaryTreeInterface<HuffmanData> huffmanTree;

	public Huffman() {
		leafEntries = new HuffmanData[SIZE];
		pq = new PriorityQueue<BinaryTreeInterface<HuffmanData>>();
		huffmanTree = new BinaryTree<HuffmanData>();
	}

	public void setFrequencies() {
	// Create test data and store as an array of HuffData
		leafEntries[0] = new HuffmanData(5000, 'a');
		leafEntries[1] = new HuffmanData(2000, 'b');
		leafEntries[2] = new HuffmanData(10000, 'c');
		leafEntries[3] = new HuffmanData(8000, 'd');
		leafEntries[4] = new HuffmanData(22000, 'e');
		leafEntries[5] = new HuffmanData(49000, 'f');
		leafEntries[6] = new HuffmanData(4000, 'g');
	}

	public void setPriorityQueue() {
	// Copy test data from array LeafEntries of HuffData
	// into a PriorityQueue of HuffmanTree
		for (int i = 0; i < 7; i++) {
			if (leafEntries[i].getFrequency() > 0)
				pq.add(new BinaryTree<HuffmanData>(leafEntries[i]));
		}
	}

	public void createHuffmanTree() {
		// Process PriorityQueue pq until there is a complete HuffmanTree
		while (pq.getSize() > 1) {
			BinaryTreeInterface<HuffmanData> b1 = pq.removeMin();
			BinaryTreeInterface<HuffmanData> b2 = pq.removeMin();
			HuffmanData newRootData =
							new HuffmanData(b1.getRootData().getFrequency() +
											b2.getRootData().getFrequency());
			BinaryTreeInterface<HuffmanData> newB =
							new BinaryTree<HuffmanData>(newRootData,
											(BinaryTree) b1,
											(BinaryTree) b2);
			pq.add(newB);
		}
		huffmanTree = pq.getMin();
	}

	public void printCode() {
		printCodeProcedure("", huffmanTree);
	}

	private void printCodeProcedure(String code, BinaryTreeInterface<HuffmanData> tree) {
	// Print out a complete HuffmanTree
				if (tree == null) {

				} else {
					printCodeProcedure(code + "0", tree.getLeftSubtree());
					printCodeProcedure(code + "1", tree.getRightSubtree());
					if (tree.getRootData().getSymbol() != '\u0000') {
						System.out.println(tree.getRootData().getSymbol() + ": " + code);
					}
				}

	}
}
