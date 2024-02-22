import java.sql.*;

public class City {
    private int cityId;
    private String cityName;
    private double currentTemperature;
    private double currentHumidity;
    private double currentWindSpeed;
    private Connection connection;
    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }
    // Constructeur
    public City(Connection connection) {
        this.connection = connection;
    }

    public void createCityRecord(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed) {
        try {
            String query = "INSERT INTO city (cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, cityId);
                statement.setString(2, cityName);
                statement.setDouble(3, currentTemperature);
                statement.setDouble(4, currentHumidity);
                statement.setDouble(5, currentWindSpeed);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Nouvel enregistrement créé avec succès !");
                } else {
                    System.out.println("Erreur lors de la création de l'enregistrement.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'enregistrement : " + e.getMessage());
        }
    }

    public void readCityRecord(int cityId) {
        try {
            String query = "SELECT * FROM city WHERE cityId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, cityId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("cityId");
                        String name = resultSet.getString("cityName");
                        double temperature = resultSet.getDouble("currentTemperature");
                        double humidity = resultSet.getDouble("currentHumidity");
                        double windSpeed = resultSet.getDouble("currentWindSpeed");

                        System.out.println(Menu.ANSI_GREEN+"======Information sur la ville======"+ Menu.ANSI_RESET);
                        System.out.println("ID : " + id);
                        System.out.println("Nom : " + name);
                        System.out.println("Température : " + temperature + " °C");
                        System.out.println("Humidité : " + humidity + "%");
                        System.out.println("Vitesse du vent : " + windSpeed + " km/h");
                        System.out.println(Menu.ANSI_GREEN+"=====+++++++++++++++++++++++++++===="+ Menu.ANSI_RESET);
                    } else {
                        System.out.println("Aucun enregistrement trouvé pour l'ID : " + cityId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la lecture de l'enregistrement : " + e.getMessage());
        }
    }

    public void updateCityRecord(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed) {
        try {
            String query = "UPDATE city SET cityName = ?, currentTemperature = ?, currentHumidity = ?, currentWindSpeed = ? WHERE cityId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, cityName);
                statement.setDouble(2, currentTemperature);
                statement.setDouble(3, currentHumidity);
                statement.setDouble(4, currentWindSpeed);
                statement.setInt(5, cityId);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Enregistrement mis à jour avec succès !");
                } else {
                    System.out.println("Aucun enregistrement trouvé pour l'ID : " + cityId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'enregistrement : " + e.getMessage());
        }
    }

    public void deleteCityRecord(int cityId) {
        try {
            String query = "DELETE FROM city WHERE cityId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, cityId);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Enregistrement supprimé avec succès !");
                } else {
                    System.out.println("Aucun enregistrement trouvé pour l'ID : " + cityId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'enregistrement : " + e.getMessage());
        }
    }
    public ResultSet readAllCities() throws SQLException {
        String query = "SELECT * FROM city";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
}
