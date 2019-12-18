package b10;

import java.io.*;
import java.util.*;


 class DataPoint 
{
public boolean realValue, predictedValue; 
public DataPoint(boolean rv, boolean pv)
{ this.realValue = rv; 
this.predictedValue = pv; 
} 
} 



public class B10 {

	public static void main(String[] args) throws IOException,FileNotFoundException {
		// TODO Auto-generated method stub
		
		BufferedReader csv = new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b10/input.csv"))); 
		String recordLine = csv.readLine(); 
		boolean rv, pv;
		 ArrayList<DataPoint> al = new ArrayList<>();
		 
		 while(recordLine!=null) {
			 
			 String[] recordArray = recordLine.split(",");
			  if(recordArray[0].equals("T")) 
			  { rv = true; }
			  else 
			  { rv = false; } 
			  
			  if(recordArray[1].equals("T")) 
			  { pv = true;
			 } 
			  else {
			 pv = false;
			 } 
			  DataPoint d = new DataPoint(rv, pv); 
			  al.add(d);
			  recordLine=csv.readLine(); 

			 
			 
		 }

		 
		 double truePositive=0, falsePositive=0, trueNegetive=0 ,falseNegetive=0; 
		 
		 double w1 = 2, w2 = 1, w3 = 1, w4 = 2; 
		 
		 for(DataPoint d : al) 
		 { 
			 if(d.realValue && d.predictedValue)
			 { truePositive++; } 
			 if(!d.realValue && d.predictedValue) 
			 { falsePositive++; } 
			 if(!d.realValue && !d.predictedValue) 
			 { trueNegetive++; } 
			 if(d.realValue && !d.predictedValue) 
			 { falseNegetive++; } 
			 
		 }
		 
		 System.out.println(truePositive + " " + falseNegetive + " " + falsePositive + " " + trueNegetive); 
		 double sensitivity = truePositive/(truePositive + falseNegetive);
		 double specificity = trueNegetive/(trueNegetive + falsePositive); 
		 double recall = truePositive/(truePositive + falseNegetive); 
		 double precision = truePositive/(truePositive+falsePositive); 
		 double weightedAccuracy = (w1*truePositive + w4*trueNegetive)/ (w1*truePositive + w2*falseNegetive + w3*falsePositive + w4*trueNegetive);
		 
		 System.out.println("Sensitivity: " + sensitivity); 
		 System.out.println("Specificity: " + specificity); 
		 System.out.println("Precision: " + precision); 
		 System.out.println("Recall: " + recall); 
		 System.out.println("Weighted Accuracy: " + weightedAccuracy);

		 csv.close();
		 
	}

}
