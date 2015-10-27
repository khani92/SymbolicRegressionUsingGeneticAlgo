import java.util.Random;

public class TestAlgebra {
	/*
	 * static int numIndepVars = 1; static int maxDepth = 5; static Random rand
	 * = new Random();
	 * 
	 * public static void main(String[] args) {
	 * 
	 * Generation generationOfTrees = new Generation(); List<GPTree> listOfTrees
	 * = new ArrayList<>(); Node[] ops = {new Plus(), new Minus(), new Mult(),
	 * new Divide()};
	 * 
	 * OperatorFactory o = new OperatorFactory(ops); TerminalFactory t = new
	 * TerminalFactory(numIndepVars); GPTree gpt = null; // Generate 500 GPTrees
	 * int i = 0; while (i < 5) { gpt = new GPTree(o, t, maxDepth, rand);
	 * listOfTrees.add(gpt); i++; }
	 * generationOfTrees.setListOfGenerationTrees(listOfTrees);
	 * 
	 * System.out.println("Enter the name of the file containing data"); Scanner
	 * scanObj = new Scanner(System.in); String fileName = scanObj.nextLine();
	 * 
	 * DataSet dataSet = new DataSet(fileName);
	 * 
	 * TreeMap<Double, GPTree> map = generationOfTrees.evalAll(dataSet);
	 * System.out.println("Fittest tree is");
	 * System.out.println(map.entrySet().iterator
	 * ().next().getValue().toString());
	 * System.out.println("With fitness ="+map.
	 * entrySet().iterator().next().getKey()); }
	 */
	static int numIndepVars = 3;
	static int maxDepth = 5;
	static Random rand = new Random();

	public static void main(String[] args) {
		Node[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
		OperatorFactory o = new OperatorFactory(ops);
		TerminalFactory t = new TerminalFactory(numIndepVars);
		GPTree gpt = new GPTree(o, t, maxDepth, rand);
		System.out.println(gpt.toString());
		System.out.println(gpt.getLeftDepth());

	}
}