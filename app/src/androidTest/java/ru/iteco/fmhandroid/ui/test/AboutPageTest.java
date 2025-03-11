package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.Data.validUser;

import android.content.Intent;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.intent.Intents;
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

@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AboutPage aboutPage = new AboutPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
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
        appBarPanel.navigateToAbout();
        aboutPage.waitCopyright();
    }

    @DisplayName("Проверка отображения элементов на странице 'О приложении'")
    @Test
    public void DisplayingElementsOnAPageTest() {
        aboutPage.checkAboutPage();
    }

    @DisplayName("Переход по ссылке 'Политика конфиденциальности' на странице 'О приложении'")
    @Test
    public void PrivacyPolicyLinkTest() {
        Intents.init();
        aboutPage.getPolicyLink().perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        Intents.release();
    }

    @DisplayName("Переход по ссылке 'Пользовательское соглашение' на странице 'О приложении'")
    @Test
    public void TermsOfUseLinkTest() {
        Intents.init();
        aboutPage.getTermsLink().perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        Intents.release();
    }

    @DisplayName("Использование кнопки навигации 'Назад'  со страницы 'О приложении'")
    @Test
    public void goBackButtonTest() {
        aboutPage.getBackButton().perform(click());
        mainPage.waitAllNews();
        mainPage.getNewsBlockText().check(matches(isDisplayed()));
    }
}