import java.util.*;
import java.util.Map.Entry;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class GraphTester
{
	public static Scanner userScanner = new Scanner(System.in);
	public static Scanner userChoice = new Scanner(System.in);
	public static Kruskal<String> cities = new Kruskal<String>();
	public static PrintStream ps_console = System.out;

	public static void main(String[] args)
	{
		mainMenu();
	}
	
	
	public static void mainMenu()
	{
		int choice;
		boolean exit = false;
		
		while(!exit)
		{
			System.out.println("{ Hamiltonian Circuit }");
			System.out.println("{ Main Menu }" + '\n');
			System.out.println("|---------------------|" + '\n');
			System.out.println("Please select an option below:" + '\n');
			System.out.println("	1. [Load Graph File]");
			System.out.println("	2. [Display Graph]");
			System.out.println("	3. [Solve Problem]");
			System.out.println("	4. [Add City]");
			System.out.println("	5. [Remove City]");
			System.out.println("	6. [Undo Previous Removal]" + '\n');
			System.out.print("\nPlease enter choice now: ");
			choice = userScanner.nextInt();
			userScanner.nextLine();
			
			if(choice > 0 && choice < 7)
			{
				switch(choice)
				{
				case 1:
					loadGraph();
					break;
					
				case 2:
					displayGraph();
					break;
					
				case 3:
					solveProblem();
					break;
					
				case 4:
					addCity();
					break;
					
				case 5:
					removeCity();
					break;
					
				case 6:
					undoRemove();
					break;
				}
			}
			else
			{
				System.out.println("\nInvalid option entered, please try again.\n");
				continue;
			}
		}
	}

	public static void clearScreen()
	{  
		for(int i = 0; i < 100; i++)
		{
		    System.out.println("\b");
		}
	}
	
	public static void loadGraph()
	{
		String mainCity, tarCity;
		int conn, dist;
		
		Scanner check = openInputFile();
		
		if(check != null)									// Check if file not found.
		{
			while(check.hasNext())
			{
				conn = check.nextInt();
				check.nextLine();
				mainCity = check.nextLine();
				for(int i = 0; i < conn; i++)
				{
					tarCity = check.nextLine();
					dist = check.nextInt();
					check.nextLine();
					cities.addEdge(mainCity, tarCity, dist);
				}
			}
			check.close();
			System.out.println("\nSuccessfully added all cities from file.\n");
		}
		else												// If check is null, display error message and exit program.
		{
			System.out.println("File not found.\n");
			return;
		}
	}
	
	public static void displayGraph()
	{
		int choice;
		String term;
		boolean exit = false;
		Visitor<String> salesperson = new Salesperson();
		
		while(!exit)
		{
			System.out.println("{ Hamiltonian Circuit }");
			System.out.println("{ Display Graph }" + '\n');
			System.out.println("|---------------------|" + '\n');
			System.out.println("Please select an option below:" + '\n');
			System.out.println("	1. [Breadth First Traversal]");
			System.out.println("	2. [Depth First Traversal]");
			System.out.println("	3. [Adjacency List]");
			System.out.println("	4. [Output to File]");
			System.out.println("	5. [Return to Menu]" + '\n');
			System.out.print("\nPlease enter choice now: ");
			choice = userScanner.nextInt();
			userScanner.nextLine();
			
			if(choice > 0 && choice < 6)
			{
				switch(choice)
				{
				case 1:
					System.out.print("\nPlease enter starting position:");
					term = userScanner.nextLine();
					cities.breadthFirstTraversal(cities.vertexSet.get(term).getData(), salesperson);
					System.out.println('\n');
					break;
					
				case 2:
					break;
					
				case 3:
					cities.showAdjTable();
					System.out.println('\n');
					break;
					
				case 4:
					try
					{
						System.out.print("\nPlease enter filename you wish to save to: ");
						term = userScanner.nextLine();
						
						System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(term)), true));
						System.out.println("Adjacency List for Hamiltonian Circuit Graph:");
						cities.showAdjTable();
						System.setOut(ps_console);
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					break;
					
				case 5:
					exit = true;
					break;
				}
			}
			else
			{
				System.out.println("\nInvalid option entered, please try again.\n");
				continue;
			}
			
			System.out.println("Would you like to:");
			System.out.println("1. [Continue]");
			System.out.println("2. [Exit to Main Menu]");
			System.out.print("\nPlease enter choice now: ");
			choice = userChoice.nextInt();
			
			if(choice == 1)
				continue;
			if(choice == 2)
			{
				exit = true;
				break;
			}
		}
	}
	
	public static void solveProblem()
	{
		
	}
	
	public static void addCity()
	{
		
	}
	
	public static void removeCity()
	{
		
	}
	
	public static void undoRemove()
	{
		
	}

	public static Scanner openInputFile()
	{
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try
		{
			scanner = new Scanner(file);
		}
		
		catch(FileNotFoundException fe)
		{
			System.out.println("Can't open input file.\n");
			return null;
		}
		return scanner;
	}
}
