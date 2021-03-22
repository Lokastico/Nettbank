package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SikkerhetTest {

    @InjectMocks
    private Sikkerhet sikkerhet;

    @Mock
    private BankRepository repository;

    @Mock
    private HttpSession session;

    @Test
    public void sjekkVellykketLoggInn() {
        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");
        String resultat = sikkerhet.sjekkLoggInn("12345678912", "hemmelig");

        assertEquals("OK", resultat);

    }

    @Test
    public void sjekkFeilLoggInn() {
        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("Feil");
        String resultat = sikkerhet.sjekkLoggInn("12345678912", "hemmelig");

        assertEquals("Feil i personnummer eller passord", resultat);

    }

    @Test
    public void sjekkFeilLoggInnPassord() {
        String resultat = sikkerhet.sjekkLoggInn("12839476912", "hei");

        assertEquals("Feil i passord", resultat);
    }

    @Test
    public void sjekkFeilLoggInnPersonnummer() {
        String resultat = sikkerhet.sjekkLoggInn("19283647", "hemmelig");

        assertEquals("Feil i personnummer", resultat);
    }


    @Test
    public void loggUt() {
        sikkerhet.loggUt();
        verify(session,times(1)).setAttribute("Innlogget", null);
    }

    @Test
    public void loggInnAdminvellykket() {
        String resultat = sikkerhet.loggInnAdmin("Admin", "Admin");
        verify(session, times(1)).setAttribute("Innlogget", "Admin");

        assertEquals("Logget inn", resultat);
    }
    @Test
    public void loggInnAdminFeil() {
        String resultat = sikkerhet.loggInnAdmin("admin", "Admin");
        verify(session, times(1)).setAttribute("Innlogget", null);

        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void loggetInn() {
        when(session.getAttribute("Innlogget")).thenReturn("hei");
        String resultat = sikkerhet.loggetInn();
        verify(session, times(1)).getAttribute("Innlogget");

        assertEquals("hei", resultat);
        //Hvis vi velger å ikke endre koden til Krattebøl, må "times" endres til "2" på linje 88.
    }

    @Test
    public void loggetInnNull() {
        when(session.getAttribute("Innlogget")).thenReturn(null);
        String resultat = sikkerhet.loggetInn();
        verify(session, times(1)).getAttribute("Innlogget");

        assertNull(resultat);
    }
}