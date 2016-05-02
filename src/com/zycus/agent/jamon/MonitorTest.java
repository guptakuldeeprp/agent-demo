package com.zycus.agent.jamon;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

public class MonitorTest {
    public static void main(String[] args) throws Exception {
        Monitor mon=null;
        for (int i=1; i<=10; i++) {
            mon = MonitorFactory.start("myFirstMonitor");
            Thread.sleep(100+i);
            mon.stop();
        }
        System.out.println(mon);  // toString() method called
    }
}
