import javax.swing.*;
import java.awt.*;

public class Gradient extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        setVisible(true);
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        int szerokosc=getWidth();
        int wysokosc=getHeight();

        Color color1=new Color(97, 26, 104);
        Color color2=new Color(236, 15, 167);

        GradientPaint gp=new GradientPaint(0,0,color1,0,wysokosc,color2);

        g2d.setPaint(gp);
        g2d.fillRect(0,0,szerokosc,wysokosc);
    }
}
