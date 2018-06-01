import java.io.BufferedReader;
import java.io.FileReader;

public class downloadableFilesTab {

	String[][] downloadableFiles = new String[x()][3];

	public int x() {
		int taille = 0;
		try {
			FileReader inf = new FileReader("./filesFile.txt");
			BufferedReader buffin = new BufferedReader(inf);
			String checklineFiles = (buffin.readLine());
			String[] chainFiles = checklineFiles.split(";");
			inf.close();
			taille = chainFiles.length / 2;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return taille;

	}
}