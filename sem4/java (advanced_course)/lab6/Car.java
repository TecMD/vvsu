import java.util.Comparator;

enum CarPriority {
    MEDICAL(3),
    POLICE(2),
    REGULAR(1);

    private final int value;

    CarPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class Car {
    private String model;
    private CarPriority priority;
    private int num;

    private static int counter = 0;

    public Car(String model, CarPriority priority) {
        this.model = model;
        this.priority = priority;
        this.num = ++counter;
    }

    public CarPriority getPriority() {
        return priority;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return model + " [" + priority + "]";
    }
}

// Медицинский > Полиция
class MedicalFirstComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        int priorityCompare = Integer.compare(c2.getPriority().getValue(), c1.getPriority().getValue());
        if (priorityCompare == 0) {
            return Integer.compare(c1.getNum(), c2.getNum());
        }
        return priorityCompare;
    }
}

// Полиция > Медицинский
class PoliceFirstComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        int priorityCompare = Integer.compare(getPriorityValue(c2.getPriority()), getPriorityValue(c1.getPriority()));
        if (priorityCompare == 0) {
            return Integer.compare(c1.getNum(), c2.getNum());
        }
        return priorityCompare;
    }

    private int getPriorityValue(CarPriority priority) {
        switch (priority) {
            case POLICE: return 3;
            case MEDICAL: return 2;
            case REGULAR: return 1;
            default: return 0;
        }
    }
}