import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Connection connection;
    private City city;
    private CityHistory cityHistory;

    public Menu(Connection connection) {
        this.connection = connection;
        this.city = new City(connection);
        this.cityHistory = new CityHistory(connection);
    }

    public void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Créer un nouvel enregistrement ville");
        System.out.println("2. Lire un enregistrement ville");
        System.out.println("3. Mettre à jour un enregistrement ville");
        System.out.println("4. Supprimer un enregistrement ville");
        System.out.println("5. Créer un nouvel enregistrement historique");
        System.out.println("6. Lire l'historique complet d'une ville");
        System.out.println("7. Mettre à jour un enregistrement historique");
        System.out.println("8. Supprimer un enregistrement historique");
        System.out.println("9. Quitter");
    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez choisir une option : ");
        return scanner.nextInt();
    }

    public void executeUserChoice(int choice) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                System.out.print("ID de la ville : ");
                int cityId = scanner.nextInt();
                System.out.print("Nom de la ville : ");
                String cityName = scanner.next();
                System.out.print("Température actuelle : ");
                double currentTemperature = scanner.nextDouble();
                System.out.print("Humidité actuelle : ");
                double currentHumidity = scanner.nextDouble();
                System.out.print("Vitesse du vent actuelle : ");
                double currentWindSpeed = scanner.nextDouble();

                city.createCityRecord(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed);
                break;

            case 2:
                System.out.print("ID de la ville à lire : ");
                int cityIdToRead = scanner.nextInt();
                city.readCityRecord(cityIdToRead);
                break;

            case 3:
                System.out.print("ID de la ville à mettre à jour : ");
                int cityIdToUpdate = scanner.nextInt();
                System.out.print("Nouveau nom de la ville : ");
                String updatedCityName = scanner.next();
                System.out.print("Nouvelle température actuelle : ");
                double updatedCurrentTemperature = scanner.nextDouble();
                System.out.print("Nouvelle humidité actuelle : ");
                double updatedCurrentHumidity = scanner.nextDouble();
                System.out.print("Nouvelle vitesse du vent actuelle : ");
                double updatedCurrentWindSpeed = scanner.nextDouble();

                city.updateCityRecord(cityIdToUpdate, updatedCityName, updatedCurrentTemperature, updatedCurrentHumidity, updatedCurrentWindSpeed);
                break;

            case 4:
                System.out.print("ID de la ville à supprimer : ");
                int cityIdToDelete = scanner.nextInt();
                city.deleteCityRecord(cityIdToDelete);
                break;

            case 5:
                System.out.print("ID de l'enregistrement historique : ");
                int historicalDataId = scanner.nextInt();
                System.out.print("ID de la ville : ");
                int cityIdForHistory = scanner.nextInt();
                System.out.print("Date de l'événement : ");
                String eventDate = scanner.next();
                System.out.print("Température : ");
                double temperature = scanner.nextDouble();

                cityHistory.createHistoricalRecord(historicalDataId, cityIdForHistory, eventDate, temperature);
                break;

            case 6:
                System.out.print("ID de la ville dont vous souhaitez lire l'historique : ");
                int cityIdForHistoryRead = scanner.nextInt();
                cityHistory.readCityHistory(cityIdForHistoryRead);
                break;

            case 7:
                System.out.print("ID de l'enregistrement historique à mettre à jour : ");
                int historicalDataIdToUpdate = scanner.nextInt();
                System.out.print("Nouvelle date de l'événement : ");
                String updatedEventDate = scanner.next();
                System.out.print("Nouvelle température : ");
                double updatedTemperature = scanner.nextDouble();

                cityHistory.updateHistoricalRecord(historicalDataIdToUpdate, updatedEventDate, updatedTemperature);
                break;

            case 8:
                System.out.print("ID de l'enregistrement historique à supprimer : ");
                int historicalDataIdToDelete = scanner.nextInt();
                cityHistory.deleteHistoricalRecord(historicalDataIdToDelete);
                break;

            case 9:
                System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                break;

            default:
                System.out.println("Choix invalide. Veuillez saisir un numéro valide.");
                break;
        }
    }
}
