package com.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crm.service.ICustomerLossService;

@Component
public class FindLossCustomerJob {
	@Autowired
	ICustomerLossService customerLossService;
	
	@Scheduled(cron="0 0 2 * * ?")
	public void customerAutoLoss() {
		customerLossService.customerAutoLoss();
	}

}
