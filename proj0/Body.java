public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** first constructor */
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double dist = Math.sqrt(Math.pow((this.xxPos-b.xxPos),2)
					 + Math.pow((this.yyPos-b.yyPos),2) );
		return dist;
	}

	public double calcForceExertedBy(Body b){
		double G = 6.67e-11;
		double Dist = this.calcDistance(b);
		double F = G * this.mass * b.mass / Math.pow(Dist,2);
		return F;
	}

	public double calcForceExertedByX(Body b){
		double F = this.calcForceExertedBy(b);
		double Dist = this.calcDistance(b);
		double Fx = F * (b.xxPos - this.xxPos)/ Dist;
		return Fx;
	}

	public double calcForceExertedByY(Body b){
		double F = this.calcForceExertedBy(b);
		double Dist = this.calcDistance(b);
		double Fy = F * (b.yyPos - this.yyPos)/ Dist;
		return Fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double Fxx = 0;
		for(Body b : allBodys){
			if(this.equals(b)){
				continue;
			}
			else{
				Fxx = Fxx + this.calcForceExertedByX(b);
			}

		}
		return Fxx;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double Fyy = 0;
		for(Body b : allBodys){
			if(this.equals(b)){
				continue;
			}
			else{
				Fyy = Fyy + this.calcForceExertedByY(b);
			}

		}
		return Fyy;
	}


	public void update(double dt, double fX, double fY){
		this.xxVel = this.xxVel + fX / this.mass * dt;
		this.yyVel = this.yyVel + fY / this.mass * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		StdDraw.enableDoubleBuffering();
		String filename = "images/"+imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos, filename);
		StdDraw.show();
	}



}




















