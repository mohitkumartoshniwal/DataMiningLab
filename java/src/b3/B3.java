package b3;

import java.io.*; 

import java.util.*;

class B3 {

	static boolean check(String x1,String x2) {

		//System.out.println("  before check "+x1+" "+x2+"\n");
		x2 = x2.replace("", ".*");
		//System.out.println(" check "+x1+" "+x2+"\n");

		if(x1.matches(x2)) {
			//System.out.println("matched");
			return true;
		}

		else{

			//System.out.println("not matched");
			 return false;
		}

		     

	}

	public static void main(String[] args) throws IOException,FileNotFoundException

	{

	      BufferedReader csv = new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b3/input.csv")));

	      String data = csv.readLine();

	      HashSet<String> items = new HashSet<>();

	      ArrayList<String> allPossibleSets = new ArrayList<>();

	      ArrayList<String> transactions = new ArrayList<>();

	      ArrayList<String> strules = new ArrayList<>();

	      double support = 0.4,confidence=0.5;

	      while(data != null)

	      {

	            String dataarray[] = data.split(",");

	            String temp1="";

	            for(String x:dataarray)

	            {

	            	  items.add(x);

	                  temp1=temp1+x;

	            }

	            transactions.add(temp1);

	            data = csv.readLine();

	      }
	      
	      System.out.println("All  transactions\n");
	      for(String b:transactions) {
	    	  System.out.println(b);
	      }
	      System.out.println();

	      String d[] = items.toArray(new String[items.size()]);

	      int n = d.length;

	      // generate all possible subset

	      for(int i=0;i < (1<<n); i++)

	      {

	            String temp="";

	            for(int j=0;j<n;j++)

	                  if(( i & (1<<j))>0)

	                        temp = temp+d[j];

	            allPossibleSets.add(temp);

	      }
	      
//	      System.out.println("All possible subsets\n");
//	      for(String a:allPossibleSets) {
//	    	  System.out.println(a);
//	      }
//	      System.out.println();

	      // generate frequent itemset

	      for(int i=1;i<=4;i++)

	      {

	     System.out.println("\nFrequent "+i+"-itemset");

	     for(String y:allPossibleSets)

	    	 if(i == y.length())

	            {

	                  double count = 0;

	                  for(String x:transactions) {
	                	  if(check(x,y))

                              count++;
	                  }

	                	  

	                  if(count/transactions.size() >= support)

	                  {

	                	  if(i == 4) {
	                		  strules.add(y); 
	                	  }

	                		

	                	  System.out.println(y + " ->"+count/transactions.size());

	                  }

	              }

	

		}

		System.out.println("\n----------Strong rules------------\n"); 
		
//		 for(String c:strules) {
//	    	  System.out.println(c);
//	      }
//	      System.out.println();

		//generate rules

		for(String p:strules)

		{

			System.out.println("\n-----For string "+p+"-----------");

		    char[] c = p.toCharArray();

		    n = c.length;

		    for(int i=0;i < (1<<n); i++)  //generate all subset

		    {

		            String temp3="",temp4="";

		            for(int j=0;j<n;j++)

		            {

		                  if(( i & (1<<j))>0)

		                        temp3 = temp3+c[j];

		                  else

		                        temp4 = temp4+c[j];

		            }

		            if(temp3.length() !=0 && temp3.length() != 4)

		                  {

				                  double count1=0,count2=0;

				                  for(String x:transactions)

				                  {

										if(check(x,p))

											count1++; 

										if(check(x,temp3))

											count2++;

								  }

										if(count2 > 0 && (count1/count2) >= confidence) System.out.println(temp3+"->"+temp4+" confidence: "+count1/count2);

		                  }

		     }

		}

	}

}