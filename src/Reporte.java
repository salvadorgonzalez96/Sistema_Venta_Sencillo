import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

class Reporte
{
	Reporte(String n,Map parameters, boolean b){
		try{ 
			File app=new File(n);
            String fileName=app.getCanonicalPath();         
            URL urlMaestro = getClass().getResource(n);
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
            JasperPrint jasperPrint = null;
            jasperPrint= JasperFillManager.fillReport(masterReport,parameters,new Conexion().getConexion());
            JasperViewer jviewer = new JasperViewer(jasperPrint,false); 
            if(jasperPrint.getPages().isEmpty()==false){
                //estado=true;
                JasperPrintManager p = new JasperPrintManager();
                p.printReport(jasperPrint,b);
            }
            else{
                JOptionPane.showMessageDialog(null,"Reporte en Blanco, Revise su Consulta","Consulta Inválida",0);
                //estado=false;
            }
            
        } 
        catch (Exception j) { 
            JOptionPane.showMessageDialog(null,"Mensaje de Error:"+j.getMessage(),"Error en Reporte",0);
            j.printStackTrace(); 
        }
    }
    
    public Reporte(String Reporte, Map parametros){
        System.out.println(Reporte);
        try{
        	JOptionPane.showMessageDialog(null,Reporte);
            URL urlMaestro = getClass().getResource(Reporte);
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
            JasperPrint jasperPrint = null;
            jasperPrint= JasperFillManager.fillReport(masterReport,parametros,new Conexion().getConexion());
            JRViewer jrv = new JRViewer(jasperPrint);
            jrv.setZoomRatio(new Float(0.99));

            if(jasperPrint.getPages().isEmpty()==false){
                new Visor_Reportes(jrv,"... Vista de Reporte");
            }
            else{
                JOptionPane.showMessageDialog(null,"No hay datos");
            }
        }
        catch (Exception j) {
            JOptionPane.showMessageDialog(null,"Mensaje de Error:"+j,"Error en Reporte2",0);
            j.printStackTrace();
        }
    }
    
    public static void main(String []arg){
    	try{
    		Map parametros = new HashMap();
            /*parametros.put("sql","select *,date_format(now(),'%d-%m-%Y %r')as hoy,'admin'as user "+
            "from v_tablaposicion where equipo='OL'");
            new Reporte2("rep_tabla_posicion.jasper",parametros,true);*/
                
            //parametros.put("sql","SELECT * FROM vista_factura;");
            //new Reporte("report_fact.jasper",parametros);
    		
            //new Reporte2("rep_tabla_posicion.jasper",parametros);
            
            parametros.put("sql","SELECT * FROM vista_det_productos;");
            new Reporte("report_fact.jasper",parametros);
    	}
        catch(Exception exp){}
    }   
	}

class Visor_Reportes extends JDialog
{
    public Visor_Reportes(JRViewer jrv, String str){
        super(new JFrame(), str, true);
        con = new Container();
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);
        setModal(true);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        jrv.setPreferredSize(new Dimension(pantalla.width - 120, pantalla.height - 150));
        JScrollPane reportScroll = new JScrollPane(jrv);
        JPanel viewer = new JPanel();
        viewer.add(jrv);
        addWindowListener(new WindowAdapter(){
        	public void windowOpened(WindowEvent windowevent){}
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
        
        viewer.setBounds(10, 10, pantalla.width - 100, pantalla.height);
        getContentPane().add(viewer);
        setSize(new Dimension(pantalla.width - 50, pantalla.height - 50));
        setJMenuBar(menu());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JMenuBar menu(){
        JMenu menuArchivo = new JMenu("ARCHIVO");
        menuArchivo.setMnemonic('A');
        menuArchivo.setBackground(Color.WHITE);
        JMenuItem exit = new JMenuItem("Salir", new ImageIcon("Imagenes/exit.jpg"));
        exit.setMnemonic('S');
        exit.setAccelerator(KeyStroke.getKeyStroke(115, 8));
        exit.setAccelerator(KeyStroke.getKeyStroke(27, 0));
        exit.setBackground(Color.WHITE);
        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		dispose();
        	}
        });
        menuArchivo.add(exit);
        JMenuBar barra = new JMenuBar();
        barra.setBackground(Color.WHITE);
        barra.add(menuArchivo);
        return barra;
    }
    JLabel muestra;
    JButton Ok;
    Dimension cuadro;
    Container con;
}
