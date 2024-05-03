package ma.projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.projet.beans.client;
import ma.projet.connexion.connexion;
import ma.projet.dao.IDao;

public class ClientService implements IDao {

    @Override
    public boolean create(client c) {
        String query = "INSERT INTO client (nom, prenom) VALUES (?, ?)";
        try (
                Connection conn = connexion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, c.getNom());
            preparedStatement.setString(2, c.getPrenom());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(client c) {
        String query = "DELETE FROM client WHERE id = ?";
        try (Connection conn = connexion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, c.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(client c) {
        String query = "UPDATE client SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection conn = connexion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, c.getNom());
            preparedStatement.setString(2, c.getPrenom());
            preparedStatement.setInt(3, c.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public client findById(int id) {
        String query = "SELECT * FROM client WHERE id = ?";
        try (Connection conn = connexion.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                return new client(id, nom, prenom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<client> findAll() {
        List<client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        try (Connection conn = connexion.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                clients.add(new client(id, nom, prenom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

}
