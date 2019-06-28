public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67e-11;

    public static String background = "images/starfield.jpg";

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;

    }
    /** Take in a Planet object
     * initialize an identical Planet object */
    public Planet (Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }


    /**
     * Calculates the distance between two Planets.
     *  param a single Planet
     *  return a double equal to the distance
     * between the supplied planet and
     * the planet that is doing the calculation
     */
    public double calcDistance(Planet p){
        double d;
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        d = Math.sqrt(dx*dx + dy*dy);
        return d;
    }
    /**
     * takes in a planet
     * returns a double describing the force
     * exerted on this planet by the given planet
     */
    public double calcForceExertedBy(Planet p){
        double f;
        double d = this.calcDistance(p);
        f = G * p.mass * this.mass / (d*d);
        return f;

    }

    /**
     * describe the force exerted in the X direction
     * */
    public double calcForceExertedByX(Planet p){
        double fx;
        double dx = p.xxPos - this.xxPos;
        fx = this.calcForceExertedBy(p)/this.calcDistance(p)*dx;
        return fx;
    }

    /**
     * describe the force exerted in the Y direction
     * */
    public double calcForceExertedByY(Planet p){
        double fy;
        double dy = p.yyPos - this.yyPos;
        fy = this.calcForceExertedBy(p)/this.calcDistance(p)*dy;
        return fy;
    }
    /**
     * calculate the net X force exerted by all planets
     * take in an array */
    public double calcNetForceExertedByX(Planet[] ps) {

        int len = ps.length;
        double f = 0;
        for (int i = 0; i < len; i++) {
            if (!this.equals(ps[i])) {
                f += this.calcForceExertedByX(ps[i]);
            }
        }
        return f;
    }


    /**
     * calculate the net Y force exerted by all planets
     * take in an array */
    public double calcNetForceExertedByY(Planet[] ps) {

        int len = ps.length;
        double f = 0;
        for (int i = 0; i < len - 1; i++) {
            if (!this.equals(ps[i])) {
                f += this.calcForceExertedByY(ps[i]);
            }
        }
        return f;

    }

    /** determines how much the forces exerted on the planet
     * will cause that planet to accelerate
     */
    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel += dt*ax;
        this.yyVel += dt*ay;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
        return;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
