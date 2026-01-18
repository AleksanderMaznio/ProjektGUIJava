import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class KartaDnia extends JFrame {

    private JPanel KartaD;
    private JButton Karta;
    private JButton Powrot;
    private JPanel Guziki;
    private JLabel Tarot;
    private JTextArea OpisKarty;


    // TABLICA NA WSZYSTKIE KARTY
    private ImageIcon[] tarotCards = new ImageIcon[22];
    private String[] tarotOpisy = {
            "The Fool — symbol nowego początku, odwagi i wolności. Zachęca, by zrobić pierwszy krok, zaufać intuicji i otworzyć się na nieznane, nawet jeśli droga wydaje się niepewna",
            "The Magician — karta mocy sprawczej. Oznacza koncentrację, kreatywność i świadome kierowanie energią. To moment, w którym możesz urzeczywistnić swoje pomysły i działać z pełnią pewności siebie.",
            "The High Priestess — głos intuicji, tajemnicy i wewnętrznej wiedzy. Zachęca, aby zaufać przeczuciom, zwolnić i zajrzeć w głąb siebie zamiast szukać odpowiedzi na zewnątrz.",
            "The Empress — energia obfitości, ciepła i troski. Przynosi wzrost, kreatywność i harmonię. Symbol relacji, miłości oraz dbałości o własne potrzeby i komfort.",
            "The Emperor — symbol stabilności, autorytetu i struktury. Karta mówi o potrzebie uporządkowania spraw, przejęcia kontroli i podejmowania decyzji z pewnością i odpowiedzialnością.",
            "The Hierophant — mądrość tradycji, duchowość i nauka. To czas, by szukać przewodnictwa, zwrócić się ku wartościom lub nauczyć się czegoś, co umocni Twoją drogę życiową.",
            "The Lovers — wybór, relacje i harmonia. To karta miłości, ale także konieczności decyzji zgodnej z sercem. Zachęca do szczerości w związkach i wewnętrznej spójności.",
            "The Chariot — determinacja, ruch i zwycięstwo. Pokazuje, że dzięki sile woli możesz pokonać przeszkody. To karta działania, dynamiki i przełomowych decyzji.",
            "Strength — odwaga, wytrwałość i łagodna kontrola. Mówi o panowaniu nad emocjami i harmonijnym podejściu do trudności. Prawdziwa siła rodzi się z empatii, nie agresji.",
            "The Hermit — czas refleksji i poszukiwania odpowiedzi w samotności. Zachęca, by zatrzymać się, spojrzeć na życie z dystansu i zrozumieć swoje prawdziwe potrzeby.",
            "Wheel of Fortune — zmiana cyklu, los i przeznaczenie. Rzeczy zaczynają się poruszać, a nowe możliwości stoją otworem. To znak, że świat chce Cię popchnąć naprzód.",
            "Justice — równowaga, uczciwość i prawda. Karta mówi o konsekwencjach działań i konieczności podejmowania decyzji w sposób przemyślany i sprawiedliwy.",
            "The Hanged Man — czas zatrzymania, zmiany perspektywy i poświęcenia. Aby ruszyć dalej, trzeba spojrzeć na sprawy inaczej lub odpuścić coś, co blokuje rozwój.",
            "Death — transformacja i głęboka zmiana. Nie oznacza końca, lecz odrodzenie. Pozbywasz się starego, aby zrobić przestrzeń na coś nowego i bardziej zgodnego z Tobą.",
            "Temperence — harmonia, spokój i równowaga. Zachęca do cierpliwości i łączenia przeciwieństw. To czas, by działać łagodnie i bez pośpiechu.",
            "The Devil — uwikłanie, pokusa, przywiązania. Wskazuje na sytuacje, które ograniczają wolność. Zachęca, by wyrwać się z negatywnych schematów i zacząć świadomie wybierać.",
            "The Tower — nagłe zmiany i rozpad tego, co było niestabilne. Choć bywa trudna, przynosi uwolnienie i potrzebny reset, prowadzący do lepszych fundamentów.",
            "The Star — nadzieja, inspiracja i uzdrowienie. To karta lekkości i wiary, że wszechświat sprzyja Twojej drodze. Przynosi jasność, spokój i optymizm.",
            "The Moon — iluzje, emocje i niejasność. Zachęca, by ufać intuicji i nie dać się zwieść lękom. Nie wszystko jest takie, jakim się wydaje na pierwszy rzut oka.",
            "The Sun — radość, czystość i życiowa energia. Przynosi sukces, dobre wiadomości i poczucie szczęścia. To jedna z najbardziej pozytywnych kart w talii.",
            "Judgement — przebudzenie i głęboka analiza. Czas spojrzeć prawdzie w oczy, wyciągnąć wnioski i podjąć nowy, dojrzalszy kierunek.",
            "The World — spełnienie, harmonia i zakończenie ważnego etapu. To karta sukcesu, integracji i domknięcia cyklu, która otwiera drogę do nowego rozdziału."
    };
    public KartaDnia() {

        // Wczytanie wszystkich obrazków
        loadTarotCards();

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
                Random rand = new Random();
                int index = rand.nextInt(22);
                Tarot.setIcon(tarotCards[index]);
                OpisKarty.setText(tarotOpisy[index]);
            }
        });

        Powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });
    }


    private void loadTarotCards() {

        String[] names = {
                "TheFool", "TheMagician", "TheHighPriestess", "TheEmpress",
                "TheEmperor", "TheHierophant", "TheLovers", "TheChariot",
                "Strength", "TheHermit", "WheelOfFortune", "Justice",
                "TheHangedMan", "Death", "Temperance", "TheDevil",
                "TheTower", "TheStar", "TheMoon", "TheSun",
                "Judgement", "TheWorld"
        };

        for (int i = 0; i < 22; i++) {
            String path = String.format("DuzeArc/%02d-%s.png", i, names[i]);
            tarotCards[i] = new ImageIcon(getClass().getResource(path));
        }
    }
}
