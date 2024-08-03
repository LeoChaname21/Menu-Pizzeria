
package Controlador;
//libreria

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import javax.swing.JFrame;


public class Controlador implements ActionListener{
    
    VisualizarFrm vista;
    public Controlador(VisualizarFrm fm){
        vista = fm;
        //fm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setLocationRelativeTo(null);
        fm.setVisible(true);
        fm.setTitle("Visualizar Catalogo de Pizzas");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}



