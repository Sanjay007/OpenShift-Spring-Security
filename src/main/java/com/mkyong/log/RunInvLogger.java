package com.mkyong.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;



public class RunInvLogger {

	private static final InvLoggimpl InvLoggimpl = null;
	
	public static final void initLogMgr(){
	
		if(com.mkyong.Constants.AppConstants.getLogFilePath()!=null){
			PropertyConfigurator.configure(com.mkyong.Constants.AppConstants.getLogFilePath().trim());
		}else if(com.mkyong.Constants.AppConstants.getLogFilePath()==null){
			
			BasicConfigurator.configure();
		}
		
	}
	
	public static InvLoggimpl  getbaseLogger(){
		
	if(	InvLoggimpl==null){
		
		return new InvLoggimpl();
	}
	return InvLoggimpl;
	}
	
}
