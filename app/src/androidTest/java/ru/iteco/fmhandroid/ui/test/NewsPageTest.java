package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.Data.getRandomNewsCategory;
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
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();

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
    }

    @DisplayName("Проверка отображения элементов экрана на странице 'Новости'")
    @Test
    public void DisplayingElementsNewsPageTest() {
        newsPage.checkNewsPage();
    }

    @DisplayName("Проверка разворачивания новости по нажатию на новость")
    @Test
    public void expandNewsItemTest() throws InterruptedException {
        newsPage.getNewsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        newsPage.getNewsItemDescription().check(matches(withEffectiveVisibility(VISIBLE)));
    }

    @DisplayName("Отрытие окна 'Фильтровать новости'")
    @Test
    public void newsSortingTest() {
        newsPage.getNewsBlockText().perform(click());
        newsPage.getNewsSortButton().perform(click()).check(matches(isDisplayed()));
    }

    @DisplayName("Фильтрация новостей без указания категории")
    @Test
    public void filteringNewsNoCategoryTest() {
        newsPage.getNewsBlockText().perform(click());
        newsPage.getNewsFilterButton().perform(click());
        newsPage.getNewsElementsTitleFilterNews().check(matches(isDisplayed())).perform(click());
        newsPage.getNewsElementsButtonCategoryFilter().perform(click());
    }

    @DisplayName("Выбор даты фильтра новостей текущий день")
    @Test
    public void filteringNewsCertainPeriodTimeTest() {
        newsPage.getNewsBlockText().perform(click());
        newsPage.getNewsFilterButton().perform(click());
        newsPage.getNewsElementsTitleFilterNews().check(matches(isDisplayed())).perform(click());
        newsPage.getNewsElementsButtonDateStart().perform(click());
        newsPage.getNewsElementsButtonOkDateStart().perform(click());
        newsPage.getNewsElementsButtonDateEnd().perform(click());
        newsPage.getNewsElementsButtonOkDateStart().perform(click());
        newsPage.getNewsElementsButtonCategoryFilter().perform(click());
    }

    @DisplayName("Фильтрация новостей c указания категории")
    @Test
    public void filteringNewsCategoryTest() {
        String category = getRandomNewsCategory();
        newsPage.getNewsBlockText().perform(click());
        newsPage.getNewsFilterButton().perform(click());
        newsPage.getCheckableImageButton().perform(click());
        newsPage.getEditTextCategory().perform(replaceText(category));
        newsPage.getEditTextCategory().check(matches(withText(category)));

    }

    @DisplayName("Отмена фильтрации новостей по заданным параметрам")
    @Test
    public void cancelNewsFilteringTest() {
        String category = getRandomNewsCategory();
        newsPage.getNewsBlockText().perform(click());
        newsPage.getNewsFilterButton().perform(click());
        newsPage.getCheckableImageButton().perform(click());
        newsPage.getEditTextCategory().perform(replaceText(category));
        newsPage.getNewsCancelButton().perform(click());
        newsPage.getNewsBlockText().check(matches(isDisplayed()));
    }
}