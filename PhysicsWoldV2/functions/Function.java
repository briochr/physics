package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// salut
public class Function{
    private String strFunc;
    private FunctionStructure polishFunction;
    private int length;
    private int slot;

    //test & debug
    public static void main(String[] args){
        Function func = new Function("z");
        System.out.println("func : "+func.strFunc);
        func.printDebugFonctionStructure();
        System.out.println("func : "+func.strFunc);
        System.out.println("dérivée : "+func.symplifyFunction().derivative().strFunc);
        func.printDebugFonctionStructure();
    }

    public void printDebugFonctionStructure(){
        double[] constants=this.polishFunction.getConstants();
        int[] intFuncNot=this.polishFunction.getIntFuncNot();
        System.out.print("int : ");
        for (int i : intFuncNot) {
            System.out.print(i + "; ");
        }
        System.out.println("");
        System.out.print("value : ");
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
        this.slot = 0;
    }
    public Function(FunctionStructure function){
        this.polishFunction = function.addImplicitMultiplication().removeNull();
        this.strFunc = intToStrNotation();
        this.length = polishFunction.getIntFuncNot().length;
        this.slot = 0;
    }
    public Function(){
        this.strFunc = "";
        this.slot = 0;
    }
    public Function(Function func){
        this.strFunc = func.strFunc;
        this.polishFunction = func.polishFunction;
        this.length = func.length;
        this.slot = 0;
    }

    //getteurs
    public String getFuncStr(){
        return this.strFunc;
    }
    public FunctionStructure getPolishFunc(){
        return this.polishFunction;
    }

    public int getSlot(){
        return this.slot;
    }

    public void assignSolt(int i){
        this.slot=i;
    }

    //convetisseur de fonction en tableau d'entier
    private FunctionStructure strToIntNotation(){
        String funcStr = this.strFunc.toLowerCase();

		funcStr = funcStr.replaceAll("-", "+-");
		funcStr = funcStr.replaceAll("exp", "e^");
		funcStr = funcStr.replaceAll(",", ".");

        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+|\\(|\\)|x|y|z|\\+|\\*|/|\\^|sqrt|log|abs|sin|sen|cos|tan|tg|-|ln|e|pi");
	  
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
        return (new FunctionStructure(intNotation, constants)).addImplicitMultiplication().removeNull();
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
        if (this.length<2) {
            return new Function();
        }


        FunctionStructure func = this.getPolishFunc();
        int[] funcInt = func.getIntFuncNot();
        double[] funcVar = func.getConstants();
        Function returnFunction = new Function(new FunctionStructure(funcInt.length));

        Boolean symplifyable = (funcInt[0]==17)&&(funcInt[funcInt.length-1]==18);

        try {
                for (int i = 1; i < funcInt.length-1; i++) {
                    if (isInBracket(funcInt, i)&&symplifyable==true) {
                        symplifyable=true;
                    }else{
                        symplifyable=false;
                    }
                }
                if (symplifyable) {
                    for (int i = 1; i < funcInt.length-1; i++) {
                        returnFunction=returnFunction.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]},new double[]{funcVar[i]})));
                    }
                    return returnFunction;
                }
                
        } catch (ArrayIndexOutOfBoundsException e) {
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
        /*problèmes:
         * -considère les fonctions de une entitée en fonctions nulles
         * -veut pas simplifier les fonctions au début mais si on le fait juste avant de dériver ça marche
         * 
         */
    private  Function derivative(String var,int iteration){
        iteration++;

        Function symplified = this;
        int[] funcInt = symplified.polishFunction.getIntFuncNot();
        double[] funcVar = symplified.polishFunction.getConstants();
        Function tempFunction = new Function();
        Function tempFuncbis = new Function();
        Function finalFunction;

        System.out.println("");
        System.out.println("on dérive : ".concat(symplified.strFunc));
        symplified.printDebugFonctionStructure();
        System.out.println("funcInt.length  : "+ funcInt.length);
        System.out.println("funcVar.length  : "+ funcVar.length);
        try {
            
            //additions
            try {
                int index = 0;
                for (int i = 0; i < funcInt.length; i++) {
                    if (index==0) {
                        if ((funcInt[i]!=FunctionTockenReference.ADD)||(isInBracket(funcInt, i))) {
                            tempFunction=tempFunction.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]},new double[]{funcVar[i]})));
                            System.out.println(iteration +" : " +i + " : ".concat(tempFunction.strFunc));
                        }else{
                            System.out.println(iteration +" : "+ i + " : addition");
                            index = i+1;
                        }
                    }else{
                        tempFuncbis=tempFuncbis.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]},new double[]{funcVar[i]})));
                    }   
                }
                System.out.println("index : " + index);
                if (index==0) {
                    int error =funcInt[-1];
                }
                
                return tempFunction.derivative(var,iteration).functionConcat("+").functionConcat(tempFuncbis.derivative(var,iteration));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(this.strFunc.concat(" n'est pas une addition"));
            }

            //multiplication
            try {
                
                tempFunction = new Function();
                tempFuncbis = new Function();
                
                int index = 0;
                for (int i = 0; i < funcInt.length; i++) {
                    if (index==0) {
                        if ((funcInt[i]!=FunctionTockenReference.MULTIPLY)||(isInBracket(funcInt, i))) {
                            tempFunction=tempFunction.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]},new double[]{funcVar[i]})));
                            System.out.println(i + " : ".concat(tempFunction.strFunc));
                        }else{
                            System.out.println(i + " : multiplication");
                            index = i+1;
                        }
                    }else{
                        tempFuncbis=tempFuncbis.functionConcat(new Function(new FunctionStructure(new int[]{funcInt[i]},new double[]{funcVar[i]})));
                    }   
                }
                System.out.println("index : " + index);
                if (index==0) {
                    int error =funcInt[-1];
                }
                return tempFunction.functionConcat("*").functionConcat(tempFuncbis);



            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(this.strFunc.concat(" n'est pas une multiplication"));
            }


        
        }catch(Exception e){
            System.out.println(iteration + " : problème à : ".concat(this.strFunc));
            e.printStackTrace();
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