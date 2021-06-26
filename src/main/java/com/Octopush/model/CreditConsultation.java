package com.Octopush.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditConsultation {
	private List<String>country_codes;
	private boolean with_details;
	private String product_name; 
}
