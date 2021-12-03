package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GetIncreaseDecreaseCounts {

	private static File file;
	
	public GetIncreaseDecreaseCounts() {
		URL fileName = getClass().getResource("Input.txt");
		file = new File(fileName.getPath());
	}

	public ArrayList<Integer> convertFileToArray(File file) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	array.add(Integer.valueOf(line));
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}

	public DepthCounts calculateDepthCounts(int scansToSumTogether) {
		DepthCounts depthCounts = new DepthCounts();
		ArrayList<Integer> inputs = convertFileToArray(file);
		int prior = 0;
		for(int scanNum=0; scanNum<scansToSumTogether; scanNum++) {				
			prior += inputs.get(scanNum);
		}
		//start at the second input cause the first has no compare
		for (int index=1; index<(inputs.size()-(scansToSumTogether-1)); index++) {
			int total = 0;
			for(int scanNum=0; scanNum<scansToSumTogether; scanNum++) {				
				total += inputs.get(index+scanNum);
			}
			if(total > prior) {
				depthCounts.addIncrease();
			} else if (total < prior) {
				depthCounts.addDecrease();
			} else {
				depthCounts.addSame();
			}
			prior=total;
		}
		return depthCounts;
	}

	protected void setFileToUse(File file) {
		GetIncreaseDecreaseCounts.file = file;
	}

}
