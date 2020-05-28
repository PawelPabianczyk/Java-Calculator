package Operacje;

public class Liczba{
    private double wartosc;

    public Liczba(double wartosc){
        this.wartosc = wartosc;
    }

    public double getWartosc() {
        return wartosc;
    }

    @Override
    public String toString() {
        return Double.toString(wartosc);
    }
}
