

import java.util.Random;

public class TestAlgebraProject {

	static Random rand = new Random();
	public static void main(String[] args) {
		
		double data1[] = {1,2,3};
		
		Binop temp=null;
		for(int i=0;i<2;i++){
					
			Node node1= getTerminalInfo();
			Node node2= getTerminalInfo();
			Binop binop= randomOperator(node1, node2);
		
			if(temp==null){
				temp=binop;
			}
			else{
				temp=randomOperator(temp, binop);
			}
		}
		
		System.out.println(temp.toString());
		System.out.println(temp.eval(data1));
		/*AlgebraNode n1=new AlgebraConst(1);
		AlgebraNode x0= new Variable(0);
		AlgebraBinop plus= new AlgebraPlus(n1, x0);
		
		System.out.println(plus.toString()+"=");
		System.out.println(plus.eval(new double[]{1,2,3}));
		*/
	}
	
	static Node getTerminalInfo(){
		final int head=0;
		final int tail=1;
		Node node= null;
		switch(rand.nextInt(2)){
			case head:
				node=new Const(rand.nextInt(20)+1);
				return node;
				
			case tail:
				node=new Variable(rand.nextInt(3));
				return node;
				
		}
		return node;
	}
	static Binop randomOperator(Node lC, Node rC){
		
		Binop binop=null;
		switch(rand.nextInt(4)){
			case 0:
				binop=new Plus(lC, rC);
				return binop;
			case 1:
				binop=new Minus(lC, rC);
				return binop;
			case 2:
				binop=new Mult(lC, rC);
				return binop;
			case 3:
				binop=new Divide(lC, rC);
				return binop;
		}
		return binop;
	}

}
