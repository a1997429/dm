/*
 * HelloNET.java -
 *
 * This file is part of the Jawin Project: http://jawinproject.sourceforge.net/
 *
 * Please consult the LICENSE file in the project root directory,
 * or at the project site before using this software.
 */

/* $Id: HelloNET.java,v 1.1 2004/07/05 11:55:59 arosii_moa Exp $ */

package com.github.imusk.dm.aide.jawin;

import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.GUID;
import org.jawin.win32.Ole32;

/**
 * Demo showing how to call .NET code published as a COM object.
 * The .NET C# source is in the demos/net folder.
 *
 * @version     $Revision: 1.1 $
 * @author      Morten Andersen, arosii_moa (at) users.sourceforge.net
 */
public class HelloNET {

	/**
	 * CLSID for the HelloFromNET COM object
	 */
	public static final GUID CLSID = new GUID("{25c2f5a2-1afe-36ce-BE27-84E040F5E19A}");

	/**
	 * ProgID for the HelloFromNET COM object - could be used instead of the CLSID
	 */
	public static final String PROG_ID = "Jawin.HelloFromNET";

	public static void main(String[] args) {
		try {
			ResourcesUtil.setLibPath();

			Ole32.CoInitialize();
			HelloFromNETItf hello = new HelloFromNETItf(PROG_ID);
			hello.setCaption("Jawin .NET test");
			hello.ShowDialog("Hello from .NET");
			hello.close();
			Ole32.CoUninitialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
