import java.io.*;
import java.util.ArrayList;

interface Istorage {
	void store(ArrayList<String> text);
	void readData();
	
	
}

class StoredTxTFile implements Istorage{
	BufferedWriter writer;
	ArrayList<String> dataStored;
	
	public StoredTxTFile() {
		writer = null;
		dataStored = new ArrayList<String>();
	}
	
	private void copyText(ArrayList<String> text){
		for(String s : text){
			dataStored.add(s);
			dataStored.add("\n");
		}
	}//Used to copy text to an ArrayList in this class	
	
	public void store(ArrayList<String> text){
		copyText(text);
		readData();
	}
	
	public void readData() {
		
			try {
			
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("StoredReport.txt"), "utf-8"));
		    for(int i =0; i<dataStored.size(); i++){
		    	writer.write(dataStored.get(i));
				writer.newLine();
		    }
		} catch (IOException ex) {
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}		
		}
	}

}

