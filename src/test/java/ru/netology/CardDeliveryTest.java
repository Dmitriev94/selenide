package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    CurrentLocalDate CurrentLocalDate = new CurrentLocalDate();
    public String date = CurrentLocalDate.localDateTime();



    @Test
    void shouldInputValid() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Уфа");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иван Валерьевич");
        $("[data-test-id=phone] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000).shouldHave(text("Встреча успешно забронирована на"));
        $("[data-test-id=notification]").shouldHave(text(date));


    }


    @Test
    void shouldInputInvalidCIty() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Бельдяшки");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иван Валерьевич");
        $("[data-test-id=phone] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));


    }

    @Test
    void shouldInputInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Phil McCrevis");
        $("[data-test-id=phone] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldInputInvalidPhone() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date);
        $("[name=name]").setValue("Иван");
        $("[name=phone]").setValue("89111488111");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(exist);


    }

    @Test
    void shouldNotCheckBox() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name=name]").setValue("Иван");
        $("[name=phone]").setValue("+79111488111");
        $(byText("Забронировать")).click();
        $("[role=presentation]").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNotInputDate() {
        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[name=name]").setValue("Иван");
        $("[name=phone]").setValue("+79111488188");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave(text("Неверно введена дата"), cssValue("color", "rgba(255, 92, 92, 1)"));
    }


}
