/*
 * HelloFromNETItf.java -
 *
 * This file is part of the Jawin Project: http://jawinproject.sourceforge.net/
 *
 * Please consult the LICENSE file in the project root directory,
 * or at the project site before using this software.
 */

/* $Id: HelloFromNETItf.java,v 1.2 2004/07/18 14:56:59 arosii_moa Exp $ */

package com.github.imusk.dm.aide.jawin;

import org.jawin.*;

/**
 * Modified version of JTB generated code
 *
 * @version     $Revision: 1.2 $
 * @author      Morten Andersen, arosii_moa (at) users.sourceforge.net
 */
public class HelloFromNETItf extends DispatchPtr {
	public static final GUID DIID = new GUID("{3823a63d-5891-3b4f-A460-DB0FB847075A}");
	public static final int iidToken;
	static {
		iidToken = IdentityManager.registerProxy(DIID, HelloFromNETItf.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * HelloFromNETItf (it is required by Jawin for some internal working though).
	 */
	public HelloFromNETItf() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with
	 * the HelloFromNETItf interface.
	 *
	 * @param progid the progid of the COM-object to create.
	 */
	public HelloFromNETItf(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For getting the HelloFromNETItf interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 *
	 * @param comObject the COM-object to get the HelloFromNETItf interface on.
	 */
	public HelloFromNETItf(COMPtr comObject) throws COMException {
		super(comObject);
	}

	/**
	 * For creating a new COM-object with the given clsid and with
	 * the HelloFromNETItf interface.
	 *
	 * @param clsid the GUID of the COM-object to create.
	 */
	public HelloFromNETItf(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	public int getIIDToken() {
		return iidToken;
	}

    /**
    *
    * @param Caption
    **/
    public void setCaption(String Caption) throws COMException
    {
        put("Caption", Caption);
    }

    /**
    *

    * @param str
    * @return void
    **/
    public void ShowDialog(String str) throws COMException
    {

		invokeN("ShowDialog", new Object[] {str});

    }
}
