package ma.projet.connexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

    private static Connection connection;

    static {
        try {
            FileInputStream stream = new FileInputStream("base.properties");
            Properties p = new Properties();
            p.load(stream);
            String url = p.getProperty("jdbc.url");
            String login = p.getProperty("jdbc.username");
            String password = p.getProperty("jdbc.password");
            String driver = p.getProperty("jdbc.driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            System.out.println("Erreur lors de la connexion : " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}