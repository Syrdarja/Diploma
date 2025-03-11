package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.Data.getElementMatchAtPosition;
import static ru.iteco.fmhandroid.ui.data.Data.waitForView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    private final ViewInteraction newsBlock = onView(withId(R.id.container_list_news_include));
    private final ViewInteraction newsBlockText = onView(withText("Новости"));
    private final ViewInteraction newsSortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction newsFilterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction newsEditButton = onView(withId(R.id.edit_news_material_button));
    private final ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    private final ViewInteraction newsCancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction editTextCategory = onView(withId(
            R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction checkableImageButton = onView(allOf(withId(
            R.id.text_input_end_icon), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
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
    private final ViewInteraction newsElementsButtonCategoryFilter = onView(withId(
            R.id.filter_button));
    private final ViewInteraction newsElementsButtonDateStart = onView(
            withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction newsElementsButtonOkDateStart = onView(
            withId(android.R.id.button1));
    private final ViewInteraction newsElementsTitleFilterNews = onView(withId(
            R.id.filter_news_title_text_view));
    private final ViewInteraction newsElementsButtonDateEnd = onView(withId(
            R.id.news_item_publish_date_end_text_input_edit_text));

    public ViewInteraction getNewsBlock() {
        return newsBlock;
    }

    public ViewInteraction getEditTextCategory() {
        return editTextCategory;
    }

    public ViewInteraction getNewsCancelButton() {
        return newsCancelButton;
    }

    public ViewInteraction getNewsBlockText() {
        return newsBlockText;
    }

    public ViewInteraction getCheckableImageButton() {
        return checkableImageButton;
    }

    public ViewInteraction getNewsSortButton() {
        return newsSortButton;
    }

    public ViewInteraction getNewsFilterButton() {
        return newsFilterButton;
    }

    public ViewInteraction getNewsEditButton() {
        return newsEditButton;
    }

    public ViewInteraction getNewsList() {
        return newsList;
    }

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

    public ViewInteraction getNewsElementsButtonCategoryFilter() {
        return newsElementsButtonCategoryFilter;
    }

    public ViewInteraction getNewsElementsButtonDateStart() {
        return newsElementsButtonDateStart;
    }

    public ViewInteraction getNewsElementsButtonOkDateStart() {
        return newsElementsButtonOkDateStart;
    }

    public ViewInteraction getNewsElementsTitleFilterNews() {
        return newsElementsTitleFilterNews;
    }

    public ViewInteraction getNewsElementsButtonDateEnd() {
        return newsElementsButtonDateEnd;
    }

    public void waitNewsEditButton() {
        onView(isRoot()).perform(waitForView(R.id.edit_news_material_button, 10000));
    }

    public void checkNewsItem() {
        getCategoryIcon().check(matches(isDisplayed()));
        getNewsItemTitle().check(matches(isDisplayed()));
        getExpandNewsItemButton().check(matches(isDisplayed()));
        getNewsItemDate().check(matches(isDisplayed()));
    }

    public void checkNewsPage() {
        AppBarPanel appBarPanel = new AppBarPanel();

        appBarPanel.checkAppBar();
        getNewsBlock().check(matches(isDisplayed()));
        getNewsBlockText().check(matches(isDisplayed()));
        getNewsSortButton().check(matches(isDisplayed()));
        getNewsFilterButton().check(matches(isDisplayed()));
        getNewsEditButton().check(matches(isDisplayed()));
        getNewsList().check(matches(isDisplayed()));
        checkNewsItem();
    }

}