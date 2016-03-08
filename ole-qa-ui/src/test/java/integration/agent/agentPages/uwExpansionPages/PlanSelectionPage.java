package integration.agent.agentPages.uwExpansionPages;

import entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.WizardPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlanSelectionPage extends WizardPage{

    @FindBy(xpath = "(//a[contains(text(),'apply now')])[1]") FluentWebElement First_Plan;
    @FindBy(xpath = "(//a[contains(text(),'apply now')])[2]") FluentWebElement Second_Plan;
    @FindBy(xpath = "//*[@id='block']/div[3]/p[4]/a") FluentWebElement ChangeEligibilityAndAvailabilityInformation;

    @FindBy(xpath = "//*[@id='RiderChoiceXW']") FluentWebElement RiderChoice_XW;
    @FindBy(xpath = "//*[@id='RiderChoiceYW']") FluentWebElement RiderChoice_YW;
    @FindBy(xpath = "//*[@id='RiderChoiceVW']") FluentWebElement RiderChoice_VW;
    @FindBy(xpath = "//*[@id='RiderChoiceWW']") FluentWebElement RiderChoice_WW;
    @FindBy(xpath = "//*[@id='RiderChoiceZW']") FluentWebElement RiderChoice_ZW;

    @FindBy(xpath = "//*[@id='RiderChoiceOW']") FluentWebElement RiderChoice_OW;
    @FindBy(xpath = "//*[@id='RiderChoicePW']") FluentWebElement RiderChoice_PW;
    @FindBy(xpath = "//*[@id='RiderChoiceQW']") FluentWebElement RiderChoice_QW;
    @FindBy(xpath = "//*[@id='RiderChoiceSW']") FluentWebElement RiderChoice_SW;

    @FindBy(xpath = "(//*[@id='RiderChoiceOW'])[2]") FluentWebElement RiderChoice_OW2;
    @FindBy(xpath = "(//*[@id='RiderChoicePW'])[2]") FluentWebElement RiderChoice_PW2;
    @FindBy(xpath = "(//*[@id='RiderChoiceSW'])[2]") FluentWebElement RiderChoice_SW2;

    public void fillAndSubmit(Application app) {

        isAt();
        ChangeEligibilityAndAvailabilityInformation.click();
        clickNextAndWaitForSpinnerToFinish();
        try{
            Thread.sleep(6000);
        }catch(Exception e){
        }
        await().atMost(10, TimeUnit.SECONDS).until("#OleRateTable tbody tr").isPresent();
        await().atMost(5, TimeUnit.SECONDS).until(".OleRateTable").withText("Plan").hasSize();
        Second_Plan.click();

        blur("apply");
        try{
            Thread.sleep(3000);
        }catch(Exception e){
        }

    }
    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Plan Selection"));
    }

}
