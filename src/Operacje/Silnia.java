package Operacje;

public class Silnia extends Operacja{

    public Silnia(){
        this.operator = '!';
        this.priorytet = 4;
    }

    IOperacja silnia = (Double x, Double y) -> silnia(x);

    @Override
    public Double oblicz(Double x, Double y) {
        return silnia.oblicz(x,y);
    }

    private double silnia(double x) {
        if (x < 1)
            return 1;
        else
            return x * silnia(x - 1);
    }
}
