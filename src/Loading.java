import javax.swing.*;
import java.awt.*;


public class Loading extends JFrame {
    private JPanel Welcome;
    private JLabel Tekst;
    private JProgressBar progressBar1;



    public Loading() {
        setTitle("Tarot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);

        Gradient gradient = new Gradient();
        gradient.setLayout(new BorderLayout());

        gradient.add(Welcome, BorderLayout.CENTER);

        Welcome.setOpaque(false);
        Tekst.setOpaque(false);

        progressBar1.setOpaque(true);




        setContentPane(gradient);

        setVisible(true);

        zbieranie();


    }
    private void zbieranie(){
        int counter = 0;
        while (counter<=100){

            progressBar1.setValue(counter);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            counter+=5;

        }
        dispose();
        Menu menu= new Menu();
    }
}
