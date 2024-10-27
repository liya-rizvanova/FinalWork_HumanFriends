import java.util.Scanner;

public class AnimalFactory {

    public static Animal createAnimal(Scanner scanner) {
        System.out.print("Enter animal type (Dog, Cat, Hamster, Horse, Camel, Donkey): ");
        String type = scanner.nextLine().toLowerCase();

        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();

        System.out.print("Enter birth date (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();

        System.out.print("Enter commands (comma separated): ");
        String commands = scanner.nextLine();

        switch (type) {
            case "dog":
                return new Dog(name, birthDate, commands);
            case "cat":
                return new Cat(name, birthDate, commands);
            case "hamster":
                return new Hamster(name, birthDate, commands);
            case "horse":
                return new Horse(name, birthDate, commands);
            case "camel":
                return new Camel(name, birthDate, commands);
            case "donkey":
                return new Donkey(name, birthDate, commands);
            default:
                System.out.println("Unknown animal type.");
                return null;
        }
    }
}
