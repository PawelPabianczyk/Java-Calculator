package Operacje;

public class Suma extends Operacja{

    public Suma(){
        this.operator = '+';
        this.priorytet = 1;
    }

    IOperacja suma = (Double x, Double y) -> x+y;

    @Override
    public Double oblicz(Double x, Double y) {
        return suma.oblicz(x, y);
    }
}
