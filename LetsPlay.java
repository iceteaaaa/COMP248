// ---------------------------------------------------------------------------------------
// Assignment 4
// Written by: Shuo Zhang 40103576
// For COMP 248 Section FF – Fall 2018
//----------------------------------------------------------------------------------------
import java.util.Scanner;
public class LetsPlay 
{
	//class attributes
	static int garden_size;
	static int num_of_players;
	static int num_of_round=1;
	static Player[] player_array;
	static Scanner kb=new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("--------------------------------------------Welcome to the Garden!--------------------------------------------");
		System.out.print
				("\nTo win this game you need some luck with the dice and a bit of strategy.\r\n" + 
				"Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).\r\n" + 
				"You roll the and based on the outcome you get to plant a pre-set number of trees and flowers.\r\n"+
				"\nRolls and their outcome:\r\n" + 
				"------------------------\r\n" + 
				"3: plan a tree (2x2) and a flower (1x1)\r\n" + 
				"6: plant 2 flowers (2 times 1x1)\r\n" + 
				"12: plant 2 trees (2 times 2x2)\r\n" + 
				"5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)\r\n" + 
				"Any other EVEN rolls: plant a tree (2x2)\r\n" + 
				"Any other ODD rolls: plant a flower (1x1)\r\n" + 
				"\nMinimum number of players: 2.\r\n" + 
				"Minimum garden size: 3x3.\r\n" + 
				"You can only plant in empty locations. To plant a tree you give the top left coordinates of the 2x2 space\r\n" + 
				"and I will check to make sure all 4 locations are free.\r\n" + 
				"Okay .. Let's start the game! May the best gardener win!!!\r\n" + 
				"\nThe default garden size is a 3x3 square. You can use this default board size or change the size.\r\n"+
				"Enter 0 to use the default garden size or -1 to enter your own size. Enter here: ");
		
		//asking user input
		garden_size=checking_garden_size();
		System.out.print("How many gardeners will there be (minimum 2 required to play, max allowed 10)? Enter here: ");
		num_of_players=checking_num_of_player();
		
		
		//creating player list 
		player_array=new Player[num_of_players];
		for(int i=0;i<num_of_players;i++)
		{
			System.out.print("--> Name of player "+(i+1)+" (no spaces allowed): ");
			player_array[i]=new Player(kb.next(),garden_size);
		}
		
		//determining who goes first
		System.out.println("\nLet's see who goes first...");
		Dice[] dice_array=new Dice[num_of_players];
		for(int i=0;i<num_of_players;i++)
		{
			dice_array[i]=new Dice();
			System.out.println(player_array[i].getName()+" rolled a "+dice_array[i].rollDice()+". "+dice_array[i]);
		}
		int dice_winner_index=Dice.determineWinners(dice_array, player_array);
		System.out.println("He/She will go first!\n");
			
		//game starts
		System.out.println("Time to play!!!\n~~~~~~~~~~~~~~~\n");
		//outermost loop making the game repeat until a winner is decided, then another static method called game_over() will make the game exit 
		for(int player_index=dice_winner_index;;player_index=loop_player_index(player_index),num_of_round++)
		{
			System.out.println(player_array[player_index].getName()+" rolled a "+Dice.rollDice_static()+"(Die 1: "+Dice.value_dice_1_static+"  Die2: "+Dice.value_dice_2_static+")"+".");
			game_rules();
			player_array[player_index].showGarden();
			System.out.println();
			//deciding on what to plant
			switch(Dice.value_dice_1_static+Dice.value_dice_2_static)
			{
				//flower
				case 1: case 7: case 9: case 11:
					
						System.out.println("and have "+player_array[player_index].howManyFlowersPossible()+" places to do this.");
						checking_plant_flower(player_index);
						System.out.println();
						break;
				//tree
				case 2: case 4: case 8:
						
						System.out.println("and have "+player_array[player_index].howManyTreesPossible()+" places to do this.");
						if(player_array[player_index].howManyTreesPossible()==0)
						{
							System.out.println("**Unfortunately you don't have any more space for a tree - you miss a turn!\n");
							break;
						}
						checking_plant_tree(player_index);
						System.out.println();
						break;
						
				//rabbit
				case 5: case 10:
						
						int row_rabbit;
						int column_rabbit;
						if(player_array[player_index].isGardenEmpty())
						{
							System.out.println("But you have no plant! The rabbit jumps back to the rabbit hole!\n");
							break;
						}
						//make sure rabbit can only eat plants and not dirt
						do 
						{
							row_rabbit=(int)(Math.random()*garden_size);
							column_rabbit=(int)(Math.random()*garden_size);
						}
						while(player_array[player_index].whatIsPlanted(row_rabbit, column_rabbit)=='-');
						player_array[player_index].eatHere(row_rabbit, column_rabbit);
						System.out.println("The rabbit ate your favorite plant at location ("+row_rabbit+","+column_rabbit+")\n");
						player_array[player_index].showGarden();
						System.out.println();
						break;
						
				//tree and flower
				case 3: 
						
						System.out.println("Let's start with the tree!");
						//if tree cannot be plant 
						if(player_array[player_index].howManyTreesPossible()==0)
						{
							System.out.println("**Unfortunately you don't have any more space for a tree! But you can plant a flower!");
							System.out.println("And you have "+player_array[player_index].howManyFlowersPossible()+" places to do this.");
							checking_plant_flower(player_index);
							System.out.println();
							break;
						}
						//if tree can be plant
						else
						{
							System.out.println("You have "+player_array[player_index].howManyTreesPossible()+" places for your tree!");
							checking_plant_tree(player_index);
							System.out.println("Now let's plant your flower!");
							System.out.println("You have "+player_array[player_index].howManyFlowersPossible()+" places for your flower!\n");
							player_array[player_index].showGarden();
							System.out.println();
							checking_plant_flower(player_index);
							System.out.println();
						}
						break;
				
				
				//2 flowers
				case 6: 
					
						System.out.println("You have "+player_array[player_index].howManyFlowersPossible()+" places for your first flower!");
						checking_plant_flower(player_index);
						System.out.println();
						player_array[player_index].showGarden();
						System.out.println("\nNow you have "+player_array[player_index].howManyFlowersPossible()+" places for your second flower!");
						checking_plant_flower(player_index);
						System.out.println();
						break;
				
				
				//2 trees
				case 12: 
					
						 if(player_array[player_index].howManyTreesPossible()==0)
						 {
							 System.out.println("**Unfortunately you don't have any more space for a tree - you miss a turn!\n");
							 break;
						 }
						 System.out.println("You have "+player_array[player_index].howManyTreesPossible()+" places for your first tree!");
						 checking_plant_tree(player_index);
						 System.out.println();
						 player_array[player_index].showGarden();
						 if(player_array[player_index].howManyTreesPossible()==0)
						 {
							 System.out.println("\n**Unfortunately you don't have any more space for a second tree - your turn ends!\n");
							 break;
						 }
						 System.out.println("\nNow you have "+player_array[player_index].howManyTreesPossible()+" places for your second tree!");
						 checking_plant_tree(player_index);
						 System.out.println();
						 break;
			}
		}
	}
	
	//force user to input valid number of players
	private static int checking_num_of_player() 
	{
		Scanner kb=new Scanner(System.in);
		int num_of_players=kb.nextInt();
		for(;num_of_players<2||num_of_players>10;)
		{
			System.out.print("Sorry but "+num_of_players+" is not a valid number of players. Please reenter: ");
			num_of_players=kb.nextInt();
		}
		System.out.println("Great! "+num_of_players+" players will be playing!\r\n");
		return num_of_players;
	}

	//force user to input valid garden_size
	private static int checking_garden_size() 
	{
		Scanner kb=new Scanner(System.in);
		int garden_size;
		int option=kb.nextInt();
		for(;option!=0&&option!=-1;)
		{
			System.out.print("Please enter a valid option: either 0 or -1. Reenter here: ");
			option=kb.nextInt();
		}
		if(option==0)
			return garden_size=3;
		else
		{
			System.out.print("Please enter your garden size: ");
			garden_size=kb.nextInt();
			for(;garden_size<3;)
			{
				System.out.print("Sorry but "+garden_size+" is not a legal choice. Reenter your choice: ");
				garden_size=kb.nextInt();
			}
			return garden_size;	
		}
	}
	
	//force player_index to loop inside of array instead of going out of bound 
	private static int loop_player_index(int player_index)
	{
		//when it's at the last memory space of the array, it goes back to the first memory space
		if(player_index==num_of_players-1)
		{
			player_index=0;
		}
		//otherwise it's going to the next memory space 
		else
		{
			player_index++;
		}
		return player_index;
	}
	
	//printing game rules according to the dice result 
	private static void game_rules()
	{
		switch(Dice.value_dice_1_static+Dice.value_dice_2_static)
		{
			case 1: System.out.println("You must plant a flower!\n");
					break;
			case 2: System.out.println("You must plant a tree!\n");
					break;
			case 3: System.out.println("You must plant a tree and a flower!\n");
					break;
			case 4: System.out.println("You must plant a tree!\n");
					break;
			case 5: System.out.println("Look! The rabbit is hungry and is gonna eat in your garden!\n");
					break;
			case 6: System.out.println("You must plant 2 flowers!\n");
					break;
			case 7: System.out.println("You must plant a flower!\n");
					break;
			case 8: System.out.println("You must plant a tree!\n");
					break;
			case 9: System.out.println("You must plant a flower!\n");
					break;
			case 10: System.out.println("Look! The rabbit is hungry and is gonna eat in your garden!\n");
			 		 break;
			case 11: System.out.println("You must plant a flower!\n");
					 break;
			case 12: System.out.println("You must plant 2 trees!\n");
					 break;
		}
	}
	
	//force user to plant flower with valid inputs 
	private static void checking_plant_flower(int player_index)
	{
		System.out.print("Enter coordinates, row first, column second: ");
		int row=kb.nextInt();
		int column=kb.nextInt();
		//making sure flower is inside grid
		for(;;)
		{
			//if out of grid
			if(row>=garden_size||column>=garden_size||row<0||column<0)
			{
				System.out.println("Sorry you are out of grid!");
				System.out.print("Reenter coordinates, row first, column second: ");
				row=kb.nextInt();
				column=kb.nextInt();
			}
			//if inside grid
			else
			{
				//if flower will be planted where a tree exists
				if(player_array[player_index].whatIsPlanted(row, column)=='t')
				{
					System.out.println("Sorry you can't plant here, there's a tree!");
					System.out.print("Reenter coordinates, row first, column second: ");
					row=kb.nextInt();
					column=kb.nextInt();
				}
				//if flower will be planted where a flower exists
				else if(player_array[player_index].whatIsPlanted(row, column)=='f')
				{
					System.out.println("Sorry you can't plant here, there's a flower!");
					System.out.print("Reenter coordinates, row first, column second: ");
					row=kb.nextInt();
					column=kb.nextInt();
				}
				//if flower can be planted
				else
					break;
			}
		}
		player_array[player_index].plantFlowerInGarden(row,column);
		game_over(player_index);
	}
	
	//force user to plant tree with valid inputs 
	private static void checking_plant_tree(int player_index)
	{
		System.out.print("Enter coordinates, row first, column second: ");
		int row=kb.nextInt();
		int column=kb.nextInt();
		//making sure tree is inside grid 
		for(;;)
		{
			//if outside grid
			if(row+1>=garden_size||column+1>=garden_size||row<0||column<0)
			{
				System.out.println("Sorry your tree will be out of grid!");
				System.out.print("Reenter coordinates, row first, column second: ");
				row=kb.nextInt();
				column=kb.nextInt();
			}
			//if inside grid 
			else
			{
				//if tree will be planted where another plant exists
				if(player_array[player_index].whatIsPlanted(row, column)=='t'||
				   player_array[player_index].whatIsPlanted(row+1, column)=='t'||
				   player_array[player_index].whatIsPlanted(row, column+1)=='t'||
				   player_array[player_index].whatIsPlanted(row+1, column+1)=='t'||
				   player_array[player_index].whatIsPlanted(row, column)=='f'||
				   player_array[player_index].whatIsPlanted(row+1, column)=='f'||
				   player_array[player_index].whatIsPlanted(row, column+1)=='f'||
				   player_array[player_index].whatIsPlanted(row+1, column+1)=='f')
				{
					System.out.println("Sorry you can't plant here, there's another plant on the ground!");
					System.out.print("Reenter coordinates, row first, column second: ");
					row=kb.nextInt();
					column=kb.nextInt();
				}
				//if tree can be planted
				else
					break;
			}
		}
		player_array[player_index].plantTreeInGarden(row,column);
		game_over(player_index);
	}
	
	//checking if game is over 
	private static void game_over(int winner_index)
	{
		if(player_array[winner_index].isGardenFull())
		{
			System.out.println("\n\n\nFINAL RESULTS\n-------------");
			System.out.println("Here are the gardens after "+num_of_round+" rounds.");
			//print the garden of all players, starting with the winner
			for(int i=0;i<num_of_players;i++,winner_index=loop_player_index(winner_index))
			{
				System.out.println("\n\n"+player_array[winner_index].getName()+"'s garden\n");
				player_array[winner_index].showGarden();
			}
			System.out.println("\n\nAnd the winner is ........ "+player_array[winner_index].getName());
			System.out.println("What a beautiful garden you have!!!\n\nYou are a great friend of the plants!");
			System.exit(0);
		}
	}
}
