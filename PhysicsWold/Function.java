import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function{
    private String strFunc;
    private FunctionStructure polishFunction;

    //test & debug
    public static void main(String[] args){
        Function func = new Function("x^(2)");
        System.out.println(func.getFuncStr());
        Function funcB = new Function(func.strToIntNotation());
        System.out.println(funcB.getFuncStr());
        func.printDebugFonctionStructure();
        funcB.printDebugFonctionStructure();
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
        this.polishFunction = strToIntNotation();
    }
    public Function(FunctionStructure function){
        this.polishFunction = function;
        this.strFunc = intToStrNotation();
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
                }
            }
            i++;
        }
        
        return new FunctionStructure(intNotation, constants);
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
                returnString.concat("x");
                break;
            case FunctionTockenReference.VARY:
                returnString.concat("y");
                break;
            case FunctionTockenReference.VARZ:
                returnString.concat("z");
                break;
            case FunctionTockenReference.ADD:
                returnString.concat("+");
                break;
            case FunctionTockenReference.SUBTRACT:
                returnString.concat("-");
                break;
            case FunctionTockenReference.MULTIPLY:
                returnString.concat("=");
                break;
            case FunctionTockenReference.DIVIDE:
                returnString.concat("/");
                break;
            case FunctionTockenReference.POW:
                returnString.concat("^");
                break;
            case FunctionTockenReference.SQRT:
                returnString.concat("sqrt");
                break;
            case FunctionTockenReference.LOG:
                returnString.concat("log");
                break;
            case FunctionTockenReference.ABS:
                returnString.concat("abs");
                break;
            case FunctionTockenReference.SIN:
                returnString.concat("sin");
                break;
            case FunctionTockenReference.COS:
                returnString.concat("cos");
                break;
            case FunctionTockenReference.TAN:
                returnString.concat("tan");
                break;
            case FunctionTockenReference.LN:
                returnString.concat("ln");
                break;
            case FunctionTockenReference.VALUE:
                returnString.concat(Double.toString(constants[i]));
                break;
            case FunctionTockenReference.LEFT_BRACKET:
                returnString.concat("x");
                break;
            case FunctionTockenReference.RIGHT_BRACKET:
                returnString.concat("x");
                break;
            case FunctionTockenReference.SIGMOID:
                returnString.concat("x");
                break;
            }
        }
        System.out.println(returnString);
        return returnString;
    }
}