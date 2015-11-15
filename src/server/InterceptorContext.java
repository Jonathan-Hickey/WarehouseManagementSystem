package server;

public class InterceptorContext
{
	private String event;
	private String message;

	public InterceptorContext(){
		event = "";
		message = "";
	}
	
	public InterceptorContext(String event, String message){
		this.event = event;
		this.message = message;
	}
	
	public void setEvent(String event)
	{
		System.out.println("Event set : " + event);
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
