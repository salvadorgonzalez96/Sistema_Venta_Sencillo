import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class General extends JFrame implements ActionListener, KeyListener
{
	int boton;
	
	JTextField txtNombre;
	JTextField txtDireccion;
	JTextField txtRTN;
	JTextField txtTelefono;
	
	JButton btnguardar;
	JButton btnsalir;
	JButton btnacces;
	
	Calendar calendario = new GregorianCalendar();
	Date fecha = calendario.getTime();
	
	JPanel panel;
	
	public JComponent build(){
		panel = new JPanel();
		setFrame();
		return panel;
	}
	
	public void setFrame(){
		panel.setLayout(null);
		
		//getContentPane().setBackground(Color.darkGray);
		
		Font font = new Font("Modern No. 20",0,20);
		JLabel label=new JLabel("Datos de Hotel");
		label.setBounds(30,20,200,30);//x,y,ancho,alto
		label.setFont(font);
		panel.add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		//Cedula
		label=new JLabel("Nombre : ");
		label.setBounds(50,60,120,30);//x,y,ancho,alto
		panel.add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		txtNombre=new JTextField();
		txtNombre.setBounds(150,60,180,30);
		panel.add(txtNombre);
		//txtNombre.addKeyListener(this);
		//---------------------------------------------------------------
		
		//Nombre
		label=new JLabel("Direccion : ");
		label.setBounds(50,100,120,30);//x,y,ancho,alto
		panel.add(label);
		txtDireccion=new JTextField();
		txtDireccion.setBounds(150,100,180,30);
		panel.add(txtDireccion);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		txtNombre.addKeyListener(this);
		
		//Apellido
		label=new JLabel("RTN : ");
		label.setBounds(50,140,120,30);//x,y,ancho,alto
		panel.add(label);
		txtRTN=new JTextField();
		txtRTN.setBounds(150,140,180,30);
		panel.add(txtRTN);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		txtRTN.addKeyListener(this);
		
		//Depto
		label=new JLabel("Telefono : ");
		label.setBounds(50,180,120,30);//x,y,ancho,alto
		panel.add(label);
		txtTelefono=new JTextField();
		txtTelefono.setBounds(150,180,180,30);
		panel.add(txtTelefono);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		
		//Boton Guardar
		btnguardar=new JButton("Guardar");
		btnguardar.setBounds(195,240,100,40);//x,y,ancho,alto
		panel.add(btnguardar);
		btnguardar.addActionListener(this);
		btnguardar.addKeyListener(this);
		
		/*label = new JLabel("");
		label.setBounds(0,0,500,300);
		label.setIcon(new ImageIcon("forms.png"));
		panel.add(label);*/
	}
	public void actionPerformed(ActionEvent evt){
		
		if(evt.getSource()==btnguardar){
			verificar();
		}
		if(evt.getSource()==btnsalir){
			dispose();
		}
	}
	
	public void verificar(){
		if(txtNombre.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese Nombre de Hotel");
			txtNombre.requestFocus();
		}
		else if(txtDireccion.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese Direccion");
			txtDireccion.requestFocus();
		}
		else if(txtRTN.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese RTN");
			txtRTN.requestFocus();
		}
		else if(txtTelefono.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese Telefono");
			txtTelefono.requestFocus();
		}
	}
	
	public void keyPressed(KeyEvent ev){
		boton = ev.getKeyCode();
		/*if(boton == ev.VK_ENTER){
			verificar();
			Mod mod = new Mod();
			mod.txtarea.setText(em.imprimirArchivo());
		}*/
        if(boton == 27){
            System.exit(0);
        }
   }
       
	public void keyReleased(KeyEvent ev){}
	
   public void keyTyped(KeyEvent ev){
	  if(ev.getSource() == txtRTN){
           char c = ev.getKeyChar();
           if(Character.isDigit(c) && txtRTN.getText().length()<14){}               
             else{
                ev.consume();
             }
       }
	   else if(ev.getSource() == txtTelefono){
           char c = ev.getKeyChar();
           if(Character.isDigit(c) && txtTelefono.getText().length()<8){}            
           else{
                ev.consume();
           }
       }
   }
   
}