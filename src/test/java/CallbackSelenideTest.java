// USING CASE SELENIDE

// load JAR-file: "java -jar artifacts/app-card-delivery.jar" before starting test

/* Поиск по селектору:
    1. По классу - ".название класса" (.name)
    2. По атрибуту - "[атрибут]" ([name=first_name])
    3. По id - "'#название'" ('#name')
    4. По тегу - "input" ($ один или $$ несколько)

    ***
    Simple селекторы можно выстраивать в последовательность, требуя соответствия всем селекторам, входящим в последовательность, например, input[type="search"] раскладывается на два simple:

    input (селектор по тегу)
    [type="search"] (селектор по значению атрибута)
* */
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.LocalDate.now;
import java.time.format.DateTimeFormatter;

class CallbackSelenideTest {

    public String generateDate(int days) {
        return now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldTest() {

        String planningDate = generateDate(4);


        open("http://localhost:9999");
        SelenideElement form = $("form");
        ElementsCollection button = $$("button");

        form.find("[data-test-id=city] input").setValue("Москва");
        form.find("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        form.find("[data-test-id=date] input").setValue(planningDate);
        form.find("[data-test-id=name] input").setValue("Васильев Василий");
        form.find("[data-test-id=phone] input").setValue("+79270000000");
        form.find("[data-test-id='agreement']").click();
        button.get(1).click();
        //$(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(12));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(12)).shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }
}