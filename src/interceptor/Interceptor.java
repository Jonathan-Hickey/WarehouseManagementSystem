package interceptor;

import server.InterceptorContext;

public abstract class Interceptor implements I_Interceptor
{
	public InterceptorContext context = new InterceptorContext();

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
	public abstract void takeAction();

}