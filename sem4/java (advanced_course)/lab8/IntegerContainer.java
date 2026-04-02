public class IntegerContainer {

    private final int LENGTH = 10;
    private final Integer[] arr = new Integer[LENGTH];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public int peek() throws ContainerEmptyException {
        if (isEmpty()) throw new ContainerEmptyException("Невозможно получить элемент: Массив пустой");
        return arr[size-1];
    }

    public void push(int e) throws ContainerFullException {
        if (size == LENGTH) throw new ContainerFullException("Невозможно добавить элемент " + e + ": Массив переполнен (Размер "+LENGTH+")");
        arr[size] = e;
        size++;
    }

    public int pop() throws ContainerEmptyException {
        if (isEmpty()) throw new ContainerEmptyException("Невозможно удалить элемент: Массив пустой");
        int e = peek();
        arr[size-1] = null;
        size--;
        return e;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void sort() throws SortException {
        if (isEmpty()) throw  new SortException("Невозможна сортировка: Массив пустой");

        boolean flag = true;
        int e = arr[0];
        for (int i : arr) {
            if (i != e) {
                flag = false;
                break;
            }
        }
        if (flag) throw new SortException("Невозможна сортировка: Элементы массива равны");

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public int find(int value) throws ContainerEmptyException, FindReplayException {
        if (size == 0) {
            throw new ContainerEmptyException("Невозможен поиск: Массив пустой");
        }

        int firstIndex = -1;
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                if (firstIndex == -1) {
                    firstIndex = i;
                }
                count++;
            }
        }

        if (count == 0) {
            return -1;
        } else if (count > 1) {
            throw new FindReplayException("Найдено несколько элементов (" + count + ") со значением " + value);
        }

        return firstIndex;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++){
            s.append(arr[i]);
            if (i < (size-1)){
                s.append(", ");
            }
        }
        return s + "]";
    }

}

class ContainerFullException extends Exception {
    public ContainerFullException(String msg) {
        super(msg);
    }
}
class ContainerEmptyException extends Exception {
    public ContainerEmptyException(String msg) {
        super(msg);
    }
}
class SortException extends Exception {
    public SortException(String msg) {
        super(msg);
    }
}
class FindReplayException extends Exception {
    public FindReplayException(String msg) {
        super(msg);
    }
}


