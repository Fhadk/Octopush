//package com.Octopush.controller;
//
///**
// * 
// * @author Fhadk
// *
// */
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Octopush.service.OctopushManager;
//
//@RestController
//@RequestMapping("/Octopush")
//public class RequestController {
//	private Logger logger = Logger.getLogger(RequestController.class);
//	@Autowired
//	OctopushManager octopushManager;
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/")
//	public String sayHello() {
//		return "Swagger Hello World";
//	}
//	
//	
////	@RequestMapping(value = "/sendSMS/{phoneNumber}/{param1}/{text}/{type}/{purpose}/{sender}")
////	public ResponseEntity<Object> sendSMS(@PathVariable(value = "phoneNumber", required = true) String phoneNumber,
////			@PathVariable(value = "param1", required = true) String param1,
////			@PathVariable(value = "text", required = true) String text,
////			@PathVariable(value = "type", required = true) String type,
////			@PathVariable(value = "purpose", required = true) String purpose,
////			@PathVariable(value = "sender", required = true) String sender) throws Exception {
////
////		logger.info("Function: " + "sendSMS" + " phoneNumber: " + phoneNumber + " param1: " + param1 + " text: " + text
////				+ " type: " + type + " purpose: " + purpose + " sender: " + sender);
////		
////		return new ResponseEntity<Object>(octopushManager.sendCompaignSMS(phoneNumber, param1, text, type, purpose, sender), HttpStatus.OK);
////	}
//
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/checkDigit4G")
//	public String checkDigit4G() throws Exception {
//
//		return "Swagger CheckDigit4G";
//	}
//
//}
