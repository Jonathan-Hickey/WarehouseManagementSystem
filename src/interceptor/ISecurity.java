package interceptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ISecurity extends Interceptor
{
	@Override
	public void takeAction()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("log.txt"));
			int count = 0;

			for (String line; (line = br.readLine()) != null;)
				if (line.contains("event 1"))
					count++;

			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt",
					true));

			if (count >= 3)
			{
				bw.write("\noperation " + context.getMessage()
				        + "event 2 shut down upon ");
				bw.newLine();
				context.consumeService(Integer.parseInt(context.getMessage()));
			}

			bw.flush();
			bw.close();
		}
		catch (IOException ioe)
		{
			// ignore
		}
	}
}