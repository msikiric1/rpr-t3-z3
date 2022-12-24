package ba.unsa.etf.rpr;

import java.util.Scanner;
import java.util.Set;

public class Program {
    public static Scanner scanner = new Scanner(System.in);
    public static Imenik imenik = new Imenik();
    public static void main(String[] args) {
        popuniPodatke();
        while(true) {
            System.out.println("Unesite komandu [dodaj, dajBroj, dajIme, naSlovo, izGrada, izGradaBrojevi, imenik, izlaz]:");
            String komanda = scanner.nextLine();
            switch (komanda) {
                case "dodaj":
                    dodajBroj();
                    break;
                case "dajBroj":
                    dajBroj();
                    break;
                case "dajIme":
                    dajIme();
                    break;
                case "naSlovo":
                    naSlovo();
                    break;
                case "izGrada":
                    izGrada();
                    break;
                case "izGradaBrojevi":
                    izGradaBrojevi();
                    break;
                case "imenik":
                    ispisiImenik();
                    break;
                case "izlaz":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pogresna komanda.");
            }
        }
    }

    private static void izGradaBrojevi() {
        System.out.print("Unesite ime grada: ");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(g);
            for(TelefonskiBroj broj : brojevi)
                System.out.println(broj);
        } catch(Exception e) {
            System.out.println("Pogresan grad.");
        }
    }

    private static void izGrada() {
        System.out.print("Unesite ime grada: ");
        String grad = scanner.nextLine();
        try {
            Grad g = Grad.valueOf(grad);
            Set<String> imena = imenik.izGrada(g);
            System.out.println(imena);
        } catch(Exception e) {
            System.out.println("Pogresan grad.");
        }
    }

    private static void naSlovo() {
        System.out.print("Unesite prvo slovo imena: ");
        String c = scanner.nextLine();
        String imena = imenik.naSlovo(c.toCharArray()[0]);
        System.out.println(imena);
    }

    private static void dajIme() {
        TelefonskiBroj broj = unesiBrojTelefona();
        String ime = imenik.dajIme(broj);
        if(ime == null)
            System.out.println("Ne postoji u imeniku");
        else
            System.out.println("Vlasnik broja " + broj + " je " + ime);
    }

    private static TelefonskiBroj unesiBrojTelefona() {
        System.out.print("Unesi tip broja [fiksni, mobilni, medjunarodni]:");
        String tip = scanner.nextLine();
        switch (tip) {
            case "fiksni":
                System.out.print("Unesite pozivni: ");
                String pozivni = scanner.nextLine();
                System.out.print("Unesite broj: ");
                String broj = scanner.nextLine();
                return new FiksniBroj(Grad.izPozivnog(pozivni), broj);
            case "mobilni":
                System.out.print("Unesite mrezu: ");
                int mreza = scanner.nextInt();
                System.out.print("Unesite broj: ");
                String mobilniBroj = scanner.nextLine();
                return new MobilniBroj(mreza, mobilniBroj);
            case "medjunarodni":
                System.out.print("Unesite kod drzave [+387]: ");
                String kod = scanner.nextLine();
                System.out.print("Unesite broj: ");
                String medjuBroj = scanner.nextLine();
                return new MedunarodniBroj(kod, medjuBroj);
        }
        return null;
    }

    private static void dodajBroj() {
        System.out.print("Unesite ime: ");
        String ime = scanner.nextLine();
        TelefonskiBroj broj = unesiBrojTelefona();
        imenik.dodaj(ime, broj);
    }

    private static void ispisiImenik() {
        System.out.println(imenik.toString());
    }

    private static void dajBroj() {
        System.out.print("Unesite ime: ");
        String ime = scanner.nextLine();
        String rezultat = imenik.dajBroj(ime);
        System.out.println((rezultat == null) ? "Nema u imeniku" : rezultat);
    }

    private static void popuniPodatke() {
        imenik.dodaj("Muaz", new FiksniBroj(Grad.SARAJEVO, "234-467"));
        imenik.dodaj("Amar", new MobilniBroj(62, "435-428"));
        imenik.dodaj("Emir", new FiksniBroj(Grad.GORAZDE, "123/764"));
        imenik.dodaj("Nedim", new MedunarodniBroj("+64", "12512543"));
    }
}