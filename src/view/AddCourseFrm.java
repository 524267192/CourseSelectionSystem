package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddCourseFrm extends JInternalFrame {
	private JTextField courseNameTextField;
	private JTextField studentNumTextField;
	private JComboBox teacherListComboBox;
	private JTextArea courseInfoTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourseFrm frame = new AddCourseFrm();
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
	public AddCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u8BFE\u7A0B");
		//bound统一居中为x为1000-width,y为800-height-50
		setBounds(193, 115, 613, 471);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label.setBounds(88, 21, 90, 19);
		label.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseNameTextField = new JTextField();
		courseNameTextField.setBounds(192, 19, 288, 24);
		courseNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setBounds(88, 80, 90, 19);
		label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		teacherListComboBox = new JComboBox();
		teacherListComboBox.setBounds(192, 78, 288, 24);
		
		JLabel label_2 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_2.setBounds(88, 141, 90, 19);
		label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		studentNumTextField = new JTextField();
		studentNumTextField.setBounds(192, 139, 288, 24);
		studentNumTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		label_3.setBounds(88, 202, 90, 19);
		label_3.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u4ECB\u7ECD.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		courseInfoTextArea = new JTextArea();
		courseInfoTextArea.setBounds(192, 201, 288, 120);
		
		JButton addCourseButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		addCourseButton.setBounds(130, 350, 128, 43);
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addCourseAct(ae);
			}
		});
		addCourseButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		addCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton resetButton = new JButton("\u91CD\u7F6E\u4FE1\u606F");
		resetButton.setBounds(333, 349, 133, 44);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		getContentPane().setLayout(null);
		getContentPane().add(label_2);
		getContentPane().add(studentNumTextField);
		getContentPane().add(label_1);
		getContentPane().add(teacherListComboBox);
		getContentPane().add(label);
		getContentPane().add(courseNameTextField);
		getContentPane().add(label_3);
		getContentPane().add(courseInfoTextArea);
		getContentPane().add(addCourseButton);
		getContentPane().add(resetButton);
		setTeacherCombox();
	}

	protected void resetValue(ActionEvent ae) {
		// TODO Auto-generated method stub
		courseNameTextField.setText("");
		courseInfoTextArea.setText("");
		studentNumTextField.setText("");
		teacherListComboBox.setSelectedIndex(0);
	}

	protected void addCourseAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		String couserName = courseNameTextField.getText().toString();
		String courseInfo = courseInfoTextArea.getText().toString();
		Teacher selectedTeacher = (Teacher)teacherListComboBox.getSelectedItem();
		int studentMaxNum = 0;
		try {
			studentMaxNum = Integer.parseInt(studentNumTextField.getText());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "学生人数只能输入数字!");
			return;
		}
		if(StringUtil.isEmpty(couserName)){
			JOptionPane.showMessageDialog(this, "请输入课程名称!");
			return;
		}
		if(studentMaxNum <= 0){
			JOptionPane.showMessageDialog(this, "学生人数只能输入大于0的数字!");
			return;
		}
		Course course = new Course();
		course.setName(couserName);
		course.setMax_student_num(studentMaxNum);
		course.setInfo(courseInfo);
		course.setTeacher_id(selectedTeacher.getId());
		CourseDao courseDao = new CourseDao();
		if(courseDao.addCourse(course)){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		courseDao.closeDao();
		resetValue(ae);
	}
	private void setTeacherCombox(){
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for (Teacher teacher : teacherList) {
			teacherListComboBox.addItem(teacher);
		}
	}
}
