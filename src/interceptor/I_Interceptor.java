package interceptor;

public interface I_Interceptor
{
	String getMessage();

	void setMessage(String message);

	String getEvent();

	void setEvent(String event);

	void takeAction();
}
