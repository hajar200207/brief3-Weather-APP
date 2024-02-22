import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/weathercity";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Menu menu = new Menu(connection);

            boolean running = true;

            while (running) {
                menu.displayMenu();
                int choice = menu.getUserChoice();

                if (choice == 9) {
                    running = false;
                } else {
                    menu.executeUserChoice(choice);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}
