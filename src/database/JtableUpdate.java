package database;

import javax.swing.JTable;

public class JtableUpdate 
{
	//����JTable������
	public static Model jtableUpdate_query(JTable table, String sql, String[] paras)
	{
		//����ģ��
		Model sm = new Model();
		sm.query(sql, paras);
		//������ʾ
		if(table!=null)
			table.setModel(sm);
		return sm;
	}
	
	/*public static Model jtableUpdate_cud(JTable table, String sql, String[] paras)
	{
		//����ģ��
		Model sm = new Model();
		sm.cud(sql, paras);
		//������ʾ
		if(table!=null)
			table.setModel(sm);
		return sm;
	}*/
}
