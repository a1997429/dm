/*
 * DemoRegistry.java -
 *
 * This file is part of the Jawin Project: http://jawinproject.sourceforge.net/
 *
 * Please consult the LICENSE file in the project root directory,
 * or at the project site before using this software.
 */

/* $Id: DemoRegistry.java,v 1.4 2004/07/02 18:45:00 arosii_moa Exp $ */

package com.github.imusk.dm.aide.jawin;

import org.jawin.COMException;
import org.jawin.FuncPtr;
import org.jawin.ReturnFlags;
import org.jawin.donated.win32.Registry;
import org.jawin.donated.win32.RegistryConstants;
import org.jawin.io.LittleEndianInputStream;
import org.jawin.io.LittleEndianOutputStream;
import org.jawin.io.NakedByteStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * NOTE: This demo modifies the registry.
 * <br><br>
 * This demo reads the Java Runtime Enviroment version from the registry.
 * It then adds a Jawin-key to the HKEY_LOCAL_MACHINE/SOFTWARE registry hive,
 * and deletes it again.
 *
 * @author Stuart Halloway, http://www.relevancellc.com/halloway/weblog/
 * @version $Revision: 1.4 $
 */
public class DemoRegistry {

    public static void deleteKey(int handle, String key) throws COMException, IOException {
        System.out.println("Deleting key " + handle + " " + key);
        Registry.DeleteKey(handle, key);
    }

    public static int createKey(int handle, String key) throws COMException, IOException {
        System.out.println("Creating key " + handle + " " + key);
        int result = Registry.CreateKey(handle, key);
        System.out.println("Created key, value is " + result);
        return result;
    }

    /**
     * FIXME - remove again - made for testing the retval-handling
     * in the GenericStub native methods.
     */
    public static int createKey2(int handle, String key) throws COMException, IOException {
        System.out.println("Creating key2 " + handle + " " + key);

        FuncPtr fpCrK = new FuncPtr("ADVAPI32.DLL", "RegCreateKeyW");
        String marshal = "IGA:I:L8n4";
        NakedByteStream nbs = new NakedByteStream();
        LittleEndianOutputStream leos = new LittleEndianOutputStream(nbs);
        leos.writeInt(handle);
        leos.writeStringUnicode(key);

        byte[] res = fpCrK.invoke(marshal, 12, nbs, null, ReturnFlags.CHECK_W32);
        ByteArrayInputStream bais = new ByteArrayInputStream(res);
        LittleEndianInputStream leis = new LittleEndianInputStream(bais);
        leis.readInt(); //ignore return value
        int result = leis.readInt(); // the out param
        System.out.println("Created key2, value is " + result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                "Accessing the registry to determine the most recent version of the JRE");
        int key =
                Registry.OpenKey(
                        RegistryConstants.HKEY_LOCAL_MACHINE,
                        "SOFTWARE\\JavaSoft\\Java Runtime Environment");
        System.out.println("Key opened successfully.  Handle is " + key);
        String value = Registry.QueryStringValue(key, "CurrentVersion");
        System.out.println(
                "The value of HKEY_LOCAL_MACHINE\\SOFTWARE\\JavaSoft\\Java Runtime Environment is "
                        + value);
        Registry.CloseKey(key);
        int jawinKey =
                createKey2(RegistryConstants.HKEY_LOCAL_MACHINE, "SOFTWARE\\Jawin");
//			createKey2(RegistryConstants.HKEY_CURRENT_USER, "SOFTWARE\\Jawin");
        int testkey = createKey2(jawinKey, "testsubkey");
        deleteKey(testkey, "");
        deleteKey(jawinKey, "");
    }
}
