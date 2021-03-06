package TestUnitaire;

import android.app.Activity;
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
import com.example.pcportablevidjay.financesnous.Magasin_Activity;
import com.example.pcportablevidjay.financesnous.R;

import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import classes.Depense;
import classes.Magasin;
import classes.MyDBHelper;
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
public class TestFonctionnelPourMagasins {
    MyDBHelper dpTest = new MyDBHelper();
    @Rule
    public ActivityTestRule<Magasin_Activity> mactivityrule = new ActivityTestRule<>(Magasin_Activity.class);
    @Test
    public void creationDeMagasins() {
        onView(withId(R.id.buttonValider)).perform(click());
        onView(withId(R.id.editTextNomMag)).perform(typeText("MagasinsTest"));
        onView(withId(R.id.editTextNomMag)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValider)).perform(click());
        onView(withId(R.id.editTextAdrMag)).perform(typeText("rue des faux magasins"));
        onView(withId(R.id.editTextCPMag)).perform(typeText("4152"));
        onView(withId(R.id.editTextVilleMag)).perform(typeText("EspressoCity"));
        onView(withId(R.id.editTextSiteMag)).perform(typeText("pasDeSite"));
        onView(withId(R.id.editTextTelMag)).perform(typeText("067069835"));
        onView(withId(R.id.editTextTelMag)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValider)).perform(click());
        onView(withId(R.id.editTextTelMag)).perform(clearText());
        onView(withId(R.id.editTextTelMag)).perform(typeText("0670698353"));
        onView(withId(R.id.editTextTelMag)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValider)).perform(click());
        onView(withId(R.id.editTextSiteMag)).perform(clearText());
        onView(withId(R.id.editTextSiteMag)).perform(typeText("www.pasDeSite.com"));
        onView(withId(R.id.editTextSiteMag)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValider)).perform(click());
        onView(withId(R.id.editTextCPMag)).perform(clearText());
        onView(withId(R.id.editTextCPMag)).perform(typeText("41520"));
        onView(withId(R.id.editTextCPMag)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonValider)).perform(click());
    }
}