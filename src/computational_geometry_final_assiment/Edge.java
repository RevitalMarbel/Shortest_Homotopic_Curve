package computational_geometry_final_assiment;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Edge {
	
	static Map<String, Edge> hashE= new HashMap<String, Edge>();
	
	
	Point p,q;
	Triangle t1= null;
	Triangle t2= null;
	
	public Edge(Point p, Point q)
	{
		this.p=p;
		this.q=q;
	}
	public Edge(Point p, Point q, Triangle t){
		String s=EdgeToKey(p,q);
		//Object k=hashE.get(s);
		if (hashE.containsKey(s))
		{
			Edge e =hashE.get(s);
			e.t2=t;
		}
		else
		{
		this.p=p;
		this.q=q;
		t1=t;
		hashE.put(EdgeToKey(this), this);
		}
	}
	
	
	public static String EdgeToKey(Edge e) {
		if (e.p.getX()< e.q.getX())
			return e.p.getX()+"_"+e.p.getY()+"_"+e.q.getX()+"_"+e.q.getY();
		else
			return e.q.getX()+"_"+e.q.getY()+"_"+e.p.getX()+"_"+e.p.getY();
	}
	public static String EdgeToKey(Point p, Point q) {
		if (p.getX()< q.getX())
			return p.getX()+"_"+p.getY()+"_"+q.getX()+"_"+q.getY();
		else
			return q.getX()+"_"+q.getY()+"_"+p.getX()+"_"+p.getY();
	}
	

}
