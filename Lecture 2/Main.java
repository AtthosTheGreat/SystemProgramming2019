import java.util.Scanner;
import java.util.ArrayList;

public class Main implements Tamagotchi.TamagotchiCallBack, RefreshTerminal.RefreshTerminalCallback
{
	private int noOfTamagotchies = 5;
	
	private ArrayList<Tamagotchi> tamagotchies = new ArrayList<Tamagotchi>(noOfTamagotchies);
	
	private ArrayList<Integer> foodsLeft = new ArrayList<Integer>(noOfTamagotchies);
	
	private int givenFood = 10;
	
	private int lastfeed = -1;
	
	public static void main(String args[])
	{	
		Scanner scanner = new Scanner(System.in);
		Main main = new Main();
		
		for(int i = 0; i < main.noOfTamagotchies; i++)
		{
			main.tamagotchies.add(new Tamagotchi(i, 20, 2000, main));
			main.tamagotchies.get(i).start();
			
			main.foodsLeft.add(new Integer(20));
		}
		
		RefreshTerminal refresh = new RefreshTerminal(main);
		refresh.start();
		
		boolean someAlive = true;
		while(someAlive)
		{
			String s = scanner.nextLine();
			try
			{
				int nr = Integer.parseInt(s);
				main.tamagotchies.get(nr).feed(main.givenFood);
				main.lastfeed = nr;
				
				main.updateTerminal();
				// System.out.println("Feed tamagotchi no: " + nr);
			}
			catch(Exception e)
			{
				System.out.println("You need to input an integer.");
			}
			
			someAlive = main.verifyAlive();
		}
		
		refresh.interrupt();
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		System.out.println("\n\n\nGAME OVER\n\n\n");
	}
	
	public boolean verifyAlive()
	{
		for(Tamagotchi x : tamagotchies)
			if (x.isAlive())
				return true;
		return false;
	}
	
	public void updateTerminal()
	{	
		System.out.print("\033[H\033[2J");
		System.out.flush();

		if (lastfeed != -1)
			System.out.println("You fed Tamagotchi " + lastfeed + "\n");
		else
			System.out.println("\n");

		for(int i = 0; i < noOfTamagotchies; i++)
		{
			if (foodsLeft.get(i) > 20)
			{
				System.out.println("Tamagotchi " + i + " Overfed. Food: " + foodsLeft.get(i));
			}
			else if (foodsLeft.get(i) < 0)
			{
				System.out.println("Tamagotchi " + i + " Starved. Food: " + foodsLeft.get(i));
			}
			else
			{
				System.out.println("Tamagotchi " + i + " OK. Food: " + foodsLeft.get(i));
			}
		}
	}
	
	@Override
	public void tamagotchiStatusChanged(int index, int foodLeft)
	{
		foodsLeft.set(index, new Integer(foodLeft));
	}
	
	public void refreshTerminal()
	{
		updateTerminal();
	}
}