package var1;

public abstract class Shape {

    public abstract double getVolume();

    public String toString() {
        return "Фигура{Объём="+getVolume()+"}";
    }

}
