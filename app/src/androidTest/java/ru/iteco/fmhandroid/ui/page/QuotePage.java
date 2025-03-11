package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.Data.getElementMatchAtPosition;
import static ru.iteco.fmhandroid.ui.data.Data.waitForView;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class QuotePage {
    private final ViewInteraction quoteTitle = onView(withId(R.id.our_mission_title_text_view));
    private final ViewInteraction quoteItemsList = onView(withId(R.id.our_mission_item_list_recycler_view));

    private final ViewInteraction quoteItemIcon = onView(getElementMatchAtPosition(
            withId(R.id.our_mission_item_image_view), 0));
    private final ViewInteraction quoteItemTitle = onView(getElementMatchAtPosition(
            withId(R.id.our_mission_item_title_text_view), 0));
    private final ViewInteraction expandQuoteItemButton = onView(getElementMatchAtPosition(
            withId(R.id.our_mission_item_open_card_image_button), 0));
    private final ViewInteraction quoteItemDescription = onView(getElementMatchAtPosition(withId(
            R.id.our_mission_item_description_text_view), 0));

    public ViewInteraction getQuoteTitle() {
        return quoteTitle;
    }

    public ViewInteraction getQuoteItemsList() {
        return quoteItemsList;
    }

    public ViewInteraction getQuoteItemIcon() {
        return quoteItemIcon;
    }

    public ViewInteraction getQuoteItemTitle() {
        return quoteItemTitle;
    }

    public ViewInteraction getExpandQuoteItemButton() {
        return expandQuoteItemButton;
    }

    public ViewInteraction getQuoteItemDescription() {
        return quoteItemDescription;
    }

    public void waitQuoteItem() {
        onView(isRoot()).perform(waitForView(R.id.our_mission_item_title_text_view, 10000));
    }

    public void checkQuoteItem() {
        getQuoteItemIcon().check(matches(isDisplayed()));
        getQuoteItemTitle().check(matches(isDisplayed()));
        getExpandQuoteItemButton().check(matches(isDisplayed()));
    }

    public void checkQuotePage() {
        AppBarPanel appBarPanel = new AppBarPanel();

        appBarPanel.checkAppBar();
        getQuoteTitle().check(matches(isDisplayed()));
        getQuoteItemsList().check(matches(isDisplayed()));
        checkQuoteItem();
    }
}