import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; // Import dla JDBC
import java.util.Random;

public class Test extends JFrame {
    private JPanel TestPanel;
    private JTextArea TestOpis;
    private JLabel TestObrazek;
    private JButton TestLosuj;

    public Test(){
        // Konfiguracja UI
        setContentPane(TestPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,750); // Zwiększyłem wysokość, aby zmieścił się opis i obrazek
        setLocationRelativeTo(null);

        // Ustawienia JTextArea dla lepszej czytelności
        TestOpis.setLineWrap(true);
        TestOpis.setWrapStyleWord(true);
        TestOpis.setEditable(false);

        TestLosuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random losuj = new Random();
                int id = losuj.nextInt(22); // Generuje id od 0 do 21

                // Dane do połączenia z XAMPP
                String url = "jdbc:mysql://localhost:3306/tarot";
                String user = "root";
                String password = "";

                // Zapytanie SQL z parametrem ?
                String query = "SELECT opis, obrazek_sciezka FROM karty_tarota WHERE id = ?";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setInt(1, id); // Wstawiamy wylosowane id w miejsce ?
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // 1. Pobieramy i ustawiamy opis
                        String opis = rs.getString("opis");
                        TestOpis.setText(opis);

                        // 2. Pobieramy ścieżkę i ustawiamy obrazek
                        String sciezka = rs.getString("obrazek_sciezka");
                        ImageIcon icon = new ImageIcon(getClass().getResource(sciezka));
                        TestObrazek.setIcon(icon);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Błąd SQL: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Błąd: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}