package com.Octopush.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Callback {
	private String url_inbound;
	private String url_sms_delivery;
	private String url_vocal_sms_delivery;
	private String url_blacklisted;
	private String email;
}
