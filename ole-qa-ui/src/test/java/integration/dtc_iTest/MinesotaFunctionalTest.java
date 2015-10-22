package integration.dtc_iTest;

import com.github.javafaker.Faker;
import entity.Application;
import entity.SubmissionResult;
import entity.dtc.CribSheet;
import integration.CQBaseIntegrationTest;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import pages.dtc.*;
import pages.dtc.variations.authorization.WI_AuthorizationPage;
import pages.dtc.variations.pastandcurrentcoverage.AR_CA_PA_PastAndCurrentCoveragePage;
import pages.dtc.variations.planapplication.AR_PA_PlanApplicationQuestionsPage;
import pages.dtc.variations.planselectionandstartdate.PA_AR_NV_MA_PlanSelectionAndStartDatePage;
import queries.SubmissionQueryDtc;
import util.DateUtils;

public class MinesotaFunctionalTest extends CQBaseIntegrationTest {

    @Page public CheatPage cheatPage;
    @Page public WhatYouNeedPage whatYouNeedPage;
    @Page public ElectronicSignatureAndDocumentConsentPage electronicSignatureAndDocumentConsentPage;
    @Page public AboutYouPage aboutYouPage;
    @Page public PA_AR_NV_MA_PlanSelectionAndStartDatePage planSelectionAndStartDatePage;
    @Page public AR_PA_PlanApplicationQuestionsPage planApplicationQuestionsPage;
    @Page public EligibilityHealthQuestionsPage eligibilityHealthQuestionsPage;
    @Page public AR_CA_PA_PastAndCurrentCoveragePage pastAndCurrentCoveragePage;
    @Page public WI_AuthorizationPage authorizationPage;
    @Page public RN034andRE073Page replacementNoticePage;
    @Page public PlanPaymentOptionsPage planPaymentOptionsPage;
    @Page public ReviewAndSubmitPage reviewAndSubmitPage;
    @Page public HealthHistoryQuestionsPage healthHistoryQuestionsPage;

    public SubmissionQueryDtc submissionQuery;
    private Faker faker;
    protected CribSheet sheet;
    private SubmissionResult expectedSubmissionResult;

    @Before
    public void setup() {
        faker = new Faker();
        sheet = new CribSheet(faker);
        app = new Application();
        submissionQuery = new SubmissionQueryDtc();
        expectedSubmissionResult = new SubmissionResult();

    }


    //TODO: Get update for Riders DTC.. Once ready to automate

    @Ignore
    public void test_wisconsin_underwriting_with_health_history() throws Exception {




        sheet.setState("MN");
        sheet.setZip("55001");
        sheet.setRiderChoice1("OW");
        sheet.setRiderChoice2("");
        sheet.setRiderChoice3("QW");
        sheet.setRiderChoice4("");
        sheet.setRiderChoice5("");
        sheet.setAarpMemid("y");
        sheet.setDOB(DateUtils.getDOBofPersonTurningAgeToday(69));
        sheet.setEffDate(DateUtils.getFirstDayOfFutureMonth(-7));
        sheet.setPsd(DateUtils.getFirstDayOfFutureMonth(1));
        sheet.setPlanCode("MW");
        sheet.setReferrer("uLayer");

        //TestData
        app.setAARPMembershipNumber(faker.numerify("##########"));
        app.setPrefix("MR");
        app.setFirstName(this.faker.firstName());
        app.setMI(this.faker.letterify("?"));
        app.setLastName(this.faker.lastName());
        app.setSuffix("PHD");
        app.setAddressLine1("111 Street dr");
        app.setAddressLine2("apt #123");
        app.setCity("Horsham");
        app.setEmail("test@uhc.com");
        app.setConfirmEmail("test@uhc.com");
        app.setPhonePrimary("9874562345");
        app.setPhoneEvening("1234561234");
        app.setGender("M");
        app.setMedicareClaimNum(faker.bothify("A#########"));
       // app.setMedicareClaimNum("123123123A");
        app.setMPAED("01/01/2010");
        app.setPartABActiveIndicator(YES);
        //Plan Application Page
        app.setTobaccoUse(YES);
        app.setLostCoverage(NO);
        //Eligibility Page
        app.setESRD(NO);
        app.setSurgeryNeeded(NO);
        //Authorizationa and verififcation page
        app.setDesignateLapse(YES);
        //Past And Current Coverage
        app.setSixMonEligEnroll(YES);
        app.setSixMonEligEnroll(YES);
        app.setSixMonTurn65Enroll(YES);
        app.setSixMonTurn65Enroll(YES);
        app.setSixEmpCovTerm(YES);
        app.setSixEmpCovTerm(YES);
        app.setSixMonMoveOut(YES);
        app.setSixMonMoveOut(YES);
        app.setSixMonResident(YES);
        app.setSixMonResident(YES);
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
        app.setMSInsCompany("Blue Cross Blue Shield NV");
        app.setMSPLAN("Medical Supplement NV");
        app.setReplaceExistingMedSup(YES);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);
        //Replacement Notice Page
        app.setCommonReplacementNoticeAnswersWithApplicantInfo();
        app.setCommonHealthHistoryAnswers();
        app.setReplaceExistingMedSup(YES);
        app.setReplacementReason("OtherReason");
        app.setApplicantPrintedNameAdd("AppName");
        app.setApplicantAddress("AppAdd");
        app.setRNOther("Cheaper");
        app.setHIV("NotHidden");

        goTo(cheatPage);
        cheatPage.fillAndSubmit(sheet);

        whatYouNeedPage.clickNextAndWaitForSpinnerToFinish();
        electronicSignatureAndDocumentConsentPage.clickNextAndWaitForSpinnerToFinish();
        aboutYouPage.fillAndSubmit(app, sheet);
        planSelectionAndStartDatePage.fillAndSubmit(app);
        planApplicationQuestionsPage.fillAndSubmit(app);
        eligibilityHealthQuestionsPage.fillAndSubmit(app);
        healthHistoryQuestionsPage.fillAndSubmit(app);
        pastAndCurrentCoveragePage.fillAndSubmit(app);
        authorizationPage.fillAndSubmit(app);
        planPaymentOptionsPage.fillAndSubmit(app);
        reviewAndSubmitPage.fillAndSubmit(app);

        expectedSubmissionResult.setPendingInfo("UNDERWRITING ELIGIBILITY", "REVIEW FOR POSSIBLE ESRD");
        submissionQuery.verifySubmissionData(app, expectedSubmissionResult);
        submissionQuery.verifyAdjudicationData(app, expectedSubmissionResult);

    }
    @Test
    public void test_wisconsin_guranteed_issue() throws Exception {

        sheet.setState("WI");
        sheet.setZip("54001");
        sheet.setRiderChoice1("OW1");
        sheet.setRiderChoice2("");
        sheet.setRiderChoice3("QW1");
        sheet.setRiderChoice4("");
        sheet.setRiderChoice5("");
        sheet.setAarpMemid("y");
        sheet.setDOB(DateUtils.getDOBofPersonTurningAgeToday(65));
        sheet.setEffDate(DateUtils.getFirstDayOfFutureMonth(1));
        sheet.setPsd(DateUtils.getFirstDayOfFutureMonth(1));
        sheet.setPlanCode("MW1");
        sheet.setReferrer("uLayer");

        //TestData
        app.setAARPMembershipNumber(faker.numerify("##########"));
        app.setPrefix("MR");
        app.setFirstName(this.faker.firstName()+"  twoSpaces");
        app.setMI(this.faker.letterify("?"));
        app.setLastName(this.faker.lastName()+" oneSpace");
        app.setSuffix("PHD");
        app.setAddressLine1("111 Street dr");
        app.setAddressLine2("apt #123");
        app.setCity("Horsham");
        app.setEmail("test@uhc.com");
        app.setConfirmEmail("test@uhc.com");
        app.setPhonePrimary("9874562345");
        app.setPhoneEvening("1234561234");
        app.setGender("M");
        app.setMedicareClaimNum(faker.bothify("A#########"));
        app.setMPAED("01/01/2010");
        app.setPartABActiveIndicator(YES);
        //Plan Application Page
        app.setTobaccoUse(YES);
        app.setLostCoverage(NO);
        app.setTurned65In6GA(YES);
        app.setPartBIn6GA(YES);
        app.setPlanEffIn6OfEligible(YES);
        //Eligibility Page
        app.setESRD(NO);
        app.setSurgeryNeeded(NO);
        //Authorizationa and verififcation page
        app.setDesignateLapse(YES);
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
        app.setMSInsCompany("Blue Cross Blue Shield NV");
        app.setMSPLAN("Medical Supplement NV");
        app.setReplaceExistingMedSup(YES);
        app.setOtherInsCoverage(YES);
        app.setOtherInsCompany("Blue Cross Blue Shield");
        app.setOtherInsType("HMO");
        app.setOtherInsStart("01/01/2001");
        app.setOtherInsEnd("01/01/2014");
        app.setOtherInsReplace(YES);
        app.setCpaSignatureInd(YES);
        //Authorizationa and verififcation page
        app.setDesignateLapse(NO);
        app.setAuxFirstName("AuxFirstName");
        app.setAuxMI("M");
        app.setAuxLastName("AuxLastName");
        app.setAuxAddressLine1("AuxAddressLine1");
        app.setAuxCity("AuxCity");
        app.setAuxState("NV");
        app.setAuxZipCode("89101");
        //Replacement Notice Page
        app.setReplaceExistingMedSup(YES);
        app.setReplacementReason("OtherReason");
        app.setApplicantPrintedNameAdd("AppName");
        app.setApplicantAddress("AppAdd");
        app.setRNOther("Cheaper");
        app.setCommonReplacementNoticeAnswersWithApplicantInfo();
        app.setApplicantRNSignatureIndRequired("");

        goTo(cheatPage);
        cheatPage.fillAndSubmit(sheet);

        whatYouNeedPage.clickNextAndWaitForSpinnerToFinish();
        electronicSignatureAndDocumentConsentPage.clickNextAndWaitForSpinnerToFinish();
        aboutYouPage.fillAndSubmit(app, sheet);
        planSelectionAndStartDatePage.fillAndSubmit(app);
        planApplicationQuestionsPage.fillAndSubmit(app);
        pastAndCurrentCoveragePage.fillAndSubmit(app);
        authorizationPage.fillAndSubmit(app);
        planPaymentOptionsPage.fillAndSubmit(app);
        reviewAndSubmitPage.fillAndSubmit(app);

        submissionQuery.verifyPlanAndRiderCodes(app, sheet, expectedSubmissionResult);

    }
}
