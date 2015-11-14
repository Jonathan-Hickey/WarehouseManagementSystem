package interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

class ILogger extends Interceptor
{
	private BufferedWriter bw;

	@Override
	public void takeAction()
	{
		// appends to outOfBandServices.log.txt
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
			Calendar calendar = Calendar.getInstance();
			String time = sdf.format(calendar.getTime());
			
			bw = new BufferedWriter(new FileWriter("log.txt", true));
			bw.write(time + " event " + context.getEvent()
					+ " occurred during operation " + context.getMessage());
			bw.newLine();
			bw.flush();
//			bw.close();
		}
		catch (IOException ioe)
		{
			// ignore
		}
	}
}