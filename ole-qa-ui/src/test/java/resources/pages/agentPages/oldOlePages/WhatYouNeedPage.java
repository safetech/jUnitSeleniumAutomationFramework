package resources.pages.agentpages.oldOlePages;

import resources.entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WhatYouNeedPage extends WizardPage {

    @FindBy(css = "#pageborder") FluentWebElement pageBorderContent;
    @FindBy(css = "#text_10_0") FluentWebElement PlanOverview;
    @FindBy(css = "#text_11_0") FluentWebElement BenefitsTable;
    @FindBy(css = "#text_1_0") FluentWebElement RulesAndDisclousures;
    @FindBy(css = "#text_12_0") FluentWebElement YourGuideToAARPMedicareSupplementInsurancePlans;
    @FindBy(css = "#text_2_0") FluentWebElement GuideToHealthInsuranceForPeopleWithMedicare;
    @FindBy(css = "#colctrl_showhide>p>a") FluentWebElement ImportantDisclousures;
    @FindBy(css = "#AgentKitProvided") FluentWebElement AgentKitProvided;
    @FindBy(css = "#signatureType_3") FluentWebElement TouchSign;
    @FindBy(css = "#yesnotest_1") FluentWebElement Yes;
    @FindBy(css = "#yesnotest_2") FluentWebElement No;


    public void fillAndSubmit(Application app) {
        isAt();
        AgentKitProvided.click();
        TouchSign.click();
        No.click();

        clickNextAndWaitForSpinnerToFinish();
    }
    public void isAt() {
         assertThat(pageTitle.getText(), equalTo("What You Need"));
    }

}
