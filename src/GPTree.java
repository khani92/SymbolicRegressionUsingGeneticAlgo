import java.util.List;
import java.util.Random;

public class GPTree implements Comparable<GPTree> {

	private Node root;
	private double fitness = 0.0;

	GPTree() {
		root = null;
	}

	GPTree(OperatorFactory o, TerminalFactory t, int maxDepth, Random rand) {

		root = o.getOperator(rand);
		root.addRandomKids(o, t, maxDepth, rand);
	}

	/**
	 * Prints the entire tree
	 */
	public String toString() {
		return root.toString();
	}

	/**
	 * Calculates the fitness of the tree
	 * 
	 * @param dataSet
	 * @return
	 */
	public double eval(DataSet dataSet) {
		fitness = 0.0;
		List<DataRow> dataRowList = dataSet.dataRowList;

		for (int i = 0; i < dataRowList.size(); i++) {
			double actualY = dataRowList.get(i).getY();
			double predictedY = root.eval(dataRowList.get(i).getxValues());
			fitness = fitness + (Math.pow((actualY - predictedY), 2));
		}

		return fitness;
	}

	/**
	 * Since all the nodes in the tree are numbered. This method picks a random
	 * number and tries to trace its parent.
	 * 
	 * @param rand
	 * @return
	 */
	public NodePairPlus randomParentAndChild(Random rand) {
		int clipNumber = Math.abs(rand.nextInt()) % (mySize() - 1) + 2;
		NodePairPlus p;
		p = root.traceTree(1, clipNumber);
		return p;
	}

	/**
	 * Returns a duplicate copy of this GPTree. Used in the crossover operation
	 * and when creating a new generation.
	 */
	public GPTree duplicate() {
		GPTree copy = new GPTree();
		copy.root = root.duplicate();
		return copy;
	}

	public int mySize() {
		return root.mySize();
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(GPTree obj) {
		return (this.fitness > obj.fitness) ? 1 : 0;
	}

	public int getLeftDepth() {
		return getLeftDepth(root);
	}

	public int getLeftDepth(Node root) {
		if (root instanceof Binop) {
			return getLeftDepth(((Binop) root).getlChild());
		} else {
			return root.getDepth();
		}

	}

}