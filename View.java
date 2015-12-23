import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View implements ActionListener {
	private Controller controller;
	private JFrame frame;
	private JPanel[] panel;
	private JLabel[] label;
	private JButton[] button;
	private JTextField[] text;
	private JRadioButton[] radio;
	private ButtonGroup[] group;
	private JTabbedPane tab;
	private JTextField save;
	private int[] tabUsed;

	public View() {
		tabUsed = new int[10];
		for(int i = 0; i < 10; i++) {
			tabUsed[i] = -1;
		}

		text = new JTextField[30];
		for(int i = 0; i < 30; i++) {
			text[i] = new JTextField("", 15);
		}

		radio = new JRadioButton[20];
		for(int i = 0; i < 10; i++) {
			radio[i] = new JRadioButton("Googld Drive");
		}

		for(int i = 10; i < 20; i++) {
			radio[i] = new JRadioButton("Other");
		}

		group = new ButtonGroup[10];
		for(int i = 0; i < 10; i++) {
			group[i] = new ButtonGroup();
			group[i].add(radio[i]);
			group[i].add(radio[i + 10]);
		}

		label = new JLabel[30];
		for(int i = 0; i < 10; i++) {
			label[i] = new JLabel("URL");
		}

		for(int i = 10; i < 20; i++) {
			label[i] = new JLabel("教科名");
		}

		for(int i = 20; i < 30; i++) {
			label[i] = new JLabel("方式");
		}

		button = new JButton[5];
		button[0] = new JButton("ヘルプ");
		button[1] = new JButton("適用");
		button[2] = new JButton("タブを削除");
		button[3] = new JButton("スタート");
		button[4] = new JButton("+");

		button[0].addActionListener(this);
		button[1].addActionListener(this);
		button[2].addActionListener(this);
		button[3].addActionListener(this);
		button[4].addActionListener(this);

		panel = new JPanel[40];
		for(int i = 0; i < 10; i++) {
			panel[i] = new JPanel();
			panel[i].add(label[i]);
			panel[i].add(text[i]);
		}

		for(int i = 10; i < 20; i++) {
			panel[i] = new JPanel();
			panel[i].add(label[i]);
			panel[i].add(text[i]);
		}

		for(int i = 20; i < 30; i++) {
			panel[i] = new JPanel();
			panel[i].add(label[i]);
			panel[i].add(radio[i - 20]);
			panel[i].add(radio[i - 10]);
		}

		for(int i = 30; i < 40; i++) {
			panel[i] = new JPanel();
			panel[i].add(panel[i - 30]);
			panel[i].add(panel[i - 20]);
			panel[i].add(panel[i - 10]);
		}

		tab = new JTabbedPane();
		tab.addTab("tab" + tab.getTabCount(), panel[tab.getTabCount() + 30]);
		tab.setPreferredSize(new Dimension(250, 180));
		tabUsed[0] = tab.getTabCount() - 1;

		JPanel MainPanel = new JPanel();
		MainPanel.add(tab);
		MainPanel.add(button[1]);
		MainPanel.add(button[2]);
		MainPanel.add(button[3]);
		MainPanel.add(button[4]);

		JLabel l = new JLabel("保存先");
		save = new JTextField("", 15);

		MainPanel.add(l);
		MainPanel.add(save);

		frame = new JFrame("title");
		frame.add(MainPanel);
		frame.setBounds(100, 100, 280, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[1]) {
			String[] ur, su, ch;
			String sa;
			int ta, count = 0;

			sa = save.getText();
			ta = tab.getTabCount();

			ur = new String[10];
			su = new String[10];
			ch = new String[10];

			for(int i = 0; i < 10; i++) {
				if(tabUsed[i] != -1) {
					tab.setTitleAt(i, text[i + 10].getText());

					if(tabUsed[i] == count) {
						ur[count] = text[i].getText();
						su[count] = text[i + 10].getText();

						if(radio[i].isSelected()) {
							ch[count] = radio[i].getText();
						} else {
							ch[count] = radio[i + 10].getText();
						}

						count++;
					}
				}
			}

			controller.addSave(ur, su, ch, sa, ta);
		}

		if(e.getSource() == button[2] && tab.getSelectedIndex() != -1) {
			for(int i = 0; i < 10; i++) {
				if(tabUsed[i] == tab.getSelectedIndex()) {
					tabUsed[i] = -1;

					for(int j = 0; j < 10; j++) {
						if(tabUsed[j] >= tab.getSelectedIndex()) {
							tabUsed[j] = tabUsed[j] - 1;
						}
					}

					tab.remove(i);
					break;
				}
			}
		}

		if(e.getSource() == button[3]) {
			controller.addGet();
		}

		if(e.getSource() == button[4] && tab.getTabCount() < 11) {
			for(int i = 0; i < 10; i++) {
				if(tabUsed[i] == -1) {
					tab.addTab("tab" + i, panel[i + 30]);
					tabUsed[i] = tab.getTabCount() - 1;
					break;
				}
			}
		}
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}
}
