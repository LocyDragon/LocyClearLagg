package com.locydragon.lagg.async.sync;

public class SyncInteger {
	private int num = 0;
	public int get() {
		return this.num;
	}

	public synchronized int incrementAndGet() {
		num++;
		return num;
	}

	public synchronized void set(int i) {
		this.num = i;
	}
}
