package sbrn.mapviewer.data;

/**
 * Represents a link between two features.
 */
public class Link
{
	private final Feature feature1;
	private final Feature feature2;
	private double blastScore;
	private String annotation;

	// RGB components used to represent this link's color on screen
	public int r = -1, g = -1, b = -1;

	public Link(Feature feature1, Feature feature2)
	{
		this.feature1 = feature1;
		this.feature2 = feature2;
	}

	public Feature getFeature1()
		{ return feature1; }

	public Feature getFeature2()
		{ return feature2; }

	@Override
	public String toString()
		{ return feature1 + " and " + feature2; }

	public double getScore()
	{
		return blastScore;
	}

	public void setScore(double blastScore)
	{
		this.blastScore = blastScore;
	}

	public String getAnnotation()
	{
		return annotation;
	}

	public void setAnnotation(String annotation)
	{
		this.annotation = annotation;
	}
}