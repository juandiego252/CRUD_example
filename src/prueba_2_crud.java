import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class prueba_2_crud {
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField edadTextField;
    private JTextField ciudadTextField;
    private JTextField ciTextField;
    private JTextField contraTextField;
    private JPanel root;
    private JButton crearButton;
    private JButton consultarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;

    static final String DB_URL = "jdbc:mysql://localhost/POOJAVA";
    static final String USER = "root";
    static final String PASS = "2002";
    public prueba_2_crud() {
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                String nombre = nombreTextField.getText();
                int edad = Integer.parseInt(edadTextField.getText());
                String ciudad = ciudadTextField.getText();
                int cedula = Integer.parseInt(ciTextField.getText());
                String contrasena = contraTextField.getText();

                try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);)
                {
                    String SQL_QUERY = "INSERT INTO estudiantes (nombre,edad,ciudad,cedula,contrasena) VALUES (?,?,?,?,?)";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)){
                        pstmt.setString(1,nombre);
                        pstmt.setInt(2,edad);
                        pstmt.setString(3,ciudad);
                        pstmt.setInt(4,cedula);
                        pstmt.setString(5,contrasena);

                        int filasInsertadas = pstmt.executeUpdate();
                        if (filasInsertadas > 0){
                            JOptionPane.showMessageDialog(root,"Estudiante registrado con exito.");
                        } else {
                            JOptionPane.showMessageDialog(root,"Error al registrar al estudiante.");
                        }
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
                    String SQL_Query_select = "SELECT * FROM estudiantes WHERE id_estudiante = ?";
                    try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                        pstm.setInt(1,id);
                        try (ResultSet rs = pstm.executeQuery()){
                            if (rs.next()){
                                String nombre = rs.getString("Nombre");
                                int edad = rs.getInt("edad");
                                String ciudad = rs.getString("ciudad");
                                int cedula = rs.getInt("cedula");
                                String contrasena = rs.getString("contrasena");
                                nombreTextField.setText(nombre);
                                edadTextField.setText(String.valueOf(edad));
                                ciudadTextField.setText(ciudad);
                                ciTextField.setText(String.valueOf(cedula));
                                contraTextField.setText(contrasena);

                                JOptionPane.showMessageDialog(root,"Estudiante econtrado!");
                            } else {
                                JOptionPane.showMessageDialog(root,"No se econtro el estudiante");
                                nombreTextField.setText("");
                                edadTextField.setText("");
                                ciudadTextField.setText("");
                                ciTextField.setText("");
                                contraTextField.setText("");
                            }
                        }
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                String nombre = nombreTextField.getText();
                int edad = Integer.parseInt(edadTextField.getText());
                String ciudad = ciudadTextField.getText();
                int cedula = Integer.parseInt(ciTextField.getText());
                String contrasena = contraTextField.getText();

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
                    String SQL_QUERY_UPDATE = "UPDATE estudiantes SET nombre=?,edad=?,ciudad=?,cedula=?,contrasena=? WHERE id_estudiante=?";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_UPDATE)){
                        pstmt.setString(1,nombre);
                        pstmt.setInt(2,edad);
                        pstmt.setString(3,ciudad);
                        pstmt.setInt(4,cedula);
                        pstmt.setString(5,contrasena);
                        pstmt.setInt(6,id);

                        int filasActualizadas = pstmt.executeUpdate();
                        if (filasActualizadas>0){
                            JOptionPane.showMessageDialog(root,"Estudiante Actualizado !");
                            nombreTextField.setText("");
                            edadTextField.setText("");
                            ciudadTextField.setText("");
                            ciTextField.setText("");
                            contraTextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(root,"Error al actualizar !");
                            nombreTextField.setText("");
                            edadTextField.setText("");
                            ciudadTextField.setText("");
                            ciTextField.setText("");
                            contraTextField.setText("");
                        }
                    }
                }catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());

                try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)){
                    String SQL_QUERY_DELETE = "DELETE FROM estudiantes WHERE id_estudiante=?";
                    try(PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY_DELETE)){
                        pstmt.setInt(1,id);
                        int filasEliminadas = pstmt.executeUpdate();
                        if (filasEliminadas > 0){
                            JOptionPane.showMessageDialog(root,"Estudiante eliminado con exito !");
                            idTextField.setText("");
                            nombreTextField.setText("");
                            edadTextField.setText("");
                            ciudadTextField.setText("");
                            ciTextField.setText("");
                            contraTextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(root,"No se encontro el estudiante !");
                        }
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("prueba_2_crud");
        frame.setContentPane(new prueba_2_crud().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
