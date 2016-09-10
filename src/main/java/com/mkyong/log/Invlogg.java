package com.mkyong.log;

public interface Invlogg {

	public void  error(Throwable Logdesc);
	public  void warn(Exception Logdesc);
	public void debug(String Logdesc);
	public void info(String Logdesc);
	public void trace(String Logdesc);
	
}
