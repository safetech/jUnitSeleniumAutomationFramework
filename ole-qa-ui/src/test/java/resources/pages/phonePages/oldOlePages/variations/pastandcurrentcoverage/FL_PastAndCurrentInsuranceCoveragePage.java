package resources.pages.phonepages.oldOlePages.variations.pastandcurrentcoverage;

import resources.entity.Application;
import resources.pages.WizardPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FL_PastAndCurrentInsuranceCoveragePage extends WizardPage{

    @FindBy(css = "#UnderstandPandC_1") FluentWebElement UnderstandPandC_Yes;
    @FindBy(css = "#UnderstandPandC_2") FluentWebElement UnderstandPandC_No;
    @FindBy(css = "#CPATurned65_1") FluentWebElement CPATurned65_Yes;
    @FindBy(css = "#CPATurned65_2") FluentWebElement CPATurned65_No;
    @FindBy(css = "#CPAPartBIn6_1") FluentWebElement CPAPartBIn6_Yes;
    @FindBy(css = "#CPAPartBIn6_2") FluentWebElement CPAPartBIn6_No;
    @FindBy(css = "#MedicaidCovered_1") FluentWebElement MedicaidCovered_Yes;
    @FindBy(css = "#MedicaidCovered_2") FluentWebElement MedicaidCovered_No;
    @FindBy(css = "#MedicaidSupPremium_1") FluentWebElement MedicaidSupPremium_Yes;
    @FindBy(css = "#MedicaidSupPremium_2") FluentWebElement MedicaidSupPremium_No;
    @FindBy(css = "#Medicaidbenefit_1") FluentWebElement Medicaidbenefit_Yes;
    @FindBy(css = "#Medicaidbenefit_2") FluentWebElement Medicaidbenefit_No;
    FluentWebElement OtherMedplanstart;
    FluentWebElement OtherMedplanend;
    @FindBy(css = "#ExistMedSupp_1") FluentWebElement ExistMedSupp_Yes;
    @FindBy(css = "#ExistMedSupp_2") FluentWebElement ExistMedSupp_No;
    FluentWebElement MSInsCompany;
    FluentWebElement MSPLAN;
    @FindBy(css = "#ReplaceExistingMedSup_1") FluentWebElement ReplaceExistingMedSup_Yes;
    @FindBy(css = "#ReplaceExistingMedSup_2") FluentWebElement ReplaceExistingMedSup_No;
    @FindBy(css = "#OtherInsCoverage_1") FluentWebElement OtherInsCoverage_Yes;
    @FindBy(css = "#OtherInsCoverage_2") FluentWebElement OtherInsCoverage_No;
    FluentWebElement OtherInsCompany;
    FluentWebElement OtherInsType;
    FluentWebElement OtherInsStart;
    FluentWebElement OtherInsEnd;
    @FindBy(css = "#OtherInsReplace_1") FluentWebElement OtherInsReplace_Yes;
    @FindBy(css = "#OtherInsReplace_2") FluentWebElement OtherInsReplace_No;
    FluentWebElement CpaSignatureInd;

    public void fillAndSubmit(Application app) {

        UnderstandPandC_Yes.click();

        fillYesNoQuestion(CPATurned65_Yes, CPATurned65_No, app.getCPATurned65());
        fillYesNoQuestion(CPAPartBIn6_Yes, CPAPartBIn6_No, app.getCPAPartBIn6());
        fillYesNoQuestion(MedicaidCovered_Yes, MedicaidCovered_No, app.getMedicaidCovered());

        if(app.getMedicaidCovered().equals("yes")) {
            fillYesNoQuestion(MedicaidSupPremium_Yes, MedicaidSupPremium_No, app.getMedicaidSupPremium());
            fillYesNoQuestion(Medicaidbenefit_Yes, Medicaidbenefit_No, app.getMedicaidbenefit());
        }

        fill(OtherMedplanstart).with(app.getOtherMedplanstart());
        fill(OtherMedplanend).with(app.getOtherMedplanend());

        fillYesNoQuestion(ExistMedSupp_Yes, ExistMedSupp_No, app.getExistMedSupp());

        if(app.getExistMedSupp().equals("yes")){
            fill(MSInsCompany).with(app.getMSInsCompany());
            fill(MSPLAN).with(app.getMSPLAN());
            fillYesNoQuestion(ReplaceExistingMedSup_Yes, ReplaceExistingMedSup_No, app.getReplaceExistingMedSup());
        }

        fillYesNoQuestion(OtherInsCoverage_Yes, OtherInsCoverage_No, app.getOtherInsCoverage());

        if(app.getOtherInsCoverage().equals("yes")) {
            fill(OtherInsCompany).with(app.getOtherInsCompany());
            fill(OtherInsType).with(app.getOtherInsType());
            fill(OtherInsStart).with(app.getOtherInsStart());
            fill(OtherInsEnd).with(app.getOtherInsEnd());
            fillYesNoQuestion(OtherInsReplace_Yes, OtherInsReplace_No, app.getOtherInsReplace());
        }

        CpaSignatureInd.click();

        clickNextAndWaitForSpinnerToFinish();
    }

    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Past and Current Insurance Coverage"));
    }
}
