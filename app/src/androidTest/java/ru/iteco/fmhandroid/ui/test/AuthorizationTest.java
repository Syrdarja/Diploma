package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.Data.checkToast;
import static ru.iteco.fmhandroid.ui.data.Data.invalidUser;
import static ru.iteco.fmhandroid.ui.data.Data.validUser;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AppBarPanel;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    View decorView;
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    AppBarPanel appBarPanel = new AppBarPanel();

    @Before
    public void setUp() {

        activityScenarioRule.getScenario()
                .onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }


    @Before
    public void appSetUp() {
        try {
            authorizationPage.waitLoginButton();
        } catch (PerformException e) {
            mainPage.waitAllNews();
            appBarPanel.logout();
            authorizationPage.waitLoginButton();
        }
    }


    @Description("Вход с валидными данными пользователя")
    @Test
    public void authorizationValidDataTest() {
        authorizationPage.login(
                validUser().getLogin(),
                validUser().getPassword()
        );
        mainPage.waitAllNews();
        mainPage.getNewsBlockText().check(matches(isDisplayed()));
    }

    @DisplayName("Авторизация в приложении без ввода логина и пароля")
    @Test
    public void authorizationEmptyLoginAndPasswordTest() {
        authorizationPage.getLoginButton().perform(click());
        checkToast(decorView, "Логин и пароль не могут быть пустыми");
    }

    @DisplayName("Авторизация в приложении по валидному логину, пустому полю пароль")
    @Test
    public void authorizationEmptyPasswordTest() {
        authorizationPage.login(
                validUser().getLogin(),
                " "
        );
        checkToast(decorView, "Логин и пароль не могут быть пустыми");
    }

    @DisplayName("Авторизация в приложении по пустому полю логин, валидному паролю")
    @Test
    public void authorizationEmptyLoginTest() {
        authorizationPage.login(
                " ",
                validUser().getPassword()
        );
        checkToast(decorView, "Логин и пароль не могут быть пустыми");
    }

    @DisplayName("Авторизация в приложении по невалидному логину и паролю")
    @Test
    public void authorizationInvalidDataTest() {
        authorizationPage.login(
                invalidUser().getLogin(),
                invalidUser().getPassword()
        );
        checkToast(decorView, "Неверный логин или пароль");
    }

    @DisplayName("Авторизация в приложении по невалидному логину, валидному паролю")
    @Test
    public void authorizationInvalidLoginValidPasswordTest() {
        authorizationPage.login(
                invalidUser().getLogin(),
                validUser().getPassword()
        );
        checkToast(decorView, "Неверный логин или пароль");
    }

    @DisplayName("Авторизация в приложении по валидному логину, невалидному паролю")
    @Test
    public void authorizationValidLoginInvalidPasswordTest() {
        authorizationPage.login(
                validUser().getLogin(),
                invalidUser().getPassword()
        );
        checkToast(decorView, "Неверный логин или пароль");
    }

}
