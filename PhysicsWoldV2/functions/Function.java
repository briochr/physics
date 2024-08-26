package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// salut
public class Function{
    private String strFunc;
    private FunctionStructure polishFunction;
    private int length;

    //test & debug
    public static void main(String[] args){
        Function func = new Function("2^(x*2)+2(x^(2))");
        System.out.println("bracket : ".concat(func.nextBracket(2).strFunc));
        func.derivative();
    }

    public void printDebugFonctionStructure(){
        double[] constants=this.polishFunction.getConstants();
        int[] intFuncNot=this.polishFunction.getIntFuncNot();
        for (int i : intFuncNot) {
            System.out.print(i + "; ");
        }
        System.out.println("");
        for (double i : constants) {
            System.out.print(i + "; ");
        }
        System.out.println("");
    }

    // constructeurs
    public Function(String function){
        this.strFunc = function;
        this.polishFunction = this.strToIntNotation();
        this.length = polishFunction.getIntFuncNot().length;
        this.strFunc = this.intToStrNotation();
    }
    public Function(FunctionStructure function){
        this.polishFunction = function.addImplicitMultiplication();
        this.strFunc = intToStrNotation();
        this.length = polishFunction.getIntFuncNot().length;
    }
    public Function(){
        this.strFunc = "";
    }

    //getteurs
    public String getFuncStr(){
        return this.strFunc;
    }
    public FunctionStructure getPolishFunc(){
        return this.polishFunction;
    }

    //convetisseur de fonction en tableau d'entier
    private FunctionStructure strToIntNotation(){
        String funcStr = this.strFunc.toLowerCase();

		funcStr = funcStr.replaceAll("-", "+-");
		funcStr = funcStr.replaceAll("exp", "e^");
		funcStr = funcStr.replaceAll(",", ".");

        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+|\\(|\\)|x|y|y'|\\+|\\*|/|\\^|sqrt|log|abs|sin|sen|cos|tan|tg|-|ln|e|pi");
	  
		Matcher m = pattern.matcher(funcStr);

        int[] intNotation = new int[funcStr.length()];
        double[] constants = new double[funcStr.length()];

        int i = 0;
        while(m.find()){

            String token = funcStr.substring(m.start(0), m.end(0));
			try{

				double value = Double.parseDouble(token);
				intNotation[i] = FunctionTockenReference.VALUE;
                constants[i] = value;
				
			}
			catch(Exception e){
                switch (token) {
                    case("x"):
                        intNotation[i] = FunctionTockenReference.VARX;
                        break;
                    case("y"):
                        intNotation[i] = FunctionTockenReference.VARY;
                        break;
                    case("z"):
                        intNotation[i] = FunctionTockenReference.VARZ;
                        break;
                    case("+"):
                        intNotation[i] = FunctionTockenReference.ADD;
                        break;
                    case("-"):
                        intNotation[i] = FunctionTockenReference.SUBTRACT;
                        break;
                    case("*"):
                        intNotation[i] = FunctionTockenReference.MULTIPLY;
                        break;
                    case("/"):
                        intNotation[i] = FunctionTockenReference.DIVIDE;
                        break;
                    case("^"):
                        intNotation[i] = FunctionTockenReference.POW;
                        break;
                    case("sqrt"):
                        intNotation[i] = FunctionTockenReference.SQRT;
                        break;
                    case("log"):
                        intNotation[i] = FunctionTockenReference.LOG;
                        break;
                    case("abs"):
                        intNotation[i] = FunctionTockenReference.ABS;
                        break;
                    case("sin"):
                        intNotation[i] = FunctionTockenReference.SIN;
                        break;
                    case("cos"):
                        intNotation[i] = FunctionTockenReference.COS;
                        break;
                    case("tan"):
                        intNotation[i] = FunctionTockenReference.TAN;
                        break;
                    case("ln"):
                        intNotation[i] = FunctionTockenReference.LN;
                        break;
                    case("("):
                        intNotation[i] = FunctionTockenReference.LEFT_BRACKET;
                        break;
                    case(")"):
                        intNotation[i] = FunctionTockenReference.RIGHT_BRACKET;
                        break;
                    case("sgm"):
                        intNotation[i] = FunctionTockenReference.SIGMOID;
                        break;
                    case("e"):
                        intNotation[i] = FunctionTockenReference.VALUE;
                        constants[i] = Math.E;
                    case("pi"):
                        intNotation[i] = FunctionTockenReference.VALUE;
                        constants[i] = Math.PI;
                }
            }
            i++;
        }
        return (new FunctionStructure(intNotation, constants)).removeNull().addImplicitMultiplication();
    }

//convertisseur tableau d'entier --> string
private String intToStrNotation(){
    String returnString = "";
    double[] constants=this.polishFunction.getConstants();
    int[] intFuncNot=this.polishFunction.getIntFuncNot();
    int maxSize = intFuncNot.length;
    for(int i=0; i<maxSize; i++){
        switch (intFuncNot[i]) {
            case FunctionTockenReference.VARX:
                returnString = returnString.concat("x");
                break;
            case FunctionTockenReference.VARY:
                returnString = returnString.concat("y");
                break;
            case FunctionTockenReference.VARZ:
                returnString = returnString.concat("z");
                break;
            case FunctionTockenReference.ADD:
                returnString = returnString.concat("+");
                break;
            case FunctionTockenReference.SUBTRACT:
                returnString = returnString.concat("-");
                break;
            case FunctionTockenReference.MULTIPLY:
                returnString = returnString.concat("*");
                break;
            case FunctionTockenReference.DIVIDE:
                returnString = returnString.concat("/");
                break;
            case FunctionTockenReference.POW:
                returnString = returnString.concat("^");
                break;
            case FunctionTockenReference.SQRT:
                returnString = returnString.concat("sqrt");
                break;
            case FunctionTockenReference.LOG:
                returnString = returnString.concat("log");
                break;
            case FunctionTockenReference.ABS:
                returnString = returnString.concat("abs");
                break;
            case FunctionTockenReference.SIN:
                returnString = returnString.concat("sin");
                break;
            case FunctionTockenReference.COS:
                returnString = returnString.concat("cos");
                break;
            case FunctionTockenReference.TAN:
                returnString = returnString.concat("tan");
                break;
            case FunctionTockenReference.LN:
                returnString = returnString.concat("ln");
                break;
            case FunctionTockenReference.VALUE:
                returnString = returnString.concat(Double.toString(constants[i]));
                break;
            case FunctionTockenReference.LEFT_BRACKET:
                returnString = returnString.concat("(");
                break;
            case FunctionTockenReference.RIGHT_BRACKET:
                returnString = returnString.concat(")");
                break;
            case FunctionTockenReference.SIGMOID:
                returnString = returnString.concat("sgm");
                break;
            case FunctionTockenReference.NULL:
                returnString = returnString.concat("");
                break;
            }
           
        }
        return returnString;
    }

    //trucs utiles

    public Function removeNull(){
        int[] intNot = this.polishFunction.getIntFuncNot();
        double[] varArray = this.polishFunction.getConstants();

        int i = 0;
        while(intNot[i]!=0){
            i++;
        }
        int[] returnIntNot = new int[i];
        double[] returnVarArray = new double[i];
        for (int j = 0; j < i; j++) {
            returnIntNot[i]=intNot[i];
            returnVarArray[i]=varArray[i];
        }
        return new Function(new FunctionStructure(returnIntNot,returnVarArray));
    }

    public boolean validBracket(Function func){
        int[] funcIntNot= func.getPolishFunc().getIntFuncNot();
        int bracket = 0;

        for (int i=0;i<=funcIntNot.length-1;i++) {
            switch (funcIntNot[i]) {
                case FunctionTockenReference.LEFT_BRACKET:
                    bracket++;
                    break;
                case FunctionTockenReference.RIGHT_BRACKET:
                    bracket--;
                    break;
            }
        }
        return (bracket==0);
    }

    public Function nextBracket(int index){
        FunctionStructure func = this.getPolishFunc();
        int[] funcInt = func.getIntFuncNot();
        double[] funcVar = func.getConstants();

        if (funcInt[index]!= FunctionTockenReference.LEFT_BRACKET) {
            return (new Function(new FunctionStructure(new int[] {funcInt[index]},new double[]{funcVar[index]})));
        }if (funcInt[index]== FunctionTockenReference.LEFT_BRACKET) {
            Function returnFunction = new Function();
            int i = index;
            int bracket=0;
            while(true){
                returnFunction=returnFunction.functionConcat(new Function(new FunctionStructure(new int[] {funcInt[i]},new double[]{funcVar[i]})));
                if (funcInt[i]==FunctionTockenReference.LEFT_BRACKET) {
                    bracket++;
                }else if (funcInt[i]==FunctionTockenReference.RIGHT_BRACKET) {
                    bracket--;
                }
                if (bracket==0) {
                    return (returnFunction);
                }
                i++;
            }

        }
        return new Function();
    }

    public Function functionConcat(Function funcB){
        return new Function(this.getFuncStr().concat(funcB.getFuncStr()));
    }

    public Function functionConcat(String func){
        return new Function(this.getFuncStr().concat(func));
    }

    public Function functionConcat(Function[] funcs){
        String returnFunc = "";

        for (Function func : funcs) {
            returnFunc = returnFunc.concat(func.getFuncStr());
        }

        return new Function(returnFunc);
    }

    public Function functionAddConcat(Function[] funcs){
        String returnFunc = "";

        for (Function func : funcs) {
            returnFunc = "+".concat(returnFunc);
            returnFunc = returnFunc.concat(func.getFuncStr());
        }

        return new Function(returnFunc);
    }

    public Function symplifyFunction(){

        FunctionStructure func = this.getPolishFunc();
        int[] funcInt = func.getIntFuncNot();
        double[] funcVar = func.getConstants();
        Function returnFunction = new Function(new FunctionStructure(funcInt.length));
        System.out.println(funcInt[0] == 17);
        System.out.println(funcInt[funcInt.length - 1] == 18);

        if((funcInt[0]==17)&&(funcInt[funcInt.length-1]==18)){
            for (int i = 1; i < funcInt.length-1; i++) {

                int[] tempInt = {funcInt[i]};
                double[] tempDouble = {funcVar[i]};

                FunctionStructure temp = new FunctionStructure(tempInt,tempDouble);
                returnFunction = returnFunction.functionConcat(new Function(temp));
            }
            
            return returnFunction.symplifyFunction();
        }
            
        return this;
        
    }


    public boolean isInBracket(int[] funcIntNot, int index){
        int bracket = 0;
        for (int i=0;i<=index;i++) {
            switch (funcIntNot[i]) {
                case FunctionTockenReference.LEFT_BRACKET:
                    bracket++;
                    break;
                case FunctionTockenReference.RIGHT_BRACKET:
                    bracket--;
                    break;
            }
        }
        return (bracket!=0);
    }






    //travaux en cours
    private  Function derivative(String var,int iteration){
        iteration++;

        int[] funcInt = this.symplifyFunction().polishFunction.getIntFuncNot();
        double[] funcVar = this.symplifyFunction().polishFunction.getConstants();
        Function tempFunction = new Function();
        Function finalFunction;

        this.symplifyFunction().printDebugFonctionStructure();
        try{
            int i=0;
            while ((funcInt[i]!=FunctionTockenReference.ADD)||(funcInt[i]!=FunctionTockenReference.ADD&&isInBracket(funcInt, i))) {
                tempFunction=tempFunction.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]}, new double[]{funcVar[i]})));
                i++;
            }


        
        }catch(Exception e){
            System.out.println("problème à :".concat(this.strFunc));
        }

        return new Function("0");

    }


















    public Function derivative(Function func){
        return func.derivative("x",0);
    }

    public Function derivative(){
        return this.derivative("x",0);
    }
    public Function derivative(String var){
        return this.derivative(var,0);
    }
    public Function derivative(Function func,String var){
        return func.derivative(var,0);
    }
    public Function derivative(Function func,String var,int iteration){
        return func.derivative(var,iteration);
    }
}