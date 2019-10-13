public class RefreshTerminal extends Thread
{
	public interface RefreshTerminalCallback
	{
		public void refreshTerminal();
	}
	
	private RefreshTerminalCallback callback;
	
	public RefreshTerminal(RefreshTerminalCallback callback)
	{
		this.callback = callback;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				callback.refreshTerminal();
				sleep(1000);
			}
		}
		catch(Exception e)
		{
			
		}
	}
}