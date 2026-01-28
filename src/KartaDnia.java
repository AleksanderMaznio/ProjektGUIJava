import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class KartaDnia extends JFrame {

    private JPanel KartaD;
    private JButton Karta;
    private JButton Powrot;
    private JPanel Guziki;
    private JLabel Tarot;
    private JTextArea OpisKarty;

    private int idUsera;

    public KartaDnia(int idUsera) {
        this.idUsera = idUsera;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());

        // Ustawienia GUI
        KartaD.setOpaque(false);
        Karta.setOpaque(false);
        Powrot.setOpaque(false);
        Guziki.setOpaque(false);

        OpisKarty.setLineWrap(true);
        OpisKarty.setWrapStyleWord(true);
        OpisKarty.setOpaque(true);
        OpisKarty.setBackground(new Color(71, 1, 66, 200));

        OpisKarty.setEditable(false);
        OpisKarty.setFocusable(false);

        gradient.add(KartaD, BorderLayout.CENTER);
        setContentPane(gradient);
        setVisible(true);

        Karta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random losuj = new Random();

                int idWylosowanejKarty = losuj.nextInt(22) ;

                String url = "jdbc:mysql://localhost:3306/tarot";
                String user = "root";
                String password = "";

                String querySelect = "SELECT opis, obrazek_sciezka FROM karty_tarota WHERE id = ?";
                String queryInsert = "INSERT INTO historia_losowan (user_id, karta_id) VALUES (?, ?)";


                try (Connection conn = DriverManager.getConnection(url, user, password)) {


                    try (PreparedStatement pstmtSelect = conn.prepareStatement(querySelect)) {
                        pstmtSelect.setInt(1, idWylosowanejKarty);
                        ResultSet rs = pstmtSelect.executeQuery();

                        if (rs.next()) {
                            OpisKarty.setText(rs.getString("opis"));

                            String sciezka = rs.getString("obrazek_sciezka");

                            java.net.URL imgURL = getClass().getResource(sciezka);
                            if (imgURL != null) {
                                Tarot.setIcon(new ImageIcon(imgURL));
                            }
                        }
                    }


                    try (PreparedStatement pstmtInsert = conn.prepareStatement(queryInsert)) {
                        pstmtInsert.setInt(1, idUsera);
                        pstmtInsert.setInt(2, idWylosowanejKarty);
                        pstmtInsert.executeUpdate();
                    }


                    revalidate();
                    repaint();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Błąd SQL: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Błąd: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        Powrot.addActionListener(e -> {
            new Menu(idUsera).setVisible(true);
            dispose();
        });
    }
}