import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Hashtable;


public class StudentManager extends JFrame implements ActionListener {
	StudentSituation ѧ�ѻ�����Ϣ���� = null;
	ModifySituation ѧ�ѻ�����Ϣ�޸� = null;
	Inquest ѧ�ѻ�����Ϣ��ѯ = null;
	Delete ѧ�ѻ�����Ϣɾ�� = null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem ����, �޸�, ��ѯ, ɾ��;
	Container con = null;
	Hashtable ѧ�ѻ�����Ϣ = null;
	File file = null;
	CardLayout card = null;
	JLabel label = null;
	JPanel pCenter;

	public StudentManager() {
		���� = new JMenuItem("Enter basic student tuition information");
		�޸� = new JMenuItem("Modify the basic information of student tuition");
		��ѯ = new JMenuItem("Query basic information about student tuition");
		ɾ�� = new JMenuItem("Delete basic student tuition information");
		bar = new JMenuBar();
		fileMenu = new JMenu("menu");
		fileMenu.add(����);
		fileMenu.add(�޸�);
		fileMenu.add(��ѯ);
		fileMenu.add(ɾ��);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label = new JLabel("Welcome!", JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.BOLD, 24));
		label.setForeground(Color.red);
		ѧ�ѻ�����Ϣ = new Hashtable();
		����.addActionListener(this);
		�޸�.addActionListener(this);
		��ѯ.addActionListener(this);
		ɾ��.addActionListener(this);
		card = new CardLayout();
		con = getContentPane();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		file = new File("Basic_tuition_information.txt");
		if (!file.exists()) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(ѧ�ѻ�����Ϣ);
				objectOut.close();
				out.close();
			} catch (IOException e) {
			}
		}
		ѧ�ѻ�����Ϣ���� = new StudentSituation(file);
		ѧ�ѻ�����Ϣ�޸� = new ModifySituation(file);
		ѧ�ѻ�����Ϣ��ѯ = new Inquest(this, file);
		ѧ�ѻ�����Ϣɾ�� = new Delete(file);
		pCenter.add("Initial Layer", label);
		pCenter.add("Tuition Input Layer", ѧ�ѻ�����Ϣ����);
		pCenter.add("Tuition Modification Layer", ѧ�ѻ�����Ϣ�޸�);
		pCenter.add("Tuition Deletion Layer", ѧ�ѻ�����Ϣɾ��);
		con.add(pCenter, BorderLayout.CENTER);
		con.validate();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
//		setVisible(true);
		setBounds(200, 100, 300, 270);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ����) {
			card.show(pCenter, "Tuition Input Layer");
		} else if (e.getSource() == �޸�) {
			card.show(pCenter, "Tuition Modification Layer");
		} else if (e.getSource() == ��ѯ) {
			ѧ�ѻ�����Ϣ��ѯ.setVisible(true);
		} else if (e.getSource() == ɾ��) {
			card.show(pCenter, "Tuition Deletion Layer");
		}
	}
}
