package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.Matchers.not;
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
public class MainPageTest {
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
    }

    @DisplayName("Проверка отображения элементов на главной странице")
    @Test
    public void DisplayingElementsOnAPageTest() {
        mainPage.checkMainPage();
    }

    @DisplayName("Сворачивание и открытие блока 'новости' на главной странице приложения")
    @Test
    public void collapseExpandNewsBlockTest() {
        mainPage.getExpandNewsButton().perform(click());
        mainPage.getAllNewsButton().check(matches(not(isDisplayed())));
        mainPage.getExpandNewsButton().perform(click());
        mainPage.getAllNewsButton().check(matches(isDisplayed()));
    }

    @DisplayName("Переход по ссылке 'Все новости' с главной страницы приложения")
    @Test
    public void goToAllNewsTest() {
        mainPage.getAllNewsButton().perform(click());
        newsPage.waitNewsEditButton();
        newsPage.getNewsBlockText().check(matches(isDisplayed()));
    }


    @DisplayName("Открытие описания новости по нажатию на новость")
    @Test
    public void expandNewsItemTest() throws InterruptedException {
        mainPage.getNewsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        mainPage.getNewsItemDescription().check(matches(withEffectiveVisibility(VISIBLE)));
    }
}