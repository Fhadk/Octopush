package com.Octopush.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * @author Fhadk
 */

public class OctopushManager {
	
	// CREDENTIALS //
	private String apiLogin;
	private String apiKey;
	
	// SMS //
	private String apiSendSmsUrl;
	private String apiSmsOnListCreateUrl;
	private String apiSmsOnListStatusUrl;
	private String apiSmsOnListConfirmUrl;
	private String apiSmsOnListCancelUrl;
	
	// Contcat//
	private String apiHRL;
	private String apiRemoveContactList;
	private String apiEmptyContactList;
	private String apiDeleteContact;
	private String apiCreateContactList;
	private String apiAddContact;
	
	// CreditConsultation //
	private String apiCheckBalance;
	private String apiCheckCredit;
	
	// CreditConsultation
	private String apiSubAccountCreate;
	private String apiSubAccountEdit;
	private String apiSubAccountInformation;
	private String apiSubAccountCreditTransfer;
	
	// VoiceSMS
	private String apiVoiceSmsSend;
	private String apiVoiceSmsSendList;
	private String apiVoiceSmsStatus;
	private String apiVoiceSmsConfirm;
	private String apiVoiceSmsCancel;
	
	// Default Paramter
	private String apiRetrieveParamter;
	private String apiModifyParamter;

	private HttpEntity<String> request;
	private ResponseEntity<Object> response;
	private HttpHeaders requestHeaders;
	private RestTemplate restTemplate;
	private Logger logger = Logger.getLogger(OctopushManager.class);

	public OctopushManager() {

		try {
			loadProperties();

			requestHeaders = new HttpHeaders();
			restTemplate = new RestTemplate();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.set("api-key", apiKey.trim());
			requestHeaders.set("api-login", apiLogin);
			requestHeaders.set("cache-control", "no-cache");
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param smsList
	 * @return
	 */

	public Object getStatusListSMS(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket,requestHeaders);
			response = processRequest(apiSmsOnListStatusUrl.replaceAll("<ticket_number>", smsTicket), HttpMethod.GET, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}

	public Object confirmListSMS(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket, requestHeaders);
			response = processRequest(apiSmsOnListConfirmUrl, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}

	/**
	 * 
	 * @param smsTicket
	 * @return
	 */
	public Object cancelListSMS(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket, requestHeaders);
			response = processRequest(apiSmsOnListCancelUrl, HttpMethod.DELETE, request);
			if(response.getStatusCode() ==  HttpStatus.NO_CONTENT) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.info("Error: " + response.getStatusCode());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}

	/**
	 * 
	 * @param smsList
	 * @return
	 */

	public Object createListSMS(String smsList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsList, requestHeaders);
			response = processRequest(apiSmsOnListCreateUrl, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}

	/**
	 * 
	 * @param SMSh
	 * @return
	 */
	public Object sendSMSCampaign(String SMS) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(SMS, requestHeaders);
			response = processRequest(apiSendSmsUrl, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	/**
	 * 
	 * @param contact
	 * @return
	 */

	public Object addContact(String contact) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contact, requestHeaders);
			response = processRequest(apiAddContact, HttpMethod.POST, request);
			
			if(response.getStatusCode() ==  HttpStatus.CREATED) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	
	/**
	 * 
	 * @param contactList
	 * @return
	 */
	
	public Object createContactList(String contactList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contactList, requestHeaders);
			response = processRequest(apiCreateContactList, HttpMethod.POST, request);
			
			if(response.getStatusCode() ==  HttpStatus.CREATED) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	
	/**
	 * 
	 * @param contactList
	 * @return
	 */
	
	public Object deleteContact(String contactList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contactList, requestHeaders);
			response = processRequest(apiDeleteContact, HttpMethod.DELETE, request);
			
			if(response.getStatusCode() ==  HttpStatus.NO_CONTENT) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	
	
	/**
	 * 
	 * @param contactList
	 * @return
	 */
	public Object emptyContactList(String contactList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contactList, requestHeaders);
			response = processRequest(apiEmptyContactList, HttpMethod.POST, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	
	/**
	 * 
	 * @param contactList
	 * @return
	 */
	
	public Object removeContactList(String contactList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contactList, requestHeaders);
			response = processRequest(apiRemoveContactList, HttpMethod.DELETE, request);
			if(response.getStatusCode() ==  HttpStatus.NO_CONTENT) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	/**
	 * 
	 * @param phoneNumbers
	 * @return
	 */
	public Object hlrLookUP(String phoneNumbers) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(phoneNumbers, requestHeaders);
			response = processRequest(apiHRL, HttpMethod.POST, request);
			
			if(response.getStatusCode() ==  HttpStatus.CREATED) {
				logger.info("Success: " + response.getStatusCode());
			}else if(response.getStatusCode() ==  HttpStatus.BAD_REQUEST) {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Object checkBalance(String data) {   // need to replace
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiCheckBalance, HttpMethod.GET, request);
			
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Object checkCredit(String data) {   // need to replace
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiCheckCredit, HttpMethod.GET, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}

	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Object subAccountCreate(String data) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiSubAccountCreate, HttpMethod.POST, request);
			if(response.getStatusCode() ==  HttpStatus.CREATED) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public Object subAccountModify(String data, String accountID) throws Exception {
		ResponseEntity<Object> response = null;
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		try {
			requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setConnectTimeout(1000);
			requestFactory.setReadTimeout(1000);
			restTemplate.setRequestFactory(requestFactory);
			
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiSubAccountEdit.replaceAll("<accountID>",accountID), HttpMethod.PATCH, request);
			
			if(response.getStatusCode() ==  HttpStatus.CREATED) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			requestFactory.destroy();
		}
		return response.getStatusCode();
	}
	
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Object subAccountInformation(String subAccountID) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(requestHeaders);
			response = processRequest(apiSubAccountInformation.replaceFirst("<accountID>", subAccountID), HttpMethod.GET, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	
	public Object subAccountTransferCredit(String data) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(requestHeaders);
			response = processRequest(apiSubAccountCreditTransfer, HttpMethod.POST, request);
			logger.info("Success: " + response.getStatusCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	
	public Object subAccountTransferCreditToken(String data) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(requestHeaders);
			response = processRequest(apiSubAccountCreditTransfer, HttpMethod.POST, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	
	/**
	 * 
	 * @param SMSh
	 * @return
	 */
	public Object sendVoiceSMS(String SMS) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(SMS, requestHeaders);
			response = processRequest(apiVoiceSmsSend, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	
	/**
	 * 
	 * @param SMS
	 * @return
	 */
	public Object sendVoiceSmsList(String SMS) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(SMS, requestHeaders);
			response = processRequest(apiVoiceSmsSendList, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	/**
	 * 
	 * @param smsList
	 * @return
	 */

	public Object getVoiceSmsStatus(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket,requestHeaders);
			response = processRequest(apiVoiceSmsStatus.replaceAll("<ticket_number>", smsTicket), HttpMethod.GET, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}

	public Object getVoiceSmsConfirmation(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket, requestHeaders);
			response = processRequest(apiVoiceSmsConfirm, HttpMethod.POST, request);

			if (HttpStatus.CREATED != null) {
				logger.info("Created-Response: " + response.getBody());
				return response.getBody();
			} else if (HttpStatus.BAD_REQUEST != null) {
				logger.error("BadRequest-Response: " + response.getBody());
				return response.getBody();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	
	
	/**
	 * 
	 * @param smsTicket
	 * @return
	 */
	public Object cancelVoiceListSms(String smsTicket) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(smsTicket, requestHeaders);
			response = processRequest(apiVoiceSmsCancel, HttpMethod.DELETE, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}
	

	/**
	 * 
	 * @param data
	 * @return
	 */
	public Object modifyParamter(String data) {
		ResponseEntity<Object> response = null;
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		try {
			requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setConnectTimeout(1000);
			requestFactory.setReadTimeout(1000);
			restTemplate.setRequestFactory(requestFactory);
			
			request = new HttpEntity<String>(data, requestHeaders);
			response = processRequest(apiModifyParamter, HttpMethod.PATCH, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getStatusCode();
	}

	
	/**
	 * 
	 * @param
	 * @return
	 */
	public Object retrieveParamter() {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(requestHeaders);
			response = processRequest(apiRetrieveParamter, HttpMethod.GET, request);
			if(response.getStatusCode() ==  HttpStatus.OK) {
				logger.info("Success: " + response.getStatusCode());
			}else {
				logger.error("Error: " + response.getStatusCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return response.getBody();
	}
	

	
	ResponseEntity<Object> processRequest(String URL, HttpMethod methodtype, HttpEntity<String> request) {
		response = restTemplate.exchange(URL, methodtype, request, Object.class);
		return response;
	}

	public String returnJson(Object object) {
		return new Gson().toJson(object);
	}

	private void loadProperties() {
		Properties prop = new Properties();
		InputStream inputStream;
		String propFileName = "application.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}

		// LOGIN //
		apiLogin = prop.getProperty("api-login");
		apiKey = prop.getProperty("api-key");
		// SMS //
		apiSendSmsUrl = prop.getProperty("apiSend");
		apiSmsOnListCreateUrl = prop.getProperty("apiListCreate");
		apiSmsOnListStatusUrl = prop.getProperty("apiListStatus");
		apiSmsOnListConfirmUrl = prop.getProperty("apiListConfirm");
		apiSmsOnListCancelUrl = prop.getProperty("apiListCancel");
		// Contact //
		apiAddContact = prop.getProperty("apiAddContact");
		apiCreateContactList = prop.getProperty("apiCreateContactList");
		apiDeleteContact = prop.getProperty("apiDeleteContact");
		apiEmptyContactList = prop.getProperty("apiEmptyContactList");
		apiRemoveContactList = prop.getProperty("apiRemoveContactList");
		apiHRL = prop.getProperty("apiHRL-LookUp");
		// CreditConsultation
		apiCheckBalance = prop.getProperty("apiCheckBalance");
		apiCheckCredit = prop.getProperty("apiCheckCredit");
		// SubAccount
		apiSubAccountCreate = prop.getProperty("apiSubAccountCreate");
		apiSubAccountEdit = prop.getProperty("apiSubAccountEdit");
		apiSubAccountInformation = prop.getProperty("apiSubAccountInformation");
		apiSubAccountCreditTransfer = prop.getProperty("apiSubAccountCreditTransfer");
		// VoiceSMS
		apiVoiceSmsSend = prop.getProperty("apiVoiceSmsSend");
		apiVoiceSmsSendList = prop.getProperty("apiVoiceSmsSendList");
		apiVoiceSmsStatus = prop.getProperty("apiVoiceSmsStatus");
		apiVoiceSmsConfirm = prop.getProperty("apiVoiceSmsConfirm");
		apiVoiceSmsCancel = prop.getProperty("apiVoiceSmsCancel");
		//DefaultParameter
		apiModifyParamter = prop.getProperty("apiModifyParamter");
		apiRetrieveParamter = prop.getProperty("apiRetrieveParamter");
		

	}
}
