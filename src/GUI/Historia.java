package GUI;

import java.io.Serializable;
import java.util.ArrayList;

public class Historia implements Serializable {
    private ArrayList<Rekord> historia;

    public Historia(){
        this.historia = new ArrayList<>();
    }

    public void dodajRekord(Rekord rekord){
        this.historia.add(rekord);
    }

    @Override
    public String toString() {
        return historia.toString();
    }
}