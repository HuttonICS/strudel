package sbrn.mapviewer.gui;

import java.io.*;
import sbrn.mapviewer.*;
import sbrn.mapviewer.gui.handlers.*;
import sbrn.mapviewer.io.*;

public class DataLoadThread extends Thread
{

	File inputFile;

	public DataLoadThread(String inputFileName)
	{
		inputFile = new File (inputFileName);
	}

	@Override
	public void run()
	{
		try
		{
			// data is loading now -- check whether the user has cancelled
			if (!Strudel.winMain.fatController.dataLoadCancelled)
			{
				// load the data
				boolean success = DataLoadUtils.loadDataFromSingleFile(inputFile);

				//check it all loaded ok
				if(!success)
					return;

				//store the input file int he recent files list
				Prefs.setRecentDocument(inputFile.getAbsolutePath());

				// build the rest of the GUI as required
				if (!Strudel.winMain.fatController.guiFullyAssembled)
					Strudel.winMain.fatController.assembleRemainingGUIComps();
				else
					Strudel.winMain.reinitialiseDependentComponents();

				//display the name of the current dataset in the window title bar
				Strudel.winMain.setTitle(inputFile.getName() + " -- Strudel " + Install4j.VERSION);

				// check if we need to enable some functionality -- depends on the number of genomes loaded
				// cannot do comparative stuff if user one loaded one (target) genome
				if (Strudel.winMain.dataContainer.gMapSets.size() == 1)
				{
					//enables toolbar controls selectively
					Strudel.winMain.toolbar.enableControls(true);
					//disable the comparative mode controls in the results table's control panel
					Strudel.winMain.foundFeaturesTableControlPanel.getFilterLabel().setEnabled(false);
					Strudel.winMain.foundFeaturesTableControlPanel.getGenomeFilterCombo().setEnabled(false);
					Strudel.winMain.foundFeaturesTableControlPanel.getShowHomologsCheckbox().setEnabled(false);
				}
				else
				{
					//enables toolbar controls selectively
					Strudel.winMain.toolbar.enableControls(false);
					//enable the comparative mode controls in the results table's control panel
					Strudel.winMain.foundFeaturesTableControlPanel.getFilterLabel().setEnabled(true);
					Strudel.winMain.foundFeaturesTableControlPanel.getGenomeFilterCombo().setEnabled(true);
					Strudel.winMain.foundFeaturesTableControlPanel.getShowHomologsCheckbox().setEnabled(true);

					// also need a new link display manager because it holds the precomputed links
					Strudel.winMain.mainCanvas.linkDisplayManager = new LinkDisplayManager(Strudel.winMain.mainCanvas);
				}

				// hide the data loading progress dialog
				if (Strudel.winMain.dataLoadingDialog != null)
					Strudel.winMain.dataLoadingDialog.setVisible(false);

				// hide the start panel if it is still showing
				Strudel.winMain.showStartPanel(false);

				//clear the results table, in case we already had data loaded
				Strudel.winMain.fatController.clearResultsTable();

				// revalidate the GUI
				Strudel.winMain.validate();

				//repaint the main canvas
				Strudel.winMain.mainCanvas.updateCanvas(true);

				// bring the focus back on the main window -- need this in case we had an overview dialog open (which then gets focus)
				Strudel.winMain.requestFocus();
			}

			// reset the cancel flag as the user might now want to try again
			Strudel.winMain.fatController.dataLoadCancelled = false;
			Strudel.winMain.fatController.recentFileLoad = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
