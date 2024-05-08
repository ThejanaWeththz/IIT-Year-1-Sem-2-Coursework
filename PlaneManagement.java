//Importing needed classes
import java.util.*;

public class PlaneManagement {
    // Global variables
    private static String[] rowA = new String[14];
    private static int[] row1 = new int[14];
    private static String[] rowB = new String[12];
    private static int[] row2 = new int[12];
    private static String[] rowC = new String[12];
    private static int[] row3 = new int[12];
    private static String[] rowD = new String[14];
    private static int[] row4 = new int[14];
    private static int seat_number;
    private static String row_letter;
    private static String row_upper;
    private static String name;
    private static String surname;
    private static String email;
    private static Scanner input = new Scanner(System.in);
    private static double price;
    private static Ticket ticketList[] = new Ticket[52];


    // Main method of the program
    public static void main(String[] args) {
        // Deleting all text files in the directory
        Person person = new Person(name,surname,email);
        Ticket ticket = new Ticket(row_upper,seat_number,price,person);
        ticket.deleteTextFiles(".");
        // Creating elements in the arrays to make seats available
        for (int element = 0; element < 14; element++) {
            rowA[element] = "O";
            row1[element] = 0;
            rowD[element] = "O";
            row4[element] = 0;
        }
        for (int element = 0; element < 12; element++) {
            rowB[element] = "O";
            row2[element] = 0;
            rowC[element] = "O";
            row3[element] = 0;
        }

        // Welcome message
        System.out.println("Welcome to the Plane Management application\n");
        // Main while loop
        while (true) {
            System.out.println("""
                    *************************************************
                    *\t\t\t\t\tMENU OPTIONS\t\t\t\t*
                    *************************************************
                    """);
            System.out.print("""
                    \t1) Buy a seat
                    \t2) Cancel a seat
                    \t3) Find first available seat
                    \t4) Show seating plan
                    \t5) Print tickets information and total sales
                    \t6) Search ticket
                    \t0) Exit
                    *************************************************

                    Please select an option :\s""");
            //Getting user inputs for the menu
            try {
                int userOption = input.nextInt();
                switch (userOption) {
                    case 1 :
                        buy_seat();
                        break;
                    case 2 :
                        cancel_seat();
                        break;
                    case 3 :
                        find_first_available();
                        break;
                    case 4 :
                        show_seating_plan();
                        break;
                    case 5 :
                        print_tickets_info();
                        break;
                    case 6 :
                        search_ticket();
                        break;
                    case 0 :
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.next();
            }
        }
    }

    // Method for inputs
    private static void input(){
        while (true) {
            System.out.print("Enter row letter : ");
            row_letter = input.next(); // Getting row letter input
            row_upper = row_letter.toUpperCase(); // Making the row letter input to uppercase
            // Check if row entered by user is valid
            if (row_upper.equals("A") ||
                    row_upper.equals("B") ||
                    row_upper.equals("C") ||
                    row_upper.equals("D")) {

                row_letter = row_upper; // Re-assigning the uppercase value to the row_letter variable

            } else {
                System.out.println("Invalid input");
                continue;
            }

            System.out.print("Enter seat number : ");
            seat_number = input.nextInt();  // Taking an input for the seat number and checking if it is valid

            if ((row_letter.equals("A") || row_letter.equals("D")) && seat_number > 14) {
                System.out.println("Invalid seat number");
                break;
            } else if ((row_letter.equals("B") || row_letter.equals("C")) && seat_number > 12) {
                System.out.println("Invalid seat number");
                break;
            } else {
                System.out.println(" ");
                break;
            }
        }
    }

    // Method for buying a seat
    private static void buy_seat() {
        while (true) {
            input();
            // Checking for seats if already taken
            if ((row_upper.equals("A") && row1[seat_number - 1] == 1) ||
                    (row_upper.equals("B") && row2[seat_number - 1] == 1) ||
                    (row_upper.equals("C") && row3[seat_number - 1] == 1) ||
                    (row_upper.equals("D") && row4[seat_number - 1] == 1)) {
                System.out.println("\nSeat already taken\n Select another.\n");
                break;
            } else {

                // Get passenger information
                System.out.print("Enter your name: ");
                name = input.next();
                System.out.print("Enter your surname: ");
                surname = input.next();
                System.out.print("Enter your email: ");
                email = input.next();

                // Calculate seat price based on row
                if (seat_number < 6) {
                    price = 200;
                } else if (seat_number < 10) {
                    price = 150;
                } else {
                    price = 180;
                }

                // Creating a for loop to check for an empty space in the tickets array
                int emptySlot = -1;
                for (int element = 0; element < ticketList.length; element++) {
                    if (ticketList[element] == null) {
                        emptySlot = element;
                        break;
                    }
                }
                // Creating objects needed
                Person person1 = new Person(name,surname,email);
                Ticket ticket1 = new Ticket(row_upper,seat_number,price,person1);
                // Entering the ticket details in the array
                if (emptySlot != -1) {
                    ticketList[emptySlot] = ticket1;
                }

                ticket1.save();    // Ticket text file creation
                System.out.println("\nSeat booked");
                // Editing the visual array
                if (row_letter.equals("A")) {
                    rowA[seat_number - 1] = String.valueOf("X");
                    row1[seat_number - 1] = 1;
                } else if (row_letter.equals("B")) {
                    rowB[seat_number - 1] = String.valueOf("X");
                    row2[seat_number - 1] = 1;
                } else if (row_letter.equals("C")) {
                    rowC[seat_number - 1] = String.valueOf("X");
                    row3[seat_number - 1] = 1;
                } else {
                    rowD[seat_number - 1] = String.valueOf("X");
                    row4[seat_number - 1] = 1;
                }
                break;
            }
        }
    }

    // Method for canceling a seat
    private static void cancel_seat() {
        while (true) {
            input();
            // Checking to see if seat already available
            if ((row_upper.equals("A") && row1[seat_number - 1] == 0) ||
                    (row_upper.equals("B") && row2[seat_number - 1] == 0) ||
                    (row_upper.equals("C") && row3[seat_number - 1] == 0) ||
                    (row_upper.equals("D") && row4[seat_number - 1] == 0)) {
                System.out.println("\nSeat already available\n Select another.\n");
                break;
            } else {
                System.out.println("\nSeat cancelled");
                // Editing the array
                if (row_letter.equals("A")) {
                    rowA[seat_number - 1] = String.valueOf("O");
                    row1[seat_number - 1] = 0;
                } else if (row_letter.equals("B")) {
                    rowB[seat_number - 1] = String.valueOf("O");
                    row2[seat_number - 1] = 0;
                } else if (row_letter.equals("C")) {
                    rowC[seat_number - 1] = String.valueOf("O");
                    row3[seat_number - 1] = 0;
                } else {
                    rowD[seat_number - 1] = String.valueOf("O");
                    row4[seat_number - 1] = 0;
                }
                // Deleting text file
                for (int i = 0; i < ticketList.length; i++) {
                    if (ticketList[i] != null && ticketList[i].getSeat() == seat_number && ticketList[i].getRow().equals(row_upper)) {
                        ticketList[i].delete(ticketList[i].getRow() + ticketList[i].getSeat() + ".txt");
                        ticketList[i] = null;
                    }
                }
                break;
            }
        }
    }

    // Method for buying the first seat available
    private static void find_first_available() {
        int count = 0;
        // Nested loops to check and find first available seat
        for (int i = 0; i < 14; i++) {
            if (row1[i] == 0) {
                count = 1;
                System.out.println("\nFirst available for purchasing : A" + (i+1));
                break;
            }
        }
        if (count == 0) {
            for (int i = 0; i < 12; i++) {
                if (row2[i] == 0) {
                    count = 1;
                    System.out.println("\nFirst available for purchasing : B" + (i+1));
                    break;
                }
            }
            if (count == 0) {
                for (int i = 0; i < 12; i++) {
                    if (row3[i] == 0) {
                        count = 1;
                        System.out.println("\nFirst available for purchasing : C" + (i+1));
                        break;
                    }
                }
                if (count == 0) {
                    for (int i = 0; i < 14; i++) {
                        if (row4[i] == 0) {
                            count = 1;
                            System.out.println("\nFirst available for purchasing : D" + (i+1));
                            break;
                        }
                    }
                    if (count == 0) {
                        System.out.println("No more seats available");
                    }
                }
            }
        }
    }

    // Method to show the seating plan
    private static void show_seating_plan() {
        // printArray method is called to show seating plan
        System.out.println("Seating Plan :\n");
        printArray(rowA);
        printArray(rowB);
        printArray(rowC);
        printArray(rowD);
    }

    // Method to print the arrays
    private static void printArray(String[] array) {
        for (String element : array) {
            System.out.print(element + "  ");
        }
        System.out.println();
    }

    // Method to show ticket information
    private static void print_tickets_info() {
        double total = 0; // ticketList array is iterated and printInfo method from Ticket class is called
        for (int i = 0; i < ticketList.length; i++){
            if (ticketList[i] != null) {
                ticketList[i].printInfo();
                total = total + ticketList[i].getPrice();
            }
        }
        if (total != 0) {
            System.out.println("Total Price : " + total);
        } else {
            System.out.println("\nNo tickets sold during this session\n");
        }
    }

    // Method to search tickets
    private static void search_ticket() {
        input();
        // Searching for the entered ticket from input
        boolean seatBought = false;
        for (int i = 0; i < ticketList.length; i++) {
            if (ticketList[i] != null && ticketList[i].getSeat() == seat_number && ticketList[i].getRow().equals(row_upper)) {
                seatBought = true;
                ticketList[i].printInfo();
                System.out.println("Seat taken");
                break;
            }
        }
        // if condition to finish the code and show if seat available
        if (seatBought) {
            System.out.println();
        }
        else if (row_upper.equals("A") || row_upper.equals("D") && seat_number > 14){
            System.out.println();
        }
        else if (row_upper.equals("B") || row_upper.equals("C") && seat_number > 12){
            System.out.println();
        } else {
            System.out.println("Seat available");
        }
    }
}