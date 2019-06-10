package computational_geometry_final_assiment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements MouseListener, Action
{
	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Point> curve = new ArrayList<Point>();
	static ArrayList<Edge> edge = new ArrayList<Edge>();
	static ArrayList<Edge> final_edge = new ArrayList<Edge>();
	ArrayList<Triangle> T;
	
	
	static Point buutomLeft= new Point(0,0);
	JButton bT;
	JButton bRandom;
	JButton bCurve;
	JButton b_res_Curve;
	JButton b_load_file;
	static int xsize=1000;
	static int ysize= 700;
	static int maxX=xsize,maxY=ysize,minX=0,minY=0;
	
	public MainFrame() 
	{
		this.setSize(xsize, ysize);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addMouseListener(this);
		initGUI();
	}
	
	private void initGUI() 
	{
		JPanel panel = new JPanel();
	
		b_load_file = new JButton();
		b_load_file.setAction(this);
		b_load_file.setSize(40,40);
		b_load_file.setEnabled(true);
		b_load_file.setText("Load File t");
		panel.add(b_load_file);
		
		bT = new JButton();
		bT.setAction(this);
	    bT.setSize(40,40);
	    bT.setEnabled(true);
	    bT.setText("Triangulation");
		panel.add(bT);
		
		bRandom = new JButton();
		bRandom.setAction(this);
		bRandom.setSize(40,40);
		bRandom.setEnabled(true);
		bRandom.setText("cerate Random Array");
		panel.add(bRandom);
		
		bCurve = new JButton();
		bCurve.setAction(this);
		bCurve.setSize(40,40);
		bCurve.setEnabled(true);
		bCurve.setText("cerate Random Curve");
		panel.add(bCurve);
		
		
		b_res_Curve = new JButton();
		b_res_Curve.setAction(this);
		b_res_Curve.setSize(40,40);
		b_res_Curve.setEnabled(true);
		b_res_Curve.setText("get crve path");
		panel.add(b_res_Curve);
		
		this.add(panel);
	}

	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		//g.clearRect(0, 0, 500, 500);
		g.setColor(Color.MAGENTA);
		
		//g.fillOval(this.buutomLeft.getX(), this.buutomLeft.getY(), 10, 10);
		//System.out.println("buttom left: x: "+this.buutomLeft.getX()+"y: " +this.buutomLeft.getY());
		for (int i = 0; i < curve.size()-1; i++) {
			
			g.fillOval(curve.get(i).getX()-5, curve.get(i).getY()-5, 10, 10);
			g.drawLine(curve.get(i).getX(), curve.get(i).getY(), curve.get(i+1).getX(), curve.get(i+1).getY());
			
		}
		if (curve.size()>0)
			g.fillOval(curve.get(curve.size()-1).getX()-5, curve.get(curve.size()-1).getY()-5, 10, 10);
		
		
		
		
		
		g.setColor(Color.BLACK);
		for (Point point : points) 
		{
			g.fillOval(point.getX()-5, point.getY()-5, 10, 10);
			//System.out.println("point  left: x: "+point.getX()+"y: " +point.getY());
		}
		for (Edge edge: Edge.hashE.values()) {
			
			g.drawLine(edge.p.getX(), edge.p.getY(), edge.q.getX(), edge.q.getY());
		}
	
		if (edge !=null) {
		g.setColor(Color.RED);
		for (Edge e : edge) {
			g.drawLine(e.p.getX(), e.p.getY(), e.q.getX(), e.q.getY());
		}}
		if (final_edge !=null) {
			g.setColor(Color.GREEN);
			for (Edge e : final_edge) {
				g.drawLine(e.p.getX(), e.p.getY(), e.q.getX(), e.q.getY());
			}}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		Point p = new Point(e.getX(), e.getY());
		points.add(p);
		repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		
		if(e.getSource() == b_load_file)
		{
			points.clear();
			edge.clear();
			System.out.println("load file t.txt");
			try {
				ReadFromFile.loadData("t.txt");
				points=ReadFromFile.points;
				curve= ReadFromFile.curve;			
				System.out.println(points);
				System.out.println(curve);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			repaint();
		}
		
			else if(e.getSource() == bRandom)
		{
			points.clear();
			edge.clear();
			System.out.println("cerate Random Array");
			for (int i = 0; i < 30; i++) {
				int rx=(int) (Math.random()*xsize);
				int yx=(int) (Math.random()*ysize);
				points.add(new Point(rx,yx));
			}
			repaint();
			
		}
		else if(e.getSource() == bCurve)
		{
			System.out.println("cerate Random Curve");
			for (int i = 0; i < 5; i++) {
				int rx=(int) (Math.random()*(maxX));
				int yx=(int) (Math.random()*(maxY));
				curve.add(new Point(rx,yx));
			}
			repaint();
		}
			
		else if(e.getSource() == bT)
		{
			System.out.println("Triangulation");
		

		buutomLeft=points.get(0);
		int j=0;
		maxX=points.get(0).getX();
		maxY=points.get(0).getY();
		minX=points.get(0).getX();
		minY=points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			if(points.get(i).getX() <minX )
				minX=points.get(i).getX();
			if(points.get(i).getY() <minY )
				minY=points.get(i).getY();
			
			if(points.get(i).getX() >maxX )
				maxX=points.get(i).getX();
			if(points.get(i).getY() >maxY )
				maxY=points.get(i).getY();
			
			//if(points.get(i).getX()<=buutomLeft.getX() && points.get(i).getY()<=buutomLeft.getY() )
			//{	
			//	buutomLeft=points.get(i);
			//	j=i;
			//}
		}
		//points.remove(j);
		repaint();
		// call triangulation
		//points.add(new Point(minX, minY));
		minX=minX-20;
		minY=minY-20;
		maxX=maxX+20;
		maxY=maxY+20;
		buutomLeft=new Point(minX, minY);
		points.add(new Point(maxX,maxY));
		points.add(new Point(minX, maxY));
		points.add(new Point(maxX, minY));
		if(points.size()>4) {
		T = Functions.Triangulation(points);
		
		  
		/*for (Triangle triangle : T) {
			edge.add(triangle.e1);
			edge.add(triangle.e2);
			edge.add(triangle.e3);
		}
		//edge.add(new Edge(points.get(0),points.get(1)));
		*/
		}
		repaint();
		}
		else if(e.getSource() == b_res_Curve)
			
		{
			for (Edge ed: Edge.hashE.values()) {
				//System.out.println(ed.p.toString()+"  "+ ed.q.toString()+"  "+ed.t1.toString() +"  "+ed.t2.toString());
				System.out.println(ed.p.toString()+"  "+ ed.q.toString());
			}
			System.out.println(Edge.hashE);
			edge=Functions.getEdgeList(T, curve);
			final_edge=Functions.getFinalPath(edge);
			for (Edge er : edge) {
				System.out.println(er);
			}
			System.out.println("         ");
			for (Edge er : final_edge) {
				System.out.println(er);
			}
			System.out.println("final curve" +final_edge);
			repaint();
		}
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
	return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}	
}