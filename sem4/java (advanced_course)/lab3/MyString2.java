public class MyString2 {

    private final char[] chars;

    public MyString2(char[] chars) {
        this.chars = chars;
    }

    public MyString2 substring(int begin) {

        return this.substring(begin, this.chars.length - 1);

    }

    public MyString2 substring(int begin, int end) {

        if ((begin >= chars.length) || ((end >= chars.length)) ||
                (begin < 0) || ((end < 0)) || (begin > end))
            throw new NullPointerException("Один из индексов вне строки и/или начальынй индекс больше конечного");

        char[] newChar = new char[end - begin + 1];
        for (int i = 0; i < end - begin + 1; i++) newChar[i] = this.chars[i + begin];

        return new MyString2(newChar);

    }

    public boolean equals(Object obj) {

        if ((obj == null) || (getClass() != obj.getClass())) return false;
        MyString2 sObj = (MyString2) obj;

        if (this.chars.length != sObj.chars.length) return false;

        for (int i = 0; i < chars.length; i++) if (this.chars[i] != sObj.chars[i]) return false;
        return true;

    }

    public int compareTo(MyString2 obj) {

        if (obj == null) throw new NullPointerException("Пустой аргумент");

        int minLength = Math.min(this.chars.length, obj.chars.length);

        for (int i = 0; i < minLength; i++) if (this.chars[i] != obj.chars[i]) return this.chars[i] - obj.chars[i];
        return this.chars.length - obj.chars.length;

    }

    public char[] toChars() {
        return this.chars;
    }


    public static MyString2 valueOf(boolean b) {
        if (b) return new MyString2(new char[]{'t', 'r', 'u', 'e'});
        else return new MyString2(new char[]{'f', 'a', 'l', 's', 'e'});
    }

    public String toString() {
        return new String(chars);
    }

}
