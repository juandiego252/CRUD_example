import java.sql.*;

public class crud {
     static final String DB_URL = "jdbc:mysql://localhost/POOJAVA";
     static final String USER = "root";
     static final String PASS = "2002";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Operacion Select
            String Query_sql_select = "SELECT * FROM estudiantes";
            try (PreparedStatement pstmt = conn.prepareStatement(Query_sql_select)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("id_estudiante: " + rs.getInt("id_estudiante"));
                        System.out.println("nombre: " + rs.getString("nombre"));
                        System.out.println("edad: " + rs.getInt("edad"));
                        System.out.println("ciudad: " + rs.getString("ciudad"));
                        System.out.println("cedula: " + rs.getInt("cedula"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}