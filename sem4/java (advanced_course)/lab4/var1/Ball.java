package var1;

public class Ball extends SolidOfRevolution{

    public Ball(double radius) {
        super(radius);
    }

    @Override
    public double getVolume() {
        return  (4.0 / 3.0) * Math.PI * Math.pow(getRadius(), 3);
    }

    public String toString() {
        return "Шар{Радиус="+getRadius()+"}";
    }
}
