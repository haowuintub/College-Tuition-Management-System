import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class ModifySituation extends JPanel implements ActionListener {
	Hashtable ѧ�ѻ�����Ϣ�� = null;
	JTextField ѧ��, ����, ����ѧ��, ס�޷�, �鱾��;
	JRadioButton ��, ��;
	ButtonGroup group = null;
	JButton ��ʼ�޸�, �����޸�, ����;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	FileOutputStream outOne = null;
	ObjectOutputStream outTwo = null;
	File file = null;

	public ModifySituation(File file) {
		this.file = file;
		ѧ�� = new JTextField(10);
		���� = new JTextField(10);
		����ѧ�� = new JTextField(10);
		ס�޷� = new JTextField(10);
		�鱾�� = new JTextField(10);
		group = new ButtonGroup();
		�� = new JRadioButton("Yes", true);
		�� = new JRadioButton("No", false);
		group.add(��);
		group.add(��);
		��ʼ�޸� = new JButton("Start Editing");
		�����޸� = new JButton("Input Modification");
		�����޸�.setEnabled(false);
		���� = new JButton("Reset");
		ѧ��.addActionListener(this);
		��ʼ�޸�.addActionListener(this);
		�����޸�.addActionListener(this);
		����.addActionListener(this);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the student ID to modify the information:", JLabel.CENTER));
		box1.add(ѧ��);
		box1.add(��ʼ�޸�);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("(New) Name:", JLabel.CENTER));
		box2.add(����);
		/*
		 * Box box3=Box.createHorizontalBox(); box3.add(new
		 * JLabel("(��)�Ա�:",JLabel.CENTER)); box3.add(��); box3.add(Ů);
		 */
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("(New) Basic tuition:", JLabel.CENTER));
		box4.add(����ѧ��);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("(New) Accommodation fee:", JLabel.CENTER));
		box5.add(ס�޷�);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("(New) Cost of books:", JLabel.CENTER));
		box6.add(�鱾��);
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
		pSouth.add(�����޸�);
		pSouth.add(����);
		add(pSouth, BorderLayout.SOUTH);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ��ʼ�޸� || e.getSource() == ѧ��) {
			String number = "";
			number = ѧ��.getText();

			if (number.length() > 0) {
				try {
					inOne = new FileInputStream(file);
					inTwo = new ObjectInputStream(inOne);
					ѧ�ѻ�����Ϣ�� = (Hashtable) inTwo.readObject();
					inOne.close();
					inTwo.close();
				} catch (Exception ee) {
				}
				if (ѧ�ѻ�����Ϣ��.containsKey(number)) {
					�����޸�.setEnabled(true);
					Student stu = (Student) ѧ�ѻ�����Ϣ��.get(number);
					����.setText(stu.getName());
					����ѧ��.setText(stu.getBasictuition());
					ס�޷�.setText(stu.getLodgingexpenses());
					�鱾��.setText(stu.getBookexpenses());
					if (stu.getWhetherhandingexpenses().equals("Yes")) {
						��.setSelected(true);
					} else {
						��.setSelected(true);
					}
				} else {
					�����޸�.setEnabled(false);

					String warning = "The student number does not exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
					ѧ��.setText(null);
					����.setText(null);
					����ѧ��.setText(null);
					ס�޷�.setText(null);
					�鱾��.setText(null);

				}
			} else {
				�����޸�.setEnabled(false);

				String warning = "You must enter your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
				ѧ��.setText(null);
				����.setText(null);
				����ѧ��.setText(null);
				ס�޷�.setText(null);
				�鱾��.setText(null);
			}
		} else if (e.getSource() == �����޸�) {
			String number = "";
			number = ѧ��.getText();
			if (number.length() > 0) {
				try {
					inOne = new FileInputStream(file);
					inTwo = new ObjectInputStream(inOne);
					ѧ�ѻ�����Ϣ�� = (Hashtable) inTwo.readObject();
					inOne.close();
					inTwo.close();
				} catch (Exception ee) {
				}
				if (ѧ�ѻ�����Ϣ��.containsKey(number)) {
					String question = "The basic information of the student already exists, do you want to modify his or her basic tuition information?";

					JOptionPane.showMessageDialog(this, question, "Warning!",
							JOptionPane.QUESTION_MESSAGE);

					String m = "Basic tuition information will be revised!";
					int ok = JOptionPane.showConfirmDialog(this, m, "Confirm",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (ok == JOptionPane.YES_OPTION) {
						String name = ����.getText();
						String basictuition = ����ѧ��.getText();
						String lodgingexpenses = ס�޷�.getText();
						String bookexpenses = �鱾��.getText();
						String whetherhandingexpenses = null;
						if (��.isSelected()) {
							whetherhandingexpenses = ��.getText();
						} else {
							whetherhandingexpenses = ��.getText();
						}
						Student ѧ�� = new Student();
						ѧ��.setNumber(number);
						ѧ��.setName(name);
						ѧ��.setBasictuition(basictuition);
						ѧ��.setLodgingexpenses(lodgingexpenses);
						ѧ��.setBookexpenses(bookexpenses);
						ѧ��.setWhetherhandingexpenses(whetherhandingexpenses);
						try {
							outOne = new FileOutputStream(file);
							outTwo = new ObjectOutputStream(outOne);
							ѧ�ѻ�����Ϣ��.put(number, ѧ��);
							outTwo.writeObject(ѧ�ѻ�����Ϣ��);
							outTwo.close();
							outOne.close();
							ѧ��.setText(null);
							����.setText(null);
							����ѧ��.setText(null);
							ס�޷�.setText(null);
							�鱾��.setText(null);
						} catch (Exception ee) {
							System.out.println(ee);
						}
						�����޸�.setEnabled(false);
					} else if (ok == JOptionPane.NO_OPTION) {
						�����޸�.setEnabled(true);
					}
				} else {

					String warning = "The student ID has no basic information and cannot be modified!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
					�����޸�.setEnabled(false);
				}
			} else {
				String warning = "You must enter your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
				�����޸�.setEnabled(false);
			}
		}
		if (e.getSource() == ����) {
			ѧ��.setText(null);
			����.setText(null);
			����ѧ��.setText(null);
			ס�޷�.setText(null);
			�鱾��.setText(null);
		}
	}
}
