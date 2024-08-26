package functions;

public class FunctionStructure {
    private static int[] intFuncNot;
    private static double[] constantList;

    //constructeurs
    public FunctionStructure(){}

    public FunctionStructure(int i){
        intFuncNot = new int[i];
        constantList = new double[i];
    }

    public FunctionStructure(int[] intFuncRef, double[] constants){
        intFuncNot=intFuncRef;
        constantList=constants;
    }

    //getteurs
    public double[] getConstants(){
        return this.constantList;
    }

    public int[] getIntFuncNot(){
        return this.intFuncNot;
    }

    //additions de fonctions
    public static FunctionStructure addFunctionStructure(FunctionStructure addedFuncA, FunctionStructure addedFuncB){
        return new FunctionStructure(intArrayAdd(addedFuncA.intFuncNot, addedFuncB.intFuncNot),doubleArrayAdd(addedFuncA.constantList, addedFuncB.constantList));
    }

    //opérations sur les tableaux (je savait pas où le ranger)
    public static int[] intArrayAdd(int[] intArray1, int[] intArray2){
        int[] returnArray = new int[intArray1.length+intArray2.length];
        int i=0;
        while(i<intArray1.length){
            returnArray[i]=intArray1[i];
            i++;
        }
        while (i<(intArray1.length+intArray2.length)) { 
            returnArray[i]=intArray2[i-intArray1.length];
            i++;
        }
        return returnArray;
    }
    public static double[] doubleArrayAdd(double[] doubleArray1, double[] doubleArray2){
        double[] returnArray = new double[doubleArray1.length+doubleArray2.length];
        int i=0;
        while(i<doubleArray1.length){
            returnArray[i]=doubleArray1[i];
            i++;
        }
        while (i<returnArray.length-1) { 
            returnArray[i]=doubleArray2[i-doubleArray1.length];
            i++;
        }
        return returnArray;
    }

    public FunctionStructure removeNull(){
        int[] intNot = this.getIntFuncNot();
        double[] varArray = this.getConstants();

        int i = 0;
        try {
            while(intNot[i]!=0){
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return this;
        }
       

        int[] returnIntNot = new int[i];
        double[] returnVarArray = new double[i];
        for (int j = 0; j < i; j++) {
            returnIntNot[j]=intNot[j];
            returnVarArray[j]=varArray[j];
        }
        return(new FunctionStructure(returnIntNot,returnVarArray));
    }


    public FunctionStructure addImplicitMultiplication(){
        int[] returnIntArray = new int[this.intFuncNot.length*2];
        double[] returnVarArray = new double[this.intFuncNot.length*2];
        int j=0;
        for (int i = 0; i <this.intFuncNot.length; i++) {
            try {
                if ((this.intFuncNot[i]==FunctionTockenReference.VARX||this.intFuncNot[i]==FunctionTockenReference.VARY||this.intFuncNot[i]==FunctionTockenReference.VARZ||this.intFuncNot[i]==FunctionTockenReference.VALUE||this.intFuncNot[i]==FunctionTockenReference.RIGHT_BRACKET)&&(this.intFuncNot[i+1]==FunctionTockenReference.VARX||this.intFuncNot[i+1]==FunctionTockenReference.VARY||this.intFuncNot[i+1]==FunctionTockenReference.VARZ||this.intFuncNot[i+1]==FunctionTockenReference.SQRT||this.intFuncNot[i+1]==FunctionTockenReference.LOG||this.intFuncNot[i+1]==FunctionTockenReference.ABS||this.intFuncNot[i+1]==FunctionTockenReference.SIN||this.intFuncNot[i+1]==FunctionTockenReference.COS||this.intFuncNot[i+1]==FunctionTockenReference.TAN||this.intFuncNot[i+1]==FunctionTockenReference.LN||this.intFuncNot[i+1]==FunctionTockenReference.LEFT_BRACKET)) {
                    returnIntArray[i+j]=this.intFuncNot[i];
                    returnVarArray[i+j]=this.constantList[i];
                    j++;
                    returnIntArray[i+j]=FunctionTockenReference.MULTIPLY;
                }else{
                    returnIntArray[i+j]=this.intFuncNot[i];
                    returnVarArray[i+j]=this.constantList[i];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                returnIntArray[i+j]=this.intFuncNot[i];
                returnVarArray[i+j]=this.constantList[i];
            }
                
        }
        if (j==0) {
            return this;
        }
            return new FunctionStructure(returnIntArray,returnVarArray);
    }
}
