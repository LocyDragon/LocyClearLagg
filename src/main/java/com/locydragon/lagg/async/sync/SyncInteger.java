package com.locydragon.lagg.async.sync;

import java.util.concurrent.atomic.LongAdder;

public class SyncInteger {
	private LongAdder num = new LongAdder();
	public int get() {
		return this.num.intValue();
	}

	public int incrementAndGet() {
		this.num.increment();
		return this.num.intValue();
	}

	public void set(int i) {
		if (i == 0) {
			this.num.reset();
		} else {
			throw new UnsupportedOperationException("Not done yet.");
		}
	}
}
