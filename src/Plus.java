

public class Plus extends Binop {

	public Plus(Node lC, Node rC) {
		super(lC, rC);
	}
	public Plus() {
		
	}
	@Override
	double eval(double[] data) {
		return (lChild.eval(data) + rChild.eval(data));
	}
	
	@Override
	public String toString() {
		return ("(" + lChild.toString()+"+" + rChild.toString() +")");
	}
	public Node duplicate() {
        Plus duplicate = new Plus();
        duplicate.lChild = lChild.duplicate();
        duplicate.rChild = rChild.duplicate();
        return duplicate;
    }
}