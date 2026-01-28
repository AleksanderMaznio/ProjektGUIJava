import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Historia extends JFrame {
    private JPanel historiaPanel;
    private JButton powrótButton;
    private JTable Tabela;
    private JPanel Przyciski;
    private JPanel Tablica;
    private JPanel Gora;
    private JScrollPane Scroll;
    private int idUsera;
    private DefaultTableModel model;

    public Historia(int idUsera) {
        this.idUsera = idUsera;
        setVisible(true);
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
        Scroll.setOpaque(false);
        Scroll.getViewport().setOpaque(false);
        Scroll.setBorder(BorderFactory.createEmptyBorder());

        Tabela.setOpaque(false);
        Tabela.setBackground(new Color(0, 0, 0, 0));
        Tabela.setGridColor(new Color(255, 255, 255, 40));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setOpaque(false);
                c.setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };


        Tabela.setDefaultRenderer(Object.class, renderer);
        Tabela.getTableHeader().setBackground(new Color(71, 1, 66));
        Tabela.getTableHeader().setForeground(Color.WHITE);
        Tabela.getTableHeader().setOpaque(false);
        gradient.add(historiaPanel, BorderLayout.CENTER);
        setContentPane(gradient);
        powrótButton.addActionListener(e -> {
            Menu menu=new Menu(idUsera);
            dispose();
        });

        wczytajDane();

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
                model.addRow(new Object[]{rs.getString("data_losowania"), rs.getString("nazwa")});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd bazy: " + e.getMessage());
        }
    }
}