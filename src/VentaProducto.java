import javax.swing.*;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.table.*;

public class VentaProducto extends JPanel implements ActionListener,KeyListener,MouseListener
{
	JPanel panel;
    DefaultTableModel model;
    JTable tabla;
    String columnas[]={"ID","Codigo","Producto","Cantidad","Peso","P.Costo","P.Venta","Impuesto","Categoria","Prov.","Ganancia","Descuento"};
    
    DefaultTableModel model2;
    JTable tabla2;
    String columnas2[]={"ID","Nombre","RTN"};
    
    DefaultTableModel model3;
    JTable tabla3;
    String columnas3[]={"ID","Codigo","Producto","Cantidad","Peso","Precio Venta","ISV","Desc."};
    DecimalFormat dosdig=new DecimalFormat("0.00");
    JTextField txtcantidad,txtnombre,txtprecio,txtpeso,txtcodigo,txtbuscarpalabra,txtbuscarcliente,txtnombrecliente,txtrtn,txtdireccion,txttelefono,txtemail;
    JTextField txtSubtotal,txtdescuento,txtISV,txtTotal;
    JComboBox impuesto,impuestoid,categoria,categoriaid;
    JButton btnvender,btnagregar;
    JRadioButton rbFormaPago[] = new JRadioButton[3];
    ButtonGroup grupo = new ButtonGroup();
    String imp,impid="",canti,id="",idcliente="",tel="",dir="",email="",desc="",disponible="";
    String fact="FAC-00"+contador("FAC");
    int maximo=0;
    
    public JComponent build(String id, String us){
    	Sesion.iduser=id;
        Sesion.us=us;
        panel=new JPanel();
        panel.setLayout(null);
        //panel.setBackground(Color.gray);
        setSize(800,550);
        setVisible(true);
        setFrame();
        return panel;
    }
    
    public void setFrame(){
    	Font font = new Font("Modern No. 20",Font.BOLD,19);
        JLabel label=new JLabel("Venta de Productos");
        label.setBounds(30,0,180,80);//x,y,ancho,alto
        label.setFont(font);
        panel.add(label);
        
        label=new JLabel("Usuario:");
        label.setBounds(15,710,100,20);//x,y,ancho,alto
        panel.add(label);
        
        label=new JLabel(Sesion.us);
        label.setBounds(70,710,100,20);//x,y,ancho,alto
        panel.add(label);
        
        label=new JLabel("Nombre del Producto");
        label.setBounds(10,80,150,50);//x,y,ancho,alto
        panel.add(label);
        txtnombre=new JTextField();
        txtnombre.setEditable(true);
        txtnombre.setBounds(135,95,180,20);
        txtnombre.addKeyListener(this);
        txtnombre.addActionListener(this);
        panel.add(txtnombre);
        txtnombre.setEditable(false);
        
        label=new JLabel("Codigo");
        label.setBounds(330,80,150,50);//x,y,ancho,alto
        panel.add(label);
        txtcodigo=new JTextField();
        txtcodigo.setEditable(true);
        txtcodigo.setBounds(375,95,120,20);
        txtcodigo.addKeyListener(this);
        txtcodigo.addActionListener(this);
        panel.add(txtcodigo);
        txtcodigo.setEditable(false);
        
        label=new JLabel("Cantidad");
        label.setBounds(10,120,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtcantidad=new JTextField();
        txtcantidad.setEditable(true);
        txtcantidad.setBounds(70,135,40,20);//x,y,ancho,alto
        //txtcantidad.setForeground(Color.black);
        txtcantidad.addKeyListener(this);
        txtcantidad.addActionListener(this);
        panel.add(txtcantidad);
        
        label=new JLabel("Peso");
        label.setBounds(150,120,60,50);//x,y,ancho,alto
        panel.add(label);
        
        txtpeso=new JTextField();
        txtpeso.setBounds(180,135,60,20);
        txtpeso.addKeyListener(this);
        txtpeso.addActionListener(this);
        panel.add(txtpeso);
        label=new JLabel("lbs");
        label.setBounds(245,120,60,50);//x,y,ancho,alto
        panel.add(label);
        txtpeso.setEditable(false);
        
        label=new JLabel("Precio L");
        label.setBounds(300,120,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtprecio=new JTextField();
        txtprecio.setEditable(true);
        txtprecio.setBounds(350,135,70,20);//x,y,ancho,alto
        //txtcantidad.setForeground(Color.black);
        txtprecio.addKeyListener(this);
        txtprecio.addActionListener(this);
        panel.add(txtprecio);
        txtprecio.setEditable(false);
        
        label=new JLabel("Buscar Producto");
        label.setBounds(20,215,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtbuscarpalabra=new JTextField();
        txtbuscarpalabra.setBounds(130,230,180,20);
        txtbuscarpalabra.addKeyListener(this);
        txtbuscarpalabra.addActionListener(this);
        txtbuscarpalabra.setText("");
        panel.add(txtbuscarpalabra);
        
        
        Font font2 = new Font("",Font.BOLD,15);
        label=new JLabel("Productos en Inventario");
        label.setBounds(200,265,180,50);//x,y,ancho,alto
        label.setFont(font2);
        panel.add(label);
        ///Tabla
        model = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column){
        		return false;
        	}
        };
        
        //Agregar las columnas al Model
        for(int i=0;i<columnas.length;i++){
        	model.addColumn(columnas[i]);
        }
        tabla=new JTable(model);

        tabla.addMouseListener(this);
        //Redimensionar las columnas del Model
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(30);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(30);
        columnModel.getColumn(6).setPreferredWidth(30);
        columnModel.getColumn(7).setPreferredWidth(30);
        columnModel.getColumn(8).setPreferredWidth(30);
        columnModel.getColumn(9).setPreferredWidth(150);
        TableColumnModel c=tabla.getColumnModel();
        c.removeColumn(c.getColumn(5));
        c.removeColumn(c.getColumn(9));
        //c.removeColumn(c.getColumn(10));
        
        JScrollPane scr=new JScrollPane(tabla);
        scr.setBounds(10,300,850,200);
        panel.add(scr);
        validartabla("");
        
        label=new JLabel("Lista Clientes");
        label.setBounds(670,15,180,50);//x,y,ancho,alto
        label.setFont(font2);
        panel.add(label);
        //Tabla Clientes
        model2 = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        
        //Agregar las columnas al Model
        for(int i=0;i<columnas2.length;i++){
        	model2.addColumn(columnas2[i]);
        }
              
        tabla2=new JTable(model2);

        tabla2.addMouseListener(this);
        //Redimensionar las columnas del Model
        TableColumnModel columnModel2 = tabla2.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(10);
        columnModel2.getColumn(1).setPreferredWidth(150);
        columnModel2.getColumn(2).setPreferredWidth(100);
        /*TableColumnModel c2=tabla2.getColumnModel();
        c2.removeColumn(c2.getColumn(2));*/
        
        JScrollPane scr2=new JScrollPane(tabla2);
        scr2.setBounds(580,50,280,200);
        panel.add(scr2);
        
        validarCliente("");
        
        label=new JLabel("Buscar Cliente");
        label.setBounds(950,30,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtbuscarcliente=new JTextField();
        txtbuscarcliente.setBounds(950,65,180,20);
        txtbuscarcliente.addKeyListener(this);
        txtbuscarcliente.addActionListener(this);
        txtbuscarcliente.setText("");
        panel.add(txtbuscarcliente);
        
        label=new JLabel("Nombre de Cliente");
        label.setBounds(950,95,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtnombrecliente=new JTextField();
        txtnombrecliente.setBounds(950,130,180,20);
        txtnombrecliente.addKeyListener(this);
        txtnombrecliente.addActionListener(this);
        txtnombrecliente.setText("");
        panel.add(txtnombrecliente);
        
        label=new JLabel("RTN Cliente");
        label.setBounds(950,155,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtrtn=new JTextField();
        txtrtn.setBounds(950,190,180,20);
        txtrtn.addKeyListener(this);
        txtrtn.addActionListener(this);
        txtrtn.setText("");
        panel.add(txtrtn);
        
        label=new JLabel("Direccion Cliente");
        label.setBounds(950,215,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtdireccion=new JTextField();
        txtdireccion.setBounds(950,250,180,20);
        txtdireccion.addKeyListener(this);
        txtdireccion.addActionListener(this);
        txtdireccion.setText("");
        panel.add(txtdireccion);
        
        label=new JLabel("Telefono Cliente");
        label.setBounds(950,275,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txttelefono=new JTextField();
        txttelefono.setBounds(950,310,180,20);
        txttelefono.addKeyListener(this);
        txttelefono.addActionListener(this);
        txttelefono.setText("");
        panel.add(txttelefono);
        
        label=new JLabel("Email Cliente");
        label.setBounds(950,335,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtemail=new JTextField();
        txtemail.setBounds(950,380,180,20);
        txtemail.addKeyListener(this);
        txtemail.addActionListener(this);
        txtemail.setText("");
        panel.add(txtemail);
        
        label=new JLabel("Productos a Facturar");
        label.setBounds(200,520,180,50);//x,y,ancho,alto
        label.setFont(font2);
        panel.add(label);
        //Tabla de Multiples Productos
        model3 = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        
        //Agregar las columnas al Model
        for(int i=0;i<columnas3.length;i++){
        	model3.addColumn(columnas3[i]);
        }
              
        tabla3=new JTable(model3);

        tabla3.addMouseListener(this);
        //Redimensionar las columnas del Model
        TableColumnModel columnModel3 = tabla3.getColumnModel();
        columnModel3.getColumn(0).setPreferredWidth(5);
        columnModel3.getColumn(1).setPreferredWidth(50);
        columnModel3.getColumn(2).setPreferredWidth(150);
        columnModel3.getColumn(3).setPreferredWidth(30);
        columnModel3.getColumn(4).setPreferredWidth(30);
        columnModel3.getColumn(5).setPreferredWidth(30);
        columnModel3.getColumn(6).setPreferredWidth(30);
        /*TableColumnModel c3=tabla3.getColumnModel();
        c3.removeColumn(c3.getColumn(6));*/
        
        JScrollPane scr3=new JScrollPane(tabla3);
        scr3.setBounds(10,555,850,150);
        panel.add(scr3);
        
        label=new JLabel("Forma de Pago");label.setForeground(Color.BLACK);
		label.setBounds(900,430,120,20);
		panel.add(label);
		rbFormaPago[0] = new JRadioButton("Contado Efectivo");
		rbFormaPago[1] = new JRadioButton("Contado Tarjeta");
		rbFormaPago[2] = new JRadioButton("Credito");
		rbFormaPago[0].setBounds(990,410,120,20);
		rbFormaPago[1].setBounds(990,430,120,20);
		rbFormaPago[2].setBounds(990,450,100,20);
		panel.add(rbFormaPago[0]);
		panel.add(rbFormaPago[1]);
		panel.add(rbFormaPago[2]);
		rbFormaPago[0].setOpaque(false);
		rbFormaPago[1].setOpaque(false);
		rbFormaPago[2].setOpaque(false);
		rbFormaPago[0].setForeground(Color.BLACK);
		rbFormaPago[1].setForeground(Color.BLACK);
		rbFormaPago[2].setForeground(Color.BLACK);
		
		grupo.add(rbFormaPago[0]);
		grupo.add(rbFormaPago[1]);
		grupo.add(rbFormaPago[2]);
        
        label=new JLabel("SubTotal");
        label.setBounds(900,455,100,50);
        label.setForeground(Color.BLACK);
        panel.add(label);
        
        txtSubtotal=new JTextField();
        txtSubtotal.setEditable(true);
        txtSubtotal.setBounds(965,470,100,20);
        txtSubtotal.addKeyListener(this);
        txtSubtotal.addActionListener(this);
        panel.add(txtSubtotal);
        txtSubtotal.setEditable(false);
        
        label=new JLabel("Descuento");
        label.setBounds(900,485,100,50);
        label.setForeground(Color.BLACK);
        panel.add(label);
        
        txtdescuento=new JTextField();
        txtdescuento.setEditable(true);
        txtdescuento.setBounds(965,500,100,20);
        txtdescuento.addKeyListener(this);
        txtdescuento.addActionListener(this);
        panel.add(txtdescuento);
        txtdescuento.setEditable(false);
        
        label=new JLabel("ISV");
        label.setBounds(900,515,100,50);
        label.setForeground(Color.BLACK);
        panel.add(label);
        
        txtISV=new JTextField();
        txtISV.setEditable(true);
        txtISV.setBounds(965,530,100,20);
        txtISV.addKeyListener(this);
        txtISV.addActionListener(this);
        panel.add(txtISV);
        txtISV.setEditable(false);
        
        label=new JLabel("Total");
        label.setBounds(900,545,100,50);
        label.setForeground(Color.BLACK);
        panel.add(label);
        
        txtTotal=new JTextField();
        txtTotal.setEditable(true);
        txtTotal.setBounds(965,560,100,20);
        txtTotal.addKeyListener(this);
        txtTotal.addActionListener(this);
        panel.add(txtTotal);
        txtTotal.setEditable(false);
        
        btnagregar=new JButton("Agregar");
        btnagregar.setBounds(950,610,90,30);
        panel.add(btnagregar);
        btnagregar.addActionListener(this);

        btnvender=new JButton("Vender");
        btnvender.setBounds(950,660,90,30);
        panel.add(btnvender);
        btnvender.addActionListener(this);
        
        label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("imagenes/fondo.jpg"));
		panel.add(label);
    }
    
    public void refrescar(){
    	model.setRowCount(0);
    }
    public void refrescarCarrito(){
    	model3.setRowCount(0);
    }
    public void refrescarcliente(){
    	model2.setRowCount(0);
    }
    
    public void actionPerformed(ActionEvent evt){
    	if(evt.getSource()==btnagregar){
    		if(txtnombre.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,"Seleccione algun Producto ");
    		}
    		else{
    			if(txtcantidad.getText().isEmpty()){
    				txtcantidad.requestFocus();
    				JOptionPane.showMessageDialog(null,"Coloque la Cantidad a Comprar");
    			}
    			else{
    				if(Integer.parseInt(txtcantidad.getText()) <= maximo){
    					//double pc = Double.parseDouble(txtprecio.getText().toString());
        			    //double pv = (pc+(pc*((Double.parseDouble(txtganancia.getText().toString())/100))));
        			    //guardar(txtcodigo.getText(),txtnombre.getText(),txtcantidad.getText(),txtpeso.getText(),String.valueOf(pc),String.valueOf(pv),txtganancia.getText());
        			    model3.addRow(new String[]{id,txtcodigo.getText(),txtnombre.getText(),txtcantidad.getText(),txtpeso.getText(),txtprecio.getText(),imp,desc});
        			    totalizar();
        			    refrescar();
        			    validartabla("");
    				}
    				else{
    					JOptionPane.showMessageDialog(null,"La Cantidad Maxima es "+maximo);
    				}
    			}
    		}
    	}
    	if(evt.getSource()==btnvender){
    		if(txtnombrecliente.getText().isEmpty()){
    			txtnombrecliente.requestFocus();
    		}
    		else{
    			if(txtrtn.getText().isEmpty()){
    				txtrtn.requestFocus();
    			}
    			else{
    				if(txttelefono.getText().isEmpty()){
    	    			txttelefono.requestFocus();
    				}
    				else{
    					if(txtemail.getText().isEmpty()){
        	    			txtemail.requestFocus();
        				}
        				else{
        					if(model3.getRowCount()==0){
        						JOptionPane.showMessageDialog(null,"Debe Ingresar Producto o Productos al Carrito");
        					}
        					else{
        						String d1="",d2="",d3="",d4="",d6="",d7="",d8="";
        						int pos=1;
        						for(int i=0;i<model3.getRowCount();i++){
        							for(int j=0;j<model3.getColumnCount();j++){
        								if(j==0){d1=model3.getValueAt(i,j).toString();}//ID
        								if(j==1){d2=model3.getValueAt(i,j).toString(); buscarproducto(d2);}//Codigo
        								if(j==2){d3=model3.getValueAt(i,j).toString();}//Producto
        								if(j==3){d4=model3.getValueAt(i,j).toString();}//Cantidad
        								if(j==5){d6=model3.getValueAt(i,j).toString();}//Precio Venta
        								if(j==6){d7=model3.getValueAt(i,j).toString();}//ISV
        								if(j==7){d8=model3.getValueAt(i,j).toString();}//Desc.
        							}
        							if(pos<=model3.getRowCount()){
        								if(rbFormaPago[0].isSelected() || rbFormaPago[1].isSelected()){
        									int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Guardar la Nueva Transaccion?","Confirmación",JOptionPane.YES_NO_OPTION);
        									if(res == JOptionPane.YES_OPTION){
        										if(rbFormaPago[0].isSelected()){
        											modificar(d2,(Integer.parseInt(canti)-Integer.parseInt(d4)));
        											if(pos==1){
        												guardartrans("Contado","Efectivo","Cancelado");
        												JOptionPane.showMessageDialog(null,"Transaccion");
        											}
        											double vis=Double.parseDouble(d6)*Double.parseDouble(d7);
        											double vdes=Double.parseDouble(d6)*Double.parseDouble(d8);
        											double pretotal=((Double.parseDouble(d6)*(Double.parseDouble(d4)))+vis-vdes);
        											guardartransdet(pos,d1,d4,d4,Double.parseDouble(d6),pretotal,vis,vdes);
        											JOptionPane.showMessageDialog(null,"Transaccion Det "+pos);
        		       			 				}			       			 	
        										else if(rbFormaPago[1].isSelected()){
        											modificar(d2,(Integer.parseInt(canti)-Integer.parseInt(d4)));
        											if(pos==1){
        												guardartrans("Contado","Tarjeta","Cancelado");
        												JOptionPane.showMessageDialog(null,"Transaccion");
        											}
        											double vis=Double.parseDouble(d6)*Double.parseDouble(d7);
        											double vdes=Double.parseDouble(d6)*Double.parseDouble(d8);
        											double pretotal=((Double.parseDouble(d6)*(Double.parseDouble(d4)))+vis-vdes);
        											guardartransdet(pos,d1,d4,d4,Double.parseDouble(d6),pretotal,vis,vdes);
        											JOptionPane.showMessageDialog(null,"Transaccion Det "+pos);
        		       			 				}
        										else if(rbFormaPago[2].isSelected()){
        											modificar(d2,(Integer.parseInt(canti)-Integer.parseInt(d4)));
        											if(pos==1){
        												guardartrans("Credito",rbFormaPago[2].getText(),"Pendiente");
        												JOptionPane.showMessageDialog(null,"Transaccion");
        											}
        											double vis=Double.parseDouble(d6)*Double.parseDouble(d7);
        											double vdes=Double.parseDouble(d6)*Double.parseDouble(d8);
        											double pretotal=((Double.parseDouble(d6)*(Double.parseDouble(d4)))+vis-vdes);
        											guardartransdet(pos,d1,d4,d4,Double.parseDouble(d6),pretotal,vis,vdes);
        											JOptionPane.showMessageDialog(null,"Transaccion Det "+pos);
        		       			 				}
        									}
        									else{}
        								}
        								else{
        									JOptionPane.showMessageDialog(null,"Debe Seleccionar alguna Forma de Pago","Campo Obligatorio",0);
        									rbFormaPago[0].requestFocus();
        								}
        							}
        							pos++;
        						}
        					}
        					refrescar();
        					validartabla("");
        					refrescarCarrito();
        					fact="FAC-00"+contador("FAC");
        				}
    				}
    			}
    		}
    	}
    }
    
    void totalizar(){
    	double subtotal=0,descu=0,isv=0,total=0;
	    for(int i=0;i<model3.getRowCount();i++){
	    	String pc=model3.getValueAt(i,3)+"";
            String ptv=model3.getValueAt(i,5)+"";
            String pti=model3.getValueAt(i,6)+"";
            String pde=model3.getValueAt(i,7)+"";
            //total+=Double.parseDouble(pt);
            
            subtotal+=(Double.parseDouble(pc)*Double.parseDouble(ptv));
            descu+=(Double.parseDouble(pc)*Double.parseDouble(pde));
    	    isv+=(Double.parseDouble(pc)*(Double.parseDouble(ptv)*Double.parseDouble(pti)));
        }
	    total+=subtotal+isv-descu;
	    txtSubtotal.setText(dosdig.format(subtotal));
	    txtdescuento.setText(dosdig.format(descu));
	    txtISV.setText(dosdig.format(isv));
        txtTotal.setText(dosdig.format(total));
    }
    
    public void validarCliente(String b){
        String rtn="",nom="",cod="";
        //model2.addRow(new String[]{"CARGILL","01011990252451"});
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from vista_cliente where cliente_rtn like'%"+b+"%' or cliente_nombre like'%"+b+"%' group by cliente_nombre asc;");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				rtn=rs.getString(3);
				dir=rs.getString(4);
				tel=rs.getString(5);
				email=rs.getString(6);
				model2.addRow(new String[]{cod,nom,rtn});
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public boolean buscarCliente(String b){
        boolean encontro=false;
    	String cod="",nom="",valor="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from vista_cliente where cliente_rtn='"+b+"';");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				valor=rs.getString(3);
				dir=rs.getString(4);
				tel=rs.getString(5);
				email=rs.getString(6);
			}
			con.close();
		}
		catch(Exception exp){}
        if(cod.equals(b)){
        	encontro=true;
        	return encontro;
        }
        else{
        	encontro=false;
        }
        return encontro;
    }
    
    public void validartabla(String b){
        String id="",cod="",nom="",stock="",peso="",pcos="",pven="",cat="",imp="",prov="",gan="",des="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from vista_det_productos where producto_descripcion like'%"+b+"%' or producto_codigobarra like'%"+b+"%' group by producto_descripcion asc;");
			while(rs.next()){
				id=rs.getString(1);
				cod=rs.getString(2);
				nom=rs.getString(3);
				stock=rs.getString(4);
				peso=rs.getString(5);
				pcos=rs.getString(6);
				pven=rs.getString(7);
				cat=rs.getString(8);
				imp=rs.getString(9);
				prov=rs.getString(10);
				gan=rs.getString(11);
				des=rs.getString(12);
				model.addRow(new String[]{id,cod,nom,stock,peso,pcos,pven,imp,cat,prov,gan,des});
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public boolean validarproducto(String b){
        boolean encontro=false;
    	String cod="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_producto where producto_codigobarra='"+b+"';");
			while(rs.next()){
				//id=rs.getString(1);
				cod=rs.getString(2);
			}
			con.close();
		}
		catch(Exception exp){}
        if(cod.equals(b)){
        	encontro=true;
        	return encontro;
        }
        else{
        	encontro=false;
        }
        return encontro;
    }
    
    public boolean buscarproducto(String b){
        boolean encontro=false;
    	String cod="",can="",codcat="",codimp="",codprov="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_producto where producto_codigobarra='"+b+"';");
			while(rs.next()){
				id=rs.getString(1);
				cod=rs.getString(2);
				can=rs.getString(4);
				codimp=rs.getString(9);
				canti=can;
				impid=codimp;
			}
			con.close();
		}
		catch(Exception exp){}
        if(cod.equals(b)){
        	encontro=true;
        	return encontro;
        }
        else{
        	encontro=false;
        }
        return encontro;
    }
    
    public boolean buscarcategoria(String b){
        boolean encontro=false;
    	String cod="",nom="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_categoria where categoria_nombre='"+b+"';");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				categoria.setSelectedItem(nom);
			}
			con.close();
		}
		catch(Exception exp){}
        if(cod.equals(b)){
        	encontro=true;
        	return encontro;
        }
        else{
        	encontro=false;
        }
        return encontro;
    }
    
    public boolean buscarimpuesto(String b){
        boolean encontro=false;
    	String cod="",nom="",valor="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_impuesto where impuesto_valor='"+b+"';");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				valor=rs.getString(3);
				impuesto.setSelectedItem(nom);
			}
			con.close();
		}
		catch(Exception exp){}
        if(cod.equals(b)){
        	encontro=true;
        	return encontro;
        }
        else{
        	encontro=false;
        }
        return encontro;
    }
    
    public int contador(String tipo){
		int conteo=1;
        String codigo="",nom="",cl="",fp="",nota="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select count(transacciones_codigo) from tbl_transacciones where transacciones_codigo like'%"+tipo+"%';");
			while(rs.next()){
				conteo+=Integer.parseInt(rs.getString(1));
			}
			con.close();
		}
		catch(Exception exp){
		}
        return conteo;
    }
    
    public Date fecha(){
		Date hoy=null;
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select now();");
			while(rs.next()){
				hoy=rs.getDate(1);
			}
			con.close();
		}
		catch(Exception exp){
		}
        return hoy;
    }
    
    public void modificar(String cod,int stock){
		Llenado modificarusu = new Llenado();
		modificarusu.insertar("UPDATE tbl_producto SET producto_stock='"+stock+"' WHERE producto_codigobarra='"+cod+"';");
	}
    
    public void guardartrans(String ref,String tipo,String status){
    	Llenado guardar = new Llenado();
    	guardar.insertar("INSERT INTO tbl_transacciones (transacciones_codigo,"
				+ "transacciones_fecha,"
				+ "transacciones_cliente,"
				+ "transacciones_total,"
				+ "transacciones_referencia,"
				+ "transacciones_tipo_pago,"
				+ "transacciones_estado) "
				+"VALUES ('"+fact+"','"+fecha()+"','"+txtrtn.getText()+"','"+txtTotal.getText()+"','"+ref+"','"+tipo+"','"+status+"')");
    }
    
    public void guardartransdet(int pos,String prod,String resta,String cantidad,double totalunidad,double total,double impisv,double descuento){
		Llenado guardar = new Llenado();
		guardar.insertar("INSERT INTO tbl_transaccion_detalle (transacciones_codigo,"
				+ "transaccion_detalle_posicion,"
				+ "producto_codigo,"
				+ "transaccion_detalle_resta_unidad,"
				+ "transaccion_detalle_cantidad,"
				+ "transaccion_detalle_total_unidad,"
				+ "transaccion_detalle_total,"
				+ "transaccion_detalle_isv,"
				+ "transaccion_detalle_desc)"
				+" VALUES ('"+fact+"','"+pos+"','"+prod+"','"+resta+"','"+cantidad+"','"+totalunidad+"','"+total+"','"+impisv+"','"+descuento+"');");
	}
    
    public void keyReleased(KeyEvent evt){
    	if(evt.getSource()==txtbuscarpalabra){
    		refrescar();
        	validartabla(txtbuscarpalabra.getText());
    	}
    	if(evt.getSource()==txtbuscarcliente){
    		refrescarcliente();
        	validarCliente(txtbuscarcliente.getText());
    	}
    }
    
    public void keyPressed(KeyEvent evt){}
    
    public void keyTyped(KeyEvent evt){
    	if(evt.getSource()==txtbuscarpalabra){
        	char c = evt.getKeyChar();
	        if((Character.isDigit(c) || Character.isLetter(c) || Character.isSpace(c)) && txtbuscarpalabra.getText().length()<30){}
	        else{
	        	evt.consume();
	        }
        }
    	if(evt.getSource()==txtcantidad){
        	char c = evt.getKeyChar();
	        if(Character.isDigit(c) && txtcantidad.getText().length()<5){}
	        else{
	        	evt.consume();
	        }
        }
    }
    
    public void mouseClicked(MouseEvent evt){
    	if(evt.getSource()==tabla){
    		int fila=tabla.getSelectedRow();
            int col=tabla.getSelectedColumn();
            id=model.getValueAt(fila,0).toString();
            String codigo=model.getValueAt(fila,1).toString();
            String nombre=model.getValueAt(fila,2).toString();
            String cantidad=model.getValueAt(fila,3).toString();
            String peso=model.getValueAt(fila,4).toString();
            String pc=model.getValueAt(fila,5).toString();
            String pv=model.getValueAt(fila,6).toString();
            String impu=model.getValueAt(fila,7).toString();
            String cat=model.getValueAt(fila,8).toString();
            String prov=model.getValueAt(fila,9).toString();
            desc=model.getValueAt(fila,11).toString();
            //JOptionPane.showMessageDialog(null,model.getValueAt(fila,11).toString());
            txtcodigo.setText(codigo);
            txtnombre.setText(nombre);
            maximo=Integer.parseInt(cantidad);
            txtpeso.setText(peso);
            txtprecio.setText(pv);
            imp=impu;
            buscarimpuesto(imp);
            buscarproducto(codigo);
    	}
    	if(evt.getSource()==tabla2){
    		int fila=tabla2.getSelectedRow();
            int col=tabla2.getSelectedColumn();
            idcliente=model2.getValueAt(fila,0).toString();
            String nombre=model2.getValueAt(fila,1).toString();
            String rtn=model2.getValueAt(fila,2).toString();
            
            buscarCliente(idcliente);
            txtcodigo.setText(idcliente);
            txtnombrecliente.setText(nombre);
            txtrtn.setText(rtn);
            txtdireccion.setText(dir);
            txttelefono.setText(tel);
            txtemail.setText(email);
    	}
        if(evt.getSource()==tabla3){
        	int input = JOptionPane.showConfirmDialog(null, "Desea Eliminar este Producto de la Carreta?");
        	if(input==0){
        		int fila=tabla3.getSelectedRow();
            	model3.removeRow(fila);
            	totalizar();
        	}
        	else{}
        }
    }
	
    public void mousePressed(MouseEvent evt){}
    
    public void mouseReleased(MouseEvent evt){}
    
    public void mouseEntered(MouseEvent evt){}
    
    public void mouseExited(MouseEvent evt){}
}