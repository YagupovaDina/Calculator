package spbstu.amd.com.calculaltor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationExpression {
    private EvaluationExpression(){}
        private static String number;
        private static Pattern patternNumber;
        private static String operations;
        private static Pattern patternExpression;
        private static Matcher matcherForExpression;
        private static Matcher matcherForExtractNumber;
        /**
         * инициализируются строки для регулярных выражений
         * структура, в которой происхоид  вычисления выражения, представлена в виде stack (Deque представим в виде стека)
         */
        static {
            number = "\\d+";
            patternNumber = Pattern.compile((number));
            operations = "[\\@ | \\+ | \\* | \\- | \\/]";
            patternExpression = Pattern.compile((number +"|"+operations));
        }

        private static double calcExpr(double a, double b, String operation) throws IllegalArgumentException{
            if (operation.equals("+"))
                return a+b;
            if (operation.equals("-"))
                return a-b;
            if (operation.equals("*"))
                return a*b;
            if (operation.equals("/")) {
                if (b == 0)
                    throw new IllegalArgumentException();
                return a / b;
            }
            return 0;
        }

    private static boolean isOperator(String s){

        if(s.equals("@") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        else return false;
    }
    /**
     *
     * @param expression - числа из арифметического выражения expression записываются в stack,
     *                   далее над числами производится арифметическая операция.
     */
    public static String calculation(String expression) throws IllegalArgumentException,SyntaxException{
        Deque<String> numbers =  new ArrayDeque<>();
        matcherForExpression = patternExpression.matcher(expression);
        while (matcherForExpression.find()){
            if (!matcherForExpression.group().equals(" ")){
                matcherForExtractNumber = patternNumber.matcher(matcherForExpression.group());
                try {
                if (matcherForExtractNumber.matches())
                    numbers.push(matcherForExpression.group());
                else if (matcherForExpression.group().equals("@")) {
                        double a = Double.parseDouble(numbers.pop());
                        Double res = -1 * a;
                        numbers.push(res.toString());
                    } else {
                        double a = Double.parseDouble(numbers.pop());
                        if (numbers.isEmpty())
                            throw new SyntaxException("wrong expression,check operands");
                        double b = Double.parseDouble(numbers.pop());
                        if (isOperator(((Double)b).toString()))
                            throw new SyntaxException("wrong expression,check operands");
                        Double result = calcExpr(b, a, matcherForExpression.group());
                        numbers.push(result.toString());
                    }
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException();
                }
            }
        }
        return numbers.getLast();
    }
}

