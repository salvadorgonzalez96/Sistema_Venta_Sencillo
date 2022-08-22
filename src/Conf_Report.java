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
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class Conf_Report extends JFrame implements ActionListener, KeyListener
{
	JTextField txtBrowsable;
	JPanel panel;
	
	Calendar calendario = new GregorianCalendar();
	Date fecha = calendario.getTime();

	/*public Form2(){
		em.modificar();
		//txtarea.setText(em.imprimirArchivo());
	}*/
	
	public JComponent build(){
		panel = new JPanel();
		setFrame();
		return panel;
	}
	
	public void setFrame(){
		panel.setLayout(null);
		
		//getContentPane().setBackground(Color.darkGray);
		Font font = new Font("Modern No. 20",0,20);
		JLabel label=new JLabel("Reporte de Ventas");
		label.setBounds(30,30,200,30);//x,y,ancho,alto
		label.setFont(font);
		panel.add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		//Cedula
		label=new JLabel("Direccion donde guardar repoortes");
		label.setBounds(50,100,250,30);//x,y,ancho,alto
		panel.add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		txtBrowsable=new JTextField();
		txtBrowsable.setBounds(50,130,190,30);
		panel.add(txtBrowsable);
		txtBrowsable.addKeyListener(this);
		//---------------------------------------------------------------
		
		/*label = new JLabel("");
		label.setBounds(0,0,500,300);
		label.setIcon(new ImageIcon("forms.png"));
		panel.add(label);*/
	}
	public void actionPerformed(ActionEvent evt){
		
	}
	
	public void keyPressed(KeyEvent ev){}
       
	public void keyReleased(KeyEvent ev){}
   
	public void keyTyped(KeyEvent ev){}

}