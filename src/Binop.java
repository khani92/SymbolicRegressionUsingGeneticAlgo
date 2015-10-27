import java.util.Random;

/**
 * This is an abstract class that is extended by binary operators
 * 
 * @author Nikhil
 * 
 */
public abstract class Binop extends Node {

	/**
	 * Child nodes of the class
	 */
	protected Node lChild, rChild;

	Binop(Node lC, Node rC) {
		super();
		lChild = lC;
		rChild = rC;
	}

	/**
	 * Default Constructor
	 */
	Binop() {
		super();
	}

	/**
	 * This is used to add child nodes to the current nodes. If depth < maxDepth
	 * the child nodes will be an operator or terminal decided at random. Else
	 * at depth==maxDepth the tree is at the bottom and can only have constants
	 * or dependent variables.
	 * 
	 * This method recursively populates the entire left arm of the tree and
	 * them moves to the right on its way back from recursion.
	 */
	@Override
	void addRandomKids(OperatorFactory o, TerminalFactory t, int maxDepth,
			Random rand) {

		// Reached max depth. Can only add Terminal
		if (getDepth() == maxDepth) {
			lChild = TerminalFactory.getTerminal(rand);
			rChild = TerminalFactory.getTerminal(rand);
			lChild.setDepth(getDepth() + 1);
			rChild.setDepth(getDepth() + 1);
		} else if (getDepth() < maxDepth) {
			// Still less than the max depth. Add either a Binop or a
			// Const/Variable.

			int randNum = rand.nextInt(OperatorFactory.getNumOperator()
					+ TerminalFactory.getNumIndepVars());

			// If randNum is < number of operators. Add Binop from
			// OperatorFactory
			if (randNum < OperatorFactory.getNumOperator()) {
				lChild = OperatorFactory.getOperator(rand);
				lChild.setDepth(getDepth() + 1);
				lChild.addRandomKids(o, t, maxDepth, rand);
			}
			// Else randNum > numOfOperators. Add a terminal to the node
			else {
				// Stopping condition for recursion
				lChild = TerminalFactory.getTerminal(rand);
				lChild.setDepth(getDepth() + 1);
				// Tree terminated no recursive call
			}

			// Now right child's turn
			randNum = rand.nextInt(OperatorFactory.getNumOperator()
					+ TerminalFactory.getNumIndepVars());

			if (randNum < OperatorFactory.getNumOperator()) {
				rChild = OperatorFactory.getOperator(rand);
				rChild.setDepth(getDepth() + 1);
				rChild.addRandomKids(o, t, maxDepth, rand);
			} else {
				rChild = TerminalFactory.getTerminal(rand);
				rChild.setDepth(getDepth() + 1);
			}
		}
	}

	/**
	 * Keeps a total count of all nodes in the tree. Number first starts along
	 * the extreme left branch of the tree from root to leaf. Then from the left
	 * leaf it moves to the right and thereon.
	 */
	public int mySize() {
		int size = 1;
		size += lChild.mySize();
		size += rChild.mySize();
		return size;
	}

	/**
	 * Find the parent and child nodes for crossover. There are three
	 * NodePairPlus objects; exactly one of them will be returned, depending on
	 * whether the current node is the parent, or the parent is in the left
	 * subtree, or the parent is in the right subtree. clipNumber is the
	 * pre-determined location of the clip point. nodeNumber is the number of
	 * the current node. If nodeNumber + 1 == clipNumber, the current node is
	 * the parent, and the left child is the child. Otherwise, send testLeft
	 * down the left subtree. If it comes back instantiated, i.e.,
	 * testLeft.parent != null, then the clip point was found down the left
	 * subtree. Return testLeft. If testLeft's counter is one less than the clip
	 * number, the current node is the parent and the right child is the child.
	 * Otherwise, search for the clip point down the right subtree.
	 */
	public NodePairPlus traceTree(int nodeNumber, int clipNumber) {
		NodePairPlus myReturn = new NodePairPlus();
		NodePairPlus testLeft = new NodePairPlus();
		NodePairPlus testRight = new NodePairPlus();
		if (nodeNumber + 1 == clipNumber) {
			myReturn.parent = this;
			myReturn.child = lChild;
			myReturn.counter = clipNumber;
			return myReturn;
		}
		testLeft = lChild.traceTree(nodeNumber + 1, clipNumber);
		if (testLeft.parent != null)
			return testLeft;
		if (testLeft.counter + 1 == clipNumber) {
			myReturn.parent = this;
			myReturn.child = rChild;
			myReturn.counter = clipNumber;
			return myReturn;
		}
		testRight = rChild.traceTree(testLeft.counter + 1, clipNumber);
		return testRight;
	}

	/**
	 * Exchange a child tree with a different child tree (one half of the
	 * crossover operation). Test to see which of lChild or rChild is the old
	 * node, and replace oldChild with newChild.
	 */
	public void changeChild(Node oldChild, Node newChild) {
		if (lChild == oldChild)
			lChild = newChild;
		else if (rChild == oldChild)
			rChild = newChild;
		else
			System.out.println("Can't find oldChild in BinOp.changeChild()");
	}

	public Node getlChild() {
		return lChild;
	}

	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}

	public Node getrChild() {
		return rChild;
	}

	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}

}