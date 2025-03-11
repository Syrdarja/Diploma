package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AppBarPanel;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.QuotePage;

@RunWith(AllureAndroidJUnit4.class)

public class AppBarPanelTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBarPanel appBarPanel = new AppBarPanel();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    QuotePage quotePage = new QuotePage();

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

    @DisplayName("Проверка отображения элементов панели на главной странице")
    @Test
    public void DisplayingElementsAppBarTest() {
        appBarPanel.checkAppBar();
    }

    @DisplayName("Переход на страницы приложения из меню AppBar")
    @Test
    public void NavigationElementsAppBarMenuTest() {
        appBarPanel.navigateToNews();
        newsPage.waitNewsEditButton();
        newsPage.getNewsBlockText().check(matches(isDisplayed()));
        appBarPanel.navigateToMain();
        mainPage.getNewsBlockText().check(matches(isDisplayed()));
        appBarPanel.navigateToAbout();
        aboutPage.waitCopyright();
        aboutPage.getVersionNumber().check(matches(isDisplayed()));
    }

    @DisplayName("Переход на страницу 'Цитаты' из меню в AppBar на главной странице")
    @Test
    public void goToQuotePageTest() {
        appBarPanel.navigateToQuote();
        quotePage.waitQuoteItem();
        quotePage.getQuoteTitle().check(matches(isDisplayed()));
    }

    @DisplayName("Выход из аккаунта с помощью панели AppBar")
    @Test
    public void logoutTest() {
        appBarPanel.logout();
        authorizationPage.waitLoginButton();
        authorizationPage.getAuthHeader().check(matches(isDisplayed()));
    }
}
