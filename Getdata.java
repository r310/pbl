import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.util.HashMap;

public class Getdata extends Data{
	HashMap<String, String> map = new HashMap<String, String>();

	public Getdata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}

	//マップからすべて削除するメソッド
	public void clearMap() {
		map.clear();
	}

	//keyfileを読み込むメソッド
	public void readkeyFile() {
		try {
			File fi = new File(path + "file/keyFile.txt");		//読み込むファイル名を設定
			BufferedReader br = new BufferedReader(new FileReader(fi));

			tab = Integer.valueOf(br.readLine()).intValue();	//タブの数を読み込み、代入
			save = br.readLine();		//保存先を読み込み、代入

			//タブの数だけ繰り返す
			for(int i = 0; i < tab; i++) {
				br.readLine();				//空白の行を読み込む
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
			String htm = getSourceText(new URL(url));	//url先のhtmlの情報を取得し、代入
			Matcher match = pa.matcher(htm);					//マッチャを作成

			try {
				File wr = new File(path + "file/websource.txt");		//書き込むファイルを指定
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(wr)));

				//regexが見つかるたびに書き込む
				while(match.find()) {
					System.out.println(url + match.group());
					String fileurl = url + match.group();		//見つかった文字列を代入
					map.put(fileurl, match.group());

					pw.println(fileurl);	//ファイルのurlを書き込む
				}

				pw.close();

			} catch(IOException e) {
				System.out.println(e);	//例外が発生した場合に表示
			}

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}
	}

	//プログラム設計の場合に使うメソッド
	public void setURL2(String url) {
		String regex = "(https://drive.google.com.file.d.0B-wjPD8Rvpuc).{15}(.view.usp.u003ddrive_web)";	//見つける文字列を指定
		Pattern pa = Pattern.compile(regex);	//パターンオブジェクトを作成

		try {
			String htm = getSourceText(new URL(url));	//url先のhtmlの情報を取得し、代入
			System.out.println(htm);
			Matcher match = pa.matcher(htm);			//マッチャを作成

			try {
				File wr = new File(path + "file/websource.txt");	//書き込むファイルを指定
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(wr)));

				//regexが見つかるたびに書き込む
				while(match.find()) {
					System.out.println(match.group());	//見つかった文字列を表示
					pw.println(match.group());	//ファイルのurlを書き込む
					String key = match.group();
					String key2 = key.substring(45, 60);

				//	System.out.println(key2);

					String regex2 = "\\p{Punct}\\p{Digit}\\p{Punct}\\p{Digit}{2}\\p{Punct}.{3,15}?((.java)|(.pdf))\\p{Punct}{7}(0B-wjPD8Rvpuc)" + key2 + "\\p{Punct}";
					Pattern pa2 = Pattern.compile(regex2);
					Matcher match2 = pa2.matcher(htm);

					System.out.println(key2);

					while(match2.find()) {
					//	System.out.println(match2.group());
						String key3 = match2.group();
						String regex3 = "\\p{Digit}\\p{Punct}\\p{Digit}{2}\\p{Punct}([0-9a-zA-Z]){3,15}((.java)|(.pdf))";
						Pattern pa3 = Pattern.compile(regex3);
						Matcher match3 = pa3.matcher(key3);

						if(match3.find()) {
							System.out.println(match3.group());
						}

						System.out.println("regex4");
						String regex4 = "((.java)|(.pdf))\\p{Punct}{3}(https://lh)\\d.(googleusercontent.com/).{70}\\p{Punct}(,,,,)\\p{Punct}(0B-wjPD8Rvpuc)" + key3 + "\\p{Punct}";
						Pattern pa4 = Pattern.compile(regex4);
						Matcher match4 = pa4.matcher(htm);

						while(match4.find()) {
							System.out.println(match4.group());
						}
					}

					System.out.println();
				}

				pw.close();

			} catch(IOException e) {
				System.out.println(e);
			}

		} catch(IOException e) {
			System.out.println(e);
		}
	}

	//urlFileを読み込むメソッド
	public void readurlFile(String[] fileurl, String sub) {
		try {
			File re = new File(path + sub + "/readurlFile.txt");	//読み込むファイル名を指定
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

	//既に保存されているファイルと比較するメソッド
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

	//ファイルを保存するメソッド
	public void download(String fileurl, String dirsub) {
		try {
			URL ur = new URL(fileurl);	//保存するファイルのurlを指定
			URLConnection conn = ur.openConnection();	//指定したurlに接続
			InputStream in = conn.getInputStream();	//ストリームを生成
			String filename = map.get(fileurl);		//fileurlに対応するマップを代入
			File fi = new File(this.save + dirsub + "/" + filename);	//保存先を指定
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

	//保存したファイルのurlを書き込むメソッド
	public void saveurlFile(String fileurl, String dirsub) {
		try {
			File fi = new File(this.save + dirsub + "/readurlFile.txt");	//書き込むファイル名を設定
			FileWriter fw = new FileWriter(fi, true);

			fw.write(fileurl + "\n");	//保存したファイルのurlを書き込む
			fw.close();

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}
	}

	//url先のhtmlの情報を取得するメソッド
	public String getSourceText(URL url) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("po.cc.ibaraki-ct.ac.jp", 3128));
		HttpURLConnection conn = (HttpURLConnection)url.openConnection(proxy);	//url先に接続
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
