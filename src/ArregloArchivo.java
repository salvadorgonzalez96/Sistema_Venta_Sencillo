public class ArregloArchivo
{
	String arr[];
	public ArregloArchivo(){
		arr=new String[0];
	}
	
	public void agregar(String n){
		String tmp[]=new String[arr.length+1];
		for(int i=0;i<arr.length;i++){
			tmp[i]=arr[i];
		}
		arr=tmp;
	}
	
	public String ret_info_S(String arch,int busindice, int retindice, String bus){
		arr=new Archivo(arch).toString().split("\n");
		for(int i=0;i<arr.length;i++){
			String inter[]=arr[i].split(";");
			if(inter[busindice].equalsIgnoreCase(bus))return inter[retindice];
		}
		return "";
	}
	
	public boolean bus_linea(String arch,String buslinea){
		arr=new Archivo(arch).toString().split("\n");
		for(int i=1;i<arr.length;i++){
			if(arr[i].trim().equals(buslinea)){
				return true;
			}
		}
		return false;
	}
	
	public boolean bus_user(String arch,String buslinea){
		try{
			Archivo a = new Archivo(arch);
			String cadena=a.toString();
			
			String palabras="";
			String ar[]=cadena.split("\n");
	        for(int i=0;i<ar.length;i++){
	            String interno[]=ar[i].split(";");
	            palabras=ar[i].toString();
	            if( (interno[0]+";"+interno[1]+";").equals(buslinea) ){
	            	return true;
	            }
	        }
		}catch(Exception exp){
			System.out.println("No encontro en el Archivo");
		}
		return false;
	}
	
	public boolean bus_cone(String arch,int n,String buslinea){
		try{
			Archivo a = new Archivo(arch);
			String cadena=a.toString();
			
			String palabras="";
			String ar[]=cadena.split("\n");
			if(n<ar.length){
		        for(int i=0;i<ar.length;i++){
		            String interno[]=ar[i].split(";");
		            palabras=ar[i].toString();
		            if( (interno[0]+";"+interno[1]+";").equals(buslinea) ){
		            	return true;
		            }
		        }
			}
		}catch(Exception exp){
			System.out.println("No encontro en el Archivo Conexion");
		}
		return false;
	}
}