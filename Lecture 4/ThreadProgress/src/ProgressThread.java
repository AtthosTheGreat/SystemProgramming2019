
public class ProgressThread extends Thread
{
	public interface ProgressThreadCallBack
	{
		void progressPercentage(String name, int progress);
		void progressFinished(String name);
	}
	
	private ProgressThreadCallBack callback;
	
	private String name;
	
	public ProgressThread(String name, ProgressThreadCallBack callback)
	{
		this.name = name;
		this.callback = callback;
	}
	
	public void run()
	{
		try
		{
			callback.progressPercentage(name, 0);
			sleep(1000);

			callback.progressPercentage(name, 10);
			sleep(1000);

			callback.progressPercentage(name, 20);
			sleep(1000);

			callback.progressPercentage(name, 30);
			sleep(1000);

			callback.progressPercentage(name, 40);
			sleep(1000);

			callback.progressPercentage(name, 50);
			sleep(1000);

			callback.progressPercentage(name, 60);
			sleep(1000);

			callback.progressPercentage(name, 70);
			sleep(1000);

			callback.progressPercentage(name, 80);
			sleep(1000);

			callback.progressPercentage(name, 90);
			sleep(1000);
			
			callback.progressFinished(name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
