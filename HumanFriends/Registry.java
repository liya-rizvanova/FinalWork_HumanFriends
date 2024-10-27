import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registry {
    private List<Animal> animals;
    private Counter counter;
    private static final String CSV_FILE_PATH = "HumanFriends.csv";

    public Registry() {
        animals = new ArrayList<>();
        counter = new Counter();
        loadFromCSV();  // Загружаем животных из файла при запуске
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        counter.increment();
        saveToCSV(animal); // Сохраняем данные нового животного в CSV
    }

    private void saveToCSV(Animal animal) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            String csvLine = animal.getClass().getSimpleName() + "," +
                             animal.getName() + "," +
                             animal.getBirthDate() + "," +
                             animal.getCommands() + "\n";
            writer.write(csvLine);
            System.out.println("Animal record saved to CSV.");
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }

    private void loadFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    String type = fields[0];
                    String name = fields[1];
                    String birthDate = fields[2];
                    String commands = fields[3];

                    // Создаем экземпляр животного в зависимости от типа
                    Animal animal;
                    switch (type) {
                        case "Dog":
                            animal = new Dog(name, birthDate, commands);
                            break;
                        case "Cat":
                            animal = new Cat(name, birthDate, commands);
                            break;
                        case "Hamster":
                            animal = new Hamster(name, birthDate, commands);
                            break;
                        case "Horse":
                            animal = new Horse(name, birthDate, commands);
                            break;
                        case "Camel":
                            animal = new Camel(name, birthDate, commands);
                            break;
                        case "Donkey":
                            animal = new Donkey(name, birthDate, commands);
                            break;
                        default:
                            System.out.println("Unknown animal type: " + type);
                            continue;
                    }
                    animals.add(animal);
                }
            }
            System.out.println("Loaded animals from CSV.");
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
        }
    }

    public void listCommands(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.println("Commands for " + name + ": " + animal.getCommands());
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    public void trainAnimal(String name, String newCommand) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                animal.addCommand(newCommand);
                System.out.println(name + " has been trained with a new command: " + newCommand);
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    public void displayAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName() + " - Name: " + animal.getName() + ", Birth Date: " + animal.getBirthDate() + ", Commands: " + animal.getCommands());
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add New Animal");
            System.out.println("2. Show Animal Commands");
            System.out.println("3. Train Animal");
            System.out.println("4. List All Animals");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    Animal newAnimal = AnimalFactory.createAnimal(scanner);
                    if (newAnimal != null) {
                        addAnimal(newAnimal);
                        System.out.println("New animal added.");
                    } else {
                        System.out.println("Invalid animal type.");
                    }
                    break;
                case 2:
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine();
                    listCommands(name);
                    break;
                case 3:
                    System.out.print("Enter animal name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new command: ");
                    String command = scanner.nextLine();
                    trainAnimal(name, command);
                    break;
                case 4:
                    displayAnimals();
                    break;
                case 5:
                    scanner.close();
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
