import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Delete extends JPanel implements ActionListener {
	Hashtable 学费基本信息表 = null;
	JTextField 学号, 姓名, 基本学费, 住宿费, 书本费;
	JRadioButton 是, 否;
	JButton 删除;
	ButtonGroup group = null;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	FileOutputStream outOne = null;
	ObjectOutputStream outTwo = null;
	File file = null;

	public Delete(File file) {
		this.file = file;
		学号 = new JTextField(10);
		删除 = new JButton("delete");
		学号.addActionListener(this);
		删除.addActionListener(this);
		姓名 = new JTextField(10);
		姓名.setEditable(false);
		基本学费 = new JTextField(10);
		基本学费.setEditable(false);
		住宿费 = new JTextField(10);
		住宿费.setEditable(false);
		书本费 = new JTextField(10);
		书本费.setEditable(false);
		是 = new JRadioButton("Yes", false);
		否 = new JRadioButton("No", false);
		group = new ButtonGroup();
		group.add(是);
		group.add(否);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the student ID to be deleted:", JLabel.CENTER));
		box1.add(学号);
		box1.add(删除);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("Name:", JLabel.CENTER));
		box2.add(姓名);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("Whether tuition has been paid:", JLabel.CENTER));
		box3.add(是);
		box3.add(否);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("Basic tuition:", JLabel.CENTER));
		box4.add(基本学费);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("Accommodation fee:", JLabel.CENTER));
		box5.add(住宿费);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("Cost of books:", JLabel.CENTER));
		box6.add(书本费);
		Box boxH = Box.createVerticalBox();
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box4);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(Box.createVerticalGlue());
		JPanel pCenter = new JPanel();
		pCenter.add(boxH);
		setLayout(new BorderLayout());
		add(pCenter, BorderLayout.CENTER);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == 删除 || e.getSource() == 学号) {
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
					String m = "Are you sure you want to delete the student ID and all tuition information?";
					int ok = JOptionPane.showConfirmDialog(this, m, "Confirm",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (ok == JOptionPane.YES_OPTION) {
						学费基本信息表.remove(number);
						try {
							outOne = new FileOutputStream(file);
							outTwo = new ObjectOutputStream(outOne);
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
					} else if (ok == JOptionPane.NO_OPTION) {
						学号.setText(null);
						姓名.setText(null);
						基本学费.setText(null);
						住宿费.setText(null);
						书本费.setText(null);
					}
				} else {
					String warning = "The student number does not exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				String warning = "You must enter your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
