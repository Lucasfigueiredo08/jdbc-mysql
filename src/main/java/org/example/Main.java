package org.example;

import org.example.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        conn = DB.getConnection();
        try {
            /**
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl Purple");
            st.setString(2, "carl@example.com");
            st.setDate(3, new java.sql.Date((sdf.parse("22/04/1985").getTime())));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4); */

            st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);

            int rowAffected = st.executeUpdate();

            if(rowAffected > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                while (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}