//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=Вариант 1=\n");

        MyTime time1 = new MyTime();
        MyTime time2 = new MyTime(555550000L);
        MyTime time3 = new MyTime(5, 23, 55);

        System.out.println("Время объектов MyTime:");
        System.out.println("time1 (текущее время): " + time1);
        System.out.println("time2 (555550000 мс): " + time2);
        System.out.println("time3 (5:23:55): " + time3);


        System.out.println("\nПроверка time2:");
        System.out.println("Часы: " + time2.getHour());
        System.out.println("Минуты: " + time2.getMinute());
        System.out.println("Секунды: " + time2.getSecond());


        System.out.println("\n\n=Вариант 2=\n");

        int arraySize = 1000;
        int[] numbers = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            numbers[i] = (int)(Math.random() * 100000);
        }

        System.out.println("Сортировка " + arraySize + " элементов");

        StopWatch stopwatch = new StopWatch();
        bubbleSort(numbers);
        stopwatch.stop();

        System.out.println("\nВремя выполнения: " + stopwatch.getElapsedTime() + " мс");
        System.out.println("Время выполнения: " + (stopwatch.getElapsedTime() / 1000.0) + " секунд");

        System.out.println("\nStart time: " + stopwatch.getStartTime());
        System.out.println("End time: " + stopwatch.getEndTime());


    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}