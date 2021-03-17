package oslomet.testing;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        Konto enTransaksjon = new Konto("10101020123", "123456789", 1290,
                "Lønnskonto", "NOK" , null);


    }
}
