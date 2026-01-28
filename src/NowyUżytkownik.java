import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NowyUżytkownik extends JFrame{
    private JPanel UżytkownikForm;
    private JTextField PodaneImie;
    private JTextField PodaneNazwisko;
    private JTextField PodanyLogin;
    private JTextField PodaneHaslo;
    private JButton dodajUżytkownikaButton;
    private JButton anulujButton;
    private JPanel NowyUżytkownik;
    private JPanel guziki;

    public NowyUżytkownik(){
        setTitle("Tarot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());


        UżytkownikForm.setOpaque(false);
        NowyUżytkownik.setOpaque(false);
        guziki.setOpaque(false);

        gradient.add(UżytkownikForm, BorderLayout.CENTER);

        setContentPane(gradient);
        setVisible(true);
        dodajUżytkownikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String imie = PodaneImie.getText().trim();
                String nazwisko = PodaneNazwisko.getText().trim();
                String login = PodanyLogin.getText().trim();
                String haslo = PodaneHaslo.getText().trim();


                if (imie.isEmpty() || nazwisko.isEmpty() || login.isEmpty() || haslo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione!", "Błąd walidacji", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. Jeśli dane są poprawne, przechodzimy do bazy
                String url = "jdbc:mysql://localhost:3306/tarot";
                String user = "root";
                String password = "";
                String dodanie = "INSERT INTO uzytkownicy (imie, nazwisko, login, haslo) VALUES (?, ?, ?, ?)";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement dodawanie = conn.prepareStatement(dodanie)) {

                    dodawanie.setString(1, imie);
                    dodawanie.setString(2, nazwisko);
                    dodawanie.setString(3, login);
                    dodawanie.setString(4, haslo);

                    int dodaneRzędy = dodawanie.executeUpdate();
                    if (dodaneRzędy > 0) {
                        JOptionPane.showMessageDialog(null, "Użytkownik dodany!");
                        new Logowanie().setVisible(true); // Upewnij się, że okno logowania ma setVisible
                        dispose();
                    }
                } catch (SQLException ex) {

                    if (ex.getErrorCode() == 1062) {
                        JOptionPane.showMessageDialog(null, "Ten login jest już zajęty!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("Błąd bazy: " + ex.getMessage());
                    }
                }
            }
        });
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logowanie logowanie=new Logowanie();
                dispose();
            }
        });
    }
}