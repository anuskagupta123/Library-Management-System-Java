import java.sql.*;
import java.util.Scanner;

public class UserModule {

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
                    rs.getString("author") + " | Qty: " + rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchBook() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        try {
            Connection con = DBConnection.connect();

            String query = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== SEARCH RESULTS =====");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("title"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void returnBook(String username) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DBConnection.connect();

            System.out.println("\n===== RETURN BOOK =====");

        // ✅ 1. Show user their issued books   
            String getIssued = "SELECT ib.id, b.title FROM issued_books ib JOIN books b ON ib.book_id=b.id WHERE ib.username=? AND ib.return_date IS NULL";
            PreparedStatement pstIssued = con.prepareStatement(getIssued);
            pstIssued.setString(1, username);
            ResultSet rs = pstIssued.executeQuery();

            System.out.println("Your issued books:");
            boolean hasBooks = false;

            while (rs.next()) {
                hasBooks = true;
                System.out.println(rs.getInt("id") + ". " + rs.getString("title"));
            }

            if (!hasBooks) {
                System.out.println("❌ You have no books to return.");
                return;
            }

        // ✅ 2. Ask for issued_id
            System.out.print("Enter Issued ID to return: ");
            int issuedId = sc.nextInt();

        // ✅ 3. Check if valid issued record
            String checkIssued = "SELECT book_id FROM issued_books WHERE id=? AND username=? AND return_date IS NULL";
            PreparedStatement pstCheck = con.prepareStatement(checkIssued);
            pstCheck.setInt(1, issuedId);
            pstCheck.setString(2, username);
            ResultSet rsCheck = pstCheck.executeQuery();

            if (!rsCheck.next()) {
                System.out.println("❌ Invalid issued ID or book already returned.");
                return;
            }

            int bookId = rsCheck.getInt("book_id");

        // ✅ 4. Mark book as returned
            String updateIssued = "UPDATE issued_books SET return_date = NOW() WHERE id=?";
            PreparedStatement pstUpdate = con.prepareStatement(updateIssued);
            pstUpdate.setInt(1, issuedId);
            pstUpdate.executeUpdate();

        // ✅ 5. Increase quantity in books table
            String updateQty = "UPDATE books SET quantity = quantity + 1 WHERE id=?";
            PreparedStatement pstQty = con.prepareStatement(updateQty);
            pstQty.setInt(1, bookId);
            pstQty.executeUpdate();

            System.out.println("✅ Book returned successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

}
