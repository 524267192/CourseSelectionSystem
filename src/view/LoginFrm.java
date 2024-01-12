package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;
import model.UserType;
import util.StringUtil;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	private JComboBox userTypeComboBox;

	/**
	 * Launch the application.
	 */
	//有个界面显示不出来应该是因为主界面字体调大了
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public LoginFrm() {
		setTitle("\u767B\u9646\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		
		//使窗口居中显示
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize =kit.getScreenSize();
//		setBounds(0, 0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(123, 104, 238));
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		//标签中log图片
		JLabel label = new JLabel("\u5B66\u751F\u9009\u4FEE\u8BFE\u7A0B\u7CFB\u7EDF");
		label.setForeground(new Color(0, 0, 0));
		label.setBounds(162, 38, 791, 200);
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u767B\u5F55logo.png")));
		label.setFont(new Font("宋体", Font.PLAIN, 60));
		
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setBounds(256, 266, 115, 28);
		label_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u7528\u6237\u540D.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		userNameTextField.setBackground(new Color(255, 255, 255));
		userNameTextField.setBounds(387, 264, 255, 39);
		userNameTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setBounds(256, 335, 115, 28);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5BC6\u7801.png")));
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		passwordTextField.setBackground(new Color(255, 255, 255));
		passwordTextField.setBounds(388, 331, 255, 39);
		passwordTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setBounds(233, 403, 136, 29);
		label_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/userType.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		userTypeComboBox = new JComboBox();
		userTypeComboBox.setBackground(new Color(255, 255, 255));
		userTypeComboBox.setBounds(387, 403, 255, 39);
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		userTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		//添加鼠标响应，回车就相当于点击登录
		JButton loginButton = new JButton("\u767B\u5F55");
		//匿名内部类KeyAdapter
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ae) {
				if (ae.getKeyChar() == ae.VK_ENTER) {
//					System.out.println("ok................");
					loginAct(ae);
				}
			}
		});
		loginButton.setToolTipText("");
		loginButton.setBounds(256, 521, 131, 50);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAct(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u767B\u5F55.png")));
		loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setBounds(492, 521, 131, 50);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				restValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		contentPane.setLayout(null);
		contentPane.add(label_1);
		contentPane.add(label_2);
		contentPane.add(userNameTextField);
		contentPane.add(passwordTextField);
		contentPane.add(label_3);
		contentPane.add(userTypeComboBox);
		contentPane.add(label);
		contentPane.add(loginButton);
		contentPane.add(resetButton);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/background1.png")));
		label_4.setBounds(1, 1, 979, 751);
		contentPane.add(label_4);
	}
	
	public void loginActionCon(){
		String userName = userNameTextField.getText().toString();
		String password = passwordTextField.getText().toString();
		UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(this, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(this, "密码不能为空！");
			return;
		}
		Admin admin = null;
		if("系统管理员".equals(selectedItem.getName())){
			AdminDao adminDao = new AdminDao();
			Admin adminTmp = new Admin();
			adminTmp.setName(userName);
			adminTmp.setPassword(password);
			admin = adminDao.login(adminTmp);
			adminDao.closeDao();
			if(admin == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+admin.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, admin).setVisible(true);
		}else if("教师".equals(selectedItem.getName())){
			//教师登录
			Teacher teacher = null;
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			teacherTmp.setName(userName);
			teacherTmp.setPassword(password);
			teacher = teacherDao.login(teacherTmp);
			teacherDao.closeDao();
			if(teacher == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+teacher.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, teacher).setVisible(true);
		}else{
			//学生登录
			Student student = null; 
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			studentTmp.setName(userName);
			studentTmp.setPassword(password);
			student = studentDao.login(studentTmp);
			studentDao.closeDao();
			if(student == null){
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
				return;
			}
			JOptionPane.showMessageDialog(this, "欢迎【"+selectedItem.getName()+"】："+student.getName()+"登录本系统！");
			this.dispose();
			new MainFrm(selectedItem, student).setVisible(true);
		}
	}
	
	//通过鼠标响应登录
	protected void loginAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		loginActionCon();
	}
		
	//通过回车响应登录
	protected void loginAct(KeyEvent ae) {
		// TODO Auto-generated method stub
		loginActionCon();	
	}

	protected void restValue(ActionEvent ae) {
		// TODO Auto-generated method stub
		userNameTextField.setText("");
		passwordTextField.setText("");
		userTypeComboBox.setSelectedIndex(0);
	}
}
