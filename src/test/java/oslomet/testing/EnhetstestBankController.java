package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;


@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    private BankController bankController;

    @Mock
    private BankRepository repository;

    @Mock
    private Sikkerhet sjekk;


    @Test
    @DisplayName("Henter transaksjoner dersom personnr ikke er null")
    public void testHentTransaksjoner_loggetinn() {

       // List<Konto> hentTrans = new ArrayList<>();

        Konto enTransaksjon = new Konto("10101020123", "123456789", 1290,
                "Lønnskonto", "NOK", null);

       // hentTrans.add(enTransaksjon);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(enTransaksjon);

        Konto resultat = bankController.hentTransaksjoner(anyString(), anyString(), anyString());

        assertEquals(resultat, enTransaksjon);

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, transaksjoner")
    public void testHentTransaksjoner_ikkeloggetinn() {

        when(sjekk.loggetInn()).thenReturn(null);

        Konto resultat = bankController.hentTransaksjoner(anyString(), anyString(), anyString());

        assertNull(resultat);
    }

    @Test
    @DisplayName("Henter konti dersom kunde er logget inn")
    public void hentKonti_LoggetInn()  {

        List<Konto> konti = new ArrayList<>();

        Konto konto1 = new Konto("10101020123", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        List<Konto> resultat = bankController.hentKonti();

        assertEquals(konti, resultat);
    }

    @Test
    @DisplayName("Returnerer null hvis kunde ikke er logget inn, konti")
    public void hentKonti_IkkeLoggetInn()  {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = bankController.hentKonti();

        assertNull(resultat);
    }

    @Test
    @DisplayName("Henter saldi dersom personnr ikke er null")
    public void testHentSaldi_loggetinn() {

        List<Konto> saldi = new ArrayList<>();

        Konto hentSaldi = new Konto("10101020123","0101010101",2500,
                "Lønnskonto","NOK",null);

       saldi.add(hentSaldi);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        List<Konto> resultat = bankController.hentSaldi();

        assertEquals(saldi, resultat);
    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, saldi")
    public void testHentSaldi_ikkeloggetinn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = bankController.hentSaldi();

        assertNull(resultat);
    }

    @Test
    @DisplayName("Registrerer betaling dersom personnr ikke er null")
    public void testRegistrerBetaling_loggetinn() {

        Transaksjon enTransaksjon = new Transaksjon(45758, "12345678",
                1250, "16.06.19", "overføring", "avventer", "12312322323");

        when(sjekk.loggetInn()).thenReturn("10101020123");

        java.lang.String string = "OK";
        when(repository.registrerBetaling(enTransaksjon)).thenReturn(string);

        String resultat = bankController.registrerBetaling(enTransaksjon);

        assertEquals(string, resultat);
        verify(sjekk).loggetInn();
        verify(repository).registrerBetaling(enTransaksjon);
    }


    @Test
    @DisplayName("Returnerer null dersom personnr er null, betalingsregistrering")
    public void testRegistrerBetaling_ikkeloggetinn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = bankController.registrerBetaling(null);

        assertNull(resultat);
        verify(sjekk).loggetInn();
        verify(repository, times(0)).registrerBetaling(null);
    }

    @Test
    @DisplayName("Henter betaling dersom personnr ikke er null")
    public void testHentBetalinger_loggetinn() {

        List<Transaksjon> hentBetaling = new ArrayList<>();

        Transaksjon betaling = new Transaksjon(45758, "12345678",
                1250, "16.06.19", "overføring", "avventer", "12312322323");

       hentBetaling.add(betaling);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentBetalinger(anyString())).thenReturn(hentBetaling);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertEquals(hentBetaling, resultat);
    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, hent betaling")
    public void testHentBetalinger_ikkeloggetinn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertNull(resultat);
    }

    @Test
    @DisplayName("Utfører betaling dersom personnr ikke er null")
    public void testUtforBetaling_loggetinn() {

        Transaksjon enTransaksjon = new Transaksjon(45758, "12345678",
                1250, "16.06.19", "overføring", "avventer", "12312322323");

        List<Transaksjon> utforbetaling = new ArrayList<>();

        utforbetaling.add(enTransaksjon);

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.utforBetaling(45758)).thenReturn(String.valueOf(utforbetaling));

        when(repository.hentBetalinger(anyString())).thenReturn(utforbetaling);

        List<Transaksjon> resultat = bankController.utforBetaling(45758);

        assertEquals(utforbetaling, resultat);

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, utfør betaling")
    public void testUtforBetaling_ikkeloggetinn() {

        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.utforBetaling(45758);

        assertNull(resultat);
    }

    @Test
    @DisplayName("Henter kundeinfo dersom kunde er logget inn")
    public void hentKundeInfo_loggetInn() {

        Kunde enKunde = new Kunde("10101020123",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("10101020123");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        Kunde resultat = bankController.hentKundeInfo();

        assertEquals(enKunde, resultat);
    }

    @Test
    @DisplayName("Returnerer null dersom kunde ikke er logget inn og man prøver å hente kundeinfo")
    public void hentKundeInfo_IkkeloggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        Kunde resultat = bankController.hentKundeInfo();

        assertNull(resultat);
    }
}

