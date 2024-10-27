public class Main {
    public static void main(String[] args) {
        try (Counter counter = new Counter()) {
            Registry registry = new Registry();
            registry.menu();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
