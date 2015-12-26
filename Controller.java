import java.io.*;

public class Controller {
	private Savedata savedata;
	private Getdata getdata;
	private View view;

	//viewに値を渡すメソッド
	public void initialInfo() {
		getdata.readkeyFile();	//readkeyFileに書かれている情報を読み込む

		String[] ur, su, ch;
		String sa;
		int ta;

		ta = getdata.getTab();		//読み込んだタブの数を代入
		sa = getdata.getSave();		//読み込んだ保存先を代入

		ur = new String[10];
		su = new String[10];
		ch = new String[10];

		ur = getdata.getURL();		//読み込んだURLを代入
		su = getdata.getSub();		//読み込んだ教科名を代入
		ch = getdata.getChbox();	//読み込んだ方式を代入

		for(int i = 0; i < ta; i++) {
			view.setURLText(ur[i], i);
			view.setSubText(su[i], i);
			view.setRadioButton(ch[i], i);
		}

		view.setSaveText(sa);
	//	view.setTabTitle(ta);
	}

	//savedataに値を渡すメソッド
	public void addSave(String[] url, String[] sub, String[] chbox, String save, int tab) {
		String[] ur, su, ch;
		String sa;
		int ta;

		ta = tab;		//タブの数を代入
		sa = save;	//保存先を代入
		ur = new String[ta];
		su = new String[ta];
		ch = new String[ta];

		//タブの数だけ代入
		for(int i = 0; i < ta; i++) {
			ur[i] = url[i];		//urlを代入
			su[i] = sub[i];		//教科名を代入
			ch[i] = chbox[i];	//方式を代入
		}

		//savedataにurl,教科名,方式,保存先,タブの数の値を渡す
		savedata.setURL(ur);
		savedata.setSub(su);
		savedata.setChbox(ch);
		savedata.setSave(save);
		savedata.setTab(tab);

		//keyfileに書き込むメソッドを呼び出す
		savedata.savekeyFile();
	}

	//keyfileを読み込み、授業資料を保存するメソッド
	public void addGet() {
		//keyfileを読み込むメソッドを呼び出す
		getdata.readkeyFile();

		String[] ur, su, ch, fileURL;
		ur = new String[10];
		su = new String[10];
		ch = new String[10];

		ur = getdata.getURL();		//keyfileから読み込んだurlの値を代入
		su = getdata.getSub();		//keyfileから読み込んだsubの値を代入
		ch = getdata.getChbox();	//keyfileから読み込んだchの値を代入

		String dirSave = getdata.getSave();		//保存先のディレクトリ名を代入
		File save = new File(dirSave);
		if(!save.exists()) {	//保存先のディレクトリが存在しない場合
			save.mkdirs();			//ディレクトリを生成
		}

		//タブの数だけ繰り返す
		for(int i = 0; i < getdata.getTab(); i++) {
			//方式によって呼び出すメソッドを決める
			switch(ch[i]) {
				case "Other":
					getdata.setURL1(ur[i]);
					break;

				case "Google Drive" :
					System.out.println("setURL2");
					getdata.setURL2(ur[i]);
					break;

				default:
					break;
			}

			String dirSub = getdata.getSave() + su[i] + "/";		//教科名のディレクトリを代入
			System.out.println(dirSub);
			File dirFile = new File(dirSub);	//教科名のディレクトリを指定

			if(dirFile.exists()) {	//教科名のディレクトリが存在する場合
				try {
					System.out.println("存在する");
					File re = new File(dirSub + "readurlFile.txt");	//読み込むファイル名を指定
					BufferedReader br = new BufferedReader(new FileReader(re));
					String line;
					int filecount = 0;

					//書き込まれているurlの数を調べる
					while((line = br.readLine()) != null) {
						filecount++;
					}

					String[] fileurl = new String[filecount];	//書き込まれているurlの数だけの配列を宣言
					getdata.readurlFile(fileurl, su[i]);		//readurlFileを呼び出す

					re = new File(getdata.getPath() + "file/websource.txt");	//読み込むファイル名を指定
					br = new BufferedReader(new FileReader(re));

					while((line = br.readLine()) != null) {
						if(getdata.comparison(line, fileurl)) {	//既に保存されたurlと比較
							getdata.download(line, su[i]);	//まだ保存されていない場合、downloadを呼び出す
							getdata.saveurlFile(line, su[i]);			//まだ保存されていない場合、saveurlFileを呼び出す
						}
					}

				} catch(IOException e) {
					System.out.println(e);	//例外が発生した場合に表示
				}

			} else {	//ディレクトリが存在しない場合
				dirFile.mkdir();		//ディレクトリを生成
				System.out.println("存在しない");

				try {
					File re = new File(getdata.getPath() + "file/websource.txt");
					BufferedReader br = new BufferedReader(new FileReader(re));
					String line;

					while((line = br.readLine()) != null) {
						getdata.download(line, su[i]);	//読み込んだurl先のファイルを保存するメソッドを呼び出す
						getdata.saveurlFile(line, su[i]);	//保存したファイルのurlを書き込むメソッドを呼び出す
					}

					br.close();

				} catch(IOException e) {
					System.out.println(e);
				}
			}

			getdata.clearMap();
		}
	}

	public void setSavedata(Savedata savedata) {
		this.savedata = savedata;
	}

	public Savedata getSavedata() {
		return savedata;
	}

	public void setGetdata(Getdata getdata) {
		this.getdata = getdata;
	}

	public Getdata getGetdata() {
		return getdata;
	}

	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}
}
