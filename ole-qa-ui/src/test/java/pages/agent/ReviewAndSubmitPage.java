package pages.agent;

import entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import pages.WizardPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReviewAndSubmitPage extends WizardPage {

    FluentWebElement requiredlink;

    public void fillAndSubmit(Application app) {

        isAt();
        requiredlink.click();
            closeSpecificBrowser(1);

        click(WIZARD_PAGE_NEXT_BTN_SELECTOR);
        await().atMost(10, TimeUnit.SECONDS);
    }

    public void closeSpecificBrowser(int Brow){
        ArrayList<String> windows = new ArrayList<String> (getDriver().getWindowHandles());
        String baseWindowHdl = getDriver().getWindowHandle();
        getDriver().switchTo().window(windows.get(Brow));
        getDriver().close();
        getDriver().switchTo().window(baseWindowHdl);
    }
    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Review and Submit"));
    }
}