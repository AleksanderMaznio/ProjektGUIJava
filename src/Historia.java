import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*; // Dodany import dla SQL

public class Historia extends JFrame {
    private JPanel historiaPanel;
    private JButton powrótButton;
    private JTable Tabela;
    private JPanel Przyciski;
    private JPanel Tablica;
    private JPanel Gora;
    private int idUsera;
    private DefaultTableModel model;

    public Historia(int idUsera) {
        this.idUsera = idUsera;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());


        historiaPanel.setOpaque(false);
        Przyciski.setOpaque(false);
        Tablica.setOpaque(false);
        Gora.setOpaque(false);


        String[] kolumny = {"Data losowania", "Wylosowana Karta"};
        model = new DefaultTableModel(kolumny, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Tabela.setModel(model);


        Tabela.setOpaque(false);
        Tabela.setBackground(new Color(0, 0, 0, 100));




        gradient.add(historiaPanel, BorderLayout.CENTER);
        setContentPane(gradient);


        powrótButton.addActionListener(e -> {
            new Menu(idUsera).setVisible(true);
            dispose();
        });


        wczytajDane();

        setVisible(true);
    }

    private void wczytajDane() {
        String url = "jdbc:mysql://localhost:3306/tarot";
        String user = "root";
        String password = "";


        String sql = "SELECT h.data_losowania, k.nazwa FROM historia_losowan h " +
                "JOIN karty_tarota k ON h.karta_id = k.id " +
                "WHERE h.user_id = ? " +
                "ORDER BY h.data_losowania DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsera);
            ResultSet rs = pstmt.executeQuery();


            model.setRowCount(0);

            while (rs.next()) {
                String data = rs.getString("data_losowania");
                String nazwaKarty = rs.getString("nazwa");


                model.addRow(new Object[]{data, nazwaKarty});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd pobierania historii: " + e.getMessage());
            e.printStackTrace();
        }
    }
}