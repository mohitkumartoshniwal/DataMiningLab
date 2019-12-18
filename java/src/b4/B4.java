package b4;

import java.io.*;
import java.util.*;

public class B4 {

	Scanner in=new Scanner(System.in);
	static ArrayList<String[]> csvLines;
	
	static private ArrayList<Double> calculateGiniIndexAndIG(int col) {
		
		double giniIndexValue = 0;
		double IGvalue=0;
		
		int numberOfColumns = csvLines.get(0).length;
		HashMap<String, ArrayList<String[]>> hm = new HashMap<>();
		
		for(String[] currentLine : csvLines) {
			String key = currentLine[col];
			if(hm.containsKey(key)) {
				ArrayList<String[]> arr = hm.get(key);
				arr.add(currentLine);
				hm.put(key, arr);
			}
			else {
				ArrayList<String[]> arr = new ArrayList<>();
				arr.add(currentLine);
				hm.put(key, arr);
			}
			
			
		}
		
		ArrayList<Double> nodesGiniIndexValues = new ArrayList<>();
		ArrayList<Double> IGValues = new ArrayList<>();
		for(Map.Entry<String, ArrayList<String[]>> m : hm.entrySet()) {
			ArrayList<String[]> currentNode = m.getValue();
			int size = currentNode.size();
			
			double numberOfYes=0, numberOfNo=0;
			for(String[] currentLine : currentNode) {
				if(currentLine[numberOfColumns-1].equals("Yes")) {
					numberOfYes++;
				}
				else {
					numberOfNo++;
				}
			}
			
			double currentNodeGiniValue; 
			currentNodeGiniValue = 1 - (Math.pow((numberOfYes/size), 2)) - (Math.pow((numberOfNo/size), 2));
			currentNodeGiniValue  = currentNodeGiniValue*currentNode.size();
			nodesGiniIndexValues.add(currentNodeGiniValue);
			
			double currentNodeIGvalue;//
			//log has to be in base 2
			//used log property
			currentNodeIGvalue= (-1)* ((numberOfYes/size)*(Math.log10(numberOfYes/size)/Math.log10(2))) - ((numberOfNo/size)*(Math.log10(numberOfNo/size)/Math.log10(2)));
			currentNodeIGvalue=currentNodeIGvalue*currentNode.size();
			IGValues.add(currentNodeIGvalue);
		}
		
		for(Double d : nodesGiniIndexValues) {
		
			giniIndexValue += d/csvLines.size();
			
			
		}
		
		for(Double d : IGValues) {
			
			IGvalue += d/csvLines.size();
			
			
		}
		
		ArrayList<Double> res =new ArrayList<Double>() ;
		res.add(giniIndexValue);
		res.add(IGvalue);
		
		return res;
	}
	
	static public void findBestSplit() {
		int numberOfColumns = csvLines.get(0).length;
		int ginicolumnToSplit = -1;
		int IGcolumnToSplit = -1;
		
		double minGiniIndexValue = 999;
		double maxInfoGain=Integer.MIN_VALUE;
		
		for(int i=0; i<numberOfColumns-1; i++) {
			
			ArrayList<Double> res =calculateGiniIndexAndIG(i);
			double giniValue = res.get(0);
			double IGValue=res.get(1);
			
			System.out.println("Gini value for Column " + i + ": " + giniValue);
			System.out.println("IG value for Column " + i + ": " + IGValue);
			
			if(giniValue < minGiniIndexValue) {
				minGiniIndexValue = giniValue;
				ginicolumnToSplit = i;
			}
			
			if(IGValue > maxInfoGain) {
				maxInfoGain = IGValue;
				IGcolumnToSplit = i;
			}
			
			
		}
		
		System.out.println("\nBest column to split acc to gini index: " + ginicolumnToSplit);
		System.out.println("\nBest column to split acc to IG: " + IGcolumnToSplit);
	}
	
	
	 public static void main(String[] args) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		
		csvLines=new ArrayList<String[]>();

		String ipLine;
		BufferedReader br=new BufferedReader(new FileReader(new File("/Users/mohit/eclipse-workspace/DMLab/src/b4/input.csv")));
		ipLine=br.readLine();
		while(ipLine!=null){
			
			String[] rowData=ipLine.split(",");
			ipLine=br.readLine();
			csvLines.add(rowData);
		}
		
		for(String[] currentLine : csvLines) {
			for(String s : currentLine) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		findBestSplit();
		
		

	}

}
