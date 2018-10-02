package com.locydragon.lagg.async;

import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;

public class AsyncThreadMonitor extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20 * 1000);
				int amountBefore = Ache.loadThreads.size() + Ache.threadCountExtra.get();
				for (int i = 0;i < Ache.loadThreads.size();i++) {
					Thread nowThread = (Thread)Ache.loadThreads.toArray()[i];
					if (!nowThread.isAlive()) {
						Ache.loadThreads.remove(i);
					}
				}
				Ache.threadCountExtra.set(0);
				int amountAfter = Ache.loadThreads.size();
				LaggLogger.info("Thread add to stack amount: "+amountBefore+" per 20 seconds.");
				LaggLogger.info("Now, "+amountAfter+" threads is alive.");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}
