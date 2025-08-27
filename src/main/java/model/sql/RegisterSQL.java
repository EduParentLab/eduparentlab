package model.sql;

public class RegisterSQL {
    public static final String INSERT =
        "INSERT INTO user(email, password, nickname, gender, birth, name, phone, cdate, role_num) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?)";
}