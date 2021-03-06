package functionalAndIntegrationTests.phone.oldSubmissionTests;

import com.github.javafaker.Faker;
import resources.entity.Application;
import resources.entity.SubmissionResult;
import resources.entity.phone.CribSheet;
import functionalAndIntegrationTests.CQBaseIntegrationTest;
import resources.pages.phonepages.oldOlePages.*;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import resources.pages.phonepages.oldOlePages.variations.pastandcurrentcoverage.VA_PastAndCurrentInsuranceCoveragePage;
import resources.pages.phonepages.oldOlePages.variations.planapplication.MD_PlanApplicationQuestionsPage;
import resources.pages.phonepages.oldOlePages.variations.replacementnotice.RN034andRE073Page;
import queries.PhoneSubmissionQuery;
import resources.utils.DateUtils;

public class MarylandIntegrationTest extends CQBaseIntegrationTest {

    @Page public CheatPage cheatPage;
    @Page public VoiceSignatureInstructionsPage voiceSignatureInstructionsPage;
    @Page public CustomerInformationPage customerInformationPage;
    @Page public PlanSelectionAndStartDatePage planSelectionAndStartDatePage;
    @Page public MD_PlanApplicationQuestionsPage planApplicationQuestionsPage;
    @Page public EligibilityHealthQuestionsPage eligibilityHealthQuestionsPage;
    @Page public VA_PastAndCurrentInsuranceCoveragePage pastAndCurrentInsuranceCoveragePage;
    @Page public AuthorizationAndVerificationPage authorizationAndVerificationPage;
    @Page public HealthHistoryQuestionsPage healthHistoryQuestionsPage;
    @Page public AgentVerificationPage agentVerificationPage;
    @Page public RN034andRE073Page replacementNoticePage;
    @Page public ReviewAndSubmitPage reviewAndSubmitPage;
    @Page public ApplicationSubmissionPage applicationSubmissionPage;

    public PhoneSubmissionQuery phoneSubmissionQuery;
    private Faker faker;
    private CribSheet sheet;
    private SubmissionResult expectedSubmissionResult;

    @Before
    public void setup() {
        phoneSubmissionQuery = new PhoneSubmissionQuery();
        faker = new Faker();

        sheet = new CribSheet(faker);
        sheet.setRandomNameGenderAndMembershipNumber();
        sheet.setRandomAddress("MD", "21234");
        sheet.setRandomContactInfo();
        sheet.setRandomCallCenterInfo();
        sheet.setDpsdToFirstDayOfFutureMonth(1);
        sheet.setPlanCode("F01");
        sheet.setGRSId(faker.numerify("######"));
        app = new Application();
        // Customer Info Page Question
        app.setMedicareClaimNum(faker.bothify("#########A"));
        app.setPartABActiveIndicator(YES);
        app.setPlanCode("F");
        app.setReqEffectiveDate(DateUtils.getFirstDayOfFutureMonth(1));
        //Eligibility Questions
        app.setESRD(NO);
        app.setSurgeryNeeded(NO);
        //Agent Verification Page
        app.setCommonAgentVerificationAnswers();

        expectedSubmissionResult = new SubmissionResult();
    }

    @Test
    public void test_maryland_health_history_underwriting_with_rn() throws Exception {

        sheet.setDateOfBirth(DateUtils.getDOBofPersonTurningAgeToday(69));
        sheet.setMedPartBdate("2014-01-01");

        // Customer Info Page
        app.setMPAED("01/01/2012");
        app.setMPBED("01/01/2014");
        //PlanApplicationQuestionPage
        app.setInsurTermed(NO);
        app.setTurned65In6GA(NO);
        app.setPartBIn6GA(NO);
        app.setPlanEffIn6OfEligible(NO);
        app.setLostCoverage(NO);
        app.setTobaccoUse(YES);
        //Health History
        app.setCommonHealthHistoryAnswers();
        //Past And Current Coverage
        app.setCPATurned65(NO);
        app.setCPAPartBIn6(NO);
        app.setMedicaidCovered(YES);
        app.setMedicaidSupPremium(YES);
        app.setMedicaidbenefit(YES);
        app.setExistingMedicare(YES);
        app.setOtherMedplanstart("01/01/2012");
        app.setOtherMedplanend("01/01/2015");
        app.setIntentReplace(YES);
        app.setFirstTime(YES);
        app.setDropMedSuppForThisPlan(YES);
        app.setExistMedSupp(YES);
        app.setMSInsCompany("Blue Cross Blue Shield");
        app.setMSPLAN("Medical Supplement");
        app.setReplaceExistingMedSup(YES);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);
        //Authorizationa and verififcation page
        app.setAuxFirstName("AuxFirstName");
        app.setAuxMI("M");
        app.setAuxLastName("AuxLastName");
        app.setAuxAddressLine1("AuxAddressLine1");
        app.setAuxCity("AuxCity");
        app.setAuxState("NV");
        app.setAuxZipCode("89101");
        //Replacement Notice Page
        app.setCommonReplacementNoticeAnswersWithApplicantInfo();

        expectedSubmissionResult.setAcceptedInfo();

        startApp(cheatPage, app, sheet);
        voiceSignatureInstructionsPage.fillAndSubmit(app);
        customerInformationPage.fillAndSubmit(app);
        planSelectionAndStartDatePage.fillAndSubmit(app);
        planApplicationQuestionsPage.fillAndSubmit(app);
        eligibilityHealthQuestionsPage.fillAndSubmit(app);
        pastAndCurrentInsuranceCoveragePage.fillAndSubmit(app);
        authorizationAndVerificationPage.fillAndSubmit(app);
        agentVerificationPage.fillAndSubmit(app);
        replacementNoticePage.fillAndSubmit(app);
        reviewAndSubmitPage.fillAndSubmit(app);
        applicationSubmissionPage.isAt();
        applicationSubmissionPage.isApproved();

        phoneSubmissionQuery.verifySubmissionData(app, expectedSubmissionResult);
        phoneSubmissionQuery.verifyAdjudicationData(app, expectedSubmissionResult);

    }
    @Test
    public void test_maryland_eligibility_underwriting_without_rn() throws Exception {

        sheet.setDateOfBirth(DateUtils.getDOBofPersonTurningAgeToday(68));
        sheet.setMedPartBdate("2014-01-01");

        // Customer Info Page
        app.setMPAED("01/01/2014");
        app.setMPBED("01/01/2014");
        //Plan Eligibility
        app.setInsurTermed(NO);
        app.setTurned65In6GA(NO);
        app.setPartBIn6GA(NO);
        app.setPlanEffIn6OfEligible(NO);
        app.setLostCoverage(NO);
        app.setTobaccoUse(NO);
        //Past And Current Coverage
        app.setCPATurned65(NO);
        app.setCPAPartBIn6(NO);
        app.setMedicaidCovered(YES);
        app.setMedicaidSupPremium(YES);
        app.setMedicaidbenefit(YES);
        app.setExistingMedicare(NO);
        app.setFirstTime(BLANK);
        app.setDropMedSuppForThisPlan(BLANK);
        app.setExistMedSupp(NO);
        app.setMSInsCompany("Blue Cross Blue Shield NV");
        app.setMSPLAN("Medical Supplement NV");
        app.setReplaceExistingMedSup(BLANK);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");                     
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);

        expectedSubmissionResult.setAcceptedInfo();

        startApp(cheatPage, app, sheet);
        voiceSignatureInstructionsPage.fillAndSubmit(app);
        customerInformationPage.fillAndSubmit(app);
        planSelectionAndStartDatePage.fillAndSubmit(app);
        planApplicationQuestionsPage.fillAndSubmit(app);
        eligibilityHealthQuestionsPage.fillAndSubmit(app);
        pastAndCurrentInsuranceCoveragePage.fillAndSubmit(app);
        authorizationAndVerificationPage.fillAndSubmit(app);
        agentVerificationPage.fillAndSubmit(app);
        reviewAndSubmitPage.fillAndSubmit(app);
        applicationSubmissionPage.isAt();
        applicationSubmissionPage.isApproved();

        phoneSubmissionQuery.verifySubmissionData(app, expectedSubmissionResult);
        phoneSubmissionQuery.verifyAdjudicationData(app, expectedSubmissionResult);

    }

}
