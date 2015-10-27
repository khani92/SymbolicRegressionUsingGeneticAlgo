import java.util.Random;

public class Const extends Node {

	private double value;

	Const(double val) {
		value = val;
	}

	double eval(double[] data) {
		return (Double.parseDouble(String.format("%.2f", value)));
	}

	@Override
	public String toString() {
		return (String.format("%.2f", value));
	}

	@Override
	void addRandomKids(OperatorFactory o, TerminalFactory t, int maxDepth,
			Random rand) {
		// Do nothing. Const is a terminal and a child cannot be added
	}

	/**
	 * Returns a NodePairPlus object whose parent and child are null, and whose
	 * counter equals the incoming nodeNumber.
	 */
	public NodePairPlus traceTree(int nodeNumber, int clipNumber) {
		NodePairPlus p = new NodePairPlus();
		p.parent = null;
		p.child = null;
		p.counter = nodeNumber;
		return p;
	}

	public void changeChild(Node oldChild, Node newChild) {
		// Do nothing. Constants don't have children
	}

	public Node duplicate() {
		return new Const(value);
	}
}