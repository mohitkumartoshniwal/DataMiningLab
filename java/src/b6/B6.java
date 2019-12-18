package b6;
import java.io.*;
import java.util.*;

class record{ 
	
double dist; 
String label; 
int x,y,z; 

public record(double p,String q,int r,int s,int t){ 
dist=p;label=q;x=r;y=s;z=t; 

} 
}


public class B6 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader csv=new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b6/input.csv")));

		int recordElemements[]=new int[3];//the no of attributes required to classify a record
		int x,y,z;
		
		Scanner in = new Scanner(System.in); 
		System.out.println("Enter x,y,z to classify in Class A,B,C"); 
		 x = in.nextInt(); 
		 y = in.nextInt(); 
		 z = in.nextInt(); 
		
		System.out.println("enter number of nearest neighbours"); 
		int k = in.nextInt(); 

		int i; 
		ArrayList<record> al = new ArrayList<>(); //to store the neighbours
		
		String recordLine=csv.readLine();
		while(recordLine!=null) {
			
			String recordArray[]=recordLine.split(",");
			double sum=0;
			
			for(i=0;i<3;i++) 
			recordElemements[i]=Integer.parseInt(recordArray[i]);
			
			sum = Math.pow(x-recordElemements[0],2) + Math.pow(y-recordElemements[1],2)+Math.pow(z-recordElemements[2],2);///euclidian distance
						
			double res = Math.sqrt(sum); //abs needed?
				
			record temp=new record(res,recordArray[3],recordElemements[0],recordElemements[1],recordElemements[2]);
				
			al.add(temp);
			
			recordLine=csv.readLine();
			
		
			
		}
		
		
		Collections.sort(al,new Comparator() {//Sorting the list based on dist

			public int compare(record p1, record p2) {
				// TODO Auto-generated method stub
				if(p1.dist==p2.dist)
					return 0;
				
				if(p1.dist>p2.dist)
					return 1;
				else
					return -1;
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		
		int labels[]=new int[3];//three labels : A B C
		//0 for A
		//1 for B
		//2 for C
		
		System.out.println("K nearest neighbours are");
		
		
		for(record temp:al ) {
			
			if(k==0)//0 neighbours
				break;
			System.out.println(temp.x+" "+temp.y+" "+temp.z+" "+temp.label);

			
			k--;//set initially
			String currentlabel=temp.label;
			if(currentlabel.equals("A"))
				labels[0]++;
			else if(currentlabel.equals("B"))
				labels[1]++;
			else if(currentlabel.equals("C"))
				labels[2]++;
		
			
		}
		
		char resLAbel = 0;
		
		int resLabelCount=Math.max(labels[0], Math.max(labels[1], labels[2]));
		
		if(resLabelCount==labels[0]) {
			resLAbel='A';
			
		}
		else if(resLabelCount==labels[1]) {
			resLAbel='B';
			
		}
		else if(resLabelCount==labels[2]) {
			resLAbel='C';
			
		}
		
		System.out.println("Label for "+x+" "+y+" "+z+" is :"+resLAbel);
				csv.close(); 
				in.close();
		


	}

}
