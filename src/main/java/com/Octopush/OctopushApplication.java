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
		System.out.println("8: Delete Contacts");
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
		System.out.println("19: Modify Configuration");
		System.out.println("20: Retrieve Configuration");
		System.out.println("21: Send Voice SMS ");
		System.out.println("22: Send Voice SMS List ");
		System.out.println("23: Status Voice SMS");
		System.out.println("24: Confirm Voice SMS ");
		System.out.println("25: Cancel  Voice SMS\n");

		System.out.print("\n Enter your choice: ");

		input = scanner.nextInt();

		switch (input) {
		case 1:
			sendSms();
			break;
		case 2:
			smsToAList();
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
			removeContacts();
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
			modifyParameter();
			break;
		case 20:
			retrieveParameter();
			break;
		case 21:
			sendVoiceSms();
			break;
		case 22:
			sendVoiceSmsToAList();
			break;
		case 23:
			getVoiceSmsToAListStatus();
			break;
		case 24:
			confirmVoiceSmsToAList();
			break;
		case 25:
			cancelVoiceSmsToAList();
			break;

		default:
			System.out.println("Enter valid input!!");
		}

		scanner.close();
	}

	public static void sendSms() {

		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
		List<Recipient> recipients = new ArrayList<Recipient>();

		String type = "sms_premium";
		String text = "This is dummy Text";
		String purpose = "alert";
		String sender = "company";
		String send_at = "";
		String requestID = "";
		boolean simulation_mode = false;
		boolean auto_optimize_text = true;

		recipient1.setPhone_number("+923214406262");
		recipient1.setParam1("Alex");

		recipient2.setPhone_number("+33689028116");
		recipient2.setParam2("Bob");

		recipients.add(recipient1);
		recipients.add(recipient2);

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

	public static void smsToAList() {

		String type = "sms_premium";
		String text = "dummy";
		String purpose = "alert";
		String sender = "company";
		String send_at = "";
		String requestID = "";
		boolean simulation_mode = false;
		boolean auto_optimize_text = true;

		String listName = "yuniList";

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
		String ticket_number = "sms_611fc09bb79ac685890484";
		System.out.println(octopushManager.getStatusListSMS(ticket_number));

	}

	public static void getConfirmation() {
		String ticket_number = "sms_611fba6bc84d2836383624";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);

		System.out.println(octopushManager.confirmListSMS(octopushManager.returnJson(getListSMS)));
	}

	public static void cancelListSms() {
		String ticket_number = "sms_611fc09bb79ac685890484";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);

		System.out.println(octopushManager.cancelListSMS(octopushManager.returnJson(getListSMS)));
	}

	public static void addContact() {
		Contact contact = new Contact();
		Contact contact2 = new Contact();
		List<Contact> contacts = new ArrayList<Contact>();
		String list_name = "yuniList";

		contact.setPhone_number("+33689028116");
		contact.setFirst_name("yuni");
		contact.setLast_name("guim");
		contact.setParam3("Alex");

		contact2.setPhone_number("+923214406262");
		contact2.setFirst_name("Fahad");
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
		String list_name = "yuniList";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);

		System.out.println(octopushManager.createContactList(octopushManager.returnJson(contactList)));
	}

	// List should be 1-200 Contacts
	public static void removeContacts() {
		String list_name = "yuniList";
		List<String> phoneNumbers = new ArrayList<String>();
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);
		String phoneNumber = "+923214406262";
		String phoneNumber2 = "+33689028116";

		phoneNumbers.add(phoneNumber);
		phoneNumbers.add(phoneNumber2);
		contactList.setPhone_numbers(phoneNumbers);

		System.out.println(octopushManager.removeContacts(octopushManager.returnJson(contactList)));
	}

	public static void emptyContactList() {
		String list_name = "yuniList";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);

		System.out.println(octopushManager.emptyContactList(octopushManager.returnJson(contactList)));
	}

	public static void removeContactList() {
		String list_name = "yuniList";
		ContactList contactList = new ContactList();
		contactList.setList_name(list_name);

		System.out.println(octopushManager.removeContactList(octopushManager.returnJson(contactList)));
	}

	// Should 1-200 numbers per request
	public static void hlrLookup() {
		ContactList contactList = new ContactList();
		List<String> phoneNumbers = new ArrayList<String>();
		String phoneNumber = "+923214406262";
		String phoneNumber2 = "+33611223344";

		phoneNumbers.add(phoneNumber);
		phoneNumbers.add(phoneNumber2);
		contactList.setPhone_numbers(phoneNumbers);

		System.out.println(octopushManager.hlrLookUP(octopushManager.returnJson(contactList)));
	}

	public static void checkSmsBalance() {

		String country_code = "FR";
		String product_name = "sms_premium";
		String with_details = "true";

		// System.out.println(octopushManager.checkBalance());
		System.out.println(octopushManager.checkBalance(with_details));
		// System.out.println(octopushManager.checkBalance(country_code,product_name));
		// System.out.println(octopushManager.checkBalance(country_code,product_name,with_details));

	}

	public static void checkCredit() {
		CreditConsultation creditConsultation = new CreditConsultation();
		// List<String> country_code = new ArrayList<String>();
		// country_code.add("AD");
		creditConsultation.setCountry_code("AD");
		// creditConsultation.setCountry_codes(country_code);
		System.out.println(octopushManager.checkCredit(octopushManager.returnJson(creditConsultation)));
	}

	public static void subAccountCreate() {
		SubAccount subAccount = new SubAccount();
		String first_name = "Fahad";
		String last_name = "Khan";
		String password = "Telenor12345!@#";
		String company_name = "Pakistan";

		//CallBacks Optional
		Callback callBacks = new Callback();
		callBacks.setCallback_url_for_inbounds("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_blacklisted_numbers("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_vocal_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setEmail_for_inbounds("myemail@email.org");
		
		//SubAccount
		subAccount.setFirst_name(first_name);
		subAccount.setLast_name(last_name);
		subAccount.setPassword(password);
		subAccount.setCompany_name(company_name);
		subAccount.setCallbacks(callBacks);

		System.out.println(octopushManager.subAccountCreate(octopushManager.returnJson(subAccount)));
	}

	public static void subAccountModify() throws Exception {
		SubAccount subAccount = new SubAccount();
		String first_name = "zFahad";
		String last_name = "zKhan";
		String password = "zTelenor12345!@#";
		String company_name = "zPakistan";
		String subAccountID = "pak_c91@sub-accounts.com";

		Callback callBacks = new Callback();
		callBacks.setCallback_url_for_inbounds("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_blacklisted_numbers("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_vocal_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setEmail_for_inbounds("myemail@email.org");

		// Required
		subAccount.setFirst_name(first_name);
		subAccount.setLast_name(last_name);
		subAccount.setPassword(password);

		// Optional
		subAccount.setCompany_name(company_name);
		subAccount.setCallbacks(callBacks);

		System.out.println(octopushManager.subAccountModify(octopushManager.returnJson(subAccount), subAccountID));
	}

	public static void subAccountInformation() {
		String subAccountID = "pak_c91@sub-accounts.com";
		System.out.println(octopushManager.subAccountInformation(subAccountID));
	}

	public static void subAccountTransferCredit() {
		SubAccount subAccount = new SubAccount();
		String token = "da793b6dd8058b5941e2763c8f95b8d5e661c89037db5cce87b9bd80254d27e9";
		String from_wallet_pack_id = "46908f1d-c513-11eb-a4c0-066a363ef6b4";
		int amount = 20;

		subAccount.setToken(token);
		subAccount.setFrom_wallet_pack_id(from_wallet_pack_id);
		subAccount.setAmount(amount);

		System.out.println(octopushManager.subAccountTransferCredit(octopushManager.returnJson(subAccount)));
	}

	public static void subAccountTransferCreditToken() {
		SubAccount subAccount = new SubAccount();
		String email_to = "pak_c91@sub-accounts.com";

		subAccount.setEmail_to(email_to);

		System.out.println(octopushManager.subAccountTransferCreditToken(octopushManager.returnJson(subAccount)));
	}

	public static void sendVoiceSms() {
		// Required
		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
		List<Recipient> recipients = new ArrayList<Recipient>();

		String type = "sms_premium";
		String text = "This is a dummy Text. This is a dummy Text. This is a dummy Text.";
		String purpose = "WholeSale";
		String sender = "company";
		String voice_gender = "male";
		String voice_language = "en-US";

		// Optional
		// String send_at = "";
		// String requestID = "";
		// boolean simulation_mode = false;
		// boolean auto_optimize_text = true;

		recipient1.setPhone_number("+923214406262");
		recipient2.setPhone_number("+33689028116");

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

		System.out.println(octopushManager.sendVoiceSMS(octopushManager.returnJson(voiceSMS)));
	}

	public static void sendVoiceSmsToAList() {
		// Required
		Recipient recipient1 = new Recipient();
		Recipient recipient2 = new Recipient();
		List<Recipient> recipients = new ArrayList<Recipient>();

		String type = "sms_premium";
		String text = "This is a dummy Text. This is a dummy Text. This is a dummy Text.";
		String purpose = "WholeSale";
		String sender = "company";
		String voice_gender = "male";
		String voice_language = "en-US";

		String listName = "Friends";

		recipient1.setPhone_number("+923214406262");
		recipient2.setPhone_number("+33689028116");

		recipients.add(recipient1);
		recipients.add(recipient2);

		VoiceSMS voiceSMS = new VoiceSMS();
		voiceSMS.setText(text);
		voiceSMS.setSender(sender);
		voiceSMS.setType(type);
		voiceSMS.setPurpose(purpose);
		voiceSMS.setVoice_gender(voice_gender);
		voiceSMS.setVoice_language(voice_language);
		voiceSMS.setList_name(listName);

		System.out.println(octopushManager.sendVoiceSmsList(octopushManager.returnJson(voiceSMS)));
	}

	public static void getVoiceSmsToAListStatus() {
		String ticket_number = "voice_6131134271eac489381188";
		System.out.println(octopushManager.getVoiceSmsToAListStatus(ticket_number));
	}

	public static void confirmVoiceSmsToAList() {
		String ticket_number = "voice_6131134271eac489381188";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);

		System.out.println(octopushManager.confirmVoiceSmsToAList(octopushManager.returnJson(getListSMS)));
	}

	public static void cancelVoiceSmsToAList() {
		String ticket_number = "voice_6131154a40975181671990";

		SMSList getListSMS = new SMSList();
		getListSMS.setTicket_number(ticket_number);

		System.out.println(octopushManager.cancelVoiceSmsToAList(octopushManager.returnJson(getListSMS)));
	}

	public static void modifyParameter() {
		DefaultParameter defaultParameter = new DefaultParameter();
		SmsCampaignAlert smsCampaignAlert = new SmsCampaignAlert();
		String alert_type = "sms_premium";
		int alert_bound = 1000;
		String alert_email_to = "fahad.khan@gmail.com";
		String alert_phone_number_to = "+923214406262";

		smsCampaignAlert.setAlert_type(alert_type);
		smsCampaignAlert.setAlert_bound(alert_bound);
		smsCampaignAlert.setAlert_email_to(alert_email_to);
		smsCampaignAlert.setAlert_phone_number_to(alert_phone_number_to);

		// CampaignParameter
		CampaignParameter campaignParameter = new CampaignParameter();
		String sender_for_sms_premium = "Octu";
		String sender_for_vocal_sms = "54321";
		String phone_number_for_test_sms = "+33611223344";

		campaignParameter.setSender_for_sms_premium(sender_for_sms_premium);
		campaignParameter.setSender_for_vocal_sms(sender_for_vocal_sms);
		campaignParameter.setPhone_number_for_test_sms(phone_number_for_test_sms);

		// CallBack
		Callback callBacks = new Callback();
		callBacks.setCallback_url_for_inbounds("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_blacklisted_numbers("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_vocal_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setCallback_url_for_sms_deliveries("https://webhook.site/be7a4405-e906-49f5-a79f-538bd713bce5");
		callBacks.setEmail_for_inbounds("myemail@email.org");

		defaultParameter.setCallbacks(callBacks);
		defaultParameter.setCampaign_parameters(campaignParameter);
		defaultParameter.setSms_campaign_alert_parameters(smsCampaignAlert);

		System.out.println(octopushManager.modifyParameter(octopushManager.returnJson(defaultParameter)));
	}

	public static void retrieveParameter() {
		System.out.println(octopushManager.retrieveParameter());
	}

}
