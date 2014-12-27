package com.icoundoul.icosime.dao.exception;

public class DAOException extends Exception{

	private String errCode;
	private String messageArgs;
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getMessageArgs() {
		return messageArgs;
	}
	public void setMessageArgs(String messageArgs) {
		this.messageArgs = messageArgs;
	}
	
}
