import java.sql.*;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private String eventDate;
    private double temperature;
    private Connection connection;

    // Constructeur
    public CityHistory(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour créer un nouvel enregistrement historique dans la base de données
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

    // Méthode pour lire l'historique complet d'une ville à partir de la base de données
    public void readCityHistory(int cityId) {
        try {
            String sql = "SELECT * FROM CityHistory WHERE cityId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cityId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        historicalDataId = resultSet.getInt("historicalDataId");
                        this.cityId = resultSet.getInt("cityId");
                        eventDate = resultSet.getString("eventDate");
                        temperature = resultSet.getDouble("temperature");

                        System.out.println("Historical Data ID: " + historicalDataId + ", City ID: " + this.cityId +
                                ", Event Date: " + eventDate + ", Temperature: " + temperature);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la lecture de l'historique : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour les détails d'un enregistrement historique existant dans la base de données
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

    // Méthode pour supprimer un enregistrement historique spécifique de la base de données
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
    public ResultSet readAllHistoryForCity(int cityId) throws SQLException {
        String query = "SELECT * FROM cityhistory WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cityId);
        return statement.executeQuery();
    }
}
