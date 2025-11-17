import java.util.ArrayList;
import java.util.Scanner;
// ========= BOOK CLASS ==========
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public void issueBook() { isIssued = true; }
    public void returnBook() { isIssued = false; }
}
// ======= USER CLASS =======
class User {
    private int userId;
    private String name;
    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    public int getUserId() { return userId; }
    public String getName() { return name; }
}
// ======== LIBRARY CLASS =======
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    public void addBook(Book book) {
        books.add(book);
    }
    public void addUser(User user) {
        users.add(user);
    }
    // Display all books with their status
    public void displayBooks() {
        System.out.println("\n----- Book List -----");
        for (Book b : books) {
            System.out.println(
                b.getId() + ". " + b.getTitle() + " - " + b.getAuthor() +
                (b.isIssued() ? " (Issued)" : " (Available)")
            );
        }
    }
    // Issue a book if available
    public void issueBook(int bookId, int userId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (b.isIssued()) {
                    System.out.println("Book is already issued.");
                } else {
                    b.issueBook();
                    System.out.println("Book issued to User " + userId);
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
    // Return a book
    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (b.isIssued()) {
                    b.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
// ======== MAIN CLASS =========
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        // Adding sample data
        library.addBook(new Book(1, "Harry Potter", "J.K. Rowling"));
        library.addBook(new Book(2, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(3, "Wings of Fire", "A.P.J. Abdul Kalam"));
        library.addUser(new User(101, "Kavya"));
        library.addUser(new User(102, "Rahul"));
        int choice;
        do {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. Display Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(bookId, userId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}
