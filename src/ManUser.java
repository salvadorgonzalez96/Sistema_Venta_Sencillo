 
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

public class ManUser extends JDialog implements ActionListener,ListSelectionListener{
    JButton btnguardar,btnlimpiar;//,t,n,x;
    JList lista,ListaId;
    DefaultListModel model=new DefaultListModel(),Ids=new DefaultListModel();
    JLabel lbltitulo,label;//,label,imagen;
    JTextField txtuser;
    JPasswordField txtpass;
    Llenado l=new Llenado();
    int Id=0,iduser;
    String ad,userfijo,bool,user;
    JButton btnnew,btnmod,btnrest,btnel,btnacces;
    String us_id;
    
    void close(){
		//if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
		//		JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
		if(true){
			//System.exit(0);
			new MenuPrincipal(us_id, user);
			dispose();
		}
	}
        
    public ManUser(final String ID, final String names){
        setTitle("Mantenimiento de Usuarios || Usuario: "+names);   
        ImageIcon img = new ImageIcon("imagenes/m.png");
		setIconImage(img.getImage());
        setModal(true);
        us_id = ID;
        user = names;
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(193,205,205));
        
		int xx=220,yy=70;
		    
		label=new JLabel();
		TitledBorder titled = new TitledBorder("Datos de Usuario");label.setBorder(titled);
		label.setBounds(200,yy,520,425);label.setForeground(Color.BLACK);getContentPane().add(label);
		
		label=new JLabel("Usuario");label.setForeground(Color.BLACK);label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtuser=new JTextField();getContentPane().add(txtuser);
		txtuser.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		label=new JLabel("Clave");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtpass=new JPasswordField();getContentPane().add(txtpass);
		txtpass.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		ImageIcon i=new ImageIcon("Images/save.jpg");btnguardar=new JButton("GUARDAR",i);
		btnguardar.setForeground(Color.BLACK);
		btnguardar.setBounds(xx,txtpass.getY()+txtpass.getHeight()+5,120,50);
		btnguardar.setBackground(Color.WHITE);
		btnguardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		                if(e.getActionCommand().equals("GUARDAR")){
		                    if(txtuser.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Usuario","Campo Obligatorio",0);                
		                        txtuser.requestFocus();
		                    }
		                    else if(txtpass.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Clave","Campo Obligatorio",0);              
		                        txtpass.requestFocus();
		                    }       
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Guardar "+txtuser.getText()+
		                        " Como Nuevo Usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                            guardar(txtuser.getText(), txtpass.getText());
		                            //JOptionPane.showMessageDialog(null,"Usuario "+txtuser.getText()+" Guardado Satisfactoriamente","Guardado",1);
		                            Limpiar();
		                            l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
		                            l.llenado_jlistid("SELECT usuario_usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		                        }
		                    }
		                }
		                else if(e.getActionCommand().equals("MODIFICAR")){
		                    if(txtuser.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Usuario","Campo Obligatorio",0);                
		                        txtuser.requestFocus();
		                    }
		                    else if(txtpass.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Clave","Campo Obligatorio",0);              
		                        txtpass.requestFocus();
		                    }
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Modificar "+txtuser.getText()+
		                        " Como Usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                        	modificar(txtuser.getText(), txtpass.getText());
		                        	Limpiar();
		                        	l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
		                            l.llenado_jlistid("SELECT usuario_usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		                        }
		                    }
		                }
		                else if(e.getActionCommand().equals("ELIMINAR")){
		                    if(!txtuser.getText().equals("")){
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Eliminar "+
		                        lista.getSelectedValue()+" Como Usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res==JOptionPane.YES_OPTION){
		                        	
		                        	eliminar(txtuser.getText(), txtpass.getText());
		                            JOptionPane.showMessageDialog(null,"Usuario "+txtuser.getText()+" Fue Eliminado Satisfactoriamente","Eliminado",1);
		                            Limpiar();ShowHide(false);
		                            l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
		                            l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		                        }
		                    }
		                    else
		                    JOptionPane.showMessageDialog(null,"Seleccione Usuario a Eliminar","Error de Selección",0);
		                }
		                else if(e.getActionCommand().equals("RESTAURAR")){
		                    if(!txtuser.getText().equals("")){
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Restaurar "+
		                        lista.getSelectedValue()+" Como Usuario?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res==JOptionPane.YES_OPTION){
		                        	restaurar(txtuser.getText(), txtpass.getText());
		                            JOptionPane.showMessageDialog(null,"Usuario "+txtuser.getText()+" Fue Restaurado Satisfactoriamente","Eliminado",1);
		                            Limpiar();ShowHide(false);
		                            l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
		                            l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		                        }
		                    }
		                    else
		                    JOptionPane.showMessageDialog(null,"Seleccione Usuario a Eliminar","Error de Selección",0);
		                }
		            }});
			getContentPane().add(btnguardar);
			
			btnlimpiar=new JButton("Limpiar",new ImageIcon("Images/limpiar.jpg"));btnlimpiar.setForeground(Color.BLACK);
			btnlimpiar.setBounds(xx+180,btnguardar.getY(),120,50);getContentPane().add(btnlimpiar);
			btnlimpiar.setBackground(Color.WHITE);
			btnlimpiar.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e){
			    	Limpiar();
			    }
			});
			            
			i=new ImageIcon("Images/new.jpg");  
			btnnew=new JButton("Nuevo");
			btnnew.setToolTipText("Agregar Nuevo Usuario");
			btnnew.addActionListener(this);
			btnnew.setBackground(Color.WHITE);
			btnnew.setBounds(200,10,120,20);getContentPane().add(btnnew);
			btnnew.setVisible(false);
			
			i=new ImageIcon("Images/modify.jpg");
			btnmod=new JButton("Modificar",i);
			btnmod.setBackground(Color.WHITE);
			btnmod.setToolTipText("Modificar Usuario");
			btnmod.addActionListener(this);
			btnmod.setBounds(320,10,120,20);getContentPane().add(btnmod);
			btnmod.setVisible(false);
			
			i=new ImageIcon("Images/baja.jpg");
			btnel=new JButton("Eliminar",i);
			btnel.setBackground(Color.WHITE);
			btnel.setToolTipText("Desabilitar Usuario");
			btnel.addActionListener(this);
			btnel.setBounds(440,10,120,20);getContentPane().add(btnel);
			btnel.setVisible(false);
			
			i=new ImageIcon("Images/alta.jpg");
			btnrest=new JButton("Restaurar",i);
			btnrest.setBackground(Color.WHITE);
			btnrest.setToolTipText("Restaurar Usuario");
			btnrest.addActionListener(this);
			btnrest.setBounds(560,10,120,20);getContentPane().add(btnrest);    
			btnrest.setVisible(false);
			
			i=new ImageIcon("Images/alta.jpg");
			btnacces=new JButton("Accesos",i);
			btnacces.setBackground(Color.WHITE);
			btnacces.setToolTipText("Accesos de Usuarios");
			btnacces.addActionListener(this);
			btnacces.setBounds(560,200,120,20);getContentPane().add(btnacces);    
			btnacces.setVisible(false);
			
			lbltitulo=new JLabel("Usuarios");lbltitulo.setBounds(10,45,280,25);
			getContentPane().add(lbltitulo);
			        
			lista=new JList(model);lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.addListSelectionListener(this);JScrollPane scr1=new JScrollPane(lista);
			scr1.setBounds(10,70,180,350);getContentPane().add(scr1);
			lista.enable(false);
			
			ListaId=new JList(Ids);ShowHide(false);
			l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
            l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
			        
			addWindowListener(new WindowAdapter(){
				public void windowOpened(WindowEvent e){}
				public void windowClosing(WindowEvent e){dispose();}
			});
			validaracceso(user);
			setSize(750,550);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int screenX = screen.width,screenY = screen.height;
			setLocation((screenX-this.getWidth())/2,((screenY-this.getHeight())/2)-10);
			setVisible(true);
		}
    
		public void actionPerformed(ActionEvent e){
		    if(e.getActionCommand().equals("Limpiar"))Limpiar();
		    
		    else if(e.getActionCommand().equals("Nuevo")){
		        Limpiar();ShowHide(false);ShowHide(true);
		        lista.enable(false);
		        habilitar(true);
		        btnguardar.setText("GUARDAR");
		        lbltitulo.setText("Usuarios Habilitados");
                l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
                l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
                lista.enable(false);
		    }
		    else if(e.getActionCommand().equals("Modificar")){
		        Limpiar();ShowHide(false);ShowHide(true);
		        lista.enable(true);
		        btnguardar.setText("MODIFICAR");
		        habilitar(true);
		        l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
                l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		        lbltitulo.setText("Usuarios Habilitados");
		        lista.enable(true);
		    }
		    else if(e.getActionCommand().equals("Eliminar")){
		        Limpiar();ShowHide(false);lista.enable(true);
		        ShowHide(true);
		        btnguardar.setText("ELIMINAR");
		        habilitar(false);
		        lbltitulo.setText("Usuarios Habilitados");
		        l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='habilitado'",Ids);
                l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='habilitado'",model);
		        Limpiar();btnguardar.setEnabled(true);
		        lista.enable(true);
		    }
		    else if(e.getActionCommand().equals("Restaurar")){
		        Limpiar();ShowHide(false);lista.enable(true);
		        ShowHide(true);Limpiar();
		        habilitar(false);
		        btnguardar.setText("RESTAURAR");lbltitulo.setText("Usuarios Deshabilitados");
		        l.llenado_jlistid("SELECT usuario_codigo FROM tbl_usuario where usuario_estado='deshabilitado'",Ids);
                l.llenado_jlistid("SELECT usuario_nick FROM tbl_usuario where usuario_estado='deshabilitado'",model);
		        Limpiar();btnguardar.setEnabled(true);
		        lista.enable(true);
		    }
		    else if(e.getActionCommand().equals("Accesos")){
		    	dispose();
		    	new Accesos_Usuarios(us_id,user);
		    }
		}
		
		public void habilitar(boolean cambio){
			txtuser.setEnabled(cambio);
			txtpass.setEnabled(cambio);
		}
		
		public boolean validaracceso(String usuario){
	        boolean continua=false;
			String login1 = "";
			String login2 = "";
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select modulo_codigo from tbl_acceso where usuario_codigo = '"+us_id+"';");
				while(rs.next()){
					login1=rs.getString(1);
					if(login1.equals("00101")){
						btnnew.setVisible(true);
			        	continua=true;
			        }
					if(login1.equals("00102")){
			        	btnmod.setVisible(true);
			        	continua=true;
			        }
					if(login1.equals("00103")){

			        	btnel.setVisible(true);
			        	continua=true;
			        }
					if(login1.equals("00104")){
			        	btnrest.setVisible(true);
			        	continua=true;
			        }
					if(login1.equals("00105")){
			        	btnacces.setVisible(true);
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
		
		public void Limpiar(){
		    txtuser.setText("");txtpass.setText("");
		}
		
		public void ShowHide(boolean b){   
		    txtuser.setEditable(b);txtpass.setEditable(b);
		}
		
		public void guardar(String usuario, String pass){
			Llenado guardarusu = new Llenado();
			guardarusu.insertar(" INSERT INTO tarea2.tbl_usuario (usuario_codigo,usuario_clave,usuario_estado) VALUES ('"+txtuser.getText()+"','"+txtpass.getText()+"', 'habilitado')");
		}
		
		public void modificar(String usuario, String pass){
			Llenado modificarusu = new Llenado();
			modificarusu.insertar("UPDATE tarea2.tbl_usuario SET usuario_clave='"+pass+"' WHERE `usuario_codigo`='"+usuario+"';");
		}

		public void eliminar(String usuario, String pass){
			Llenado eliminarusu = new Llenado();
			eliminarusu.insertar("UPDATE tarea2.tbl_usuario SET usuario_estado='deshabilitado' WHERE usuario_codigo='"+usuario+"';");
		}
		
		public void restaurar(String usuario, String pass){
			Llenado restaurarusu = new Llenado();
			restaurarusu.insertar("UPDATE tarea2.tbl_usuario SET usuario_estado='habilitado' WHERE usuario_codigo='"+usuario+"';");
		}
		
		
		
		@Override
		public void valueChanged(ListSelectionEvent e){
			validarusuario(lista.getSelectedIndex());
		}
		
		public void validarusuario(int ide){
	        String id="",nick="",pass="";
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select usuario_codigo,usuario_nick,usuario_clave from tbl_usuario where usuario_codigo='"+Ids.get(ide)+"';");
				while(rs.next()){
					id=rs.getString(1);
					nick=rs.getString(2);
					pass=rs.getString(3);
					txtuser.setText(nick);
					txtpass.setText(pass);
				}
				con.close();
			}
			catch(Exception exp){}
	    }
}