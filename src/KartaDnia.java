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
            this.idUsera=idUsera;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());

        gradient.add(KartaD, BorderLayout.CENTER);
        setContentPane(gradient);
        setVisible(true);

        KartaD.setOpaque(false);
        Karta.setOpaque(false);
        Powrot.setOpaque(false);
        Guziki.setOpaque(false);
        OpisKarty.setLineWrap(true);
        OpisKarty.setWrapStyleWord(true);
        OpisKarty.setOpaque(false);
        OpisKarty.setEditable(false);
        OpisKarty.setFocusable(false);


        Karta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random losuj = new Random();
                int id = losuj.nextInt(22);


                String url = "jdbc:mysql://localhost:3306/tarot";
                String user = "root";
                String password = "";


                String query = "SELECT opis, obrazek_sciezka FROM karty_tarota WHERE id = ?";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {

                        String opis = rs.getString("opis");
                        OpisKarty.setText(opis);
                        OpisKarty.setOpaque(true);
                        OpisKarty.setBackground(Color.BLACK);

                        String sciezka = rs.getString("obrazek_sciezka");
                        ImageIcon icon = new ImageIcon(getClass().getResource(sciezka));
                        Tarot.setIcon(icon);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Błąd SQL: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Błąd: " + ex.getMessage());
                }
            }
        });

        Powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu(idUsera);
                dispose();
            }
        });
    }



}
