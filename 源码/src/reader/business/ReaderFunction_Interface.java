package reader.business;

import reader.dao.Student;

public interface ReaderFunction_Interface 
{
	/*
	 * ��һ���飬�ҵ���������������
	 * @param bookName ����*/
	public void SearchBook(String bookName);
	/*
	 * ����
	 * @param bookNumber ͼ����*/
	public boolean BorrowBook(String bookNumber);
	/*
	 * ����
	 * @param bookNumber ͼ����*/
	public boolean ReturnBook(String bookNumber);
	/*
	 * �޸Ķ�������
	 * @param newPassword ������*/
	public boolean alterPassword(String newPassword);
	/*
	 * ����
	 *  bookNumber ͼ����
	 */
	public boolean renew(String bookNumber) ;
}
