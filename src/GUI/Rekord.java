package GUI;

public class Rekord {
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
}
