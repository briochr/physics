public class Untitled {
    public static void main(String[] args) {
        int[] i =new int[]{1};
    }
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
            funcTocken[actualTocken++] = derivative(new Function( new FunctionStructure(funcTockenInt,funcTockenVar)),var,iteration);
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
    return (firstFunc.functionConcat("*").functionConcat(derivative(secondFunc,var,iteration)).functionConcat("+").functionConcat(derivative(firstFunc,var,iteration)).functionConcat("*").functionConcat(secondFunc));
} catch (ArrayIndexOutOfBoundsException e) {}



if (funcInt[0]==FunctionTockenReference.SIN) {
    return derivative(nextBracket(2),var,iteration).functionConcat("*").functionConcat("cos(").functionConcat(nextBracket(2)).functionConcat(")");
}
if (funcInt[0]==FunctionTockenReference.COS) {
    return derivative(nextBracket(2),var,iteration).functionConcat("*").functionConcat("(-1)*sin(").functionConcat(nextBracket(2)).functionConcat(")");
}
if (funcInt[0]==FunctionTockenReference.TAN) {
    return derivative(nextBracket(2),var,iteration).functionConcat("*").functionConcat("1+tan(").functionConcat(nextBracket(2)).functionConcat(")*tan()").functionConcat(nextBracket(2)).functionConcat(")");
}
if ((funcInt[0]==FunctionTockenReference.LN)) {
    return derivative(nextBracket(2),var,iteration).functionConcat("*").functionConcat("1/(").functionConcat(nextBracket(2)).functionConcat(")");
}
if (((funcInt[0]==FunctionTockenReference.VALUE)&&funcInt.length==1)) {
    return new Function("0");
}
if ((funcInt[this.nextBracket(0).length]==FunctionTockenReference.POW)&&(this.nextBracket(0).length+this.nextBracket(this.nextBracket(0).length+1).length+1==this.length)) {
    return new Function("0");
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