/*
 * RunRMIRegistry.java -
 *
 * This file is part of the Jawin Project: http://jawinproject.sourceforge.net/
 *
 * Please consult the LICENSE file in the project root directory,
 * or at the project site before using this software.
 */

/* $Id: RunRMIRegistry.java,v 1.3 2004/06/14 20:22:23 arosii_moa Exp $ */

package com.github.imusk.dm.aide.jawin;

import org.jawin.donated.win32.Console;
import org.jawin.donated.win32.HandlerRoutine;

import java.rmi.registry.LocateRegistry;

class LogoffEventHandler extends HandlerRoutine {

	public boolean call(long dwCtrlType) {
		System.out.println("Event " + dwCtrlType);
		if (CTRL_LOGOFF_EVENT == dwCtrlType) {
			//ignore logoff events
			return true;
		}
		return false;
	}
}

/**
 * Starts the RMI-registry in a process that ignores the logoff event.
 * This should be started with javaw to successfully run across logins?
 *
 * @version     $Revision: 1.3 $
 * @author      Stuart Halloway, http://www.relevancellc.com/halloway/weblog/
 */
public class RunRMIRegistry {

	public static void main(String[] args) throws Exception {

		Object waitForever = new Object();
		Console.SetConsoleCtrlHandler(new LogoffEventHandler(), true);
		LocateRegistry.createRegistry(1099);
		try {
			synchronized (waitForever) {
				waitForever.wait();
			}
		} catch (InterruptedException ie) {
			System.out.println("Shutting down unexpectedly...");
			ie.printStackTrace();
		}
	}
}
