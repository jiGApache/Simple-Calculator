import java.util.HashMap;
import java.util.Map;

public class NumberParser implements Parser {

    private Map<String, Integer> romanNumbers;

    public NumberParser(){
        romanNumbers = new HashMap<String, Integer>();
        romanNumbers.put("I", 1);
        romanNumbers.put("II", 2);
        romanNumbers.put("III", 3);
        romanNumbers.put("IV", 4);
        romanNumbers.put("V", 5);
        romanNumbers.put("VI", 6);
        romanNumbers.put("VII", 7);
        romanNumbers.put("VIII", 8);
        romanNumbers.put("IX", 9);
        romanNumbers.put("X", 10);
    }

    public int toArabic(String number){

        return !isRoman(number) ? Integer.parseInt(number) : romanNumbers.get(number);

    }

    public boolean isRoman(String number){

        return romanNumbers.containsKey(number);

    }

    public String toRoman(int number){

        //римское число не может быть 0 или меньше
        //в этом случае возвращаем пустую строку
        if(number <= 0)
            return "";

        StringBuilder resultRoman = new StringBuilder();

        // Выделяем количесвто сотен (по условию задачи их не больше 1)
        int hundreds = number / 100;
        resultRoman.append( C(hundreds) );
        // остаток из сотен
        int modulo = number % 100;

        // Выделяем количество десятков после определения сотен
        double dozensToHundred = (double)modulo / 10.0;
        resultRoman.append(LandX(dozensToHundred));
        // остаток
        modulo = modulo % 50 % 10;

        // Выделяем то что осталось
        resultRoman.append(basic(modulo));
        return resultRoman.toString();

    }

    // преобразовываем целые сотни
    private String C(int hundreds){
        if(hundreds != 0) {
            return "C";
        }
        else {
            return "";
        }
    }

    private String LandX(double dozensToHundred) {
        StringBuilder result = new StringBuilder();
        // если 90..100
        if (dozensToHundred >= 9) result.append("XC");
            //если 50..90
        else if (dozensToHundred >= 5) {
            result.append("L");
            //считаем сколько десятков осталось ПОСЛЕ определения полусотен (L)
            double lastDozens = Math.floor(dozensToHundred % 5);
            if ((lastDozens != 0) && (lastDozens < 4)) {
                int i = 0;
                while (i < lastDozens) {
                    result.append("X");
                    i++;
                }
            }

        }
        //если 40..50
        else if (dozensToHundred >= 4 && dozensToHundred < 5) result.append("XL");
            //если 10..40
        else if (dozensToHundred >= 1 && dozensToHundred < 4) {
            //считаем сколько десятков осталось БЕЗ определения полусотен (L)
            double lastDozens = Math.floor(dozensToHundred % 5);
            int i = 0;
            while (i < lastDozens) {
                result.append("X");
                i++;
            }
        }
        return result.toString();
    }

    // От 1 до 9, то что осталось
    private String basic(int modulo) {

        String[] availableNums = new String[]{"", "I", "II", "III",  "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return availableNums[modulo];

    }

}
