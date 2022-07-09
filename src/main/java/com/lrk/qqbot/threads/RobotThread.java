package com.lrk.qqbot.threads;

public abstract class RobotThread extends Thread {
    
	protected void init() {
	}

	protected void reload() {
	}

	@Override
	public synchronized void run() {
		super.run();
		try {
			process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void process() throws Exception;
	
}
