import var1.Ball;
import var1.Box;
import var1.Cylinder;
import var1.Pyramid;
import var2.Animal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Box box = new Box(100);

        Ball ball = new Ball(2);
        Cylinder cylinder = new Cylinder(2, 5);
        Pyramid pyramid = new Pyramid(10, 6);

        System.out.println(ball);
        System.out.println(cylinder);
        System.out.println(pyramid);
        System.out.println();

        System.out.println("Добавлен шар: " + box.add(ball));
        System.out.println("Добавлен цилиндр: " + box.add(cylinder));
        System.out.println("Добавлена пирамида: " + box.add(pyramid));
        System.out.println();

        Ball bigBall = new Ball(4);
        System.out.println(bigBall);
        System.out.println("Добавлен большой шар: " + box.add(bigBall));
        System.out.println();

        System.out.println(box);



        System.out.println("=== Тест с числами Integer ===");
        MyStack intStack = new MyStack();
        intStack.push(5);
        intStack.push(2);
        intStack.push(8);
        intStack.push(1);
        intStack.push(9);

        System.out.println("До сортировки: " + intStack);
        intStack.sort();
        System.out.println("После сортировки: " + intStack);

        // Тест 2: Стек строк String
        System.out.println("\n=== Тест со строками String ===");
        MyStack stringStack = new MyStack();
        stringStack.push("banana");
        stringStack.push("apple");
        stringStack.push("cherry");
        stringStack.push("date");

        System.out.println("До сортировки: " + stringStack);
        stringStack.sort();
        System.out.println("После сортировки: " + stringStack);

        // Тест 3: Стек с пользовательским классом
        System.out.println("\n=== Тест с пользовательским классом Person ===");
        MyStack personStack = new MyStack();
        personStack.push(new Animal("Alice", "Кошка"));
        personStack.push(new Animal("Bob", "Собака"));
        personStack.push(new Animal("Charlie", "Бурундук"));
        personStack.push(new Animal("Diana", "Рыбка"));

        System.out.println("До сортировки: " + personStack);
        personStack.sort();
        System.out.println("После сортировки (по имени): " + personStack);
    }

}