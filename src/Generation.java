import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

	/**
	 * Arraylist to holds the entire generation of GPTrees
	 */
	private List<GPTree> generationOfGPTrees = new ArrayList<>();
	private int numOfTrees;
	static Random rand = new Random();
	private GPTree gpTree = null;

	// Array to hold inverseFitness. This is useful to give higher probability
	// to more fit trees
	private double[] inverseFitness = null;

	Generation(int numOfTrees) {
		this.numOfTrees = numOfTrees;
	}

	/**
	 * Evaluates the entire generation and also populates the inverseFitness
	 * array
	 * 
	 * @param dataSet
	 */
	public void evalAll(DataSet dataSet) {

		inverseFitness = new double[generationOfGPTrees.size()];
		for (int i = 0; i < numOfTrees; i++) {
			double evalFitness = generationOfGPTrees.get(i).eval(dataSet);
			inverseFitness[i] = 1 / evalFitness;
		}
	}

	/**
	 * A handy method to print the fittest tree from among the generation
	 * 
	 * @return
	 */
	public GPTree getFittestTree() {
		double fitness;
		GPTree fittestTree = null;
		if (generationOfGPTrees != null && generationOfGPTrees.size() != 0) {

			fitness = generationOfGPTrees.get(0).getFitness();
			for (int i = 0; i < generationOfGPTrees.size(); i++) {

				if (fitness > generationOfGPTrees.get(i).getFitness()) {
					fitness = generationOfGPTrees.get(i).getFitness();
					fittestTree = generationOfGPTrees.get(i);
				}
			}
		}

		return fittestTree;
	}

	/**
	 * This is the method that brings the Genetic Programming aspect to the
	 * project. It randomly picks a tree from the entire generation but makes
	 * sure to give higher chances to fitter trees.
	 * 
	 * It does this by storing the inverse fitness of the trees in an array and
	 * then randomly generating a nuber between 0 and final value of cumulative
	 * array. Since the gap between value with low fitness (high inverse
	 * fitness) will be high, chances of getting a number near it will be high
	 * 
	 * @param rand
	 * @return
	 */
	public GPTree chooseTreeProportionalToFitness(Random rand) {

		double[] cumulativeFitArray = new double[generationOfGPTrees.size()];
		double sum = 0;
		GPTree randomSelectedTree = null;
		// Populate cumulative array to give more chances to fitter trees
		for (int i = 0; i < generationOfGPTrees.size(); i++) {
			sum = sum + inverseFitness[i];
			cumulativeFitArray[i] = sum;
		}
		double treeIndexer = rand.nextDouble() * sum;
		int treePos = -1;
		for (int i = 0; i < generationOfGPTrees.size(); i++) {

			if (treeIndexer < cumulativeFitArray[0]) {
				treePos = 0;
				break;
			} else if (i + 1 != generationOfGPTrees.size()) {
				if (treeIndexer > cumulativeFitArray[i]
						&& treeIndexer < cumulativeFitArray[i + 1]) {
					// Found tree
					treePos = i + 1;
					break;
				}
			}
		}

		if (treePos != -1) {
			randomSelectedTree = generationOfGPTrees.get(treePos);
		}

		return randomSelectedTree;

	}

	/**
	 * Performs the crossvoer ooperation
	 * 
	 * @param t1
	 * @param t2
	 * @param rand
	 */
	public static void crossover(GPTree t1, GPTree t2, Random rand) {
		NodePairPlus pair1 = t1.randomParentAndChild(rand);
		NodePairPlus pair2 = t2.randomParentAndChild(rand);
		pair1.parent.changeChild(pair1.child, pair2.child);
		pair2.parent.changeChild(pair2.child, pair1.child);
	}

	public List<GPTree> getGenerationOfGPTrees() {
		return generationOfGPTrees;
	}

	public void setGenerationOfGPTrees(List<GPTree> generationOfGPTrees) {
		this.generationOfGPTrees = generationOfGPTrees;
	}

	public int getNumOfTrees() {
		return numOfTrees;
	}

	public void printGeneration() {
		for (int i = 0; i < numOfTrees; i++) {
			System.out.println(generationOfGPTrees.get(i));
		}
	}
}
