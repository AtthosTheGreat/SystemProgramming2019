import java.util.Scanner;

public class MainClass
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		
		TiisuPrintingThread t = new TiisuPrintingThread();
		
		boolean running = false;
		String operation = "EnterLoop";
		while(!operation.equals("QUIT"))
		{
			System.out.println("Master, give your command:");
			operation = scanner.nextLine();
			
			switch(operation)
			{
				case "START":
					if (!running)
					{
						t.start();
						running = true;
					}
					else
						System.out.println("ERROR: Already running");
					break;
				case "STOP":
					if (running)
					{
						t.stop();
						running = false;
					}
					else
						System.out.println("ERROR: Already stopped");
					break;
			}
		}
		if (running)
			t.stop();
	}
}