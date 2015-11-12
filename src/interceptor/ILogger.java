package interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class ILogger extends Interceptor
{
	private BufferedWriter bw;

	@Override
	public void takeAction()
	{
		// appends to outOfBandServices.log.txt
		try
		{
			bw = new BufferedWriter(new FileWriter("log.txt", true));
			bw.write("event " + context.getEvent()
					+ " occurred during operation " + context.getMessage());
			bw.newLine();
			bw.flush();
		}
		catch (IOException ioe)
		{
			// ignore
		}
	}
}