package uteevbkru;

public class TestHW5My {
    private int a;
    private String s;

    public TestHW5My(){

    }
    public TestHW5My(Integer A){
        this.a = A;
    }

    public TestHW5My(Integer A, String name){
        this.a = A;
        this.s = name;
    }

    int getA(){
        return a;
    }

    String getS(){
        return s;
    }

    void setDefault(){
        a = 1;
        s = "";
    }

}
