package com.locydragon.lagg.sql;

import org.bukkit.Location;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
// import java.util.concurrent.locks.ReentrantLock;

public class SqlManager {
	public static Connection conn;
	public static PreparedStatement statement;
	public static ResultSet set;
	public static final Object lockOn = new Object();

	public static void write(int id, Location where) {
		try {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd^HH:mm:ss");
			statement = conn.prepareStatement("insert into item value(?,?,?)");
			synchronized (lockOn) {
				statement.setInt(1, id);
				statement.setString(2, getLoc(where));
				statement.setString(3, format.format(date));
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getLoc(Location loc) {
		StringBuilder sb = new StringBuilder();
		sb = sb.append("[").append(loc.getWorld().getName()).append(",").append(loc.getBlockX())
				.append(",").append(loc.getBlockY()).append(",").append(loc.getBlockZ()).append("]");
		return sb.toString();
	}
}
