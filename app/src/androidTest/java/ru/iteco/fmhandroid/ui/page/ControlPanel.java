package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.Data.getElementMatchAtPosition;
import static ru.iteco.fmhandroid.ui.data.Data.waitForView;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanel {

    private final ViewInteraction controlPanelTitle = onView(withText("Панель \n управления"));

    private final ViewInteraction deleteNewsItemImageView = onView(getElementMatchAtPosition
            (withId(R.id.delete_news_item_image_view), 0));

    private final ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));

    private final ViewInteraction sortNewsMaterialButton = onView(withId(
            R.id.sort_news_material_button));

    private final ViewInteraction controlPanelElementsButtonOk = onView(
            withId(android.R.id.button1));

    private final ViewInteraction editNewsItemImageView = onView(getElementMatchAtPosition
            (withId(R.id.edit_news_item_image_view), 0));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction newsItemTitleTextInputEditText = onView(withId
            (R.id.news_item_title_text_input_edit_text));

    private final ViewInteraction saveButton = onView(withId(R.id.save_button));

    private final ViewInteraction switcher = onView(withId(R.id.switcher));

    private final ViewInteraction filterNewsMaterialButton = onView(withId(
            R.id.filter_news_material_button));

    private final ViewInteraction filterNewsInactiveMaterialCheckBox = onView(
            withId(R.id.filter_news_inactive_material_check_box));

    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));

    private final ViewInteraction filterNewsActiveMaterialCheckBox = onView(
            withId(R.id.filter_news_active_material_check_box));

    private final ViewInteraction addNewsImageView = onView(withId(R.id.add_news_image_view));

    private final ViewInteraction newsItemCategoryTextAutoCompleteTextView = onView(
            withId(R.id.news_item_category_text_auto_complete_text_view));


    private final ViewInteraction buttonDateCreatingNews = onView(withId(
            R.id.news_item_publish_date_text_input_edit_text));

    private final ViewInteraction buttonTimeCreatingNews = onView(withId(
            R.id.news_item_publish_time_text_input_edit_text));

    private final ViewInteraction descriptionCreatingNews = onView(withId(
            R.id.news_item_description_text_input_edit_text));

    public final ViewInteraction newsItemPublishedTextView = onView(withText("НЕ АКТИВНА"));

    public ViewInteraction getNewsItemPublishedTextView() {
        return newsItemPublishedTextView;
    }

    public ViewInteraction getControlPanelTitle() {
        return controlPanelTitle;
    }

    public ViewInteraction getDeleteNewsItemImageView() {
        return deleteNewsItemImageView;
    }

    public ViewInteraction getSortNewsMaterialButton() {
        return sortNewsMaterialButton;
    }

    public ViewInteraction getCancelButton() {
        return cancelButton;
    }

    public ViewInteraction getControlPanelElementsButtonOk() {
        return controlPanelElementsButtonOk;
    }

    public ViewInteraction getNewsList() {
        return newsList;
    }

    public ViewInteraction getEditNewsItemImageView() {
        return editNewsItemImageView;
    }

    public ViewInteraction getNewsItemTitleTextInputEditText() {
        return newsItemTitleTextInputEditText;
    }

    public ViewInteraction getSaveButton() {
        return saveButton;
    }

    public ViewInteraction getSwitcher() {
        return switcher;
    }

    public ViewInteraction getFilterNewsMaterialButton() {
        return filterNewsMaterialButton;
    }

    public ViewInteraction getFilterNewsInactiveMaterialCheckBox() {
        return filterNewsInactiveMaterialCheckBox;
    }

    public ViewInteraction getFilterButton() {
        return filterButton;
    }

    public ViewInteraction getFilterNewsActiveMaterialCheckBox() {
        return filterNewsActiveMaterialCheckBox;
    }

    public ViewInteraction getAddNewsImageView() {
        return addNewsImageView;
    }

    public ViewInteraction getNewsItemCategoryTextAutoCompleteTextView() {
        return newsItemCategoryTextAutoCompleteTextView;
    }

    public ViewInteraction getButtonDateCreatingNews() {
        return buttonDateCreatingNews;
    }

    public ViewInteraction getButtonTimeCreatingNews() {
        return buttonTimeCreatingNews;
    }

    public ViewInteraction getDescriptionCreatingNews() {
        return descriptionCreatingNews;
    }

    public void waitNewsCreateButton() {
        onView(isRoot()).perform(waitForView(R.id.add_news_image_view, 10000));
    }

    public void waitNewsItem() {
        onView(isRoot()).perform(waitForView(R.id.news_item_published_text_view, 10000));
    }
}
