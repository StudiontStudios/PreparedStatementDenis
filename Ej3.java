package AD_T2_A_PrepareStatment;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ej3 {

    public static void main(String[] args) {
        try {
            //conexiones
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");
            //fecha actual
            LocalDate actual = LocalDate.now();
            // recuperar argumentos de 
            //50 RUDOFREDO CONSTRUCTOR 7000 1600 250 20
            String emp_no = "60";
            String apellido = "PEPE";
            String oficio = "CONSTRUCTOR";
            String dir = "7050";
            String salario = "1600";
            String comision = "250";
            String dept_no = "20";
            String fecha_alt = "2021-10-13";


            //filtracion de sqls, para luego insertar datos
            if (comprobarDep(dept_no, conexion) == true) {
                if (comprobarNumEmp(emp_no, conexion) == true) {
                    if (comprobarSalario(salario) == true) {
                        if (comprobarDir(dir, conexion) == true) {
                            if (comprobarApellidoYoficio(apellido, oficio) == true) {
                                if (comprobarFecha(fecha_alt) == true) {
                                    //construir orden INSERT	        
                                    String sql = String.format("INSERT INTO empleados VALUES (%s, '%s', '%s', %s,%s,%s, %s, %s)",
                                            emp_no, apellido, oficio, dir, fecha_alt, salario, comision, dept_no);
                                    System.out.println(sql);
                                    Statement sentencia = conexion.createStatement();
                                    int filas = 0;
                                    try {
                                        filas = sentencia.executeUpdate(sql.toString());
                                        System.out.println("Filas afectadas: " + filas);
                                    } catch (SQLException e) {
                                        //e.printStackTrace();
                                        System.out.printf("HA OCURRIDO UNA EXCEPCI�N:%n");
                                        System.out.printf("Mensaje   : %s %n", e.getMessage());
                                        System.out.printf("SQL estado: %s %n", e.getSQLState());
                                        System.out.printf("C�d error : %s %n", e.getErrorCode());
                                    }
                                    sentencia.close(); // Cerrar Statement
                                    conexion.close(); // Cerrar conexi�n
                                }
                            }
                        }
                    }
                }
            }

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// fin de main

    private static boolean comprobarDep(String dept_no, Connection conexion) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String sql = "SELECT dept_no from departamentos where dept_no = " + dept_no;
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);
        sentencia.close();
        return valor;
    }

    private static boolean comprobarNumEmp(String emp_no, Connection conexion) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String sql = "SELECT emp_no from empleados where dept_no = " + emp_no;
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);
        sentencia.close();
        return valor;
    }

    private static boolean comprobarSalario(String salario) throws SQLException {
        if (Integer.parseInt(salario) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean comprobarDir(String dir, Connection conexion) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String sql = "SELECT  emp_no from empleados where emp_no = " + dir;
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);
        sentencia.close();
        return valor;
    }

    private static boolean comprobarApellidoYoficio(String apellido, String oficio) throws SQLException {
        if (apellido == null && oficio == null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean comprobarFecha(String fecha_alt) throws SQLException {
        LocalDate actual = LocalDate.now();
        return fecha_alt.equals(actual.toString());
    }
}// fin de la clase
