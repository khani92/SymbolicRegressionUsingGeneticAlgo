

public class Mult extends Binop {

	public Mult(Node lC, Node rC) {
		super(lC, rC);
	}
	
	public Mult() {
	}
	@Override
	double eval(double[] data) {
		return (lChild.eval(data) * rChild.eval(data));
	}

	@Override
	public String toString() {
		return ("(" + lChild.toString() + "*" + rChild.toString() + ")");
	}

	public Node duplicate() {
        Mult duplicate = new Mult();
        duplicate.lChild = lChild.duplicate();
        duplicate.rChild = rChild.duplicate();
        return duplicate;
    }
}
