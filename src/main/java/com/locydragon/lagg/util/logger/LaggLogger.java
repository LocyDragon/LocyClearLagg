package com.locydragon.lagg.util.logger;

import com.locydragon.lagg.ClearLagg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaggLogger {
	public static final String path = ".//plugins//LocyClearLagg//Logs//log.txt";
	public static File ioFile;
	public static List<String> logs = new ArrayList<>();
	public static FileWriter writer;
	public static final Object object = new Object();
	static {
		ioFile = new File(path);
		if (!ioFile.exists()) {
			ioFile.getParentFile().mkdirs();
		} else {
			ioFile.delete();
		}
		try {
			ioFile.createNewFile();
			writer = new FileWriter(ioFile);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void info(String msg, boolean onConsole) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String dateString = format.format(now);
		msg = "["+dateString+"] " + msg + "\n";
		if (onConsole) {
			ClearLagg.instance.getLogger().info(msg);
		}
		logs.add(msg);
		try {
			writer.write(msg);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void info(String msg) {
		info(msg, false);
	}
}
