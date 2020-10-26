import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Delete extends JPanel implements ActionListener {
	Hashtable ѧ�ѻ�����Ϣ�� = null;
	JTextField ѧ��, ����, ����ѧ��, ס�޷�, �鱾��;
	JRadioButton ��, ��;
	JButton ɾ��;
	ButtonGroup group = null;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	FileOutputStream outOne = null;
	ObjectOutputStream outTwo = null;
	File file = null;

	public Delete(File file) {
		this.file = file;
		ѧ�� = new JTextField(10);
		ɾ�� = new JButton("delete");
		ѧ��.addActionListener(this);
		ɾ��.addActionListener(this);
		���� = new JTextField(10);
		����.setEditable(false);
		����ѧ�� = new JTextField(10);
		����ѧ��.setEditable(false);
		ס�޷� = new JTextField(10);
		ס�޷�.setEditable(false);
		�鱾�� = new JTextField(10);
		�鱾��.setEditable(false);
		�� = new JRadioButton("Yes", false);
		�� = new JRadioButton("No", false);
		group = new ButtonGroup();
		group.add(��);
		group.add(��);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Enter the student ID to be deleted:", JLabel.CENTER));
		box1.add(ѧ��);
		box1.add(ɾ��);
		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("Name:", JLabel.CENTER));
		box2.add(����);
		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("Whether tuition has been paid:", JLabel.CENTER));
		box3.add(��);
		box3.add(��);
		Box box4 = Box.createHorizontalBox();
		box4.add(new JLabel("Basic tuition:", JLabel.CENTER));
		box4.add(����ѧ��);
		Box box5 = Box.createHorizontalBox();
		box5.add(new JLabel("Accommodation fee:", JLabel.CENTER));
		box5.add(ס�޷�);
		Box box6 = Box.createHorizontalBox();
		box6.add(new JLabel("Cost of books:", JLabel.CENTER));
		box6.add(�鱾��);
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
		if (e.getSource() == ɾ�� || e.getSource() == ѧ��) {
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
					String m = "Are you sure you want to delete the student ID and all tuition information?";
					int ok = JOptionPane.showConfirmDialog(this, m, "Confirm",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (ok == JOptionPane.YES_OPTION) {
						ѧ�ѻ�����Ϣ��.remove(number);
						try {
							outOne = new FileOutputStream(file);
							outTwo = new ObjectOutputStream(outOne);
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
					} else if (ok == JOptionPane.NO_OPTION) {
						ѧ��.setText(null);
						����.setText(null);
						����ѧ��.setText(null);
						ס�޷�.setText(null);
						�鱾��.setText(null);
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
