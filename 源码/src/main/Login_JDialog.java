package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import manager.dao.Manager;
import reader.dao.Student;
import reader.dao.Teacher;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login_JDialog extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private JTextField number_textField;
	private JPasswordField passwordField;
	private main mainFrame;

	/**
	 * Create the dialog.
	 * @param string 
	 */
	public Login_JDialog(main main, String readersCategory) 
	{
		setTitle("\u767B\u5F55");
		this.mainFrame=main;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		number_textField = new JTextField();//"201700800370"
		number_textField.setColumns(10);
		number_textField.setBounds(165, 44, 213, 21);
		contentPanel.add(number_textField);
		
		JLabel label = new JLabel("\u5E10\u53F7:");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(60, 44, 38, 21);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(60, 95, 50, 21);
		contentPanel.add(label_1);
		
		passwordField = new JPasswordField();
		//passwordField.setText("123456");
		passwordField.setBounds(165, 95, 213, 21);
		contentPanel.add(passwordField);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (readersCategory.equals("manager")) //����Ա��¼
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*��֤*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from manager where ����Ա���=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);
						try {
							if(!rs.next())//��
							{
								JOptionPane.showMessageDialog(null, "�˺Ų�����", "����", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//�ڶ�������-password
								if((datapassword.trim()).equals(getpassword))//ƥ��ɹ�
								{
									mainFrame.manager=new Manager(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5)) ;
									mainFrame.managerInfo_button.setIcon(new ImageIcon("personinfo.png"));
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "�������", "����", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
					}
				}
				
				else if (readersCategory.equals("student"))//ѧ����¼
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*��֤*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from student where ���߱��=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);//���ʺ�
						try {
							if(!rs.next())//��
							{
								JOptionPane.showMessageDialog(null, "�˺Ų�����", "����", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//�ڶ�������-password
								if((datapassword.trim()).equals(getpassword))//ƥ��ɹ�
								{
									mainFrame.studentInfo_label.setText(getnumber+" "+rs.getString(3));
									mainFrame.studentInfo_button.setIcon(new ImageIcon("personinfo.png"));
									
									mainFrame.student=new Student(rs.getString("���߱��"), rs.getString("����"), rs.getString("����"), rs.getString("�Ա�"), rs.getString("ѧλ"), rs.getString("ѧԺ"), rs.getString("��ϵ�绰"), 
											Integer.parseInt(rs.getString("�ɽ�����")), Integer.parseInt(rs.getString("������")),rs.getString("�Ƿ񶳽�"));
									mainFrame.teacher=null;
									mainFrame.teacherInfo_label.setText("");
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "�������", "����", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
					}
				}
				else if (readersCategory.equals("teacher"))//��ʦ��¼
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*��֤*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from teacher where ���߱��=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);//���ʺ�
						try {
							if(!rs.next())//��
							{
								JOptionPane.showMessageDialog(null, "�˺Ų�����", "����", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//�ڶ�������-password
								if((datapassword.trim()).equals(getpassword))//ƥ��ɹ�
								{
									mainFrame.teacherInfo_label.setText(getnumber+" "+rs.getString(3));
									mainFrame.teacherInfo_button.setIcon(new ImageIcon("personinfo.png"));
									mainFrame.teacher=new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
											Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(8)), rs.getString(11));
									mainFrame.studentInfo_label.setText("");
									mainFrame.student=null;
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "�������", "����", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
					}
				}
			}
			/*private void manager() 
			{
				Manager_JFrame manager_JFrame = new Manager_JFrame(main);
				manager_JFrame.setVisible(true);
			}*/
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
