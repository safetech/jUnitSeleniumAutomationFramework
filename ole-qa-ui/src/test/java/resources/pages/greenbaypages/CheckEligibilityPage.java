package resources.pages.greenbaypages;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.entity.Application;
import resources.pages.WizardPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckEligibilityPage extends WizardPage {

    public FluentWebElement ZipCode;
    public FluentWebElement DOB;
    public FluentWebElement MPBED;
    public FluentWebElement ReqEffectiveDate;
    public FluentWebElement State;
    
    @FindBy(xpath = "//*[@id='ReqEffectiveDate']/option") FluentWebElement RequestedEffectiveDateSelector;

    public void fillAndSubmit(Application app) {

        isAt();
        fill(ZipCode).with(app.getZipCode());
        blur("#ZipCode");
            try{
                Thread.sleep(5000);
            }catch(Exception e){
            }
        blur("#State");
        await().atMost(10, TimeUnit.SECONDS).until("#State").hasAttribute("value", app.getState());
        fill(DOB).with(app.getDOB());
        fill(MPBED).with(app.getMPBED());
        blur("#MPBED");
            try{
                Thread.sleep(500);
            }catch(Exception e){
            }
        
        fillSelect("div.customer_eligibility_form #ReqEffectiveDate").withIndex(1);
        blur("#ReqEffectiveDate");
        try{
            Thread.sleep(1000);
        }catch(Exception e){
        }
        blur("#ReqEffectiveDate");
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickNextAndWaitForSpinnerToFinish();
    }

    public void blur(String selector){
        executeScript("$('"+selector+"').blur()");
    }

    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Check Eligibility"));

    }

}




