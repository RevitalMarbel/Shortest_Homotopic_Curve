package computational_geometry_final_assiment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;


public class Functions2 {

	public static ArrayList<Triangle> Triangulation(ArrayList<Point> points){
		//TODO- INITIALLIZE - BUTTON LEFT
		points.sort(new PointComperator());
		for (Point point2 : points) {
		System.out.println("point left: x: "+point2.getX()+"y: " +point2.getY());
		}
		ArrayList<Triangle> T=new ArrayList<Triangle>();
		boolean isFirstTimePositive=false;
		Stack<Point> special= new Stack<Point>();
		special.push(MainFrame.buutomLeft);
		special.push(points.get(0));
		special.push(points.get(1));
		Point next= points.get(1);
		Point cur= points.get(0);
		
		T.add(new Triangle(MainFrame.buutomLeft, points.get(0), points.get(1)));
		
		Stack<Point> pivots = new Stack<Point>();
		pivots.push(MainFrame.buutomLeft);
		//pivots.push(points.get(1));
		
		for (int i = 1; i < points.size()-1; i++) {
			cur=points.get(i);
			next=points.get(i+1);
			if(Orient(points.get(i-1),cur,next)) {
				
				//pivots.add(cur);
				T.add(new Triangle(pivots.peek(), cur, next));
				System.out.println(" "+pivots.peek()+" "+cur+" "+ next);
				Point temp=pivots.pop();
				if(pivots.isEmpty())
					pivots.push(temp);
				while(pivots.peek() != MainFrame.buutomLeft && Orient(pivots.peek(),temp,next)) {
					T.add(new Triangle(pivots.peek(), temp, next));
					temp=pivots.pop();
				}
				if(pivots.peek() == MainFrame.buutomLeft && Orient(points.get(0),temp,next))
					T.add(new Triangle(pivots.peek(), temp, next));
				else
				pivots.push(temp);
				special.add(cur);
				/*if (isFirstTimePositive)
				{
					isFirstTimePositive=false;
					special=cur;
				}*/
			}
			else {
				while (!Orient(special.get(special.size()-2), special.peek(), next)) {
					T.add(new Triangle(special.get(special.size()-2), special.peek(), next));
					special.pop();
				}
				special.push(next);
				pivots.push(cur);
			}
		}
		
	
		
		
		return T;
	}
	
	
	
	
	public static boolean Orient(Point p, Point q ,Point r) {
		int det = (q.getX()-p.getX())*(r.getY()-p.getY()) - (q.getY()-p.getY())*(r.getX()-p.getX());
		return det >= 0 ? true : false;
		
	}
	
	//todo - sort
}
