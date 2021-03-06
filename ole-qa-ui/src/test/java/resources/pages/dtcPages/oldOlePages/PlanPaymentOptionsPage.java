package resources.pages.dtcpages.oldOlePages;

import resources.entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlanPaymentOptionsPage extends WizardPage {

    @FindBy(css = "#PaymentChoice_1") FluentWebElement CouponBook;
    @FindBy(css = "#PaymentChoice_2") FluentWebElement AutomaticPayment;
    @FindBy(css = "#EFTType_1") FluentWebElement ReoccuringPayments;
    @FindBy(css = "#EFTType_2") FluentWebElement OneTimePayments;
    @FindBy(xpath = "html/body/div[2]/div[1]/div[1]/form/section/div[3]/div[2]/div[2]/input") FluentWebElement PaymentChoiceCouponBook;

    protected int TOTAL_POSSIBLE_QUESTION_COUNT = 5;

    public void fillAndSubmit(Application app) {

        isAt();
        if(!PaymentChoiceCouponBook.isDisplayed()){
        CouponBook.click();
        }else
            PaymentChoiceCouponBook.click();
        
        if(app.getPlanPaymentOptions().equals("Recurring")) {
            AutomaticPayment.click();
            ReoccuringPayments.click();
        }else if (app.getPlanPaymentOptions().equals("OneTime")){
            AutomaticPayment.click();
            OneTimePayments.click();                
        }
                

        verifyStateOfElementAfterAnswers(app);

        clickNextAndWaitForSpinnerToFinish();
    }

    public void verifyStateOfElementAfterAnswers(Application app) {

        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }

    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Plan Payment Options"));
    }
}

