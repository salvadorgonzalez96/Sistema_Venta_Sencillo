import java.sql.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.text.NumberFormat;

public class Manten_Deducciones extends JDialog implements ActionListener,ListSelectionListener{
    JButton btnguardar,btnnew,btnmod,btnrest,btnel;
    JLabel lbltitulo,label;
    JTextField txtnombre,txtdescripcion;
    JComboBox cmbforma;
    JList lista,ListaId;
    Llenado l=new Llenado();
    DefaultListModel model=new DefaultListModel(),Ids=new DefaultListModel();
    String idus="",user="";
    
    void close(){
		if(true){
			//new Main();
			System.exit(0);
			dispose();
		}
	}
        
    public Manten_Deducciones(String id, String us){
        setTitle("Mantenimiento de Deducciones");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		idus=id;
		user=us;
		
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(193,205,205));
        
		int xx=220,yy=70;
		    
		label=new JLabel();
		TitledBorder titled = new TitledBorder("Datos de Deduccion");
		titled.setTitleColor(Color.BLUE);
		label.setBorder(titled);
		label.setBounds(200,yy,450,130);
		label.setForeground(Color.BLACK);
		getContentPane().add(label);
		
		label=new JLabel("Nombre");label.setForeground(Color.BLACK);label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtnombre=new JTextField();getContentPane().add(txtnombre);
		txtnombre.setBounds(label.getX()+label.getWidth(),yy,150,20);
		
		label=new JLabel("Puesto");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		cmbforma=new JComboBox();
		cmbforma.addItem("Seleccionar Forma");cmbforma.addItem("Monto");cmbforma.addItem("Porcentaje");
		cmbforma.setBounds(label.getX()+label.getWidth(),yy,150,20);
		getContentPane().add(cmbforma);
		
		label=new JLabel("Descripcion");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtdescripcion=new JTextField();getContentPane().add(txtdescripcion);
		txtdescripcion.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		lista=new JList(model);lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		JScrollPane scr1=new JScrollPane(lista);
		scr1.setBounds(30,70,170,200);getContentPane().add(scr1);
		lista.setEnabled(false);
		
		ListaId=new JList(Ids);ListaId.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListaId.addListSelectionListener(this);
		JScrollPane scr2=new JScrollPane(ListaId);
		scr2.setBounds(30,70,170,200);getContentPane().add(scr2);
		scr2.setVisible(false);
		
		l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion;",Ids);
        l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion;",model);
		
		btnnew=new JButton("Nuevo");
		btnnew.setToolTipText("Agregar Nuevo Usuario");
		btnnew.addActionListener(this);
		btnnew.setBackground(Color.WHITE);
		btnnew.setBounds(200,25,120,25);
		getContentPane().add(btnnew);
			
		btnmod=new JButton("Modificar");
		btnmod.setBackground(Color.WHITE);
		btnmod.setToolTipText("Modificar Usuario");
		btnmod.addActionListener(this);
		btnmod.setBounds(320,25,120,25);
		getContentPane().add(btnmod);
			
		btnel=new JButton("Eliminar");
		btnel.setBackground(Color.WHITE);
		btnel.setToolTipText("Desabilitar Usuario");
		btnel.addActionListener(this);
		btnel.setBounds(440,25,120,25);
		getContentPane().add(btnel);
			
		btnrest=new JButton("Restaurar");
		btnrest.setBackground(Color.WHITE);
		btnrest.setToolTipText("Restaurar Usuario");
		btnrest.addActionListener(this);
		btnrest.setBounds(560,25,120,25);
		getContentPane().add(btnrest);    
		
		btnguardar=new JButton("GUARDAR");
		btnguardar.setForeground(Color.BLACK);
		btnguardar.setBounds(230,220,120,50);
		btnguardar.setBackground(Color.WHITE);
		btnguardar.setEnabled(true);
		btnguardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		                if(e.getActionCommand().equals("GUARDAR")){
		                    if(txtnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Cedula","Campo Obligatorio",0);       
		                        txtnombre.requestFocus();
		                    }
		                    else if(txtdescripcion.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Nombre","Campo Obligatorio",0);
		                        txtdescripcion.requestFocus();
		                    }     
		                    else if(((String) cmbforma.getSelectedItem()).isEmpty()){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Categoria","Campo Obligatorio",0);              
		                        cmbforma.requestFocus();
		                    }
		                    else{
		            			if(cmbforma.getSelectedItem().equals("Seleccionar Puesto")){
		            				JOptionPane.showMessageDialog(null, "Porfavor elija un Departamento");
		            				cmbforma.requestFocus();
		            			}
		                    else{
		                    	//JOptionPane.showMessageDialog(null, validarid(txtcedula.getText()));
			                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Guardar a:\n"+txtnombre.getText()+"\n"+
			                        		txtdescripcion.getText()+"\n"+
			                        		cmbforma.getSelectedItem().toString()+"\nComo Nueva Deduccion?","Confirmación",JOptionPane.YES_NO_OPTION);
			                        if(res == JOptionPane.YES_OPTION){
			                            guardar(txtnombre.getText(),txtdescripcion.getText());
			                            //JOptionPane.showMessageDialog(null,"Usuario "+txtuser.getText()+" Guardado Satisfactoriamente","Guardado",1);
			                            Limpiar();
			                            l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion;",Ids);
			                            l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion;",model);
			                        }
		                    }
		                    }
		                }
		                else if(e.getActionCommand().equals("MODIFICAR")){
		                	if(txtnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar Nombre Deduccion","Campo Obligatorio",0);                
		                        txtnombre.requestFocus();
		                    }
		                    else if(txtdescripcion.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar Descripcion","Campo Obligatorio",0);              
		                        txtdescripcion.requestFocus();
		                    }
		                    else if(cmbforma.getSelectedItem().toString().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Tipo","Campo Obligatorio",0);              
		                        cmbforma.requestFocus();
		                    }
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Modificar a:\n"+txtdescripcion.getText()+"\n"+
		                        		cmbforma.getSelectedItem().toString()+
		                        "\nComo Usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                        	modificar(txtnombre.getText(),txtdescripcion.getText());
		                        	Limpiar();
		                        	l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='habilitado';",Ids);
		                            l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='habilitado';",model);
		                        }
		                    }
		                }
		                else if(e.getActionCommand().equals("ELIMINAR")){
		                	eliminar(txtnombre.getText(),txtdescripcion.getText());
                        	Limpiar();
                        	l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='habilitado';",Ids);
                            l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='habilitado';",model);
		                }
		                else if(e.getActionCommand().equals("RESTAURAR")){
		                	restaurar(txtnombre.getText(),txtdescripcion.getText());
                        	Limpiar();
                        	l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='deshabilitado';",Ids);
                            l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='deshabilitado';",model);
		                }
		            }});
			getContentPane().add(btnguardar);
		
		addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){}
			public void windowClosing(WindowEvent e){dispose();}
		});
		//validaracceso(user);
		setSize(700,400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenX = screen.width,screenY = screen.height;
		setLocation((screenX-this.getWidth())/2,((screenY-this.getHeight())/2)-10);
		setVisible(true);
		setResizable(false);
	}
    
	public void actionPerformed(ActionEvent e){
	    if(e.getActionCommand().equals("Limpiar"))Limpiar();
	    
	    else if(e.getActionCommand().equals("Nuevo")){
		        Limpiar();
		        habilitar(true);
		        lista.setEnabled(false);
		        l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='habilitado';",Ids);
                l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='habilitado';",model);
		        btnguardar.setText("GUARDAR");
		    }
		    else if(e.getActionCommand().equals("Modificar")){
		        Limpiar();
		        habilitar(true);
		        lista.setEnabled(true);
		        l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='habilitado';",Ids);
                l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='habilitado';",model);
		        btnguardar.setText("MODIFICAR");
		    }
		    else if(e.getActionCommand().equals("Eliminar")){
		        Limpiar();
		        habilitar(false);
		        btnguardar.setText("ELIMINAR");
		        lista.setEnabled(true);
		        l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='habilitado';",Ids);
                l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='habilitado';",model);
		        Limpiar();btnguardar.setEnabled(true);
		    }
		    else if(e.getActionCommand().equals("Restaurar")){
		        Limpiar();
		        lista.setEnabled(true);
		        l.llenado_jlistid("SELECT deduccion_codigo FROM tbl_deduccion where deduccion_estado='deshabilitado';",Ids);
                l.llenado_jlistid("SELECT deduccion_nombre FROM tbl_deduccion where deduccion_estado='deshabilitado';",model);
		        habilitar(false);
		        btnguardar.setText("RESTAURAR");
		        Limpiar();btnguardar.setEnabled(true);
		    }
		}
		
		public void Limpiar(){
		    txtnombre.setText("");txtdescripcion.setText("");
		    cmbforma.setSelectedIndex(0);
		}
		
		public void habilitar(boolean cambio){
			txtnombre.setEditable(cambio);
			txtdescripcion.setEditable(cambio);
			cmbforma.setEnabled(cambio);
		}
		
		public boolean validardeduccion(int usuario) {
	        boolean continua=false;
	        String cod="";
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select * from tbl_deduccion where deduccion_nombre='"+model.get(usuario)+"';");
				while(rs.next()){
					cod=rs.getString(1);
					String nom=rs.getString(2);
					String des=rs.getString(3);
					String form=rs.getString(4);
					txtnombre.setText(nom);
					txtdescripcion.setText(des);
					cmbforma.setSelectedItem(form);
				}
				con.close();
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
			}
	        if(cod.equals(usuario)){
	        	//JOptionPane.showMessageDialog(null, "SE HA VALIDADO");
	        	continua=true;
	        }
	        return continua;
	    }
		
		public String codigo(int nombre) {
	        String estado="";
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select deduccion_codigo from tbl_deduccion where deduccion_codigo='"+Ids.get(nombre)+"';");
				while(rs.next()){
					estado=rs.getString(1);
				}
				con.close();
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
			}
	        return estado;
	    }
		
		public void guardar(String nombre, String descrip){
			Llenado guardardedu = new Llenado();
			guardardedu.insertar(" INSERT INTO tbl_deduccion (deduccion_codigo,deduccion_nombre,deduccion_descripcion,deduccion_forma,deduccion_estado)"+
								" VALUES ('0','"+txtnombre.getText()+"','"+txtdescripcion.getText()+"','"+cmbforma.getSelectedItem().toString()+"','habilitado')");
		}
		
		public void modificar(String nombre, String descrip){
			Llenado modificardedu = new Llenado();
			modificardedu.insertar("UPDATE tbl_deduccion SET deduccion_nombre='"+nombre+"', deduccion_descripcion='"+descrip+
									"', deduccion_forma='"+cmbforma.getSelectedItem().toString()+"',deduccion_estado='habilitado' WHERE deduccion_codigo='"+codigo(lista.getSelectedIndex())+"';");
		}

		public void eliminar(String nombre, String descrip){
			Llenado eliminardedu = new Llenado();
			eliminardedu.insertar("UPDATE tbl_deduccion SET deduccion_estado='deshabilitado' WHERE deduccion_codigo='"+codigo(lista.getSelectedIndex())+"';");
		}
		
		public void restaurar(String nombre, String descrip){
			Llenado restaurardedu= new Llenado();
			restaurardedu.insertar("UPDATE tbl_deduccion SET deduccion_estado='habilitado' WHERE deduccion_codigo='"+codigo(lista.getSelectedIndex())+"';");
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//JOptionPane.showMessageDialog(null, lista.getSelectedValues());
			if(validardeduccion(lista.getSelectedIndex())){
				//JOptionPane.showMessageDialog(null, lista.getSelectedValues());
			}
		}
		
		/*public static void main(String args[]){
			new Manten_Deducciones("1","admin");
		}*/
}