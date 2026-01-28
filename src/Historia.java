import javax.swing.*;
import java.awt.*;

public class Historia extends JFrame {
    private JPanel historiaPanel;
    private JButton button1;
    private JButton button2;
    private JTable table1;
    private JPanel Przyciski;
    private JPanel Tablica;
    private JPanel Label;
    private int idUsera;

    public Historia(int idUsera){
        this.idUsera = idUsera;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());

        // KLUCZOWE POPRAWKI:
        historiaPanel.setOpaque(false);
        Przyciski.setOpaque(false);
        Tablica.setOpaque(false);

        // Jeśli tabela jest w JScrollPane (standard w IntelliJ Designer):
        // table1.setOpaque(false);
        // ((JViewport)table1.getParent()).setOpaque(false);

        gradient.add(historiaPanel, BorderLayout.CENTER);
        setContentPane(gradient);
        setVisible(true);

    }
}
