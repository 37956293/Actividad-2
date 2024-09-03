/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class Cliente extends Persona {
    private String NIT;
    private int id;
    Conexion cn;
public Cliente (){}
    public Cliente(int id,String NIT, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.NIT = NIT;   
    }
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

// vamos hacer el crud
    @Override
    public void crear (){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO clentes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNIT());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            int executar = parametro.executeUpdate();
            System.out.println("ingreso exitoso prra......" + Integer.toString(executar));
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("Error en crear:" + ex.getMessage());
        }
    }
    @Override
       public DefaultTableModel leer(){
           DefaultTableModel tabla = new DefaultTableModel ();
           try{
               cn = new Conexion();
               cn.abrir_conexion();
               String query = "select * from clentes";
               ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
               
               String encabezado [] = {"id","nit","nombres","apellidos","direccion","telefono","fecha_nacimiento"};
               tabla.setColumnIdentifiers(encabezado);
               String datos[]=new String [7];
               while(consulta.next()){
                   datos[0] = consulta.getString("id_cliente");
                   datos[1] = consulta.getString("nit");
                   datos[2] = consulta.getString("nombres");
                   datos[3] = consulta.getString("apellidos");
                   datos[4] = consulta.getString("direccion");
                   datos[5] = consulta.getString("telefono");
                   datos[6] = consulta.getString("fecha_nacimiento");
                   tabla.addRow(datos);
               }
               cn.cerrar_conexion();
           }catch(SQLException ex){
               cn.cerrar_conexion();
               System.out.println("Error en leer: " + ex.getMessage());
           }
           return tabla;
       }
        
    @Override
    public void actualizar(){
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE clentes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE `id_cliente` = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNIT());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7,getId());
            int executar = parametro.executeUpdate();
            System.out.println("MODIFICACION EXITOSA:" + Integer.toString(executar));
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("ERROR EN ACTUALIZAR:" + ex.getMessage());
        }
}  
    @Override
    public void borrar(){
      try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "delete from clentes WHERE id_cliente = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            
            parametro.setInt(1,getId());
            int executar = parametro.executeUpdate();
            System.out.println("ELIMINACION EXITOSA:" + Integer.toString(executar));
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("ERROR EN BORRAR:" + ex.getMessage());
        }  
    }
}  