import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByAttribute;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class Test {

    String generateDate(int addDays) {
        String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return date;
    }


    @org.junit.jupiter.api.Test
    public void testDelivery() {
        open("http://localhost:9999/");
        $("input[placeholder = 'Город']").sendKeys("Новосибирск");
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        $("input[placeholder = 'Дата встречи']").sendKeys(deleteString);
        $("input[placeholder = 'Дата встречи']").sendKeys(generateDate(10));
        $("input[name = 'name']").sendKeys("Сиб Сибов");
        $("input[name = 'phone']").sendKeys("+71111111111");
        $("[data-test-id=\"agreement\"]").click();
        $(By.className("button")).click();
        $("[data-test-id=\"success-notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Успешно!"));
    }

    @org.junit.jupiter.api.Test
    public void testDelivery1() {
        open("http://localhost:9999/");
        $(By.className("calendar-input__custom-control")).click();
        $("td[data-day = '1702832400000']").click();
        $("input[placeholder = 'Дата встречи']").shouldBe(Condition.value("18.12.2023"));
        $("input[placeholder = 'Город']").sendKeys("но");
        //$(By.linkText("Новосибирск")).click();
        //$(By.partialLinkText("Новосибирск")).click();
        //$(By.className("menu-item__control")).shouldHave(Condition.text("Новосибирск")).click();
        //$(By.className("menu-item__control")).shouldHave(Condition.attribute("Новосибирск")).click();
        //$$(By.className("menu-item__control")).get(0).click(); вариант проканал
        $$(By.className("menu-item__control")).findBy(Condition.text("Новосибирск")).click();
        $("input[name = 'name']").sendKeys("Сиб Сибов");
        $("input[name = 'phone']").sendKeys("+71111111111");
        $("[data-test-id=\"agreement\"]").click();
        $(By.className("button")).click();
        $("[data-test-id=\"success-notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Успешно!"));

    }
}
