package com.intheeast.usingtheautoproxyfacility.bean;

import org.springframework.transaction.annotation.Transactional;

import com.intheeast.usingtheautoproxyfacility.service.BusinessService;

public class BusinessObject2 implements BusinessService{
	
	@Transactional
	public void process() {
        System.out.println("Processing in BusinessObject2");
    }
	
}
