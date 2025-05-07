package BasesDeDatos;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import javax.swing.*;

public class AgregarRegistro implements ActionListener {
    private JTextField[] campos;

    public AgregarRegistro(JTextField[] campos) {
        this.campos = campos;
    }

/**
     * Método para agregar un registro a la base de datos.
     * Se conecta a la base de datos SQLite y ejecuta una sentencia SQL para insertar un nuevo registro.
     * 
     * @param evento El evento de acción que desencadena la inserción del registro.
     * @throws SQLException Si ocurre un error al conectar a la base de datos o al ejecutar la sentencia SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase del controlador JDBC.
     * @throws InstantiationException Si ocurre un error al instanciar la clase del controlador JDBC.
     * @throws IllegalAccessException Si no se puede acceder a la clase del controlador JDBC.
     * @throws NoSuchMethodException Si no se encuentra el método especificado en la clase del controlador JDBC.
     * @throws InvocationTargetException Si ocurre un error al invocar el método especificado en la clase del controlador JDBC.
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL.
     * @throws Exception Si ocurre un error inesperado.
     * @throws Throwable Si ocurre un error inesperado.
     * @throws Error Si ocurre un error inesperado.
     * @throws Throwable Si ocurre un error inesperado.
     * 
     * PreparedStatement sirve para ejecutar sentencias SQL precompiladas y parametrizadas.
     * Se utiliza para evitar inyecciones SQL y mejorar el rendimiento al reutilizar la misma sentencia SQL con diferentes parámetros.
     * PreparedStatement es una interfaz de Java que permite ejecutar sentencias SQL precompiladas y parametrizadas.
     * Se utiliza para evitar inyecciones SQL y mejorar el rendimiento al reutilizar la misma sentencia SQL con diferentes parámetros.
     * En este caso, se utiliza para insertar un nuevo registro en la tabla "addresses" de la base de datos SQLite.
     * La sentencia SQL tiene 10 parámetros (?), que se reemplazarán por los valores de los campos de texto.
     * Los valores de los campos de texto se obtienen mediante un bucle que recorre el array "campos" y se asignan a los parámetros de la sentencia SQL.
     * El método setString() se utiliza para establecer el valor de cada parámetro en la sentencia SQL.
     * El método executeUpdate() se utiliza para ejecutar la sentencia SQL y obtener el número de filas afectadas.
     * Si se insertó al menos una fila, se muestra un mensaje de éxito.
     * Si no se insertó ninguna fila, se muestra un mensaje de error.
     * El bloque try-with-resources se utiliza para cerrar automáticamente la conexión a la base de datos y el PreparedStatement después de su uso.
   
     */


    @Override
    public void actionPerformed(ActionEvent evento) {
        //Para conocer la ruta de la base de datos 
        System.out.println("Ruta de base de datos: " + new java.io.File("direcciones.db").getAbsolutePath());
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:direcciones.db")) {
           System.out.println("Conexión exitosa a la base de datos");
            String sql = "INSERT INTO addresses (firstname, lastname, address, city, stateorprovince, postalcode, country, emailaddress, homephone)" +
                          " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


                     
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                for (int i = 0; i < campos.length; i++) {
                    stmt.setString(i + 1, campos[i].getText());
                }

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar el registro");
                }
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar registro: " + e.getMessage());
        }
    }
}