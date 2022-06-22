package com.studentservice.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	
	public String getCurrentTimestamp() {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    String strDate= formatter.format(date);  
	    return strDate;  
	}
}
