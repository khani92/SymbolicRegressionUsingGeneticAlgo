

public class Minus extends Binop {

	public Minus(Node lC, Node rC) {
		super(lC, rC);
	}
	
	public Minus() {
	}
	@Override
	double eval(double[] data) {
		return (lChild.eval(data) - rChild.eval(data));
	}

	@Override
	public String toString() {
		return ("(" + lChild.toString() + "-" + rChild.toString() + ")");
	}
	public Node duplicate() {
        Minus duplicate = new Minus();
        duplicate.lChild = lChild.duplicate();
        duplicate.rChild = rChild.duplicate();
        return duplicate;
    }
}