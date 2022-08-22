import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Form extends JDialog{

	ArregloArchivo ar;
	ImageIcon img;
	Calendar calendario = new GregorianCalendar();
	Date fecha = calendario.getTime();
	static String us_id,user;
	
	/*public static void main(String args[]){
    	new Form();
    }*/
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			new MenuPrincipal(Sesion.iduser,Sesion.us);
			dispose();
		}
	}
	
    public Form(String iduser, String user){
    	//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	Sesion.iduser = iduser;
		Sesion.us = user;
    	setTitle("Ajustes");
    	ImageIcon img = new ImageIcon("imagenes/m.png");
		setIconImage(img.getImage());
    	setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
    	/*Image icon = new ImageIcon(getClass().getResource("ventana.png")).getImage();
		setIconImage(icon);*/
		
    	ar=new ArregloArchivo();
		
    	getContentPane().add(build());
    	
    	setSize(500,350);
    	setLocationRelativeTo(null);
    	setVisible(true);
    	setResizable(false);
    }
    
    public JComponent build(){
    	JTabbedPane pest = new JTabbedPane();
    	
    	General gen = new General();
    	Conf_Report conreport = new Conf_Report();
    	Notificacion noti = new Notificacion();
    	//ImageIcon icono = new ImageIcon("hotel.ico");
    	
    	pest.addTab("General", null, gen.build(), "Datos Generales");
        pest.addTab("Reportes", null, conreport.build(), "Ubicacion de Reportes");
        pest.addTab("Notificaciones Email", null, noti.build(), "Notificacion de Email");
        
    	return pest;
    }
}