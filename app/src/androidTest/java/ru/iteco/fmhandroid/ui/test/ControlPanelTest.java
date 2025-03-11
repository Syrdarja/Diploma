package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.Data.checkPopupWithText;
import static ru.iteco.fmhandroid.ui.data.Data.validUser;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AppBarPanel;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.ControlPanel;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    ControlPanel controlPanel = new ControlPanel();

    AppBarPanel appBarPanel = new AppBarPanel();

    @Before
    public void appSetUp() {
        try {
            authorizationPage.waitLoginButton();
            authorizationPage.login(
                    validUser().getLogin(),
                    validUser().getPassword()
            );
            mainPage.waitAllNews();
        } catch (PerformException e) {
            mainPage.waitAllNews();
        }
        mainPage.getAllNewsButton().perform(click());
        newsPage.waitNewsEditButton();
        newsPage.getNewsEditButton().perform(click());
        controlPanel.waitNewsCreateButton();
    }

    @DisplayName("Проверка отображения элементов экрана на странице 'Панель управления'")
    @Test
    public void displayingElementsControlPanelTest() {
        appBarPanel.checkAppBar();
        controlPanel.getControlPanelTitle().check(matches(isDisplayed()));
        controlPanel.getSortNewsMaterialButton().check(matches(isDisplayed()));
        controlPanel.getFilterNewsMaterialButton().check(matches(isDisplayed()));
        controlPanel.getAddNewsImageView().check(matches(isDisplayed()));
        controlPanel.getNewsList().check(matches(isDisplayed()));
    }

    @DisplayName("Сортировка  новостей на странице 'Панель управлени' ")
    @Test
    public void sortingNewsControlPanelTest() {
        controlPanel.getSortNewsMaterialButton().perform(click());
        controlPanel.getControlPanelTitle().check(matches(isDisplayed()));

    }

    @DisplayName("Проверка разворачивания новости по нажатию на новость на странице Панель управления ")
    @Test
    public void viewingNewsControlPanelTest() throws InterruptedException {
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        newsPage.getNewsItemDescription().check(matches(withEffectiveVisibility(VISIBLE)));
    }

    @DisplayName("Создание и удаление новости на странице Панель управления ")
    @Test
    public void creationAndDeletingNewsInControlPanelTest() throws InterruptedException {
        String newsTitle = "Должен быть заголовок";
        controlPanel.getAddNewsImageView().perform(click());
        controlPanel.getNewsItemCategoryTextAutoCompleteTextView().perform(click());
        controlPanel.getNewsItemTitleTextInputEditText().perform(click(),
                clearText(), replaceText(newsTitle));
        controlPanel.getButtonDateCreatingNews().perform(click());
        controlPanel.getControlPanelElementsButtonOk().perform(click());
        controlPanel.getButtonTimeCreatingNews().perform(click());
        controlPanel.getControlPanelElementsButtonOk().perform(click());
        controlPanel.getDescriptionCreatingNews().perform(replaceText("Новое объявление"),
                closeSoftKeyboard());
        controlPanel.getSaveButton().perform(scrollTo(), click());
        newsPage.getNewsItemTitle().check(matches(withText(newsTitle)));
        controlPanel.waitNewsItem();
        Thread.sleep(1000);
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click())).
        check(matches(hasDescendant(withText(newsTitle))));
        controlPanel.getDeleteNewsItemImageView().perform(click());
        checkPopupWithText(activityScenarioRule,
                "Вы уверены, что хотите безвозвратно удалить документ? " +
                        "Данные изменения нельзя будет отменить в будущем.");
        checkPopupWithText(activityScenarioRule, "OK").perform(click());
    }

    @DisplayName("Редактирование новости на странице Панель управления ")
    @Test
    public void editNewsControlPanelTest() throws InterruptedException {
        String title = "Новый заголовок";
        String newTitle = "Редактирование заголовка";
        controlPanel.getAddNewsImageView().perform(click());
        controlPanel.getNewsItemCategoryTextAutoCompleteTextView().perform(click());
        controlPanel.getNewsItemTitleTextInputEditText().perform(click(),
                clearText(), replaceText(title));
        controlPanel.getButtonDateCreatingNews().perform(click());
        controlPanel.getControlPanelElementsButtonOk().perform(click());
        controlPanel.getButtonTimeCreatingNews().perform(click());
        controlPanel.getControlPanelElementsButtonOk().perform(click());
        controlPanel.getDescriptionCreatingNews().perform(replaceText("Новое объявление"),
                closeSoftKeyboard());
        controlPanel.getSaveButton().perform(scrollTo(), click());
        newsPage.getNewsItemTitle().check(matches(withText(title)));
        controlPanel.getEditNewsItemImageView().perform(click());
        controlPanel.getNewsItemTitleTextInputEditText().perform(click(),
                clearText(), replaceText(newTitle));
        controlPanel.getSaveButton().perform(scrollTo(), click());
        controlPanel.waitNewsItem();
        Thread.sleep(1000);
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click())).
                check(matches(hasDescendant(withText(newTitle))));
        controlPanel.getDeleteNewsItemImageView().perform(click());
        checkPopupWithText(activityScenarioRule,
                "Вы уверены, что хотите безвозвратно удалить документ? " +
                        "Данные изменения нельзя будет отменить в будущем.");
        checkPopupWithText(activityScenarioRule, "OK").perform(click());

    }

    @DisplayName("Отмена создания новости на странице Панель управления")
    @Test
    public void createNewsCancelTest() {
        controlPanel.getAddNewsImageView().perform(click());
        controlPanel.getCancelButton().perform(click());
        checkPopupWithText(activityScenarioRule, "Изменения не будут сохранены." +
                " Вы действительно хотите выйти?");
        checkPopupWithText(activityScenarioRule, "OK").perform(click());
        controlPanel.waitNewsCreateButton();
        controlPanel.getControlPanelTitle().check(matches(isDisplayed()));
    }

    @DisplayName("Попытка создания новости с пустыми полями на странице Панель управления")
    @Test
    public void createNewsFailForEmptyFieldsTest() {
        controlPanel.getAddNewsImageView().perform(click());
        controlPanel.getSaveButton().perform(scrollTo(), click());
        checkPopupWithText(activityScenarioRule, "Заполните пустые поля");
    }

    @DisplayName("Отмена редактирования новости на странице Панель управления")
    @Test
    public void editNewsCancelTest() throws InterruptedException {
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        controlPanel.getEditNewsItemImageView().perform(click());
        controlPanel.getCancelButton().perform(click());
        checkPopupWithText(activityScenarioRule, "Изменения не будут сохранены." +
                " Вы действительно хотите выйти?");
        checkPopupWithText(activityScenarioRule, "OK").perform(click());
        controlPanel.waitNewsCreateButton();
        controlPanel.getControlPanelTitle().check(matches(isDisplayed()));
    }

    @DisplayName("Смена статуса новости на странице Панель управления")
    @Test
    public void statusChangeNewsTest() throws InterruptedException {
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        controlPanel.getEditNewsItemImageView().perform(click());
        controlPanel.getSwitcher().perform(click());
        controlPanel.getSaveButton().perform(scrollTo(), click());
        controlPanel.waitNewsItem();
        Thread.sleep(1000);
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click())).
                check(matches(hasDescendant(withText("Не активна"))));
    }

    @DisplayName("Фильтрация новостей по статусу 'Не активна' на странице Панель управления")
    @Test
    public void filterNewsByStatusActiveTest() {
        controlPanel.getFilterNewsMaterialButton().perform(click());
        controlPanel.getFilterNewsActiveMaterialCheckBox().perform(click());
        controlPanel.getFilterButton().perform(click());
    }

    @DisplayName("Фильтрация новостей по статусу 'Активна' на странице Панель управления")
    @Test
    public void filterNewsByStatusInactiveTest() {
        controlPanel.getFilterNewsMaterialButton().perform(click());
        controlPanel.getFilterNewsInactiveMaterialCheckBox().perform(click());
        controlPanel.getFilterButton().perform(click());
    }

}






