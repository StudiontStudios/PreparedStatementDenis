/*
Utiliza la interfaz PreparedStatement, para visualizar ahora el salario medio y el
número de empleados del departamento. Si el departamento no existe en la tabla
departamentos visualiza un mensaje indicándolo. Utiliza la clase DecimalFormat
para dar formato al salario.
 */
package AD_T2_A_PrepareStatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author Vespertino
 */
public class Ej5 {

    public static void main(String[] args) {
        try {
            //conexiones
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E://sqlite-tools-win32-x86-3360000//ejemplo.db", "", "");

            // recuperar argumentos de main
            String dept_no = args[0]; // num. departament
            //formato decimal
            DecimalFormat formato = new DecimalFormat ("#.##");
            
              

            if (comprobarDep(dept_no, conexion) == true) {

                // construir orden select
                String sql = "SELECT count(dept_no)as numero,sum(salario)/count(dept_no)as medio from empleados where dept_no = ?";

                System.out.println(sql);
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                //suustituimos la interrogacion
                sentencia.setString(1, dept_no);
                
                try {
                    ResultSet rs = sentencia.executeQuery(); //es Query es para hacer consultas select
                    // Nos recoremos los objetos de la coleccion y muestra los datos
                    while (rs.next()) {
                        System.out.println(rs.getString("numero") + " " +  formato.format(rs.getFloat("medio")));    
                    }
                    rs.close();
                    //errores
                } catch (SQLException e) {
                    System.out.println("HA OCURRIDO UNA EXCEPCI�N:");
                    System.out.println("Mensaje:    " + e.getMessage());
                    System.out.println("SQL estado: " + e.getSQLState());
                    System.out.println("C�d error:  " + e.getErrorCode());
                }
                sentencia.close(); // Cerrar Statement
                conexion.close(); // Cerrar conexi�n
            }else{
                System.out.println("NO EXISTE EL DEPARTAMENTO");
            }
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// fin de main

    //metodo para comprobar un departamento
    private static boolean comprobarDep(String dept_no, Connection conexion) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String sql = "SELECT dept_no from departamentos where dept_no = " + dept_no;
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);
        sentencia.close();
        return valor;
    }
}
