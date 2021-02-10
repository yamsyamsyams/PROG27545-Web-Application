package ca.sheridancollege.hoangjam;


public class MulObject {
    private int a;
    private int b;
    private int result;

    public MulObject() {
        a = (int) (Math.random() * 8) + 1;
        b = (int) (Math.random() * 8) + 1;
        result = a * b;
    }

    public MulObject(int a, int b){
        this.a = a;
        this.b = b;
        result = a * b;
    }

    public int getA(){
        return a;
    }

    public int getB(){
        return b;
    }

    public int getResult(){
        return result;
    }
}
