package interceptor;

import coreclasses.InterceptorDispatcher;

public class InterceptorApplication {
	
	private InterceptorDispatcher dispatch;

	public void runApp()
	{
		dispatch = new InterceptorDispatcher();

		ILogger log = new ILogger();
		log.setEvent("1");
		log.setMessage("application");
		dispatch.register(log);

	}

}
