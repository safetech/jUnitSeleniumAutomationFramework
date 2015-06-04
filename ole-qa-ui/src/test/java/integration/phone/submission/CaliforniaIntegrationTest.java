package integration.phone.submission;

import com.github.javafaker.Faker;
import integration.CQBaseIntegrationTest;
import integration.phone.entity.Application;
import integration.phone.entity.CribSheet;
import integration.phone.entity.SubmissionResult;
import integration.phone.pages.*;
import integration.phone.pages.variations.authorizationandverification.*;
import integration.phone.pages.variations.healthhistoryquestions.*;
import integration.phone.pages.variations.eligibilityhealthquestions.*;
import integration.phone.pages.variations.pastandcurrentcoverage.*;
import integration.phone.pages.variations.planapplicationpage.*;
import integration.phone.pages.variations.replacementnotice.*;
import integration.phone.queries.SubmissionQuery;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import util.DateUtils;

public class CaliforniaIntegrationTest extends CQBaseIntegrationTest {

    @Page public CheatPage cheatPage;
    @Page public VoiceSignatureInstructionsPage voiceSignatureInstructionsPage;
    @Page public CustomerInformationPage customerInformationPage;
    @Page public PlanSelectionAndStartDatePage planSelectionAndStartDatePage;
    @Page public CAPlanApplicationQuestions planApplicationQuestionsPage;
    @Page public CAEligibilityHealthQuestionsPage eligibilityHealthQuestionsPage;
    @Page public CAHealthHistoryQuestionsPage healthHistoryQuestionsPage;
<<<<<<< HEAD
    @Page public CA_PA_NJ_IN_PastAndCurrentInsuranceCoveragePage pastAndCurrentInsuranceCoveragePage;
=======
    @Page public PA_NJ_IN_PastAndCurrentInsuranceCoveragePage pastAndCurrentInsuranceCoveragePage;
>>>>>>> 5293b44c5eab755574b49c8948fee89ee219a367
    @Page public CAAuthorizationAndVerificationPage authorizationAndVerificationPage;
    @Page public AgentVerificationPage agentVerificationPage;
    @Page public RN040Page replacementNoticePage;
    @Page public ReviewAndSubmitPage reviewAndSubmitPage;
    @Page public ApplicationSubmissionPage applicationSubmissionPage;

    public SubmissionQuery submissionQuery;
    private Faker faker;
    private CribSheet sheet;
    private SubmissionResult expectedSubmissionResult;

    @Before
    public void setup() {
        submissionQuery = new SubmissionQuery();
        faker = new Faker();

        sheet = new CribSheet(faker);
        sheet.setRandomNameGenderAndMembershipNumber();
        sheet.setRandomAddress("CA", "90210");
        sheet.setRandomContactInfo();
        sheet.setRandomCallCenterInfo();
        sheet.setDpsdToFirstDayOfFutureMonth(1);
        sheet.setPlanCode("F01");

        app = new Application();
        // Customer Info Page Question
<<<<<<< HEAD
        app.setMedicareClaimNum(faker.bothify("3########?"));
=======
        app.setMedicareClaimNum(faker.bothify("??#########"));
>>>>>>> 5293b44c5eab755574b49c8948fee89ee219a367
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
    public void test_california_full_underwriting_with_rn() throws Exception {

        sheet.setDateOfBirth(DateUtils.getDOBofPersonTurningAgeToday(69));
<<<<<<< HEAD
        sheet.setMedPartBdate("2012-01-01");

        //Customer Information
        app.setMPAED("01/01/2011");
        app.setMPBED("01/01/2012");

=======
        sheet.setMedPartBdate("2012-10-01");

        //Customer Information
        app.setMPAED("01/01/2011");
        app.setMPBED("10/01/2012");

        app.setMedicareClaimNum(faker.bothify("??#########"));
>>>>>>> 5293b44c5eab755574b49c8948fee89ee219a367
        //Plan Application
        app.setTurned65In6GA(NO); //TODO: Replace these hard coded values with helper function that will determine answer based upon DOB
        app.setPartBIn6GA(NO); //TODO: Replace these hard coded values with helper function that will determine answer based upon MPBED
        app.setPlanEffIn6OfEligible(NO);  //TODO: Replace these hard coded values with helper function that will determine answer based upon DOB & MPBED
        app.setLostCoverage(NO);
        app.setTobaccoUse(NO);
        app.setGI30dayBday(NO);
        app.setGIEmployerCov(NO);
        app.setGIMediCal(NO);
        app.setGIMilitary(NO);
        app.setGILocation(NO);
        //Eligibility Questions
        app.setESRD(NO);
        app.setSurgeryNeeded(NO);
        //Eligibility Questions(SPECIFIC TO CA)
        app.setEligdialysis(NO);
        app.setEligRecdialysis(NO);
        app.setEligHospital(NO);
        app.setEligSurgery(NO);
        app.setEligOrgan(NO);
        app.setEligSpine(NO);
        app.setEligjoint(NO);
        app.setEligCancer(NO);
        app.setEligHeart(NO);
        app.setEligVascular(NO);
      //TODO  //Health History (Verify that we need the Health History data below
        app.setEmphysema(YES);
        app.setOthercancer(YES);
        app.setPolycystic(YES);
        app.setCirrhosis(YES);
        app.setBonemarrow(YES);
        app.setPancreatitis(YES);
        app.setAmputation(YES);
        app.setAlcohol(YES);
        app.setParaplegia(YES);
        app.setBipolar(YES);
        app.setMacular(YES);
        app.setAlzheimers(YES);
        app.setHIV(YES);
        //Past And Current Coverage
        app.setMedicaidCovered(YES);
        app.setMedicaidSupPremium(YES);
        app.setMedicaidbenefit(YES);
        app.setExistingMedicare(YES);
        app.setOtherMedplanstart("01/01/2000");
        app.setOtherMedplanend("01/01/2015");
        app.setIntentReplace(YES);
        app.setFirstTime(YES);
        app.setDropMedSuppForThisPlan(YES);
        app.setExistMedSupp(YES);
        app.setReplaceExistingMedSup(YES);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);
        //Authorization Page
        //Agent Verification Page
        app.setAgentOtherInsPoliciesSold("HIP");
        app.setAgentPoliciesInForce("EP");
        app.setAgentPoliciesSoldNotInForce("EPHIP");
        app.setAgentSignatureInd(YES);
        //Replacement Notice Page
        app.setReplacementReason("OtherReason");
        app.setRNOther("Cheaper");
        app.setAgentPrintedNameAdd("ProducerName");
        app.setAgentAddress("ProducerAdd");
        app.setApplicantPrintedNameAdd("AppName");
        app.setApplicantAddress("AppAdd");


        expectedSubmissionResult.setAdjudicationStatus("P");
        expectedSubmissionResult.setStatus("C");
        expectedSubmissionResult.setWorkQueue("UNDERWRITING");
        expectedSubmissionResult.setWorkQueueReason("REVIEW FOR POSSIBLE ESRD");

        logger.info(gson.toJson(app));

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
<<<<<<< HEAD
        reviewAndSubmitPage.fillAndSubmit(app);

        applicationSubmissionPage.isAt();
        applicationSubmissionPage.isApproved();

        submissionQuery.verifySubmissionData(app, expectedSubmissionResult);
        submissionQuery.verifyAdjudicationData(app, expectedSubmissionResult);

    }

    @Test
    public void test_california_full_underwriting_without_rn() throws Exception {

        sheet.setDateOfBirth(DateUtils.getDOBofPersonTurningAgeToday(65));
        sheet.setMedPartBdate("2015-01-01");

        //Customer Information
        app.setMPAED("01/01/2015");
        app.setMPBED("01/01/2015");

        //Plan Application
        app.setTurned65In6GA(YES); //TODO: Replace these hard coded values with helper function that will determine answer based upon DOB
        app.setPartBIn6GA(YES); //TODO: Replace these hard coded values with helper function that will determine answer based upon MPBED
        app.setPlanEffIn6OfEligible(YES);  //TODO: Replace these hard coded values with helper function that will determine answer based upon DOB & MPBED
        app.setLostCoverage(BLANK);
        app.setTobaccoUse(BLANK);
        app.setGI30dayBday(YES);
        app.setGIEmployerCov(BLANK);
        app.setGIMediCal(BLANK);
        app.setGIMilitary(BLANK);
        app.setGILocation(BLANK);
        //Past And Current Coverage
        app.setMedicaidCovered(YES);
        app.setMedicaidSupPremium(YES);
        app.setMedicaidbenefit(YES);
        app.setExistingMedicare(NO);
        app.setOtherMedplanstart(BLANK);
        app.setOtherMedplanend(BLANK);
        app.setIntentReplace(BLANK);
        app.setFirstTime(BLANK);
        app.setDropMedSuppForThisPlan(BLANK);
        app.setExistMedSupp(NO);
        app.setReplaceExistingMedSup(BLANK);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);
        //Authorization Page
        //Agent Verification Page
        app.setAgentOtherInsPoliciesSold("HMO");
        app.setAgentPoliciesInForce("EP");
        app.setAgentPoliciesSoldNotInForce("EP-HIP");
        app.setAgentSignatureInd(YES);
        //Replacement Notice Page
        app.setReplacementReason("OtherReason");
        app.setRNOther("Cheaper");
        app.setAgentPrintedNameAdd("ProducerName");
        app.setAgentAddress("ProducerAdd");
        app.setApplicantPrintedNameAdd("AppName");
        app.setApplicantAddress("AppAdd");


        expectedSubmissionResult.setAdjudicationStatus("A");
        expectedSubmissionResult.setStatus("C");
        expectedSubmissionResult.setWorkQueue("");
        expectedSubmissionResult.setWorkQueueReason("");

        logger.info(gson.toJson(app));

        startApp(cheatPage, app, sheet);

        voiceSignatureInstructionsPage.fillAndSubmit(app);
        customerInformationPage.fillAndSubmit(app);
        planSelectionAndStartDatePage.fillAndSubmit(app);
        planApplicationQuestionsPage.fillAndSubmit(app);
        pastAndCurrentInsuranceCoveragePage.fillAndSubmit(app);
        authorizationAndVerificationPage.fillAndSubmit(app);
        agentVerificationPage.fillAndSubmit(app);
=======
>>>>>>> 5293b44c5eab755574b49c8948fee89ee219a367
        reviewAndSubmitPage.fillAndSubmit(app);

        applicationSubmissionPage.isAt();
        applicationSubmissionPage.isApproved();

        submissionQuery.verifySubmissionData(app, expectedSubmissionResult);
        submissionQuery.verifyAdjudicationData(app, expectedSubmissionResult);

    }
}
