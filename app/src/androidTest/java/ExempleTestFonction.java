import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.DatePicker;

import com.example.pcportablevidjay.financesnous.Accueil_Activity;
import com.example.pcportablevidjay.financesnous.CreerCompte_Activity;
import com.example.pcportablevidjay.financesnous.Depense_Activity;
import com.example.pcportablevidjay.financesnous.Login_Activity;
import com.example.pcportablevidjay.financesnous.R;

import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Test;

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


public class ExempleTestFonction extends ActivityInstrumentationTestCase2<Login_Activity> {
    public ExempleTestFonction() {
        super(Login_Activity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Call the activity before each test.
        getActivity();
    }

    public void testNextActivity() {

        //onView(withId(R.id.btn_creer_compte)).check(matches(withText("Créer un compte")));
        onView(withId(R.id.link_new_account)).perform(click());

        onView(withId(R.id.editTextCreerEmail)).perform(typeText("ça fonctionne pas !"));
        onView(withId(R.id.editTextCreerMdp)).perform(typeText("mdptest"));
        //onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.editTextCreerEmail)).perform(clearText());
        onView(withId(R.id.editTextCreerMdp)).perform(clearText());

        onView(withId(R.id.editTextCreerEmail)).perform(closeSoftKeyboard());
        pressBack();

        /*creer un compte mail*/
        //onView(withId(R.id.editTextCreerEmail)).perform(typeText("test@mail.com"));
        //onView(withId(R.id.editTextCreerMdp)).perform(typeText("mdptest"));
        //onView(withId(R.id.button3)).perform(click());


        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Connexion")));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        //pressBack();
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //onView(withId(R.id.email_sign_in_button)).check(matches(withText("Connexion")));
        //onView(withId(R.id.email_sign_in_button)).perform(click());
        //onView(withId(R.id.drawer_layout)).perform(click());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.editTextMontant)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValiderDépense)).perform(click());
        onView(withId(R.id.editTextMontant)).perform(typeText("123"));
        onView(withId(R.id.editTextMontant)).perform(closeSoftKeyboard());

        onView(withId(R.id.spinner_domaine)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Alimentation"))).perform(click());

        onView(withId(R.id.spinner_enseigne)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Leclerc"))).perform(click());

        onView(withId(R.id.editText_date)).perform(click());
        pressBack();
        onView(withId(R.id.CBGarantie)).perform(click());
        onView(withId(R.id.CBGarantie)).perform(click());
        onView(withId(R.id.CBNoteDeFrais)).perform(click());
        onView(withId(R.id.CBNoteDeFrais)).perform(click());
        onView(withId(R.id.CBGarantie)).perform(click());
        onView(withId(R.id.CBNoteDeFrais)).perform(click());
        onView(withId(R.id.CBGarantie)).perform(click());
        onView(withId(R.id.CBNoteDeFrais)).perform(click());

        onView(withId(R.id.buttonValiderDépense)).perform(scrollTo(), click());

        //onView(withId(R.id.spinner_enseigne)).perform(pre);
        //onView(withId(R.id.buttonValider)).perform(click());

        //onView(withId(R.id.editTextMontant)).perform(clearText());

    }

}