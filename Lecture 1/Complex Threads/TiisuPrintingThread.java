import java.time.*;
import java.time.temporal.ChronoUnit;

public class TiisuPrintingThread extends Thread
{
	public void run()
	{
		try
		{
			while(true)
			{
				System.out.println(LocalDateTime.now() + ": Tiisu, We want more!!!");
				sleep(5000);
			}
		}
		catch(Exception e)
		{
			System.out.println("An error occured inside TiisuPrintingThread");
		}
	}
}