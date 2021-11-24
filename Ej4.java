/*
Utiliza la interfaz PreparedStatement para visualizar el APELLIDO, SALARIO y
OFICIO de los empleados de un departamento cuyo valor se recibe desde los
argumentos del main(). Visualiza también el nombre del departamento.
 */
package AD_T2_A_PrepareStatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vespertino
 */
public class Ej4 {

    public static void main(String[] args) {
        try {
            //conexiones
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");

            // recuperar argumentos de main
            String apellido = args[0]; // num. departamento
            String salario = args[1]; // nombre
            String oficio = args[2]; // localidad

            // construir orden INSERT
            String sql = "SELECT * from empleados where apellido = ? and salario = ? and oficio = ? ";
           

            System.out.println(sql);
            //preparar sentencia
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //introduces los datos en las interrogaciones
            sentencia.setString(1, apellido);
            sentencia.setFloat(2, Float.parseFloat(salario));
            sentencia.setString(3, oficio);


            try {
                ResultSet rs = sentencia.executeQuery(); //es Query es para hacer consultas select
                // Nos recoremos los objetos de la coleccion obtenida en la sql
                while (rs.next()) 
                    System.out.println(rs.getString("apellido")+ " " +rs.getFloat("salario")+ " "+ rs.getString("oficio"));
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

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// fin de main

}
