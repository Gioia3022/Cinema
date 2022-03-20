package Cinema;

import java.sql.*;
import java.util.ArrayList;

public class Connect {
    String url;
    Connection conn = null;
    ResultSet rs = null;
    private ResultSetMetaData rsMeta;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    public Connect(String url) throws SQLException {
        this.url=url;
    }

    public void connectData() throws SQLException {
        conn = DriverManager.getConnection(url);
    }
    public void closeCourseDataConnection() throws SQLException {
        conn.close();
    }
    public void createStatement() throws SQLException {
        this.stmt = conn.createStatement();
    }

    //Gives all student names from database
    public ArrayList<String> SQLQueryFilm() throws SQLException {
        ArrayList<String> admin = new ArrayList<>();
        // language=<SQL>
        String sql = "Select FilmName from film";

        rs = stmt.executeQuery(sql);

        while (rs != null && rs.next()) {
            String name = rs.getString(1);
            admin.add(name);
        }
        return admin;
    }
}
