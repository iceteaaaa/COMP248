// ---------------------------------------------------------------------------------------
// Assignment 4
// Written by: Shuo Zhang 40103576
// For COMP 248 Section FF – Fall 2018
//----------------------------------------------------------------------------------------
public class Garden 
{
	//attribute
	char[][] garden;
	
	//constructor
	Garden()
	{
		garden=new char[3][3];
		initializeGarden();
	}
	
	Garden(int size)
	{
		garden=new char[size][size];
		initializeGarden();
	}
	
	private void initializeGarden()
	{
		for(int i=0;i<garden.length;i++)
			for(int j=0;j<garden.length;j++)
				garden[i][j]='-';
	}
	
	//accessor
	public char getInLocation(int i,int j)
	{
		return garden[i][j];
	}
	
	//mutator
	public void plantFlower(int i,int j)
	{
		garden[i][j]='f';
	}
	
	public void removeFlower(int i,int j)
	{
		garden[i][j]='-';
	}
	
	public void plantTree(int i,int j)
	{
		garden[i][j]='t';
		garden[i+1][j]='t';
		garden[i][j+1]='t';
		garden[i+1][j+1]='t';
	}
	
	
	//flower_count
	public int countPossibleFlowers()
	{
		int count=0;
		for(int i=0;i<garden.length;i++)
			for(int j=0;j<garden.length;j++)
				if(garden[i][j]=='-')
					count++;
		return count;
	}
	
	//tree_count, it's determined by looking at the 4 spaces taken by the tree. If they are 
	//all empty, it can be planted.
	public int countPossibleTrees()
	{
		int count=0;
		for(int i=0;i<garden.length-1;i++)
			for(int j=0;j<garden.length-1;j++)
				if(garden[i][j]=='-'&&garden[i+1][j]=='-'&&garden[i][j+1]=='-'&&garden[i+1][j+1]=='-')
					count++;
		return count;
	}
	
	//garden_full
	public boolean gardenFull()
	{
		for(int i=0;i<garden.length;i++)
			for(int j=0;j<garden.length;j++)
				if(garden[i][j]=='-')
				{
					return false;
				}
		return true;
	}
	
	
	//toString
	public String toString()
	{
		String indention="  ";
		String long_indention="    ";
		String garden_in_String_form=long_indention+" |"+indention;
		//first row
		for(int i=0;i<garden.length;i++)
		{
			garden_in_String_form+=i+long_indention;
		}
		//2nd to last rows
		for(int i=0;i<garden.length;i++)
		{
			garden_in_String_form+="\n\n"+long_indention+i+"|"+indention;
			for(int j=0;j<garden.length;j++)
			{
				garden_in_String_form+=garden[i][j]+long_indention;
			}
		}
		return garden_in_String_form;		
	}
	
	//additional methods
	public int length()
	{
		return garden.length;
	}
	
	public boolean gardenEmpty()
	{
		for(int i=0;i<garden.length;i++)
			for(int j=0;j<garden.length;j++)
				if(garden[i][j]!='-')
				{
					return false;
				}
		return true;
	}
}
