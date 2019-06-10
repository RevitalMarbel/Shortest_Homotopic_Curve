package computational_geometry_final_assiment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class ReadFromFile 
{
	
	public static Point q;
	static Integer sizeOfArray=0;
	static Integer sizeOfCurve=0;
	static ArrayList<Point> points = new  ArrayList<Point>();
	static ArrayList<Point> curve = new  ArrayList<Point>();
	

	public static void loadData(String path) throws Exception
	{
		File file = new File(path);
		//LinkedList<Point> points = new  LinkedList<Point>();
		
		BufferedReader br = new BufferedReader(new FileReader(file));

		String numOfPointsStr = br.readLine();
		sizeOfArray = Integer.parseInt(numOfPointsStr);

		String nextLine = null;		
		
		LinkedList<Integer> data1 = new LinkedList<Integer>();
		LinkedList<Integer> data2 = new LinkedList<Integer>();
		nextLine = br.readLine();
		String[] pointsValuesStr = nextLine.split(" ");
			
		for (String string : pointsValuesStr) 
			{
			data1.add(Integer.parseInt(string));
			}
		
		for (int i = 0; i < data1.size() - 1; i += 2)
		{
			int x = data1.get(i);
			int y = data1.get(i + 1);

			points.add(new Point(x, y));
		}
		numOfPointsStr = br.readLine();
		sizeOfCurve = Integer.parseInt(numOfPointsStr);
		
		nextLine = br.readLine();
		pointsValuesStr = nextLine.split(" ");
		for (String string : pointsValuesStr) 
		{
		data2.add(Integer.parseInt(string));
		}
	
		for (int i = 0; i < data2.size() - 1; i += 2)
		{
			int x = data2.get(i);
			int y = data2.get(i + 1);
	
			curve.add(new Point(x, y));
		}
			
		//System.out.println("###" + numOfPoints + " " + points.size() + "###" );
		
//		String qStr = br.readLine();    // q line
//		String[] qValuesStr = qStr.split(" ");
//		int x = Integer.parseInt(qValuesStr[0]);
//		int y = Integer.parseInt(qValuesStr[1]);
//		q = new Point(x,y);
	}

	public static void main(String[] args) throws Exception //Test
	{
		loadData("t.txt");
		System.out.println(sizeOfArray);
		System.out.println(sizeOfCurve);
		System.out.println(points);
		System.out.println(curve);
//		for (Point p : points) 
//		{
//			System.out.println(p);
//		}

//		System.out.println("points size: " + points.size());
//		System.out.println("q: " + q);

	}

}
