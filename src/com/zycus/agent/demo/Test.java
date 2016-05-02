package com.zycus.agent.demo;

import net.anotheria.moskito.webui.util.APILookupUtility;

public class Test {
	public static void main(String[] args) throws Exception {
		net.anotheria.moskito.webui.embedded.StartMoSKitoInspectBackendForRemote.startMoSKitoInspectBackend();
		APILookupUtility.getAdditionalFunctionalityAPI().forceIntervalUpdate("1m");
		Hello hello = new Hello();
		hello.say();
	}

}
