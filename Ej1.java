/*
Tomando como base el programa que ilustra los pasos de funcionamiento de
JDBC obtén el APELLIDO, OFICIO y SALARIO de los empleados del departamento
10.
 */
package AD_T2_A_PrepareStatment;

import java.sql.*;

public class Ej1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //CONEXION A SQLITE 	       
        Class.forName("org.sqlite.JDBC");
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");

        //Declaramos la select en un string
        String sql = "SELECT apellido,oficio,salario from empleados inner join departamentos on \n"
                + "empleados.dept_no = departamentos.dept_no where empleados.dept_no = 30;";
        //Declaramos la sentencia, y lo conectamos con la conexion
        Statement sentencia = conexion.createStatement();
        //Hace un boolen para ejecutar la sentencia
        boolean valor = sentencia.execute(sql);
        //compruebas si se ha ejecutado bien o mal
        if (valor) {
            //te devuelve los datos en "rs"
            ResultSet rs = sentencia.getResultSet();
            while (rs.next()) {
                //muestra los datos obtenidos
                System.out.printf("%s, %s, %s %n",
                        rs.getString(1), rs.getString(2), rs.getString(3));
            }
            //cierra el resultado
            rs.close();
        } else {
            //filas afectadas
            int f = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas:%d %n", f);
        }

        //CERRAR LAS CONCEXIONS
        sentencia.close();
        conexion.close();
    }
}
