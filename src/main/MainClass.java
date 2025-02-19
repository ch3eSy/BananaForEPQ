package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {

	public static void main(String[] args) {
		new Menu();
	     // load the sqlite-JDBC driver using the current class loader

	}

	public static void startGame() {
		new Game();
	}

}
