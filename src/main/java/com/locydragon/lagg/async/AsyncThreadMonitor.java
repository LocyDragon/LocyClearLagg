package com.locydragon.lagg.async;

import com.locydragon.lagg.listeners.ache.Ache;
import com.locydragon.lagg.util.logger.LaggLogger;

public class AsyncThreadMonitor extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20 * 1000);
				int amountBefore = Ache.loadThreads.size();
				for (int i = 0;i < Ache.loadThreads.size();i++) {
					Thread nowThread = Ache.loadThreads.get(i);
					if (!nowThread.isAlive()) {
						Ache.loadThreads.remove(i);
					}
				}
				int amountAfter = Ache.loadThreads.size();
				LaggLogger.info("Thread add to stack amount: "+amountBefore+" per 20 seconds.");
				LaggLogger.info("Now, "+amountAfter+" threads is alive.");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}