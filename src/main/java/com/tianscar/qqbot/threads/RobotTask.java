package com.tianscar.qqbot.threads;

public abstract class RobotTask implements Runnable {
    
	protected void init() {
	}

	protected void reload() {
	}

	@Override
	public void run() {
		try {
			process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void process() throws Exception;
	
}
