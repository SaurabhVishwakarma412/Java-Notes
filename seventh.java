// Lesson 7: classes, objects, constructors, and encapsulation.
public class seventh {
    public static void main(String[] args) {
        Student student = new Student("Mina", 17);
        System.out.println(student.getName() + " is " + student.getAge());
        student.haveBirthday();
        System.out.println("Next year: " + student.getAge());

        Point first = new Point(2, 3);
        Point second = new Point(5, 7);
        System.out.println("Manhattan distance: " + first.manhattanDistanceTo(second));
    }

    // Keep fields private and expose validated operations through methods.
    static class Student {
        private final String name;
        private int age;

        Student(String name, int age) {
            if (age < 0) {
                throw new IllegalArgumentException("age must not be negative");
            }
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        void haveBirthday() {
            age++;
        }
    }

    static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int manhattanDistanceTo(Point other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }
    }
}