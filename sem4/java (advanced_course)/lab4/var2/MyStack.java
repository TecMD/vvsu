package var2;

import java.util.ArrayList;

public class MyStack {
    private ArrayList<Comparable> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Comparable peek() {
        return list.get(getSize() - 1);
    }

    public Comparable pop() {
        Comparable o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public void push(Comparable o) {
        list.add(o);
    }

    @Override
    public String toString() {
        return "стек: " + list.toString();
    }

    public void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Comparable temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

}
