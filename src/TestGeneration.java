import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGeneration {

	static int numIndepVars = 1;
	static int maxDepth = 5;
	static Random rand = new Random();

	public static void main(String[] args) {

		Node[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
		OperatorFactory o = new OperatorFactory(ops);
		TerminalFactory t = new TerminalFactory(numIndepVars);
		GPTree gpTree = null;
		
		List<GPTree> list = new ArrayList<>();
		Generation generationObj = new Generation(500);
		
		for (int i = 0; i < 500; i++) {
			gpTree = new GPTree(o, t, maxDepth, rand);
			list.add(gpTree);
		}
		generationObj.setGenerationOfGPTrees(list);

		DataSet ds = new DataSet();
		ds.getDataFromFile("DataSet.txt");
		
		generationObj.evalAll(ds);
		
		System.out.println("Fittest Tree");
		System.out.println(generationObj.getFittestTree().toString());
		System.out.println("Fitness: " + generationObj.getFittestTree().getFitness());

		System.out.println("Random Tree");
		GPTree  x= generationObj.chooseTreeProportionalToFitness(rand);
		System.out.println(x.toString());
		System.out.println("Fitness: " + x.getFitness());
	}
}
