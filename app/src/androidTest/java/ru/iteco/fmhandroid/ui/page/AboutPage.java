package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.Data.waitForView;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    private final ViewInteraction versionText = onView(withId(R.id.about_version_title_text_view));
    private final ViewInteraction versionNumber = onView(withId(
            R.id.about_version_value_text_view));
    private final ViewInteraction policyText = onView((withId(
            R.id.about_privacy_policy_label_text_view)));
    private final ViewInteraction policyLink = onView((withId(
            R.id.about_privacy_policy_value_text_view)));
    private final ViewInteraction termsText = onView((withId(
            R.id.about_terms_of_use_label_text_view)));
    private final ViewInteraction termsLink = onView((withId(
            R.id.about_terms_of_use_value_text_view)));
    private final ViewInteraction backButton = onView(withId(R.id.about_back_image_button));
    private final ViewInteraction copyright = onView(withId(R.id.trademark_image_view));

    public ViewInteraction getVersionText() {
        return versionText;
    }

    public ViewInteraction getVersionNumber() {
        return versionNumber;
    }

    public ViewInteraction getPolicyText() {
        return policyText;
    }

    public ViewInteraction getPolicyLink() {
        return policyLink;
    }

    public ViewInteraction getTermsText() {
        return termsText;
    }

    public ViewInteraction getTermsLink() {
        return termsLink;
    }

    public ViewInteraction getBackButton() {
        return backButton;
    }

    public ViewInteraction getCopyright() {
        return copyright;
    }

    public void waitCopyright() {
        onView(isRoot()).perform(waitForView(R.id.trademark_image_view, 10000));
    }

    public void checkAboutPage() {
        getVersionText().check(matches(isDisplayed()));
        getVersionNumber().check(matches(isDisplayed()));
        getPolicyText().check(matches(isDisplayed()));
        getPolicyLink().check(matches(isDisplayed()));
        getTermsText().check(matches(isDisplayed()));
        getTermsLink().check(matches(isDisplayed()));
        getBackButton().check(matches(isDisplayed()));
        getCopyright().check(matches(isDisplayed()));
    }
}