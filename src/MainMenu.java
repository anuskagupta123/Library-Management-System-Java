import java.util.Scanner;

public class MainMenu {

    public static void menu(String username, String role) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (role.equals("admin")) {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Issue Book");
                System.out.println("4. View Issued Books");
                System.out.println("5. Logout");

                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> AdminModule.addBook();
                    case 2 -> AdminModule.viewBooks();
                    case 3 -> AdminModule.issueBook();
                    case 4 -> AdminModule.viewIssuedBooks();
                    case 5 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }

            } else {
                System.out.println("\n===== USER MENU =====");
                System.out.println("1. View Books");
                System.out.println("2. Search Book");
                System.out.println("3. Return Book");
                System.out.println("4. Logout");

                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> UserModule.viewBooks();
                    case 2 -> UserModule.searchBook();
                    case 3 -> UserModule.returnBook(username);
                    case 4 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}
