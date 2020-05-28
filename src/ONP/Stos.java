package ONP;

import java.util.LinkedList;

public class Stos<T> {
    private LinkedList<T> stos;
    private int wskaznikStosu;
    private int pojemnosc;

    Stos(int pojemnosc){
        this.stos = new LinkedList<>();
        this.wskaznikStosu = 0;
        this.pojemnosc = pojemnosc;
    }

    public void push(T x) throws ArrayIndexOutOfBoundsException{
        if(wskaznikStosu>=pojemnosc)
            throw new ArrayIndexOutOfBoundsException("ONP.Stos jest pełny.");
        this.stos.push(x);
        this.wskaznikStosu++;
    }

    public T pop() throws ArrayIndexOutOfBoundsException{
        if(this.wskaznikStosu==0)
            throw new ArrayIndexOutOfBoundsException("ONP.Stos jest pusty.");
        wskaznikStosu--;
        return this.stos.pop();
    }

    public int getRozmiar() {
        return wskaznikStosu;
    }

    public void setPojemnosc(int nowaPojemnosc) throws ArrayIndexOutOfBoundsException{
        if(nowaPojemnosc<0)
            throw new ArrayIndexOutOfBoundsException("Wartość ustawianej pojemności jest nieprawidłowa.");
        this.pojemnosc = nowaPojemnosc;
    }

    public T getWierzcholek(){
        if(getRozmiar() >0)
            return stos.getFirst();
        return null;
    }

    public LinkedList<T> getStos() {
        return stos;
    }
}