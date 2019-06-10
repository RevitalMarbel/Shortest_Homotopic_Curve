package computational_geometry_final_assiment;

import java.util.Comparator;

public class PointComperator implements Comparator<Point>
{
	@Override
	public int compare(Point q, Point r) 
	{
		Point p=MainFrame.buutomLeft;
		if (Functions.Orient(p, q, r))
			return -1;
		else 
			return 1;
	}


}
