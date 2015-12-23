import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Getdata extends Data{
	public Getdata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}

	//keyfileを読み込むメソッド
	public void readkeyFile() {
		try {
			File fi = new File(path + "\\file\\keyfile.txt");		//読み込むファイル名を設定
			BufferedReader br = new BufferedReader(new FileReader(fi));

			tab = Integer.valueOf(br.readLine()).intValue();	//タブの数を読み込み、代入
			save = br.readLine();		//保存先を読み込み、代入

			//タブの数だけ繰り返す
			for(int i = 0; i < tab; i++) {
				br.readLine();						//空白の行を読み込む
				url[i] = br.readLine();		//urlを読み込み、代入
				sub[i] = br.readLine();		//教科名を読み込み、代入
				chbox[i] = br.readLine();	//方式を読み込み、代入
			}

			br.close();	//ファイルを閉じる

		} catch(IOException e) {
			System.out.println(e);		//例外が発生した場合に表示
		}
	}

	//離散数学の場合に使うメソッド
	public void setURL1(String url) {
		String regex = "(4Dmath-)\\d{1,2}(.pptx)";	//見つける文字列を指定
		Pattern pa = Pattern.compile(regex);				//パターンオブジェクトを作成

		try {
			String htm = getSourceText(new URL(url));	//url先のhtmlの情報を種痘し、代入
			Matcher match = pa.matcher(htm);					//マッチャを作成

			try {
				File wr = new File(path + "file\\websource.txt");		//書き込むファイルを指定
				PrintWriter pw = new PrintWriter(new BufferedReader new FileWriter(wr)));

				//regexが見つかるたびに書き込む
				while(match.find()) {
					System.out.println(url + match.group());
					System fileurl = url + match.group();		//見つかった文字列を代入

					pw.println(fileurl);	//ファイルのurl先を書き込む
				}

			} catch(IOException e) {
				System.out.println(e);	//例外が発生した場合に表示
			}

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}
	}

	//プログラム設計の場合に使うメソッド
	public void setURL2(String ur) {

	}

	//urlFileを読み込むメソッド
	public void readurlFile(String[] fileurl, String sub) {
		try {
			File re = new File(path + "file\\" + sub + "\\readurlFile.txt");	//読み込むファイル名を指定
			BufferedReader br = new BufferedReader(new FileReader(re));
			int count = 0;
			String line;

			while((line = br.readLine()) != null) {
				fileurl[count++] = line;	//urlを一行ずつ読み込む
			}

		} catch(IOException e) {
			System.out.println(e);		//例外が発生した場合に表示
		}
	}

	public boolean comparison(String url1, String[] url2) {
			boolean judge = true;
			for(int i = 0; i < url2.length; i++) {
				if(url1.equals(url2[i])) {	//既に保存されているか比較
					judge = false;
					break;
				}
			}

			return judge;	//結果を渡す
	}

	public void download(String fileurl, String filename, String dir) {
		try {
			URL ur = new URL(fileurl);	//保存するファイルのurlを指定
			URLConnection conn = ur.openConnection();	//指定したurlに接続
			InputStream in = conn.getInputStream();	//ストリームを生成

			File fi = new File(dir + filename);	//保存先を指定
			fi.mkdir();	//保存先を生成

			FileOutputStream out = new FileOutputStream(fi, false);

			int b;
			while((b = in.read()) != -1) {
				out.write(b);		//保存
			}

			out.close();
			in.close();

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}
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

			}

			pw.close();

		} catch(IOException e) {
			System.out.println(e);
		}
	}

	//url先のhtmlの情報を取得するメソッド
	public String getSourceText(URL url) throws IOException {
		URLConnection conn = url.openConnection();	//url先に接続
		StringBuilder sb = new StringBuilder();			//可変長の文字列を宣言

		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));		//一行ずつ読み込むストリーム

			String source;
			//最後まで読み込む
			while((source = bf.readLine()) != null) {
				sb.append(source);	//読み込んだ文字列を追加
			}

			bf.close();		//ストリームを閉じる

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}

		return sb.toString();	//url先のhtmlの情報を渡す
	}
}
