package object2d;

public class Polygon {
    private static Point center;
    private static Point[] ptsDef;
    private static double surface;
    private static boolean isConvex;
    private static double rotation;
    private static Point position;

    public double getSurface(){
        return this.surface;
    }
    public Point[] getPoints(){
        return this.ptsDef;
    }
    public Point getCenter(){
        return this.center;
    }
    public Point getPosition(){
        return this.position;
    }
    public double getrotation(){
        return this.rotation;
    }

    //polygon random
    public Polygon(Point[] lpts){
        this.ptsDef = lpts;
        this.center = this.centerCalc();
        this.surface = this.surfaceCalc();
        this.position = new Point(0, 0);
        this.rotation = 0d;
    }

    public Polygon(Point[] lpts,Point pos){
        this.ptsDef = lpts;
        this.center = this.centerCalc();
        this.surface = this.surfaceCalc();
        this.position = pos;
        this.rotation = 0d;
    }
    public Polygon(Point[] lpts,Point pos, double rot){
        this.ptsDef = lpts;
        this.center = this.centerCalc();
        this.surface = this.surfaceCalc();
        this.position = pos;
        this.rotation = rot;
    }

    //carré

    public static Polygon newSquare(double width,double height){
        return new Polygon(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)});
    }

    public Polygon newSquare(double width,double height,Point pos){
        return new Polygon(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)},pos);
    }

    public Polygon newSquare(double width,double height,Point pos, double rot){
        return new Polygon(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)},pos,rot);
    }


    //reste
    public double surfaceCalc() {
        this.isConvex=true;
        double surface = 0;
        for (int i = 0; i < this.ptsDef.length; i++) {
            int j = (i + 1) % this.ptsDef.length;
            double surfaceAdded=this.ptsDef[i].getX() * this.ptsDef[i].getY() - this.ptsDef[j].getX() * this.ptsDef[j].getY();
            surface += surfaceAdded;
            if (surfaceAdded<0) {
                this.isConvex=false;
            }
        }
        return (double) (Math.abs(surface) / 2.0);
    }

    public Point centerCalc() {
        double sumX = 0;
        double sumY = 0;
        for (Point vec : this.ptsDef) {
            sumX += vec.getX();
            sumY += vec.getY();
        }
        return new Point(sumX / this.ptsDef.length, sumY / this.ptsDef.length);
    }

    public boolean collisionForSAT(Polygon a, Polygon b){
        Point[] aPoints=a.getPoints();
        Point[] bPoints=b.getPoints();
        double maxA;
        double minA;
        double maxB;
        double minB;
        boolean collisionForA=false;
        boolean collisionForB=false;
        for(int i = 0; i < aPoints.length; i++) {

            Vector refVector;
            try {
                refVector = new Vector(aPoints[i],aPoints[i+1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                refVector = new Vector(aPoints[i],aPoints[0]);
            }
            minA = new Vector(aPoints[0]).dotProduct(refVector);
            maxA = minA;
            minB = minA;
            maxB = minA;

            for(int j = 0; j < aPoints.length; j++) {
                double value = new Vector(aPoints[j]).dotProduct(refVector);
                if (value<minA) {
                    minA = value;
                }else if(value>maxA) {
                    maxA = value;
                }
            }

            for(int j = 0; j < bPoints.length; j++) {
                double value = new Vector(bPoints[j]).dotProduct(refVector);
                if (value<minB) {
                    minB = value;
                }else if(value>maxB) {
                    maxB = value;
                }
            }

            if ((minA>maxB||maxA<minB)&&(collisionForA==false)) {
                collisionForA = false;
            }else{
                collisionForA = true;
            }

        }
        System.out.println("true for a :" + collisionForA);



        for(int i = 0; i < bPoints.length; i++) {

            Vector refVector;
            try {
                refVector = new Vector(bPoints[i],bPoints[i+1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                refVector = new Vector(bPoints[i],bPoints[0]);
            }
            
            minA = new Vector(bPoints[0]).dotProduct(refVector);
            maxA = minA;
            minB = minA;
            maxB = minA;
            for(int j = 0; j < bPoints.length; j++) {
                double value = new Vector(bPoints[j]).dotProduct(refVector);
                if (value<minA) {
                    minA = value;
                }else if(value>maxA) {
                    maxA = value;
                }
            }

            for(int j = 0; j < aPoints.length; j++) {
                double value = new Vector(aPoints[j]).dotProduct(refVector);
                if (value<minB) {
                    minB = value;
                }else if(value>maxB) {
                    maxB = value;
                }
            }

            if ((minA>maxB||maxA<minB)&&(collisionForB==false)) {
                collisionForB = false;
            }else{
                collisionForB = true;
            }

        }
        System.out.println("true for b :" + collisionForB);




        
        return (collisionForA&&collisionForB)==false;
    }

    


    public boolean collisionForSAT(Polygon b){
        return collisionForSAT(this, b);
    }
}
