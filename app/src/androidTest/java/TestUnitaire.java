import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.test.AndroidTestCase;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.example.pcportablevidjay.financesnous.Depense_Activity;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;

import classes.Depense;
import classes.Magasin;
import classes.MyDBHelper;
import classes.Utilisateur;

import static android.support.v4.app.ActivityCompat.startActivity;


public class TestUnitaire extends AndroidTestCase {


    Date fictif = new Date();
    Utilisateur utilisateurBidon = new Utilisateur(12,"bidon","1234");
    Utilisateur utilisateur = new Utilisateur(1,"test@fn.fr","test");
    Magasin magasinTest = new Magasin(10,"test","test","test","test","test","test");
    Magasin magasinTestDeux = new Magasin(11,"test2","test2","test2","test2","test2","test2");
    Depense test = new Depense(10,fictif,14,utilisateurBidon,"domaine",magasinTest,"rien");
    Depense testVrai = new Depense(10,fictif,14.00,utilisateur,"Alimentation",magasinTest,"");
    MyDBHelper dpTest = new MyDBHelper();

    public void testsurDepense() throws Exception {
        assertEquals(magasinTest, test.getMagasin());
    }

    public void testsurDomaineAlimentation() throws Exception {
        assertEquals("Alimentation", dpTest.getDomaineWithId(1));
    }

    public void testsurMagasinAuchanAvecId() throws Exception {
        assertEquals("Auchan", dpTest.getMagasinWithId(1).getNom_managasin());
    }

    /*public void testsurMagasinAuchan() throws Exception  {
    Looper.prepare();
                Depense_Activity hActivity = new Depense_Activity();
                assertEquals("Auchan", dpTest.getMagasinWithReference("Auchan", hActivity).toString());
    Looper.loop();
    }*/

    public void testSurCombienDepense() throws Exception {
        assertEquals(321.0, dpTest.getDepenseByDomaine(6));
    }

    public void testMotDePasseUtilisateur() throws Exception {
        assertEquals("test", dpTest.getUtilisateur("test@fn.fr").getMotDePasse());
    }

    public void testIdUtilisateur() throws Exception {
        assertEquals(1, dpTest.getUtilisateur("test@fn.fr").getId_utilisateur());
    }

    public void testSurLeNombreDeMagasins() throws Exception {
        assertEquals(2, dpTest.getAllMagasins().size());
    }

    public void testSurLeNombreDeDomaines() throws Exception {
        assertEquals(6, dpTest.getAllDomaines().size());
    }

    public void testSurLaCreationDeDepenseFaux() throws Exception {
        int nbDeDepense = dpTest.getLastDepenseID();
        dpTest.insertDepense(test);
        assertEquals(nbDeDepense, dpTest.getLastDepenseID());
    }

    public void testSurAllDepense() throws Exception {
        assertEquals("", utilisateur.getAllDepensesInString());
    }
    public void testSurDixDerniereDepense() throws Exception {
        assertEquals(10, utilisateur.get10DernierDepenses().size());
    }

}