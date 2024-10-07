package ma.projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;

public class DeveloppeurService {
    private final Connection connection = Connexion.getConnection();

    private boolean exists(Developpeur developpeur) {
        try {
            String sql = "SELECT COUNT(*) FROM personne WHERE nom = ? AND salaire = ? AND type = 'developpeur'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, developpeur.getNom());
            ps.setDouble(2, developpeur.getSalaire());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence du développeur : " + e.getMessage());
        }
        return false;
    }

    public boolean create(Developpeur developpeur) {
        if (exists(developpeur)) {
            System.out.println("Développeur existe déjà, insertion évitée.");
            return false;
        }
        
        try {
            String sql = "INSERT INTO personne (nom, salaire, type, manager_id) VALUES (?, ?, 'developpeur', ?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, developpeur.getNom());
            ps.setDouble(2, developpeur.getSalaire());
            ps.setObject(3, developpeur.getManagerId(), java.sql.Types.INTEGER);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    developpeur.setId(rs.getInt(1)); 
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du développeur : " + e.getMessage());
        }
        return false;
    }

    public boolean update(Developpeur developpeur) {
        try {
            String sql = "UPDATE personne SET nom = ?, salaire = ?, manager_id = ? WHERE id = ? AND type = 'developpeur'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, developpeur.getNom());
            ps.setDouble(2, developpeur.getSalaire());
            ps.setObject(3, developpeur.getManagerId(), java.sql.Types.INTEGER);
            ps.setInt(4, developpeur.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du développeur : " + e.getMessage());
            return false;
        }
    }

    public List<Developpeur> getAll() {
        List<Developpeur> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM personne WHERE type = 'developpeur'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Developpeur dev = new Developpeur(rs.getString("nom"), rs.getDouble("salaire"));
                dev.setId(rs.getInt("id"));
                dev.setManagerId(rs.getInt("manager_id"));
                list.add(dev);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de tous les développeurs : " + e.getMessage());
        }
        return list;
    }
}