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
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, transaksjoner")
    public void testHentTransaksjoner_ikkeloggetinn() {

    }

    @Test
    @DisplayName("Henter saldi dersom personnr ikke er null")
    public void testHentSaldi_loggetinn() {

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, saldi")
    public void testHentSaldi_ikkeloggetinn() {

    }

    @Test
    @DisplayName("Registrerer betaling dersom personnr ikke er null")
    public void testRegistrerBetaling_loggetinn() {

    }

    @Test
    @DisplayName("Returnerer null dersom personnr er null, betalingsregistrering")
    public void testRegistrerBetaling_ikkeloggetinn() {

    }

    @Test
    @DisplayName("Henter betaling dersom personnr ikke er null")
    public void testHentBetaling_loggetinn() {

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, hent betaling")
    public void testHentBetaling_ikkeloggetinn() {

    }

    @Test
    @DisplayName("Utfører betaling dersom personnr ikke er null")
    public void testUtforBetaling() {

    }

    @Test
    @DisplayName("Returnerer null hvis personnr er null, utfør betaling")
    public void testUtforBetaling() {

    }

    @Test
    @DisplayName("Henter kundeinfo dersom kunde er logget inn")
    public void hentKundeInfo_loggetInn() {

        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

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

    @Test
    @DisplayName("Henter konti dersom kunde er logget inn")
    public void hentKonti_LoggetInn()  {

        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

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
}

