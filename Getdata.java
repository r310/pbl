import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Getdata extends Data{
	public Getdata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}
	
	public void readkeyFile() {
		try {
			File fi = new File(path + "src\\");
			BufferedReader br = new BufferedReader(new FileReader(fi));
			
			tab = Integer.valueOf(br.readLine()).intValue();
			save = br.readLine();
			
			for(int i = 0; i < tab; i++) {
				br.readLine();
				url[i] = br.readLine();
				sub[i] = br.readLine();
				chbox[i] = br.readLine();
			}

			br.close();
			
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void setURL1(String url, String sub) {
		String regex = "(4Dmath-)\\d{1,2}(.pptx)";

		Pattern pa = Pattern.compile(regex);

		try {
			String htm = getSourceText(new URL(url));
			check(pa, htm, url, sub);

		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void setURL2(String ur) {
		
	}
	
	public void readurlFile(String[] fileURL, String subject) {		
		int count = 0;
		String line;
		
		try {
			File re = new File(path + "file\\readurlFile.txt");
			BufferedReader br = new BufferedReader(new FileReader(re));
			
			while((line = br.readLine()) != null) {
				fileURL[count++] = line;
			}
			
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public void comparison() {
		
	}
	
	public void download() {
		
	}
	
	public void saveurlFile() {
		
	}
	
	public void check(Pattern pa, String target, String url, String sub) {
		Matcher match = pa.matcher(target);

		try {
			File wr = new File(path + "file\\websource.txt");
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(wr)));

			while(match.find()) {
				System.out.println(url + match.group());
				String websource = url + match.group();

				pw.println(websource);
/*
				try {
					URL ur = new URL(websource);
					URLConnection conn = ur.openConnection();
					InputStream in = conn.getInputStream();

					String dir = "/home/st12d28/pbl/" + sub + "/";
					File newdir = new File(dir);
					newdir.mkdir();

					String filename = dir + match.group();
					File file = new File(filename);
					FileOutputStream out = new FileOutputStream(file, false);

					int b;
					while((b = in.read()) != -1) {
						out.write(b);
					}

					out.close();
					in.close();

				} catch(IOException e) {
					System.out.println(e);
				}
*/
			}

			pw.close();

		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public String getSourceText(URL url) throws IOException {
		URLConnection conn = url.openConnection();
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String source;
			while((source = bf.readLine()) != null) {
				sb.append(source);
			}

			bf.close();

		} catch(IOException e) {
			System.out.println(e);
		}

		return sb.toString();
	}
}