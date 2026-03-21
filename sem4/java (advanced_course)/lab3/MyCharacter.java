public class MyCharacter {

    private final char value;

    public MyCharacter(char value) {
        this.value = value;
    }

    public char charValue() {
        return this.value;
    }

    public int compareTo(MyCharacter anotherCharacter) {
        if (anotherCharacter == null) throw new NullPointerException("Пустой аргумент");
        return this.value - anotherCharacter.value;
    }

    public boolean equals(Object anotherCharacter) {
        if ((anotherCharacter == null) || (getClass() != anotherCharacter.getClass())) return false;
        return this.value == ((MyCharacter) anotherCharacter).value;
    }

    public boolean isDigit() {
        return isDigit(this);
    }

    public static boolean isDigit(MyCharacter ch) {
        if (ch == null) return false;
        return ch.charValue() >= '0' && ch.charValue() <= '9';


    }

    public static boolean isLetter(MyCharacter ch) {
        if (ch == null) return false;
        char c = ch.charValue();
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') ||
                (c >= 'А' && c <= 'Я') || (c >= 'а' && c <= 'я') || c == 'Ё' || c == 'ё';
    }

    public static boolean isLetterOrDigit(MyCharacter ch) {
        return isLetter(ch) || isDigit(ch);
    }


}
