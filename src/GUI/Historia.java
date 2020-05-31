package GUI;

import java.io.Serializable;
import java.util.ArrayList;

public class Historia implements Serializable {
    private ArrayList<Rekord> historia;
    private int wskaznik;

    public Historia(){
        this.historia = new ArrayList<>();
        this.wskaznik = historia.size();
    }

    public void dodajRekord(Rekord rekord){
        this.historia.add(rekord);
        this.wskaznik = historia.size()-1;
    }

    public int getSize(){
        return historia.size();
    }


    public Rekord poprzedniRekord(){
        if(!historia.isEmpty()){
            if(wskaznik >0){
                wskaznik--;
                Rekord rekord = historia.get(wskaznik);
                return rekord;
            }
            else {
                wskaznik = 0;
                return historia.get(0);
            }
        }
        return null;
    }

    public Rekord nastepnyRekord(){
        if(!historia.isEmpty()){
            if(wskaznik < historia.size()-1){
                wskaznik++;
                Rekord rekord = historia.get(wskaznik);
                return rekord;
            }
            else {
                wskaznik = historia.size()-1;
                return historia.get(historia.size()-1);
            }
        }
        return null;
    }

    public Boolean czyPusta(){
        if(historia.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return historia.toString();
    }
}