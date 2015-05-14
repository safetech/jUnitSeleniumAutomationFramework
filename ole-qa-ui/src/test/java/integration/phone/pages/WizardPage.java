package integration.phone.pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class WizardPage extends FluentPage {

    @FindBy(css = "section h3:first-of-type")
    protected FluentWebElement pageTitle;

    @FindBy(css = "a.action_next")
    protected FluentWebElement nextButton;

    private String TEXT_SUB_SELECTOR = "span[data-textsub-id='%s']";

    protected void waitForSpinnerToFinish() {
        waitForSpinnerToFinish(10);
    }

    protected void waitForSpinnerToFinish(int seconds) {
        await().atMost(seconds, TimeUnit.SECONDS).until("#loading_fader").areNotDisplayed();
    }

    protected void fillYesNoQuestion(FluentWebElement yesRadio, FluentWebElement noRadio, String answer) {
        if(answer.equals("yes")) {
            yesRadio.click();
        }
        if(answer.equals("no")) {
            noRadio.click();
        }
    }

    protected void assertYesNoQuestion(FluentWebElement yesRadio, FluentWebElement noRadio, String answer) {
        switch (answer) {
            case "yes":
                assertThat(String.format("Testing selectedness of radio with id = %s", yesRadio.getId()), yesRadio.isSelected(), equalTo(true));
                break;
            case "no":
                assertThat(String.format("Testing selectedness of radio with id = %s", yesRadio.getId()), noRadio.isSelected(), equalTo(true));
                break;
            default:
                assertThat(String.format("Testing selectedness of radio with id = %s", yesRadio.getId()), yesRadio.isSelected(), equalTo(false));
                assertThat(String.format("Testing selectedness of radio with id = %s", yesRadio.getId()), noRadio.isSelected(), equalTo(false));
        }
    }

    protected void assertVisibleBasedUpon(boolean shouldBeVisible, FluentWebElement... elements) {
        for(FluentWebElement element : elements) {
            assertThat(String.format("Testing visibility of %s", element.getId()),element.isDisplayed(), equalTo(shouldBeVisible));
        }
    }

    protected void assertVisible(FluentWebElement... elements) {
        assertVisibleBasedUpon(true, elements);
    }

    protected void assertHidden(FluentWebElement... elements) {
        assertVisibleBasedUpon(false, elements);
    }

    protected void fillCheckboxQuestion(FluentWebElement checkbox, String answer) {
        if(answer.equals("yes")) {
            checkbox.click();
        }
    }

    protected void clickNextAndWaitForSpinnerToFinish() {
        clickNextAndWaitForSpinnerToFinish(10);
    }

    protected FluentWebElement getTextSubValue(String answername) {
        return findFirst(String.format(TEXT_SUB_SELECTOR, answername));
    }

    protected void assertTextSubValue(String answername, String value) {
        if(!value.equals("")) {
            assertThat(getTextSubValue(answername).getText(), equalTo(value));
        } else {
            assertThat(find(String.format(TEXT_SUB_SELECTOR, answername)).size(), equalTo(0));
        }
    }

    protected void clickNextAndWaitForSpinnerToFinish(int seconds) {
        nextButton.click();
        waitForSpinnerToFinish(seconds);
    }

    protected void assertHidden(FluentWebElement element) {
        assertThat(element.isDisplayed(), equalTo(false));
    }

    protected void assertBlank(FluentWebElement... elements) {
        String assertMessage = "Testing following element for blankness: %s";
        for(FluentWebElement element : elements) {
            switch (element.getTagName().toLowerCase()) {
                case "input":
                    switch (element.getAttribute("type").toLowerCase()) {
                        case "radio":
                        case "checkbox":
                            assertThat(String.format(assertMessage, element.getId()), element.isSelected(), equalTo(false));
                            break;
                        default:
                            assertThat(String.format(assertMessage, element.getId()), element.getValue(), equalTo(""));
                            break;
                    }
                    break;
                case "select":
                case "textarea":
                    assertThat(String.format(assertMessage, element.getId()), element.getValue(), equalTo(""));
                    break;
                default:
                    fail();
            }
        }
    }

    protected void assertQuestionCount(int expectedCount) {
        assertThat(find("input[type='text'], input[type='checkbox'], input[type='radio'], select").size(), equalTo(expectedCount));
    }

    public int getVisibleElementCount() {
        return Integer.parseInt(getScriptResult("$('input:visible, select:visible').length"));
    }

    public String getScriptResult(String script) {
        if (find("body > #output").size() == 0) {
            executeScript("$('body').append('<div id=\"output\" style=\"\"/>');");
            await().atMost(10, TimeUnit.SECONDS).until("body > #output").isPresent();
        }
        executeScript("$('#output').text('');");
        executeScript("$('#output').text(" + script + ");");
        return find("body > #output").getText();
    }

}
