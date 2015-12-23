import java.io.*;

public class Savedata extends Data{
	public Savedata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}

	//keyfileに保存するメソッド
	public void savekeyFile() {
		try {
			File fi = new File(path + "file\\keyFile.txt");		//書き込むファイル名を指定
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fi)));

			pw.println(tab);		//タブの数を書き込む
			pw.println(save);		//保存先を書き込む

			//タブの数だけ繰り返す
			for(int i = 0; i < tab; i++) {
				pw.println();	//空白の行を書き込む
				pw.println(url[i]);	//urlを書き込む
				pw.println(sub[i]);	//教科名を書き込む
				pw.println(chbox[i]);	//方式を書き込む
			}

			pw.close();	//ファイルを閉じる

		} catch(IOException e) {
			System.out.println(e);	//例外が発生した場合に表示
		}
	}
}
