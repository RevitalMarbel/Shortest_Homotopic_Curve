package computational_geometry_final_assiment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;


public class Functions {

	public static ArrayList<Triangle> Triangulation(ArrayList<Point> points){
		//points.add(new Point(0,0));
		//MainFrame.buutomLeft=new Point(0,0);
		//points.add(new Point(MainFrame.xsize, 0));
		//points.add(new Point(0,MainFrame.ysize));
		//points.add(new Point(MainFrame.xsize, MainFrame.ysize));
		
		
		
		points.sort(new PointComperator());
		points.add(MainFrame.buutomLeft);
		Stack<Point> CH= new Stack<Point>();
		ArrayList<Triangle> T=new ArrayList<Triangle>();
		CH.push(MainFrame.buutomLeft);
		CH.push(points.get(0));
		CH.push(points.get(1));
		
		for (int i = 0; i < points.size(); i++) {
			Point curr=points.get(i);
			if(Orient(CH.get(CH.size()-2),CH.peek(), curr))
			{
				T.add(new Triangle(curr, CH.peek(), MainFrame.buutomLeft));
				CH.push(curr);				
			}
			else
			{
				T.add(new Triangle(CH.peek(), curr, MainFrame.buutomLeft));

				while(!Orient(CH.get(CH.size()-2),CH.peek(),curr)) {
					Point temp = CH.pop();
					T.add(new Triangle(temp, CH.peek(), curr));
				}
				CH.push(curr);
			}
		}
		
		return T;
	}
	
	
	public static boolean Intersect(Edge e1, Edge e2 )
	{
		
		if(Orient(e1.p, e1.q, e2.p)!= Orient(e1.p, e1.q, e2.q)  && Orient(e2.p, e2.q, e1.p)!= Orient(e2.p, e2.q, e1.q) )
			return true;
		return false;
	}
	
	public static ArrayList<Edge> getFinalPath(ArrayList<Edge> edges)
	{
		ArrayList<Edge> finalE= (ArrayList<Edge>) edges.clone();
		int i=0;
		while(i<finalE.size()-1) {
			if(finalE.get(i).equals(finalE.get(i+1)))
				{
				System.out.println("duplicae  "+ finalE.get(i) +" "+ finalE.get(i+1));
				finalE.remove(i);
				finalE.remove(i);
				if(i>0)
					i--;
			
				}
			else
				i++;
		}
		
		return finalE;
	}
	
	
	public static ArrayList<Edge> getEdgeList(ArrayList<Triangle> t, ArrayList<Point> c)
	{
		ArrayList<Edge> edgeList= new ArrayList<Edge> ();
		
			//search the triangle that contains c(i)
			Triangle tempT =null;
			for (Triangle T : t) {
				if (isPointInTriangle(c.get(0), T))
				{
					tempT=T;
				}
				
			}
			int i=0;
			System.out.println("first triangle "+ tempT);
			if(tempT!=null)
			{
				while (i<c.size()-1) {
				System.out.println("triangle is not null");
				System.out.println("in "+ tempT);
				Edge mainEdge= new Edge(c.get(i), c.get(i+1));
				System.out.println("main edge" + mainEdge);
				Edge lastEdge=mainEdge;
				while( ! isPointInTriangle(c.get(i+1), tempT)&& tempT!=null)
				{
					
					String e2=Edge.EdgeToKey( tempT.p1,tempT.p2);
					Edge e=Edge.hashE.get(e2);
					if(Intersect(Edge.hashE.get(e2), mainEdge)&& !e.equals(lastEdge))
					{

						edgeList.add(e);
						lastEdge=e;
						if(tempT.equals(e.t1))
							{tempT=e.t2;
							System.out.println("t1");}
						else {
							tempT=e.t1;
							System.out.println("t1");}
						System.out.println("intersect1 "+ e.t1 +"  "+ e.t2);
						
					}
					else {
						String e1=Edge.EdgeToKey( tempT.p2,tempT.p3);	
						e=Edge.hashE.get(e1);
						if(Intersect(e, mainEdge)  && !e.equals(lastEdge ))
						{
						
							edgeList.add(e);
							lastEdge=e;
							if(tempT.equals(e.t1))
								tempT=e.t2;
							else
								tempT=e.t1;
							System.out.println("intersect2 "+ e.t1 +"  "+ e.t2);
							
						}
						else {
						String e3=Edge.EdgeToKey( tempT.p1,tempT.p3);	
						e=Edge.hashE.get(e3);
						if(Intersect(e, mainEdge)&&!e.equals(lastEdge))
						{
							edgeList.add(e);
							lastEdge=e;
							if(tempT.equals(e.t1))
								tempT=e.t2;
							else
								tempT=e.t1;
							System.out.println("intersect3 "+ e.t1 +"  "+ e.t2);
							
						}
				}}}
				i++;}
				
				
			}
		
		return edgeList;
	}
	

	
	public static boolean isPointInTriangle(Point p, Triangle t)
	{
		if(Orient(t.p1, t.p2, p)==Orient(t.p2, t.p3, p) &&
				Orient(t.p1, t.p2, p)==Orient(t.p3, t.p1, p) )
		{
			return true;
		}
		return false;
		
	}
	public static boolean Orient(Point p, Point q ,Point r) {
		int det = (q.getX()-p.getX())*(r.getY()-p.getY()) - (q.getY()-p.getY())*(r.getX()-p.getX());
		return det >= 0 ? true : false;
		
	}
	
	//todo - sort
}
