public class NBody {
    

    /** return a double corresponding to the radius
     * of the universe in that file*/
    public static double readRadius(String add){
        In in = new In(add);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    /**Given a file name
     * return an array of Planets
     */
    public static Planet[] readPlanets(String add){
        In in = new In(add);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] ps = new Planet[N];
        for (int i = 0; i<N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ps[i] = new Planet(xP, yP, xV, yV, m, img);

        }
        return ps;


    }

    public static void main(String[] args) {
        /** Get data*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        Planet[] ps = readPlanets(filename);
        double R = readRadius(filename);

        /** Draw */
        StdDraw.setScale(-R, R);
        /* Clears the drawing window. */
        StdDraw.clear();
        /* Draw background. */
        StdDraw.picture(0, 0, "images/starfield.jpg");
        /* Draw 5 planets */
        for (Planet p: ps){
            p.draw();
        }

        /** Creating an Animation.*/
        StdDraw.enableDoubleBuffering();

        double t = 0;

        while(t<T){
            double[] xForce = new double[ps.length];
            double [] yForce = new double[ps.length];

            for (int i = 0; i<ps.length; i++){
                xForce[i] = ps[i].calcNetForceExertedByX(ps);
                yForce[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for (int i = 0; i<ps.length; i++){
                ps[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p: ps){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;

            StdOut.printf("%d\n", ps.length);
            StdOut.printf("%.2e\n", R);
            for (int i = 0; i < ps.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                        ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
            }



        }





    }
}
