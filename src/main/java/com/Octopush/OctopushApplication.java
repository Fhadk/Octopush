package com.Octopush;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Octopush.model.Contact;
import com.Octopush.model.ContactList;
import com.Octopush.model.CreateListSMS;
import com.Octopush.model.CreditConsultation;
import com.Octopush.model.Recipient;
import com.Octopush.model.SMS;
import com.Octopush.model.SMSList;
import com.Octopush.service.OctopushManager;

public class OctopushApplication {
	public static OctopushManager octopushManager = new OctopushManager();
	
	public static void main(String[] args) {
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
		System.out.println("8: Empty Contact List");
		System.out.println("10: Delete Contact List");
		System.out.println("11: Remove Contact List");
		System.out.println("12: HLR LookUp");
		System.out.println("13: Check Sms Balance");
		System.out.println("14: Check Credit \n");
//		System.out.println("15: SMS Send");
//		System.out.println("16: SMS Send");
//		System.out.println("17: SMS Send");
//		System.out.println("18: SMS Send \n");
		System.out.print("Enter your choice: ");
		
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
			case 6:
				cancelListSms();
				break;
			case 7:
				addContact();
				break;
			case 8:
				createContactList();
				break;
			case 9:
				deleteContactList();
				break;
			case 10:
				emptyContactList();
				break;
			case 11:
				removeContactList();
				break;
			case 12:
				hlrLookup();
				break;
			case 13:
				checkSmsBalance();
				break;
			case 14:
				checkCredit();
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
		
		recipient2.setPhone_number("+923154213516");
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
		
		System.out.println(octopushManager.deleteContactList(octopushManager.returnJson(contactList)));
	}
	
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
		List<String> country_code = new ArrayList<String>();
		country_code.add("AD");
		creditConsultation.setCountry_codes(country_code);
		System.out.println(octopushManager.checkCredit(octopushManager.returnJson(creditConsultation)));
	}
	
	
}
