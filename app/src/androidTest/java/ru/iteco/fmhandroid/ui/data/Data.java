package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.app.Activity;
import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;


public class Data {

    public Data() {
    }

    public static class AuthDate {
        private final String login;
        private final String password;

        public AuthDate(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthDate validUser() {
        return new AuthDate("login2", "password2");
    }

    public static AuthDate invalidUser() {
        return new AuthDate("LOGIN", "PASS");
    }

    public static String getRandomString(String[] arr, int length) {
        Random random = new Random();
        String randomString = arr[random.nextInt(arr.length)];
        for (int i = 1; i < length; i++) {
            randomString = randomString.concat(arr[random.nextInt(arr.length)]);
        }
        return randomString;
    }

    public static String getRandomNewsCategory() {
        String[] category = {
                "Объявление",
                "День рождения",
                "Зарплата",
                "Профсоюз",
                "Праздник",
                "Массаж",
                "Благодарность",
                "Нужна помощь"
        };
        return getRandomString(category, 1);
    }

    public static Matcher<View> getElementMatchAtPosition(final Matcher<View> matcher,
                                                          final int position) {
        return new BaseMatcher<View>() {
            int counter = 0;

            @Override
            public boolean matches(final Object item) {
                if (matcher.matches(item)) {
                    if (counter == position) {
                        counter++;
                        return true;
                    }
                    counter++;
                }
                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Element at hierarchy position " + position);
            }
        };
    }

    public static <T extends Activity> T getActivity(ActivityScenarioRule<T> activityScenarioRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityScenarioRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }

    public static ViewInteraction checkToast(View decorView, String text) {
        return onView(withText(text)).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    public static ViewInteraction checkPopupWithText(ActivityScenarioRule activityScenarioRule,
                                                     String text) {
        return onView(withText(text)).inRoot(withDecorView(not(is(getActivity(activityScenarioRule)
                .getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public static ViewAction waitForView(int viewId, long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait up to " + timeout + " milliseconds for the viewId" + viewId +
                        " view to become visible";
            }

            @Override
            public void perform(UiController uiController, View view) {
                long endTime = System.currentTimeMillis() + timeout;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (withId(viewId).matches(child) && child.isShown()) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException("Waited " + timeout + " milliseconds"))
                        .build();
            }
        };
    }
}
