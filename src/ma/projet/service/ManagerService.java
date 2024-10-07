package ma.projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;

public class ManagerService {
    private final Connection connection = Connexion.getConnection();

  
    public boolean exists(Manager manager) { 
        try {
            String sql = "SELECT COUNT(*) FROM personne WHERE nom = ? AND salaire = ? AND type = 'manager'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du manager : " + e.getMessage());
        }
        return false; 
    }

   
    public boolean create(Manager manager) {
        if (exists(manager)) {
            System.out.println("Manager existe déjà, insertion évitée.");
            return false; 
        }

        try {
            String sql = "INSERT INTO personne (nom, salaire, type, manager_id) VALUES (?, ?, 'manager', ?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.setObject(3, manager.getManagerId(), java.sql.Types.INTEGER);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    manager.setId(rs.getInt(1)); 
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du manager : " + e.getMessage());
            return false;
        }
        return false;
    }


    public boolean update(Manager manager) {
        try {
            String sql = "UPDATE personne SET nom = ?, salaire = ?, manager_id = ? WHERE id = ? AND type = 'manager'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.setObject(3, manager.getManagerId(), java.sql.Types.INTEGER);
            ps.setInt(4, manager.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du manager : " + e.getMessage());
            return false;
        }
    }

    public List<Manager> getAll() {
        List<Manager> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM personne WHERE type = 'manager'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manager mgr = new Manager(rs.getString("nom"), rs.getDouble("salaire"), rs.getInt("manager_id"));
                mgr.setId(rs.getInt("id"));
                list.add(mgr);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de tous les managers : " + e.getMessage());
        }
        return list;
    }
}