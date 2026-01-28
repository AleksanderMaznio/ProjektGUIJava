import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel Menu;
    private JButton historiaPrzepowiedniButton;
    private JButton kartaDniaButton;
    private JButton znakZZodiakuButton;
    private JPanel Tekst;
    private JButton wyjdźButton;
    private int idUsera;
    public Menu(int idUsera){
        this.idUsera=idUsera;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());

        gradient.add(Menu, BorderLayout.CENTER);
        setContentPane(gradient);
        setVisible(true);
        Menu.setOpaque(false);
        Tekst.setOpaque(false);
        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        kartaDniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KartaDnia kartaDnia=new KartaDnia(idUsera);
                dispose();
            }
        });
        znakZZodiakuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zodiac zodiac=new Zodiac(idUsera);
                dispose();

            }
        });
        historiaPrzepowiedniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Historia historia=new Historia(idUsera);
                    dispose();
            }
        });
    }
}
