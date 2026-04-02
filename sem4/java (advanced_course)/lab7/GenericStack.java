public class GenericStack<E> {

    private int SIZE = 1;
    private E[] arr = (E[]) new Object[SIZE];
    private int count_el = 0;

    public int getSize() {
        return count_el;
    }

    public E peek() {
        return arr[count_el-1];
    }

    public void push(E o) {
        if (getSize() == SIZE) {
            SIZE *= 2;
            E[] new_arr = (E[]) new Object[SIZE];
            int c = 0;
            for (E i : arr) {
                new_arr[c] = i;
                c++;
            }
            arr = new_arr;
        }
        arr[count_el] = o;
        count_el++;
    }

    public E pop() {
        E o = peek();
        arr[count_el-1] = null;
        count_el--;
        return o;
    }

    public boolean isEmpty() {
        return count_el == 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < count_el; i++){
            s.append(arr[i]);
            if (i < (count_el-1)){
                s.append(", ");
            }
        }
        return s + "]";
    }


}