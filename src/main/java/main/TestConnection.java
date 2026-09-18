package main;

import java.sql.Connection;

import util.DataBaseConnection;

public class TestConnection {
	
	public static void main(String[] args) {
		Connection connection=DataBaseConnection.getConnection();
		System.out.println(connection);
	}
}
