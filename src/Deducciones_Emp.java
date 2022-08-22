import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Deducciones_Emp extends JDialog implements ActionListener,MouseListener{

	DefaultTableModel model;
    JTable tabla;
    DecimalFormat dosdig=new DecimalFormat("0.00");
    String columnas[]={"Empleado","Deduccion","Valor","Identidad"};
    JButton btnAgregar;
    JComboBox cmbEmpleado,cmbIdEmpleado,cmbDeduccion,cmbIdDeduccion,cmbTipoDeduccion;
    static String idded,tipoded,idemp;
    static double sueldo;
    
    void close(){
		if(true){
			new Main();
			dispose();
		}
	}
    
	public Deducciones_Emp() {
		setTitle("Salario");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
        setFrame();
        setSize(450,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
	}

	public void setFrame(){  
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
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(40);
        TableColumnModel c=tabla.getColumnModel();
        c.removeColumn(c.getColumn(3));
        
        JScrollPane scr=new JScrollPane(tabla);
        scr.setBounds(20,150,400,200);
        getContentPane().add(scr);
        
        JLabel label=new JLabel("Empleado");
		label.setBounds(20,20,120,30);
		getContentPane().add(label);
		
		cmbIdEmpleado = new JComboBox();
		cmbIdEmpleado.setBounds(label.getWidth(),label.getY(),120,20);
		cmbIdEmpleado.addItem("");
		cmbIdEmpleado.setVisible(false);
		
		cmbEmpleado = new JComboBox();
		cmbEmpleado.setBounds(label.getWidth(),label.getY(),200,20);
		cmbEmpleado.addItem("Seleccione Empleado");
		validarEmp();
		cmbEmpleado.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cmbIdEmpleado.setSelectedIndex(cmbEmpleado.getSelectedIndex());
            	idemp=cmbIdEmpleado.getSelectedItem().toString();

            	refrescar();
            	validartabla();
            }
        });
		getContentPane().add(cmbEmpleado);
		
		label=new JLabel("Deduccion");
		label.setBounds(20,80,120,30);
		getContentPane().add(label);
		cmbDeduccion= new JComboBox();
		cmbDeduccion.setBounds(label.getWidth(),label.getY(),120,20);
		cmbDeduccion.addItem("Seleccione Deduccion");
		getContentPane().add(cmbDeduccion);
		
		cmbIdDeduccion= new JComboBox();
		cmbIdDeduccion.setBounds(label.getWidth(),label.getY(),120,20);
		cmbIdDeduccion.addItem("");
		cmbIdDeduccion.setVisible(false);
		
		cmbTipoDeduccion= new JComboBox();
		cmbTipoDeduccion.setBounds(label.getWidth(),label.getY(),120,20);
		cmbTipoDeduccion.addItem("");
		cmbTipoDeduccion.setVisible(false);
		
		cmbDeduccion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cmbIdDeduccion.setSelectedIndex(cmbDeduccion.getSelectedIndex());
            	cmbTipoDeduccion.setSelectedIndex(cmbDeduccion.getSelectedIndex());

                idded=cmbIdDeduccion.getSelectedItem().toString();
                tipoded=cmbTipoDeduccion.getSelectedItem().toString();
            }
        });
        
		validarDed();
		
        btnAgregar=new JButton("Agregar");
        btnAgregar.setToolTipText("Agregar Deduccion a Empleado");
        btnAgregar.addActionListener(this);
        btnAgregar.setBackground(Color.WHITE);
        btnAgregar.setBounds(300,60,120,40);
        getContentPane().add(btnAgregar);
    }
	
	public void mouseClicked(MouseEvent evt){}
	
    public void mousePressed(MouseEvent evt){}
    
    public void mouseReleased(MouseEvent evt){}
    
    public void mouseEntered(MouseEvent evt){}
    
    public void mouseExited(MouseEvent evt){}
	
	public static double pedirDouble(String msg){
		double numero=0;
		boolean seguir=true;
		do{
			try{
				numero=Double.parseDouble(JOptionPane.showInputDialog(msg));
				seguir=false;
			}
			catch(Exception exp){
				JOptionPane.showMessageDialog(null,"Favor Ingresar un Numero");
				seguir=false;
			}
		}while(seguir);
		return numero;
	}
	
	public void validarSal() {
        String sal="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select emp_salario from tbl_emp_salarios where emp_identidad='"+idemp+"';");
			while(rs.next()){
				sal=rs.getString(1);
				sueldo = Double.parseDouble(sal);
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
    }
	
	public boolean validarEmp() {
		boolean continua=false;
        String pnom="",snom="",pape="",sape="",id="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select emp_pnombre, emp_snombre, emp_papellido, emp_sapellido, emp_identidad from tbl_empleado where emp_estado='activo';");
			while(rs.next()){
				pnom=rs.getString(1);
				snom=rs.getString(2);
				pape=rs.getString(3);
				sape=rs.getString(4);
				id=rs.getString(5);
				cmbEmpleado.addItem(pnom+" "+snom+" "+pape+" "+sape);
				cmbIdEmpleado.addItem(id);
				continua=true;
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
        return continua;
    }
	
	public boolean validarDed() {
		boolean continua=false;
        String ded="",tipo="",cod="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select ded_nombre,ded_forma,ded_codigo from tbl_deduccion;");
			while(rs.next()){
				ded=rs.getString(1);
				tipo=rs.getString(2);
				cod=rs.getString(3);
				cmbDeduccion.addItem(ded);
				cmbIdDeduccion.addItem(cod);
				cmbTipoDeduccion.addItem(tipo);
				continua=true;
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
        //JOptionPane.showMessageDialog(null, "SE HA VALIDADO");
        return continua;
    }
	
	public void refrescar(){
		model.setRowCount(0);
	}
	
	public boolean validartabla(){
		boolean continua=false;
        String nom="",dedu="",valor="",id="";
        try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select concat(E.emp_pnombre,' ',emp_snombre,' ',emp_papellido,' ',emp_sapellido) as nombre, D.ded_nombre, "+
			"emp_ded_valor from tbl_empleado_deduccion ED inner join tbl_empleado E on E.emp_identidad=ED.emp_identidad inner join "+
					"tbl_deduccion D on D.ded_codigo=ED.ded_codigo where ED.emp_identidad='"+idemp+"' order by D.ded_codigo;");
			while(rs.next()){
				nom=rs.getString(1);
				dedu=rs.getString(2);
				valor=rs.getString(3);
				model.addRow(new String[]{nom,dedu,valor,idemp});
				continua=true;
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
        //JOptionPane.showMessageDialog(null, "SE HA VALIDADO");
        return continua;
    }
	
	public void guardar(String valor){
		Llenado guardar = new Llenado();
		guardar.insertar("INSERT INTO tbl_empleado_deduccion (emp_identidad,ded_codigo,emp_ded_tipo,emp_ded_valor)"+
							" VALUES ('"+idemp+"','"+idded+"','"+tipoded+"','"+valor+"')");
		model.addRow(new String[]{cmbEmpleado.getSelectedItem().toString(),cmbDeduccion.getSelectedItem().toString(),valor,cmbIdEmpleado.getSelectedItem().toString()});
	}

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==btnAgregar){
        	if(tipoded.equals("Porcentaje")){
        		validarSal();
        		double total=sueldo*( (pedirDouble("Ingrese el porcentaje de la deduccion") )/100);
        		if(total!=0){
        			guardar(String.valueOf(total));
        		}
        	}
        	else{
        		double total=pedirDouble("Ingrese el Monto de la Deduccion");
        		if(total!=0){
        			guardar(String.valueOf(total));
        		}
        	}
        }
    }
    
    /*public static void main(String args[]){
    	new Deducciones_Emp();
    }*/
}
