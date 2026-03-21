package var1;

public abstract class SolidOfRevolution extends Shape{

    private final double radius;

    public SolidOfRevolution(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return "ТелоВращения{Радиус="+radius+"}";
    }

}
