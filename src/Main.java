import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        boolean isEs;
        String operation;

        input = input.replaceAll("\\s", "");
        String [] operands = input.split("[+\\-*/]");
        if (operands.length !=2) throw new Exception("Вы ввели больше двух чисел или использовали неверную арифметическую операцию");
        operation = Counting.detectOperation(input);

        if (EsAndArabian.isEs(operands[0]) && EsAndArabian.isEs(operands[1])) {
            number1 = EsAndArabian.convertToArab(operands[0]);
            number2 = EsAndArabian.convertToArab(operands[1]);
            isEs = true;
        }
        else if (!EsAndArabian.isEs(operands[0]) && !EsAndArabian.isEs(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isEs = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате, только арабские (2 - 1) или только римские числа (I + V)");
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arab = Counting.calculation(number1, number2, operation);
        if (isEs) {
            if (arab <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            input = EsAndArabian.convertToEs(arab);
        } else {
            input = String.valueOf(arab);
        }
        return input;
    }
}

class Numbers{
    static String[] esArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
}

class Counting {
    public static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    public static int calculation(int a, int b, String operation) {
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

class EsAndArabian {

    public static boolean isEs(String val){
        for (String s : Numbers.esArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArab(String es) {
        for (int i = 0; i < Numbers.esArray.length; i++) {
            if (es.equals(Numbers.esArray[i])) {
                return i;
            }
        }
        return 0;
    }

    public static String convertToEs(int arabian) {
        return Numbers.esArray[arabian];
    }
}