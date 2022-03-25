package Cinema;

import Controller.Connect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin{
    private Controller.Admin ca;
    String mail, password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
