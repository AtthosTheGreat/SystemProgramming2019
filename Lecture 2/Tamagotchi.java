public class Tamagotchi extends Thread
{
	public interface TamagotchiCallBack
	{
		public void tamagotchiStatusChanged(int index, int food);
	}
	
	boolean alive;
	int index;
	int foodLeft;
	int feedingTimeMs;
	TamagotchiCallBack callbackInterface;
	
	public Tamagotchi(int index, int initialFood, int feedingTimeMs, TamagotchiCallBack callbackInterface)
	{
		this.index = index;
		foodLeft = initialFood;
		this.feedingTimeMs = feedingTimeMs;
		alive = true;
		
		this.callbackInterface = callbackInterface;
	}
	
	public void feed(int foodGiven)
	{
		foodLeft += foodGiven;
	}
	
	public int getFood()
	{
		return foodLeft;
	}
	
	public void run()
	{
		try
		{
			while(alive)
			{
				if (foodLeft > 20)
				{
					alive = false;
				}
				else
				{
					foodLeft--;
					if (foodLeft < 0)
						alive = false;
				}
				
				callbackInterface.tamagotchiStatusChanged(index, foodLeft);
				sleep(feedingTimeMs);
			}
		}
		catch(Exception e)
		{
			
		}
	}
}