package var1;

public class Cylinder extends SolidOfRevolution{

    private final double height;

    public Cylinder(double height, double radius) {

        super(radius);
        this.height = height;

    }

    @Override
    public double getVolume() {
        return Math.PI * Math.pow(getRadius(), 2) * height;
    }

    public String toString() {
        return "Цилиндр{Высота="+height+"}";
    }
}
