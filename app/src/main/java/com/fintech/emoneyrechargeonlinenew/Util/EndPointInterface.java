package com.fintech.emoneyrechargeonlinenew.Util;

import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GenerateDepositOTPRequest;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GenerateDepositOTPResponse;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GetAEPSResponse;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GetAepsRequest;
import com.fintech.emoneyrechargeonlinenew.AccountOpen.dto.AccountOpenListRequest;
import com.fintech.emoneyrechargeonlinenew.AccountOpen.dto.AccountOpenListResponse;
import com.fintech.emoneyrechargeonlinenew.AccountSettlement.API.SettlementAccountResponse;
import com.fintech.emoneyrechargeonlinenew.AccountSettlement.API.UpdateSettlementAccountRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.AEPSReportRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.AEPSResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.BrowsePlan.dto.ResponsePlan;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DTHChannelPlanInfoRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanChannelAllResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanInfoAllResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanLanguageWiseRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.ROffer.dto.RofferResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Object.DmrRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AchieveTargetRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AggrePayTransactionUpdateRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AppUserListRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AppUserReffDetailRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AppUserRegisterRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.BalanceRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.BasicRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.CallBackRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.ChangePinPasswordRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.ContactUsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.DFStatusRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FetchBillRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FundDCReportRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FundTransferRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GatewayTransactionRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetChargedAmountRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetDMTReceiptRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetHLRLookUpRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetRoleForReferralRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetSenderRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.HeavyrefreshRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.IncentiveDetailRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.IntiateUPIRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.LedgerReportRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.LogoutRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.MoveToWalletRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.NewsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.OnboardingRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.OptionalOperatorRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.PayTMTransactionUpdateRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.PurchaseTokenRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.RealApiChangeRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.RefundLogRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.SignupRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.SubmitSocialDetailsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateBankRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateFcmRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateKycStatusRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateUPIRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateUserRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpgradePackageRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UserDayBookRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppBannerResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserReffDetailResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BalanceResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BankListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BasicResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.DFStatusResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.DMTReceiptResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.FetchBillResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.FundreqToResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GatwayTransactionResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GetAvailablePackageResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GetBankAndPaymentModeResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GetHLRLookUPResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.InitiateUpiResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.NumberListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.OnboardingResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.OperatorOptionalResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.RechargeReportResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.SlabCommissionResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.TransactionModeResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.UpdateKycResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.WalletTypeResponse;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.ASPayCollectRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.AppGetAMRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.AppGetAMResponse;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.FosAccStmtAndCollReportRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.FosAccStmtAndCollReportResponse;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.FosAppUserListRequest;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.dto.RSlabRangDetailRequest;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.dto.RSlabRangDetailResponse;
import com.fintech.emoneyrechargeonlinenew.DMRPipe.dto.ValiSenderRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DTHSubscriptionRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthSubscriptionReportRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthSubscriptionReportResponse;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageChannelRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageResponse;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.PincodeAreaRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.PincodeAreaResponse;
import com.fintech.emoneyrechargeonlinenew.Fragments.dto.GetUserResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.DthCustomerInfo.dto.DTHInfoResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanInfoResponse;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.InitiateMiniATMReq;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.InitiateMiniBankATMRes;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.UpdateMiniBankStatusReq;
import com.fintech.emoneyrechargeonlinenew.MoveToWallet.Api.MoveToBankReportRequest;
import com.fintech.emoneyrechargeonlinenew.MoveToWallet.Api.MoveToBankReportResponse;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.GetVAResponse;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.MapQRToUserRequest;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.UPIPaymentReq;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAListUPIPaymentRequest;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAListUPIPaymentResponse;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAVerifyResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface EndPointInterface {

    @Headers("Content-Type: application/json")
    @POST("App/GetNumberList?")
    Call<NumberListResponse> GetNumberList(@Body com.fintech.emoneyrechargeonlinenew.Util.NunberListRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/UserSubscriptionApp?")
    Call<BasicResponse> GetInTouch(@Body ContactUsRequest appInfo);


    @Headers("Content-Type: application/json")
    @POST("App/Login?")
    Call<LoginResponse> secureLogin(@Body com.fintech.emoneyrechargeonlinenew.Util.LoginRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/ValidateOTP?")
    Call<LoginResponse> ValidateOTP(@Body com.fintech.emoneyrechargeonlinenew.Util.OtpRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetBalance?")
    Call<BalanceResponse> Balancecheck(@Body BalanceRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetOpTypes?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.OpTypeResponse> GetOpTypes(@Body BasicRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetAvailablePackages?")
    Call<GetAvailablePackageResponse> GetAvailablePackages(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UpgradePackage?")
    Call<BasicResponse> UpgradePackage(@Body UpgradePackageRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/Transaction?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.RechargeCResponse> Recharge(@Body com.fintech.emoneyrechargeonlinenew.Util.RechargeRequest rechargeRequest);

    @Headers("Content-Type: application/json")
    @POST("App/LastRechargeReport?")
    Call<RechargeReportResponse> LastRechargeReport(@Body com.fintech.emoneyrechargeonlinenew.Util.RechargeReportRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/Logout?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.RechargeCResponse> Logout(@Body LogoutRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/GetOperatorOptionals?")
    Call<OperatorOptionalResponse> GetOperatorOptionals(@Body OptionalOperatorRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UserDaybook?")
    Call<AppUserListResponse> UserDaybook(@Body UserDayBookRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UserDaybookDMR?")
    Call<AppUserListResponse> UserDaybookDmt(@Body UserDayBookRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/RechargeReport?")
    Call<RechargeReportResponse> RechargeReport(@Body com.fintech.emoneyrechargeonlinenew.Util.RechargeReportRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/PSATransaction")
    Call<AppUserListResponse> GetAppPurchageToken(@Body PurchaseTokenRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetHLRLookUp?")
    Call<GetHLRLookUPResponse> GetHLRLookUp(@Body GetHLRLookUpRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/FundOrderReport?")
    Call<RechargeReportResponse> FundOrderReport(@Body LedgerReportRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("/App/CallOnboarding?")
    Call<OnboardingResponse> CallOnboarding(@Body OnboardingRequest onboardingRequest);

    @Headers("Content-Type: application/json")
    @POST("App/LedgerReport?")
    Call<RechargeReportResponse> LedgerReport(@Body LedgerReportRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/FundDCReport?")
    Call<RechargeReportResponse> FundDCReport(@Body FundDCReportRequest fundDCReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/RefundLog?")
    Call<AppUserListResponse> RefundLog(@Body RefundLogRequest fundDCReportRequest);

    @POST("App/MoveToWallet?")
    Call<TransactionModeResponse> MoveToWallet(@Body MoveToWalletRequest moveToWalletRequest);

    @Headers("Content-Type: application/json")
    @POST("App/FundRequestTo?")
    Call<FundreqToResponse> FundRequestTo(@Body BalanceRequest fundDCReportRequest);


    @Headers("Content-Type: application/json")
    @POST("App/GetBankAndPaymentMode?")
    Call<GetBankAndPaymentModeResponse> GetBankAndPaymentMode(@Body BalanceRequest fundDCReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/AppUserSignup?")
    Call<LoginResponse> AppUserSignup(@Body SignupRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetRoleForReferral?")
    Call<LoginResponse> GetRoleForReferral(@Body GetRoleForReferralRequest request);



    @Multipart
    @POST("App/AppFundOrder?")
    Call<GetBankAndPaymentModeResponse> AppFundOrder(@Part MultipartBody.Part file, @Part("UserFundRequest") RequestBody userRequest);


    @Headers("Content-Type: application/json")
    @POST("App/MyCommission?")
    Call<RechargeReportResponse> MyCommission(@Body BalanceRequest AppFundOrder);

    @Headers("Content-Type: application/json")
    @POST("App/GetBank?")
    Call<GetBankAndPaymentModeResponse> GetBank(@Body BalanceRequest AppFundOrder);

    @Headers("Content-Type: application/json")
    @POST("App/DisplayCommission?")
    Call<SlabCommissionResponse> DisplayCommission(@Body BalanceRequest AppFundOrder);

    @Headers("Content-Type: application/json")
    @POST("App/GetWalletType?")
    Call<WalletTypeResponse> GetWalletType(@Body BalanceRequest AppFundOrder);


    @Headers("Content-Type: application/json")
    @POST("App/GetSender?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.CreateSenderResponse> GetSender(@Body GetSenderRequest getSenderRequest);
    @Headers("Content-Type: application/json")
    @POST("App/GetSenderP?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.CreateSenderResponse> GetSenderNew(@Body GetSenderRequest getSenderRequest);

    @POST("App/GetTransactionMode")
    Call<TransactionModeResponse> GetTransactionMode(@Body BalanceRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/CreateSender?")
    Call<RechargeReportResponse> CreateSender(@Body GetSenderRequest createsenderRequest);
    @Headers("Content-Type: application/json")
    @POST("App/CreateSenderP?")
    Call<RechargeReportResponse> CreateSenderNew(@Body GetSenderRequest createsenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/VerifySender?")
    Call<RechargeReportResponse> VerifySender(@Body GetSenderRequest createsenderRequest);
    @Headers("Content-Type: application/json")
    @POST("App/VerifySenderP?")
    Call<RechargeReportResponse> VerifySenderNew(@Body GetSenderRequest createsenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/ChangePinOrPassword?")
    Call<RechargeReportResponse> ChangePinOrPassword(@Body ChangePinPasswordRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/ChangeDFStatus?")
    Call<DFStatusResponse> ChangeDFStatus(@Body DFStatusRequest createsenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/AppUserReffDetail?")
    Call<AppUserReffDetailResponse> AppUserReffDetail(@Body AppUserReffDetailRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppUserRegistraion?")
    Call<BasicResponse> AppUserRegistraion(@Body AppUserRegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetBankList?")
    Call<BankListResponse> GetBankList(@Body BalanceRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/AddBeneficiary?")
    Call<RechargeReportResponse> AddBeneficiary(@Body GetSenderRequest balanceRequest);
    @Headers("Content-Type: application/json")
    @POST("App/AddBeneficiaryP?")
    Call<RechargeReportResponse> AddBeneficiaryNew(@Body GetSenderRequest balanceRequest);


    @Headers("Content-Type: application/json")
    @POST("App/VerifyAccount?")
    Call<RechargeReportResponse> VerifyAccount(@Body GetSenderRequest balanceRequest);
    @Headers("Content-Type: application/json")
    @POST("App/VerifyAccountP?")
    Call<RechargeReportResponse> VerifyAccountNew(@Body GetSenderRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetCompanyProfile?")
    Call<AppUserListResponse> GetCompanyProfile(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetBeneficiary?")
    Call<RechargeReportResponse> GetBeneficiary(@Body GetSenderRequest balanceRequest);
    @Headers("Content-Type: application/json")
    @POST("App/GetBeneficiaryP?")
    Call<RechargeReportResponse> GetBeneficiaryNew(@Body GetSenderRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetChargedAmount?")
    Call<RechargeReportResponse> GetChargedAmount(@Body GetChargedAmountRequest getChargedAmountRequeat);
    @Headers("Content-Type: application/json")
    @POST("App/GetChargedAmountP?")
    Call<RechargeReportResponse> GetChargedAmountNew(@Body GetChargedAmountRequest getChargedAmountRequeat);

    @Headers("Content-Type: application/json")
    @POST("App/SendMoney?")
    Call<RechargeReportResponse> SendMoney(@Body com.fintech.emoneyrechargeonlinenew.Util.SendMoneyRequest sendMoneyRequest);
    @Headers("Content-Type: application/json")
    @POST("App/SendMoneyP?")
    Call<RechargeReportResponse> SendMoneyNew(@Body com.fintech.emoneyrechargeonlinenew.Util.SendMoneyRequest sendMoneyRequest);

    @Headers("Content-Type: application/json")
    @POST("App/DeleteBeneficiary?")
    Call<RechargeReportResponse> DeleteBeneficiary(@Body GetSenderRequest sendMoneyRequest);

    @Headers("Content-Type: application/json")
    @POST("App/DeleteBeneficiaryP?")
    Call<RechargeReportResponse> DeleteBeneficiaryNew(@Body GetSenderRequest sendMoneyRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetDMTReceipt?")
    Call<DMTReceiptResponse> GetDMTReceipt(@Body GetDMTReceiptRequest sendMoneyRequest);

    @Headers("Content-Type: application/json")
    @POST("App/DMTReport?")
    Call<RechargeReportResponse> DMTReport(@Body DmrRequest dmrRequest);


    @Headers("Content-Type: application/json")
    @POST("App/AppUserChildRoles?")
    Call<AppUserListResponse> AppUserChildRoles(@Body AppUserListRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/FundOrderPending?")
    Call<AppUserListResponse> FundOrderPending(@Body AppUserListRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppFundTransfer?")
    Call<AppUserListResponse> AppFundTransfer(@Body FundTransferRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppFundReject?")
    Call<AppUserListResponse> AppFundReject(@Body FundTransferRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/ChangeUserStatus?")
    Call<AppUserListResponse> ChangeUserStatus(@Body FundTransferRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppUserList?")
    Call<AppUserListResponse> AppUserList(@Body AppUserListRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/GetAppNews?")
    Call<AppUserListResponse> GetAppNews(@Body NewsRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetAppBanner?")
    Call<AppBannerResponse> GetAppBanner(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetProfile?")
    Call<GetUserResponse> GetProfile(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UpdateProfile?")
    Call<BasicResponse> UpdateProfile(@Body UpdateUserRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppDocumentDetails?")
    Call<UpdateKycResponse> UpdateKycApi(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UpdateFCMID?")
    Call<BasicResponse> UpdateFCMID(@Body UpdateFcmRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/ForgetPassword?")
    Call<BasicResponse> ForgetPassword(@Body com.fintech.emoneyrechargeonlinenew.Util.LoginRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/UpdateKYCStatus?")
    Call<UpdateKycResponse> UpdateKYCStatus(@Body UpdateKycStatusRequest request);



    @Multipart
    @POST("App/UploadDocs?")
    Call<BasicResponse> UploadDocs(@Part MultipartBody.Part file, @Part("userRequest") RequestBody userRequest);


    @Headers("Content-Type: application/json")
    @POST("App/DisplayCommissionLvl?")
    Call<SlabCommissionResponse> DisplayCommissionLvl(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetAppNotification?")
    Call<AppUserListResponse> GetAppNotification(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/FetchBill?")
    Call<FetchBillResponse> FetchBill(@Body FetchBillRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/RefundRequest?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.RefundRequestResponse> RefundRequest(@Body com.fintech.emoneyrechargeonlinenew.Util.RefundRequestRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/ChoosePaymentGateway?")
    Call<AppUserListResponse> ChoosePaymentGateway(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/PGatewayTransaction?")
    Call<GatwayTransactionResponse> GatewayTransaction(@Body GatewayTransactionRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/PayTMTransactionUpdate")
    Call<BasicResponse> PayTMTransactionUpdate(@Body PayTMTransactionUpdateRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/IntiateUPI")
    Call<InitiateUpiResponse> IntiateUPI(@Body IntiateUPIRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UPIPaymentUpdate")
    Call<InitiateUpiResponse> UPIPaymentUpdate(@Body UpdateUPIRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetVADetail?")
    Call<GetVAResponse> GetVADetail(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AEPSReport?")
    Call<AEPSResponse> getAEPSReport(@Body AEPSReportRequest aepsReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/RSlabRangDetail?")
    Call<RSlabRangDetailResponse> RSlabRangDetail(@Body RSlabRangDetailRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/GetPopupAfterLogin?")
    Call<BalanceResponse> GetPopupAfterLogin(@Body BalanceRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetTargetAchieved")
    Call<AppUserListResponse> GetTargetAchieved(@Body AchieveTargetRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetCallMeUserReq")
    Call<BasicResponse> GetCallMeUserReq(@Body CallBackRequest getSenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/ChangeRealAPIStatus?")
    Call<BasicResponse> ChangeRealAPIStatus(@Body RealApiChangeRequest createsenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/AppUpdateBank?")
    Call<BasicResponse> AppUpdateBank(@Body UpdateBankRequest request);

    @Multipart
    @POST("App/UploadProfile?")
    Call<BasicResponse> UploadProfile(@Part MultipartBody.Part file, @Part("userRequest") RequestBody userRequest);

    @POST("App/MakeW2RRequest")
    Call<com.fintech.emoneyrechargeonlinenew.Util.RefundRequestResponse> MakeW2RRequest(@Body W2RRequest w2RRequest);

    @POST("App/WTRLog")
    Call<AppUserListResponse> WTRLog(@Body RefundLogRequest wTRLogRequest);

    //Dth subscription

    @Headers("Content-Type: application/json")
    @POST("/App/GetDTHPackage?")
    Call<GetDthPackageResponse> GetDTHPackage(@Body GetDthPackageRequest onboardingRequest);

    @Headers("Content-Type: application/json")
    @POST("/App/DTHChannelByPackageID?")
    Call<GetDthPackageResponse> DTHChannelByPackageID(@Body GetDthPackageChannelRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/DTHSubscription?")
    Call<com.fintech.emoneyrechargeonlinenew.Util.RechargeCResponse> DTHSubscription(@Body DTHSubscriptionRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/DTHSubscriptionReport")
    Call<DthSubscriptionReportResponse> DTHSubscriptionReport(@Body DthSubscriptionReportRequest rechargeReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetPincodeArea?")
    Call<PincodeAreaResponse> GetPincodeArea(@Body PincodeAreaRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GenerateBenficiaryOTP?")
    Call<RechargeReportResponse> GenerateBenficiaryOTP(@Body GetSenderRequest balanceRequest);


    @Headers("Content-Type: application/json")
    @POST("App/ValidateBeneficiary?")
    Call<RechargeReportResponse> ValidateBeneficiary(@Body ValiSenderRequest valiSenderRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetAppRefferalContent?")
    Call<AppBannerResponse> GetAppRefferalContent(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/IncentiveDetail")
    Call<AppUserListResponse> IncentiveDetail(@Body IncentiveDetailRequest incentiveDetailRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GeUserCommissionRate")
    Call<BasicResponse> GeUserCommissionRate(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AccStmtAndColl")
    Call<FosAccStmtAndCollReportResponse> AccStmtAndColl(@Body FosAccStmtAndCollReportRequest FosAccStmtAndCollReportRequest);

    @Headers("Content-Type: application/json")
    @POST("App/ASPayCollect")
    Call<AppUserListResponse> ASPayCollect(@Body ASPayCollectRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AppGetAM")
    Call<AppGetAMResponse> AppGetAM(@Body AppGetAMRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetASCollectBank")
    Call<BankListResponse> GetASCollectBank(@Body BalanceRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/FOSRetailerList?")
    Call<AppUserListResponse> FOSRetailerList(@Body FosAppUserListRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/CheckFlagsEmail")
    Call<BasicResponse> CheckFlagsEmail(@Body BasicRequest appInfo);


    @Headers("Content-Type: application/json")
    @POST("App/SendEmailVerification")
    Call<BasicResponse> SendEmailVerification(@Body BasicRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/SaveSocialAlertSetting")
    Call<BasicResponse> SaveSocialAlertSetting(@Body SubmitSocialDetailsRequest appInfo);

    @Headers("Content-Type: application/json")
    @POST("App/GetAccountOpeningBanner")
    Call<AccountOpenListResponse> GetAccountOpeningList(@Body AccountOpenListRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/MapQRToUser")
    Call<BasicResponse> MapQRToUser(@Body MapQRToUserRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/GetAEPSBanks?")
    Call<BankListResponse> GetAEPSBanks(@Body BalanceRequest balanceRequest);

    @Headers("Content-Type: application/json")
    @POST("App/DepositNow")
    Call<GenerateDepositOTPResponse> DepositNow(@Body GenerateDepositOTPRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/AEPSWithdrawal?")
    Call<GetAEPSResponse> AEPSWithdrawal(@Body GetAepsRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/Aadharpay")
    Call<GetAEPSResponse> Aadharpay(@Body GetAepsRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetBalanceAEPS?")
    Call<GetAEPSResponse> GetBalanceAEPS(@Body GetAepsRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/BankMiniStatement?")
    Call<GetAEPSResponse> BankMiniStatement(@Body GetAepsRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/VerifyDepositOTP")
    Call<GenerateDepositOTPResponse> VerifyDepositOTP(@Body GenerateDepositOTPRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GenerateDepositOTP")
    Call<GenerateDepositOTPResponse> GenerateDepositOTP(@Body GenerateDepositOTPRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/InitiateMiniBank?")
    Call<InitiateMiniBankATMRes> initiateMiniBank(@Body InitiateMiniATMReq miniATMReq);

    @Headers("Content-Type: application/json")
    @POST("App/UpdateMiniBankStatus?")
    Call<InitiateMiniBankATMRes> updateMiniBankStatus(@Body UpdateMiniBankStatusReq bankStatusReq);

    @Headers("Content-Type: application/json")
    @POST("App/AggrePayTransactionUpdate")
    Call<BasicResponse> AggrePayTransactionUpdate(@Body AggrePayTransactionUpdateRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetASSumm")
    Call<FosAccStmtAndCollReportResponse> GetASSumm(@Body FosAccStmtAndCollReportRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/DoUPIPayment?")
    Call<RechargeReportResponse> doUPIPayment(@Body UPIPaymentReq upiPaymentReq);

    @Headers("Content-Type: application/json")
    @POST("App/RealTimeCommission")
    Call<RSlabRangDetailResponse> RealTimeCommission(@Body BasicRequest basicRequest);

    @Headers("Content-Type: application/json")
    @POST("App/MoveToBankReport")
    Call<MoveToBankReportResponse> MoveToBankReport(@Body MoveToBankReportRequest dmrRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetSettlementAccount")
    Call<SettlementAccountResponse> GetSettlementAccount(@Body BasicRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UpdateSettlementAccount")
    Call<BasicResponse> UpdateSettlementAccount(@Body UpdateSettlementAccountRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/DeleteSettlementAcount")
    Call<BasicResponse> DeleteSettlementAcount(@Body UpdateSettlementAccountRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/ToggleDefaulSettlementAcount")
    Call<BasicResponse> ToggleDefaultSettlementAcount(@Body UpdateSettlementAccountRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/VerifySettlementAccountOfUser")
    Call<BasicResponse> VerifySettlementAccountOfUser(@Body UpdateSettlementAccountRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/UpdateUTRByUser")
    Call<BasicResponse> UpdateUTRByUser(@Body UpdateSettlementAccountRequest request);


    @Headers("Content-Type: application/json")
    @POST("App/CashFreeTransactionUpdate")
    Call<BasicResponse> CashFreeTransactionUpdate(@Body PayTMTransactionUpdateRequest request);

    @FormUrlEncoded
    @POST("PGCallback/CashFreeStatusCheck")
    Call<InitiateUpiResponse> CashFreeStatusCheck(@Field("TID") String tid);

    @Headers("Content-Type: application/json")
    @POST("App/GetVPAListUPIPayement")
    Call<VPAListUPIPaymentResponse> GetVPAListUPIPayment(@Body VPAListUPIPaymentRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/VerifyUPI")
    Call<VPAVerifyResponse> VerifyUPI(@Body VPAListUPIPaymentRequest request);

    /*Plans ,ROffer,DTH Info,HeavyRefresh*/

    @Headers("Content-Type: application/json")
    @POST("App/ROffer?")
    Call<RofferResponse> ROffer(@Body com.fintech.emoneyrechargeonlinenew.Util.ROfferRequest rOfferRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetRNPRoffer")
    Call<RofferResponse> GetRNPRoffer(@Body com.fintech.emoneyrechargeonlinenew.Util.ROfferRequest rOfferRequest);


    @Headers("Content-Type: application/json")
    @POST("App/GetRNPDTHHeavyRefresh")
    Call<DthPlanInfoResponse> GetRNPDTHHeavyRefresh(@Body HeavyrefreshRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/DTHHeavyRefresh")
    Call<DthPlanInfoResponse> DTHHeavyRefresh(@Body HeavyrefreshRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/SimplePlan?")
    Call<ResponsePlan> Rechageplans(@Body com.fintech.emoneyrechargeonlinenew.Util.PlanRequest rOfferRequest);

    @Headers("Content-Type: application/json")
    @POST("App/RechSimplePlan")
    Call<ResponsePlan> RechargePlansUpdated(@Body com.fintech.emoneyrechargeonlinenew.Util.PlanRequest rOfferRequest);

    /*false*/
    @Headers("Content-Type: application/json")
    @POST("App/DTHCustomerInfo?")
    Call<DTHInfoResponse> DTHCustomerInfo(@Body com.fintech.emoneyrechargeonlinenew.Util.ROfferRequest dthInfoRequest);

    /*true*/
    @Headers("Content-Type: application/json")
    @POST("App/GetRNPDTHCustInfo")
    Call<DTHInfoResponse> GetRNPDTHCustInfo(@Body com.fintech.emoneyrechargeonlinenew.Util.ROfferRequest dthInfoRequest);


    /*True*/
    @Headers("Content-Type: application/json")
    @POST("App/GetDTHSimplePlan")
    Call<DthPlanInfoAllResponse> GetDTHSimplePlan(@Body ROfferRequest dthInfoRequest);

    /*false*/
    @Headers("Content-Type: application/json")
    @POST("App/DTHSimplePlanInfo?")
    Call<DthPlanInfoResponse> DTHSimplePlanInfo(@Body com.fintech.emoneyrechargeonlinenew.Util.ROfferRequest dthInfoRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetDTHChannelList")
    Call<DthPlanChannelAllResponse> GetDTHChannelList(@Body DTHChannelPlanInfoRequest request);

    @Headers("Content-Type: application/json")
    @POST("App/GetDTHPlanListByLanguage")
    Call<DthPlanInfoResponse> GetDTHPlanListByLanguage(@Body DthPlanLanguageWiseRequest dthInfoRequest);

    @Headers("Content-Type: application/json")
    @POST("App/GetDTHPlanByLang")
    Call<DthPlanInfoAllResponse> GetDTHPlanByLang(@Body DthPlanLanguageWiseRequest dthInfoRequest);

    @Headers("Content-Type: application/json")
    @POST("/App/DTHChannelPlanInfo")
    Call<DthPlanInfoResponse> DTHChannelPlanInfo(@Body DTHChannelPlanInfoRequest request);

}
