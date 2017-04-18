import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class point implements Comparable<point> {
	int x;
	int y;
point(int i,int j){
	this.x=i;
	this.y=j;
}
	@Override
	public int compareTo(point o) {
		// TODO Auto-generated method stub
		if (this.x > o.x) {
			return 1;
		} else if (this.x == o.x) {
			if (this.y > o.y) {
				return 1;
			} else if (this.y == o.y) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

}

public class GrahamScanAlgo {
	private static Stack<point> hull = new Stack<point>();
	static point p0;

	static point nextToTop(Stack<point> hull) {
		point p = hull.pop();
		point res = hull.peek();
		hull.push(p);
		return res;
	}

	public static void GrahamScan(point[] points) {
		if (points == null)
			throw new IllegalArgumentException("argument is null");
		if (points.length == 0)
			throw new IllegalArgumentException("array is of length 0");

		// defensive copy
		int n = points.length;
		point[] a = new point[n];
		for (int i = 0; i < n; i++) {
			if (points[i] == null)
				throw new IllegalArgumentException("points[" + i + "] is null");
			a[i] = points[i];
		}
		Arrays.sort(a);
		point[] arr=new point[n-1];
		for(int i=0;i<n-1;i++){
			arr[i]=a[i+1];
		}
		p0=a[0];
		Arrays.sort(arr, comp);
		int m=0;		
		for(int i=0;i<n-1;i++){
			while(i<n-2 && orientation(p0 ,arr[i],arr[i+1])==0){
				i++;
				arr[m]=arr[i];
				m++;
			}
		}
		if(m<3)return;
		hull.push(p0);
		hull.push(arr[0]);
		hull.push(arr[1]);
		for(int i=2;i<m;i++){
			while(orientation(nextToTop(hull),hull.peek(),arr[i])!=2){
				hull.pop();
				hull.push(arr[i]);
			}
		}
		while(!hull.isEmpty()){
			point p=hull.peek();
			System.out.println(p.x+""+p.y);
			hull.pop();
		}

	}

	

	static int distSq(point p1, point p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	static Comparator<point> comp = new Comparator<point>() {
		@Override
		public int compare(point o1, point o2) {
			int o = orientation(p0, o1, o2);
			if (o == 0) {
				return (distSq(p0, o2) >= distSq(p0, o1)) ? -1 : 1;
			}
			return (o == 2) ? -1 : 1;
		}
	};

	public static int orientation(point p, point q, point r) {
		int val = (q.y - p.y) * (r.x - q.x) - (r.y - q.y) * (q.x - p.x);
		if (val == 0)
			return 0;
		return (val > 0) ? 1 : 2;
	}
	
public static void main(String[] args){
	point points[] =new point[8];
	points[0]=new point(0,3);
	points[1]=new point(1,1);
	points[2]=new point(2,2);
	points[3]=new point(4,4);
	points[4]=new point(0,0);
	points[5]=new point(1,2);
	points[6]=new point(3,1);
	points[7]=new point(3,3);
	
	GrahamScan(points);
	
}
}
