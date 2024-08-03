package Crud;
import static Vista.VisualizarFrm.JtblListaProductos;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.ConexionDB;

//import Formatos.Mensajes;

public class CRUDproductos extends ConexionDB{
    
    public CRUDproductos(){ 
        
     
    }
    public static ResultSet rs;
    public static String sql="";
//metodo que inserta en registro a la tabla productos
public static void Lista(){
    DefaultTableModel modelo=new DefaultTableModel();
        sql="select * from productos";
        rs= ConexionDB.getTabla(sql);
        System.out.println(sql);
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Categoria","Tamano","Nombre","Precio","Stock"});
        try{
            while(rs.next())
            {
                modelo.addRow(new Object[]{rs.getString("Codigo"),rs.getString("Categoria"),
                rs.getString("Tamano"),rs.getString("Nombre"),rs.getInt("Precio"),rs.getString("Stock")});
                Vista.VisualizarFrm.JtblListaProductos.setModel(modelo); 
            }
                    
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
}

public static int c=0;
public static void PDFProductos(){
        rs= ConexionDB.getTabla(sql);
        try{
            
            c++;
            String pdfc="C://pdf//CatalogoPizzaPaul"+c+".pdf";
            
                OutputStream file = new FileOutputStream(new File(pdfc));
                Document document = new Document();
                
                PdfWriter.getInstance(document, file);
                
                document.open();
                PdfPTable tabla =new PdfPTable(6);
                Paragraph p= new Paragraph("Catálogo de Productos de Pizzas Paul",FontFactory.getFont("Arial",18,Font.UNDERLINE,BaseColor.RED));
                Paragraph pa= new Paragraph("\n ",FontFactory.getFont("Arial",18));
                    p.setAlignment(Element.ALIGN_CENTER);
                    document.add(p);
                    document.add(pa);
                document.add(new Paragraph(""));
                 
                  float[] mediaCeldas ={2.00f,4.20f,2.80f,4.00f,3.10f,3.50f};
                  
                  tabla.setWidths(mediaCeldas);
                  tabla.addCell(new Paragraph("Cod",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  tabla.addCell(new Paragraph("Categoria",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  tabla.addCell(new Paragraph("Tamaño",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  tabla.addCell(new Paragraph("Nombre",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  tabla.addCell(new Paragraph("Precio",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  tabla.addCell(new Paragraph("Stock",FontFactory.getFont("Arial",14,BaseColor.BLUE)));
                  
                  while(rs.next())
                  {
                  tabla.addCell(new Paragraph(rs.getString("Codigo"),FontFactory.getFont("Arial",12)));
                  tabla.addCell(new Paragraph(rs.getString("Categoria"),FontFactory.getFont("Arial",12)));
                  tabla.addCell(new Paragraph(rs.getString("Tamano"),FontFactory.getFont("Arial",12)));
                  tabla.addCell(new Paragraph(rs.getString("Nombre"),FontFactory.getFont("Arial",12)));
                  tabla.addCell(new Paragraph("S/. "+rs.getInt("Precio"),FontFactory.getFont("Arial",12)));
                  tabla.addCell(new Paragraph(rs.getString("Stock"),FontFactory.getFont("Arial",12)));
                  }
                 
                 document.add(tabla);
                 document.setMargins(0, 0, 0, 0);
                 
                 document.close();
                 JOptionPane.showMessageDialog(null,"Se guardo Correctamente el archivo PDF","PDF",JOptionPane.WARNING_MESSAGE,Vista.VisualizarFrm.PDF);
                 
            // Abrir el PDF automáticamente
            File pdfFile = new File(pdfc);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("La apertura automática de archivos PDF no es compatible en esta plataforma.");
                }
            } else {
                System.out.println("El archivo PDF no fue encontrado.");
            }
                
                
            }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error en el Sistema\n");
            
        }

      
}

    
public static void FiltroCategoria(){

        DefaultTableModel modelo=new DefaultTableModel();
        sql="select * from productos WHERE Categoria = '"+Vista.VisualizarFrm.Cat+"'";
        rs= ConexionDB.getTabla(sql);
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Categoria","Tamano","Nombre","Precio","Stock"});
        try{
            while(rs.next())
            {
                modelo.addRow(new Object[]{rs.getString("Codigo"),rs.getString("Categoria"),
                rs.getString("Tamano"),rs.getString("Nombre"),rs.getInt("Precio"),rs.getString("Stock")}); 
            
            }
            JtblListaProductos.setModel(modelo);           
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
}
public static void FiltroTamaño(){

        DefaultTableModel modelo=new DefaultTableModel();
        sql="select * from productos WHERE Categoria = '"+Vista.VisualizarFrm.Cat+"' AND Tamano = '"+Vista.VisualizarFrm.Tam+"'";
        rs= ConexionDB.getTabla(sql);//select * from productos WHERE Categoria = 'Pizzas y Pasta' AND Tamaño = 'Personal';
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Categoria","Tamano","Nombre","Precio","Stock"});
        try{
            while(rs.next())
            {
                modelo.addRow(new Object[]{rs.getString("Codigo"),rs.getString("Categoria"),
                rs.getString("Tamano"),rs.getString("Nombre"),rs.getInt("Precio"),rs.getString("Stock")}); 
            
            }
            JtblListaProductos.setModel(modelo);           
            }
        catch(Exception e)
        {
            System.out.println(e);
        }

        }
public static void FiltroStock(){
    
        DefaultTableModel modelo=new DefaultTableModel();
        sql="select * from productos WHERE Categoria = '"+Vista.VisualizarFrm.Cat+"' AND Tamano = '"+Vista.VisualizarFrm.Tam+"' AND Stock = '"+Vista.VisualizarFrm.Sto+"'";
        rs= ConexionDB.getTabla(sql);//select * from productos WHERE Categoria = 'Pizzas y Pasta' AND Tamaño = 'Personal' AND Stock = 'Agotado';
        modelo.setColumnIdentifiers(new Object[]{"Codigo","Categoria","Tamano","Nombre","Precio","Stock"});
        try{
            while(rs.next())
            {
                modelo.addRow(new Object[]{rs.getString("Codigo"),rs.getString("Categoria"),
                rs.getString("Tamano"),rs.getString("Nombre"),rs.getInt("Precio"),rs.getString("Stock")}); 
            
            }
            JtblListaProductos.setModel(modelo);           
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }

}
