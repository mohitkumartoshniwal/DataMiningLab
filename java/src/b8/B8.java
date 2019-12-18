package b8;

import java.util.Scanner;

public class B8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//y=mx+b
		
		double m,b;
		m=b=-1000;
		
		double mean_x=0,mean_y=0,cov_xy=0,var_x=0;
		double[] x=  { 1, 2, 3, 4, 5, 6, 7, 8 }; 
		double[] y=  { 7,12,17,22,27,32,37,42}; 
		
		double total_x=0,total_y=0;
		for(int i=0;i<x.length;i++) {
			total_x+=x[i];
			total_y+=y[i];
		}
		
		mean_x=total_x/x.length;
		mean_y=total_y/y.length;
		
		double var_x_num=0,cov_xy_num=0;
		for(int i=0;i<x.length;i++) { 
			var_x_num += Math.pow(x[i] - mean_x, 2); 
			cov_xy_num += (x[i] - mean_x) * (y[i] - mean_y); 
			} 
		
		var_x = var_x_num/x.length;
		cov_xy = cov_xy_num/x.length;
		
		m = cov_xy/var_x;
		b = mean_y-m*mean_x;
		
		System.out.println("enter the value for x"); 
		Scanner in = new Scanner(System.in);
		double X = in.nextInt(); 
		double Y = X*m+b; 
		System.out.println("Predicted output for "+X+" : "+Y); 
		in.close();
		
		
	}

}
