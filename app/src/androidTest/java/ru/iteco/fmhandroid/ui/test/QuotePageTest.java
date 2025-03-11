package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
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
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.QuotePage;

@RunWith(AllureAndroidJUnit4.class)
public class QuotePageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBarPanel appBarPanel = new AppBarPanel();
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
        appBarPanel.navigateToQuote();
        quotePage.waitQuoteItem();
    }

    @DisplayName("Проверка отображения элементов на странице цитат")
    @Test
    public void DisplayingElementsOnAPageTest() {
        quotePage.checkQuotePage();
    }

    @DisplayName("Открытие цитаты на странице 'Цитаты'")
    @Test
    public void expandQuoteItemTest() throws InterruptedException {
        quotePage.getQuoteItemsList().perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(200);
        quotePage.getQuoteItemDescription().check(matches(withEffectiveVisibility(VISIBLE)));
    }

}
