package book.dao;

public class Book 
{
	public String bookNumber;//ͼ����
	public String bookName;//ͼ������
	public String category;//���
	public String author;//����
	public String publishingHouse;//������
	public String publicationDate;//��������
	
	public Book(String bookNumber,String bookName,String category,String author,String publishingHouse,String publicationDate) 
	{
		this.bookNumber=bookNumber;
		this.bookName=bookName;
		this.category=category;
		this.author=author;
		this.publishingHouse=publishingHouse;
		this.publicationDate=publicationDate;
	}
	
	
}
