package AD_T2_A_PrepareStatment;

import java.sql.*;

public class EjemploExecuteSQLite {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //CONEXION A MYSQL  	       
        Class.forName("org.sqlite.JDBC");
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db","","");

        String sql = "SELECT * FROM departamentos";
        Statement sentencia = conexion.createStatement();
        boolean valor = sentencia.execute(sql);

        if (valor) {
            ResultSet rs = sentencia.getResultSet();
            while (rs.next()) {
                System.out.printf("%d, %s, %s %n",
                        rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        } else {
            int f = sentencia.getUpdateCount();
            System.out.printf("Filas afectadas:%d %n", f);
        }

        sentencia.close();
        conexion.close();
    }//main
}//
