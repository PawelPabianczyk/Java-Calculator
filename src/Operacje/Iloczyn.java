package Operacje;

public class Iloczyn extends Operacja {
    public Iloczyn(){
        this.operator= '*';
        this.priorytet = 2;
    }

    IOperacja iloczyn = (Double x, Double y) -> x*y;

    @Override
    public Double oblicz(Double x, Double y) {
        return iloczyn.oblicz(x,y);
    }
}
