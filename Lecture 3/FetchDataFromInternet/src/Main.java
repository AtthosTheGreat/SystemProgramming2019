import java.util.ArrayList;
import java.util.Scanner;

public class Main implements NetworkThread.NetworkInterfaceCallback
{
	public static void main(String args[])
	{
		System.out.print("Give URL:> ");
		Scanner scanner = new Scanner(System.in);
		String url = scanner.nextLine();
		
		// "http://oamk.fi/~vetapani/"
		NetworkThread nt = new NetworkThread(new Main(), url);
		nt.start();
	}

	@Override
	public void sendResults(ArrayList<String> result)
	{
		for(String x : result)
		{
			System.out.println(x);
		}
	}
}
