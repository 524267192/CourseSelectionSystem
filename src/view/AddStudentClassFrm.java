package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;

import dao.ClassDao;
import model.StudentClass;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddStudentClassFrm extends JInternalFrame {
	private JTextField classNameTextField;
	private JTextArea classInfotextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentClassFrm frame = new AddStudentClassFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddStudentClassFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u73ED\u7EA7\u4FE1\u606F");
		setBounds(208, 164, 583, 371);
		
		JLabel label = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		label.setBounds(73, 38, 90, 19);
		label.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		
		classNameTextField = new JTextField();
		classNameTextField.setBounds(177, 32, 237, 32);
		classNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u73ED\u7EA7\u4FE1\u606F\uFF1A");
		label_1.setBounds(73, 104, 90, 19);
		label_1.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u73ED\u7EA7\u4ECB\u7ECD.png")));
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		
		classInfotextArea = new JTextArea();
		classInfotextArea.setBounds(177, 103, 237, 101);
		
		JButton submitButton = new JButton("\u63D0\u4EA4");
		submitButton.setBounds(168, 248, 90, 41);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					submitClass(ae);
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		submitButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		
		JButton restButton = new JButton("\u91CD\u7F6E");
		restButton.setBounds(314, 248, 96, 41);
		restButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		restButton.setIcon(new ImageIcon(AddStudentClassFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		restButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		getContentPane().setLayout(null);
		getContentPane().add(submitButton);
		getContentPane().add(restButton);
		getContentPane().add(label_1);
		getContentPane().add(classInfotextArea);
		getContentPane().add(label);
		getContentPane().add(classNameTextField);

	}

	protected void submitClass(ActionEvent ae) throws HeadlessException, SQLException {
		// TODO Auto-generated method stub
		String className = classNameTextField.getText().toString();
		String classInfo = classInfotextArea.getText().toString();
		if(StringUtil.isEmpty(className)){
			JOptionPane.showMessageDialog(this, "°à¼¶Ãû³Æ²»ÄÜÎª¿Õ£¡");
			return;
		}
		StudentClass scl = new StudentClass();
		scl.setName(className);
		scl.setInfo(classInfo);
		ClassDao classDao = new ClassDao();
		if(classDao.addClass(scl)){
			JOptionPane.showMessageDialog(this, "°à¼¶Ìí¼Ó³É¹¦£¡");
		}else{
			JOptionPane.showMessageDialog(this, "°à¼¶Ìí¼ÓÊ§°Ü£¡");
		}
		classDao.closeDao();
		resetValue(ae);
	}

	protected void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		classNameTextField.setText("");
		classInfotextArea.setText("");
	}
}
