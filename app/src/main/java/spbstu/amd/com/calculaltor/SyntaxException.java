package spbstu.amd.com.calculaltor;

public class SyntaxException extends Exception {
    private String error;

    public SyntaxException(String str){
        super(str);
        error = str;
    }

    public String toString() {
        return error;
    }
}
