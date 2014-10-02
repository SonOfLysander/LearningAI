package geneticAlgorithm;

public class ExpressionTree {

	private ExpressionTree() {

	}

	private class Node {
		private Node left, right;
		byte value;

		private Node(byte nodeValue) {
			this(nodeValue, null, null);
		}

		private Node(byte nodeValue, Node leftNode, Node rightNode) {
			value = nodeValue;
			left = leftNode;
			right = rightNode;
		}

		private boolean isLeaf() {
			if (left == null && right == null)
				return true;
			return false;
		}
	}

}