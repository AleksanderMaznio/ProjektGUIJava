import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Logowanie extends JFrame{
    private JPanel Login;
    private JTextField TextLogin;
    private JPasswordField TextHaslo;
    private JButton zalogujButton;
    private JButton wyjdźButton;
    private JButton utwórzUżytkownikaButton;
    private JPanel LoginForm;
    private JPanel Buttons;

    public Logowanie(){
        setTitle("Tarot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        Gradient gradient=new Gradient();
        gradient.setLayout(new BorderLayout());
        gradient.add(Login,BorderLayout.CENTER);
        setContentPane(gradient);
        setVisible(true);
        Login.setOpaque(false);
        LoginForm.setOpaque(false);
        Buttons.setOpaque(false);
        utwórzUżytkownikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NowyUżytkownik nowyUżytkownik=new NowyUżytkownik();
                dispose();
            }
        });
        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/tarot";
                String user = "root";
                String password = "";

                String inputLogin=TextLogin.getText();
                String inputHaslo=TextHaslo.getText();

                String sql="SELECT * FROM uzytkownicy WHERE login = ? AND haslo = ?";
                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, inputLogin);
                    pstmt.setString(2, inputHaslo);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                                int idUsera=rs.getInt("id");
                        JOptionPane.showMessageDialog(null, "Logowanie pomyślne! Witaj " + rs.getString("imie"));
                     Menu menu=new Menu(idUsera);
                      dispose();


                    } else {
                        // Brak dopasowania
                        JOptionPane.showMessageDialog(null, "Błędny login lub hasło.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd połączenia z bazą: " + ex.getMessage());
                }
            }
        });

}
}
