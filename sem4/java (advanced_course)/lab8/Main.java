void main() {

    IntegerContainer container = new IntegerContainer();

    System.out.println("Добавление элементов:");
    try {
        container.push(10);
        container.push(5);
        container.push(8);
        container.push(3);
        container.push(1);
        container.push(7);
        container.push(6);
        container.push(12);
        container.push(18);
        container.push(0);
        System.out.println(container);

        container.push(20);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("\nУдаление элементов:");
    try {
        IntegerContainer container2 = generateContainer(1);
        System.out.println(container2);
        System.out.println("Удалён элемент: " + container2.pop());
        container2.pop();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("\nДемонстрация сортировки:");
    try {
        IntegerContainer container2 = generateContainer(10);
        System.out.println(container2);
        container2.sort();
        System.out.println(container2);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("\nПоиск элемента:");
    try {
        int index = container.find(5);
        if (index != -1) {
            System.out.println("Элемент 5 найден на позиции: " + index);
        } else {
            System.out.println("Элемент не найден");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    try {
        System.out.println("\nПопытка сортировки пустого контейнера:");
        generateContainer(0).sort();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("\nСортировка с одинаковыми элементами:");
    try {
        generateContainer(10, 3, 3).sort();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println("\nПоиск с дубликатами:");
    try {
        IntegerContainer container3 = new IntegerContainer(){{
            push(1);
            push(1);
            push(1);
            push(1);
            push(2);
            push(2);
            push(2);
            push(2);
            push(3);
            push(4);
        }};
        int index = container3.find(2);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

private IntegerContainer generateContainer(int size, int x1, int x2) throws ContainerFullException {
    IntegerContainer arr = new IntegerContainer();
    Random random = new Random();
    for (int i = 0; i < size; i++) {
        arr.push(random.nextInt(x2 - x1 + 1) + x1);
    }
    return arr;
}
private IntegerContainer generateContainer(int size) throws ContainerFullException {
    return generateContainer(size, 0, 100);
}
