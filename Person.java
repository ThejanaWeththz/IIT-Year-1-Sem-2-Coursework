public class Person {

    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void printInfo() {
        System.out.println("Name: " + this.name  + " " + this.surname);
        System.out.println("Email: " + this.email);
    }
}