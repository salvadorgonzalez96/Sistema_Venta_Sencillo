import java.io.*;
import javax.swing.JOptionPane;
public class Archivo
{
    String nombre;
    int salario;
    public Archivo(String nombArch){//Constructor
		try{
			File app=new File(nombArch);
			nombre=app.getCanonicalPath();				
			//JOptionPane.showMessageDialog(null,nombre);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"No se Encuentra Archivo de Fallas");
		}
	}
    
    public int length(String nomb){
		String cadena="";
		int cont=0;
		String linea="";
		try{
			File app=new File(nomb);
			cadena=toString();
		}
		catch(Exception exp){
			JOptionPane.showMessageDialog(null,"No se Encuentra Archivo de Fallas");
		}
		
		String ar[]=cadena.split("\n");
        for(int i=0;i<ar.length;i++){
            String interno[]=ar[i].split(";");
            for(int j=0; j<interno.length; j++){
            	if(i==0){
            		cont++;
            		//JOptionPane.showMessageDialog(null, "B-"+cont+"\n"+ar.length);
            		return ar.length;
            	}
            }
        }
        
        return cont;
	}
    
	public String toString(){//Retorna todo el archivo en una sola cadena
		String cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();		
		try{
			entrada=new FileReader(nombre);
			int c;
			while((c=entrada.read())!=-1){
				cadena+=(char)c;
			}
		}
		catch(Exception ex){System.out.println("Error al Cargar");}
		
		return cadena;
	}
	
	public void guardarstring(String ultima) throws IOException{//Guarda una nueva linea
		String lineas[]=toString().split("\n");
		PrintWriter g=new PrintWriter(nombre);
		g.flush();
		for(int i=0;i<=lineas.length-1;i++){
			//System.out.println(lineas[i]);
			g.write(lineas[i]);
			g.println();
		}
		g.write(ultima);
		g.println();
		g.close();
	}
	
	public void guardararreglo(String lineas[]) throws IOException{//Guarda un nuevo arreglo
		//String lineas[]=TraeLineas();
		PrintWriter g=new PrintWriter(nombre);
		g.flush();
		for(int i=0;i<=lineas.length-1;i++){
			//System.out.println(lineas[i]);
			g.write(lineas[i]);
			g.println();
		}
		//g.write(ultima);
		//g.println();
		g.close();
	}
}