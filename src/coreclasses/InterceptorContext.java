package coreclasses;

public class InterceptorContext
{
	private String event, message;

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getEvent()
	{
		return event;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public void consumeService(int operation)
	{
		System.out.println("\n\tContext.consumeService()");
	//	CoreSystem.terminate(operation);
	}
}
