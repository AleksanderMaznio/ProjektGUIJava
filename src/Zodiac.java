import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zodiac extends JFrame {

    private JPanel Zodiac;
    private JTextField Data;
    private JButton button1;
    private JButton button2;
    private JPanel Opcje;
    private JPanel Znak;
    private JLabel Obrazek;
    private JPanel Opis;
    private JLabel Nazwa;

    private ImageIcon[] ZnakiZodiac=new ImageIcon[12];
    private int idUsera;
    public Zodiac(int idUsera) {
        this.idUsera=idUsera;
        WczytajZnaki();

        setTitle("Zodiac");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);


        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());


        gradient.add(Zodiac, BorderLayout.CENTER);


        setContentPane(gradient);


        setVisible(true);

        Opis.setOpaque(false);
        Zodiac.setOpaque(false);
        Data.setOpaque(true);
        button1.setOpaque(false);
        button2.setOpaque(false);
        Opcje.setOpaque(false);
        Znak.setOpaque(false);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu=new Menu(idUsera);
                dispose();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Pdata = Data.getText();   // format: dd.mm.yyyy

                    String[] Czesci = Pdata.split("[-./]");
                    int day = Integer.parseInt(Czesci[0]);
                    int month = Integer.parseInt(Czesci[1]);

                    int znak = getZodiacSign(day, month);

                    if (znak == -1) {
                        JOptionPane.showMessageDialog(null,"Zła data");
                        return;
                    }

                    Obrazek.setIcon(ZnakiZodiac[znak]);
                    Nazwa.setText(nazwyZnakow[znak]);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Zła data");
                }
            }
        });
    }

    private final String[] nazwyZnakow = {
            "aries", "taurus", "gemini", "cancer", "leo", "virgo",
            "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"
    };
    private void WczytajZnaki(){
        for (int i=0;i<12;i++){
            String path=String.format("Zodiac/%s.png",nazwyZnakow[i]);
            ZnakiZodiac[i]=new ImageIcon(getClass().getResource(path));
        }
    }
    private int getZodiacSign(int day, int month) {
        switch (month) {
            case 1:  return (day <= 19) ? 9 : 10;   // Koziorożec / Wodnik
            case 2:  return (day <= 18) ? 10 : 11;  // Wodnik / Ryby
            case 3:  return (day <= 20) ? 11 : 0;   // Ryby / Baran
            case 4:  return (day <= 19) ? 0 : 1;    // Baran / Byk
            case 5:  return (day <= 20) ? 1 : 2;    // Byk / Bliźnięta
            case 6:  return (day <= 20) ? 2 : 3;    // Bliźnięta / Rak
            case 7:  return (day <= 22) ? 3 : 4;    // Rak / Lew
            case 8:  return (day <= 22) ? 4 : 5;    // Lew / Panna
            case 9:  return (day <= 22) ? 5 : 6;    // Panna / Waga
            case 10: return (day <= 22) ? 6 : 7;    // Waga / Skorpion
            case 11: return (day <= 21) ? 7 : 8;    // Skorpion / Strzelec
            case 12: return (day <= 21) ? 8 : 9;    // Strzelec / Koziorożec
        }
        return -1;
    }

}
