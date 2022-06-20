package com.qa.opencart.customexceptions;

public class FrameworkException extends RuntimeException{
	
	public FrameworkException (String messg) {
		super (messg);	
		printStackTrace();
	}
	

}
