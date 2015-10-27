import java.util.Random;

public class TerminalFactory {

	/**
	 * Represents the number of independent variables in our regression model
	 */
	private static int numIndepVars;

	public TerminalFactory(int n) {
		numIndepVars = n;
	}

	public static Node getTerminal(Random rand) {
		Node terminalNode = null;

		int randNum = rand.nextInt(numIndepVars + 1);

		if (randNum < numIndepVars) {
			// Terminal node is an independent var
			terminalNode = new Variable(rand.nextInt(numIndepVars));
		} else if (randNum == numIndepVars) {
			// Terminal node is a constant
			terminalNode = new Const(rand.nextDouble());
		}

		return terminalNode;
	}

	public static int getNumIndepVars() {
		return numIndepVars;
	}
}
