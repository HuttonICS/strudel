package sbrn.mapviewer.gui.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import sbrn.mapviewer.gui.*;
import sbrn.mapviewer.gui.entities.*;

public class ZoomControlPanel extends JToolBar implements ChangeListener, ActionListener, MouseListener
{
	// ===============================================vars=======================================

	WinMain winMain;
	JLabel label;
	JSlider zoomSlider;
	JButton resetButton;
	GMapSet gMapSet;


	// ===================================================c'tor====================================

	public ZoomControlPanel(WinMain winMain,GMapSet gMapSet)
	{
		super();

		this.winMain = winMain;
		this.gMapSet = gMapSet;

		setFloatable(false);
		setBorderPainted(false);

		setupComponents();
	}

	// ==============================================methods====================================

	private void setupComponents()
	{
		int sliderMin = 1;
		int sliderMax = 500;
		int sliderInitialVal = 1;

		// left hand control
		JPanel leftPanel = new JPanel();
		//label
		label = new JLabel(Icons.getIcon("ZOOM"));
		zoomSlider = new JSlider(sliderMin, sliderMax, sliderInitialVal);
		zoomSlider.setToolTipText("Zoom this genome in or out");
		// add it
		zoomSlider.addChangeListener(this);
		//we need the mouse listener so we can have the canvas repainted with antialias on when the mouse button is released
		//this is the way it happens for all other cases where we need a pretty repaint
		zoomSlider.addMouseListener(this);
		zoomSlider.setPaintTicks(true);
		zoomSlider.setMinorTickSpacing(50);
		zoomSlider.setMajorTickSpacing(100);
		//reset button
		resetButton = new JButton(Icons.getIcon("RESET"));
		resetButton.setToolTipText("Reset zoom");
		resetButton.addActionListener(this);
		if (scri.commons.gui.SystemUtils.isMacOS() == false)
			resetButton.setMargin(new Insets(2, 1, 2, 1));

		add(new JLabel("  "));
		add(label);
		add(new JLabel(" "));
		add(zoomSlider);
		add(resetButton);
		add(new JLabel("  "));
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void stateChanged(ChangeEvent e)
	{
		JSlider source = (JSlider) e.getSource();
		if (source.equals(zoomSlider))
		{
			winMain.mainCanvas.zoomHandler.processContinuousZoomRequest(source.getValue(), 0, gMapSet, true);
		}
		updateSliders();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void updateSliders()
	{
		//update the sliders
		zoomSlider.setValue((int) gMapSet.zoomFactor);
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==resetButton)
		{
			winMain.mainCanvas.zoomHandler.processZoomResetRequest(gMapSet, 1000);
		}

		updateSliders();
	}



	public void mouseReleased(MouseEvent e)
	{
		MapViewer.winMain.mainCanvas.antiAlias = true;
		MapViewer.winMain.mainCanvas.updateCanvas(true);		
	}
	
	//theseare currently not needed
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
