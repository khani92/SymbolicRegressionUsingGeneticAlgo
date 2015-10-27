public class Divide extends Binop {

	public Divide(Node lC, Node rC) {
		super(lC, rC);
	}

	public Divide() {
	}

	@Override
	double eval(double[] data) {
		if (Math.abs(rChild.eval(data)) < 0.0001) {
			// To avoid the case when rChild.eval i.e the divisor comes out to
			// be 0
			return 1;
		} else {
			return (lChild.eval(data) / rChild.eval(data));
		}
	}

	@Override
	public String toString() {

		return ("(" + lChild.toString() + "/" + rChild.toString() + ")");
	}
	public Node duplicate() {
        Divide duplicate = new Divide();
        duplicate.lChild = lChild.duplicate();
        duplicate.rChild = rChild.duplicate();
        return duplicate;
    }
}