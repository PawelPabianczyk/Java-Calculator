package Operacje;

public class Modulo extends Operacja {

    public Modulo(){
        this.operator = '%';
        this.priorytet = 2;
    }

    IOperacja modulo = (Double x, Double y) -> x%y;

    @Override
    public Double oblicz(Double x, Double y) {
        return modulo.oblicz(x,y);
    }
}
