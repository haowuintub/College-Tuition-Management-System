import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Login extends JFrame {

	private JPasswordField password;
	private JTextField username;
	private JButton login;
	private JButton reset;

	public static void main(String args[]) {
		new Login();
	}

	private class ResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			username.setText("");
			password.setText("");
		}
	}

	private class LoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			String uname = username.getText().trim();
			String pword = new String(password.getPassword());
			if (uname.equals("123") && pword.equals("123")) {
				StudentManager studentManager = new StudentManager();
				studentManager.setVisible(true);
				Login.this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Only the administrator can log in!");
				username.setText("");
				password.setText("");
			}
		}
	}

	public Login() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("College Tuition Management System");
		setBounds(100, 100, 380, 200);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1);

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(2);
		gridLayout.setVgap(10);
		panel_2.setLayout(gridLayout);
		panel_1.add(panel_2);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("username£º");
		panel_2.add(label_1);
		username = new JTextField(20);
		username.setPreferredSize(new Dimension(0, 0));
		panel_2.add(username);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("password£º");
		panel_2.add(label_2);
		password = new JPasswordField(20);
		password.setEchoChar('*');
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);

		final JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		login = new JButton();
		login.addActionListener(new LoginAction());
		login.setText("Login");
		panel_3.add(login);
		reset = new JButton();
		reset.addActionListener(new ResetAction());
		reset.setText("Reset");
		panel_3.add(reset);

		final JLabel label_3 = new JLabel();
		label_3.setText("College Tuition Management System");
		label_3.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3, BorderLayout.NORTH);

		setVisible(true);
		setResizable(false);
	}
}