// momentum : m2 u2 + m1 u1 = m2 v2 + m1 v1

public class Maths{
	private float g = 9.8; //  m/s

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ux = v0x
    // vy = v1y
    // x : distance on the x axis
    public float getX(float ux, float ax, float y, float t){
    	return ux*t+(ax*t*t*y)/2;
    }
    public float getX(float uy, float ay, float t){
    	return uy*t+(ay*t*t)/2;
    }

    public float getY(float x, float ux, float ax, float t){
    	return (2*x+2*ux*t)/(ax*t*t);
    }
    public float getY(float uy, float t){
    	return uy*t-(g*t*t)/2;
    }
    public float getY(float uy, float ux, float x){
    	return (uy*x)/ux - ((g*x*x)/(2*ux*ux))
    }

    public float getT(float x, float ux){ // it work also with uy
    	return x/ux;
    }
    //f or more: https://www.studysmarter.co.uk/explanations/math/mechanics-maths/the-trajectory-of-a-projectile/

    public float getUx(float x, float ax, float y, float t){
    	return (x-(ax*t*t*y)/2)/t;
    }
    public float getAx(float x, float ux, float y, float t){
    	return (2*x-2*ux*t)/(y*t*t);
    }

    public float getUy(float y, float t){
    	return (y+(g*t*t)/2)/t;
    }
    public float getAy(float y, float uy, float y, float t){
    	return (2*x-2*uy*t)/(t*t);
    }

}