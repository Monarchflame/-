package manager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import book.dao.Book;
import database.ConnectDatabase;
import database.JtableUpdate;
import database.Model;
import main.main;
import manager.business.Interface_manager;
import manager.ui.AddBook_JDialog;
import manager.ui.AlterBook_JDialog;
import manager.ui.BookInfo_JFrame;
import manager.ui.ReaderInfo_JFrame;
import reader.dao.Student;

public class Manager implements Interface_manager
{
	public String number;//������
	public String name;//����
	public String gender;
	public String phonenumber;//�绰
	private ConnectDatabase condata=new ConnectDatabase();
	public Manager(String number,String name,String gender,String phonenumber) 
	{
		this.number=number;
		this.name=name;
		this.gender=gender;
		this.phonenumber=phonenumber;
	}
	@Override
	public Model searchBook(String bookName)
	{
		String sql="select * from book where ͼ������=?";
		String []paras= {bookName};
		if(bookName.equals(""))
		{
			sql="select * from book";
			paras = null;
		}
		return JtableUpdate.jtableUpdate_query(null, sql, paras);
	}

	@Override
	public void outEveryBook() 
	{
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public boolean deleteBook(String bookNumber) 
	{
		try 
		{
			String sql="delete from book where ͼ����=?";
			String []paras = {bookNumber};
			return condata.cudExecute(sql,paras);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addBook(Book book,int total) 
	{
		if (!book.bookNumber.equals("") && !book.bookName.equals("") && !book.category.equals("") && !book.author.equals("") && !book.publishingHouse.equals("") && !book.publicationDate.equals("") && total!=0)
		{
			try 
			{
				String sql="insert into book values (?,?,?,?,?,?,?,?)";
				String []paras = {book.bookNumber,book.bookName,book.category,book.author,book.publishingHouse,book.publicationDate,total+"",total+""};
				boolean rs=condata.cudExecute(sql,paras);
				return rs;
			} catch (Exception e) {
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	@Override
	public Model alterBook(String oldBookNumber,Book newBook)
	{
		if (oldBookNumber==null && newBook==null)//ֱ�ӵ����ť
		{
			String sql="select * from book";
			String []paras = null;
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		else if (oldBookNumber==null && newBook!=null)
		{
			String sql="select * from book where ͼ����=?";
			String []paras = {newBook.bookNumber};
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		else if (!newBook.bookNumber.equals("") && !newBook.bookName.equals("") && !newBook.category.equals("") && !newBook.author.equals("") && !newBook.publishingHouse.equals("") && !newBook.publicationDate.equals(""))
		{
			try 
			{
				newBook.category=newBook.category.split(" ")[1];
				String sql="UPDATE book SET ͼ����=? ,ͼ������=? ,���=? ,����=? ,������=? ,��������=? where ͼ����=? ";
				String []paras = {newBook.bookNumber,newBook.bookName,newBook.category,newBook.author,newBook.publishingHouse,newBook.publicationDate,oldBookNumber};
				boolean rs=condata.cudExecute(sql,paras);
				if (rs) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//sqlΪupdate������ֱ��jtableUpdate
					sql = "select * from book";
					return JtableUpdate.jtableUpdate_query(null, sql, null);
				}
				else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else
		{
			String sql="select * from book where ͼ����=?";
			String []paras = {newBook.bookNumber};
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		return null;
	}
	@Override
	public boolean asBook(String bookNumber, int bookChange) 
	{
		String sql="UPDATE book set �ڲ���=�ڲ���+"+bookChange+""+",����=����+"+bookChange+""+" where ͼ����=?";
		String []paras= {bookNumber};
		boolean ju = condata.cudExecute(sql,paras);
		return ju;
	}
	@Override
	public void returnBook(String bookNumber, String readerNumber) 
	{
		String sql1="UPDATE studentrecording set ����ʱ�� = GETDATE() WHERE ���߱��=? and ͼ����=? and ����ʱ�� is null";
		String []paras= {readerNumber,bookNumber};
		boolean ju1 = condata.cudExecute(sql1,paras);
		String sql2="UPDATE teacherrecording set ����ʱ�� = GETDATE() WHERE ���߱��=? and ͼ����=? and ����ʱ�� is null";
		boolean ju2 = condata.cudExecute(sql2,paras);
		if (ju1) 
		{
			String sql = "select ����,���� from studentfine WHERE ���߱��=? and ͼ����=? and �Ƿ�ɷ� = 0";
			ResultSet rs = condata.queryExecute(sql, paras);
			try {
				if (rs.next()) //����
				{
					String overDate = rs.getString(1);
					String fine = rs.getString(2);
					JOptionPane.showMessageDialog(null, "���鳬��"+overDate+"��,�뽻��"+fine+"Ԫ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					if (main.student!=null) {
						main.student.freeze="1";
					}
					else if (main.teacher!=null) {
						main.teacher.freeze="1";
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		else if (ju2) 
		{
			String sql = "select ����,���� from teacherfine WHERE ���߱��=? and ͼ����=? and �Ƿ�ɷ� = 0";
			ResultSet rs = condata.queryExecute(sql, paras);
			try {
				if (rs.next()) //����
				{
					String overDate = rs.getString(1);
					String fine = rs.getString(2);
					JOptionPane.showMessageDialog(null, "���鳬��"+overDate+"��,�뽻��"+fine+"Ԫ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					if (main.student!=null) {
						main.student.freeze="1";
					}
					else if (main.teacher!=null) {
						main.teacher.freeze="1";
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null, "����ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
	}
	@Override
	public Model searchReader(String readerNumber,String readerCategary) 
	{
		String sql=null;
		if (readerCategary.equals("student")) 
		{
			sql="select ���߱��,����,����,�Ա�,��ϵ�绰,�ɽ�����,������ from student where ���߱��=?";
		}
		else if (readerCategary.equals("teacher")) {
			sql="select ���߱��,����,����,�Ա�,��ϵ�绰,�ɽ�����,������ from teacher where ���߱��=?";
		}
		String []paras= {readerNumber};
		return JtableUpdate.jtableUpdate_query(null, sql, paras);		
	}
	@Override
	public boolean payFine(String readerNumber,String readerCategary) 
	{
		ConnectDatabase condata=new ConnectDatabase();
		String sql=null;
		String []paras = {readerNumber};
		String fine = "0";
		if (readerCategary.equals("student")) 
		{
			sql="select ���� from studentfine where ���߱��=? and �Ƿ�ɷ�=0";
			ResultSet rs=condata.queryExecute(sql,paras);
			try {
				while(rs.next())
				{
					fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (!fine.equals("0")) {
				int n = JOptionPane.showConfirmDialog(null, "���ɷ���"+fine+"Ԫ", "��ʾ",JOptionPane.YES_NO_OPTION);
				if (n==0) {
					sql="update studentfine set �Ƿ�ɷ�=1 where ���߱��=? and �Ƿ�ɷ�=0";
					return condata.cudExecute(sql, paras);
				}
				else
					return false;
			}
			else 
				return false;	
		}
		else if (readerCategary.equals("teacher")) 
		{
			sql="select ���� from teacherfine where ���߱��=? and �Ƿ�ɷ�=0";
			ResultSet rs=condata.queryExecute(sql,paras);
			try {
				while(rs.next())
				{
					fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (!fine.equals("0")) {
				int n = JOptionPane.showConfirmDialog(null, "���ɷ���"+fine+"Ԫ", "��ʾ",JOptionPane.YES_NO_OPTION);
				if (n==0) {
					sql="update teacherfine set �Ƿ�ɷ�=1 where ���߱��=? and �Ƿ�ɷ�=0";
					return condata.cudExecute(sql, paras);
				}
				else
					return false;
			}
			else 
				return false;	
		}
		else 
			return false;	
	}
}
