package computational_geometry_final_assiment;

public class Triangle {

	Edge e1,e2,e3;
	Point p1,p2,p3;
	
//	public Triangle(Edge e1,Edge e2,Edge e3) {
//		this.e1=e1;
//		this.e2=e2;
//		this.e3=e3;
//		
//	}
	
	public Triangle(Point p,Point q,Point r) {

		p1=p;
		p2=q;
		p3=r;
		e1=new Edge(p, q, this);
		e2=new Edge(q, r,this);
		e3=new Edge(r, p, this);
	}
}
