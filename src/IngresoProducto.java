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

public class IngresoProducto extends JPanel implements ActionListener,KeyListener,MouseListener
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
    String columnas3[]={"Codigo","Producto","Cantidad","Peso","P.Costo","P.Venta","Categoria","Impuesto","Prov.","Ganancia","Desc"};
    DecimalFormat dosdig=new DecimalFormat("0.00");
    JTextField txtcantidad,txtnombre,txtprecio,txtganancia,txtpeso,txtcodigo,txtproveedor,txtrtn,txtbuscarpalabra;   
    JTextField txtSubtotal,txtdescuento,txtISV,txtTotal;
    JComboBox impuesto,impuestoid,categoria,categoriaid;
    JButton btncomprar,btnagregar,btnnuevo;
    JRadioButton rbFormaPago[] = new JRadioButton[3];
    ButtonGroup grupo = new ButtonGroup();
    String idcat="",cat,imp,impid="",canti,id="",idprov="",porcentaje="",descuen="";
    String fact="COM-00"+contador("COM");
    double subtotal=0,impv=0,total=0;
    double isv=0;
    
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
        JLabel label=new JLabel("Ingreso de Producto");
        label.setBounds(30,0,180,80);//x,y,ancho,alto
        label.setFont(font);
        panel.add(label);
        
        label=new JLabel("Busqueda Producto");
        label.setBounds(240,40,150,50);//x,y,ancho,alto
        panel.add(label);
        
        txtbuscarpalabra=new JTextField();
        txtbuscarpalabra.setBounds(360,55,150,20);
        txtbuscarpalabra.addKeyListener(this);
        txtbuscarpalabra.addActionListener(this);
        txtbuscarpalabra.setText("");
        panel.add(txtbuscarpalabra);
        
        btnnuevo=new JButton("Producto Nuevo");
        btnnuevo.setBounds(580,180,150,25);
        panel.add(btnnuevo);
        btnnuevo.addActionListener(this);
        
        label=new JLabel("Usuario:");
        label.setBounds(15,710,100,20);
        panel.add(label);
        
        label=new JLabel(Sesion.us);
        label.setBounds(70,710,100,20);
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
        
        label=new JLabel("Codigo");
        label.setBounds(330,80,150,50);//x,y,ancho,alto
        panel.add(label);
        txtcodigo=new JTextField();
        txtcodigo.setEditable(true);
        txtcodigo.setBounds(375,95,120,20);
        txtcodigo.addKeyListener(this);
        txtcodigo.addActionListener(this);
        panel.add(txtcodigo);
        
        label=new JLabel("Categoria");
        label.setBounds(510,80,100,50);//x,y,ancho,alto
        panel.add(label);
       
        categoria=new JComboBox();
        categoria.setBounds(570,95,120,20);
        categoria.addActionListener(this);
        panel.add(categoria);

        categoriaid=new JComboBox();
        categoriaid.setBounds(240,135,120,20);
        categoriaid.addActionListener(this);
        categoriaid.setVisible(false);
        panel.add(categoriaid);
        
        validarcategorias("");
        categoria.setSelectedIndex(0);
        categoria.getSelectedIndex();
        categoria.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	categoriaid.setSelectedIndex(categoria.getSelectedIndex());
            	categoria.setSelectedIndex(categoria.getSelectedIndex());
                idcat=categoriaid.getSelectedItem().toString();
                cat=categoria.getSelectedItem().toString();
            }
        });
        
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
         
        label=new JLabel("Impuesto ISV");
        label.setBounds(120,120,100,50);//x,y,ancho,alto
        panel.add(label);
       
        impuesto=new JComboBox();
        impuesto.setBounds(200,135,100,20);
        impuesto.addActionListener(this);
        panel.add(impuesto);

        impuestoid=new JComboBox();
        impuestoid.setBounds(240,135,100,20);
        impuestoid.addActionListener(this);
        impuestoid.setVisible(false);
        panel.add(impuestoid);
        
        validarimpuestos("");
        impuesto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	impuestoid.setSelectedIndex(impuesto.getSelectedIndex());
            	impuesto.setSelectedIndex(impuesto.getSelectedIndex());
                impid=impuestoid.getSelectedItem().toString();
                imp=impuesto.getSelectedItem().toString();
            }
        });
        
        label=new JLabel("Precio Costo");
        label.setBounds(310,120,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtprecio=new JTextField();
        txtprecio.setBounds(390,135,60,20);
        txtprecio.addKeyListener(this);
        txtprecio.addActionListener(this);
        panel.add(txtprecio);
        
        label=new JLabel("% Ganancia");
        label.setBounds(470,120,120,50);//x,y,ancho,alto
        panel.add(label);
        
        txtganancia=new JTextField();
        txtganancia.setBounds(540,135,30,20);
        txtganancia.addKeyListener(this);
        txtganancia.addActionListener(this);
        panel.add(txtganancia);
        
        label=new JLabel("Peso");
        label.setBounds(590,120,60,50);//x,y,ancho,alto
        panel.add(label);
        
        txtpeso=new JTextField();
        txtpeso.setBounds(630,135,60,20);
        txtpeso.addKeyListener(this);
        txtpeso.addActionListener(this);
        panel.add(txtpeso);
        label=new JLabel("lbs");
        label.setBounds(695,120,60,50);//x,y,ancho,alto
        panel.add(label);
        
        label=new JLabel("Proveedor");
        label.setBounds(10,160,120,50);//x,y,ancho,alto
        panel.add(label);
        
        txtproveedor=new JTextField();
        txtproveedor.setBounds(75,175,150,20);
        txtproveedor.addKeyListener(this);
        txtproveedor.addActionListener(this);
        txtproveedor.setEditable(false);
        //txtproveedor.setEnabled(false);
        panel.add(txtproveedor);
        
        label=new JLabel("RTN");
        label.setBounds(245,160,50,50);//x,y,ancho,alto
        panel.add(label);
        
        txtrtn=new JTextField();
        txtrtn.setBounds(275,175,115,20);
        txtrtn.addKeyListener(this);
        txtrtn.addActionListener(this);
        txtrtn.setEditable(false);
        //txtrtn.setEnabled(false);
        panel.add(txtrtn);
        
        Font font2 = new Font("",Font.BOLD,15);
        label=new JLabel("Productos en Inventario");
        label.setBounds(200,225,180,50);//x,y,ancho,alto
        label.setFont(font2);
        panel.add(label);
        ///Tabla
        model = new DefaultTableModel(){
        	public boolean isCellEditable(int row, int column) {
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
        c.removeColumn(c.getColumn(10));
        
        JScrollPane scr=new JScrollPane(tabla);
        scr.setBounds(10,260,850,200);
        panel.add(scr);
        validartabla("");
        
        label=new JLabel("Productos a Facturar");
        label.setBounds(200,465,180,50);//x,y,ancho,alto
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
        /*TableColumnModel c3=tabla3.getColumnModel();
        c3.removeColumn(c3.getColumn(5));*/
        
        JScrollPane scr3=new JScrollPane(tabla3);
        scr3.setBounds(10,500,850,150);
        panel.add(scr3);
        
        label=new JLabel("Proveedores");
        label.setBounds(1000,10,100,50);//x,y,ancho,alto
        label.setFont(font2);
        panel.add(label);
        
        //Tabla Proveedor
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
        scr2.setBounds(895,50,280,200);
        panel.add(scr2);
        validarProveedor("");
        
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

        btncomprar=new JButton("Comprar");
        btncomprar.setBounds(950,660,90,30);//x,y,ancho,alto
        panel.add(btncomprar);
        btncomprar.addActionListener(this);
        
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
    
    public void habilitar(boolean x){
    	txtcodigo.setEditable(x);
    	txtnombre.setEditable(x);
    	txtganancia.setEditable(x);
    	txtpeso.setEditable(x);
    	categoria.setEnabled(x);
    	impuesto.setEnabled(x);
    	txtprecio.setEditable(x);
    	tabla2.setEnabled(x);
    	if(x==true){
    		txtcodigo.setText("");
        	txtnombre.setText("");
        	txtganancia.setText("");
        	txtpeso.setText("");
        	txtprecio.setText("");
        	categoria.setSelectedIndex(0);
        	impuesto.setSelectedIndex(0);
    	}
    }
    
    public void actionPerformed(ActionEvent evt){
    	if(evt.getSource()==btnnuevo){
    		habilitar(true);
    	}
    	if(evt.getSource()==btncomprar){
    		if(model3.getRowCount()==0){
    			JOptionPane.showMessageDialog(null,"Debe Ingresar Producto o Productos al Carrito");
    		}
    		else{
    			double pc = Double.parseDouble(txtprecio.getText().toString());
        		double pv = (pc+(pc*((Double.parseDouble(txtganancia.getText().toString())/100))));
        		String d1="",d2="",d3="",d4="",d5="",d6="",d7="",d8="",d9="",d10="",d11="";
        		int pos=1;
        		for(int i=0;i<model3.getRowCount();i++){
        			for(int j=0;j<model3.getColumnCount();j++){
        				if(j==0){d1=model3.getValueAt(i,j).toString();}
        				if(j==1){d2=model3.getValueAt(i,j).toString();}
        				if(j==2){d3=model3.getValueAt(i,j).toString();}
        				if(j==3){d4=model3.getValueAt(i,j).toString();}
        				if(j==4){d5=model3.getValueAt(i,j).toString();}
        				if(j==5){d6=model3.getValueAt(i,j).toString();}
        				if(j==6){d7=model3.getValueAt(i,j).toString();}
    					if(j==7){d8=model3.getValueAt(i,j).toString();}
    					if(j==8){d9=model3.getValueAt(i,j).toString();}
    					if(j==9){d10=model3.getValueAt(i,j).toString();}
    					if(j==1){d11=model3.getValueAt(i,j).toString();}
        			}
        			
        			if(pos<=model3.getRowCount()){
        				if(rbFormaPago[0].isSelected() || rbFormaPago[1].isSelected()){
        					int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Guardar la Nueva Transaccion?","Confirmación",JOptionPane.YES_NO_OPTION);
        					if(res == JOptionPane.YES_OPTION){
		                    	if(rbFormaPago[0].isSelected()){
		                    		guardar(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11);
		                    		JOptionPane.showMessageDialog(null,"Prod");
		                    		if(pos==1){
    	                    			guardartrans("Contado","Efectivo","Cancelado");
    	                    			JOptionPane.showMessageDialog(null,"Transaccion");
    	                    		}
		                    		buscarisv(d8);
		                    		double vdes=Double.parseDouble(d11)*Double.parseDouble(d6);
		                    		double pretotal=(Double.parseDouble(d6)+isv-vdes);
		                    		guardartransdet(pos,d1,d3,d3,Double.parseDouble(d6),pretotal,isv,vdes);
		                    		JOptionPane.showMessageDialog(null,"Transaccion Det "+pos);
			       			 	}			       			 	
			                	else if(rbFormaPago[1].isSelected()){
				       		        guardar(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11);
				       		     JOptionPane.showMessageDialog(null,"Prod");
				       		        if(pos==1){
				       		        	guardartrans("Contado","Tarjeta","Cancelado");
				       		        	JOptionPane.showMessageDialog(null,"Transaccion");
				       		        }
				       		     buscarisv(d8);
		                    		double vdes=Double.parseDouble(d1)*Double.parseDouble(d6);
		                    		double pretotal=(Double.parseDouble(d6)+isv-vdes);
		                    		guardartransdet(pos,d1,d3,d3,Double.parseDouble(d6),pretotal,isv,vdes);
		                    		JOptionPane.showMessageDialog(null,"Transaccion Det "+pos);
			       			 	}
			                	else if(rbFormaPago[2].isSelected()){
				       		        guardar(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11);
				       		     JOptionPane.showMessageDialog(null,"Prod");
    		                		if(pos==1){
    		                			guardartrans("Credito",rbFormaPago[2].getText(),"Pendiente");
    		                			JOptionPane.showMessageDialog(null,"Transaccion");
    		                		}
    		                		buscarisv(d8);
		                    		double vdes=Double.parseDouble(d11)*Double.parseDouble(d6);
		                    		double pretotal=(Double.parseDouble(d6)+isv-vdes);
		                    		guardartransdet(pos,d1,d3,d3,Double.parseDouble(d6),pretotal,isv,vdes);
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
    	}
    	if(evt.getSource()==btnagregar){
    		if(txtnombre.getText().isEmpty()){
    			txtnombre.requestFocus();
    			JOptionPane.showMessageDialog(null,"Escriba el Nombre del Producto");
    		}
    		else{
    			if(txtcantidad.getText().isEmpty()){
    				txtcantidad.requestFocus();
    				JOptionPane.showMessageDialog(null,"Coloque la Cantidad a Comprar");
    			}
    			else{
    				if(txtprecio.getText().isEmpty()){
    					txtprecio.requestFocus();
    					JOptionPane.showMessageDialog(null,"Coloque el Precio al cual esta Comprando el Producto");
    				}
    				else{
    					if(txtganancia.getText().isEmpty()){
    						txtganancia.requestFocus();
    						JOptionPane.showMessageDialog(null,"Coloque el Porcentaje de su Ganancia");
    					}
    					else{
    						if(txtcodigo.getText().isEmpty()){
    							txtcodigo.requestFocus();
    							JOptionPane.showMessageDialog(null,"Ingrese el Codigo del Producto");
    						}
    						else{
    							if(txtproveedor.getText().isEmpty()){
	    							JOptionPane.showMessageDialog(null,"Seleccione un Proveedor");
	    						}
    							else{
    								if(idcat.equals("")){
    									JOptionPane.showMessageDialog(null,"Seleccione una Categoria");
    								}
    								else{
    									if(impid.equals("")){
    										JOptionPane.showMessageDialog(null,"Seleccione un ISV");
    									}
    									else{
    										double pc = Double.parseDouble(txtprecio.getText().toString());
    			    						double pv = (pc+(pc*((Double.parseDouble(txtganancia.getText().toString())/100))));
    			    						//"VALUES ('0','"+cod+"','"+nom+"','"+cant+"','"+p+"','"+pc+"','"+pv+"','"+idcat+"','"+impid+"','"+idprov+"','"+g+"')");
        									//guardar(txtcodigo.getText(),txtnombre.getText(),txtcantidad.getText(),txtpeso.getText(),String.valueOf(pc),String.valueOf(pv),txtganancia.getText());
        									model3.addRow(new String[]{txtcodigo.getText(),txtnombre.getText(),txtcantidad.getText(),txtpeso.getText(),String.valueOf(pc),String.valueOf(pv),idcat,impid,idprov,txtganancia.getText(),"0.0"});
        									totalizar();
        									refrescar();
        				        			validartabla("");
    									}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    void totalizar(){
    	double pd=0;
	    for(int i=0;i<model3.getRowCount();i++){
	    	String pc=model3.getValueAt(i,2)+"";
            String ptv=model3.getValueAt(i,5)+"";
            String pti=model3.getValueAt(i,7)+"";
            String pdes=model3.getValueAt(i,10)+"";
            //total+=Double.parseDouble(pt);
            buscarisv(pti);
            pd+=Double.parseDouble(pdes);
            subtotal+=(Double.parseDouble(pc)*Double.parseDouble(ptv));
    	    impv+=(Double.parseDouble(pc)*(Double.parseDouble(ptv)*isv));
        }
	    total+=subtotal+impv;
	    txtSubtotal.setText(dosdig.format(subtotal));
	    txtISV.setText(dosdig.format(impv));
	    txtdescuento.setText(dosdig.format(pd));
        txtTotal.setText(dosdig.format(total));
    }

    public void validarcategorias(String b){
        String id="",nom="",desc="";
        categoria.addItem("Elegir Categoria");
		categoriaid.addItem("");
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_categoria where categoria_codigo like'%"+b+"%';");
			while(rs.next()){
				id=rs.getString(1);
				nom=rs.getString(2);
				desc=rs.getString(3);
				categoria.addItem(nom);
				categoriaid.addItem(id);
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public void validarimpuestos(String b){
        String id="",nom="",valor="";
        impuesto.addItem("Elegir ISV");
		impuestoid.addItem("");
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_impuesto where impueto_codigo like'%"+b+"%';");
			while(rs.next()){
				id=rs.getString(1);
				nom=rs.getString(2);
				valor=rs.getString(3);
				impuesto.addItem(nom);
				impuestoid.addItem(id);
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public void validartabla(String b){
        String id="",cod="",desc="",stock="",peso="",pcos="",pven="",cat="",imp="",prov="",gan="",descu="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from vista_det_productos where producto_descripcion like'%"+b+"%' or producto_codigobarra like'%"+b+"%' group by producto_descripcion asc;");
			//ResultSet rs=s1.executeQuery("select producto_codigo,producto_codigobarra,producto_descripcion,"
			//+ "producto_stock,producto_precio_unidad,categoria_codigo,impuesto_codigo from vista_det_productos;");
			while(rs.next()){
				id=rs.getString(1);
				cod=rs.getString(2);
				desc=rs.getString(3);
				stock=rs.getString(4);
				peso=rs.getString(5);
				pcos=rs.getString(6);
				pven=rs.getString(7);
				cat=rs.getString(8);
				imp=rs.getString(9);
				prov=rs.getString(10);
				gan=rs.getString(11);
				descu=rs.getString(12);
				model.addRow(new String[]{id,cod,desc,stock,peso,pcos,pven,imp,cat,prov,gan,descu});
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public void validarProveedor(String b){
        String rtn="",nom="",cod="";
        //model2.addRow(new String[]{"CARGILL","01011990252451"});
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_proveedor where proveedor_rtn like'%"+b+"%' or proveedor_nombre like'%"+b+"%' group by proveedor_nombre asc;");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				rtn=rs.getString(3);
				model2.addRow(new String[]{cod,nom,rtn});
			}
			con.close();
		}
		catch(Exception exp){}
    }
    
    public boolean buscarproveedor(String b){
        boolean encontro=false;
    	String cod="",nom="",rtn="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_proveedor where proveedor_nombre='"+b+"';");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				rtn=rs.getString(3);
				txtrtn.setText(rtn);
				idprov=cod;
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
				codcat=rs.getString(8);
				codimp=rs.getString(9);
				codprov=rs.getString(10);
				canti=can;
				idcat=codcat;
				impid=codimp;
				idprov=codprov;
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
				isv=Double.parseDouble(valor);
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
    
    public boolean buscarisv(String b){
        boolean encontro=false;
    	String cod="",nom="",valor="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select * from tbl_impuesto where impueto_codigo='"+b+"';");
			while(rs.next()){
				cod=rs.getString(1);
				nom=rs.getString(2);
				valor=rs.getString(3);
				//impuesto.setSelectedItem(nom);
				isv=Double.parseDouble(valor);
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
    
    public void guardar(String cod,String nom,String cant,String p,String pc,String pv,String ca,String i,String pr,String g,String des){
		Llenado guardar = new Llenado();
		guardar.insertar("INSERT INTO tbl_producto (producto_codigo,"
				+ "producto_codigobarra,"
				+ "producto_descripcion,"
				+ "producto_stock,"
				+ "producto_peso,"
				+ "producto_precio_costo,"
				+ "producto_precio_venta,"
				+ "categoria_codigo,"
				+ "impuesto_codigo,"
				+ "proveedor_codigo,"
				+ "producto_ganancia,"
				+ "producto_descuento)"
				+" VALUES ('0','"+cod+"','"+nom+"','"+cant+"','"+p+"','"+pc+"','"+pv+"','"+ca+"','"+i+"','"+pr+"','"+g+"','0.0');");
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
    
    public void modificar(String cod,String stock){
		Llenado modificarusu = new Llenado();
		modificarusu.insertar("UPDATE tbl_producto SET producto_stock='"+stock+"' WHERE producto_codigobarra='"+cod+"';");
	}
    
    public void keyReleased(KeyEvent evt){
    	if(evt.getSource()==txtbuscarpalabra){
    		refrescar();
        	validartabla(txtbuscarpalabra.getText());
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
    	if(evt.getSource()==txtnombre){
        	char c = evt.getKeyChar();
	        if((Character.isLetter(c) || Character.isDigit(c) || Character.isSpace(c)) && txtnombre.getText().length()<30){}
	        else{
	        	evt.consume();
	        }
        }
    	if(evt.getSource()==txtcodigo){
        	char c = evt.getKeyChar();
	        if((Character.isDigit(c) || Character.isLetter(c)) && txtcodigo.getText().length()<15){}
	        else{
	        	evt.consume();
	        }
        }
    	if(evt.getSource()==txtprecio){
        	char c = evt.getKeyChar();
	        if(Character.isDigit(c) && txtprecio.getText().length()<7){}
	        else{
	        	evt.consume();
	        }
        }
    	if(evt.getSource()==txtganancia){
        	char c = evt.getKeyChar();
	        if(Character.isDigit(c) && txtganancia.getText().length()<3){}
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
            String imp=model.getValueAt(fila,7).toString();
            String cat=model.getValueAt(fila,8).toString();
            String prov=model.getValueAt(fila,9).toString();
            porcentaje=model.getValueAt(fila,10).toString();
            descuen=model.getValueAt(fila,11).toString();
            
            txtcodigo.setText(codigo);
            txtnombre.setText(nombre);
            txtcantidad.setText(cantidad);
            txtpeso.setText(peso);
            txtprecio.setText(pc);
            txtproveedor.setText(prov);
            txtganancia.setText(porcentaje);
            buscarproveedor(prov);
            buscarcategoria(cat);
            buscarimpuesto(imp);
            buscarproducto(codigo);
            habilitar(false);
    	}
        if(evt.getSource()==tabla2){
        	if(tabla2.isEnabled()){
        		int fila=tabla2.getSelectedRow();
                int col=tabla2.getSelectedColumn();
                String identidad=model2.getValueAt(fila,0).toString();
                String palabra=model2.getValueAt(fila,1).toString();
                String rtn=model2.getValueAt(fila,2).toString();
                
                txtproveedor.setText(palabra);
                txtrtn.setText(rtn);
                idprov=identidad;
        	}
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