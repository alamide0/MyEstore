package com.zsy.exception;

public class MsgException extends Exception {
	public MsgException(){
		
	}
	
	public MsgException (String msg){
		super(msg);
	}
}
