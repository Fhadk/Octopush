package com.Octopush.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultParameter {
	private List<SmsCampaignAlert> sms_campaign_alert_parameters;
	private List<CampaignParameter> campaign_parameters;
	private List<Callback> callbacks;
}
