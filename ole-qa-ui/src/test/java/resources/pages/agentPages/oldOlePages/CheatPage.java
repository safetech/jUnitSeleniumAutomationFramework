package resources.pages.agentpages.oldOlePages;

import resources.entity.agent.CribSheet;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import resources.utils.AnswerUtils;
import resources.utils.PropertyUtils;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheatPage extends FluentPage{

    @FindBy(css = "#page-title") FluentWebElement pageTitle;

    FluentWebElement preset_select;
    FluentWebElement AgentId;
    FluentWebElement AgentMedSuppStates;
    FluentWebElement AgentCertificationYears;
    FluentWebElement marketability_code;
    FluentWebElement SiteId;
    FluentWebElement AgentNPN;
    FluentWebElement AgentName;
    FluentWebElement AgentEmail;
    FluentWebElement AgentPartyId;
    FluentWebElement referrer;
    FluentWebElement resume;
    FluentWebElement CloakedInd;
    FluentWebElement cheatButton;


    public String getUrl() {
        return PropertyUtils.getProperty("agent.urls");

    }

    public void fillAndSubmit(CribSheet sheet) {
        isAt();
        fill(AgentId).with(sheet.getAgentID());
        fill(AgentMedSuppStates).with(sheet.getAgentMedSuppStates());
        fill(AgentCertificationYears).with(sheet.getAgentCertificationYears());
        fill(marketability_code).with(sheet.getMarketability_code());
        fill(SiteId).with(sheet.getSiteId());
        fill(AgentNPN).with(sheet.getAgentNPN());
        fill(AgentName).with(sheet.getAgentName());
        fill(AgentEmail).with(sheet.getAgentEmail());
        fill(AgentPartyId).with(sheet.getAgentPartyId());
        fill(referrer).with(sheet.getReferrer());

        if(!sheet.getCloakedInd().equals(AnswerUtils.BLANK)) {
            CloakedInd.click();
        }
        if(!sheet.getResume().equals(AnswerUtils.BLANK)) {
            resume.click();
        }
        cheatButton.click();
        waitForSpinnerToFinish();
    }

    protected void waitForSpinnerToFinish() {
        await().atMost(7, TimeUnit.SECONDS).until("#loading_fader").areNotDisplayed();
    }
    public void isAt() {
        assertThat(pageTitle.getText(), equalTo("Cheat Page"));
    }

}
