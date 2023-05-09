package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class IntentTest {
//    @Rule
//    public ActivityTestRule<MainActivity> activityTestRule =
//            new ActivityTestRule<>(MainActivity.class);
    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(MainActivity.class);

    @Test
    public void checkingSettingsElementAndClickTest() {
        ViewInteraction moreOptionButton = onView(
                allOf(withContentDescription("More options"),
                        isDisplayed()));
        moreOptionButton.perform(click());

        ViewInteraction menuSettings = onView(
                allOf(withId(androidx.recyclerview.R.id.title), withText("Settings"),
                        withParent(withParent(withId(androidx.constraintlayout.widget.R.id.content))),
                        isDisplayed()));
        menuSettings.check(matches(isDisplayed()));
        menuSettings.check(matches(withText("Settings")));
//        Intents.init();
        menuSettings.perform(click());
        intended(hasData("https://google.com"));
        intended(hasAction(Intent.ACTION_VIEW));
//        Intents.release();
    }
}

