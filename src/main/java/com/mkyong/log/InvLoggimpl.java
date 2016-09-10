package com.mkyong.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;

import com.mkyong.Constants.AppConstants;

public class InvLoggimpl  implements Invlogg{
	
	private static final   Logger logger=Logger.getRootLogger();
	
public InvLoggimpl(){
	initLogMgr();
}
public static final void initLogMgr(){
	
	if(AppConstants.getLogFilePath()!=null){
		PropertyConfigurator.configure(AppConstants.getLogFilePath().trim());
	}else if(AppConstants.getLogFilePath()==null){
		
		BasicConfigurator.configure();
	}
	
}	
	
	@Override
	public void error(Throwable Logdesc) {
	synchronized (logger) {
		logger.error("Following Error Caused By:"+Logdesc.getMessage(),Logdesc);
		logger.error(Logdesc.getStackTrace().toString(),Logdesc);
		
	}
	
	
	}

	@Override
	public void warn(Exception Logdesc) {
		
		if(logger.isEnabledFor(Priority.WARN)){
			logger.warn(Logdesc.toString(),Logdesc);
			logger.error(Logdesc.getStackTrace().toString(),Logdesc);
		}
		
	}

	@Override
	public void    debug(String Logdesc) {
		
		if(logger.isDebugEnabled()){
			
			logger.debug(Logdesc);
		}
		
	}

	@Override
	public void  info(String Logdesc){
		if(logger.isInfoEnabled()){
			
			logger.info(Logdesc);
		}
	}

	@Override
	public void trace(String Logdesc) {
		if(logger.isTraceEnabled()){
		logger.trace(Logdesc);	
		}
		
	}
	
	
}
