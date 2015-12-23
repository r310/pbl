import java.io.*;

public class Controller {
	private Savedata savedata;
	private Getdata getdata;
	private View view;

	//savedata�ɒl��n�����\�b�h
	public void addSave(String[] url, String[] sub, String[] chbox, String save, int tab) {
		String[] ur, su, ch;
		String sa;
		int ta;

		ta = tab;		//�^�u�̐�����
		sa = save;	//�ۑ������
		ur = new String[ta];
		su = new String[ta];
		ch = new String[ta];

		//�^�u�̐��������
		for(int i = 0; i < ta; i++) {
			ur[i] = url[i];		//url����
			su[i] = sub[i];		//���Ȗ�����
			ch[i] = chbox[i];	//��������
		}

		//savedata��url,���Ȗ�,����,�ۑ���,�^�u�̐��̒l��n��
		savedata.setURL(ur);
		savedata.setSub(su);
		savedata.setChbox(ch);
		savedata.setSave(save);
		savedata.setTab(tab);

		//keyfile�ɏ������ރ��\�b�h���Ăяo��
		savedata.savekeyFile();
	}

	//keyfile��ǂݍ��݁A���Ǝ�����ۑ����郁�\�b�h
	public void addGet() {
		//keyfile��ǂݍ��ރ��\�b�h���Ăяo��
		getdata.readkeyFile();

		String[] ur, su, ch, fileURL;
		ur = new String[10];
		su = new String[10];
		ch = new String[10];

		ur = getdata.getURL();		//keyfile����ǂݍ���url�̒l����
		su = getdata.getSub();		//keyfile����ǂݍ���sub�̒l����
		ch = getdata.getChbox();	//keyfile����ǂݍ���ch�̒l����

		String dirSave = getdata.getPath() + getdata.getSave();		//�ۑ���̃f�B���N�g��������
		File save = new File(dirSave);
		if(!save.exists()) {	//�ۑ���̃f�B���N�g�������݂��Ȃ��ꍇ
			save.mkdirs();			//�f�B���N�g���𐶐�
		}

		//�^�u�̐������J��Ԃ�
		for(int i = 0; i < getdata.getTab(); i++) {
			//�����ɂ���ČĂяo�����\�b�h�����߂�
			switch(ch[i]) {
				case "Other":
					getdata.setURL1(ur[i]);
					break;

				case "Google Drive" :
					getdata.setURL2(ur[i]);
					break;

				default:
					break;
			}

			File dirFile = new File(getdata.getPath() + "file\\" + getdata.getSave() + "\\" + su[i] + "\\");	//���Ȗ��̃f�B���N�g�����w��
			if(dirFile.exists()) {	//���Ȗ��̃f�B���N�g�������݂���ꍇ
				try {
					File re = new File(getdata.getPath() + "file\\" + getdata.getSave() + "\\" + su[i] + "\\readurlFile.txt");	//�ǂݍ��ރt�@�C�������w��
					BufferedReader br = new BufferedReader(new FileReader(re));
					String line;
					int filecount = 0;

					//�������܂�Ă���url�̐��𒲂ׂ�
					while((line = br.readLine()) != null) {
						filecount++;
					}

					String[] fileurl = new String[filecount];	//�������܂�Ă���url�̐������̔z���錾
					getdata.readurlFile(fileurl, su[i]);			//readurlFile���Ăяo��

					re = new File(getdata.getPath() + "file\\websource.txt");	//�ǂݍ��ރt�@�C�������w��
					br = new BufferedReader(new FileReader(re));

					while((line = br.readLine()) != null) {
						if(comparison(line, fileurl)) {	//���ɕۑ����ꂽurl�Ɣ�r
							getdata.download(line)				//�܂��ۑ�����Ă��Ȃ��ꍇ�Adownload���Ăяo��
							getdata.saveurlFile(line)			//�܂��ۑ�����Ă��Ȃ��ꍇ�AsaveurlFile���Ăяo��
						}
					}

				} catch(IOException e) {
					System.out.println(e);	//��O�����������ꍇ�ɕ\��
				}
			} else {	//�f�B���N�g�������݂��Ȃ��ꍇ
				dirFile.mkdir();		//�f�B���N�g���𐶐�

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
