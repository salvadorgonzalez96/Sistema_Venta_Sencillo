import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Principal extends JFrame implements ActionListener
{
	Conexion conex;
	JButton btnok;
	static JTextField txtubicacion;
	static JTextField txtbase;
	static JTextField txtuser;
	static JTextField txtpass;
	static String localizacion,db,user,pass,url;
	String arreglo[]=new String[2];
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			dispose();
		}
	}
	
	public Principal(){
		super("Verificador");
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
		getContentPane().setLayout(null);
		JLabel label=new JLabel("Constractor");
		label.setBounds(50,20,120,30);
		getContentPane().add(label);
		
		label=new JLabel("Ubicacion");
		label.setBounds(50,50,120,30);
		getContentPane().add(label);
		txtubicacion = new JTextField();
		txtubicacion.setBounds(120,55,120,20);
		getContentPane().add(txtubicacion);
		
		label=new JLabel("BD");
		label.setBounds(50,80,120,30);
		getContentPane().add(label);
		txtbase = new JTextField();
		txtbase.setBounds(120,85,120,20);
		getContentPane().add(txtbase);
		
		label=new JLabel("User");
		label.setBounds(50,120,120,30);
		getContentPane().add(label);
		txtuser = new JTextField();
		txtuser.setBounds(120,125,120,20);
		getContentPane().add(txtuser);
		
		label=new JLabel("Password");
		label.setBounds(50,150,120,30);
		getContentPane().add(label);
		txtpass= new JPasswordField();
		txtpass.setBounds(120,155,120,20);
		getContentPane().add(txtpass);

		btnok=new JButton("OK");
		btnok.setBounds(100,200,120,40);
		btnok.addActionListener(this);
		getContentPane().add(btnok);
		
		llenado("Conexion.txt");
		
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void actualizar(){
		Archivo arch= new Archivo("Conexion.txt");
	    String cadena="",salario="";
	    cadena=arch.toString();
	    String palabras[]=new String[2];
	    palabras[0]="Localizacion;BaseDatos;Usuario;Clave";
	    palabras[1]=txtubicacion.getText()+";"+txtbase.getText()+";"+txtuser.getText()+";"+txtpass.getText()+";";
	    try{
	    	arch.guardararreglo(palabras);
	    }
	    catch(Exception exp){
	    }
	 }
	
	public void llenado(String ruta){
        Archivo a= new Archivo(ruta);
        String cadena="";
        cadena=a.toString();
        String ar[]=cadena.split("\n");
        for(int i=0;i<ar.length;i++){
            String interno[]=ar[i].split(";");
            arreglo[i]=interno[0]+";"+interno[1]+";"+interno[2]+";"+interno[3]+";";
            if(i==1){
	            txtubicacion.setText(interno[0]);
	            txtbase.setText(interno[1]);
	            txtuser.setText(interno[2]);
	            txtpass.setText(interno[3]);
            }
        }
    }

	public static boolean verificar(){
		boolean continuara = true;
		
		Archivo a= new Archivo("Conexion.txt");
        String cadena="";
        cadena=a.toString();
        String ar[]=cadena.split("\n");
        for(int i=1;i<ar.length;i++){
            String interno[]=ar[i].split(";");
            localizacion=interno[0];
            db=interno[1];
            user=interno[2];
            pass=interno[3];
        }
        url = "jdbc:mysql://"+localizacion+":3306/"+db;
        Conexion conex = new Conexion(localizacion,db,user,pass);
        Connection con = conex.getConexion();
		if(con==null){
			continuara=false;
		}
		return continuara;
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==btnok){
			conex = new Conexion(txtubicacion.getText(), txtbase.getText(), txtuser.getText(), txtpass.getText());
			Connection con = conex.getConexion();
			actualizar();
			if(con!=null){
				new Main();
				dispose();
			}
		}
	}
	
	public static void main(String args[]){
		if(verificar() == true){
			new Main();
		}else{
			new Principal();
		}
	}	
}