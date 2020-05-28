package ONP;

import Operacje.*;

public class ONP {
    private Stos<Operacja> stos;
    private Stos<Liczba> stosLiczb;

    public String przeksztalcNaONP(String rownanie){
        String wynik = "";
        this.stos = new Stos<Operacja>(100);
        for (int i = 0; i < rownanie.length(); i++) {
            if (czyCyfra(rownanie.charAt(i)) || rownanie.charAt(i)=='.') {
                wynik += rownanie.charAt(i);
                if (!czyCyfra((rownanie.charAt(i + 1))) && rownanie.charAt(i+1)!='.')
                    wynik += " ";
            } else {
                switch (rownanie.charAt(i)) {
                    case ('('): {
                        stos.push(new NawiasLewy());
                        break;
                    }
                    case ('+'): {
                        if(czyWypisacStos(new Suma()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Suma());
                        break;
                    }
                    case ('-'): {
                        if(czyWypisacStos(new Roznica()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Roznica());
                        break;
                    }
                    case (')'):{
                        while (stos.getRozmiar() > 0 && !stos.getWierzcholek().czyRowne('('))
                            wynik = dodajNaWyjscie(wynik);

                        stos.pop();
                        break;
                    }
                    case ('/'): {
                        if(czyWypisacStos(new Iloraz()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Iloraz());
                        break;
                    }
                    case ('*'): {
                        if(czyWypisacStos(new Iloczyn()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Iloczyn());
                        break;
                    }
                    case ('%'): {
                        if(czyWypisacStos(new Modulo()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Modulo());
                        break;
                    }
                    case ('N'): {
                        if(czyWypisacStos(new Negacja()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Negacja());
                        break;
                    }
                    case ('^'): {
                        if(czyWypisacStos(new Potega()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Potega());
                        break;
                    }
                    case ('V'): {
                        if(czyWypisacStos(new Pierwiastek()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Pierwiastek());
                        break;
                    }
                    case ('!'): {
                        if(czyWypisacStos(new Silnia()))
                            wynik = dodajNaWyjscie(wynik);

                        stos.push(new Silnia());
                        break;
                    }
                    case ('='): {
                        while (stos.getRozmiar() > 0)
                            wynik = dodajNaWyjscie(wynik);

                        wynik += "=";
                        break;
                    }
                }
            }
        }
        return wynik;
    }

    private Boolean czyWypisacStos(Operacja operacja){
        if(stos.getRozmiar()>0 && operacja.czyMniejszyPriorytet(stos.getWierzcholek()) && !stos.getWierzcholek().czyRowne('('))
            return true;
        return false;
    }

    private String dodajNaWyjscie(String wynik){
        wynik = wynik + stos.pop().getOperator() + " ";
        return wynik;
    }

    private Boolean czyCyfra(Character znak){
        if (znak >= '0' && znak <= '9')
            return true;
        return false;
    }

    public String obliczOnp(String rownanie) throws ArithmeticException {
        stosLiczb= new Stos<Liczba>(100);
        String wynik = "";
        Double a = 0.0;
        Double b = 0.0;

        for (int i = 0; i < rownanie.length(); i++) {
            if (czyCyfra(rownanie.charAt(i)) || rownanie.charAt(i)=='.') {
                wynik += rownanie.charAt(i);
                if (!czyCyfra(rownanie.charAt(i + 1)) && rownanie.charAt(i+1)!='.') {
                    stosLiczb.push(new Liczba(Double.parseDouble(wynik)));
                    wynik = "";
                }

            } else if (rownanie.charAt(i) == '=') {
                return stosLiczb.pop().toString();

            }else if (rownanie.charAt(i) != ' ') {
                if (rownanie.charAt(i) != '!' && rownanie.charAt(i)!='N'){
                    b = stosLiczb.pop().getWartosc();
                }
                a = stosLiczb.pop().getWartosc();

                switch (rownanie.charAt(i)) {
                    case ('+'): {
                        stosLiczb.push(new Liczba((a + b)));
                        break;
                    }
                    case ('-'): {
                        stosLiczb.push(new Liczba((a - b)));
                        break;
                    }
                    case ('x'):
                        ;
                    case ('*'): {
                        stosLiczb.push(new Liczba((a * b)));
                        break;
                    }
                    case ('/'): {
                        if(b==0)
                            throw new ArithmeticException("Nie dziel przez zero.");
                        stosLiczb.push(new Liczba((a / b)));
                        break;
                    }
                    case ('^'): {
                        if(a==0 && b==0)
                            throw new ArithmeticException("Zero do potęgi zerowej.");
                        stosLiczb.push(new Liczba(Math.pow(a, b)));
                        break;
                    }
                    case ('V'): {
                        if(a <= 0)
                            throw new ArithmeticException("Pierwsza liczba nie jest dodatnia.");
                        if(b==0)
                            throw new ArithmeticException("Druga liczba jest zerem.");
                        stosLiczb.push(new Liczba(Math.pow(b, 1/a)));
                        break;
                    }
                    case ('%'): {
                        if(a==0)
                            throw new ArithmeticException("Nie dziel przez zero.");
                        stosLiczb.push(new Liczba(a%b));
                        break;
                    }
                    case ('!'): {
                        if(a<0)
                            throw new ArithmeticException("Podana liczba jest mniejsza od zera.");
                        stosLiczb.push(new Liczba(silnia(a)));
                        break;
                    }
                    case ('N'):{
                        stosLiczb.push(new Liczba(-a));
                        break;
                    }
                }
            }
        }
        return "0.0";
    }

    private static double silnia(double i) {
        if (i < 1)
            return 1;
        else
            return i * silnia(i - 1);
    }
}