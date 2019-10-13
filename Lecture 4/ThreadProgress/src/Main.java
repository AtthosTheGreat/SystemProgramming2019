import java.time.LocalDateTime;
import java.util.Scanner;

public class Main implements ProgressThread.ProgressThreadCallBack
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		
		while(!line.equals("quit"))
		{
			new ProgressThread(line, new Main()).start();
			line = scanner.nextLine();
		}
		scanner.close();
	}

	@Override
	public void progressPercentage(String name, int progress)
	{
		System.out.println(LocalDateTime.now() + ". Thread: " + name + ". Progress: " + progress);
	}

	@Override
	public void progressFinished(String name)
	{
		System.out.println(LocalDateTime.now() + ". Thread: " + name + ". FINISHED");
	}
}
