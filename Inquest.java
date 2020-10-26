import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Inquest extends JDialog implements ActionListener {
	Hashtable ѧ�ѻ�����Ϣ�� = null;
	JTextField ѧ��, ����, ����ѧ��, ס�޷�, �鱾��;
	JRadioButton ��, ��;
	JButton ��ѯ;
	ButtonGroup group = null;
	FileInputStream inOne = null;
	ObjectInputStream inTwo = null;
	File file = null;

	public Inquest(JFrame f, File file) {
		super(f, "Query box", false);
		this.file = file;
		ѧ�� = new JTextField(10);
		��ѯ = new JButton("Inquire");
		ѧ��.addActionListener(this);
		��ѯ.addActionListener(this);
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
		box1.add(new JLabel("Input the student ID to be queried:", JLabel.CENTER));
		box1.add(ѧ��);
		box1.add(��ѯ);
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
		Container con = getContentPane();
		con.add(pCenter, BorderLayout.CENTER);
		con.validate();
		setVisible(false);
		setBounds(200, 100, 600, 270);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		����.setText(null);
		����ѧ��.setText(null);
		ס�޷�.setText(null);
		�鱾��.setText(null);

		if (e.getSource() == ��ѯ || e.getSource() == ѧ��) {
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

				} else {
					String warning = "The student number does not exist!";
					JOptionPane.showMessageDialog(this, warning, "Warning!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				String warning = "You must input your student ID!";
				JOptionPane.showMessageDialog(this, warning, "Warning!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
