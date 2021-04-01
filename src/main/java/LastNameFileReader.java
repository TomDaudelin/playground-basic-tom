import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
	Reads Last names from last name text file with the one line per name format
*/
public class LastNameFileReader {
	
	private BufferedReader file_reader;
	
	//Sets up the buffered reader (links last name file in order to read from it)
	public LastNameFileReader(String resource_path) throws IOException {
		String path = System.getProperty("user.dir");
		path = path.concat(resource_path);
		
		this.file_reader = new BufferedReader(new FileReader(path));
		this.file_reader.mark(8192);
	}
	
	//Read a last name from the last name text file
	public String readLastName() throws IOException {
		return this.file_reader.readLine();
	}
	
	//Reset file pointer to allow re-reading the last name file
	public void reset() throws IOException {
		this.file_reader.reset();
	}
	
	//Close the buffered reader after completion of reading file
	public void close() throws IOException {
		this.file_reader.close();
	}
}