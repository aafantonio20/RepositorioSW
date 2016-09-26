/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métodolargo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aafan
 */
public class Conexion {
    private Connection conn;
    String rpta = "";
    
    public void hacerConexion(String nombreBD) throws SQLException{
        
             Connection conn = 
                        DriverManager.getConnection("jdbc:derby:"+ nombreBD +
                                ";create=true;user=is2;password=123");            
                    
    }
    
    
    public void ejecutarConexion(String sql ) throws SQLException{
         Statement statement = conn.createStatement();
            if (statement.execute(sql)){
                    // Entrega un resulset
                    ResultSet rs = statement.getResultSet();
                    while(rs.next()){
                        int numColumns = rs.getMetaData().getColumnCount();
                        //String[] valoresColumna = new String[numColumns];
                        for (int i=0; i<numColumns; i++){
                            String nombreColumna = 
                                    rs.getMetaData().getColumnName(i+1);
                            int tipoColumna = 
                                    rs.getMetaData().getColumnType(i+1);
                            
                            String valorColumna = "";
                            if (tipoColumna == java.sql.Types.VARCHAR){
                                valorColumna = rs.getString(nombreColumna);
                            }else if (tipoColumna == java.sql.Types.INTEGER){
                                valorColumna = 
                                        String.valueOf(rs.getInt(nombreColumna));
                            }
                            //valoresColumna[i] = valorColumna;
                            System.out.print(valorColumna + "\t\t");
                        }
                        System.out.println();
                    }
                }else{
                 rpta = "Se ejecutó la sentencia correctamente";
                 System.out.println(rpta);
            }      
    } 
    
}
