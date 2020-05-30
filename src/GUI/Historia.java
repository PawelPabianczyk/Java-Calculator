package GUI;

import java.io.Serializable;
import java.util.ArrayList;

public class Historia implements Serializable {
    private ArrayList<Rekord> historia;
    private int wskaznik;

    public Historia(){
        this.historia = new ArrayList<>();
    }

    public void dodajRekord(Rekord rekord){
        this.historia.add(rekord);
        this.wskaznik = historia.size();
    }

    public Rekord poprzedniRekord(){
        if(!historia.isEmpty()){
            if(wskaznik >0){
                wskaznik--;
                return historia.get(wskaznik);
            }
        }
        return null;
    }

    public Rekord nastepnyRekord(){
        if(!historia.isEmpty()){
            if(wskaznik < historia.size()-1){
                wskaznik++;
                return historia.get(wskaznik);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return historia.toString();
    }
}