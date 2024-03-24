package org.example;

import org.example.db.DB;

public class Main {
    public static void main(String[] args) {
        var conn = DB.getConnection();

        DB.closeConnection();
    }
}