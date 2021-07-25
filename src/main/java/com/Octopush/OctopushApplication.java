package com.Octopush;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Octopush.model.Callback;
import com.Octopush.model.CampaignParameter;
import com.Octopush.model.Contact;
import com.Octopush.model.ContactList;
import com.Octopush.model.CreateListSMS;
import com.Octopush.model.CreditConsultation;
import com.Octopush.model.DefaultParameter;
import com.Octopush.model.Recipient;
import com.Octopush.model.SMS;
import com.Octopush.model.SMSList;
import com.Octopush.model.SmsCampaignAlert;
import com.Octopush.model.SubAccount;
import com.Octopush.model.VoiceSMS;
import com.Octopush.service.OctopushManager;

public class OctopushApplication {
	public static OctopushManager octopushManager = new OctopushManager();
	
	public static void main(String[] args) throws Exception {
		int input = Integer.MIN_VALUE;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please Select from below API:\n");
		System.out.println("1: SMS Send / Scheduling");
		System.out.println("2: Send SMS List");
		System.out.println("3: Get Status");
		System.out.println("4: Get Confirmation");
		System.out.println("5: Cancel SMS List");
		System.out.println("6: Add Contact");
		System.out.println("7: Create Contact List");
		System.out.println("8: Delete Contact List");
		System.out.println("9: Empty Contact List");
		System.out.println("10: Remove Contact List");
		System.out.println("11: HLR LookUp");
		System.out.println("12: Check Sms Balance");
		System.out.println("13: Check Credit");
		System.out.println("14: Create Sub Account");
		System.out.println("15: Modify Sub Account");
		System.out.println("16: Account Information");
		System.out.println("17: Transfer Credit");
		System.out.println("18: Transfer Credit Token");
		
		System.out.println("19: Retrieve Configuration ");
		System.out.println("20: Modify Configuration ");
		
		System.out.println("21: Send Voice SMS ");
		System.out.println("22: Send Voice SMS List ");
		System.out.println("23: Status Voice SMS");
		System.out.println("24: Confirm Voice SMS ");
		System.out.println("25: Cancel  Voice SMS\n");
		
		System.out.print("\n Enter your choice: ");
		
		input = scanner.nextInt();
		
		switch(input) {
			case 1:
				sendSms();
				break;
			case 2:
				smsList();
				break;
			case 3:
				getStatus();
				break;
			case 4:
				getConfirmation();
				break;
			case 5:
				cancelListSms();
				break;
			case 6:
				addContact();
				break;
			case 7:
				createContactList();
				break;
			case 8:
				deleteContactList();
				break;
			case 9:
				emptyContactList();
				break;
			case 10:
				removeContactList();
				break;
			case 11:
				hlrLookup();
				break;
			case 12:
				checkSmsBalance();
				break;
			case 13:
				checkCredit();
				break;
			case 14:
				subAccountCreate();
				break;
			case 15:
				subAccountModify();
				break;
			case 16:
				subAccountInformation();
				break;
			case 17:
				subAccountTransferCredit();
				break;
			case 18:
				subAccountTransferCreditToken();
				break;
			case 19:
				modifyParamter();
				break;
			case 20:
				retrieveParamter();
				break;
			case 21:
				sendVoiceSms();
				break;
			case 22:
				sendVoiceSmsList();
				break;
			case 23:
				getVoiceSmsStatus();
				break;
			case 24:
				getVoiceSmsConfirmation();
				break;
			case 25:
				cancelVoiceListSms();
				break;
				
			default:
				System.out.println("Enter valid input!!");
		}
		
		scanner.close();
	}
	
	
	public static void sendSms() {
		
		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
		List<Recipient> recipients =  new ArrayList<Recipient>();
		
		
		String type = "sms_premium";
		String text = "This is dummy Text";
		String purpose = "WholeSale";
		String sender = "company";
		String send_at = "";
		String requestID = "";
		boolean simulation_mode = false;
		boolean auto_optimize_text = true;
		
		
		recipient1.setPhone_number("+923214406262");
		recipient1.setParam1("Alex");
		
//		recipient2.setPhone_number("+923154213516");
//		recipient2.setParam2("Bob");
		
		recipients.add(recipient1);
//		recipients.add(recipient2);
		
		// Setting values
		
		SMS SMS = new SMS();
		
		SMS.setRecipients(recipients);
		SMS.setText(text);
		SMS.setSender(sender);
		SMS.setType(type);
		SMS.setPurpose(purpose);
		SMS.setSend_at(send_at.toString());
		SMS.setAuto_optimize_text(auto_optimize_text);
		SMS.setSimulation_mode(simulation_mode);
		SMS.setRequest_id(requestID);
		
		System.out.println(octopushManager.sendSMSCampaign(octopushManager.returnJson(SMS)));
	}
	
	public static void smsList() {
		
		String type = "sms_premium";
		String text = "dummy";
		String purpose = "WholeSale";
		String sender = "company";
		String send_at = "";
		String requestID = "";
		boolean simulation_mode = false;
		boolean auto_optimize_text = true;
		
		String listName = "Friends";
		
		CreateListSMS listSMS = new CreateListSMS();
		listSMS.setList_name(listName);
		listSMS.setText(text);
		listSMS.setSender(sender);
		listSMS.setType(type);
		listSMS.setPurpose(purpose);
		listSMS.setSend_at(send_at.toString());
		listSMS.setAuto_optimize_text(auto_optimize_text);
		listSMS.setSimulation_mode(simulation_mode);
		listSMS.setRequest_id(requestID);

		System.out.println(octopushManager.createListSMS(octopushManager.returnJson(listSMS)));
	}
	
	public static void getStatus() {		
		String ticket_number = "sms_60d062ff13cea996286928";
	
		SMSList listSMS = new SMSList();
		listSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.getStatusListSMS(octopushManager.returnJson(listSMS)));
	}
	
	public static void getConfirmation() {
		String ticket_number = "sms_60cdcd5e3a079065328438";
	
		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.confirmListSMS(octopushManager.returnJson(getListSMS)));
	}
	
	public static void cancelListSms() {
		String ticket_number = "sms_60cdcd5e3a079065328438";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.cancelListSMS(octopushManager.returnJson(getListSMS)));
	}
	
	public static void addContact() {
		Contact contact = new Contact();
		Contact contact2 = new Contact();
		List<Contact> contacts =  new ArrayList<Contact>();
		String list_name = "Friends";
				
		contact.setPhone_number("+33689028116");
		contact.setFirst_name("Fahad");
		contact.setLast_name("khan");
		contact.setParam3("Alex");
		
		contact2.setPhone_number("+923154213516");
		contact2.setFirst_name("Ahsan");
		contact2.setLast_name("khan");
		contact2.setParam3("Bob");
		
		contacts.add(contact);
		contacts.add(contact2);
		
		// Setting values //
		
		ContactList contactList = new ContactList();
		
		contactList.setContacts(contacts);
		contactList.setList_name(list_name);
		
		System.out.println(octopushManager.addContact(octopushManager.returnJson(contactList)));
	}
	
	public static void createContactList() {
		String list_name = "Friends";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);  
		
		System.out.println(octopushManager.createContactList(octopushManager.returnJson(contactList)));
	}
	
	
	// List should be 1-200 Contacts
	public static void deleteContactList() {
		String list_name = "Friends";
		List<String> phoneNumbers =  new ArrayList<String>();
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);
		String phoneNumber = "+923214406262";
		String phoneNumber2 = "+33689028116";
		
		phoneNumbers.add(phoneNumber);
		phoneNumbers.add(phoneNumber2);
		contactList.setPhone_numbers(phoneNumbers);
		
		System.out.println(octopushManager.deleteContact(octopushManager.returnJson(contactList)));
	}
	
	public static void emptyContactList() {
		String list_name = "Friends";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);
		
		System.out.println(octopushManager.emptyContactList(octopushManager.returnJson(contactList)));
	}
	
	public static void removeContactList() {
		String list_name = "Friends";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);
		
		System.out.println(octopushManager.removeContactList(octopushManager.returnJson(contactList)));
	}
	
	// Should 1-200 numbers per request
	public static void hlrLookup() {
		ContactList contactList = new ContactList();
		List<String> phoneNumbers =  new ArrayList<String>();
		String phoneNumber = "+923214406262";
		String phoneNumber2 = "+33611223344";
		
		phoneNumbers.add(phoneNumber);
		phoneNumbers.add(phoneNumber2);
		contactList.setPhone_numbers(phoneNumbers);
		
		System.out.println(octopushManager.hlrLookUP(octopushManager.returnJson(contactList)));
	}
	
	
	public static void checkSmsBalance() {
		CreditConsultation creditConsultation = new CreditConsultation();		
		System.out.println(octopushManager.checkBalance(octopushManager.returnJson(creditConsultation)));
	}
	
	
	public static void checkCredit() {	
		CreditConsultation creditConsultation = new CreditConsultation();
//		List<String> country_code = new ArrayList<String>();
//		country_code.add("AD");
		creditConsultation.setCountry_code("AD");
//		creditConsultation.setCountry_codes(country_code);
		System.out.println(octopushManager.checkCredit(octopushManager.returnJson(creditConsultation)));
	}
	
	public static void subAccountCreate () {	
		SubAccount subAccount = new SubAccount();
		String first_name = "Fahad";
		String last_name = "Khan";
		String password = "Telenor";
		String company_name = "";
		List<String> callBacks = new ArrayList<String>();
		
		subAccount.setFirst_name(first_name);
		subAccount.setLast_name(last_name);
		subAccount.setPassword("MXVuR2CZe4xHnpUFWok5G8LPOzIbDjEw");
		
		subAccount.setCompany_name(company_name);
		System.out.println(octopushManager.subAccountCreate(octopushManager.returnJson(subAccount)));
	}
	
	public static void subAccountModify () throws Exception {	
		SubAccount subAccount = new SubAccount();
		String first_name = "Fahad";
		String last_name = "Khan";
		String password = "Telenor";
		String company_name = "";
		String subAccountID = "kha_ae6@sub-accounts.com";
		List<Callback> callBacks = new ArrayList<Callback>();
		
		// Required
		subAccount.setFirst_name(first_name);
		subAccount.setLast_name(last_name);
		subAccount.setPassword("MXVuR2CZe4xHnpUFWok5G8LPOzIbDjEw");
		
		// Optional
		subAccount.setCompany_name(company_name);
		Callback callBack = new Callback();
		callBack.setUrl_inbound("https://dns.com/call-back-url-1");
		callBacks.add(callBack);
		
		subAccount.setCallbacks(callBacks);
		
		System.out.println(octopushManager.subAccountModify(octopushManager.returnJson(subAccount),subAccountID));
	}
	
	public static void subAccountInformation () {
		String subAccountID = "kha_ae6@sub-accounts.com";
		System.out.println(octopushManager.subAccountInformation(subAccountID));
	}
	
	 
	public static void subAccountTransferCredit() {	
		SubAccount subAccount = new SubAccount();
		String token  = "";  								// need to set value
		String from_wallet_pack_id = "";  				    // need to set value
		int amount = 10;
		
		subAccount.setToken(token);
		subAccount.setFrom_wallet_pack_id(from_wallet_pack_id);
		subAccount.setAmount(amount);
		
		System.out.println(octopushManager.subAccountTransferCredit(octopushManager.returnJson(subAccount)));
	}
	
	
	public static void subAccountTransferCreditToken() {	
		SubAccount subAccount = new SubAccount();
		String email_to  = "";  						    // need to set value
		
		subAccount.setEmail_to(email_to);
		
		System.out.println(octopushManager.subAccountTransferCreditToken(octopushManager.returnJson(subAccount)));
	}
	
	
	public static void sendVoiceSms() {
		// Required
		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
		List<Recipient> recipients =  new ArrayList<Recipient>();
		
		
		String type = "sms_premium";
		String text = "This is dummy Text";
		String purpose = "WholeSale";
		String sender = "company";
		String voice_gender = "male";
		String voice_language = "en-US";
		
		// Optional
//		String send_at = "";
//		String requestID = "";
//		boolean simulation_mode = false;
//		boolean auto_optimize_text = true;
		
		
		recipient1.setPhone_number("+923214406262");
		recipient2.setPhone_number("+923154213516");
		
		recipients.add(recipient1);
		recipients.add(recipient2);
		
		// Setting values
		
		VoiceSMS voiceSMS = new VoiceSMS();
		
		voiceSMS.setRecipients(recipients);
		voiceSMS.setText(text);
		voiceSMS.setSender(sender);
		voiceSMS.setType(type);
		voiceSMS.setPurpose(purpose);
		voiceSMS.setVoice_gender(voice_gender);
		voiceSMS.setVoice_language(voice_language);
		
//		SMS.setSend_at(send_at.toString());
//		SMS.setAuto_optimize_text(auto_optimize_text);
//		SMS.setSimulation_mode(simulation_mode);
//		SMS.setRequest_id(requestID);
		
		System.out.println(octopushManager.sendVoiceSMS(octopushManager.returnJson(voiceSMS)));
	}
	
	public static void sendVoiceSmsList() {
		// Required
		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
        List<Recipient> recipients =  new ArrayList<Recipient>();
		
        String type = "sms_premium";
		String text = "This is dummy Text";
		String purpose = "WholeSale";
		String sender = "company";
		String voice_gender = "male";
		String voice_language = "en-US";
		
//		String listName = "Friends";
		
		recipient1.setPhone_number("+923214406262");
		recipient2.setPhone_number("+923154213516");
		
		recipients.add(recipient1);
		recipients.add(recipient2);
		
		VoiceSMS voiceSMS = new VoiceSMS();
		voiceSMS.setText(text);
		voiceSMS.setSender(sender);
		voiceSMS.setType(type);
		voiceSMS.setPurpose(purpose);
		voiceSMS.setVoice_gender(voice_gender);
		voiceSMS.setVoice_language(voice_language);
		
		System.out.println(octopushManager.sendVoiceSmsList(octopushManager.returnJson(voiceSMS)));
	}

	public static void getVoiceSmsStatus() {		
		String ticket_number = "sms_60d062ff13cea996286928";
	
		SMSList listSMS = new SMSList();
		listSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.getVoiceSmsStatus(octopushManager.returnJson(listSMS)));
	}
	
	public static void getVoiceSmsConfirmation() {
		String ticket_number = "sms_60cdcd5e3a079065328438";
	
		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.getVoiceSmsConfirmation(octopushManager.returnJson(getListSMS)));
	}
	
	public static void cancelVoiceListSms() {
		String ticket_number = "sms_60cdcd5e3a079065328438";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);
		
		System.out.println(octopushManager.cancelVoiceListSms(octopushManager.returnJson(getListSMS)));
	}
	
	
	public static void modifyParamter() {
		DefaultParameter defaultParameter =  new DefaultParameter();
		
//		SmsCampaignAlert
		List<SmsCampaignAlert> smsCampaignAlerts = new ArrayList<SmsCampaignAlert>();
		SmsCampaignAlert smsCampaignAlert = new SmsCampaignAlert();
		String alert_type = "";
		String alert_bound = "";
		String alert_email_to = "";
		String alert_phone_number_to = "";
		
		
		smsCampaignAlert.setAlert_type(alert_type);
		smsCampaignAlert.setAlert_bound(alert_bound);
		smsCampaignAlert.setAlert_email_to(alert_email_to);
		smsCampaignAlert.setAlert_phone_number_to(alert_phone_number_to);
		
		smsCampaignAlerts.add(smsCampaignAlert);
		
//		CampaignParameter
		List<CampaignParameter> campaignParameters = new ArrayList<CampaignParameter>();
		CampaignParameter campaignParameter = new CampaignParameter();
		String sender_for_sms_premium = "";
		String sender_for_vocal_sms = "";
		String phone_number_for_test_sms = "";
		
		campaignParameter.setSender_for_sms_premium(sender_for_sms_premium);
		campaignParameter.setSender_for_vocal_sms(sender_for_vocal_sms);
		campaignParameter.setPhone_number_for_test_sms(phone_number_for_test_sms);
		
		campaignParameters.add(campaignParameter);
		
//		CallBack
		List<Callback> callBacks = new ArrayList<Callback>();
		Callback callBack = new Callback();
		
		callBack.setUrl_inbound("https://dns.com/call-back-url-1");
		
		callBacks.add(callBack);		
		
		defaultParameter.setCallbacks(callBacks);
		defaultParameter.setCampaign_parameters(campaignParameters);
		defaultParameter.setSms_campaign_alert_parameters(smsCampaignAlerts);
		
		System.out.println(octopushManager.modifyParamter(octopushManager.returnJson(defaultParameter)));
	}
	
	
	public static void retrieveParamter() {	
		System.out.println(octopushManager.retrieveParamter());
	}
	
}
