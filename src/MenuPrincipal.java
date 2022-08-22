import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuPrincipal extends JFrame implements ActionListener,Runnable
{
	JMenuBar menuBarra;
    JMenu archivo,usuarios,planilla,clientes,reportes,ayuda;
    JMenuItem salir,primer,configempleado,configusuario,config,acercaDe,reporte1,reporte2,configplanilla,configdeduccion;
	JButton btnventa,btnreportes,btncompra,btninventario;
	Archivo arc;
	String user;
	String us_id;
	JLabel labelfecha;
    Thread hilo;
    Font fuente;
	
	void close(){
		//if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
		//		JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
		if(true){
			//System.exit(0);
			new Main();
			dispose();
		}
	}
	
	public MenuPrincipal(String id, String us){
		super("Menu");
		ImageIcon img = new ImageIcon("imagenes/m.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		us_id = id;
		user = us;
		getContentPane().setLayout(null);
		
		menuBarra = new JMenuBar();
        setJMenuBar(menuBarra);
		//menu archivo
        archivo=new JMenu("Archivo");
        //archivo.setMnemonic('A');
        archivo.setFont(new Font("Impact",Font.PLAIN,15));
        archivo.setIcon(new ImageIcon("imagenes/archivo.png"));
        
        config=new JMenuItem("Ajustes",new ImageIcon("imagenes/herram.png"));
        archivo.add(config);
        config.setEnabled(false);
        config.addActionListener(this);
        menuBarra.add(archivo);
        
        salir=new JMenuItem("Cerrar Sesion",new ImageIcon("imagenes/exit.png"));
        archivo.add(salir);
        salir.addActionListener(this);
        menuBarra.add(archivo);
        
        usuarios=new JMenu("Usuarios");
        usuarios.setFont(new Font("Impact",Font.PLAIN,15));
        usuarios.setIcon(new ImageIcon("imagenes/cate1.png"));
        
        configempleado=new JMenuItem("Configuracion Empleado",new ImageIcon("imagenes/usuario1.png"));
        usuarios.add(configempleado);
        configempleado.setEnabled(false);
        configempleado.addActionListener(this);
        menuBarra.add(usuarios);
        
        configusuario=new JMenuItem("Configuracion Usuarios",new ImageIcon("imagenes/usuario2.png"));
        usuarios.add(configusuario);
        configusuario.setEnabled(false);
        configusuario.addActionListener(this);
        menuBarra.add(usuarios);
        
        configdeduccion=new JMenuItem("Configuracion Deduccion",new ImageIcon("imagenes/usuario1.png"));
        usuarios.add(configdeduccion);
        configdeduccion.setEnabled(false);
        configdeduccion.addActionListener(this);
        menuBarra.add(usuarios);
        
        planilla=new JMenu("Planilla");
        planilla.setFont(new Font("Impact",Font.PLAIN,15));
        planilla.setIcon(new ImageIcon("imagenes/archivo.png"));
        
        configplanilla=new JMenuItem("Configuracion Planilla",new ImageIcon("imagenes/usuario1.png"));
        planilla.add(configplanilla);
        configplanilla.setEnabled(false);
        configplanilla.addActionListener(this);
        menuBarra.add(planilla);
        
        reportes=new JMenu("Reportes");
        //reportes.setMnemonic('R');
        reportes.setFont(new Font("Impact",Font.PLAIN,15));
        reportes.setIcon(new ImageIcon("imagenes/ayuda1.png"));
        menuBarra.add(reportes);
        
        reporte1=new JMenuItem("Reporte 1",new ImageIcon("imagenes/exit.png"));
        //reporte1.setMnemonic('S');
        //reporte1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        reportes.add(reporte1);
        reporte1.addActionListener(this);
        menuBarra.add(reportes);
        
        reporte2=new JMenuItem("Reporte 2",new ImageIcon("imagenes/exit.png"));
        //reporte2.setMnemonic('S');
        //reporte2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        reportes.add(reporte2);
        reporte2.addActionListener(this);
        menuBarra.add(reportes);
        
        ayuda=new JMenu("Ayuda");
        ayuda.setFont(new Font("Impact",Font.PLAIN,15));
        ayuda.setIcon(new ImageIcon("imagenes/info.png"));
        menuBarra.add(ayuda);
        
        acercaDe=new JMenuItem("Acerca Del Sistema",new ImageIcon("imagenes/info.png"));
        //acercaDe.setMnemonic('S');
        //acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        ayuda.add(acercaDe);
        acercaDe.addActionListener(this);
        menuBarra.add(ayuda);
        
		Font font = new Font("Modern No. 20",0,30);
		JLabel label=new JLabel("Bienvenido "+user);
		label.setBounds(470,80,250,30);
		label.setFont(font);
		getContentPane().add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		btnventa=new JButton("Venta");
		btnventa.setBounds(20,80,120,40);
		getContentPane().add(btnventa);
		btnventa.addActionListener(this);
		//btnventa.setVisible(false);
		
		btnreportes=new JButton("Reportes");
		btnreportes.setBounds(20,130,120,40);
		getContentPane().add(btnreportes);
		btnreportes.addActionListener(this);
		//btnreportes.setVisible(false);
		
		btncompra=new JButton("Compra");
		btncompra.setBounds(20,180,120,40);
		getContentPane().add(btncompra);
		btncompra.addActionListener(this);
		//btncompra.setVisible(false);
		
		btninventario=new JButton("Inventario");
		btninventario.setBounds(20,230,120,40);
		getContentPane().add(btninventario);
		btninventario.addActionListener(this);
		//btninventario.setVisible(false);
		
		fuente = new Font("Modern No. 20",0,20);
        labelfecha=new JLabel("");
        labelfecha.setBounds(60,680,1000,40);
        labelfecha.setFont(fuente);
        getContentPane().add(labelfecha);
        
        //Hilo Para Hora
        hilo = new Thread(this);
        hilo.start();
		
		label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("imagenes/fondo.jpg"));
		getContentPane().add(label);
		
		validaracceso(user);
		
		setSize(1200,800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public boolean validaracceso(String usuario) {
        boolean continua=false;
		String login1 = "";
		String login2 = "";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select modulo_codigo from tbl_acceso where usuario_codigo = '"+us_id+"';");
			while(rs.next()){
				login1=rs.getString(1);
				if(login1.equals("01")){
					config.setEnabled(true);
		        	continua=true;
		        }
				if(login1.equals("001")){
					configusuario.setEnabled(true);
		        	continua=true;
		        }
				if(login1.equals("002")){
					btnreportes.setVisible(true);
		        	continua=true;
		        }
				if(login1.equals("003")){
					btncompra.setVisible(true);
		        	continua=true;
		        }
				if(login1.equals("004")){
					configempleado.setEnabled(true);
		        	continua=true;
		        }
				if(login1.equals("005")){
					configdeduccion.setEnabled(true);
		        	continua=true;
		        }
				if(login1.equals("006")){
					planilla.setEnabled(true);
		        	continua=true;
		        }
				if(login1.equals("00602")){
					configplanilla.setEnabled(true);
		        	continua=true;
		        }
			}
			con.close();
		}
		catch(Exception exp){
			JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
        return continua;
    }
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==salir){
    		System.exit(0);
    	}
    	if(evt.getSource()==config){
    	    new Form(us_id, user);
            dispose();
    	}
    	if(evt.getSource()==configusuario){
			new ManUser(us_id, user);
			dispose();
		}
    	if(evt.getSource()==configempleado){
			new Manten_Empleado(us_id, user);
			dispose();
		}
    	if(evt.getSource()==configdeduccion){
			new Manten_Deducciones(us_id, user);
			dispose();
		}
    	if(evt.getSource()==btncompra){
            new OpcionesProductos(us_id, user);
            dispose();
        }
		if(evt.getSource()==btnreportes){
			Map parametros = new HashMap();
			parametros.put("sql","SELECT * FROM vista_factura;");
            new Reporte("report_fact.jasper",parametros);
			//dispose();
		}
		if(evt.getSource()==btnventa){
			new OpcionesVenta(us_id, user);
			dispose();
		}
		if(evt.getSource()==acercaDe){
			JOptionPane.showMessageDialog(null,"Programa Realizado por \nIchigoz AppSoft Corporation.","Acerca De",1);
		}
	}
	
	public void run(){
         Thread ct = Thread.currentThread();
         while(ct == hilo) {   
             Calendar calendario = new GregorianCalendar();
             Date fecha = calendario.getTime();
             int dia = calendario.get(Calendar.DAY_OF_MONTH);
             //String mes = calendario.getDisplayName(Calendar.MONTH,2,"pm");
             int mes = calendario.get(Calendar.MONTH)+1;
             int anio = calendario.get(Calendar.YEAR);
             calendario.setTime(fecha);
             //labelfecha.setText("Fecha: "+dia+"-"+mes+"-"+anio);
             labelfecha.setText(fecha.toLocaleString());
             //labelhor.setText("Hora: "+fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds());
          try {
           Thread.sleep(1000);
          }catch(InterruptedException e) {}
        }
    }
}