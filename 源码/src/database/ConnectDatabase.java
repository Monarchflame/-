/*���ݿ�ײ�����*/
package database;
import java.sql.*;

public class ConnectDatabase
{
	//���ݿ�
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost; DatabaseName=SchoolBookManage";
	private String user = "sa";
	private String passwd = "123456";
	
	private void run(String sql) throws ClassNotFoundException, SQLException 
	{
		//1����������
		Class.forName(driver);
		//2������
		ct = DriverManager.getConnection(url, user, passwd);
		//3������PreparedStatement,����ִ��SQL���
		ps = ct.prepareStatement(sql);
	}
	
	//��ѯ�����ز�ѯ�����
	public ResultSet queryExecute(String sql, String []paras)
	{
		try
		{
			run(sql);
			//���ʺŸ�ֵ
			if(paras != null)
			{
				for(int i = 0; i < paras.length; i++) 
				{
					ps.setString(i + 1, paras[i]);
				}
			}
			//ִ��
			try {//���޽��������ʱ������
				rs = ps.executeQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		//����ֵ
		return rs;
	}
		
	//��ɾ��
	public boolean cudExecute(String sql, String []paras)
	{
		boolean b = true;
		try 
		{
			run(sql);
			//���ʺŸ�ֵ
			for(int i = 0; i < paras.length; i++)
			{
				ps.setString(i + 1, paras[i]);
			}
			//ִ��
			if(ps.executeUpdate() != 1) 
				b = false;
		} 
		catch (Exception e) 
		{
			b = false;
			e.printStackTrace();
		} 
		finally 
		{
			this.close();
		}
		//����ֵ
			return b;
	}
		
	//�ر���Դ
	public void close()
	{
		try 
		{
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
	}
}

