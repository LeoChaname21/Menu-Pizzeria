package visualizar;
import Vista.*;
import Controlador.*;

public class Visualizar {
    
    
    public static VisualizarFrm fm;
    public static Controlador controlmenu;

 
    public static void main(String[] args) {
       
       fm =  new VisualizarFrm();
      controlmenu = new Controlador(fm);   

    }
    
 
    
}
