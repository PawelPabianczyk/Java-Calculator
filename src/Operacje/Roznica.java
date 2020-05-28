package Operacje;

public class Roznica extends Operacja {

    public Roznica(){
        this.operator = '-';
        this.priorytet = 1;
    }

    IOperacja roznica = (Double x, Double y) -> x-y;

    @Override
    public Double oblicz(Double x, Double y) {
        return roznica.oblicz(x,y);
    }
}
