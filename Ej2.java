/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AD_T2_A_PrepareStatment;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //CONEXION A SQLLITE	       
        Class.forName("org.sqlite.JDBC");
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");

        //Sentencia SQL
        String sql = "SELECT empleados.apellido, departamentos.dnombre, empleados.salario from empleados inner join departamentos on \n"
                + "empleados.dept_no = departamentos.dept_no where     \n"
                + "empleados.salario = (select max(empleados.salario) from empleados);";
        //Crea una sentencia
        Statement sentencia = conexion.createStatement();
        //Booleano para ejecutar el comando
        boolean valor = sentencia.execute(sql);

        //Ejecuta la sql
        if (valor) {
            //Guarda los resultados en "rs"
            ResultSet rs = sentencia.getResultSet();
            while (rs.next()) {
                //muestra los datos
                System.out.printf("%s, %s, %s %n",
                        rs.getString(1), rs.getString(2), rs.getString(3));
            }
            //cerrar datos leidos
            rs.close();
        } else {
            //filas afectadas
            int f = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas:%d %n", f);
        }

        //CERRAR CONEXIONES
        sentencia.close();
        conexion.close();
    }//main
}
