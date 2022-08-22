import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class Notificacion extends JFrame implements ActionListener, KeyListener
{
	Calendar calendario = new GregorianCalendar();
	Date fecha = calendario.getTime();
	
	JTextField txtCorreo;
	JPasswordField txtPass;
	JTextField txtPuerto;
	JTextField txtHost;
	JTextField txtCcEmail;
	
	JButton btnguardar;
	
	JPanel panel;
	
	public Notificacion(){}
	
	public JComponent build(){
		panel = new JPanel();
		setFrame();
		return panel;
	}
	
	public void setFrame(){
		panel.setLayout(null);
		
		//getContentPane().setBackground(Color.darkGray);
		Font font = new Font("Modern No. 20",0,20);
		JLabel label=new JLabel("Notificacion por Email");
		label.setBounds(30,20,200,30);//x,y,ancho,alto
		label.setFont(font);
		panel.add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
				
		//Cedula
		label=new JLabel("Correo :");
		label.setBounds(50,60,120,30);//x,y,ancho,alto
		panel.add(label);
		txtCorreo=new JTextField();
		txtCorreo.setBounds(150,60,180,30);
		panel.add(txtCorreo);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		
		label=new JLabel("Contraseña :");
		label.setBounds(50,90,120,30);//x,y,ancho,alto
		panel.add(label);
		txtPass=new JPasswordField();
		txtPass.setBounds(150,90,180,30);
		panel.add(txtPass);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		label=new JLabel("Puerto :");
		label.setBounds(50,120,120,30);//x,y,ancho,alto
		panel.add(label);
		txtPuerto=new JTextField();
		txtPuerto.setBounds(150,120,180,30);
		panel.add(txtPuerto);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		label=new JLabel("Host : ");
		label.setBounds(50,150,120,30);//x,y,ancho,alto
		panel.add(label);
		txtHost=new JTextField();
		txtHost.setBounds(150,150,180,30);
		panel.add(txtHost);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		label=new JLabel("Copia de Notifiaciones: ");
		label.setBounds(50,180,180,30);//x,y,ancho,alto
		panel.add(label);
		txtCcEmail=new JTextField();
		txtCcEmail.setBounds(180,190,180,30);
		panel.add(txtCcEmail);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);

		//Boton Guardar
		btnguardar=new JButton("Guardar");
		btnguardar.setBounds(50,250,100,50);//x,y,ancho,alto
		panel.add(btnguardar);
		btnguardar.addActionListener(this);
		
		/*label = new JLabel("");
		label.setBounds(0,0,500,300);
		label.setIcon(new ImageIcon("forms.png"));
		panel.add(label);*/
	}
	
	public void actionPerformed(ActionEvent evt){}
	
	public void keyPressed(KeyEvent ev){}
	public void keyReleased(KeyEvent ev){}
	public void keyTyped(KeyEvent ev){}
}