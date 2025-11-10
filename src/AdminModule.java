import java.sql.*;
import java.util.Scanner;

public class AdminModule {

    public static void addBook() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = DBConnection.connect();

            System.out.print("Book title: ");
            String title = sc.nextLine();

            System.out.print("Author: ");
            String author = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();

            String query = "INSERT INTO books (title, author, category, quantity) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, category);
            pst.setInt(4, qty);

            pst.executeUpdate();
            System.out.println("✅ Book Added Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewBooks() {
        try {
            Connection con = DBConnection.connect();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            System.out.println("\n===== BOOK LIST =====");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + ". " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getString("category") + " | Qty: " + rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void issueBook() {
    Scanner sc = new Scanner(System.in);

    try {
        Connection con = DBConnection.connect();

        System.out.println("\n===== ISSUE BOOK =====");

        // ✅ 1. Ask Book ID
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        // ✅ 2. Check if book exists
        String checkBook = "SELECT * FROM books WHERE id=?";
        PreparedStatement pstBook = con.prepareStatement(checkBook);
        pstBook.setInt(1, bookId);
        ResultSet rsBook = pstBook.executeQuery();

        if (!rsBook.next()) {
            System.out.println("❌ Book ID does not exist!");
            return;
        }

        // ✅ 3. Check quantity > 0
        int qty = rsBook.getInt("quantity");

        if (qty <= 0) {
            System.out.println("❌ Book is out of stock!");
            return;
        }

        // ✅ 4. Ask Username
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        // ✅ 5. Check if user exists
        String checkUser = "SELECT * FROM users WHERE username=?";
        PreparedStatement pstUser = con.prepareStatement(checkUser);
        pstUser.setString(1, username);
        ResultSet rsUser = pstUser.executeQuery();

        if (!rsUser.next()) {
            System.out.println("❌ User does not exist!");
            return;
        }

        // ✅ 6. Issue the book
        String issue = "INSERT INTO issued_books (book_id, username) VALUES (?, ?)";
        PreparedStatement pstIssue = con.prepareStatement(issue);
        pstIssue.setInt(1, bookId);
        pstIssue.setString(2, username);
        pstIssue.executeUpdate();

        // ✅ 7. Reduce book quantity
        String updateQty = "UPDATE books SET quantity = quantity - 1 WHERE id=?";
        PreparedStatement pstQty = con.prepareStatement(updateQty);
        pstQty.setInt(1, bookId);
        pstQty.executeUpdate();

        System.out.println("✅ Book issued successfully!");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    public static void viewIssuedBooks() {
        try {
            Connection con = DBConnection.connect();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM issued_books");

            System.out.println("\n===== ISSUED BOOKS =====");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + ". Book ID: " +
                    rs.getInt("book_id") + " | User: " +
                    rs.getString("username") + " | Issued On: " +
                    rs.getTimestamp("issue_date")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
