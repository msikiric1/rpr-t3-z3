package ba.unsa.etf.rpr;

import java.util.*;

public class Imenik {
    private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        this.brojevi = new HashMap<>();
    }

    public Map<String, TelefonskiBroj> getBrojevi() {
        return brojevi;
    }

    public void setBrojevi(Map<String, TelefonskiBroj> brojevi) {
        this.brojevi = brojevi;
    }

    public void dodaj(String ime, TelefonskiBroj broj) {
        this.brojevi.put(ime, broj);
    }

    public String dajBroj(String ime) {
        TelefonskiBroj broj = brojevi.get(ime);
        if(broj != null)
            return broj.ispisi();
        return null;
    }

    public String dajIme(TelefonskiBroj broj) {
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(entry.getValue().ispisi().equals(broj.ispisi())) { // koristimo metodu ispisi() zbog toga sto nemamo implementiranu equals() metodu
                return entry.getKey();
            }
        }
        return null;
    }

    public String naSlovo(char s) {
        StringBuilder builder = new StringBuilder();

        int i = 1;
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if (entry.getKey().startsWith(String.valueOf(s))) {
                builder.append(i).append(". ").append(entry.getKey())
                        .append(" - ").append(entry.getValue().ispisi())
                        .append(System.lineSeparator());
            }
            i++;
        }
        return builder.toString();
    }

    public Set<String> izGrada(Grad g) {
        Set<String> osobe = new TreeSet<>(); // TreeSet automatski sortira elemente u rastući poredak
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            if(jeLiIzGrada(entry.getValue(), g))
                osobe.add(entry.getKey());
        }
        return osobe;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> brojevi = new TreeSet<>(new Comparator<TelefonskiBroj>() {
            @Override
            public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                return o1.ispisi().compareTo(o2.ispisi());
            }
        });

        for(Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if(jeLiIzGrada(entry.getValue(), g))
                brojevi.add(entry.getValue());
        }
        return brojevi;
    }

    private boolean jeLiIzGrada(TelefonskiBroj broj, Grad g) {
        if(broj instanceof FiksniBroj)
            return g.equals(((FiksniBroj) broj).getGrad());
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int i = 1;
        for(Map.Entry<String, TelefonskiBroj> entry : brojevi.entrySet()) {
            builder.append(i).append(". ").append(entry.getKey())
                        .append(" - ").append(entry.getValue().ispisi())
                        .append(System.lineSeparator());
            i++;
        }
        return builder.toString();
    }
}
