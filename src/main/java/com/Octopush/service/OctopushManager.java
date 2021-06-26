package com.Octopush.service;

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
			response = processRequest(apiSmsOnListStatusUrl, HttpMethod.GET, request);

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
			logger.info("Success: " + response.getStatusCode());
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
			logger.info("Success: " + response.getStatusCode());
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
			logger.info("Success: " + response.getStatusCode());
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
			logger.info("Success: " + response.getStatusCode());
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
			logger.info("Success: " + response.getStatusCode());
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
	
	public Object deleteContactList(String contactList) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(contactList, requestHeaders);
			response = processRequest(apiRemoveContactList, HttpMethod.DELETE, request);
			logger.info("Success: " + response.getStatusCode());
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
			logger.info("Success: " + response.getStatusCode());
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
	public Object checkBalance(String data) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiCheckBalance, HttpMethod.GET, request);
			logger.info("Success: " + response.getStatusCode());
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
	public Object checkCredit(String data) {
		ResponseEntity<Object> response = null;
		try {
			request = new HttpEntity<String>(data,requestHeaders);
			response = processRequest(apiCheckCredit, HttpMethod.GET, request);
			logger.info("Success: " + response.getStatusCode());
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

	}
}
