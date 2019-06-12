package reader.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import database.ConnectDatabase;
import reader.business.Reader;
import reader.business.ReaderFunction_Interface;

public class Student extends Reader implements ReaderFunction_Interface
{
	public String degree;//ѧλ
	public String college;//ѧԺ
	public String freeze;//����
	public Student(String number,String password,String name,String gender,String degree,String college,String phonenumber,int canBorrowNumber,int borrownumber,String freeze) 
	{
		super(number, password, name, gender, phonenumber, canBorrowNumber, borrownumber);
		this.degree=degree;
		this.college=college;
		this.freeze=freeze;
	}
	@Override
	public void SearchBook(String bookName) 
	{
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public boolean BorrowBook(String bookNumber) 
	{
		ConnectDatabase conData = new ConnectDatabase();
		
		String []paras = {number,bookNumber};
		String sql="select * from studentrecording where ���߱��=? and ͼ����=? and ����ʱ�� is null";
		ResultSet rs = conData.queryExecute(sql, paras);
		try 
		{
			if(rs.next())//����һ��һ�����黹û��
			{
				return false;
			}
			else {
				sql = "INSERT INTO studentrecording(���߱��,ͼ����)  VALUES (?, ?)";	
				boolean ju = conData.cudExecute(sql,paras);
				return ju;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	@Override
	public boolean ReturnBook(String bookNumber) 
	{
		ConnectDatabase condata = new ConnectDatabase();
		String sql = "update studentrecording set ׼������ = 1 where ͼ����=? and ���߱��=? and ����ʱ�� is null";
		String paras[]= {bookNumber,number};
		boolean ju = condata.cudExecute(sql,paras);
		return ju;
	}
	@Override
	public boolean alterPassword(String newPassword) 
	{
		if (!newPassword.equals(""))
		{
			try 
			{
				ConnectDatabase conData = new ConnectDatabase();
				String sql="UPDATE student SET ����=?  where ���߱��=? ";
				String []paras = {newPassword,this.number};
				boolean rs=conData.cudExecute(sql,paras);
				return rs;
			} catch (Exception e) {
				return false;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "������������Ϣ", "����", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	@Override
	public boolean renew(String bookNumber)
	{
		ConnectDatabase conData = new ConnectDatabase();
		String sql="select �Ƿ����� from studentrecording where ���߱��=? and ͼ����=?";
		String []paras = {this.number,bookNumber.trim()};
		ResultSet rs = conData.queryExecute(sql, paras);
		try {
			if (rs.next()) 
			{
				String bol = rs.getString(1);
				if (bol.equals("0")) //û����
				{
					sql="UPDATE studentrecording SET �Ƿ�����=1 where ���߱��=? and ͼ����=? ";//������ʵ������
					boolean rs1 = conData.cudExecute(sql, paras);
					return rs1;
				}
				else {
					JOptionPane.showMessageDialog(null, "ֻ������һ��", "����", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
}
