package sbrn.mapviewer.gui.components;

import java.awt.*;
import java.awt.font.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import sbrn.mapviewer.*;
import sbrn.mapviewer.data.*;
import sbrn.mapviewer.gui.entities.*;

public class ResultsTable extends JTable
{

	//===============================================vars=========================================

	HyperlinkCellRenderer hyperlinkCellRenderer = new HyperlinkCellRenderer();

	public boolean isFilterEvent = false;

	TableRowSorter<TableModel> sorter;

	//===============================================curve'tor=========================================

	public ResultsTable()
	{
		//configure table for selections
		setRowSelectionAllowed(true);

		//for centering text in table
		setDefaultRenderer(String.class, new LeftAlignedRenderer());
		setDefaultRenderer(Integer.class, new LeftAlignedRenderer());
		setDefaultRenderer(Float.class, new LeftAlignedRenderer());

		//set up sorting/filtering capability
		sorter = new TableRowSorter<TableModel>(this.getModel());
		setRowSorter(sorter);

		//add listener
		HomologResultsTableListener homologResultsTableListener = new HomologResultsTableListener(this);
		addMouseMotionListener(homologResultsTableListener);
		addMouseListener(homologResultsTableListener);
		getSelectionModel().addListSelectionListener(homologResultsTableListener);

	}

	//===============================================methods=========================================


	@Override
	public TableCellRenderer getCellRenderer(int row, int column)
	{
		HomologResultsTableModel model = (HomologResultsTableModel)getModel();
		
		// get the index of the selected row but check for changes due to filtering
		int modelRow = convertRowIndexToModel(row);

		//find out whether user clicked on column potentially containing a URL
		boolean isURLColumn = false;
		if (column == (model.findColumn(HomologResultsTableModel.homologColumnLabel)) ||
						column == (model.findColumn(HomologResultsTableModel.targetNameColumnLabel)))
			isURLColumn = true;

		if(isURLColumn && cellHasURLSet(modelRow, column))
			return hyperlinkCellRenderer;

		return super.getCellRenderer(modelRow, column);
	}


	//===============================================inner classes=========================================

	class HyperlinkCellRenderer extends JLabel implements TableCellRenderer
	{

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			// get the index of the selected row but check for changes due to filtering
			int modelRow = table.convertRowIndexToModel(row);

			// set the font up so it's blue and underlined to make it look like a hyperlink
			Map<TextAttribute, Integer> attributes = new Hashtable<TextAttribute, Integer>();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			Font font = new Font(attributes);
			setFont(font);
			setForeground(Color.blue);
			setHorizontalAlignment(LEFT);

			// this is what we want to print to the cell
			String cellContent = "";
			Object obj = table.getModel().getValueAt(modelRow, column);
			if(obj instanceof String)
				cellContent = (String)obj;
			else if (obj instanceof Float)
				cellContent = ((Float)obj).toString();
			setText(cellContent);

			// selection colors etc
			if (isSelected)
			{
				setBackground(table.getSelectionBackground());
				setOpaque(true);
			}
			else
			{
				setOpaque(false);
			}


			return this;
		}

	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	/*
	 * Center the text
	 */
	class LeftAlignedRenderer extends DefaultTableCellRenderer
	{
		public LeftAlignedRenderer()
		{
			setHorizontalAlignment(LEFT);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			return this;
		}
	}



	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void initColumnSizes()
	{
		TableColumn column = null;
		for (int i = 0; i < getColumnModel().getColumnCount(); i++)
		{
			// this is the maxWidth for entire column, header included
			int maxWidth = 0;

			// get the font metrics for this table
			FontMetrics fm = getFontMetrics(getFont());

			// get the string width for the data header for this column
			int headerWidth = fm.stringWidth(getColumnName(i));
			if (headerWidth > maxWidth)
				maxWidth = headerWidth;

			// get the data in this column and check their width
			for (int j = 0; j < getModel().getRowCount(); j++)
			{
				String cellContent = "";
				if(getModel().getValueAt(j, i) != null)
					cellContent = getModel().getValueAt(j, i).toString();
				int cellWidth = fm.stringWidth(cellContent);
				if (cellWidth > maxWidth)
					maxWidth = cellWidth;
			}
			column = getColumnModel().getColumn(i);
			column.setPreferredWidth(maxWidth);
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public int getTotalTableWidth()
	{
		int width = 0;
		TableColumn column = null;
		for (int i = 0; i < getColumnModel().getColumnCount(); i++)
		{
			column = getColumnModel().getColumn(i);
			width += column.getPreferredWidth();
		}
		return width;
	}

	//	---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//checks whether mapset with feature in cell in this table at row,column has a URL or not
	public boolean cellHasURLSet(int row, int column)
	{	
		HomologResultsTableModel model = (HomologResultsTableModel)getModel();

		//get the feature that was clicked on
		Feature feature = null;
		if(column == model.findColumn(HomologResultsTableModel.targetNameColumnLabel))
			feature = model.tableEntries.get(row).getTargetFeature();
		else if (column == model.findColumn(HomologResultsTableModel.homologColumnLabel))
			feature = model.tableEntries.get(row).getHomologFeature();

		//find out whether the mapset containing the feature that was clicked on actually had a URL supplied
		boolean urlAvailable = false;
		if(feature != null)
			urlAvailable = feature.getOwningMapSet().getURL() != null;

		return urlAvailable;
	}


	//	---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public LinkedList<ResultsTableEntry> getVisibleEntries()
	{
		LinkedList<ResultsTableEntry> visibleEntries = new LinkedList<ResultsTableEntry>();

		for (int i = 0; i < getRowCount(); i++)
		{
			HomologResultsTableModel model = (HomologResultsTableModel) getModel();
			ResultsTableEntry resultsTableEntry = model.tableEntries.get(convertRowIndexToModel(i));
			visibleEntries.add(resultsTableEntry);
		}

		return visibleEntries;
	}

	//	---------------------------------------------------------------------------------------------------------------------------------------------------------------------------


}
