import java.util.Scanner;

public class Calculator {

    public enum Operation{
        SUM{
            public int action(int x, int y){return x + y;}
        },
        SUB{
            public int action(int x, int y){return x - y;}
        },
        MULT{
            public int action(int x, int y){return x * y;}
        },
        DIV{
            public int action(int x, int y){return x / y;}
        };
        public abstract int action(int x, int y);
    }

    public static void main(String[] args){
        System.out.print("Введите выражение для вычисления (\'q\' для выхода): ");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();

        while(!expression.equals("q")){
            try {
                String result = calculate(expression);
                System.out.println("The result is: " + result);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            finally {
                System.out.print("Введите выражение для вычисления (\'q\' для выхода): ");
                expression = in.nextLine();
            }
        }

        System.out.println("Bye!");
    }

    //Функция подсчета результата введенного выражения
    private static String calculate(String expression) throws Exception {
        // Получаем опранды и оператор
        String[] params = expression.split(" ");

        // parser используется для перевода числа из римского представления в арабское и обратно
        NumberParser parser = new NumberParser();
        // checker используется для проверки входного выражения на соответствие условию задачи
        ExpressionChecker checker = new ExpressionChecker();
        boolean checkResult = checker.check(params, parser);
        if (!checkResult) {
            throw new Exception("Incorrect data");
        }

        Operation op;
        int calcResult = 0;
        switch (params[1]) {
            case "+":
                op = Operation.SUM;
                calcResult = op.action(parser.toArabic(params[0]), parser.toArabic(params[2]));
                break;
            case "-":
                op = Operation.SUB;
                calcResult = op.action(parser.toArabic(params[0]), parser.toArabic(params[2]));
                break;
            case "*":
                op = Operation.MULT;
                calcResult = op.action(parser.toArabic(params[0]), parser.toArabic(params[2]));
                break;
            case "/":
                op = Operation.DIV;
                calcResult = op.action(parser.toArabic(params[0]), parser.toArabic(params[2]));
                break;
        }

        return !parser.isRoman(params[0]) ? Integer.toString(calcResult) : parser.toRoman(calcResult);
    }

}
