package resources.pages.phonepages.oldOlePages;


import resources.entity.Application;
import resources.pages.WizardPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReviewAndSubmitPage extends WizardPage {

    public void fillAndSubmit(Application app) {

        isAt();
        nextButton.click();
        waitForSpinnerToFinish(20);

    }

    public void isAt() {
        System.out.println(pageTitle.getText());
        assertThat(pageTitle.getText(), equalTo("Review and Submit"));
    }
}
