//Тест проверки вопросов и ответов в разделе "Вопросы о важном"

package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POM.MainPage;

@RunWith(Parameterized.class)
    public class QuestionsAboutImportantTest {
    private final int tabindex; //индекс локатора
    private final String question; //вопрос
    private final String answer; //ответ

    public QuestionsAboutImportantTest(int tabindex, String question, String answer) {
        this.tabindex = tabindex;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }


        @Rule
        public DriverFactory factory = new DriverFactory();

        @Test
        public void checkText() {
            WebDriver driver = factory.getDriver();
            var mainPage = new MainPage(driver);

            //Открыли главную страницу, согласились с cookie
            mainPage.openPage();

            //Прокрутили страницу до списка "Вопросы о важном"
            mainPage.scrollIntoViewAccordionElement();

            //Кликаем по вопросу для расскрытия поля с ответом
            mainPage.clickAndGetTextQuestion(tabindex);

            //Проверяем, что текст вопроса и ответа отображаемого на экране, соответствует эталонному из @Parameterized.Parameters

            String currentQuestion = mainPage.clickAndGetTextQuestion(tabindex);
            Assert.assertEquals("Проверьте, что текст вопроса на экране, соответствует эталонному", question, currentQuestion);

            String currentAnswer = mainPage.getResponseTextFromScreen(tabindex);
            Assert.assertEquals("Проверьте, что текст ответа на экране, соответствует эталонному", answer, currentAnswer);
        }

}
