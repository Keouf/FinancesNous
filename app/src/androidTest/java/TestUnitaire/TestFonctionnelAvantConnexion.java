package TestUnitaire;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.DatePicker;

import com.example.pcportablevidjay.financesnous.Accueil_Activity;
import com.example.pcportablevidjay.financesnous.CreerCompte_Activity;
import com.example.pcportablevidjay.financesnous.Depense_Activity;
import com.example.pcportablevidjay.financesnous.Login_Activity;
import com.example.pcportablevidjay.financesnous.R;

import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import classes.Depense;
import classes.Magasin;
import classes.Utilisateur;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.core.deps.guava.base.CharMatcher.is;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;

@RunWith(AndroidJUnit4.class)
public class TestFonctionnelAvantConnexion {
    @Rule
    public ActivityTestRule<Login_Activity> mactivityrule = new ActivityTestRule<>(Login_Activity.class);
    @Test
    public void testCreationDeCompteFaux() {
        onView(withId(R.id.link_new_account)).perform(click());

        onView(withId(R.id.editTextCreerEmail)).perform(typeText("ça fonctionne pas !"));
        onView(withId(R.id.editTextCreerMdp)).perform(typeText("mdptest"));
        onView(withId(R.id.buttonValiderCompte)).perform(click());
        onView(withId(R.id.editTextCreerEmail)).check(matches(withText("ça fonctionne pas !")));
        onView(withId(R.id.editTextCreerMdp)).check(matches(withText("mdptest")));
        onView(withId(R.id.editTextCreerEmail)).perform(clearText());
        onView(withId(R.id.editTextCreerMdp)).perform(clearText());

        onView(withId(R.id.editTextCreerEmail)).perform(closeSoftKeyboard());
        pressBack();
        onView(withId(R.id.link_new_account)).check(matches(withText("Inscrivez-vous !")));
    }
    @Test
    public void testCreationDeCompte() {
        onView(withId(R.id.link_new_account)).check(matches(withText("Inscrivez-vous !")));
        onView(withId(R.id.link_new_account)).perform(click());

        onView(withId(R.id.editTextCreerEmail)).perform(typeText("test@mail.com"));
        onView(withId(R.id.editTextCreerMdp)).perform(typeText("mdptest"));
        onView(withId(R.id.editTextCreerMdp)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValiderCompte)).perform(click());

        onView(withId(R.id.link_new_account)).check(matches(withText("Inscrivez-vous !")));
    }
    @Test
    public void testConnexionAvecIdentifiant() {
        onView(withId(R.id.email)).perform(typeText("test@fn.fr"));
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText("testFaux@mail.com"));
        onView(withId(R.id.password)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.password)).perform(typeText("faux"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email)).perform(typeText("test@fn.fr"));
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.password)).perform(typeText("test"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        onView(withId(R.id.textBienvenue)).check(matches(withText("Bienvenue sur Finances&Nous")));
        onView(withId(R.id.textNewsTitre)).check(matches(withText("QUOI DE NEUF ?")));
        //onView(withText("L'email ou le mot de passe est incorrect")).check(matches(isDisplayed()));
        //onView(withId(R.id.link_new_account)).check(matches(withText("Inscrivez-vous !")));
    }
}