/*
 * MTFindFeaturesPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package sbrn.mapviewer.gui.dialog;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import sbrn.mapviewer.gui.*;
import sbrn.mapviewer.gui.entities.*;

/**
 *
 * @author  __USER__
 */
public class MTFindFeaturesPanel extends javax.swing.JPanel implements ActionListener
{
	
	/** Creates new form MTFindFeaturesPanel */
	public MTFindFeaturesPanel()
	{
		initComponents();
		
		//set up the combo boxes with their data models
		Vector<String> genomes = new Vector<String>();
		for (GMapSet gMapSet : MapViewer.winMain.mainCanvas.gMapSetList)
		{
			genomes.add(gMapSet.name);
		}
		genomeCombo.setModel(new DefaultComboBoxModel(genomes));
		setUpInitialCombos();
	}
	
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{
		
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		ffTextArea = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		genomeCombo = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		chromoCombo = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		intervalStartTextField = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		intervalEndTextField = new javax.swing.JTextField();
		displayHomologsCheckBox = new javax.swing.JCheckBox();
		displayLabelsCheckbox = new javax.swing.JCheckBox();
		
		jLabel1.setText("<html>Enter one or more feature names (one per line) you would like to highlight:</html>");
		
		ffTextArea.setColumns(20);
		ffTextArea.setRows(1);
		ffTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5));
		jScrollPane1.setViewportView(ffTextArea);
		
		jLabel2.setText("<html><b>OR:</b> Select a chromosome and an interval you would like to list all features for:</html>");
		
		jLabel4.setText("Genome:");
		
		genomeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]
		{ "Item 1", "Item 2", "Item 3", "Item 4" }));
		genomeCombo.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				MTFindFeaturesPanel.this.actionPerformed(evt);
			}
		});
		
		jLabel5.setText("Chromosome:");
		
		chromoCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]
		{ "Item 1", "Item 2", "Item 3", "Item 4" }));
		chromoCombo.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				MTFindFeaturesPanel.this.actionPerformed(evt);
			}
		});
		
		jLabel6.setText("Interval start:");
		
		jLabel7.setText("Interval start:");
		
		displayHomologsCheckBox.setText("Display homologies for this range");
		displayHomologsCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
		
		displayLabelsCheckbox.setText("Display labels for all features");
		displayLabelsCheckbox.setMargin(new java.awt.Insets(0, 0, 0, 0));
		
		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
						layout.createSequentialGroup().addContainerGap().add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.LEADING).add(
														layout.createSequentialGroup().add(
																		jLabel1,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		268,
																		Short.MAX_VALUE).add(
																		12,
																		12,
																		12)).add(
														layout.createSequentialGroup().add(
																		layout.createParallelGroup(
																						org.jdesktop.layout.GroupLayout.TRAILING).add(
																						layout.createParallelGroup(
																										org.jdesktop.layout.GroupLayout.LEADING).add(
																										jLabel4).add(
																										jLabel5).add(
																										jLabel6)).add(
																						jLabel7)).addPreferredGap(
																		org.jdesktop.layout.LayoutStyle.RELATED).add(
																		layout.createParallelGroup(
																						org.jdesktop.layout.GroupLayout.LEADING,
																						false).add(
																						genomeCombo,
																						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
																						chromoCombo,
																						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
																						intervalStartTextField).add(
																						intervalEndTextField,
																						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																						127,
																						Short.MAX_VALUE)).addContainerGap(
																		81,
																		Short.MAX_VALUE)).add(
														org.jdesktop.layout.GroupLayout.TRAILING,
														layout.createSequentialGroup().add(
																		layout.createParallelGroup(
																						org.jdesktop.layout.GroupLayout.TRAILING).add(
																						jScrollPane1,
																						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																						270,
																						Short.MAX_VALUE).add(
																						jLabel2,
																						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																						270,
																						Short.MAX_VALUE)).addContainerGap()).add(
														layout.createSequentialGroup().add(
																		displayHomologsCheckBox).addContainerGap(
																		101,
																		Short.MAX_VALUE)).add(
														layout.createSequentialGroup().add(
																		displayLabelsCheckbox).addContainerGap(
																		121,
																		Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
						layout.createSequentialGroup().addContainerGap().add(jLabel1).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.UNRELATED).add(
										jScrollPane1,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										90, Short.MAX_VALUE).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.UNRELATED).add(
										jLabel2).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.UNRELATED).add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.BASELINE).add(
														jLabel4).add(
														genomeCombo,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.BASELINE).add(
														jLabel5).add(
														chromoCombo,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.BASELINE).add(
														jLabel6).add(
														intervalStartTextField,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										layout.createParallelGroup(
														org.jdesktop.layout.GroupLayout.BASELINE).add(
														intervalEndTextField,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
														jLabel7)).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										displayHomologsCheckBox).addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED).add(
										displayLabelsCheckbox).addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents
	
	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt)
	{
		// TODO add your handling code here:
	}
	
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JComboBox chromoCombo;
	private javax.swing.JCheckBox displayHomologsCheckBox;
	private javax.swing.JCheckBox displayLabelsCheckbox;
	private javax.swing.JTextArea ffTextArea;
	private javax.swing.JComboBox genomeCombo;
	private javax.swing.JTextField intervalEndTextField;
	private javax.swing.JTextField intervalStartTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JScrollPane jScrollPane1;
	
	// End of variables declaration//GEN-END:variables
	
	public JTextArea getFFTextArea()
	{
		return ffTextArea;
	}
	
	public javax.swing.JComboBox getChromoCombo()
	{
		return chromoCombo;
	}
	
	public javax.swing.JComboBox getGenomeCombo()
	{
		return genomeCombo;
	}
	
	public javax.swing.JTextField getIntervalEndTextField()
	{
		return intervalEndTextField;
	}
	
	public javax.swing.JTextField getIntervalStartTextField()
	{
		return intervalStartTextField;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == genomeCombo)
		{
			JComboBox cb = (JComboBox) e.getSource();
			String genomeName = (String) cb.getSelectedItem();
			setUpChromoCombo(genomeName);
		}
	}
	
	private void setUpInitialCombos()
	{
		//populate the chromoCombo with the chromos for the currently selected genome
		setUpChromoCombo((String) genomeCombo.getSelectedItem());
	}
	
	private void setUpChromoCombo(String genomeName)
	{
		//find the genome object and set the other combo to list its chromosomes
		GMapSet gMapSet = Utils.getGMapSetByName(genomeName);
		Vector<String> chromoNames = new Vector<String>();
		for (GChromoMap gChromoMap : gMapSet.gMaps)
		{
			chromoNames.add(gChromoMap.name);
		}
		chromoCombo.setModel(new DefaultComboBoxModel(chromoNames));
	}
	
	public javax.swing.JCheckBox getDisplayHomologsCheckBox()
	{
		return displayHomologsCheckBox;
	}
	
	public javax.swing.JCheckBox getDisplayLabelsCheckbox()
	{
		return displayLabelsCheckbox;
	}
	
}