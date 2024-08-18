package ru.yandex.praktikum;//Переменные используемые в проекте
import org.openqa.selenium.By;

import java.time.Duration;

public class EnvConfig {

    //Базовый адрес сайта заказа самокатов
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    //Локатор кнопки использования cookie (кнопка "да все привыкли").
    public static final By COOKIE_PATH = By.xpath(".//button[@id='rcc-confirm-button']");
    //Переменная длительности ожидания
    public static final Duration INIT_TIME = Duration.ofSeconds(5);
}
