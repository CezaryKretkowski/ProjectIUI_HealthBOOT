package com.example.ProjectIUI_HealthBOOT.DBHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBHelper {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void initializeTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS RecordStored (" +
                "id SERIAL PRIMARY KEY," +
                "idLekarz VARCHAR(100)," +
                "Nazwa VARCHAR(100))");
        System.out.println("Tabela została utworzona lub już istnieje.");
    }

    public void insertData(String IdLekarz, String fileName) {
        jdbcTemplate.update("INSERT INTO RecordStored (IDLekarz, Nazwa) VALUES (?, ?)", IdLekarz, fileName);
        System.out.println("Dane zostały dodane pomyślnie.");
    }

    public void updateData(int id, String name, int age) {
        jdbcTemplate.update("UPDATE tabela SET name = ?, age = ? WHERE id = ?", name, age, id);
        System.out.println("Dane zostały zaktualizowane pomyślnie.");
    }

    public void selectData() {
        jdbcTemplate.query("SELECT * FROM tabela", (resultSet, rowNum) ->
                "ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age")
        ).forEach(System.out::println);
    }
}
