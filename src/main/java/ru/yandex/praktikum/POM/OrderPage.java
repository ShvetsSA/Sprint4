//Тест заказа самоката
//Используется параметризация для оформления заказа, через верхнюю и нижнюю кнопки "Заказать" на главной странице.
//При запуске в Сhrome обнаруживается баг,  который не даёт оформить заказ. Это невозможность нажатия кнопки "Да".
// Он воспроизводится только в Chrome, в Firefox поведение нормальное

package ru.yandex.praktikum.POM;

import org.openqa.selenium.*;

public class OrderPage {

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Список локаторов первой страницы заказа самоката:

    //Локатор поля ввода "Имя"
    private final By fieldName = By.xpath("//input[@placeholder='* Имя']");
    //Локатор поля ввода "Фамилия"
    private final By fieldsurname = By.xpath("//input[@placeholder='* Фамилия']");
    //Локатор поля ввода "Адрес: куда привезти заказ"
    private final By addressWhereToDeliver = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор списка станций метро
    private final By listStation = By.xpath("//Input[@placeholder = '* Станция метро']");
     //Локатор поля "Телефон на который позвонит курьер "
    private final By fieldPhone = By.xpath("//Input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Локатор кнопки "Далее"
    private final By nextButton = By.xpath("//div[@class='Order_NextButton__1_rCA']/button");

    //Список локаторов второй страницы заказа самоката:

    //Локатор поля даты "Когда привезти самокат"
    private final By whenToBring = By.xpath("//Input[@placeholder = '* Когда привезти самокат']");
    //Локатор поля списка со сроком аренды
    private final By rentalTerm = By.className("Dropdown-placeholder");
    //Локатор поля "Комментарий для курьера"
    private final By commentForCourier = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    //Локатор кнопки "Заказать" (большая кнопка внизу страницы)
    private final By orderBtnP2 = By.xpath("//div[@class='Order_Content__bmtHS']//button[text()='Заказать']");

    //Список локаторов модального окна подтверждения заказа

    //Локатор кнопки "Да"
    private final By yesButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    // Локатор об успешном создании заказа
    private final By successOrderMessage = By.xpath(".//div[text()='Заказ оформлен']");

    //Методы для заполнения данных и выполнения тестов:

    //Ввод имени, фамилии, адреса доставки заказа, телефона
    public void setNameAndAddress(String name, String surname, String address, String phone) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldsurname).sendKeys(surname);
        driver.findElement(addressWhereToDeliver).sendKeys(address);
        driver.findElement(fieldPhone).sendKeys(phone);
    }

    //Выбор станции из списка
    public void selectStation(String station){
        driver.findElement(listStation).click();
        WebElement nameStation = driver.findElement(By.xpath("//ul[@class='select-search__options']//div[text()='"+station+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", nameStation);
        nameStation.click();
    }
    //нажатие кнопки Далее
    public void clickNext(){
        driver.findElement(nextButton).click();
    }
    //Установка даты заказа
    public void  setDateDelivery(String dateDelivery) {
        driver.findElement(whenToBring).sendKeys(dateDelivery);
        driver.findElement(whenToBring).sendKeys(Keys.ENTER);
    }
    //Выбор периода аренды
    public void setRentalPeriod(String rentalPeriod){
        driver.findElement(rentalTerm).click();
        driver.findElement(By.xpath("//*[text() = '"+ rentalPeriod + "']")).click();
    }
    //Выбор цвета
    public void setColour(String colour){
        driver.findElement(By.id(colour)).click();
    }
    //Комментарий для курьера
    public void sendComment(String comment){
        driver.findElement(commentForCourier).sendKeys(comment);
    }
    //Нажатие кнопки заказать и Да для подтвеждения заказа
    public void clickOrderAndConfirm(){
        driver.findElement(orderBtnP2).click();
        driver.findElement(yesButton).click();
    }
    //
    public void isSuccessOrderCreate() {
        driver.findElement(successOrderMessage).isDisplayed();
    }

}
