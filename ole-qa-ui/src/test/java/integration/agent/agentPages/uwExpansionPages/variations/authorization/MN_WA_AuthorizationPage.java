package integration.agent.agentPages.uwExpansionPages.variations.authorization;

import entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MN_WA_AuthorizationPage extends WizardPage {

    @FindBy(css = "#UnderstandAuth_1") FluentWebElement UnderstandAuth_Yes;
    @FindBy(css = "#UnderstandAuth_2") FluentWebElement UnderstandAuth_No;
    FluentWebElement SignatureInd;
    FluentWebElement MedicalReleaseAuthSignatureInd;

    public void fillAndSubmit(Application app) {

        SignatureInd.click();
        fillTouchSignature("SignatureIndTouch",  app.getSignatureIndTouch());

        clickNextAndWaitForSpinnerToFinish();

    }

    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Authorization and Verification"));
    }
}