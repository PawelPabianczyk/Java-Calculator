package Operacje;

public class Potega extends Operacja {

    public Potega(){
        this.operator = '^';
        this.priorytet = 3;
    }

    IOperacja potega = (Double x, Double y) -> Math.pow(x,y);

    @Override
    public Double oblicz(Double x, Double y) {
        return potega.oblicz(x,y);
    }
}
