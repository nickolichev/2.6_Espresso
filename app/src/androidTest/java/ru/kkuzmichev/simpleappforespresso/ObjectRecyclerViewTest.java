package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ObjectRecyclerViewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }
    @Test
    public void checkingObjectTypeTest() {
        ViewInteraction navigationButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        navigationButton.check(matches(isDisplayed()));
        navigationButton.perform(click());

        ViewInteraction galleryButton = onView(
                allOf(withId(androidx.navigation.ui.R.id.design_menu_item_text), withText("Gallery"),
                        withParent(allOf(withId(R.id.nav_gallery),
                                withParent(withId(androidx.navigation.ui.R.id.design_navigation_view)))),
                        isDisplayed()));
        galleryButton.check(matches(isDisplayed()));
        galleryButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycle_view),
                        withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.check(CustomViewAssertions.isRecyclerView());
    }
}
