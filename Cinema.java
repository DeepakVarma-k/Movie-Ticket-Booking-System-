import java.util.*;

// This class handles seat display functionality
class Display {
    // Displays the seat matrix with booked(1) and available(0) seats
    void displayAllSeats(int arr[][], int n) {
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " - ");
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }
}

// Represents a person booking a seat
class Person {
    String name;
    int id;

    Person() {
        this.name = "";
        this.id = 0;
    }

    Person(String name) {
        this.name = name;
        this.id = 0;
    }
}

// Handles ticket booking and cancellation, and extends display functionality
class Counter extends Display {

    // Validates if row and col are within bounds
    boolean isValid(int row, int col, int n) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    // Converts row and column to a unique seat ID
    int changeToInt(int x, int y) {
        return x * 10 + y;
    }

    // Cancels a booked ticket by verifying name and ID
    void cancelTicket(Person arr[][], int seat[][]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Your Name:");
            String str = sc.nextLine();

            System.out.println("Enter Seat ID (e.g., 23 for row 2, col 3):");
            int Id = sc.nextInt();
            sc.nextLine();

            int x = (Id / 10) - 1;
            int y = (Id % 10) - 1;

            // Check for valid seat location
            if (x < 0 || x >= 8 || y < 0 || y >= 8 || seat[x][y] == 0) {
                System.out.println("Invalid ID or seat is not booked. Try again.");
                continue;
            }

            // Verify name and ID match the booking
            if (str.equals(arr[x][y].name) && Id == arr[x][y].id) {
                seat[x][y] = 0; // Mark seat as available
                arr[x][y] = null; // Remove person info
                System.out.println("Your seat has been successfully cancelled.");
                break;
            } else {
                System.out.println("Incorrect details. Please try again.");
            }
        }
    }

    // Books one or more tickets
    void bookTicket(int seat[][], Person arr[][]) {
        Scanner sc = new Scanner(System.in);
        int n = 8; // number of rows/cols

        System.out.println("Enter number of tickets to book:");
        int no = sc.nextInt();
        sc.nextLine(); // Consume leftover newline

        for (int i = 0; i < no; i++) {
            // Ask for person name and create new Person object
            System.out.println("Enter details for ticket " + (i + 1));
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            Person person = new Person(name);

            // Display available seats
            displayAllSeats(seat, n);

            int row, col;

            System.out.println("Rows 1–3: ₹25");
            System.out.println("Rows 4–5: ₹50");
            System.out.println("Rows 6–8: ₹100");

            // Ask for seat and validate
            while (true) {
                System.out.print("Enter row and column (1-8): ");
                row = sc.nextInt();
                col = sc.nextInt();
                sc.nextLine(); // Clear buffer

                // Validate bounds and seat availability
                if (!isValid(row, col, n)) {
                    System.out.println("Invalid seat location. Try again.");
                    continue;
                }
                if (seat[row - 1][col - 1] == 1) {
                    System.out.println("Seat already booked by: " + arr[row - 1][col - 1].name);
                    continue;
                }
                break; // valid seat chosen
            }

            // Book the seat
            person.id = changeToInt(row, col);
            seat[row - 1][col - 1] = 1;
            arr[row - 1][col - 1] = person;

            // Print confirmation
            int price = (row <= 3) ? 25 : (row <= 5) ? 50 : 100;
            System.out.println("Booking successful!");
            System.out.println("Name: " + person.name);
            System.out.println("Seat ID: " + person.id);
            System.out.println("Amount: ₹" + price);
            System.out.println();
        }
    }
}

// Main class with menu options
public class Cinema {
    public static void main(String[] args) {
        Person arr[][] = new Person[8][8]; // Stores booked person info
        int seat[][] = new int[8][8];      // Stores seat status (0=free, 1=booked)

        Scanner sc = new Scanner(System.in);

        while (true) {
            // Show menu options
            System.out.println("------ CINEMA BOOKING SYSTEM ------");
            System.out.println("1 - Book Ticket");
            System.out.println("2 - Cancel Ticket");
            System.out.println("3 - Display Seats");
            System.out.println("0 - Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear newline
            Counter counter = new Counter();

            if (choice == 1) {
                counter.bookTicket(seat, arr);
            } else if (choice == 2) {
                counter.cancelTicket(arr, seat);
            } else if (choice == 3) {
                counter.displayAllSeats(seat, 8);
            } else if (choice == 0) {
                System.out.println("Thank you for using the system!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
