public class NBody{
	public static void main(String[] args){
		/** read args */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] planets = NBody.readBodies(filename);

		//read num of bodies;
		int num = NBody.readNum(filename);


		/** Drawing */
		/** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
		StdDraw.enableDoubleBuffering();

		/** Sets up the universe so it goes from
		  * -r,-r up to r,r */
		double r = NBody.readRadius(filename);
		StdDraw.setScale(-r,r);

		/* Clears the drawing window. */
		StdDraw.clear();

		//draw background
		StdDraw.picture(0,0,"images/starfield.jpg",2*r,2*r);

		// draw bodies
		for(Body b:planets){
			b.draw();
		}

		//show
		StdDraw.show();

		/** animation*/
		double time = 0;

		while (time < T){

			double[] xForces = new double[num];
			double[] yForces = new double[num];

			//cal forces
			for(int k = 0; k < num; k+= 1){
				xForces[k] = planets[k].calcNetForceExertedByX(planets);
				yForces[k] = planets[k].calcNetForceExertedByY(planets);
			}


			for(int k = 0; k < num; k+= 1){
				planets[k].update(dt,xForces[k], yForces[k]);
			}

			/* Clears the drawing window. */
			StdDraw.clear();
			//draw background
			StdDraw.picture(0,0,"images/starfield.jpg",2*r,2*r);
			//draw body
			for(Body b: planets){
				b.draw();
			}
			StdDraw.show();

			StdDraw.pause(10);

			time = time+ dt;
		}

		//print result 
		StdOut.printf("%d\n", num);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}



	}

	/**Given a file name as a String, 
it should return a double corresponding to the radius of the universe in that file
*/


/** read num of bodoes*/
	public static int readNum(String filename){

		In in = new In(filename);
		int num = in.readInt();
		return num;
		
	}

	public static double readRadius(String filename){

		In in = new In(filename);
		int num = in.readInt();
		double r = in.readDouble();
		return r;
		
	}

	public static Body[] readBodies(String filename){

		In in = new In(filename);
		int num = in.readInt();
		double r = in.readDouble();
		Body[] planets = new Body[num];

		for( int k = 0; k < num; k+= 1){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[k] = new Body(xxPos,yyPos, xxVel,yyVel,mass, img);
		
		}
		return planets;
	}


}