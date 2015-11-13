package coreclasses;

import java.util.ArrayList;
import java.util.List;

import interceptor.Interceptor;

public class InterceptorDispatcher implements I_Interceptor
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
		
		System.out.println("interceptor size: " + registeredInterceptors.size());

		for (Interceptor element : registeredInterceptors)
			if (element.getEvent().equals(this.context.getEvent()))
			{

				System.out.println("interceptor2");
				result = element.getClass().getName();
				element.setMessage(this.context.getMessage());

				System.out.println(context.getMessage() + "\t" + result);

				element.takeAction();
				break;
			}
	}

	@Override
	public String getMessage()
	{
		return context.getMessage();
	}

	@Override
	public void setMessage(String message)
	{
		context.setMessage(message);
	}

	@Override
	public String getEvent()
	{
		return context.getEvent();
	}

	@Override
	public void setEvent(String event)
	{
		context.setEvent(event);
	}

	@Override
	public void takeAction()
	{
		// Interceptor override
	}
}
