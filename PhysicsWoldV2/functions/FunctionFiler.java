package functions;
import functions.functionfile.*;

public class FunctionFiler {

    public static void main(String[] args) {
        FunctionFiler ff =new FunctionFiler();
        ff.giveFile(new Function("4x"));
        ff.giveFile(new Function("3x"));
        ff.removeFile(1);
        ff.giveFile(new Function("4x^(2)"));
    }






    private FunctionSlot f1;
    private FunctionSlot f2;
    private FunctionSlot f3;
    private FunctionSlot f4;
    private FunctionSlot f5;
    private FunctionSlot f6;
    private FunctionSlot f7;
    private FunctionSlot f8;
    private FunctionSlot f9;
    private FunctionSlot f10;
    private FunctionSlot f11;
    private FunctionSlot f12;
    private FunctionSlot f13;
    private FunctionSlot f14;
    private FunctionSlot f15;
    private FunctionSlot f16;

    public FunctionFiler(){
        this.f1 = new FunctionSlot();
        FunctionCompilator.newFile("F1", new Function("0"));
        this.f2 = new FunctionSlot();
        FunctionCompilator.newFile("F2", new Function("0"));
        this.f3 = new FunctionSlot();
        FunctionCompilator.newFile("F3", new Function("0"));
        this.f4 = new FunctionSlot();
        FunctionCompilator.newFile("F4", new Function("0"));
        this.f5 = new FunctionSlot();
        FunctionCompilator.newFile("F5", new Function("0"));
        this.f6 = new FunctionSlot();
        FunctionCompilator.newFile("F6", new Function("0"));
        this.f7 = new FunctionSlot();
        FunctionCompilator.newFile("F7", new Function("0"));
        this.f8 = new FunctionSlot();
        FunctionCompilator.newFile("F8", new Function("0"));
        this.f9 = new FunctionSlot();
        FunctionCompilator.newFile("F9", new Function("0"));
        this.f10 = new FunctionSlot();
        FunctionCompilator.newFile("F10", new Function("0"));
        this.f11 = new FunctionSlot();
        FunctionCompilator.newFile("F11", new Function("0"));
        this.f12 = new FunctionSlot();
        FunctionCompilator.newFile("F12", new Function("0"));
        this.f13 = new FunctionSlot();
        FunctionCompilator.newFile("F13", new Function("0"));
        this.f14 = new FunctionSlot();
        FunctionCompilator.newFile("F14", new Function("0"));
        this.f15 = new FunctionSlot();
        FunctionCompilator.newFile("F15", new Function("0"));
        this.f16 = new FunctionSlot();
        FunctionCompilator.newFile("F16", new Function("0"));
    }

    public void giveFile(Function func){
        if(this.f1.dontHaveFunc()){
            this.f1.setfunc(func);
            FunctionCompilator.newFile("F1", func);
            func.assignSolt(1);
        }else if(this.f2.dontHaveFunc()){
            this.f2.setfunc(func);
            FunctionCompilator.newFile("F2", func);
            func.assignSolt(2);
        }else if(this.f3.dontHaveFunc()){
            this.f3.setfunc(func);
            FunctionCompilator.newFile("F3", func);
            func.assignSolt(3);
        }else if(this.f4.dontHaveFunc()){
            this.f4.setfunc(func);
            FunctionCompilator.newFile("F4", func);
            func.assignSolt(4);
        }else if(this.f5.dontHaveFunc()){
            this.f5.setfunc(func);
            FunctionCompilator.newFile("F5", func);
            func.assignSolt(5);
        }else if(this.f6.dontHaveFunc()){
            this.f6.setfunc(func);
            FunctionCompilator.newFile("F6", func);
            func.assignSolt(6);
        }else if(this.f7.dontHaveFunc()){
            this.f7.setfunc(func);
            FunctionCompilator.newFile("F7", func);
            func.assignSolt(7);
        }else if(this.f8.dontHaveFunc()){
            this.f8.setfunc(func);
            FunctionCompilator.newFile("F8", func);
            func.assignSolt(8);
        }else if(this.f9.dontHaveFunc()){
            this.f9.setfunc(func);
            FunctionCompilator.newFile("F9", func);
            func.assignSolt(9);
        }else if(this.f10.dontHaveFunc()){
            this.f10.setfunc(func);
            FunctionCompilator.newFile("F10", func);
            func.assignSolt(10);
        }else if(this.f11.dontHaveFunc()){
            this.f11.setfunc(func);
            FunctionCompilator.newFile("F11", func);
            func.assignSolt(11);
        }else if(this.f12.dontHaveFunc()){
            this.f12.setfunc(func);
            FunctionCompilator.newFile("F12", func);
            func.assignSolt(12);
        }else if(this.f13.dontHaveFunc()){
            this.f13.setfunc(func);
            FunctionCompilator.newFile("F13", func);
            func.assignSolt(13);
        }else if(this.f14.dontHaveFunc()){
            this.f14.setfunc(func);
            FunctionCompilator.newFile("F14", func);
            func.assignSolt(14);
        }else if(this.f15.dontHaveFunc()){
            this.f15.setfunc(func);
            FunctionCompilator.newFile("F15", func);
            func.assignSolt(15);
        }else if(this.f16.dontHaveFunc()){
            this.f16.setfunc(func);
            FunctionCompilator.newFile("F16", func);
            func.assignSolt(16);
        }
    }

    public void removeFile(int i){
        switch (i) {
            case 1:
            this.f1 = new FunctionSlot();
                break;
            case 2:
            this.f2 = new FunctionSlot();
                break;
            case 3:
            this.f3 = new FunctionSlot();
                break;
            case 4:
            this.f4 = new FunctionSlot();
                break;
            case 5:
            this.f5 = new FunctionSlot();
                break;
            case 6:
            this.f6 = new FunctionSlot();
                break;
            case 7:
            this.f7 = new FunctionSlot();
                break;
            case 8:
            this.f8 = new FunctionSlot();
                break;
            case 9:
            this.f9 = new FunctionSlot();
                break;
            case 10:
            this.f10 = new FunctionSlot();
                break;
            case 11:
            this.f11 = new FunctionSlot();
                break;
            case 12:
            this.f12 = new FunctionSlot();
                break;
            case 13:
            this.f13 = new FunctionSlot();
                break;
            case 14:
            this.f14 = new FunctionSlot();
                break;
            case 15:
            this.f15 = new FunctionSlot();
                break;
            case 16:
            this.f16 = new FunctionSlot();
                break;
        }
    }

    public double calc(Function func,double x,double y,double z){
        switch (func.getSlot()) {
            case 0:
                giveFile(func);
                calc(func, x, y, z);
            case 1:
                return F1.calcFunc(x, y, z);
            case 2:
                return F2.calcFunc(x, y, z);
            case 3:
                return F3.calcFunc(x, y, z);
            case 4:
                return F4.calcFunc(x, y, z);
            case 5:
                return F5.calcFunc(x, y, z);
            case 6:
                return F6.calcFunc(x, y, z);
            case 7:
                return F7.calcFunc(x, y, z);
            case 8:
                return F8.calcFunc(x, y, z);
            case 9:
                return F9.calcFunc(x, y, z);
            case 10:
                return F10.calcFunc(x, y, z);
            case 11:
                return F11.calcFunc(x, y, z);
            case 12:
                return F12.calcFunc(x, y, z);
            case 13:
                return F13.calcFunc(x, y, z);
            case 14:
                return F14.calcFunc(x, y, z);
            case 15:
                return F15.calcFunc(x, y, z);
            case 16:
                return F16.calcFunc(x, y, z);
        }






        return 0d;
    }
}



class FunctionSlot{
    private Boolean haveFunc;
    private Function slotedFunc;

    public FunctionSlot(){
        this.haveFunc=false;
        this.slotedFunc=new Function();
    }

    public Boolean getHaveFunc(){
        return haveFunc;
    }
    public Boolean dontHaveFunc(){
        return (haveFunc==false);
    }

    public void setfunc(Function func){
        this.slotedFunc=func;
        this.haveFunc=true;
    }
}