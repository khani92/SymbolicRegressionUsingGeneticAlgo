import java.util.Random;

public class Variable extends Node {

	private int index;

	Variable(int index) {
		this.index = index;
	}

	double eval(double[] data) {
		return data[index];
	}

	@Override
	public String toString() {
		return (String.valueOf("X" + index));
	}

	@Override
	void addRandomKids(OperatorFactory o, TerminalFactory t, int maxDepth,
			Random rand) {
		// Do nothing as Var is a terminal and a child cannot be added
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
		// Do nothing. Variables don't have children
	}

	public Node duplicate() {
		return new Variable(index);
	}
}