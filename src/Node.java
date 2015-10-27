import java.util.Random;

public abstract class Node implements Cloneable {

	/**
	 * Number associated with each node representing the height of the tree at
	 * that level
	 */
	private int depth = 0;

	/**
	 * This method is used to evaluate the value of the expression. Overridden
	 * in Plus, Minus operators and const classes
	 */
	abstract double eval(double[] data);

	/**
	 * This method is overridden in the subclasses and based on the height of
	 * the tree it will be used to add a Binop or Constant/Variable to the tree
	 * 
	 * @param o
	 *            OperatorFactoryObject. So that the overloaded methods can
	 *            generate binary operator at random
	 * @param t
	 *            TerminalFactoryObject. So that the overloaded methods can
	 *            generate terminal (either constants or variable at random
	 * @param maxDepth
	 *            : Depth of the tree. At the bottom of the tree there can only
	 *            be Constant or Variable objects
	 * @param rand
	 */
	abstract void addRandomKids(OperatorFactory o, TerminalFactory t,
			int maxDepth, Random rand);

	public abstract NodePairPlus traceTree(int nodeNumber, int clipNumber);

	public abstract void changeChild(Node oldChild, Node newChild);

	public abstract Node duplicate();

	@Override
	public Node clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (Node) o;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int mySize() {
		return 1;
	}

}