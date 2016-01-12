package pages.agent.variations.planapplication;

import entity.Application;
import pages.phone.PlanApplicationQuestions;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class AR_PA_PlanApplicationQuestionsPage extends PlanApplicationQuestions {


    protected int TOTAL_POSSIBLE_QUESTION_COUNT = 6;
    @FindBy(css = "#LostCoverage_1") FluentWebElement LostCoverage_Yes;
    @FindBy(css = "#LostCoverage_2") FluentWebElement LostCoverage_No;

    public void verifyInitialStateOfElements(Application app) {
<<<<<<< HEAD
//        assertYesNoQuestion(Turned65In6GA_Yes, Turned65In6GA_No, app.getTurned65In6GA());
//        assertYesNoQuestion(PartBIn6GA_Yes, PartBIn6GA_No, app.getPartBIn6GA());
=======

>>>>>>> 5e0f9f2781d22ac123ca26f074e5951288aeafc1
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertBlank(LostCoverage_Yes, LostCoverage_No);
        assertBlank(TobaccoUse_Yes, TobaccoUse_No);
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }

    public void fillAndSubmit(Application app) {
        isAt();

        verifyInitialStateOfElements(app);
        fillYesNoQuestion(PlanEffIn6OfEligible_Yes,PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        fillYesNoQuestion(LostCoverage_Yes, LostCoverage_No, app.getLostCoverage());
        fillYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());

        verifyStateOfElementAfterAnswers(app);

        clickNextAndWaitForSpinnerToFinish();
    }

    public void verifyStateOfElementAfterAnswers(Application app) {
<<<<<<< HEAD
        assertVisible(PlanEffIn6OfEligible_Yes,
                PlanEffIn6OfEligible_No,
                LostCoverage_Yes,
                LostCoverage_No);
       // assertYesNoQuestion(Turned65In6GA_Yes, Turned65In6GA_No, app.getTurned65In6GA());
       // assertYesNoQuestion(PartBIn6GA_Yes, PartBIn6GA_No, app.getPartBIn6GA());
=======
        assertVisible(
                PlanEffIn6OfEligible_Yes,
                PlanEffIn6OfEligible_No,
                LostCoverage_Yes,
                LostCoverage_No);
>>>>>>> 5e0f9f2781d22ac123ca26f074e5951288aeafc1
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertYesNoQuestion(LostCoverage_Yes, LostCoverage_No, app.getLostCoverage());
        assertVisibleBasedUpon(app.getLostCoverage().equals(NO), TobaccoUse_Yes, TobaccoUse_No);
        assertYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }

}
