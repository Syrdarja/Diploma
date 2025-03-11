package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.Data.getElementMatchAtPosition;
import static ru.iteco.fmhandroid.ui.data.Data.waitForView;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {
    private final ViewInteraction newsBlock = onView(withId(R.id
            .container_list_news_include_on_fragment_main));
    private final ViewInteraction newsBlockText = onView(withText("Новости"));
    private final ViewInteraction expandNewsButton = onView(allOf(withId(R.id.expand_material_button),
            withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main)))));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    private final ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    private final ViewInteraction categoryIcon = onView(getElementMatchAtPosition(
            withId(R.id.category_icon_image_view), 0));
    private final ViewInteraction newsItemTitle = onView(getElementMatchAtPosition(
            withId(R.id.news_item_title_text_view), 0));
    private final ViewInteraction expandNewsItemButton = onView(getElementMatchAtPosition(
            withId(R.id.view_news_item_image_view), 0));
    private final ViewInteraction newsItemDate = onView(getElementMatchAtPosition(
            withId(R.id.news_item_date_text_view), 0));
    private final ViewInteraction newsItemDescription = onView(getElementMatchAtPosition(
            withId(R.id.news_item_description_text_view), 0));


    public ViewInteraction getCategoryIcon() {
        return categoryIcon;
    }

    public ViewInteraction getNewsItemTitle() {
        return newsItemTitle;
    }

    public ViewInteraction getExpandNewsItemButton() {
        return expandNewsItemButton;
    }

    public ViewInteraction getNewsItemDate() {
        return newsItemDate;
    }

    public ViewInteraction getNewsItemDescription() {
        return newsItemDescription;
    }

    public ViewInteraction getNewsBlock() {
        return newsBlock;
    }

    public ViewInteraction getNewsBlockText() {
        return newsBlockText;
    }

    public ViewInteraction getExpandNewsButton() {
        return expandNewsButton;
    }

    public ViewInteraction getAllNewsButton() {
        return allNewsButton;
    }

    public ViewInteraction getNewsList() {
        return newsList;
    }

    public void waitAllNews() {
        onView(isRoot()).perform(waitForView(R.id.all_news_text_view, 10000));
    }

    public void checkNewsItem() {
        getCategoryIcon().check(matches(isDisplayed()));
        getNewsItemTitle().check(matches(isDisplayed()));
        getExpandNewsItemButton().check(matches(isDisplayed()));
        getNewsItemDate().check(matches(isDisplayed()));
    }

    public void checkMainPage() {
        AppBarPanel appBarPanel = new AppBarPanel();
        appBarPanel.checkAppBar();
        getNewsBlock().check(matches(isDisplayed()));
        getNewsBlockText().check(matches(isDisplayed()));
        getExpandNewsButton().check(matches(isDisplayed()));
        getAllNewsButton().check(matches(isDisplayed()));
        getNewsList().check(matches(isDisplayed()));
        getNewsList().check(matches(hasChildCount(3)));
        checkNewsItem();
    }
}
