import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private String row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public void save() {
        FileWriter file = null;
        try {
            file = new FileWriter(this.row + this.seat + ".txt");
            file.write("Name : " + person.getName() + " " + person.getSurname() + "\n");
            file.write("Email : " + person.getEmail()  + "\n");
            file.write("Seat : " + this.row + this.seat  + "\n");
            file.write("Price : " + this.price  + "\n");
            file.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void delete(String name) {
        File file = null;
        file = new File(name);
        file.delete();
    }

    public void deleteTextFiles(String path) {

        File file1 = new File(path);
        File[] filesList = file1.listFiles();

        for (File file2 : filesList) {
            if (file2.exists() && file2.getName().endsWith(".txt")) {
                    file2.delete();
            }
        }
    }

    public void printInfo() {
        person.printInfo();
        System.out.println("Seat : " + this.row + this.seat);
        System.out.println("Price : " + this.price + "\n");
    }
}
