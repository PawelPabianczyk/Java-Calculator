package Operacje;

public class Iloraz extends Operacja {

    public Iloraz(){
        this.operator = '/';
        this.priorytet = 2;
    }

    IOperacja iloraz = (Double x, Double y) -> x/y;

    @Override
    public Double oblicz(Double x, Double y) {
        return iloraz.oblicz(x,y);
    }
}
