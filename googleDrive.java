import java.io.*;
import java.net.*;

class googleDrive {
	public static void main(String[] args) {
		String html = "";
		
		try {
			URL url = new URL("https://drive.google.com/a/gm.ibaraki-ct.ac.jp/folderview?id=0B-wjPD8RvpucSWgzRjBuQU5naVU&usp=sharing#grid");
			URLConnection conn = url.openConnection();
			BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while((line = bf.readLine()) != null) {
				html += line + "\n";
			}
			
			bf.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		System.out.println(html);
	}
}