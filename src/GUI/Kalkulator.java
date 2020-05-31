package GUI;

import GUI.elementy.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Kalkulator {

    private JFrame frame;
    private JPanel panelGlowny;
    private JPanel panel;
    private JPanel panel2;
    private MyLabel labelONP;
    private MyLabel labelWynik;
    private MyTextField poleR;
    private String[] btnLabels;
    private MyButton[] btns;
    private ArrayList<String> rownanie;
    private Historia historia;

    private void odczytajHistorie(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("historia.bin"))) {
            this.historia = (Historia) inputStream.readObject();
        } catch (Exception e) {
            this.historia = new Historia();
            e.getMessage();
        }
    }

    public Kalkulator(){

        odczytajHistorie();

        this.frame = new JFrame();
        frame.setMinimumSize(new Dimension(402,650));
        frame.setPreferredSize(new Dimension(402,650));

        this.panelGlowny = new JPanel();

        this.panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        this.panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6,5));

        this.rownanie = new ArrayList<>();

        this.labelONP = new MyLabel("ONP");
        panel.add(labelONP);

        this.labelWynik = new MyLabel("Wynik");
        panel.add(labelWynik);

        this.poleR = new MyTextField("");
        panel.add(poleR);
        poleR.addActionListener(new MyActionListener(labelONP,labelWynik,poleR, rownanie, historia));
        poleR.addMouseListener(new MyMouseAdapter(poleR));

        this.btnLabels = new String[]{"", "<", "", ">", "", "C", "DEL", "(", ")", "=","7","8", "9",""+(char)8730, "!", "4",
                "5", "6", "%", "^", "1", "2", "3", "x", ""+(char)247, "NEG", "0", ".","+", "-"};

        this.btns = new MyButton[btnLabels.length];

        for(int i = 0; i<btnLabels.length; i++){
            btns[i] = new MyButton(btnLabels[i]);
            panel2.add(btns[i]);

            if(i==0 || i==2 || i==4){
                btns[i].setEnabled(false);
            }
            else{
                btns[i].addActionListener(new MyActionListener(labelONP,labelWynik,poleR, rownanie, historia));
            }

        }

        panelGlowny.setLayout(new BoxLayout(panelGlowny, BoxLayout.PAGE_AXIS));
        panelGlowny.add(panel);
        panelGlowny.add(panel2);

        frame.add(panelGlowny, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Kalkulator");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}
