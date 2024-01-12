package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class ManageCourseFrm extends JInternalFrame {
	private JTextField searchCourseNameTextField;
	private JTable courseListTable;
	private JTextField editCourseTextField;
	private JTextField editCourseStudentNumTextField;
	private JComboBox editCourseTeachComboBox;
	private JTextArea editCourseInfoTextArea;
	private List<Teacher> teacherList = new ArrayList<Teacher>();
	private JComboBox searchTeacherComboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCourseFrm frame = new ManageCourseFrm();
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
	public ManageCourseFrm() {
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u7BA1\u7406");
		setBounds(90, 0, 819, 704);
		setClosable(true);
		setIconifiable(true);
		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label.setBounds(81, 42, 90, 19);
		label.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchCourseNameTextField = new JTextField();
		searchCourseNameTextField.setBounds(189, 40, 131, 24);
		searchCourseNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setBounds(352, 42, 90, 19);
		label_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		searchTeacherComboBox = new JComboBox();
		searchTeacherComboBox.setBounds(456, 40, 122, 24);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.setBounds(628, 38, 81, 27);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchCourse(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 106, 628, 266);
		
		JPanel panel = new JPanel();
		panel.setBounds(81, 407, 628, 228);
		panel.setBorder(new TitledBorder(null, "\u7F16\u8F91\u8BFE\u7A0B\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label_2.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseTextField = new JTextField();
		editCourseTextField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_3.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseTeachComboBox = new JComboBox();
		
		JLabel label_4 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_4.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseStudentNumTextField = new JTextField();
		editCourseStudentNumTextField.setColumns(10);
		
		JLabel label_5 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		label_5.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4ECB\u7ECD.png")));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		editCourseInfoTextArea = new JTextArea();
		
		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editCourseSubmit(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton deleteCourseButton = new JButton("\u5220\u9664\u8BFE\u7A0B");
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteCourse(ae);
			}
		});
		deleteCourseButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u5220\u9664.png")));
		deleteCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseStudentNumTextField))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(editCourseInfoTextArea))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(154)
							.addComponent(submitEditButton)
							.addGap(54)
							.addComponent(deleteCourseButton)))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(editCourseStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(editCourseInfoTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(deleteCourseButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(submitEditButton))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		courseListTable = new JTable();
		courseListTable.setBackground(new Color(255, 255, 224));
		courseListTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, new Color(255, 255, 224)));
		courseListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				selectedCourse(me);
			}
		});
		courseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u7F16\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u8001\u5E08", "\u8BFE\u7A0B\u6700\u5927\u4EBA\u6570", "\u5DF2\u9009\u4EBA\u6570", "\u8BFE\u7A0B\u4ECB\u7ECD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		courseListTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		courseListTable.getColumnModel().getColumn(5).setPreferredWidth(225);
		scrollPane.setViewportView(courseListTable);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().add(scrollPane);
		getContentPane().add(label);
		getContentPane().add(searchCourseNameTextField);
		getContentPane().add(label_1);
		getContentPane().add(searchTeacherComboBox);
		getContentPane().add(searchButton);
		setTeacherCombox();
		setCourseListTable(new Course());
	}
	protected void editCourseSubmit(ActionEvent ae) {
		// TODO Auto-generated method stub
		int row = courseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		Teacher teacher = (Teacher) editCourseTeachComboBox.getSelectedItem();
		String courseName = editCourseTextField.getText().toString();
		if(StringUtil.isEmpty(courseName)){
			JOptionPane.showMessageDialog(this, "课程名称不能为空！");
			return;
		}
		int max_student_num = 0;
		try {
			max_student_num = Integer.parseInt(editCourseStudentNumTextField.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		if(max_student_num <= 0){
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		String courseInfo = editCourseInfoTextArea.getText().toString();
		Course course = new Course();
		course.setId(course_id);
		course.setName(courseName);
		course.setTeacher_id(teacher.getId());
		course.setMax_student_num(max_student_num);
		course.setInfo(courseInfo);
		CourseDao courseDao = new CourseDao();
		if(courseDao.update(course)){
			JOptionPane.showMessageDialog(this, "修改成功！");
		}else{
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		courseDao.closeDao();
		setCourseListTable(new Course());
		
	}

	protected void selectedCourse(MouseEvent me) {
		// TODO Auto-generated method stub
		int row = courseListTable.getSelectedRow();
		String couseName = courseListTable.getValueAt(row, 1).toString();
		int teacher_id = getTeacherIdByName(courseListTable.getValueAt(row, 2).toString());
		int max_student_num = Integer.parseInt(courseListTable.getValueAt(row, 3).toString());
		String couseInfo = courseListTable.getValueAt(row, 5).toString();
		editCourseTextField.setText(couseName);
		editCourseStudentNumTextField.setText(max_student_num+"");
		editCourseInfoTextArea.setText(couseInfo);
		for(int i=0;i<editCourseTeachComboBox.getItemCount();i++){
			Teacher t = (Teacher) editCourseTeachComboBox.getItemAt(i);
			if(t.getId() == teacher_id){
				editCourseTeachComboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	protected void searchCourse(ActionEvent ae) {
		// TODO Auto-generated method stub
		String searchCourseName = searchCourseNameTextField.getText().toString();
		Teacher teacher = (Teacher) searchTeacherComboBox.getSelectedItem();
		Course course = new Course();
		course.setName(searchCourseName);
		course.setTeacher_id(teacher.getId());
		setCourseListTable(course);
	}

	protected void deleteCourse(ActionEvent ae) {
		// TODO Auto-generated method stub
		int row = courseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		CourseDao courseDao = new CourseDao();
		if(courseDao.delete(course_id)){
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		courseDao.closeDao();
		setCourseListTable(new Course());
	}

	private void setCourseListTable(Course course){
		CourseDao courseDao = new CourseDao();
		List<Course> courseList = courseDao.getCourseList(course);
		DefaultTableModel dft = (DefaultTableModel) courseListTable.getModel();
		dft.setRowCount(0);
		for (Course c : courseList) {
			Vector v = new Vector();
			v.add(c.getId());
			v.add(c.getName());
			v.add(getTeacherNameById(c.getTeacher_id()));
			v.add(c.getMax_student_num());
			v.add(c.getSelected_num());
			v.add(c.getInfo());
			dft.addRow(v);
		}
		courseDao.closeDao();
	}
	private void setTeacherCombox(){
		TeacherDao teacherDao = new TeacherDao();
		teacherList = teacherDao.getTeacherList(new Teacher());
		teacherDao.closeDao();
		for (Teacher teacher : teacherList) {
			editCourseTeachComboBox.addItem(teacher);
			searchTeacherComboBox.addItem(teacher);
		}
	}
	private String getTeacherNameById(int teacher_id){
		String retString = "";
		for (Teacher teacher : teacherList) {
			if(teacher.getId() == teacher_id){
				retString = teacher.getName();
				break;
			}
		}
		return retString;
	}
	private int getTeacherIdByName(String teacher_name){
		int retId = -1;
		for (Teacher teacher : teacherList) {
			if(teacher_name.equals(teacher.getName())){
				retId = teacher.getId();
				break;
			}
		}
		return retId;
	}
}
