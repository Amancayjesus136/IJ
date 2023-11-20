import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Estudiante extends JFrame{
    private JPanel panel;
    private JTextField idText;
    private JTextField nombreTxt;
    private JTextField apellidoText;
    private JTextField telefonoTxt;
    private JTextField carreraTxt;
    private JButton ingresarBtn;
    private JButton consultarBtn;
    private JList lista;
    private JTextField edadTxt;
    Connection con;
    PreparedStatement ps;
    Statement st;
    ResultSet r;
    DefaultListModel mod = new DefaultListModel();

    public Estudiante() {
        consultarBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void listar() throws SQLException {
        conectar();
        lista.setModel(mod);
        st = con.createStatement();
        r = st.executeQuery("SELECT idestudiante, nombre, apellido, edad, telefono, carrera from estudiante");
        mod.removeAllElements();
        while (r.next()){
            mod.addElement(r.getString(1)+" "+r.getString(2)+" "+r.getString(3));
        }
    }

    public void insertar() throws SQLException {
        conectar();
        ps = con.prepareStatement("INSERT INTO estudiante VALUES (?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(idText.getText()));
        ps.setString(2,nombreTxt.getText());
        ps.setString(3,apellidoText.getText());
        ps.setInt(4, Integer.parseInt(edadTxt.getText()));
        ps.setString(5,telefonoTxt.getText());
        ps.setString(6,carreraTxt.getText());
        if (ps.executeUpdate()>0){
            lista.setModel(mod);
            mod.removeAllElements();
            mod.addElement("! Registro exitosa !");

            idText.setText("");
            nombreTxt.setText("");
            apellidoText.setText("");
            edadTxt.setText("");
            telefonoTxt.setText("");
            carreraTxt.setText("");
        }
    }
    public static void main(String[] args) {
        Estudiante f = new Estudiante();
        f.setContentPane(new Estudiante().panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.pack();
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/learning", "root", "");
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("error: "+ e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
