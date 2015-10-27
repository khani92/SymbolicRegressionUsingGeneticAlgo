
import java.util.Random;

public class OperatorFactory {

	static Node[] ops;
	
	private static int numOperator; // This is kept dynamic for extensibility.
									// In our case the number of operator is 4
									// in our case {+,-,*,/}

	OperatorFactory(Node[] operators) {
		ops = operators;
		numOperator = operators.length;
	}

	static Binop getOperator(Random rand) {
		Node operatorNode = null;
		switch (rand.nextInt(4)) {
		case 0:
			operatorNode = ops[0].clone();
			break;
		case 1:
			operatorNode = ops[1].clone();
			break;
		case 2:
			operatorNode = ops[2].clone();
			break;
		case 3:
			operatorNode = ops[3].clone();
			break;
		}
		return (Binop) operatorNode;
	}

	public static int getNumOperator() {
		return numOperator;
	}
}
