import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public Estudiante() {
        consultarBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton funciona");
                conectar();
            }
        });
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
