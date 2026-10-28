package util;
// Jo bhi aapka actual package name hai

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection conn = null;

        // config.properties file ko root se load karna
        try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("../../config.properties")) {
            if (is == null) {
                System.out.println("Error: config.properties file nahi mili!");
                return null;
            }
            props.load(is);

            // File se properties read karna
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password"); // Ab password variable me aayega

            // Connection banana
            Class.forName("com.mysql.cj.jdbc.Driver"); // Agar driver load karna ho
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
