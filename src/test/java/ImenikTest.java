import ba.unsa.etf.rpr.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest {
    private static Imenik imenik = new Imenik();

    @BeforeAll
    public static void setuo() {
        imenik.dodaj("Muaz", new FiksniBroj(Grad.SARAJEVO, "234-467"));
        imenik.dodaj("Amar", new MobilniBroj(62, "435-428"));
        imenik.dodaj("Emir", new FiksniBroj(Grad.GORAZDE, "123/764"));
        imenik.dodaj("Nedim", new MedunarodniBroj("+64", "12512543"));
    }

    @Test
    public void dajBrojFound() {
        String broj = imenik.dajBroj("Muaz");
        assertEquals(broj, "033/234-467");
    }

    @Test
    public void dajBrojNotFound() {
        String broj = imenik.dajBroj("Bakir");
        assertNull(broj);
    }

    @Test
    public void dodajTestPositive() {
        TelefonskiBroj broj = new MobilniBroj(61, "234-231");
        imenik.dodaj("Hamo", broj);

        String brojString = imenik.dajBroj("Hamo");
        assertEquals(brojString, "061/234-231");
    }

    @Test
    public void dodajFiksniException() {
        // old-school
        assertThrows(BrojException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new FiksniBroj(null, "123-123");
            }
        });

        // lambda
        assertThrows(BrojException.class, () -> {
            new FiksniBroj(null, "123-123");
        });
    }

}
