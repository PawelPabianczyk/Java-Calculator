package Operacje;

public class Negacja extends Operacja {

    public Negacja(){
        this.operator = 'N';
        this.priorytet = 2;
    }

    IOperacja negacja = (Double x, Double y) -> x*(-1);

    @Override
    public Double oblicz(Double x, Double y) {
        return negacja.oblicz(x,y);
    }
}
