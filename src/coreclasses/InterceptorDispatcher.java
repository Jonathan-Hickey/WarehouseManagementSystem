package coreclasses;

import java.util.ArrayList;
import java.util.List;

import interceptor.Interceptor;
import servercommunication.InterceptorContext;

public class InterceptorDispatcher
{
	private static List<Interceptor> registeredInterceptors = new ArrayList<Interceptor>();
	private InterceptorContext context;

	public void register(Interceptor interCept)
	{
		String event = interCept.getEvent();
		boolean exists = false;
		for (Interceptor element : registeredInterceptors)
			if (element.getEvent().equals(event))
			{
				exists = true;
				break;
			}
		if (!exists)
		{
			registeredInterceptors.add(interCept);
			System.out.println("registering " + interCept.getClass());
		}
	}

	public void notify(InterceptorContext context)
	{
		this.context = context;
		String result;

		for (Interceptor element : registeredInterceptors)
			if (element.getEvent().equals(this.context.getEvent()))
			{

				result = element.getClass().getName();
				element.setMessage(this.context.getMessage());
				element.takeAction();
				break;
			}
	}


}
