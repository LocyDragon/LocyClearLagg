package com.locydragon.lagg.util.logger;

import com.locydragon.lagg.ClearLagg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LaggLogger {
	public static final String path = ".//plugins//LocyClearLagg//Logs//log.txt";
	public static File ioFile;
	public static ConcurrentLinkedQueue<String> logs = new ConcurrentLinkedQueue<>();
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
		if (ClearLagg.debug) {
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			String dateString = format.format(now);
			msg = "[" + dateString + "] " + msg + "\n";
			logs.add(msg);
			try {
				writer.write(msg);
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		if (onConsole) {
			ClearLagg.instance.getLogger().info(msg);
		}
	}

	public static void info(String msg) {
		info(msg, false);
	}
}
