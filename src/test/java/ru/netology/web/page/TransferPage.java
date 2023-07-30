package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.exactText;

public class TransferPage {
    private SelenideElement header = $(byText("Пополнение карты"));
    private SelenideElement amountToTransfer = $("[data-test-id=amount] input");
    private SelenideElement transferFromCard = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");

    public TransferPage() {
        header.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer (String amountTransfer, DataHelper.CardInfo cardInfo) {
        amountToTransfer.setValue(amountTransfer);
        transferFromCard.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }
    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}