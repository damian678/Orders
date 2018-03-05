import java.io.Serializable;

public class Pozycja implements Serializable
{
    //pola

    String nazwaTowaru;
    int ileSztuk;
    double cena;

    //konstruktor

    public Pozycja(String nazwaTowaru, int ileSztuk, double cena) {
        this.nazwaTowaru = nazwaTowaru;
        this.ileSztuk = ileSztuk;
        this.cena = cena;
    }

    //metoda licząca wartość pozycji

    public double obliczWartosc() {
        double wartosc;
        wartosc =  ileSztuk * cena;
        wartosc*=100;
        wartosc=Math.round(wartosc);
        wartosc=wartosc/100;
        return wartosc;
    }

    //metoda licząca wartość pozycji z rabatem

//        – 5–10 szt. rabat 5%,
//        – 10–20 szt. rabat 10
//        – powyżej 20 szt. rabat 15%.

    public double obliczWartoscZRabatem() {
        double wynik = 0;
        double rabat;
        if (ileSztuk >= 5 && ileSztuk <= 10) {
            wynik = obliczWartosc() - obliczWartosc() * 0.05;
        } else if (ileSztuk > 10 && ileSztuk <= 20) {
            wynik = obliczWartosc() - obliczWartosc() * 0.1;
        } else if (ileSztuk > 20) {
            wynik = obliczWartosc() - obliczWartosc() * 0.15;
        } else {
            wynik = obliczWartosc();
        }
        rabat = wynik * 100;
        rabat = Math.round(rabat);
        rabat = rabat / 100;

        return rabat;
    }

    //metoda toString

    public String toString()
    {
        java.text.DecimalFormat df=new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String wynik = "", wynik1, wynik2 = "", wynik3 = "", wynik4 = "", wynik5 = "";
        wynik1 = wynik.concat("|" + nazwaTowaru);
        for (int i = 0; i < 20 - nazwaTowaru.length(); i++) {
            wynik1 = wynik1.concat(" ");
        }
        String cenka = "" + df.format(cena);
        String sztuki = "" + ileSztuk;
        wynik2 = wynik1.concat("|" + cenka + " zł.");
        for (int i = 0; i < 10 - cenka.length(); i++) {
            wynik2 = wynik2.concat(" ");
        }
        wynik3 = wynik2.concat("|" + ileSztuk + " szt.");
        for (int i = 0; i < 4 - sztuki.length(); i++) {
            wynik3 = wynik3.concat(" ");
        }
        String wynik6=""+df.format(obliczWartosc());
        wynik4 = wynik3.concat("|" + df.format(obliczWartosc()) + " zł.");
        for (int i = 0; i < 7 - wynik6.length(); i++) {
            wynik4 = wynik4.concat(" ");
        }
        wynik5 = wynik4.concat("|" + df.format(obliczWartoscZRabatem()) + " zł.");
        return wynik5;
    }
}