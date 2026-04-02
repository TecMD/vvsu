import java.util.*;

class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    private void normalize() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public double getValue() {
        return (double) numerator / denominator;
    }

    public double getAbsValue() {
        return Math.abs(getValue());
    }

    @Override
    public int compareTo(Fraction other) {
        return Double.compare(this.getValue(), other.getValue());
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

}

class AscendingComp implements Comparator<Fraction> {
    @Override
    public int compare(Fraction f1, Fraction f2) {
        return Double.compare(f1.getValue(), f2.getValue());
    }
}

class DescendingComp implements Comparator<Fraction> {
    @Override
    public int compare(Fraction f1, Fraction f2) {
        return Double.compare(f2.getValue(), f1.getValue());
    }
}

class AbsAscendingComp implements Comparator<Fraction> {
    @Override
    public int compare(Fraction f1, Fraction f2) {
        return Double.compare(f1.getAbsValue(), f2.getAbsValue());
    }
}

class AbsDescendingComp implements Comparator<Fraction> {
    @Override
    public int compare(Fraction f1, Fraction f2) {
        return Double.compare(f2.getAbsValue(), f1.getAbsValue());
    }
}
