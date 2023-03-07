
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BufferReader {
	
	public static String wordsReader(String filename) {
		try {
			String text = Files.readString(Paths.get(filename));
			return text.toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
