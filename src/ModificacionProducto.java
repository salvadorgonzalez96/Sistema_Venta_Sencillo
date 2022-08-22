import java.awt.BorderLayout;
import java.awt.Graphics2D;
import javax.swing.*;

import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.table.*;

public class ModificacionProducto extends JPanel implements ActionListener,KeyListener,MouseListener
{
	JPanel panel;
    JTextArea text;
    DefaultTableModel model;
    JTable tabla;
    //String columnas[]={"Empleado","Deduccion","Valor",};
    String columnas[]={"ID","Codigo","Producto","Cantidad","Precio","Impuesto","Categoria"};
    int tam[]={40,150,100,100,100,100};
    
    JTextField txtcantidad,txtdescuento,txtprecio,txtimpuesto,txtsubtotal,txttotal,txtdescuentototalt,txtusuario;
    JComboBox producto;
    JComboBox cliente;
    JButton btnagregar;
    JButton btnfactura;
    double descuento=0;
    double precio,impuesto,cantidad,subtotal;
    double subtotaltotal,impuestototal,descuentototal,total;
    String fila[]=new String[0];
    DecimalFormat Dos=new DecimalFormat("0.00");
    String estado="Habilitado";
    String datos="";
    
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
        JLabel label=new JLabel("Ingreso de Producto");
        label.setBounds(70,-19,150,80);//x,y,ancho,alto
        panel.add(label);
        
        label=new JLabel("Usuario");
        label.setBounds(600,40,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtusuario=new JTextField();
        txtusuario.setEditable(false);
        txtusuario.setBounds(660,60,80,20);//x,y,ancho,alto
        txtusuario.setText(Sesion.us);
        //txtusuario.setForeground(Color.black);
        txtusuario.addKeyListener(this);
        txtusuario.addActionListener(this);
        panel.add(txtusuario);
        
        
        label=new JLabel("Cantidad");
        label.setBounds(10,80,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtcantidad=new JTextField();
        txtcantidad.setEditable(true);
        txtcantidad.setBounds(70,90,80,20);//x,y,ancho,alto
        //txtcantidad.setForeground(Color.black);
        txtcantidad.addKeyListener(this);
        txtcantidad.addActionListener(this);
        panel.add(txtcantidad);
         
        
        label=new JLabel("Producto");
        label.setBounds(160,80,100,50);//x,y,ancho,alto
        panel.add(label);
       
        producto=new JComboBox();
        producto.setBounds(220,90,100,30);
        producto.addActionListener(this);
        panel.add(producto);

        
        label=new JLabel("Cliente");
        label.setBounds(600,80,100,50);//x,y,ancho,alto
        panel.add(label);
       
        cliente=new JComboBox();
        cliente.setBounds(670,90,100,30);
        cliente.addActionListener(this);
        panel.add(cliente);
        //llenarcomboclien();
        
        txtprecio=new JTextField();
        txtprecio.setEditable(false);
        txtprecio.setVisible(false);
        txtprecio.setBounds(310,90,120,20);//x,y,ancho,alto
        //txtprecio.setForeground(Color.black);
        txtprecio.addKeyListener(this);
        txtprecio.addActionListener(this);
        panel.add(txtprecio);   
         
        
        label=new JLabel("Descuento");
        label.setBounds(340,80,100,50);//x,y,ancho,alto
        panel.add(label);
        txtdescuentototalt=new JTextField();
        txtdescuentototalt.setEditable(true);
        txtdescuentototalt.setBounds(720,300,120,20);//x,y,ancho,alto
        //txtdescuentototalt.setForeground(Color.black);
        txtdescuentototalt.addKeyListener(this);
        txtdescuentototalt.addActionListener(this);
        panel.add(txtdescuentototalt);
        
        label=new JLabel("Subtotal");
        label.setBounds(630,190,100,50);//x,y,ancho,alto
        panel.add(label);
        txtsubtotal=new JTextField();
        txtsubtotal.setEditable(true);
        txtsubtotal.setBounds(720,200,120,20);//x,y,ancho,alto
        //txtsubtotal.setForeground(Color.black);
        txtsubtotal.addKeyListener(this);
        txtsubtotal.addActionListener(this);
        panel.add(txtsubtotal);   
        
        label=new JLabel("Impuesto");
        label.setBounds(630,240,100,50);//x,y,ancho,alto
        panel.add(label);
        txtimpuesto=new JTextField();
        txtimpuesto.setEditable(true);
        txtimpuesto.setBounds(720,250,120,20);//x,y,ancho,alto
        //txtimpuesto.setForeground(Color.black);
        txtimpuesto.addKeyListener(this);
        txtimpuesto.addActionListener(this);
        panel.add(txtimpuesto);
        
        label=new JLabel("Descuento");
        label.setBounds(630,290,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtdescuento=new JTextField();
        txtdescuento.setEditable(true);
        txtdescuento.setBounds(410,90,120,20);//x,y,ancho,alto
        //txtdescuento.setForeground(Color.black);
        txtdescuento.addKeyListener(this);
        txtdescuento.addActionListener(this);
        panel.add(txtdescuento);
        
        label=new JLabel("Total");
        label.setBounds(630,340,100,50);//x,y,ancho,alto
        panel.add(label);
        txttotal=new JTextField();
        txttotal.setEditable(true);
        txttotal.setBounds(720,350,120,20);//x,y,ancho,alto
        //txttotal.setForeground(Color.black);
        txttotal.addKeyListener(this);
        txttotal.addActionListener(this);
        panel.add(txttotal);
        
        //llenarcombo();
        
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
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(140);
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(50);
        /*TableColumnModel c=tabla.getColumnModel();
        c.removeColumn(c.getColumn(5));*/
        
        JScrollPane scr=new JScrollPane(tabla);
        scr.setBounds(20,200,600,300);
        panel.add(scr);
         //
        btnagregar=new JButton("AGREGAR");
        btnagregar.setBounds(500,120,90,30);//x,y,ancho,alto
        panel.add(btnagregar);
        btnagregar.addActionListener(this);
        
        btnfactura=new JButton("facturar");
        btnfactura.setBounds(720,400,90,30);//x,y,ancho,alto
        panel.add(btnfactura);
        btnfactura.addActionListener(this);
        
        label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("imagenes/fondo.jpg"));
		panel.add(label);
    }
    
    public void actionPerformed(ActionEvent evt){
    	
    }
    
    public void keyReleased(KeyEvent evt){
        if(evt.getSource()==txtdescuento){
            if(txtdescuento.getText().isEmpty()){
             //txtdescuento.setText("0.00");
            }
        }
        if(txtcantidad.getText().isEmpty()){
        	//txtcantidad.setText("0.00");
        }
    }
    
    public void keyPressed(KeyEvent evt){}
    
    public void keyTyped(KeyEvent evt){
       if(evt.getSource()==txtcantidad){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtcantidad.getText().length()<6){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtdescuento){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtdescuento.getText().length()<6){}
           else{
              evt.consume();
           }
       }
    }
    
    public void mouseClicked(MouseEvent evt){}
	
    public void mousePressed(MouseEvent evt){}
    
    public void mouseReleased(MouseEvent evt){}
    
    public void mouseEntered(MouseEvent evt){}
    
    public void mouseExited(MouseEvent evt){}
}