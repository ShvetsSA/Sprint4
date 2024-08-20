//Тест заказа самоката
//Используется параметризация для оформления заказа, через верхнюю и нижнюю кнопки "Заказать" на главной странице.
//При запуске в Сhrome обнаруживается баг,  который не даёт оформить заказ. Это невозможность нажатия кнопки "Да".
// Он воспроизводится только в Chrome, в Firefox поведение нормальное

package ru.yandex.praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POM.MainPage;
import ru.yandex.praktikum.POM.OrderPage;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    //Кнопка Заказать на главной странице
    private final String orderButton;
    // Имя
    private final String name;
    // Фамилия
    private final String surname;
    // Адрес доставки
    private final String address;
    //Станция метро
    private final String metro;
    // Номер телефона
    private final String phoneNumber;
    //Когда привезти
    private final String dateDelivery;
    // Период аренды
    private final String rentalPeriod;
    // Цвет самоката
    private final String colour;
    // Комментарий
    private final String comment;


    public OrderScooterTest(String orderButton, String name, String surname, String address, String metro, String phoneNumber, String dateDelivery, String rentalPeriod, String colour, String comment){
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.dateDelivery = dateDelivery;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }


@Parameterized.Parameters()
public static Object[][] getOrderFormData() {
    return new Object[][]{
            {"top", "Владимир", "Маяковский", "Москва, пер. Маяковского 15", "Марксистская", "+74991234567", "23.08.2024","сутки", "grey", ""},
            {"bottom", "Михаил", "Булгаков", "Москва, Улица Большая Садовая, дом 10", "Маяковская", "+74997654321", "25.08.2024","шестеро суток", "black", "Перед доставкой позвонить"},
    };
}

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
        public void positiveOrderTest() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var orderPage = new OrderPage(driver);
        //Открыли главную страницу, согласились с cookie
        mainPage.openPage();
        //Нажали кнопку заказать(верхнюю или нижнюю, в зависимости от входных параметров)
        mainPage.clickOrderButton(orderButton);
        //Ввод имени, фамилии, адреса доставки, номера телефона
        orderPage.setNameAndAddress(name, surname, address,phoneNumber);
        //выбор станции метро
        orderPage.selectStation(metro);
        //нажатие кнопки Далее
        orderPage.clickNext();
        //Дата когда привезти заказ
        orderPage.setDateDelivery(dateDelivery);
        //Выбор периода аренды
        orderPage.setRentalPeriod(rentalPeriod);
        //выбор цвета
        orderPage.setColour(colour);
        //Комментарий курьеру
        orderPage.sendComment(comment);
        //Нажимаем кнопку Заказать и подтвеждаем
        orderPage.clickOrderAndConfirm();
        //Проверка успешности создания заказа
        orderPage.isSuccessOrderCreate();
    }

}
