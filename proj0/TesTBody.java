/**
 creates two bodies and prints out the pairwise force between them.
 */

 public class TesTBody{

 	public static void main(String[] args){
 		printForce();
 	}

 	public static void printForce(){
 		Body b1 = new Body(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        double Fx1 = b1.calcForceExertedByX(b2);
        double Fy1 = b1.calcForceExertedByY(b2);

        System.out.println("X Force by b1 to b2 = " + Fx1);
        System.out.println("Y Force by b1 to b2 = " + Fy1);


 	}


 }