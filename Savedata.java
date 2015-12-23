import java.io.*;

public class Savedata extends Data{
	public Savedata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}

	public void savekeyFile() {
		try {
			File fi = new File(path + "file\\keyFile.txt");
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fi)));

			pw.println(tab);
			pw.println(save);

			for(int i = 0; i < tab; i++) {
				pw.println();
				pw.println(url[i]);
				pw.println(sub[i]);
				pw.println(chbox[i]);
			}

			pw.close();

		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
