package spbstu.amd.com.calculaltor;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParseExpression {

    /**
     *
     * @param s - входной символ
     * @return у * и / приоритет выше, чем у + и -, унарный минус - @ - самый высокий приоритет
     */
    private  int priorityOperator(String s){
        if (s.equals("@"))
            return 3;
        if(s.equals("+") || s.equals("-"))
            return 1;
        return 2;
    }

    /**
     *
     * @param s - входной символ из строки
     * @return если символ операнд - return true
     */
    private  boolean isOperand(String s){
        if(s.equals("@") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("(") || s.equals(")") || s.equals(" "))
            return false;
        else return true;
    }

    /**
     *
     * @param s - входной символ из строки
     * @return если символ оператор - true, иначе false
     */
    private  boolean isOperator(String s){

        if(s.equals("@") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        else return false;
    }

    private boolean isAmnountOfBracketsEqual(String str){
        int countLeft = str.length() - str.replace("(", "").length();
        int counRight = str.length() - str.replace(")", "").length();
        if (counRight == countLeft)
            return true;
        return false;
    }

    /**
     *
     * @param str - входное выражение
     * @return выражение в обратной польской записи
     */
    public  String parseToPolishNotation(String str) throws SyntaxException {
        if (!isAmnountOfBracketsEqual(str))
            throw new SyntaxException(" brackets are not equal");
        String outputString = "";
        int slength = str.length();
        Deque<String> stack = new ArrayDeque<>();
        for (int i =0;slength > 0;i++,slength--){
            if (String.valueOf(str.charAt(i)).equals("-") &&
                 (i == 0 || String.valueOf(str.charAt(i-1)).equals("(") || isOperator(String.valueOf(str.charAt(i-1))))){
                    stack.push("@");
                }
            //}
            else if(isOperator(String.valueOf(str.charAt(i)))){
                    while (stack.size() > 0) {
                        if (stack.peek().equals("(")) {
                            break;
                        } else if (isOperator(stack.peek())) {
                            if (priorityOperator(stack.peek()) < priorityOperator(String.valueOf(str.charAt(i)))) {
                                break;
                            }
                        }
                        outputString = outputString.concat(stack.pop());
                    }
                    stack.push(String.valueOf(str.charAt(i)));
            }
            else if(String.valueOf(str.charAt(i)).equals("(") || String.valueOf(str.charAt(i)).equals(")")){
                if(String.valueOf(str.charAt(i)).equals("(")){
                    stack.push(String.valueOf(str.charAt(i)));
                }else{
                    while(stack.size() > 0){
                        if(stack.peek().equals("(")){
                            stack.pop();
                            break;
                        } else{
                            outputString = outputString.concat(stack.pop());
                        }
                    }
                }
            }
            else{
                if(outputString.length()==0) outputString = String.valueOf(str.charAt(i));
                else {
                    if(str.length()>1 && !isOperand(String.valueOf(str.charAt(i-1)))) outputString = outputString.concat(" ");
                    outputString = outputString.concat(String.valueOf(str.charAt(i)));
                }
            }
        }
        if (stack.contains("("))
            throw new SyntaxException(" brackets are established wrong");
        while(stack.size()>0){
            outputString = outputString.concat(stack.pop());
        }
        return outputString;
    }
}
