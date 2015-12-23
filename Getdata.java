import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Getdata extends Data{
	public Getdata() {
		url = new String[10];
		sub = new String[10];
		chbox = new String[10];
	}

	//keyfile��ǂݍ��ރ��\�b�h
	public void readkeyFile() {
		try {
			File fi = new File(path + "\\file\\keyfile.txt");		//�ǂݍ��ރt�@�C������ݒ�
			BufferedReader br = new BufferedReader(new FileReader(fi));

			tab = Integer.valueOf(br.readLine()).intValue();	//�^�u�̐���ǂݍ��݁A���
			save = br.readLine();		//�ۑ����ǂݍ��݁A���

			//�^�u�̐������J��Ԃ�
			for(int i = 0; i < tab; i++) {
				br.readLine();						//�󔒂̍s��ǂݍ���
				url[i] = br.readLine();		//url��ǂݍ��݁A���
				sub[i] = br.readLine();		//���Ȗ���ǂݍ��݁A���
				chbox[i] = br.readLine();	//������ǂݍ��݁A���
			}

			br.close();	//�t�@�C�������

		} catch(IOException e) {
			System.out.println(e);		//��O�����������ꍇ�ɕ\��
		}
	}

	//���U���w�̏ꍇ�Ɏg�����\�b�h
	public void setURL1(String url) {
		String regex = "(4Dmath-)\\d{1,2}(.pptx)";	//�����镶������w��
		Pattern pa = Pattern.compile(regex);				//�p�^�[���I�u�W�F�N�g���쐬

		try {
			String htm = getSourceText(new URL(url));	//url���html�̏����퓗���A���
			Matcher match = pa.matcher(htm);					//�}�b�`�����쐬

			try {
				File wr = new File(path + "file\\websource.txt");		//�������ރt�@�C�����w��
				PrintWriter pw = new PrintWriter(wr);

				//regex�������邽�тɏ�������
				while(match.find()) {
					System.out.println(url + match.group());
					System fileurl = url + match.group();		//�����������������

					pw.println(fileurl);	//�t�@�C����url�����������
				}

			} catch(IOException e) {
				System.out.println(e);	//��O�����������ꍇ�ɕ\��
			}

		} catch(IOException e) {
			System.out.println(e);	//��O�����������ꍇ�ɕ\��
		}
	}

	//�v���O�����݌v�̏ꍇ�Ɏg�����\�b�h
	public void setURL2(String ur) {

	}

	//urlFile��ǂݍ��ރ��\�b�h
	public void readurlFile(String[] fileurl, String sub) {
		try {
			File re = new File(path + "file\\" + sub + "\\readurlFile.txt");	//�ǂݍ��ރt�@�C�������w��
			BufferedReader br = new BufferedReader(new FileReader(re));
			int count = 0;
			String line;

			while((line = br.readLine()) != null) {
				fileurl[count++] = line;	//url����s���ǂݍ���
			}

		} catch(IOException e) {
			System.out.println(e);		//��O�����������ꍇ�ɕ\��
		}
	}

	public boolean comparison(String url1, String[] url2) {
			boolean judge = true;
			for(int i = 0; i < url2.length; i++) {
				if(url1.equals(url2[i])) {	//���ɕۑ�����Ă��邩��r
					judge = false;
					break;
				}
			}

			return judge;	//���ʂ�n��
	}

	public void download(String fileurl, String filename) {
		try {
			URL ur = new URL(fileurl);	//�ۑ�����t�@�C����url���w��
			URLConnection conn = ur.openConnection();	//�w�肵��url�ɐڑ�
			InputStream in = conn.getInputStream();	//�X�g���[���𐶐�

			File fi = new File(getdata.getSave() + filename);	//�ۑ�����w��
			fi.mkdir();	//�ۑ���𐶐�

			FileOutputStream out = new FileOutputStream(fi, false);

			int b;
			while((b = in.read()) != -1) {
				out.write(b);		//�ۑ�
			}

			out.close();
			in.close();

		} catch(IOException e) {
			System.out.println(e);	//��O�����������ꍇ�ɕ\��
		}
	}

	public void saveurlFile(String fileurl, String dir) {
		try {
			File fi = new File(dir + "readurlFile.txt");	//�ǂݍ��ރt�@�C������ݒ�
			FileWriter fw = new FileWriter(file, true);

			fw.write(fileurl);	//�ۑ������t�@�C����url����������
			fw.close();

		} catch(IOException e) {
			System.out.println(e);	//��O�����������ꍇ�ɕ\��
		}
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

	//url���html�̏����擾���郁�\�b�h
	public String getSourceText(URL url) throws IOException {
		URLConnection conn = url.openConnection();	//url��ɐڑ�
		StringBuilder sb = new StringBuilder();			//�ϒ��̕������錾

		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));		//��s���ǂݍ��ރX�g���[��

			String source;
			//�Ō�܂œǂݍ���
			while((source = bf.readLine()) != null) {
				sb.append(source);	//�ǂݍ��񂾕������ǉ�
			}

			bf.close();		//�X�g���[�������

		} catch(IOException e) {
			System.out.println(e);	//��O�����������ꍇ�ɕ\��
		}

		return sb.toString();	//url���html�̏���n��
	}
}
