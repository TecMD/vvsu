import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("=Вариант 1=\n");

        MyCharacter ch1 = new MyCharacter('1');
        MyCharacter ch2 = new MyCharacter('b');
        MyCharacter ch3 = new MyCharacter('&');

        System.out.println("Символ 1: " + ch1.charValue());
        System.out.println("Разница порядка символа 1 от символа 2: " + ch2.compareTo(ch1));
        System.out.println("Равенство символа 1 и символа '&': " + ch3.equals(new MyCharacter('&')));
        System.out.println("Символ 1 цифра: " + ch1.isDigit());

        System.out.println("[Статичный метод] Символ 2 цифра: " + MyCharacter.isDigit(ch2));
        System.out.println("Символ 2 буква: " + MyCharacter.isLetter(ch2));
        System.out.println("Символ 3 буква или цифра: " +MyCharacter.isLetterOrDigit(ch3));


        System.out.println("\n\n=Вариант 2=\n");

        MyString2 s1 = new MyString2(new char[]{'H', 'e', 'l', 'l', 'o'});
        MyString2 s2 = new MyString2(new char[]{'w', 'o', 'r', 'l', 'd'});
        MyString2 s3 = new MyString2(new char[]{'w', 'o', 'r', 'l', 'd'});

        System.out.println("Подстрока строки 1 от индекса 2: " + s1.substring(2));
        System.out.println("Подстрока строки 1 от индекса 1 до 3: " + s1.substring(1, 3));
        System.out.println("Равенство строк 2 и 3: " + s2.equals(s3));
        System.out.println("Разница порядка строки 1 от строки 2 " + s1.compareTo(s2));
        System.out.println("Строка 2 в массиве символов: " + Arrays.toString(s2.toChars()));
        System.out.println("Булевые значения в виде строк: " + MyString2.valueOf(true) + " и " + MyString2.valueOf(false));



    }
}
