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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JFrame implements ActionListener,KeyListener
{
	JButton btningresar;
	JTextField txtuser;
	JPasswordField txtpass;
	Archivo arc;
	int boton;
	ArregloArchivo ar;
	String login1 = "", login2 = "", login3 = "";
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	public Main(){
		super("Login");
		ImageIcon img = new ImageIcon("imagenes/m.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
		getContentPane().setLayout(null);
		
		ar = new ArregloArchivo();
		JLabel label=new JLabel("User");
		label.setBounds(50,100,120,30);
		getContentPane().add(label);
		txtuser = new JTextField();
		txtuser.setBounds(100,100,120,30);
		getContentPane().add(txtuser);
		
		label.setForeground(Color.white);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		label=new JLabel("Password");
		label.setBounds(40,150,120,30);
		getContentPane().add(label);
		txtpass= new JPasswordField();
		txtpass.setBounds(100,150,120,30);
		getContentPane().add(txtpass);
		txtpass.addKeyListener(this);

		label.setForeground(Color.white);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		btningresar=new JButton("Login");
		btningresar.setBounds(100,200,120,40);
		btningresar.addActionListener(this);
		getContentPane().add(btningresar);
		
		label = new JLabel("");
		label.setBounds(0,0,300,300);
		label.setIcon(new ImageIcon("imagenes/login.png"));
		getContentPane().add(label);
		
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==btningresar){
			if(validarusuario(txtuser.getText().toString(), txtpass.getText().toString())){			
				new MenuPrincipal(login1, txtuser.getText().toString());
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "Usuario o Contraseña Invalida");
			}
		}
	}
	
	public boolean validarusuario(String usuario, String clave) {
        boolean continua=false;
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select usuario_codigo,usuario_nick,usuario_clave from proyectoava2.tbl_usuario where usuario_nick='"+usuario+"'and usuario_clave='"+clave+"' and usuario_estado ='habilitado';");
			while(rs.next()){
				login1=rs.getString(1);
				login2=rs.getString(2);
				login3=rs.getString(3);
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
        if(login2.equals(usuario) && login3.equals(clave)){
        	continua=true;
        }
        return continua;
    }

	@Override
	public void keyPressed(KeyEvent ev) {
		if(ev.getSource()==txtuser){
			if(txtuser.getText().length()==0){
				txtuser.requestFocus();
			}
			else{
				txtpass.requestFocus();
			}
		}
		if(ev.getSource()==txtpass){
			if(txtuser.getText().isEmpty()){
				txtuser.requestFocus();
			}
			else{
				if(ev.getKeyCode()==KeyEvent.VK_ENTER){
					if(validarusuario(txtuser.getText().toString(), txtpass.getText().toString())){			
						new MenuPrincipal(login1, txtuser.getText().toString());
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Usuario o Contraseña Invalida");
					}
		        }
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent ev) {}

	@Override
	public void keyTyped(KeyEvent ev) {}
}