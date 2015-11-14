package interceptor;

import coreclasses.InterceptorDispatcher;

public class FactoryDispatcher {

	public static InterceptorDispatcher makeDispatcher(){
		
		InterceptorDispatcher dispatcher = new InterceptorDispatcher();
		
		ILogger log = new ILogger();
		log.setEvent("logging");
		log.setMessage("application");
		dispatcher.register(log);
		
		return dispatcher;
	}
}
