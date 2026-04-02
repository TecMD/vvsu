public class GenericStack2<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    public int getSize() {
        return list.size();
    }
    public E peek() {
        return list.get(getSize() - 1);
    }
    public void push(E o) {
        list.add(o);
    }
    public E pop() {
        E o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void addArrayList(java.util.ArrayList<E> o) {
        for (E i : o){
            if (!list.contains(i)) {
                push(i);
            }
        }
    }
    @Override
    public String toString() {
        return "стек: " + list.toString();
    }
}