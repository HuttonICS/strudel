package sbrn.mapviewer.gui;

import javax.swing.*;

import scri.commons.gui.*;

import apple.dts.samplecode.osxadapter.*;

public class MapViewer
{
	public static WinMain winMain;

	public static void main(String[] args)
	{
		// OS X: This has to be set before anything else
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Map Viewer");

		new MapViewer();
	}

	MapViewer()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("TextArea.font", UIManager.get("TextField.font"));

			// Keep Apple happy...
			if (SystemUtils.isMacOS())
				handleOSXStupidities();

//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {}

		try
		{
			winMain = new WinMain();
			winMain.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// --------------------------------------------------
	// Methods required for better native support on OS X

	private void handleOSXStupidities()
	{
		try
		{
			// Register handlers to deal with the System menu about/quit options

//			OSXAdapter.setPreferencesHandler(this,
//				getClass().getDeclaredMethod("osxPreferences", (Class[])null));

//			OSXAdapter.setAboutHandler(this,
//				getClass().getDeclaredMethod("osxAbout", (Class[])null));

			OSXAdapter.setQuitHandler(this,
				getClass().getDeclaredMethod("osxShutdown", (Class[])null));

			// Dock the menu bar at the top of the screen
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		catch (Exception e) {}
	}

	/** "Preferences" on the OS X system menu. */
	public void osxPreferences()
	{
//		winMain.mHelp.helpPrefs();
	}

	/** "About Map Viewer" on the OS X system menu. */
	public void osxAbout()
	{
//		winMain.mHelp.helpAbout();
	}

	/** "Quit Map Viewer" on the OS X system menu. */
	public boolean osxShutdown()
	{
		// Put any additional quit handling code here (if need be)

		return true;
	}
}