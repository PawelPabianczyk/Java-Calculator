package GUI;

import java.io.Serializable;

public class Rekord implements Serializable {
    private String onp;
    private String wynik;
    private String rownanie;

    public Rekord(String onp, String rownanie, String wynik){
        this.onp = onp;
        this.rownanie = rownanie;
        this.wynik = wynik;
    }

    public String getOnp() {
        return onp;
    }

    public String getRownanie() {
        return rownanie;
    }

    public String getWynik() {
        return wynik;
    }

    @Override
    public String toString() {
        return "Rekord{" +
                "onp='" + onp + '\'' +
                ", wynik='" + wynik + '\'' +
                ", rownanie='" + rownanie + '\'' +
                '}';
    }
}
