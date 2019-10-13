import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main
{
	private static String path = "txtfile.txt";
	
	public static void main(String argv[])
	{
		// reading
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			System.out.println("File " + path + " contains texts:");
			String line;
			while((line = reader.readLine()) != null)
			{
                System.out.println(line);
            } 
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println("File does not exist. Creating new empty file.");
			
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				writer.close();
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
		}
		
		// writing
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			
			System.out.println("Please enter new text ->");
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			scanner.close();
			
			System.out.println("Writing to file...");
			
			writer.write(line);
			writer.newLine();
			writer.close();
			
			System.out.println("Writing done.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}