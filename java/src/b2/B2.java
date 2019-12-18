package b2;

import java.io.*;
import java.util.*;

public class B2 {
	Scanner in=new Scanner(System.in);
	static ArrayList<String[]> csvLines;
	
	static public void  missingString(int col, String defValue) {
		HashMap<String, Integer> hm=new HashMap<String,Integer>();
		for(String[] row: csvLines) {
			if(!row[col].equalsIgnoreCase(defValue)) {
				hm.put(row[col], hm.getOrDefault(row[col],0)+1);
			}
		}
		
		String maxValName = null;
		int maxVal=0;
		for(Map.Entry<String, Integer> me:hm.entrySet()) {
			if(me.getValue()>maxVal)
				maxValName=me.getKey();
		}
		
		for(String[] row: csvLines) {
			if(row[col].equalsIgnoreCase(defValue)) {
				row[col]=maxValName;
			}
		}
		
		
	}
	
	static public void missingInteger(int col, String defValue) {
		float avg=0;
		int count=0;
		for(String[] row: csvLines) {
			if(!row[col].equalsIgnoreCase(defValue)) {
				avg+=Float.parseFloat(row[col]);
			}
			count++;
		}
		avg=avg/count;
		for(String[] row: csvLines) {
			if(row[col].equalsIgnoreCase(defValue)) {
				row[col]=Integer.toString((int)avg);
			}
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub

		
		csvLines=new ArrayList<String[]>();

		String ipLine;
		BufferedReader br=new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b2/input.csv")));
		ipLine=br.readLine();
		while(ipLine!=null){
			
			String[] rowData=ipLine.split(",");
			ipLine=br.readLine();
			csvLines.add(rowData);
		}
		
		missingString(5, "NA");
		missingInteger(1, "NA");
		
		for(String[] rowData:csvLines) {
			String newData=Arrays.toString(rowData);
			newData=newData.substring(1,newData.length()-1);
			System.out.println(newData);
			
		}
		
		try {
			FileWriter fw=new FileWriter(new File("/Users/mohit/eclipse-workspace/DMLab/src/b2/output.csv"));
			for(String[] rowData:csvLines) {
				String newData=Arrays.toString(rowData);
				newData=newData.substring(1,newData.length()-1);
				fw.write(newData);
				fw.write('\n');
			}
			
			fw.close();
			System.out.println("\nFile generated successfully!");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
