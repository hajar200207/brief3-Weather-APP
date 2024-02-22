import java.sql.Connection;
import java.sql.ResultSet;
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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public void displayMenu() {
        System.out.println(ANSI_BLUE+"################################################" +ANSI_RESET);
        System.out.println(ANSI_PURPLE+"           *           *             *          ");
        System.out.println("                     Menu                       ");
        System.out.println("           *           *             *          "+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"################################################");
        System.out.println("#             Welcome to City Manager          #");
        System.out.println("################################################"+ANSI_RESET);
        System.out.println("    1. Créer un nouvel enregistrement ville     ");
        System.out.println("    2. search par un id ville                   ");
        System.out.println("    3. Mettre à jour un enregistrement ville    ");
        System.out.println("    4. Supprimer un enregistrement ville        ");
        System.out.println("    5. afficher tous  ville                     ");
        System.out.println(ANSI_YELLOW+"################################################");
        System.out.println("#            Welcome to history city           #");
        System.out.println("################################################"+ANSI_RESET);
        System.out.println("    6. Créer un nouvel enregistrement historique");
        System.out.println("    7. search par id un l'historique d'une ville");
        System.out.println("    8. Mettre à jour un historique ville        ");
        System.out.println("    9. Supprimer un enregistrement historique   ");
        System.out.println("    10. afficher tous  HISTORIQUE               ");
        System.out.println(ANSI_YELLOW+"################################################");
        System.out.println("#           Welcome to exit programme          #");
        System.out.println("################################################"+ANSI_RESET);
        System.out.println("                    0. Quitter                  ");
        System.out.println(ANSI_BLUE+"################################################" +ANSI_RESET);
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
                try {
                    ResultSet resultSet = city.readAllCities();
                    while (resultSet.next()) {
                        int CityId = resultSet.getInt("cityId");
                        String CityName = resultSet.getString("cityName");
                        int CurrentTemperature = resultSet.getInt("currentTemperature");
                        int CurrentHumidity = resultSet.getInt("currentHumidity");
                        int currentwindSpeed = resultSet.getInt("currentWindSpeed");
                        System.out.println(Menu.ANSI_GREEN+"======Information sur les ville======"+ Menu.ANSI_RESET);
                        System.out.println("City ID: " + CityId);
                        System.out.println("City Name: " + CityName);
                        System.out.println("Temperature: " + CurrentTemperature);
                        System.out.println("Humidity: " + CurrentHumidity);
                        System.out.println("Wind Speed: " + currentwindSpeed);
                        System.out.println();
                    } System.out.println(Menu.ANSI_GREEN+"=====+++++++++++++++++++++++++++===="+ Menu.ANSI_RESET);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
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

            case 7:
                System.out.print("ID de la ville dont vous souhaitez lire l'historique : ");
                int cityIdForHistoryRead = scanner.nextInt();
                cityHistory.readCityHistory(cityIdForHistoryRead);
                break;

            case 8:
                System.out.print("ID de l'enregistrement historique à mettre à jour : ");
                int historicalDataIdToUpdate = scanner.nextInt();
                System.out.print("Nouvelle date de l'événement : ");
                String updatedEventDate = scanner.next();
                System.out.print("Nouvelle température : ");
                double updatedTemperature = scanner.nextDouble();

                cityHistory.updateHistoricalRecord(historicalDataIdToUpdate, updatedEventDate, updatedTemperature);
                break;

            case 9:
                System.out.print("ID de l'enregistrement historique à supprimer : ");
                int historicalDataIdToDelete = scanner.nextInt();
                cityHistory.deleteHistoricalRecord(historicalDataIdToDelete);
                break;
            case 10:
                try {
                    ResultSet historiesResultSet = cityHistory.readAllHistories();
                    while (historiesResultSet.next()) {
                        int HistoricalDataId = historiesResultSet.getInt("historicalDataId");
                        String CityName = historiesResultSet.getString("cityName");
                        String EventDate = historiesResultSet.getString("eventDate");
                        int Temperature = historiesResultSet.getInt("temperature");
                        System.out.println(Menu.ANSI_GREEN+"======Information sur la history======"+ Menu.ANSI_RESET);
                        System.out.println("Historical Data ID: " + HistoricalDataId);
                        System.out.println("City Name: " + CityName);
                        System.out.println("Event Date: " + EventDate);
                        System.out.println("Temperature: " + Temperature);
                        System.out.println(Menu.ANSI_CYAN+"======+++++++++++++++++++++++++++====="+ Menu.ANSI_RESET);
                        System.out.println();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case 0:
                System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                break;

            default:
                System.out.println("Choix invalide. Veuillez saisir un numéro valide.");
                break;
        }
    }
}
