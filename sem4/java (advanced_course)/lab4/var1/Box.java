package var1;

import java.util.ArrayList;

public class Box extends Shape{

    private final ArrayList<Shape> arr;
    private double totalArrSize;
    private final double size;

    public Box(double size) {
        this.arr = new ArrayList<>();
        this.size = size;
        this.totalArrSize = 0;
    }

    public boolean add(Shape shape) {

        if (totalArrSize + shape.getVolume() <= this.size) {
            this.totalArrSize += shape.getVolume();
            arr.add(shape);
            return true;
        }
        return false;
    }

    @Override
    public double getVolume() {
        return size;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Коробка{Объём=" + getVolume() + ", Фигуры=[");
        for (Shape shape : this.arr) {
            str.append(shape.toString()).append(", ");
        }
        str.setLength(str.length() - 2);
        str.append("]}");
        return str.toString();
    }
}
