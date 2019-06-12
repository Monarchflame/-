package database;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
 
public class Model extends AbstractTableModel//���ر��
{
	public String datebasename="bookManage";
	private Vector<String> columnNames;//�����
	private Vector<Vector<String>> rowDates;//���Ԫ��
	
	//��ɾ��
	public boolean cud(String sql, String []paras)
	{
		return new ConnectDatabase().cudExecute(sql, paras);
	}
	
	//��ѯ
	public void query(String sql, String []paras)//ֻдһ��query��Ϊ�˸��¼�
	{
		ConnectDatabase conData = null;
		//��ʼ��JTable��Ϣ
		columnNames = new Vector<String>();//�У�ʵ����һ����̬���飬����ΪԪ��
		rowDates = new Vector<Vector<String>>();//ʵ����һ����̬���飬Ԫ��ΪԪ��
		try 
		{
			conData = new ConnectDatabase();conData = new ConnectDatabase();
			ResultSet rs = conData.queryExecute(sql, paras);
			
			ResultSetMetaData metaData = rs.getMetaData(); 
			int colum = metaData.getColumnCount();    
			for (int i = 1; i <= colum; i++)    
			{    
			 	//��ȡ����    
			 	String columName = metaData.getColumnName(i); 
			 	columnNames.add(columName);
			}
			while(rs.next()) //���������
			{//�м��оͼӶ���
				Vector<String> row = new Vector<String>();//Ĭ�ϴ�СΪ10��һ��Ԫ�ؾ���Ԫ���һ������
				for (int i = 1; i <= colum; i++)
				{    
				 	//��ȡԪ������   
				 	String attributes = rs.getString(i); 
				 	row.add(attributes);
				}
				rowDates.add(row);//һ��Ԫ�ؾ������ݿ���һ��Ԫ��
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conData.close();
		}
	}
	
	@Override
	public int getColumnCount() 
	{
		// TODO Auto-generated method stub
		return this.columnNames.size();// ���ش������е��������
	}
 
	@Override
	public int getRowCount() 
	{
		// TODO Auto-generated method stub
		return this.rowDates.size();
	}
 
	@Override
	public Object getValueAt(int row, int col)
	{
		// TODO Auto-generated method stub
		if(!rowDates.isEmpty())
			return ((Vector<?>)this.rowDates.get(row)).get(col);
		else
			return null;
	}
	
	@Override
	public String getColumnName(int column) 
	{
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
}
