import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Helper {
	public static void printDisplay() throws IOException {
		BufferedReader iStream = new BufferedReader(new FileReader("input.txt"));
		String string = new String();
		for(int i = 0; i < 11; i++) {
			
			string = iStream.readLine();
			if (i == 11) {
				System.out.print(string+" ");
				break;
			}
			System.out.println(string);
		}
		iStream.close();
	}
}
