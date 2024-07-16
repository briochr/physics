public class FunctionStructure {
    private int[] intFuncNot;
    private double[] constantList;

    public FunctionStructure(int[] intFuncRef, double[] constants){
        intFuncNot=intFuncRef;
        constantList=constants;
    }

    public double[] getConstants(){
        return this.constantList;
    }

    public int[] getIntFuncNot(){
        return this.intFuncNot;
    }
}
