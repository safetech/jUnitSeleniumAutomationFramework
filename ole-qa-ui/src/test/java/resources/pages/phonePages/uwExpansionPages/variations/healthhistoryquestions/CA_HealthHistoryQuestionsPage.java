package resources.pages.phonepages.uwExpansionPages.variations.healthhistoryquestions;

import resources.entity.Application;
import resources.pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CA_HealthHistoryQuestionsPage extends WizardPage {

    public void fillAndSubmit(Application app) {

        clickNextAndWaitForSpinnerToFinish();

    }

    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Health History Questions"));
    }
}
