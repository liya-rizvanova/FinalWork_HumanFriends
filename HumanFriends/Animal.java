public abstract class Animal {
    private String name;
    private String birthDate;
    private String commands;

    public Animal(String name, String birthDate, String commands) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCommands() {
        return commands;
    }

    public void addCommand(String newCommand) {
        this.commands += ", " + newCommand;
    }
}
