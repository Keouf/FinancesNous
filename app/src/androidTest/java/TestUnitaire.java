import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.example.pcportablevidjay.financesnous.Depense_Activity;
import com.example.pcportablevidjay.financesnous.Magasin_Activity;
import android.support.test.rule.ActivityTestRule;
import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import classes.Depense;
import classes.Magasin;
import classes.MyDBHelper;
import classes.Utilisateur;

import static android.app.PendingIntent.getActivity;
import static android.support.v4.app.ActivityCompat.startActivity;

public class TestUnitaire extends AndroidTestCase {


    Date fictif = new Date();
    Utilisateur utilisateurBidon = new Utilisateur(13,"bidon@fn.fr","1234");
    Magasin magasinTest = new Magasin(10,"test","test","test","test","test","test");
    Depense test = new Depense(10,fictif,14,utilisateurBidon,"domaine",magasinTest,"rien");
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

    public void testIdUtilisateur() throws Exception {
        assertEquals(1, dpTest.getUtilisateur("test@fn.fr").getId_utilisateur());
    }

    public void testSurLaCreationDeDepenseFaux() throws Exception {
        int nbDeDepense = dpTest.getLastDepenseID();
        dpTest.insertDepense(test);
        assertEquals(nbDeDepense, dpTest.getLastDepenseID());
    }


    public void testAjoutDomaine() throws Exception {
        assertEquals(true, dpTest.ajoutDomaine("TestUnit"));
    }

    public void testAjoutUtilisateur() throws Exception {
        assertEquals(true, dpTest.creerCompte(utilisateurBidon));
    }


}