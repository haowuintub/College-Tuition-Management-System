import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class ModifySituation extends JPanel implements ActionListener {
	Hashtable 学费基本信息表 = null;
	JTextField 学号, 姓名, 基本学费, 住宿费, 书本费;
	JRadioButton 是, 否;
	ButtonGroup group = null;
	JButton 开始修改, 输入修改, 重置;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	FileOutputStream outOne = null;
	ObjectOutputStream outTwo = null;
	File file = null;

	public ModifySituation(File file) {
		this.file = file;
		学号 = new JTextField(10);
		姓名 = new JTextField(10);
		基本学费 = new JTextField(10);
		住宿费 = new JTextField(10);
		书本费 = new JTextField(10);
		group = new ButtonGroup();
		是 = new JRadioButton("Yes", true);
		否 = new JRadioButton("No", false);
		group.add(是);
		group.add(否);
		开始修改 = new JButton("Start Editing");
		输入修改 = new JButton("Input Modification");
		输入修改.setEnabled(false);
		重置 = new JButton("Reset");
		学号.addActionListener(this);
		开始修改.addActionListener(this);
		输入修改.addActionListener(this);
		重置.addActionListener(this);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the student ID to modify the information:", JLabel.CENTER));
		box1.add(学号);
		box1.add(开始修改);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("(New) Name:", JLabel.CENTER));
		box2.add(姓名);
		/*
		 * Box box3=Box.createHorizontalBox(); box3.add(new
		 * JLabel("(新)性别:",JLabel.CENTER)); box3.add(男); box3.add(女);
		 */
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("(New) Basic tuition:", JLabel.CENTER));
		box4.add(基本学费);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("(New) Accommodation fee:", JLabel.CENTER));
		box5.add(住宿费);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("(New) Cost of books:", JLabel.CENTER));
		box6.add(书本费);
		Box boxH = Box.createVerticalBox();
		boxH.add(box1);
		boxH.add(box2);
		// boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(Box.createVerticalGlue());
		JPanel pCenter = new JPanel();
		pCenter.add(boxH);
		setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);
		JPanel pSouth = new JPanel();
		pSouth.add(输入修改);
		pSouth.add(重置);
		add(pSouth, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == 开始修改 || e.getSource() == 学号) {
			String number = "";
			number = 学号.getText();

			if (number.length() > 0) {
				try {
					inOne = new FileInputStream(file);
					inTwo = new ObjectInputStream(inOne);
					学费基本信息表 = (Hashtable) inTwo.readObject();
					inOne.close();
					inTwo.close();
				} catch (Exception ee) {
				}
				if (学费基本信息表.containsKey(number)) {
					输入修改.setEnabled(true);
					Student stu = (Student) 学费基本信息表.get(number);
					姓名.setText(stu.getName());
					基本学费.setText(stu.getBasictuition());
					住宿费.setText(stu.getLodgingexpenses());
					书本费.setText(stu.getBookexpenses());
					if (stu.getWhetherhandingexpenses().equals("Yes")) {
						是.setSelected(true);
					} else {
						否.setSelected(true);
					}
				} else {
					输入修改.setEnabled(false);

					String warning = "The student number does not exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
					学号.setText(null);
					姓名.setText(null);
					基本学费.setText(null);
					住宿费.setText(null);
					书本费.setText(null);

				}
			} else {
				输入修改.setEnabled(false);

				String warning = "You must enter your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
				学号.setText(null);
				姓名.setText(null);
				基本学费.setText(null);
				住宿费.setText(null);
				书本费.setText(null);
			}
		} else if (e.getSource() == 输入修改) {
			String number = "";
			number = 学号.getText();
			if (number.length() > 0) {
				try {
					inOne = new FileInputStream(file);
					inTwo = new ObjectInputStream(inOne);
					学费基本信息表 = (Hashtable) inTwo.readObject();
					inOne.close();
					inTwo.close();
				} catch (Exception ee) {
				}
				if (学费基本信息表.containsKey(number)) {
					String question = "The basic information of the student already exists, do you want to modify his or her basic tuition information?";

					JOptionPane.showMessageDialog(this, question, "Warning!",
							JOptionPane.QUESTION_MESSAGE);

					String m = "Basic tuition information will be revised!";
					int ok = JOptionPane.showConfirmDialog(this, m, "Confirm",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (ok == JOptionPane.YES_OPTION) {
						String name = 姓名.getText();
						String basictuition = 基本学费.getText();
						String lodgingexpenses = 住宿费.getText();
						String bookexpenses = 书本费.getText();
						String whetherhandingexpenses = null;
						if (是.isSelected()) {
							whetherhandingexpenses = 是.getText();
						} else {
							whetherhandingexpenses = 否.getText();
						}
						Student 学生 = new Student();
						学生.setNumber(number);
						学生.setName(name);
						学生.setBasictuition(basictuition);
						学生.setLodgingexpenses(lodgingexpenses);
						学生.setBookexpenses(bookexpenses);
						学生.setWhetherhandingexpenses(whetherhandingexpenses);
						try {
							outOne = new FileOutputStream(file);
							outTwo = new ObjectOutputStream(outOne);
							学费基本信息表.put(number, 学生);
							outTwo.writeObject(学费基本信息表);
							outTwo.close();
							outOne.close();
							学号.setText(null);
							姓名.setText(null);
							基本学费.setText(null);
							住宿费.setText(null);
							书本费.setText(null);
						} catch (Exception ee) {
							System.out.println(ee);
						}
						输入修改.setEnabled(false);
					} else if (ok == JOptionPane.NO_OPTION) {
						输入修改.setEnabled(true);
					}
				} else {

					String warning = "The student ID has no basic information and cannot be modified!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
					输入修改.setEnabled(false);
				}
			} else {
				String warning = "You must enter your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
				输入修改.setEnabled(false);
			}
		}
		if (e.getSource() == 重置) {
			学号.setText(null);
			姓名.setText(null);
			基本学费.setText(null);
			住宿费.setText(null);
			书本费.setText(null);
		}
	}
}
