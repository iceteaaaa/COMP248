import java.util.Scanner;
public class Garden_Game_test
{
	public static void main(String[] args)
	{
		//testing Dice 
		Scanner kb=new Scanner(System.in);
//		Dice dice=new Dice();
//		System.out.print(dice.getValue_dice_1());
//		System.out.print(dice.getValue_dice_2());
//		System.out.print(dice.rollDice());
//		System.out.print(dice.getValue_dice_1());
//		System.out.print(dice.getValue_dice_2());
//		System.out.println(dice);
//		Dice[] dice_array=new Dice[24];
//		for(int i=0;i<24;i++)
//			{dice_array[i]=new Dice();
//			dice_array[i].rollDice();}
//		Player[] player_array=new Player[24];
//		for(int i=0;i<24;i++)
//			player_array[i]=new Player(kb.next(),5);
//				
//		System.out.println(Dice.determineWinners(dice_array, player_array));
		
		//testing Garden
//		Garden garden=new Garden(5);
//		garden.plantTree(3, 4);
//		garden.plantTree(2, 3);
//		for(int i=0;i<garden.length();i++)
//			for(int j=0;j<garden.length();j++)
//				garden.plantFlower(i, j);
//		System.out.print(garden);;
//		System.out.print(garden.countPossibleFlowers());
//		System.out.print(garden.countPossibleTrees());
//		System.out.print(garden.gardenFull());
		
		//testing Player
		Player shuo=new Player("Shuo",5);
		shuo.showGarden();
		
		//testing rabbit
		System.out.println("Look! The rabbit is hungry and is gonna eat in your garden!\n");
		System.out.println((int)(Math.random()*5)+""+(int)(Math.random()*LetsPlay.garden_size));
		System.out.println(5+5);
	
	}
}
