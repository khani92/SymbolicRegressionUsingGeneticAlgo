import java.util.Random;

public class Evolver {

	Generation gen0 = null;
	Generation gen1 = null;
	DataSet ds = null;
	Random rand = null;

	public Evolver(Generation generationOfTrees, DataSet ds, Random rand) {
		super();
		this.gen0 = generationOfTrees;
		this.ds = ds;
		this.rand = rand;
	}

	/**
	 * This method pick 2 trees from the generation of trees at random and
	 * performs cross over action over them. It then repeats the same action
	 * over the entire generation.
	 * 
	 * @param d
	 * @param rand
	 */
	public void makeNewGeneration(DataSet d, Random rand) {

		// Create a copy of older generation
		gen1 = new Generation(gen0.getNumOfTrees());

		for (int i = 0; i < gen0.getNumOfTrees(); i++) {
			gen1.getGenerationOfGPTrees().add(
					gen0.getGenerationOfGPTrees().get(i).duplicate());
		}

		// Always remember to run this after any change in trees in a generation
		// to fill the fitness values in each tree of the generation
		gen1.evalAll(d);

		// Perform cross over and add the crossovered trees in gen0
		for (int i = 0; i < gen0.getNumOfTrees() / 2; i++) {
			GPTree t1 = gen1.chooseTreeProportionalToFitness(rand);
			GPTree t2 = gen1.chooseTreeProportionalToFitness(rand);
			gen0.getGenerationOfGPTrees().add(2 * i, t1.duplicate());
			gen0.getGenerationOfGPTrees().add(2 * i + 1, t2.duplicate());
			gen0.crossover(gen0.getGenerationOfGPTrees().get(2 * i), gen0
					.getGenerationOfGPTrees().get(2 * i + 1), rand);
		}
		// Always remember to run this after any change in trees in a generation
		// to fill the fitness values in each tree of the generation
		gen0.evalAll(d);
	}

	// Print the new cross-overed generation. Fitness for this should be better
	// than the older generation
	public void printGen0() {
		System.out.println();
		System.out.println("Here is Generation 0:");
		gen0.printGeneration();
	}

	// Print the older generation. Fitness for this should be better than the
	// older generation
	public void printGen1() {
		System.out.println();
		System.out.println("Here is Generation 1:");
		gen1.printGeneration();
	}

}
