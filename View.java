import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;
import javax.swing.text.*;

class View implements ActionListener{
//GUIの作成
	private Controller controller;

	private JFrame frame,helpf;
	private JLabel save;
	private JLabel[] URL,subject,method,savepoint;
	private JRadioButton[] drive,other;
	private ButtonGroup[] group;
	private JPanel savePanel;
	private	JPanel[] URLPanel,subPanel,metPanel,MainPanel;
	private JTextField saveText;
	private JTextField[] URLText,subText;
	private JButton help,apply,start,plus,delete;
	private JTabbedPane tab;
	private JTextArea helpText;
	private File file;
	private FileReader filereader;
	private int[] taborder;
	private JTextArea result, filename;
	private JPanel color;
	private JScrollPane scroll;

	public void setSaveText(String save) {
		saveText.setText(save);
	}

	public void setURLText(String url, int i) {
		URLText[i].setText(url);
	}

	public void setSubText(String sub, int i) {
		subText[i].setText(sub);
	}

	public void setRadioButton(String chb, int i) {
		switch(chb) {
			case "Google Drive":
				drive[i].setSelected(true);
				break;

			case "Other":
				other[i].setSelected(true);
				break;

			default:
				break;
		}
	}

	public void setDisp(int ta) {
		for(int i = 0; i < ta; i++) {
			tab.addTab(subText[i].getText(), MainPanel[i]);
			taborder[i] = i;
		}
	}

	public View(){
		//MainFrameの作成
		frame = new JFrame("PBL");
		frame.setSize(600, 400);
//		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//HelpFrameの作成
		helpf= new JFrame("Help");
		helpf.setSize(600, 600);
//		helpf.setBounds(300, 300, 600, 600);
		helpText = new JTextArea();

		Container cont = frame.getContentPane();
		cont.setLayout(null);

		try{
			file = new File("");
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			StringBuilder sb = new StringBuilder();
			String text;
			//ここできない
			while((text = br.readLine())!=null){
				sb.append(text);
			}
			br.close();
			text = sb.toString();
			helpText.setText(text);
		}catch(FileNotFoundException a){
  		//	System.out.println(a);
		}catch(IOException a){
			System.out.println(a);
		}


		//labelの作成
		save    = new JLabel("保存先                 ");
		URL     = new JLabel[10];
		subject = new JLabel[10];
		method  = new JLabel[10];
		for(int i=0;i<10;i++){
			URL[i]     = new JLabel("                     URL                      ");
			subject[i] = new JLabel("                     教科名                 ");
			method[i]  = new JLabel("方式");
		}

		//TextFieldの作成
		saveText = new JTextField("",28);
		URLText  = new JTextField[10];
		subText  = new JTextField[10];
		for(int i=0;i<10;i++){
			URLText[i] = new JTextField("",28);
			subText[i] = new JTextField("",28);
		}

		//RadioButtonの作成
		drive = new JRadioButton[10];
		other = new JRadioButton[10];
		for(int i=0;i<10;i++){
			drive[i] = new JRadioButton("Google Drive");
			other[i] = new JRadioButton("Other");
		}

		//driveとotherを含むButtonGroupを作成
		group = new ButtonGroup[10];
		for(int i=0;i<10;i++){
			group[i] = new ButtonGroup();
			group[i].add(drive[i]);
			group[i].add(other[i]);
		}

		//buttonを作成する
		help   = new JButton("ヘルプ");
		apply  = new JButton("適用");
		start  = new JButton("スタート");
		plus = new JButton("+");
		delete = new JButton("タブを削除");
		plus.setFont(new Font("メイリオ", Font.PLAIN, 10));

		//アクションを設定
		help.addActionListener(this);
		apply.addActionListener(this);
		start.addActionListener(this);
		plus.addActionListener(this);
		delete.addActionListener(this);

		//各パーツをパネル化する
		savePanel = new JPanel();
		savePanel.add(save);
		savePanel.add(saveText);
		savePanel.add(apply);
		savePanel.add(start);
		savePanel.add(help);
		savePanel.add(plus);
		URLPanel  = new JPanel[10];
		subPanel  = new JPanel[10];
		metPanel  = new JPanel[10];

		for(int i=0;i<10;i++){
			//URLのLabelとTextFieldを結合
			URLPanel[i] = new JPanel();
			URLPanel[i].setLayout(new FlowLayout());
			URLPanel[i].add(URL[i]);
			URLPanel[i].add(URLText[i]);
			//subjectのLabelとTextFieldを結合
			subPanel[i] = new JPanel();
			subPanel[i].setLayout(new FlowLayout());
			subPanel[i].add(subject[i]);
			subPanel[i].add(subText[i]);
			//methodのLabelとButtonFieldを結合
			metPanel[i] = new JPanel();
			metPanel[i].setLayout(new FlowLayout());
	//		metPanel[i].add(method[i]);
			metPanel[i].add(drive[i]);
			metPanel[i].add(other[i]);
		}

		//Tabを作成
			tab = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);

		//MainPanel[i]を作成
		MainPanel = new JPanel[10];
		for(int i=0;i<10;i++){
			MainPanel[i] = new JPanel();
			MainPanel[i].setLayout(new BoxLayout(MainPanel[i],BoxLayout.Y_AXIS));
			//MainPanel[i]にパーツを配置
			MainPanel[i].add(URLPanel[i]);
			MainPanel[i].add(subPanel[i]);
			MainPanel[i].add(metPanel[i]);
		}

		//TabにMainPanelをくっつける
	//	for(int i=0;i<10;i++){
	//		tab.addTab(null/*"教科"+ (i+1)*/,MainPanel[i]);
	//	}

		taborder = new int[10];
		for(int i = 0; i < 10; i++) {
			taborder[i] = -1;
		}
	//	tab.addTab("新しいタブ",MainPanel[0]);
	//	taborder[0] = 0;

		//Frameに配置
//		frame.setLayout(new BorderLayout());
		//Tabを配置
//		frame.add(tab,BorderLayout.CENTER);
		//SavePanelを配置
//		frame.add(savePanel,BorderLayout.SOUTH);

		//HelpPageの作成
//		helpf.add(helpText,BorderLayout.CENTER);

		color = new JPanel();
		color.setBackground(new Color(238, 238, 238));

		result = new JTextArea();
		scroll = new JScrollPane(result);

		cont.add(method[0]);
		cont.add(delete);
		cont.add(help);
		cont.add(plus);
		cont.add(color);
		cont.add(tab);
		cont.add(start);
		cont.add(apply);
		cont.add(savePanel);
		cont.add(scroll);

		method[0].setBounds(68, 128, 75, 22);
		delete.setBounds(475, 160, 120, 20);
		help.setBounds(520, 0, 75, 22);
		plus.setBounds(480, 0, 45, 22);
		tab.setBounds(0, 0, 480, 180);
		color.setBounds(475, 22, 500, 170);
		start.setBounds(485, 230, 100, 20);
		apply.setBounds(485, 200, 100, 20);
		savePanel.setBounds(58, 195, 430, 30);
		scroll.setBounds(22, 275, 550, 80);
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


		//中央に表示
    	frame.setLocationRelativeTo(null);
		//可視性を与える
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void actionPerformed(ActionEvent e){
		String sa; //saveText
		String[] ur,su,ch; //URLText,subText,checkbox
		int ta; //tab

		ta = 10;
		ur = new String[10];
		su = new String[10];
		ch = new String[10];

		if(e.getSource() == delete) {
			int index = tab.getSelectedIndex();
			if(index != -1) {

				for(int i = 0; i < 10; i++) {
					if(taborder[i] == index) {
						taborder[i] = -1;
						tab.remove(i);
						break;
					}
				}
			}

			for(int i = 0; i < 10; i++) {
				if(taborder[i]  > index) {
					taborder[i]--;
				}
			}

			System.out.println("order");
			for(int i = 0; i < 10; i++) {
				System.out.println(i + "  :  " + taborder[i]);
			}
		}

		if(e.getSource() == plus) {
			int tabcount = tab.getTabCount();
			for(int i = 0; i < 10; i++) {
				if(taborder[i] == -1) {
					tab.addTab("新しいタブ(" + i + ")",MainPanel[i]);
					taborder[i] = tabcount;
					break;
				}

				if(i == 9) {
					result.append("これ以上タブは増やせません\n");
				}
			}

			System.out.println("order");
			for(int i = 0; i < 10; i++) {
				System.out.println(i + "  :  " + taborder[i]);
			}
		}

		//適用ボタンが押された場合
		if(e.getSource() == apply){
			//保存先を格納
			sa = saveText.getText();
			ta = tab.getTabCount();
			//各タブのURLと教科名と方式を格納
			for(int i=0;i<ta;i++){
				ur[i] = URLText[i].getText();
				su[i] = subText[i].getText();
				//タブのタイトルを教科名に変更
				tab.setTitleAt(i,su[i]);
				//押されているボタンによって保存する文字列を決定
				if(drive[i].isSelected()){
					ch[i] = drive[i].getText();
				} else {
					ch[i] = other[i].getText();
				}
			}
			//ユーザが入力した値をコントローラーに渡す
			controller.addSave(ur, su, ch, sa, ta);
		}

		//ヘルプボタンが押された場合
		if(e.getSource() == help){
			helpf.setVisible(true);
		}

		//スタートボタンが押された場合
		if(e.getSource() == start){
			controller.addGet();
		}
	}

	public void addResult(String st) {
		result.append(st + "\n");
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}
}
