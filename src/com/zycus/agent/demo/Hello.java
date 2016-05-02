package com.zycus.agent.demo;

public class Hello {
	public void say() throws InterruptedException {
		System.out.println("Wasting time..");
		StringBuilder messageOne = new StringBuilder();
		int numIterations = 50000000;
		for (int idx = 0; idx < numIterations; ++idx) {
			messageOne.append("blah");
		}
		System.out.println("Hello");
		/*System.out.println("sleeping (10s)..");
		Thread.sleep(10000);*/
	}
}
