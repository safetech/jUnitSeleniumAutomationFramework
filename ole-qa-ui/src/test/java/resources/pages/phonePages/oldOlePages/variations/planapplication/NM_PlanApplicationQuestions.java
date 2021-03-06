package resources.pages.phonepages.oldOlePages.variations.planapplication;

import resources.entity.Application;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.pages.phonepages.oldOlePages.PlanApplicationQuestions;

public class NM_PlanApplicationQuestions extends PlanApplicationQuestions {


    protected int TOTAL_POSSIBLE_QUESTION_COUNT = 11;
    @FindBy(css = "#LostCoverage_1") FluentWebElement LostCoverage_Yes;
    @FindBy(css = "#LostCoverage_2") FluentWebElement LostCoverage_No;
    @FindBy(css = "#GIMPBED") FluentWebElement GIMPBED;

    public void verifyInitialStateOfElements(Application app) {
        assertBlank(TobaccoUse_Yes, TobaccoUse_No);
        assertYesNoQuestion(Turned65In6GA_Yes, Turned65In6GA_No, app.getTurned65In6GA());
        assertYesNoQuestion(PartBIn6GA_Yes, PartBIn6GA_No, app.getPartBIn6GA());
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertBlank(LostCoverage_Yes, LostCoverage_No);
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }

    public void fillAndSubmit(Application app) {

        isAt();
        verifyInitialStateOfElements(app);

        fillYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());
        if((app.getMPBED()).equals(YES))
            fill(GIMPBED).with(app.getGIMPBED());
        fillYesNoQuestion(LostCoverage_Yes, LostCoverage_No, app.getLostCoverage());

        verifyStateOfElementAfterAnswers(app);

        clickNextAndWaitForSpinnerToFinish();
    }

    public void verifyStateOfElementAfterAnswers(Application app) {
        assertVisible(TobaccoUse_Yes,
                TobaccoUse_No,
                Turned65In6GA_Yes,
            Turned65In6GA_No,
            PartBIn6GA_Yes,
            PartBIn6GA_No,
            PlanEffIn6OfEligible_Yes,
            PlanEffIn6OfEligible_No);
        assertYesNoQuestion(TobaccoUse_Yes, TobaccoUse_No, app.getTobaccoUse());
        assertYesNoQuestion(Turned65In6GA_Yes, Turned65In6GA_No, app.getTurned65In6GA());
        assertYesNoQuestion(PartBIn6GA_Yes, PartBIn6GA_No, app.getPartBIn6GA());
        assertYesNoQuestion(PlanEffIn6OfEligible_Yes, PlanEffIn6OfEligible_No, app.getPlanEffIn6OfEligible());
        assertVisibleBasedUpon(app.getPartBIn6GA().equals(YES), GIMPBED);
        assertYesNoQuestion(LostCoverage_Yes, LostCoverage_No, app.getLostCoverage());
        assertQuestionCount(TOTAL_POSSIBLE_QUESTION_COUNT);
    }
//TODO: Check app.getGIMPBED()
}
