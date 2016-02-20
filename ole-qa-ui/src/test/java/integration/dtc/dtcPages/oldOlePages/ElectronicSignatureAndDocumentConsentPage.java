package integration.dtc.dtcPages.oldOlePages;

import pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ElectronicSignatureAndDocumentConsentPage  extends WizardPage {
    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Electronic Signature and Documents Consent"));
    }
}