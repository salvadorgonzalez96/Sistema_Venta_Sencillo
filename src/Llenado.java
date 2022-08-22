 
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class Llenado
{
	//Conexion con=new Conexion();
	
	public void insertar(String sql){
		try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			s1.execute(sql);
			con.close();
		}
		catch(Exception exp){
			JOptionPane.showMessageDialog(null,""+exp,"Error Al Insertar en BD",0);//System.exit(0);
		}
	}
	
	public void llenar_combo(String sql, JComboBox cmb, JComboBox cmbi){
		cmb.removeAllItems();cmbi.removeAllItems();
		cmb.addItem("");cmbi.addItem("");
		try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery(sql);
			while(rs.next()){
				cmbi.addItem(rs.getInt(1));
				cmb.addItem(rs.getString(2));
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error Llenando ComboBox",0);System.exit(0);
		}
		cmb.setSelectedIndex(0);
	}
	
	public void llenado_jlist(String sql, DefaultListModel model){
		model.removeAllElements();
		try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery(sql);
			while(rs.next()){
				String nombre=rs.getString(1);
				String snombre=rs.getString(2);
				String apellido=rs.getString(3);
				String sapellido=rs.getString(4);
				//String usuario=rs.getString("usuario_user");
				//JOptionPane.showMessageDialog(null,usuario);	
				model.addElement(new String(nombre+" "+snombre+" "+apellido+" "+sapellido));
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,"Error "+exp.getMessage(),"Error Llenando Campos",0);
		}
	}
	
	public void llenado_jlistid(String sql, DefaultListModel model){
		model.removeAllElements();
		try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery(sql);
			while(rs.next()){
				String usuario=rs.getString(1);
				//String usuario=rs.getString("usuario_user");
				//JOptionPane.showMessageDialog(null,usuario);	
				model.addElement(new String(usuario));
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,"Error "+exp.getMessage(),"Error Llenando Campos ID",0);
		}
	}
}