package b7;

import java.util.*; 

import java.io.*; 

class temp{

	double x,y,z;
	
	
	public String toString() {
		return x+" "+y+" "+z;
		
	}

}

class B7

{
	
	

	public static void main(String args[]) throws FileNotFoundException, IOException

	{

		BufferedReader csv = new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b7/input.csv")));

		String data = csv.readLine();

		Random rand = new Random();

		ArrayList<temp> al = new ArrayList<>();

		double[] weight = new double[3];//depends upon no of inputs in dataset+ w0

		double lrate = 0.02;

		while(data != null)

		{

		     String[] dataarray = data.split(",");

		     temp v = new temp();

		     v.x = Double.parseDouble(dataarray[0]);

		     v.y = Double.parseDouble(dataarray[1]);

		     v.z = Double.parseDouble(dataarray[2]);

		     al.add(v);
		     //System.out.println(v);

		     data = csv.readLine();

		}

		weight[0] = rand.nextDouble();//bias, xo=1 assumed

		weight[1] =rand.nextDouble();
		
		weight[2] = rand.nextDouble();
		
		System.out.println("initial wts "+weight[0]+" "+weight[1]+" "+weight[2]+"\n");

		for(int i =0;i<10000;i++)

		{

		     for(int j=0;j<al.size()-1;j++)//?size one less

		     {

		           int res=0;//update for every new cycle

		           double sum = weight[0]+weight[1]*(al.get(j)).x+weight[2]*(al.get(j)).y; 

		           if(sum>0)

		           	{res=1;}

		           double error = (al.get(j)).z-res;

		           weight[0]=weight[0]+error*lrate*1;

		           weight[1]=weight[1]+(lrate*error*(al.get(j)).x);

		           weight[2]=weight[2]+(lrate*error*(al.get(j)).y); 

		     }

		}

		System.out.println("enter x,y test data"); 
		Scanner in = new Scanner(System.in);

		double p = in.nextDouble();

		double q = in.nextDouble();

		double sum1 = weight[0]*1+weight[1]*p+weight[2]*q; 

		System.out.println(sum1);

		if(sum1>0)

		     System.out.println("predicted :1.0");

		 

		else

		     System.out.println("predicted :0.0");

		System.out.println("final weight[0] :"+weight[0]+"\nfinal weight[1] :"+weight[1]+"\n final weight[2] :"+weight[2]);

	}

}