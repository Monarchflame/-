package reader.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import main.main;
import reader.ui.AlterPassword_JDialog;
import reader.dao.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReaderInfo_JFrame extends JFrame 
{
	private JPanel contentPane;
	public JTextArea broBook_textArea;
	JLabel number_label;
	String readersCategory;
	String fine="0";
	/**
	 * Create the frame.
	 */
	public ReaderInfo_JFrame(main main) 
	{
		setTitle("\u4E2A\u4EBA\u8D44\u6599");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 514, 376);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 15));
		label_1.setBounds(177, 10, 55, 20);
		contentPane.add(label_1);
		
		number_label = new JLabel();
		number_label.setBounds(278, 10, 150, 20);
		contentPane.add(number_label);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 15));
		label.setBounds(177, 55, 55, 20);
		contentPane.add(label);
		
		JLabel name_label = new JLabel();
		name_label.setBounds(278, 55, 150, 20);
		contentPane.add(name_label);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("����", Font.PLAIN, 15));
		label_3.setBounds(177, 100, 55, 20);
		contentPane.add(label_3);
		
		JLabel gender_label = new JLabel();
		gender_label.setBounds(278, 100, 150, 20);
		contentPane.add(gender_label);
		
		JLabel label_4 = new JLabel("\u53EF\u501F\u9605\u4E66\u6570\uFF1A");
		label_4.setFont(new Font("����", Font.PLAIN, 15));
		label_4.setBounds(177, 145, 91, 20);
		contentPane.add(label_4);
		
		JLabel borNumber_label = new JLabel();
		borNumber_label.setBounds(278, 145, 150, 20);
		contentPane.add(borNumber_label);
		
		JLabel label_6 = new JLabel("\u501F\u9605\u4E66\u76EE\uFF1A");
		label_6.setFont(new Font("����", Font.PLAIN, 15));
		label_6.setBounds(177, 190, 91, 20);
		contentPane.add(label_6);
		
		broBook_textArea = new JTextArea();
		broBook_textArea.setForeground(Color.BLACK);
		JScrollPane scrl = new JScrollPane(broBook_textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scrl.setSize(212, 131);
		scrl.setLocation(278, 198);
		broBook_textArea.setBounds(298, 205, 212, 76);
		broBook_textArea.setEditable(false);
		setBorrowedBook();//�����Ŀ�ͷ���
		
		JLabel fine_label = new JLabel("");
		fine_label.setFont(new Font("����", Font.PLAIN, 17));
		fine_label.setForeground(Color.red);
		fine_label.setBounds(20, 234, 248, 34);
		contentPane.add(fine_label);
		
		JLabel avatar_label = new JLabel("");
		avatar_label.setBounds(10, 10, 130, 130);
		avatar_label.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(avatar_label);
		
		if (main.student!=null && main.teacher==null) 
		{
			readersCategory="student";
			number_label.setText(main.student.number);
			name_label.setText(main.student.name);
			gender_label.setText(main.student.gender);
			borNumber_label.setText(main.student.canBorrowNumber+"");
			ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
			if (main.student.freeze.equals("1")) {
				fine_label.setText("����"+fine+"Ԫ����δ���ɣ�");
			}
		}
		else if (main.student==null && main.teacher!=null) 
		{
			readersCategory="teacher";
			number_label.setText(main.teacher.number);
			name_label.setText(main.teacher.name);
			gender_label.setText(main.teacher.gender);
			borNumber_label.setText(main.teacher.canBorrowNumber+"");
			ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
		}
		
		JButton changeAvatar_button = new JButton("\u4FEE\u6539\u5934\u50CF");
		changeAvatar_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //�޸�ͷ��
			{
				//��ʼ���ļ�ѡ���
				JFileChooser fDialog = new JFileChooser();
				//����ֻѡ�ļ�
				fDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//�����ļ�ѡ���ı���
				fDialog.setDialogTitle("��ѡ����Ҫ�򿪵��ļ�");
				//����ѡ���
				int returnVal = fDialog.showOpenDialog(null);
				// �����ѡ�����ļ�
				if(JFileChooser.APPROVE_OPTION == returnVal)
				{
					String filepath = fDialog.getSelectedFile().getAbsolutePath();
					try
					{
						/*��ͷ����뵽���ݿ���*/
						ImageIO.setAvatarToDatabase(number_label.getText(),filepath,avatar_label,null,readersCategory);
						/*��ȡͷ�����õ����*/
						ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
					}
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(fDialog, this, "�ļ�ѡȡʧ��", returnVal);
						e.printStackTrace();
					}
				}
			}
		});
		changeAvatar_button.setFont(new Font("����", Font.PLAIN, 17));
		changeAvatar_button.setBounds(20, 150, 106, 34);
		contentPane.add(changeAvatar_button);
		contentPane.add(scrl);
		
		JButton alterInfo_button = new JButton("\u4FEE\u6539\u5BC6\u7801");
		alterInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				alterInfo();
			}
		});
		alterInfo_button.setFont(new Font("����", Font.PLAIN, 17));
		alterInfo_button.setBounds(20, 190, 106, 34);
		contentPane.add(alterInfo_button);
		
		JButton logout_button = new JButton("\u6CE8\u9500");
		logout_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				logout(main);
			}
		});
		logout_button.setFont(new Font("����", Font.PLAIN, 20));
		logout_button.setBounds(30, 283, 97, 46);
		contentPane.add(logout_button);
	}
	/*
	 * ��ʾ�ѽ��ĵ���Ŀ�ͷ���
	 * */
	private void setBorrowedBook() 
	{//Ϊʲô���岻�ܸ�
		ConnectDatabase condata=new ConnectDatabase();
		String sql="";
		String []paras = new String[1];
		if (main.student!=null && main.teacher==null) 
		{
			sql="select book.ͼ������ from student ,studentrecording,book where student.���߱��=studentrecording.���߱�� and book.ͼ����=studentrecording.ͼ���� and ����ʱ�� is null and student.���߱��=?";
			paras[0]=main.student.number;
		}
		else if (main.student==null && main.teacher!=null) 
		{
			sql="select book.ͼ������ from teacher ,teacherrecording,book where teacher.���߱��=teacherrecording.���߱�� and book.ͼ����=teacherrecording.ͼ���� and ����ʱ�� is null and teacher.���߱��=?";
			paras[0]=main.teacher.number;
		}
		ResultSet rs=condata.queryExecute(sql,paras);
		
		try {
			while(rs.next())
			{
				String string = rs.getString(1);
				broBook_textArea.append(string.trim()+"\n");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (main.student!=null && main.teacher==null) 
		{
			sql="select ���� from studentfine WHERE ���߱��=? and �Ƿ�ɷ� = 0";
		}
		else if (main.student==null && main.teacher!=null) 
		{
			sql="select ���� from teacherfine WHERE ���߱��=? and �Ƿ�ɷ� = 0";
		}
		rs=condata.queryExecute(sql,paras);
		try {
			while(rs.next())
			{
				fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void alterInfo()
	{
		AlterPassword_JDialog inReader_JDialog =new AlterPassword_JDialog(main.student,main.teacher,this);
		inReader_JDialog.setVisible(true);
	}
	public void logout(main main)
	{
		main.student=null;
		main.teacher=null;
		main.studentInfo_label.setText("");
		main.teacherInfo_label.setText("");
		main.studentInfo_button.setIcon(new ImageIcon("login.png"));
		dispose();
	}
}
