package oslomet.testing;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.bind.annotation.RequestBody;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AdminKontoControllerTest {

    @InjectMocks
    private AdminKontoController adminKontoController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    @DisplayName("Henter alle konti dersom personnr ikke er null")
    public void testHentAlleKonti_loggetInn(){
        List<Konto> konti = new ArrayList<>();

        Konto enKonto1 = new Konto("10101020123", "01010110523", 720,
                "Lønnskonto", "NOK" , null);
        Konto enKonto2 = new Konto("10101020123", "12345678901" , 1000 ,
                "Brukskonto" , "NOK", null );

        konti.add(enKonto1);
        konti.add(enKonto2);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentAlleKonti()).thenReturn(konti);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertEquals(konti, resultat);
    }

    @Test
    @DisplayName("")
    public void testHentAlleKonti_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertNull(resultat);
    }


    @Test
    @DisplayName("")
    public void testRegistrerKonto_loggetInn(){
        Konto nyKonto = new Konto("10101020123", "01010110523", 720,
                "Lønnskonto", "NOK" , null);

        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.registrerKonto(nyKonto)).thenReturn("OK)");

        String resultat = adminKontoController.registrerKonto(nyKonto);

        assertEquals("OK", resultat);
    }

    @Test
    @DisplayName("")
    public void testRegistrerKonto_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.registrerKonto(null);

        assertEquals("ikke innlogget", resultat);
    }

    @Test
    @DisplayName("")
    public void testEndreKonto_loggetInn(){
        Konto originalKonto = new Konto("10101020123", "01010110523", 720,
                "Lønnskonto", "NOK" , null);

        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.endreKonto(originalKonto)).thenReturn("OK");

        String resultat = adminKontoController.endreKonto(originalKonto);

        assertEquals("OK",resultat);
    }

    @Test
    @DisplayName("")
    public void testEndreKonto_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.endreKonto(null);

        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    @DisplayName("")
    public void testSlettKonto_loggetInn(){
        Konto slettetKonto = new Konto("10101020123", "01010110523", 720,
                "Lønnskonto", "NOK" , null);

        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.slettKonto("10101020123")).thenReturn("OK");

        String resultat = adminKontoController.slettKonto("10101020123");

        assertEquals("OK",resultat);
    }

    @Test
    @DisplayName("")
    public void testSlettKonto_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.slettKonto(null);

        assertEquals("Ikke innlogget", resultat);
    }


}
