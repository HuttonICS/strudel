package sbrn.mapviewer.gui.handlers;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import sbrn.mapviewer.*;
import sbrn.mapviewer.data.*;
import sbrn.mapviewer.gui.*;
import sbrn.mapviewer.gui.dialog.*;
import sbrn.mapviewer.gui.entities.*;


public class LabelDisplayManager
{
	
	private static int fontHeight = 11;
	
	
	//------------------------------------------------------------------------------------------------------------------------------------
	
	//this just draws labels of single highlighted features
	public static void drawHighlightedFeatureLabels(Graphics2D g2, Feature f1, Feature f2)
	{
		// the usual font stuff
		g2.setFont(new Font("Sans-serif", Font.PLAIN, fontHeight));
		FontMetrics fm = g2.getFontMetrics();
		
		//easiest to do this with a local Vector for the two features -- saves duplication 
		Vector<Feature> features = new Vector<Feature>();
		features.add(f1);
		features.add(f2);
		
		//work out the mapset index for the homolog
		int mapSetIndexF2 = MapViewer.winMain.dataContainer.gMapSetList.indexOf(f2.getOwningMap().getGChromoMap().owningSet);
		
		// for all features in our list
		for (Feature f : features)
		{
			
			// get the name of the feature
			String featureName = f.getName();
			int stringWidth = fm.stringWidth(featureName);
			
			// we need these for working out the y positions
			ChromoMap chromoMap = f.getOwningMap();
			GChromoMap gChromoMap = chromoMap.getGChromoMap();
			float mapEnd = chromoMap.getStop();
			// this factor normalises the position to a value between 0 and 100
			float scalingFactor = gChromoMap.height / mapEnd;
			
			//			// the y position of the feature itself
			int featureY;
			if (f.getStart() == 0.0f)
			{
				featureY = gChromoMap.y;
			}
			else
			{
				featureY = Math.round(gChromoMap.y + gChromoMap.currentY + (f.getStart() * scalingFactor));
			}		
			//check whether the map is inverted			
			if(gChromoMap.isPartlyInverted)
			{
				featureY = (int) ((mapEnd - f.getStart()) / (mapEnd / gChromoMap.height)) + (gChromoMap.y + gChromoMap.currentY);
			}
			
			//the y position of the feature label
			int labelY = featureY + (fontHeight/2);
			
			// next decide where to place the label on x
			// the amount by which we want to move the label end away from the chromosome (in pixels)
			int lineLength = 50;
			
			//x coords
			int labelX = gChromoMap.x - lineLength - stringWidth;
			int lineStartX =  gChromoMap.x;
			int lineEndX =  gChromoMap.x- lineLength;
			
			// next decide where to place the label on x					
			//determine whether the marker should go on the left or right
			boolean markersRight = false;
			
			//we want the label on the right for the rightmost genome, left for the leftmost genome
			if((features.indexOf(f) == 0 && mapSetIndexF2 == 0) ||
							(features.indexOf(f) == 1 && mapSetIndexF2 == 2))
			{
				markersRight = true;	
			}
			else
			{
				markersRight =  false;
			}
			if (markersRight)
			{				
				lineStartX = gChromoMap.x + gChromoMap.width;		
				labelX = lineStartX + lineLength; 
				lineEndX = labelX;
			}
			
			//draw a rounded rectangle as a background for the label
			g2.setColor(Colors.highlightedFeatureLabelBackgroundColour);
			float arcSize = fontHeight/1.5f;
			int horizontalGap = 3;
			int verticalGap = 4;
			RoundRectangle2D.Float backGroundRect = new RoundRectangle2D.Float(labelX - horizontalGap, labelY - fontHeight, stringWidth + horizontalGap*2,
							fontHeight + verticalGap, arcSize, arcSize);
			g2.fill(backGroundRect);
			
			// set the label colour
			g2.setColor(Colors.highlightedFeatureLabelColour);
			// draw the label
			g2.drawString(featureName, labelX, labelY);
			
			// draw a line from the marker to the label
			g2.setColor(Colors.strongEmphasisLinkColour);
			g2.drawLine(lineStartX, featureY, lineEndX, labelY - fontHeight / 2);
			
			// set the feature colour
			g2.setColor(Colors.highlightedFeatureColour);
			// draw a line for the marker on the chromosome itself
			g2.drawLine(gChromoMap.x -1, featureY, gChromoMap.x + gChromoMap.width +1, featureY);
		}
	}
	
	//	------------------------------------------------------------------------------------------------------------------------------------
	
	// draws labels next to found features in a specified range only
	public static void drawFeatureLabelsInRange(Graphics2D g2, boolean isMultiChromoRange, Vector<GChromoMap> gMaps,float intervalStart,float intervalEnd,Vector<Feature> features)
	{	
		g2.setFont(new Font("Sans-serif", Font.PLAIN, fontHeight));
		FontMetrics fm = g2.getFontMetrics();
		
		//first work out the features' y positions
		//we need to create a LinkedHashMap withe the default positons
		//these will all be at the featureY of the Feature
		LinkedHashMap<Feature, Integer> featurePositions = calculateFeaturePositions(features);
		
		//now work out the actual positions after correction for collision of labels
		LinkedHashMap<Feature, Integer> laidoutPositions = calculateLabelPositions(isMultiChromoRange, gMaps,intervalStart,intervalEnd,features, featurePositions);
		
		// for all features in our list
		for (Feature f : features)
		{
			if (f != null)
			{
				// get the name of the feature
				String featureName = f.getName();
				int stringWidth = fm.stringWidth(featureName);
				
				// this is where the label goes
				int labelY = laidoutPositions.get(f);
				int featureY = featurePositions.get(f);
				
				//apply a correction factor to move the label down by half a label height relative to the feature's y pos so
				//that the label's center on y is aligned with the feature y
				labelY = labelY + (fontHeight / 2);
				
				// next decide where to place the label on x
				int mapSetX = Math.round(f.getOwningMap().getGChromoMap().owningSet.xPosition);
				int chromoWidth = MapViewer.winMain.mainCanvas.chromoWidth;
				
				// the amount by which we want to move the label end away from the chromosome (in pixels)
				int lineLength = 50;
				int labelX = mapSetX + chromoWidth + lineLength; // this is where the label is drawn from
				int lineStartX = mapSetX + chromoWidth; // this is where the line to the label is drawn from
				int lineEndX = labelX - 2; // the label connects to the line here
				g2.setColor(Colors.foundFeatureLabelBackgroundColour);
				
				// draw a line from the marker to the label
				g2.drawLine(lineStartX, featureY, lineEndX, labelY - fontHeight / 2);
				
				//draw a rectangle as a background for the label
				float arcSize = fontHeight / 1.5f;
				int horizontalGap = 3;
				int verticalGap = 4;
				RoundRectangle2D.Float backGroundRect = new RoundRectangle2D.Float(labelX - horizontalGap, labelY - fontHeight, stringWidth + horizontalGap * 2, fontHeight + verticalGap, arcSize, arcSize);
				g2.fill(backGroundRect);
				
				// set the label font colour
				g2.setColor(Colors.highlightedFeatureLabelColour);
				// draw the label
				g2.drawString(featureName, labelX, labelY);
				
				//set the highlight colour
				g2.setColor(Colors.highlightedFeatureColour);
				// draw a line to highlight the marker on the chromosome itself
				g2.drawLine(mapSetX, featureY, mapSetX + MapViewer.winMain.mainCanvas.chromoWidth - 1, featureY);
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private static LinkedHashMap<Feature, Integer> calculateLabelPositions(boolean isMultiChromoRange, Vector<GChromoMap> gMaps,float intervalStart,float intervalEnd,Vector<Feature> features, LinkedHashMap<Feature, Integer> featurePositions)
	{
		LinkedHashMap<Feature, Integer> labelPositions = (LinkedHashMap<Feature, Integer>)featurePositions.clone();
		
		//the label's height, includes some space between text vertically
		int gap = 1;
		int labelHeight = fontHeight + gap;
		
		//first we want to work out where we start drawing the labels relative to the range start point
		//we want the labels fanning out evenly on y both up and downwards from the features themselves
		//first work out the combined height of the labels
		int totalLabelHeight = features.size() * labelHeight;
		//then the height of the interval
		
		int intervalStartPos = -1;
		int intervalEndPos = -1;
		//all features are in a single range on a single chromosome
		if(!isMultiChromoRange)
		{
			ChromoMap chromoMap = gMaps.get(0).chromoMap;
			//convert the interval values to actual pixel positions on the canvas
			intervalStartPos = (int) ((chromoMap.getGChromoMap().owningSet.chromoHeight / chromoMap.getStop()) * intervalStart);
			intervalEndPos = (int) ((chromoMap.getGChromoMap().owningSet.chromoHeight / chromoMap.getStop()) * intervalEnd);
		}
		//features stretch out over multiple neighbouring chromosomes
		else
		{
			MapViewer.logger.fine("isMultiChromoRange = " + isMultiChromoRange);
			//work out the positions on the canvas, in pixels, of the top end of the topmost chromo and the bottom end of the 
			//bottom most chromo
//			intervalStartPos = (int) gMaps.get(0).boundingRectangle.getY();
//			intervalEndPos = (int) (gMaps.get(gMaps.size()-1).boundingRectangle.getY() + gMaps.get(gMaps.size()-1).height);
			Feature topMostFeature = features.get(0);
			Feature bottomMostFeature = features.get(features.size()-1);
			
			intervalStartPos = Utils.convertRelativeFPosToPixels(topMostFeature.getOwningMap().getGChromoMap().owningSet,
							topMostFeature.getOwningMap(), topMostFeature.getStart());
			intervalEndPos = Utils.convertRelativeFPosToPixels(bottomMostFeature.getOwningMap().getGChromoMap().owningSet,
							bottomMostFeature.getOwningMap(), bottomMostFeature.getStart());
			
			MapViewer.logger.fine("gMaps.size() = " + gMaps.size()); 
			MapViewer.logger.fine("features.size() = " + features.size());
			MapViewer.logger.fine("intervalStartPos in LabelDisplayManager = " + intervalStartPos);
			MapViewer.logger.fine("intervalEndPos in LabelDisplayManager = " + intervalEndPos);
		}
		
		//size of the interval in pixels
		int intervalHeight = intervalEndPos - intervalStartPos;
		
		//the difference between the total label height and the interval height
		int differential = totalLabelHeight - intervalHeight;
		//the label offset on y relative to the start of the features themselves
		//need to subtract this from each label position
		int offset = Math.round(differential / 2.0f);	
		
		//don't want the offset to be negative
		if(differential < 0)
			offset = 0;
		
		//need to check that the label interval is no less than the height of an individual label plus some space at 
		//the top and bottom of it respectively	
		for (int i = 0; i < features.size(); i++)
		{
			try
			{
				Feature f1 = features.get(i);
				Feature f2 = features.get(i+1);
				
				if (f1 != null && f2 != null)
				{
					//if the difference between the feature y pos of this feature and that of the next one is less than the labelheight
					//then we need to shuffle them downwards
					int yDistance = labelPositions.get(f2) - labelPositions.get(f1);
					if (yDistance < labelHeight)
					{
						//move the position of feature 2 down on y by the so it is at the position of feature 1 plus one label height
						//need to make this change both to the map with the laid out positions as well as the default one because
						//the value from the latter will be used in the next iteration
						int newPos = labelPositions.get(f1) + labelHeight;
						labelPositions.put(f2, newPos);
					}
				}
			}
			//this occurs when we process the last element in the features vector -- just ignore
			catch (ArrayIndexOutOfBoundsException e)
			{
			}
		}
		
		//now subtract from each position the offset so things fan out properly
		for (Feature feature : labelPositions.keySet())
		{
			int newPos = labelPositions.get(feature) - offset;
			labelPositions.put(feature, newPos);
		}
		
		return labelPositions;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	private static LinkedHashMap<Feature, Integer> calculateFeaturePositions(Vector<Feature> features)
	{
		LinkedHashMap<Feature, Integer> featurePositions = new LinkedHashMap<Feature, Integer>();
		
		for (Feature f : features)
		{
			if (f != null)
			{
				// we need these for working out the y positions
				ChromoMap chromoMap = f.getOwningMap();
				GChromoMap gChromoMap = chromoMap.getGChromoMap();
				float mapEnd = chromoMap.getStop();
				// this factor normalises the position to a value between 0 and 100
				float scalingFactor = gChromoMap.height / mapEnd;
				// the y position of the feature itself
				int featureY;
				if (f.getStart() == 0.0f)
				{
					featureY = gChromoMap.y;
				}
				else
				{
					featureY = Math.round(gChromoMap.y + gChromoMap.currentY + (f.getStart() * scalingFactor));
				}
				//check whether the map is inverted			
				if (gChromoMap.isPartlyInverted)
				{
					featureY = (int) ((mapEnd - f.getStart()) / (mapEnd / gChromoMap.height)) + (gChromoMap.y + gChromoMap.currentY);
				}
				featurePositions.put(f, featureY);
			}
		}
		
		return featurePositions;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void drawLabelsForAllVisibleFeatures(Graphics2D g2, GMapSet gMapSet)
	{
		//draw all labels for all features on this chromosome if this has been requested at the level of the mapset
		if(gMapSet.alwaysShowAllLabels && (gMapSet.zoomFactor >= gMapSet.singleChromoViewZoomFactor))
		{
			//combine all the features from the visible maps into one
			Vector<Feature> combinedFeatures = new Vector<Feature>();
			Vector<GChromoMap> gMaps = gMapSet.getVisibleMaps();
			for (GChromoMap gMap : gMaps)
			{
				//get all the features of this map and put them into the combined features vector
				combinedFeatures.addAll(Arrays.asList(gMap.allLinkedFeatures));
			}
			
			//now draw the labels
			//only use those features that are actually visible on canvas
			LabelDisplayManager.drawFeatureLabelsInRange(g2, true, gMaps , -1, -1, Utils.checkFeatureVisibility(combinedFeatures));
		}
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
}//end class
