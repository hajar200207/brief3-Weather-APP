import java.sql.*;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private String eventDate;
    private double temperature;
    private Connection connection;

    public CityHistory(Connection connection) {
        this.connection = connection;
    }

    public void createHistoricalRecord(int historicalDataId, int cityId, String eventDate, double temperature) {
        try {
            String sql = "INSERT INTO CityHistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, historicalDataId);
                statement.setInt(2, cityId);
                statement.setString(3, eventDate);
                statement.setDouble(4, temperature);
                statement.executeUpdate();

                System.out.println("Nouvel enregistrement historique créé avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'enregistrement historique : " + e.getMessage());
        }
    }

    public void readCityHistory(int cityId) {
        try {
            String sql = "SELECT ch.historicalDataId, ch.cityId, ch.eventDate, ch.temperature, c.cityName " +
                    "FROM CityHistory ch " +
                    "JOIN City c ON ch.cityId = c.cityId " +
                    "WHERE ch.cityId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cityId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        historicalDataId = resultSet.getInt("historicalDataId");
                        this.cityId = resultSet.getInt("cityId");
                        String cityName = resultSet.getString("cityName");
                        eventDate = resultSet.getString("eventDate");
                        temperature = resultSet.getDouble("temperature");
                        System.out.println(Menu.ANSI_GREEN+"===============+++++++++++++++++++++++++++================="+ Menu.ANSI_RESET);
                        System.out.println("Historical Data ID: " + historicalDataId + ", City ID: " + this.cityId +
                                ", City Name: " + cityName + ", Event Date: " + eventDate + ", Temperature: " + temperature);
                        System.out.println(Menu.ANSI_GREEN+"===============+++++++++++++++++++++++++++================="+ Menu.ANSI_RESET);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la lecture de l'historique : " + e.getMessage());
        }
    }


    public void updateHistoricalRecord(int historicalDataId, String newEventDate, double newTemperature) {
        try {
            String sql = "UPDATE CityHistory SET eventDate = ?, temperature = ? WHERE historicalDataId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newEventDate);
                statement.setDouble(2, newTemperature);
                statement.setInt(3, historicalDataId);
                statement.executeUpdate();

                System.out.println("Enregistrement historique mis à jour avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'enregistrement historique : " + e.getMessage());
        }
    }

    public void deleteHistoricalRecord(int historicalDataId) {
        try {
            String sql = "DELETE FROM CityHistory WHERE historicalDataId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, historicalDataId);
                statement.executeUpdate();

                System.out.println("Enregistrement historique supprimé avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'enregistrement historique : " + e.getMessage());
        }
    }
    public ResultSet readAllHistories() throws SQLException {
        String sql = "SELECT ch.historicalDataId, ch.eventDate, ch.temperature, c.cityName " +
                "FROM cityhistory ch " +
                "JOIN city c ON ch.cityId = c.cityId";

        PreparedStatement statement = connection.prepareStatement(sql);

        return statement.executeQuery();
    }


}
