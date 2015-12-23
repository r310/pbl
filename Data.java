public class Data {
	protected String[] url;
	protected String[] sub;
	protected String save;
	protected String[] chbox;
	protected int tab;
	protected static String path = "C:\\pbl\\";
	
	public String getPath() {
		return path;
	}
	
	public void setURL(String[] url) {
		this.url = url;
	}
	
	public String[] getURL() {
		return url;
	}
	
	public void setSub(String[] sub) {
		this.sub = sub;
	}
	
	public String[] getSub() {
		return sub;
	}
	
	public void setSave(String save) {
		this.save = save;
	}
	
	public String getSave() {
		return save;
	}
	
	public void setChbox(String[] chbox) {
		this.chbox = chbox;
	}
	
	public String[] getChbox() {
		return chbox;
	}
	
	public void setTab(int tab) {
		this.tab = tab;
	}
	
	public int getTab() {
		return tab;
	}
}