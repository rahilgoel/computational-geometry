import java.util.ArrayList;

class point{
	
	
		public point(int i, int j) {
		// TODO Auto-generated constructor stub
			this.x=i;
			this.y=j;
	}
		int x;
		int y;
	}

public class giftWrappingAlgo {

	public static int orientation(point p, point q, point r){
		int val = (q.y-p.y)*(r.x-q.x) - (q.x-p.x)*(r.y-q.y);
		
		if(val==0){
			return 0;
		}
		else{
			return (val>0)?1:2;
		}
	}
	
	static void convexHull(point points[], int n){
	if(n < 3){
		return;
	}
	ArrayList<point> result = new ArrayList<point>();
	
	int l=0;
	for(int i=1; i<n ;i++){
		if(points[i].x<points[l].x){
			l=i;
		}
	}
	int p=l,q;
	do{
		result.add(points[p]);
		
		q=(p+1)%n;
		for(int i=1;i<n;i++){
			if(orientation(points[p],points[i],points[q])==2){
				q=i;
			}
		}
		p=q;
	}while(p!=l);
		
	
	for(int i =0;i<result.size();i++){
		System.out.println("( "+result.get(i).x +""+result.get(i).y +" )");
	}
	}
	
	
	
	
	
	public static void main(String[] args){
		point[] points=new point[7];
		points[0]=new point(0,3);
				points[1]=new point(2,2);
				points[2]=new point(1,1);
				points[3]=new point(2,1);
				points[4]=new point(3,0);
				points[5]=new point(0,0);
				points[6]=new point(3,3);
				convexHull(points,7);
		
		
	}
	
}
