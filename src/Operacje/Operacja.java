package Operacje;

public class Operacja implements IOperacja{

    protected Character operator;
    protected int priorytet;

    public Character getOperator() {
        return operator;
    }

    public int getPriorytet() {
        return priorytet;
    }

    public Boolean czyMniejszyPriorytet(Operacja operacja){
        if(operacja.getPriorytet() >= this.getPriorytet())
            return true;
        return false;
    }

    public Boolean czyRowne(Character o){
        if(this.operator == o)
            return true;
        return false;
    }

    @Override
    public Double oblicz(Double x, Double y) {
        return null;
    }
}
