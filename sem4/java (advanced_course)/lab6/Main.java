void main() {

    System.out.println("=Задание 1=\n");

    List<Fraction> fractions = new ArrayList<>();
    fractions.add(new Fraction(3, 4));
    fractions.add(new Fraction(-1, 2));
    fractions.add(new Fraction(5, 3));
    fractions.add(new Fraction(-2, 5));
    fractions.add(new Fraction(1, -3));
    fractions.add(new Fraction(4, -6));

    System.out.println("Исходный список:");
    printFractions(fractions);

    Collections.sort(fractions, new AscendingComp());
    System.out.println("\nПо возрастанию):");
    printFractions(fractions);

    Collections.sort(fractions, new DescendingComp());
    System.out.println("\nПо убыванию:");
    printFractions(fractions);

    Collections.sort(fractions, new AbsAscendingComp());
    System.out.println("\nПо возрастанию абсолютных значений:");
    printFractions(fractions);

    Collections.sort(fractions, new AbsDescendingComp());
    System.out.println("\nПо убыванию абсолютных значений:");
    printFractions(fractions);

    System.out.println("\n\n=Задание 2=");

    System.out.println("\n1) Медицинский имеет наивысший приоритет");
    demonstrateQueue(new MedicalFirstComparator());


    System.out.println("\n2) Полиция имеет наивысший приоритет");
    demonstrateQueue(new PoliceFirstComparator());
}

private static void printFractions(List<Fraction> list) {
    for (Fraction f : list) {
        System.out.printf("%s (%.3f)%n", f, f.getValue());
    }
}

private static void demonstrateQueue(Comparator<Car> comparator) {
    PriorityQueue<Car> queue = new PriorityQueue<>(comparator);

    System.out.println("Очередь:");
    Car car1 = new Car("Toyota Camry", CarPriority.REGULAR);
    System.out.println("1. " + car1);
    queue.offer(car1);

    Car car2 = new Car("Ford Focus", CarPriority.REGULAR);
    System.out.println("2. " + car2);
    queue.offer(car2);

    Car car3 = new Car("Ambulance", CarPriority.MEDICAL);
    System.out.println("3. " + car3);
    queue.offer(car3);

    Car car4 = new Car("Police Cruiser", CarPriority.POLICE);
    System.out.println("4. " + car4);
    queue.offer(car4);

    Car car5 = new Car("Honda Civic", CarPriority.REGULAR);
    System.out.println("5. " + car5);
    queue.offer(car5);

    Car car6 = new Car("Another Ambulance", CarPriority.MEDICAL);
    System.out.println("6. " + car6);
    queue.offer(car6);

    Car car7 = new Car("Second Police", CarPriority.POLICE);
    System.out.println("7. " + car7);
    queue.offer(car7);

    System.out.println("\nВ порядке приоритета:");
    int order = 1;
    while (!queue.isEmpty()) {
        Car car = queue.poll();
        System.out.println(order++ + ". " + car + " (Был в очереди под номером " + car.getNum() + ")");
    }
}
