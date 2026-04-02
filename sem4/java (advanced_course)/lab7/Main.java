void main() {
    System.out.println("=Вариант 1=");
    GenericStack<Integer> arr = new GenericStack<>();
    System.out.println("Стэк пустой: " + arr.isEmpty());

    arr.push(1);
    arr.push(2);

    System.out.println("Стэк: " + arr);
    System.out.println("Верхний элемент: " + arr.peek());
    System.out.println("Размер Стэка: " + arr.getSize());

    arr.push(3);
    arr.push(4);
    arr.push(5);

    System.out.println("Стэк: " + arr);
    System.out.println("Удаленный верхний элемент: " + arr.pop());
    System.out.println("Стэк: " + arr);
    System.out.println("Размер Стэка: " + arr.getSize());


    System.out.println("\n=Вариант 2=");
    GenericStack2<Integer> arr2 = new GenericStack2<>();

    for (int i = 0; i < 5; i++) arr2.push(i);
    System.out.println(arr2);

    arr2.addArrayList(new ArrayList<>(){{add(6); add(3); add(7); add(7); add(1); add(10);}});
    System.out.println(arr2);





}
