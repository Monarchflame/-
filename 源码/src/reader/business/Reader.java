package reader.business;

public abstract class Reader 
{
	public String number;//���߱��
	public String password;//��������
	public String name;//��������
	public String gender;//�����Ա�
	public String phonenumber;//��ϵ�绰
	public int canBorrowNumber;//�ɽ�����
	public int borrownumber;//�ѽ�����
	
	public Reader(String number,String password,String name,String gender,String phonenumber,int canBorrowNumber,int borrownumber) 
	{
		this.number=number;
		this.name=name;
		this.password=password;
		this.gender=gender;
		this.phonenumber=phonenumber;
		this.canBorrowNumber=canBorrowNumber;
		this.borrownumber=borrownumber;
	}
}
