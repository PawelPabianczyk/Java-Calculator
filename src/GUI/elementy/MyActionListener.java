package GUI.elementy;

import GUI.Rekord;
import ONP.ONP;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyActionListener implements ActionListener {
    private MyLabel labelONP;
    private MyLabel labelWynik;
    private MyTextField poleR;
    private ArrayList<String> rownanie;
    private ArrayList<Rekord> historia;

    public MyActionListener(MyLabel labelONP, MyLabel labelWynik, MyTextField poleR, ArrayList rownanie){
        this.labelONP=labelONP;
        this.labelWynik=labelWynik;
        this.poleR=poleR;
        this.rownanie=rownanie;
        this.historia = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof MyButton){
            String wejscie = ((MyButton) e.getSource()).getText();

            switch (wejscie){
                case "C":
                    rownanie.clear();
                    break;
                case "DEL":
                    if(rownanie.size()>0)
                        rownanie.remove(rownanie.size()-1);
                    break;
                case "=":{
                    if(czyPrawidloweRownanie()) {
                        wyswietlObliczenia();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Błędne równanie.");
                    }
                    break;
                }
                case "NEG":
                    if(!rownanie.isEmpty())
                        rownanie.add("N");
                    else{
                        JOptionPane.showMessageDialog(null, "Najpierw liczba.");
                    }
                    break;
                case ")":
                    if(czyPoprawneNawiasy() && !czyZnakOperacja(rownanie.size()-1))
                        rownanie.add(wejscie);
                    else{
                        JOptionPane.showMessageDialog(null, "Nieprawidłowa ilość nawiasów.");
                    }
                    break;
                case "(":
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    rownanie.add(wejscie);
                    break;
                default:
                    if(rownanie.isEmpty())
                        JOptionPane.showMessageDialog(null, "Najpierw liczba.");
                    else if(!czyZnakOperacja(rownanie.size()-1))
                        rownanie.add(wejscie);
                    else
                        JOptionPane.showMessageDialog(null, "Dwa działania pod rząd!");
                    break;
            }
            poleR.setText(String.join("", rownanie));
        }
        else if(e.getSource() instanceof JTextField){
            String wejscie = ((JTextField) e.getSource()).getText();
            wejscie = zamienNaZnakiDoWyswietlania(wejscie);
            wejscie = wejscie + "=";
            wejscie = wejscie.substring(0, wejscie.indexOf("="));

            rownanie.clear();

            for(int i=0; i<wejscie.length(); i++){
                rownanie.add(String.valueOf(wejscie.charAt(i)));
            }

            if(czyPrawidloweRownanie()){
                wyswietlObliczenia();
            }
            else {
                JOptionPane.showMessageDialog(null, "Błędne równanie.");
            }
        }
    }

    private Boolean czyPrawidloweRownanie(){

        if(rownanie.isEmpty())
            return false;

        if(czyZnakOperacja(rownanie.size()-1))
            return false;

        if(Collections.frequency(rownanie, "(") != Collections.frequency(rownanie, ")"))
            return false;

        for(int i=1; i<rownanie.size(); i++){
            if(czyZnakOperacja(i-1) && czyZnakOperacja(i)) {
                return false;
            }
        }

        return true;
    }

    private void wyswietlObliczenia(){
        ONP onp = new ONP();

        if(!rownanie.get(rownanie.size()-1).equals("="))
            rownanie.add("=");

        String rownanieZwykle = String.join("", rownanie);
        String rownanieONP = onp.przeksztalcNaONP(zamienNaZnakiDoLiczenia(rownanieZwykle));
        labelONP.setText(zamienNaZnakiDoWyswietlania(rownanieONP));

        try {
            String wynik = onp.obliczOnp(rownanieONP);
            labelWynik.setText(wynik);
            zapisDoPliku();
        }catch (Exception exc){
            labelWynik.setText(exc.getMessage());
        }
    }

    private Boolean czyPoprawneNawiasy(){
        if(Collections.frequency(rownanie, "(") > Collections.frequency(rownanie, ")"))
            return true;
        return false;
    }

    private Boolean czyZnakOperacja(int indeks){
        if(rownanie.get(indeks).equals(""+(char)8730))
            return true;
        if(rownanie.get(indeks).equals("%"))
            return true;
        if(rownanie.get(indeks).equals("^"))
            return true;
        if(rownanie.get(indeks).equals("x"))
            return true;
        if(rownanie.get(indeks).equals(""+(char)247))
            return true;
        if(rownanie.get(indeks).equals("+"))
            return true;
        if(rownanie.get(indeks).equals("-"))
            return true;
        if(rownanie.get(indeks).equals("."))
            return true;
        if(rownanie.get(indeks).equals("("))
            return true;

        return false;
    }

    private String zamienNaZnakiDoLiczenia(String napis){

        napis = napis.replaceAll(""+(char)8730, "V");
        napis = napis.replaceAll(""+(char)247, "/");
        napis = napis.replaceAll("x", "*");

        return napis;
    }

    private String zamienNaZnakiDoWyswietlania(String napis){
        napis = napis.replace( 'V', (char)8730);
        napis = napis.replace('/', (char)247);
        napis = napis.replace('*', 'x');

        return napis;
    }

    private void zapisDoPliku(){
        historia.add(new Rekord(labelONP.getText(), poleR.getText(), labelWynik.getText()));

        try(FileWriter fw = new FileWriter("historia.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("Rownanie: " + poleR.getText());
            out.println("Rownanie ONP: "+labelONP.getText());
            out.println("Wynik: "+ labelWynik.getText());
            out.println("");
        } catch (IOException exc) {
            exc.getMessage();
        }
    }

}