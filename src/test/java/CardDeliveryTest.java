import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;




public class CardDeliveryTest {

    CurrentLocalDate localDate = new CurrentLocalDate();


    @Test

    void shouldInputValid(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Уфа");
        $("[data-test-id=date]").click().click().setValue(LocalDate.now().plusDays(3));
        $("[data-test-id=name] input").setValue("Иван Валерьевич");
        $("[data-test-id=phone] input").setValue("+79111488111");
        $("[data-test-id=agreement]").click();
        $(byText(Забронировать)).click();
        $("[data-test-id=notification]").waitUntil(visible,15000).shouldHave(text("Встреча успешно забронирована на"));
        $("[data-test-id=notification]").shoulHave(text(currentDate));





    }


}
