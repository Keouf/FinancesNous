package TestUnitaire;

import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
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
public class TestFonctionnelPourDepense {
    @Rule
    public ActivityTestRule<Depense_Activity> mactivityrule = new ActivityTestRule<>(Depense_Activity.class);
    @Test
    public void creationDepense() {
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
    }
}