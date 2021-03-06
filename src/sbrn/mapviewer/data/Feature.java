package sbrn.mapviewer.data;

import java.util.*;
import sbrn.mapviewer.gui.*;

/**
 * Class that represents a feature on a map. This is rather abstract for now, so
 * a Feature can literally correspond to whatever "feature" is required for a
 * given task - marker, qtl, annotation, etc.
 */
public class Feature implements Comparable<Feature>
{
	// Feature types
	public final static int GENERIC = 0;

	public final static int MARKER = 1;
	public final static int SSR = 2;
	public final static int SNP = 3;
	public final static int GENE = 4;
	//etc

	// Owning ChromoMap for this Feature
	private ChromoMap owner;

	// The name of this Feature
	private final String name;
	// And any aliases
	private final Vector<String> aliases = new Vector<String>();
	// And any links its involved in
	private final Vector<Link> links = new Vector<Link>();

	// Its start and stop positions (in whatever distance format) on the map
	private float start, stop;

	//annotation info
	private String annotation;

	// Feature type
	private String type = "generic";

	/**
	 * Constructs a new feature with the given name.
	 * @param name the name of this feature
	 */
	public Feature(String name)
	{
		this.name = name;
	}

	/**
	 * Returns a string representation of this feature. Currently its name.
	 * @return a string representation of this feature.
	 */
	@Override
	public String toString()
		{ return name; }

	/**
	 * Returns the name of this feature.
	 * @return the name of this feature
	 */
	public String getName()
		{ return name; }

	public void setStart(float start)
		{ this.start = start; }

	public float getStart()
		{ return start; }

	public void setStop(float stop)
		{ this.stop = stop; }

	public float getStop()
		{ return stop; }

	public String getType()
		{ return type; }

	public void setType(String type)
		{ this.type = type; }

	/**
	 * Returns a list of every alias that this feature is known by.
	 * @return a list of every alias that this feature is known by
	 */
	public Vector<String> getAliases()
		{ return aliases; }

	/**
	 * Adds another name alias for this feature.
	 * @param alias the name alias to add
	 */
	public void addAlias(String alias)
		{ aliases.add(alias); }

	/**
	 * Returns a reference to the ChromoMap object that contains this feature.
	 * @return a reference to the ChromoMap object that contains this feature
	 */
	public ChromoMap getOwningMap()
		{ return owner; }

	/**
	 * Returns a reference to the MapSet object that contains the ChromoMap that
	 * contains this feature. This is just a utility method to avoid having to
	 * call Feature.getOwningMap().getOwningMapSet() which has the same result.
	 * @return a reference to the MapSet object that contains the map that
	 * contains this feature
	 * @see sbrn.mapviewer.data.ChromoMap#getOwningMapSet()
	 *  ChromoMap.getOwningMapSet()
	 */
	public MapSet getOwningMapSet()
		{ return owner.getOwningMapSet(); }

	/**
	 * Sets the owning ChromoMap object for this feature.
	 * @param owner the new owning ChromoMap object for this feature
	 */
	public void setOwningMap(ChromoMap owner)
		{ this.owner = owner; }

	/**
	 * Returns a list of links that correspond to this feature. Each Link will
	 * hold a reference to this feature in either its feature1 or feature2
	 * reference.
	 * @return a list of links that correspond to this feature.
	 */
	public Vector<Link> getLinks()
		{ return links; }

	public String getAnnotation()
	{
		return annotation;
	}

	public void setAnnotation(String annotation)
	{
		this.annotation = annotation;
	}

	public int compareTo(Feature other)
	{
		if (this.start < other.start)
			return -1;
		if (this.start > other.start)
			return 1;

		return 0;
	}
	
	public String getFullFeatureInfo()
	{
		// get the feature info to display
		String featureInfo = name + " | " + type + " | " + "Pos:" + Utils.formatFeaturePosition(start) + "-" + Utils.formatFeaturePosition(stop);
		
		if(annotation != null)
			featureInfo += " | " + annotation;	
		
		return featureInfo;
	}
	
	public String getMouseOverDisplayLabel(boolean strongEmphasis, boolean isMouseOver)
	{
		String label = null;
		if(Prefs.showFullFeatureInfoOnMouseOver && !strongEmphasis && isMouseOver)
			label = getFullFeatureInfo();
		else
			label = name;		
		return label;
	}
}