package resources.pages.agentpages.uwExpansionPages.variations.planapplication;

import resources.entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.pages.agentpages.oldOlePages.PlanApplicationQuestions;

public class OR_PlanApplicationQuestionsPage extends PlanApplicationQuestions {

    protected int TOTAL_POSSIBLE_QUESTION_COUNT = 8;
    @FindBy(css = "#LostCoverage_1") FluentWebElement LostCoverage_Yes;
    @FindBy(css = "#LostCoverage_2") FluentWebElement LostCoverage_No;
    @FindBy(css = "#GI30dayBday_1") FluentWebElement GI30dayBday_Yes;
    @FindBy(css = "#GI30dayBday_2") FluentWebElement GI30dayBday_No;

    public void verifyInitialStateOfElements(Application app) {
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertVisibleBasedUpon(app.getPlanEffIn6OfEligible().equals(NO), GI30dayBday_Yes, GI30dayBday_No);
        assertHidden(LostCoverage_Yes, LostCoverage_No);
        assertBlank(TobaccoUse_Yes, TobaccoUse_No);

    }

    public void fillAndSubmit(Application app) {
        isAt();

        verifyInitialStateOfElements(app);
        if(app.getPlanEffIn6OfEligible().equals(NO)) {
            fillYesNoQuestion(GI30dayBday_Yes, GI30dayBday_No, app.getGI30dayBday());
            if(app.getGI30dayBday().equals(NO)) {
                fillYesNoQuestion(LostCoverage_Yes, LostCoverage_No, app.getLostCoverage());
            }

        }
        fillYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());
        verifyStateOfElementAfterAnswers(app);

        clickNextAndWaitForSpinnerToFinish();
    }

    public void verifyStateOfElementAfterAnswers(Application app) {
        assertVisible(PlanEffIn6OfEligible_Yes,
                PlanEffIn6OfEligible_No,
                TobaccoUse_Yes,
                TobaccoUse_No);
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertVisibleBasedUpon(app.getPlanEffIn6OfEligible().equals(NO), GI30dayBday_Yes, GI30dayBday_No);
        assertVisibleBasedUpon(app.getGI30dayBday().equals(NO), LostCoverage_Yes, LostCoverage_No);

        assertYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }

}
