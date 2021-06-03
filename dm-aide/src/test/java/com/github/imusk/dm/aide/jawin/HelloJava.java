package com.github.imusk.dm.aide.jawin;

import com.github.imusk.dm.aide.utils.ResourcesUtil;
import org.jawin.COMException;
import org.jawin.donated.win32.*;

import java.io.IOException;

class TestWNDPROC extends WNDPROC {
	public int call(int hwnd, int msg, int wParam, int lParam) throws IOException {
		try {
			switch (msg) {
				/*
				      case WM_CREATE:
				  PlaySound (TEXT ("hellowin.wav"), NULL, SND_FILENAME | SND_ASYNC) ;
				  return 0 ;

				*/
				case WM_Constants.WM_PAINT :
					{
						PAINTSTRUCT ps = new PAINTSTRUCT();
						RECT rect = new RECT();

						int hdc = User32.BeginPaint(hwnd, ps);

						User32.GetClientRect(hwnd, rect);

						User32.DrawTextW(
							hdc,
							"Hello, Java/Win32!",
							-1,
							rect,
							DT_Constants.DT_SINGLELINE | DT_Constants.DT_CENTER | DT_Constants.DT_VCENTER);

						User32.EndPaint(hwnd, ps);
						return 0;
					}
				case WM_Constants.WM_DESTROY :
					User32.PostQuitMessage(0);
					return 0;
			}
			return User32.DefWindowProcW(hwnd, msg, wParam, lParam);
		} catch (COMException e) {
			//COMEBACK need some way to handle callback exceptions
			e.printStackTrace();
			return 0;
		}
	}
}

/**
 * Port of Petzold chapter 3 to Jawin
 *
 * @version     $Revision: 1.4 $
 * @author      Stuart Halloway, http://www.relevancellc.com/halloway/weblog/
 */
public class HelloJava {

	public static void main(String[] args) throws Exception {

		ResourcesUtil.setLibPath();

		int hInst = W32Process.GetModuleHandleW(null);

		WNDCLASS wndclass = new WNDCLASS();
		wndclass.style = MiscellaneousConstants.CS_HREDRAW | MiscellaneousConstants.CS_VREDRAW;
		wndclass.lpfnWndProc = new TestWNDPROC();
		wndclass.hIcon = User32.LoadIconW(MiscellaneousConstants.NULL, MiscellaneousConstants.IDI_APPLICATION);
		wndclass.hCursor = User32.LoadCursorW(MiscellaneousConstants.NULL, MiscellaneousConstants.IDC_ARROW);
		wndclass.lpszClassName = "HelloWin";
		wndclass.hbrBackground = GDI32.GetStockObject(MiscellaneousConstants.WHITE_BRUSH);
		wndclass.hInstance = hInst;
		short atom = User32.RegisterClassW(wndclass);
		int hwnd = User32.CreateWindowExW(0, "HelloWin", // window class name
			"The Hello Program", // window caption
			WS_Constants.WS_OVERLAPPEDWINDOW, // window style
			MiscellaneousConstants.CW_USEDEFAULT, // initial x position
			MiscellaneousConstants.CW_USEDEFAULT, // initial y position
			MiscellaneousConstants.CW_USEDEFAULT, // initial x size
			MiscellaneousConstants.CW_USEDEFAULT, // initial y size
			MiscellaneousConstants.NULL, // parent window handle
			MiscellaneousConstants.NULL, // window menu handle
			hInst, // program instance handle
			MiscellaneousConstants.NULL); // creation parameters

		User32.ShowWindow(hwnd, MiscellaneousConstants.SW_SHOW);
		User32.UpdateWindow(hwnd);

		MSG msg = new MSG();
		while (User32.GetMessageW(msg, MiscellaneousConstants.NULL, 0, 0)) {
			User32.TranslateMessage(msg);
			User32.DispatchMessageW(msg);
		}
	}
}


