// ---------------------------------------------------------------------------------------
// Assignment 4
// Written by: Shuo Zhang 40103576
// For COMP 248 Section FF – Fall 2018
//----------------------------------------------------------------------------------------
public class Dice 
{
	//attributes
	//to accommodate RollDice() method below
	private int value_dice_1;
	private int value_dice_2;
	
	//to accommodate RollDice_static() method below 
	public static int value_dice_1_static;
	public static int value_dice_2_static;

	//constructor
	Dice()
	{
		value_dice_1=0;
		value_dice_2=0;
	}
	
	//accessor
	public int getValue_dice_1()
	{
		return value_dice_1;
	}
	
	public int getValue_dice_2()
	{
		return value_dice_2;
	}
	
	public int getValue_dices_sum()
	{
		return value_dice_1+value_dice_2;
	}
	
		
	//rollDice on specific dices
	public int rollDice()
	{
		value_dice_1=(int)(Math.random()*6+1);
		value_dice_2=(int)(Math.random()*6+1);
		int sum=value_dice_1+value_dice_2;
		return sum;
	}
	
	//rollDice as a static method 
	public static int rollDice_static()
	{
		value_dice_1_static=(int)(Math.random()*6+1);
		value_dice_2_static=(int)(Math.random()*6+1);
		int sum=value_dice_1_static+value_dice_2_static;
		return sum;
	}
	
	//toString
	public String toString()
	{
		return "Die 1: "+value_dice_1+"  Die 2: "+value_dice_2;
	}
	
	//other methods
	private void clear()
	{
		value_dice_1=0;
		value_dice_2=0;
	}
	//this method is to decide winners among different people rolling dices. If a group of people ties, they
	//reroll amongst themselves, repeat until a sole winner is found. It takes 2 arrays, one for the list of players
	//the other for the corresponding rolls by each player. It's the reason why there are private attributes 
	//in this class. 
	public static int determineWinners(Dice[] dicestocompare, Player[] playerslist)
	{
		boolean[] isWinner=new boolean[dicestocompare.length];
		//make sure there's only one winner at the end of the dice roll 
		for(int winner_count = 0;winner_count!=1;)
		{
			//resetting for 2nd iteration and onward
			winner_count=0;
			int max_roll=dicestocompare[0].getValue_dices_sum();
			
			//determining highest roll number obtained by the players
			for(int i=0;i<dicestocompare.length;i++)
			{
				if(i!=0&&max_roll<dicestocompare[i].getValue_dices_sum())
				{
					max_roll=dicestocompare[i].getValue_dices_sum();
				}
			}

			//determine winners, then reroll between winners. Winners will have the highest roll number.
			//also set losers score to 0, so they don't participate in the next round. 
			for(int i=0;i<dicestocompare.length;i++)
			{
				if(dicestocompare[i].getValue_dices_sum()==max_roll)
				{
					isWinner[i]=true;
					dicestocompare[i].rollDice();
					winner_count++;
				}
				else
				{
					isWinner[i]=false;
					dicestocompare[i].clear();
				}
			}
			
			//declaring joint winners (if more than one person wins). 
			//It's separated from the above step because when winner_count = 1. You don't have joint winners.
			if(winner_count!=1)
				for(int i=0;i<dicestocompare.length;i++)
				{
					if(isWinner[i]==true)
					{
						System.out.println(playerslist[i].getName()+" is a joint winner in the dice roll! He/She rerolled a "+dicestocompare[i].getValue_dices_sum()+".");
					}
				}
		}
		
		//return player_index of the sole winner.
		for(int i=0;i<dicestocompare.length;i++)
			if(isWinner[i]==true)
			{
				System.out.print("And the sole winner is: "+playerslist[i].getName()+". ");
				return i;
			}
		
		//to make compiler happy
		return 0;
	}
}
