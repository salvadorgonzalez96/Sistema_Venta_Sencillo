import javax.swing.*;
import java.awt.*;
public class OpcionesVenta extends JDialog
{
    
    static String i="",u="";
    
    public static void main(String args[]){
        new OpcionesVenta("1","admin");
    }
    
    void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			new MenuPrincipal(Sesion.iduser, Sesion.us);
			dispose();
			//System.exit(0);
		}
	}
    
    public OpcionesVenta(String id, String u){
    	setTitle("Venta de Productos");
        Sesion.iduser=id;
        Sesion.us=u;
    	setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("imagenes/m.png");
		setIconImage(img.getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
        
        getContentPane().add(build());
        //this.getContentPane().setBackground(Color.gray);
        setSize(1200,800);
        //setLocation(0,0);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public JComponent build(){
        JTabbedPane pest=new JTabbedPane();
        VentaProducto n1=new VentaProducto();
        //if(ar.bus_linea("Usuario_Acceso.txt",Sesion.iduser+";5;"))
        pest.addTab("Venta", null, n1.build(Sesion.iduser,Sesion.us));
        return pest;
    }
}