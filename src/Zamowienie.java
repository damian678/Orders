import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Zamowienie implements Serializable
{
    //pola

    Pozycja[] tab;
    int ileDodanych;
    int maksRozmiar;

    //konstruktory

    public Zamowienie()
    {
        maksRozmiar = 10;
    }

    public Zamowienie(int maksRozmiar)
    {
        tab = new Pozycja[maksRozmiar];
        this.maksRozmiar = maksRozmiar;
    }

    //metoda dodająca pozycję do zamówienia

    public void dodajPozycje(Pozycja p)
    {
        ileDodanych++;
        for (int i = ileDodanych - 1; i < ileDodanych; i++)
        {
            tab[i] = p;
        }
    }

    //metoda usuwająca pozycję z zamówienia

    public void usunPozycje(int index)
    {
        for (int i = 0; i < ileDodanych; i++)
        {
            if (i == index - 1)
            {
                for (int j = 0; j < ileDodanych; j++)
                {
                    tab[i + j] = tab[i + (j + 1)];
                }
            }
        }
        ileDodanych--;
    }

    //edycja pozycji

    public void edytujPozycje(int index)
    {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < ileDodanych; i++)
        {
            if (i == index - 1)
            {
                System.out.println("\nEdytujesz pozycję w koszyku numer " + index);
                System.out.print("Podaj nazwę towaru: ");
                String nazwa = scanner.nextLine();
                System.out.print("Podaj liczbę sztuk towaru: ");
                int szt = scanner.nextInt();
                System.out.print("Podaj cenę towaru: ");
                double cena = scanner.nextDouble();
                tab[i].nazwaTowaru = nazwa;
                tab[i].ileSztuk = szt;
                tab[i].cena = cena;
            }
        }
    }

    //metoda licząca wartość zamówienia

    public double obliczWartosc()
    {

        double wart = 0;
        for (int i = 0; i < ileDodanych; i++)
        {
            wart += tab[i].obliczWartoscZRabatem();
        }
        return wart;
    }

    //zapis zamówienia

    public static void zapiszZamowienie(Zamowienie z, String nazwa) throws IOException
    {
        FileWriter plik = new FileWriter(nazwa);
        plik.write(z.toString());
        plik.close();
    }

    //odczyt zamówienia

    public static void wczytajZamowienie(String nazwa) throws IOException
    {
        File file = new File(nazwa);
        Scanner in = new Scanner(file);
        String zdanie = in.nextLine();
        System.out.println(zdanie);
        while (in.hasNextLine())
        {
            System.out.println(in.nextLine());
        }
    }

    //metoda toString

    public String toString()
    {
        String pozycja = "";
        java.text.DecimalFormat df=new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        for (int i = 0; i < ileDodanych; i++) {
            pozycja = pozycja.concat(tab[i] + "\n");
        }
        return "Dodanych: " + ileDodanych + "\nZamówienie: \n" +
                "|Nazwa towaru        |Cena          |Sztuk    |Koszt      |Z rabatem\n"+pozycja + "\nRazem: "
                + df.format(obliczWartosc())+" zł.";
    }

    public static void main(String[] args) throws IOException
    {
        Pozycja p1=new Pozycja("Cukier",12,2.30);
        Pozycja p2=new Pozycja("Kwiaty",2,19.99);
//        System.out.println(p1);
//        System.out.println(p2);
        Zamowienie zamowienie=new Zamowienie(10);
        zamowienie.dodajPozycje(p1);
        zamowienie.dodajPozycje(p2);

//        zamowienie.usunPozycje(1);
        System.out.println(zamowienie);
//        zamowienie.edytujPozycje(1);

//        Zamowienie.zapiszZamowienie(zamowienie,"order.txt");
        Zamowienie.wczytajZamowienie("order.txt");
    }
}