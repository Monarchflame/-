package manager.business;

import book.dao.Book;
import database.Model;
import reader.dao.Student;

public interface Interface_manager 
{
	//��ͼ�������
	/*
	 * ��һ���飬�ҵ���������������
	 * @param bookName ����*/
	public Model searchBook(String bookName);
	/*
	 * �������ͼ������*/
	public void outEveryBook();
	/*
	 * ɾ��ͼ��
	 * @param bookNumber ͼ����*/
	public boolean deleteBook(String bookNumber);
	/*
	 * ���ͼ��*/
	public boolean addBook(Book book,int total);
	/*
	 * �޸�ͼ��*/
	public Model alterBook(String oldBookNumber,Book newBook);
	/*
	 * ��ԭ�л����ϸ���ͼ����Ŀ,+-bookChange
	 * @param bookName ����
	 * @param bookChange �������*/
	public boolean asBook(String bookNumber,int bookChange);
	/*
	 * ������
	 * @param bookNumber ͼ����
	 * @param readerNumber ���߱��
	 * */
	public void returnBook(String bookNumber,String readerNumber);
	
	//�Զ��߲���
	/*
	 * ���Ҷ�����Ϣ
	 * @param readerNumber ���߱��
	 * @param readerCategary ��������*/
	public Model searchReader(String readerNumber,String readerCategary);
	/*
	 * ���ɷ���
	 * @param readerNumber ���߱��
	 * @param readerCategary ��������
	 */
	public boolean payFine(String readerNumber,String readerCategary);
}
