import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalDriverClass {

	static int numIndepVars = 1;
	static int maxDepth = 5;
	static Random rand = new Random();

	public static void main(String[] args) {

		Node[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
		OperatorFactory o = new OperatorFactory(ops);
		TerminalFactory t = new TerminalFactory(numIndepVars);
		GPTree gpTree = null;
		Evolver evol = null;
		List<GPTree> list = new ArrayList<>();
		Generation generationObj = new Generation(500);

		// Creates the generation
		for (int i = 0; i < 500; i++) {
			gpTree = new GPTree(o, t, maxDepth, rand);
			list.add(gpTree);
		}

		generationObj.setGenerationOfGPTrees(list);

		DataSet ds = new DataSet();
		ds.getDataFromFile("DataSet.txt");

		// Evaluates fitness of the generation
		generationObj.evalAll(ds);

		evol = new Evolver(generationObj, ds, rand);

		/*
		 * To print the new and older generation
		 * 
		 * System.out.println(evol.gen0.getFittestTree());
		 * System.out.println(evol.gen0.getFittestTree().getFitness());
		 * System.out.println("\n");
		 * 
		 * System.out.println(evol.gen1.getFittestTree());
		 * System.out.println(evol.gen1.getFittestTree().getFitness());
		 */

		//Runs the evolving process 10 times to see substantial difference
		for (int i = 0; i < 10; i++) {
			System.out.println("Fittest Tree in Generation " + (i + 1) + ":");
			System.out.println(generationObj.getFittestTree().toString());
			System.out.println("Fitness: "
					+ generationObj.getFittestTree().getFitness()+"\n");

			evol = new Evolver(generationObj, ds, rand);
			evol.makeNewGeneration(ds, rand);
		}
	}

}
