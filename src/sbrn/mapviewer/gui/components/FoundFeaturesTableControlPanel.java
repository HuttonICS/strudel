/*
 * FoundFeaturesTableControlPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package sbrn.mapviewer.gui.components;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import sbrn.mapviewer.*;
import sbrn.mapviewer.data.*;
import sbrn.mapviewer.gui.*;

/**
 *
 * @author  __USER__
 */
public class FoundFeaturesTableControlPanel extends javax.swing.JPanel implements DocumentListener
{
	private boolean isInitialising = true;
	
	private RowFilter<TableModel, Integer> nameAnnotationFilter = null;
	
	private RowFilter<TableModel, Integer> homologFilter = null;
	
	/** Creates new form FoundFeaturesTableControlPanel */
	public FoundFeaturesTableControlPanel()
	{
		initComponents();
		setupGenomeFilterCombo();
		
		highlightWhiteCheckbox.setSelected(Prefs.highlightHomologiesInWhite);
		highlightWhiteCheckbox.setEnabled(showHomologsCheckbox.isSelected());
		
		isInitialising = false;
		
		txtFilter.getDocument().addDocumentListener(this);
	}
	
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{
		
		tabbedPane = new javax.swing.JTabbedPane();
		infoPanel = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		numberFeaturesLabel = new javax.swing.JLabel();
		genomeLabel = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		regionStartLabel = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		regionEndLabel = new javax.swing.JLabel();
		chromoLabel = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		controlsPanel = new javax.swing.JPanel();
		txtFilter = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		highlightWhiteCheckbox = new javax.swing.JCheckBox();
		genomeFilterCombo = new javax.swing.JComboBox();
		filterLabel = new javax.swing.JLabel();
		showHomologsCheckbox = new javax.swing.JCheckBox();
		showLabelsCheckbox = new javax.swing.JCheckBox();
		
		setBorder(javax.swing.BorderFactory.createTitledBorder("Selected region -- info and controls: "));
		setMinimumSize(new java.awt.Dimension(0, 0));
		setPreferredSize(new java.awt.Dimension(310, 310));
		
		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel5.setText("No. of features:");
		
		numberFeaturesLabel.setText("n/a");
		
		genomeLabel.setText("n/a");
		
		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel3.setText("Region start:");
		
		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel4.setText("Region end:");
		
		regionStartLabel.setText("n/a");
		
		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel2.setText("Chromosome:");
		
		regionEndLabel.setText("n/a");
		
		chromoLabel.setText("n/a");
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel1.setText("Genome:");
		
		org.jdesktop.layout.GroupLayout infoPanelLayout = new org.jdesktop.layout.GroupLayout(infoPanel);
		infoPanel.setLayout(infoPanelLayout);
		infoPanelLayout.setHorizontalGroup(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(infoPanelLayout.createSequentialGroup().addContainerGap().add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false).add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(numberFeaturesLabel).add(regionEndLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).add(regionStartLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).add(chromoLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).add(genomeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)).addContainerGap()));
		infoPanelLayout.setVerticalGroup(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(infoPanelLayout.createSequentialGroup().addContainerGap().add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel1).add(genomeLabel)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel2).add(chromoLabel)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel3).add(regionStartLabel)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel4).add(regionEndLabel)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(infoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel5).add(numberFeaturesLabel)).addContainerGap()));
		
		tabbedPane.addTab("Info", infoPanel);
		
		jLabel6.setText("Filter by name or annotation:");
		
		highlightWhiteCheckbox.setText("Always highlight in white");
		highlightWhiteCheckbox.addChangeListener(new javax.swing.event.ChangeListener()
		{
			public void stateChanged(javax.swing.event.ChangeEvent evt)
			{
				highlightWhiteCheckboxStateChanged(evt);
			}
		});
		
		genomeFilterCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]
		{ "Item 1", "Item 2", "Item 3", "Item 4" }));
		genomeFilterCombo.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				genomeFilterComboActionPerformed(evt);
			}
		});
		
		filterLabel.setText("Filter by reference genome:");
		
		showHomologsCheckbox.setText("Show all homologies");
		showHomologsCheckbox.addChangeListener(new javax.swing.event.ChangeListener()
		{
			public void stateChanged(javax.swing.event.ChangeEvent evt)
			{
				showHomologsCheckboxStateChanged(evt);
			}
		});
		
		showLabelsCheckbox.setText("Show all labels");
		showLabelsCheckbox.addChangeListener(new javax.swing.event.ChangeListener()
		{
			public void stateChanged(javax.swing.event.ChangeEvent evt)
			{
				showLabelsCheckboxStateChanged(evt);
			}
		});
		
		org.jdesktop.layout.GroupLayout controlsPanelLayout = new org.jdesktop.layout.GroupLayout(controlsPanel);
		controlsPanel.setLayout(controlsPanelLayout);
		controlsPanelLayout.setHorizontalGroup(controlsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(controlsPanelLayout.createSequentialGroup().addContainerGap().add(controlsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(controlsPanelLayout.createSequentialGroup().add(filterLabel).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(genomeFilterCombo, 0, 86, Short.MAX_VALUE)).add(showLabelsCheckbox).add(showHomologsCheckbox).add(controlsPanelLayout.createSequentialGroup().add(21, 21, 21).add(highlightWhiteCheckbox)).add(controlsPanelLayout.createSequentialGroup().add(jLabel6).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(txtFilter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))).addContainerGap()));
		controlsPanelLayout.setVerticalGroup(controlsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(controlsPanelLayout.createSequentialGroup().addContainerGap().add(controlsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(filterLabel).add(genomeFilterCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(showLabelsCheckbox).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(showHomologsCheckbox).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(highlightWhiteCheckbox).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(controlsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(jLabel6).add(txtFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		
		tabbedPane.addTab("Controls", controlsPanel);
		
		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup().addContainerGap().add(tabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(tabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		
		tabbedPane.getAccessibleContext().setAccessibleName("tab1\n");
	}// </editor-fold>
	//GEN-END:initComponents
	
	private void highlightWhiteCheckboxStateChanged(javax.swing.event.ChangeEvent evt)
	{//GEN-FIRST:event_highlightWhiteCheckboxStateChanged
		Prefs.highlightHomologiesInWhite = highlightWhiteCheckbox.isSelected();
		
		if (isInitialising == false)
			Strudel.winMain.mainCanvas.updateCanvas(true);
	}//GEN-LAST:event_highlightWhiteCheckboxStateChanged
	
	private void genomeFilterComboActionPerformed(java.awt.event.ActionEvent evt)
	{
		if (!(Strudel.winMain.ffResultsPanel.resultsTable.getModel() instanceof DefaultTableModel))
		{
			JComboBox cb = (JComboBox) evt.getSource();
			String genomeName = (String) cb.getSelectedItem();
			HomologResultsTableModel homologResultsTableModel = (HomologResultsTableModel) Strudel.winMain.ffResultsPanel.resultsTable.getModel();
			int genomeColumnIndex = homologResultsTableModel.findColumn(homologResultsTableModel.homologGenomeColumnLabel);
			homologFilter(genomeName, genomeColumnIndex);
			
			Strudel.winMain.mainCanvas.updateCanvas(true);
		}
	}
	
	private void showHomologsCheckboxStateChanged(javax.swing.event.ChangeEvent evt)
	{
		if (Strudel.winMain.mainCanvas.drawFoundFeaturesInRange)
		{
			//synchronise this checkbox with the corresponding one in the find features in range panel
			if (showHomologsCheckbox.isSelected())
				Strudel.winMain.ffInRangeDialog.ffInRangePanel.getDisplayHomologsCheckBox().setSelected(true);
			else
				Strudel.winMain.ffInRangeDialog.ffInRangePanel.getDisplayHomologsCheckBox().setSelected(false);
			highlightWhiteCheckbox.setEnabled(showHomologsCheckbox.isSelected());
			Strudel.winMain.mainCanvas.updateCanvas(true);
		}
	}
	
	private void showLabelsCheckboxStateChanged(javax.swing.event.ChangeEvent evt)
	{
		if (Strudel.winMain.mainCanvas.drawFoundFeaturesInRange)
		{
			//synchronise this checkbox with the corresponding one in the find features in range panel
			if (showLabelsCheckbox.isSelected())
				Strudel.winMain.ffInRangeDialog.ffInRangePanel.getDisplayLabelsCheckbox().setSelected(true);
			else
				Strudel.winMain.ffInRangeDialog.ffInRangePanel.getDisplayLabelsCheckbox().setSelected(false);
			Strudel.winMain.mainCanvas.updateCanvas(true);
		}
	}
	
	public void setupGenomeFilterCombo()
	{
		//set up the combo box with its data model
		Vector<String> genomes = new Vector<String>();
		genomes.add("<none>");
		for (MapSet mapSet : Strudel.winMain.dataSet.allMapSets)
		{
			genomes.add(mapSet.getName());
		}
		genomeFilterCombo.setModel(new DefaultComboBoxModel(genomes));
	}
	
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel chromoLabel;
	private javax.swing.JPanel controlsPanel;
	private javax.swing.JLabel filterLabel;
	private javax.swing.JComboBox genomeFilterCombo;
	private javax.swing.JLabel genomeLabel;
	private javax.swing.JCheckBox highlightWhiteCheckbox;
	private javax.swing.JPanel infoPanel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel numberFeaturesLabel;
	private javax.swing.JLabel regionEndLabel;
	private javax.swing.JLabel regionStartLabel;
	private javax.swing.JCheckBox showHomologsCheckbox;
	private javax.swing.JCheckBox showLabelsCheckbox;
	private javax.swing.JTabbedPane tabbedPane;
	javax.swing.JTextField txtFilter;
	
	// End of variables declaration//GEN-END:variables
	
	public javax.swing.JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	
	public javax.swing.JLabel getChromoLabel()
	{
		return chromoLabel;
	}
	
	public javax.swing.JLabel getGenomeLabel()
	{
		return genomeLabel;
	}
	
	public javax.swing.JLabel getNumberFeaturesLabel()
	{
		return numberFeaturesLabel;
	}
	
	public javax.swing.JLabel getRegionEndLabel()
	{
		return regionEndLabel;
	}
	
	public javax.swing.JLabel getRegionStartLabel()
	{
		return regionStartLabel;
	}
	
	public javax.swing.JCheckBox getShowHomologsCheckbox()
	{
		return showHomologsCheckbox;
	}
	
	public javax.swing.JCheckBox getShowLabelsCheckbox()
	{
		return showLabelsCheckbox;
	}
	
	public javax.swing.JLabel getFilterLabel()
	{
		return filterLabel;
	}
	
	public javax.swing.JComboBox getGenomeFilterCombo()
	{
		return genomeFilterCombo;
	}
	
	public String getTxtFilter()
	{
		return txtFilter.getText();
	}
	
	@Override
	public void insertUpdate(DocumentEvent e)
	{
		nameAnnotationFilter();
	}
	
	@Override
	public void removeUpdate(DocumentEvent e)
	{
		nameAnnotationFilter();
	}
	
	@Override
	public void changedUpdate(DocumentEvent e)
	{
		nameAnnotationFilter();
	}
	
	private void nameAnnotationFilter()
	{
		nameAnnotationFilter = null;
		
		try
		{
			nameAnnotationFilter = RowFilter.regexFilter(txtFilter.getText(), 0, 8);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		setupAndFilter();
	}
	
	//applies a regular epxression based filter to the results table
	public void homologFilter(String filterExpression, int index)
	{
		homologFilter = null;
		String expr = "^" + filterExpression;
		
		try
		{
			homologFilter = RowFilter.regexFilter(filterExpression, index);
		}
		catch (java.util.regex.PatternSyntaxException e)
		{
			return;
		}
		
		if (filterExpression.equals("<none>"))
			homologFilter = null;
		else
			homologFilter = RowFilter.regexFilter(expr, 4);
		
		setupAndFilter();
	}
	
	private void setupAndFilter()
	{
		ArrayList<RowFilter<TableModel, Integer>> filters = new ArrayList<RowFilter<TableModel, Integer>>();
		
		filters.add(homologFilter);
		filters.add(nameAnnotationFilter);
		
		DefaultRowSorter<TableModel, Integer> rowSorter = (DefaultRowSorter<TableModel, Integer>) Strudel.winMain.ffResultsPanel.resultsTable.getRowSorter();
		
		if (homologFilter != null && nameAnnotationFilter != null)
			rowSorter.setRowFilter(RowFilter.andFilter(filters));
		else if (homologFilter != null)
			rowSorter.setRowFilter(homologFilter);
		else
			rowSorter.setRowFilter(nameAnnotationFilter);
		
		//update the label that says how many features are contained in the results table
		Strudel.winMain.foundFeaturesTableControlPanel.getNumberFeaturesLabel().setText(new Integer(Strudel.winMain.ffResultsPanel.resultsTable.getVisibleEntries().size()).toString());
	}
	
	public JCheckBox getHighlightWhiteCheckbox()
	{
		return highlightWhiteCheckbox;
	}
	
}
