import java.util.regex.Matcher;
import java.util.regex.Pattern;
// salut
public class Function{
    private String strFunc;
    private FunctionStructure polishFunction;
    private int length;

    //test & debug
    public static void main(String[] args){
        Function func = new Function("x^(2y)");
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
        }if (funcInt[index]!= FunctionTockenReference.RIGHT_BRACKET) {
            return new Function();
        }
        Function[] returnFunction = new Function[funcInt.length-index];
        int i = index;
        while(i < funcInt.length) {
            returnFunction[i-index] = new Function(new FunctionStructure(new int[] {funcInt[i]},new double[]{funcVar[i]}));
            i++;
        }
        return functionConcat(returnFunction);
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

        System.out.println(iteration + this.strFunc);
        try{

            if (iteration>50) {
                System.out.println("boucle <50 dérivation");
                return new Function("0");
            }

            //fonction simplifiée
            Function symplified = this.symplifyFunction();
            FunctionStructure func = symplified.getPolishFunc();

            //utiles pout tout
            int[] funcInt = func.getIntFuncNot();
            double[] funcVar = func.getConstants();

            //utile pour couper la fonction
            int[] funcTockenInt = new int[funcInt.length];
            double[] funcTockenVar = new double[funcInt.length];
            Function[] funcTocken = new Function[funcInt.length];
            int actualTocken=0;


            for (int i = 0; i < funcInt.length-1; i++) {
                if( (funcInt[i]!=FunctionTockenReference.ADD) || (isInBracket(funcInt, i)) ){
                    funcTockenInt[i]=funcInt[i];
                    funcTockenVar[i]=funcVar[i];
                }else{
                    if (i!=0) {
                        funcTocken[actualTocken++] = derivative(new Function( new FunctionStructure(funcTockenInt,funcTockenVar)));
                        funcTockenInt = new int[funcInt.length];
                        funcTockenVar = new double[funcInt.length];
                    }
                }
            }



            //à dériver

            //cas d'un produit
            funcTockenInt = new int[funcInt.length];
            funcTockenVar = new double[funcInt.length];
            int productIndex=-1;

            for (int i = 0; i < funcInt.length-1; i++) {
                if( (funcInt[i]!=FunctionTockenReference.MULTIPLY) || (isInBracket(funcInt, i))){
                    funcTockenInt[i]=funcInt[i];
                    funcTockenVar[i]=funcVar[i];

                }else{
                    productIndex=i;
                }
            }
            try {
                Function firstFunc = new Function(new FunctionStructure(funcTockenInt, funcTockenVar));
                funcTockenInt = new int[funcInt.length-productIndex+1];
                funcTockenVar = new double[funcInt.length-productIndex+1];

                for (int i = productIndex+1; i < funcInt.length-1; i++) {
                    funcTockenInt[i]=funcInt[i];
                    funcTockenVar[i]=funcVar[i];
                }
                Function secondFunc = new Function(new FunctionStructure(funcTockenInt, funcTockenVar));
                return (firstFunc.functionConcat("*").functionConcat(derivative(secondFunc)).functionConcat("+").functionConcat(derivative(firstFunc)).functionConcat("*").functionConcat(secondFunc));
            } catch (ArrayIndexOutOfBoundsException e) {}
            
            

            if (funcInt[0]==FunctionTockenReference.SIN) {
                return derivative(nextBracket(2)).functionConcat("*").functionConcat("cos(").functionConcat(nextBracket(2)).functionConcat(")");
            }
            if (funcInt[0]==FunctionTockenReference.COS) {
                return derivative(nextBracket(2)).functionConcat("*").functionConcat("(-1)*sin(").functionConcat(nextBracket(2)).functionConcat(")");
            }
            if (funcInt[0]==FunctionTockenReference.TAN) {
                return derivative(nextBracket(2)).functionConcat("*").functionConcat("1+tan(").functionConcat(nextBracket(2)).functionConcat(")*tan()").functionConcat(nextBracket(2)).functionConcat(")");
            }
            if ((funcInt[0]==FunctionTockenReference.LN)) {
                return derivative(nextBracket(2)).functionConcat("*").functionConcat("1/(").functionConcat(nextBracket(2)).functionConcat(")");
            }
            if (((funcInt[0]==FunctionTockenReference.VALUE)&&funcInt.length==1)) {
                return new Function("0");
            }
            if ((funcInt[this.nextBracket(0).length]==FunctionTockenReference.POW)&&(this.nextBracket(0).length+this.nextBracket(this.nextBracket(0).length+1).length+1==this.length)) {
                return derivative((new Function("(ln(")).functionConcat(this.nextBracket(0)).functionConcat("))*(").functionConcat(this.nextBracket(this.nextBracket(0).length+1)).functionConcat(")*(").functionConcat((this.nextBracket(0))).functionConcat(")^(").functionConcat(this.nextBracket(this.nextBracket(0).length+1).functionConcat(")"))  );
            }
            if ((var.equals("x"))&&funcInt[0]==FunctionTockenReference.VARX&&this.length==1) {
                return new Function("1");
            }
            if ((var.equals("y"))&&funcInt[0]==FunctionTockenReference.VARY&&this.length==1) {
                return new Function("1");
            }
            if ((var.equals("z"))&&funcInt[0]==FunctionTockenReference.VARZ&&this.length==1) {
                return new Function("1");
            }
            if ((var.equals("x"))&&(funcInt[0]==FunctionTockenReference.VARY||funcInt[0]==FunctionTockenReference.VARZ)&&this.length==1) {
                return new Function("0");
            }
            if ((var.equals("y"))&&(funcInt[0]==FunctionTockenReference.VARX||funcInt[0]==FunctionTockenReference.VARZ)&&this.length==1) {
                return new Function("0");
            }
            if ((var.equals("z"))&&(funcInt[0]==FunctionTockenReference.VARX||funcInt[0]==FunctionTockenReference.VARY)&&this.length==1) {
                return new Function("0");
            }


            
                
            return functionAddConcat(funcTocken);
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
}