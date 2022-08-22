import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class Conexion
{
	static String url;
	static String user;
	static String pass;
	static String db;
	static String localizacion;
	
	public static void main(String args[]){
		Conexion c=new Conexion();
		Connection con=c.getConexion();
		if(con!=null)JOptionPane.showMessageDialog(null,"Si Hay Conexion");
	}
	
	Conexion(){}
	
	Conexion(String l, String b, String u, String p){
		this.db = b;
		this.localizacion = l;
		this.user = u;
		this.pass = p;
		this.url = "jdbc:mysql://"+l+":3306/"+b;
	}
	
	Connection getConexion(){
		try{
			Connection con=null;
			try{
				String driver = "com.mysql.jdbc.Driver";
				Class.forName( driver );
				con = DriverManager.getConnection( url , user , pass );
				//JOptionPane.showMessageDialog(null,"Si Hay Conexion en la base "+this.db);
				return con;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null," "+e);
				//System.exit(0);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"ERROR EN CONEXION DE RED A BASE DE DATOS\n"+e.getMessage(),
			"Conexion a Base de Datos Incorrecta",0);
			System.exit(0);
		}
		return null;
	}
}