package computational_geometry_final_assiment;

public class test {

	public static void main(String[] args) {
		Point p1= new Point(3,1);
		Point p2= new Point(3,3);
		Point p3= new Point(0,2);
		Point p4= new Point(2,2);
		
		
		Edge e1= new Edge(p1,p2);
		Edge e2= new Edge(p3,p4);
		System.out.println(Functions.Intersect(e1,e2));	
		
		Point p5= new Point(2,1);
		Point p6= new Point(0,2);
		Point p7= new Point(1,1);
		
		Point p8= new Point(1,1);
		
		Triangle t= new Triangle(p5,p6,p7);
		System.out.println(Functions.isPointInTriangle(p8, t));
		
		
	}
	
	
}
