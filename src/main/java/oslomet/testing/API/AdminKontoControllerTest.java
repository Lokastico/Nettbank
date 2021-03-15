package oslomet.testing.API;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;
import static org.junit.Assert.*;

public class AdminKontoControllerTest {

    @Autowired
    AdminRepository repository;

    @Autowired
    private Sikkerhet sjekk;


    @Test
    public void testHentAlleKonti(){


    }

    public void testRegistrerKonto(){

    }
}
