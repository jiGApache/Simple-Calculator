import java.util.ArrayList;

public class ExpressionChecker {

    private  ArrayList<String> operators;

    public ExpressionChecker(){
        operators = new ArrayList<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    public boolean check(String[] params, NumberParser parser){

        //должно быть строго 3 оеранда и оператора вместе взятых
        if(params.length != 3){
            return false;
        }
        // проверка что указан верный оператор
        else if(!operators.contains(params[1])){
            return false;
        }
        // проверка что оба операнда являются римскими
        else if(parser.isRoman(params[0]) && parser.isRoman(params[2])){
            return true;
        }
        //проверка диапазона входных данных
        else if(isNumber(params[0]) && parser.toArabic(params[0]) >= 1 &&  parser.toArabic(params[0]) <= 10 &&
                isNumber(params[2]) && parser.toArabic(params[2]) >= 1 &&  parser.toArabic(params[2]) <= 10){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNumber(String str){
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
