package oslomet.testing;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminKundeControllerTest {

    @InjectMocks
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sjekk;
    @org.junit.Test

    @Test
    @DisplayName("Henter kunder dersom kunden er logget inn")
    public void testHentAlleKunder_loggetInn(){
        List <Kunde> kunder = new ArrayList<>() ;

        Kunde enKunde1 = new Kunde("10101020123", "Per", "Hansen", "Markveien 1",
                "0553", "Oslo", "45238845", "OsloMet123");

        Kunde enKunde2 = new Kunde("10101020122", "Ola", "Johansen", "Osloveien 2",
                "0551", "Oslo", "97129943", "AnvendtData99");
        kunder.add(enKunde1);
        kunder.add(enKunde2);

        when (sjekk.loggetInn()).thenReturn("10101020123");
        when (repository.hentAlleKunder()).thenReturn(kunder);

        List <Kunde> resultat = adminKundeController.hentAlle();

        assertEquals(kunder, resultat);

    }
    @org.junit.Test

    @Test
    @DisplayName("")

    public void testHentAlleKunder_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);
        List <Kunde> resultat = adminKundeController.hentAlle();
        assertNull(resultat);
    }
    @org.junit.Test

    @Test
    @DisplayName("")
    public void testRegistrerKunde_loggetInn(){
        Kunde nyKunde = new Kunde ("10101020123", "Per", "Hansen", "Markveien 1",
                "0553", "Oslo", "45238845", "OsloMet123");
        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.registrerKunde(nyKunde)).thenReturn("OK");

        String resultat = adminKundeController.lagreKunde(nyKunde);
        assertEquals("OK", resultat);
    }
    @org.junit.Test

    @Test
    @DisplayName("")
    public void testRegistrerKunde_ikkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);
        String resultat = adminKundeController.lagreKunde(null);
        assertEquals("Ikke innlogget", resultat);

    }
    @org.junit.Test

    @Test
    @DisplayName("")
    public void testEndreKunde_loggetInn(){
        Kunde originalKunde = new Kunde("10101020123", "Per", "Hansen", "Markveien 1",
                "0553", "Oslo", "45238845", "OsloMet123");
        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.endreKundeInfo(originalKunde)).thenReturn("OK");

        String resultat = adminKundeController.endre(originalKunde);
        assertEquals("ok", resultat);
    }
    @org.junit.Test
    @Test
    @DisplayName("")
    public void testEndreKunde_ikkeInnlogget() {
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.endre(null);
        assertEquals("Ikke innlogget", resultat);

    }
    @org.junit.Test
    @Test
    @DisplayName("")
    public void testSlettKunde_loggetInn (){
        Kunde slettKunde = new Kunde("10101020123", "Per", "Hansen", "Markveien 1",
                "0553", "Oslo", "45238845", "OsloMet123");
        when(sjekk.loggetInn()).thenReturn("10101020123");
        when(repository.slettKunde("10101020123")).thenReturn("OK");

        String resultat =adminKundeController.slett("10101020123");
        assertEquals("OK",resultat);
    }

    @org.junit.Test
    @Test
    @DisplayName("")
    public void testSlettKunde_ikkeInnlogget(){
        when(sjekk.loggetInn()).thenReturn(null);
        String resultat =adminKundeController.slett(null);
        assertEquals("Ikke innlogget", resultat);
    }
}
