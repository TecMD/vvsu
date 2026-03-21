package var1;

public class Pyramid extends Shape{

    private final double s;
    private final double h;

    public Pyramid(double s, double h) {
        this.s = s;
        this.h = h;
    }

    @Override
    public double getVolume() {
        return (1.0 / 3.0) * s * h;
    }

    public String toString() {
        return "Пирамида{Пл.основания="+this.s+"; Высота="+this.h+"}";
    }
}
