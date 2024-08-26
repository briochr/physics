package functions;

import java.awt.Desktop;  
import java.io.*; 

public class FunctionCompilator{
    public static void main(String[] args){
        newFile("Test",new Function("x^(2)"));
    }

    public static String getTockenString(int i) {
        switch (i) {
                
            case FunctionTockenReference.VARX:
               return ("x");
            case FunctionTockenReference.VARY:
                return("y");
            case FunctionTockenReference.VARZ:
                return("z");
            case FunctionTockenReference.ADD:
                return("+");
            case FunctionTockenReference.SUBTRACT:
                return("-");
            case FunctionTockenReference.MULTIPLY:
                return("*");
            case FunctionTockenReference.DIVIDE:
                return("/");
            case FunctionTockenReference.POW:
                return("Math.pow");
            case FunctionTockenReference.SQRT:
                return("Math.sqrt");
            case FunctionTockenReference.LOG:
                return("Math.log");
            case FunctionTockenReference.ABS:
                return("Math.abs");
            case FunctionTockenReference.SIN:
                return("Math.sin");
            case FunctionTockenReference.COS:
                return("Math.cos");
            case FunctionTockenReference.TAN:
                return("Math.tan");
            case FunctionTockenReference.LN:
                return("Math.ln");
            case FunctionTockenReference.VALUE:
                return("value");
            case FunctionTockenReference.LEFT_BRACKET:
                return("(");
            case FunctionTockenReference.RIGHT_BRACKET:
                return(")");
            case FunctionTockenReference.SIGMOID:
                return("sgm");
            default:
                return("");
            }
    }

    private static String getFuncCodeString(Function func){
        String returnString = "";
        double[] constants=func.getPolishFunc().getConstants();
        int[] intFuncNot=func.getPolishFunc().getIntFuncNot();
        int maxSize = intFuncNot.length;
        for(int i=0; i<maxSize; i++){
            try {
                if (intFuncNot[i+1]==FunctionTockenReference.POW) {
                returnString+="Math.pow(".concat(getTockenString(intFuncNot[i])).concat(",");
                i+=3;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            
            String addStr = getTockenString(intFuncNot[i]);
            if (addStr=="value") {
            returnString+=Double.toString(constants[i]);
            }else{
            returnString+=addStr;
            }
        }
        return returnString;
    }

    public static void newFile(String fileName, Function func) {
        try {

            // Recevoir le fichier 
            File f = new File("C:\\Users\\Rispal\\OneDrive\\Bureau\\java\\PhysicsWoldV2\\functions\\functionFile\\".concat(fileName).concat(".java"));

            // Créer un nouveau fichier
            // Vérifier s'il n'existe pas
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
        String textToWrite = "public class ".concat(fileName).concat("{\r\n" + //
                        "    public static double calcFunc(double x){ \r\n" + //
                        "        return " + getFuncCodeString(func) +"; \r\n" + //
                        "    }\r\n" + //
                        "}");

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\Rispal\\OneDrive\\Bureau\\java\\PhysicsWoldV2\\functions\\functionFile\\".concat(fileName).concat(".java")));
            writer.println(textToWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try{  
            //constructor of file class having file as argument  
            File file = new File("C:\\Users\\Rispal\\OneDrive\\Bureau\\java\\PhysicsWoldV2\\functions\\compile.bat");   
            if(!Desktop.isDesktopSupported()){  
                System.out.println("not supported");  
            }  
            Desktop desktop = Desktop.getDesktop();  
            if(file.exists())
            desktop.open(file);
        }  
        catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
}