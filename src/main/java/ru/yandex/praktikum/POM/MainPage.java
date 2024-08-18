package ru.yandex.praktikum.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

public class MainPage {
    private final WebDriver driver;

   // Список локаторов:
    //Локатор основного поля со списком "Вопросы о важном"
    private final By accordion = By.className("Home_FAQ__3uVm4");

    //Локатор верхней кнопки заказать
    private final By  topButtonOrder = By.xpath("//div[@class='Header_Header__214zg']//button[@class='Button_Button__ra12g']");

    //Локатор нижней кнопки заказать
    private final By  bottomButtonOrder = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

        public MainPage (WebDriver driver){
        this.driver = driver;
        }

        //Открытие главной страницы и принятие cookie
        public void openPage() {
        driver.get(EnvConfig.BASE_URL);
        new WebDriverWait(driver, EnvConfig.INIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(EnvConfig.COOKIE_PATH));
        driver.findElement(EnvConfig.COOKIE_PATH).click();
        }

        //Прокрутка до списка вопросов раздела "Вопросы о важном"
        public void scrollIntoViewAccordionElement() {
        WebElement accordion = driver.findElement(this.accordion);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordion);
        new WebDriverWait(driver, EnvConfig.INIT_TIME)
                .until(ExpectedConditions.visibilityOfElementLocated(this.accordion));
        }

        //Получаем текст вопроса отображаемого на экране.
        // (раздел "Вопросы о важном")
        public String clickAndGetTextQuestion(int tabindex){
            By question = By.id(String.format("accordion__heading-%s", tabindex));
            WebElement questionElement = driver.findElement(question);
            questionElement.click();
        return questionElement.getText();
        }

        //Получаем текст ответа, который отображается на экране после клика на вопрос.
        // (раздел "Вопросы о важном")
        public String getResponseTextFromScreen(int tabindex) {
            WebElement answerElement = driver.findElement(By.id((String.format("accordion__panel-%s", tabindex))));
        return answerElement.getText();
        }

        //Кликаем по верхней или нижней кнопке "Заказать", в зависимости от входных параметров в тесте.
        //При параметре "top" будет задействована верхняя кнопка на главной странице,при "bottom" нижняя.
        //для нижней кнопки задействованна прокрутка и ожидание видимости элемента
        public void clickOrderButton(String orderButton){
            if (orderButton.equals("bottom")) {
                WebElement bottom = driver.findElement(bottomButtonOrder);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", bottom);
                new WebDriverWait(driver, EnvConfig.INIT_TIME)
                        .until(ExpectedConditions.visibilityOfElementLocated(this.bottomButtonOrder));
                driver.findElement(bottomButtonOrder).click();
            } else if (orderButton.equals("top")) {
                driver.findElement(topButtonOrder).click();
            }
        }



}

