import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Hashtable;


public class StudentManager extends JFrame implements ActionListener {
	StudentSituation 学费基本信息输入 = null;
	ModifySituation 学费基本信息修改 = null;
	Inquest 学费基本信息查询 = null;
	Delete 学费基本信息删除 = null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem 输入, 修改, 查询, 删除;
	Container con = null;
	Hashtable 学费基本信息 = null;
	File file = null;
	CardLayout card = null;
	JLabel label = null;
	JPanel pCenter;

	public StudentManager() {
		输入 = new JMenuItem("Enter basic student tuition information");
		修改 = new JMenuItem("Modify the basic information of student tuition");
		查询 = new JMenuItem("Query basic information about student tuition");
		删除 = new JMenuItem("Delete basic student tuition information");
		bar = new JMenuBar();
		fileMenu = new JMenu("menu");
		fileMenu.add(输入);
		fileMenu.add(修改);
		fileMenu.add(查询);
		fileMenu.add(删除);
		bar.add(fileMenu);
		setJMenuBar(bar);
		label = new JLabel("Welcome!", JLabel.CENTER);
		label.setFont(new Font("TimesRoman", Font.BOLD, 24));
		label.setForeground(Color.red);
		学费基本信息 = new Hashtable();
		输入.addActionListener(this);
		修改.addActionListener(this);
		查询.addActionListener(this);
		删除.addActionListener(this);
		card = new CardLayout();
		con = getContentPane();
		pCenter = new JPanel();
		pCenter.setLayout(card);
		file = new File("Basic_tuition_information.txt");
		if (!file.exists()) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(学费基本信息);
				objectOut.close();
				out.close();
			} catch (IOException e) {
			}
		}
		学费基本信息输入 = new StudentSituation(file);
		学费基本信息修改 = new ModifySituation(file);
		学费基本信息查询 = new Inquest(this, file);
		学费基本信息删除 = new Delete(file);
		pCenter.add("Initial Layer", label);
		pCenter.add("Tuition Input Layer", 学费基本信息输入);
		pCenter.add("Tuition Modification Layer", 学费基本信息修改);
		pCenter.add("Tuition Deletion Layer", 学费基本信息删除);
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
		if (e.getSource() == 输入) {
			card.show(pCenter, "Tuition Input Layer");
		} else if (e.getSource() == 修改) {
			card.show(pCenter, "Tuition Modification Layer");
		} else if (e.getSource() == 查询) {
			学费基本信息查询.setVisible(true);
		} else if (e.getSource() == 删除) {
			card.show(pCenter, "Tuition Deletion Layer");
		}
	}
}
