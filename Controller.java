import java.io.*;

public class Controller {
	private Savedata savedata;
	private Getdata getdata;
	private View view;
	
	public void addSave(String[] url, String[] sub, String[] chbox, String save, int tab) {
		String[] ur, su, ch;
		String sa;
		int ta;
		
		ta = tab;
		sa = save;
		ur = new String[ta];
		su = new String[ta];
		ch = new String[ta];
		
		for(int i = 0; i < ta; i++) {
			ur[i] = url[i];
			su[i] = sub[i];
			ch[i] = chbox[i];
		}
		
		savedata.setURL(ur);
		savedata.setSub(su);
		savedata.setChbox(ch);
		savedata.setSave(save);
		savedata.setTab(tab);
		
		savedata.savekeyFile();
	}
	
	public void addGet() {
		getdata.readkeyFile();

		String[] ur, su, ch, fileURL;
		ur = new String[10];
		su = new String[10];
		ch = new String[10];

		ur = getdata.getURL();
		su = getdata.getSub();
		ch = getdata.getChbox();
		
		
		File dir;
		String dirPath = getdata.getPath();
		int fileCount;

		for(int i = 0; i < getdata.getTab(); i++) {
			
			if(ch[i] == "Other") {
				getdata.setURL1(ur[i], su[i]);
			}
			
			else if(ch[i] == "Google Drive") {
				
			}
			
			
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