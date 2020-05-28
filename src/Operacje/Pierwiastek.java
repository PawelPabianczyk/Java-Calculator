package Operacje;

public class Pierwiastek extends Operacja {

    public Pierwiastek(){
        this.operator = 'V';
        this.priorytet = 3;
    }

    IOperacja pierwiastek = (Double x, Double y) -> Math.pow(y,1/x);

    @Override
    public Double oblicz(Double x, Double y) {
        return pierwiastek.oblicz(x,y);
    }
}
