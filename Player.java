// ---------------------------------------------------------------------------------------
// Assignment 4
// Written by: Shuo Zhang 40103576
// For COMP 248 Section FF – Fall 2018
//----------------------------------------------------------------------------------------
public class Player 
{
	//attributes
	private String name;
	private Garden name_garden;
	
	//constructor
	Player(String name,int garden_size)
	{
		this.name=name;
		name_garden=new Garden(garden_size);
	}
	
	//accessor
	public String getName()
	{
		return name;
	}
	
	//garden methods
	public int howManyFlowersPossible()
	{
		return name_garden.countPossibleFlowers();
	}
	
	public int howManyTreesPossible()
	{
		return name_garden.countPossibleTrees();
	}
	
	public char whatIsPlanted(int i,int j)
	{
		return name_garden.getInLocation(i,j);
	}
	
	public void plantTreeInGarden(int i,int j)
	{
		name_garden.plantTree(i,j);
	}
	
	public void plantFlowerInGarden(int i,int j)
	{
		name_garden.plantFlower(i, j);
	}
	
	public void eatHere(int i,int j)
	
	{
		name_garden.removeFlower(i,j);
	}
	
	public boolean isGardenFull()
	{
		return name_garden.gardenFull();
	}
	
	public void showGarden()
	{
		System.out.println(name_garden);
	}
	
	//additional method
	public boolean isGardenEmpty()
	{
		return name_garden.gardenEmpty();
	}
	
}

