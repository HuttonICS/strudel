package sbrn.mapviewer.gui.tests.syntenyviewer2d;

/**
 * Class representing a graphical representation of a chromosomes. Assumes all chromosomes in a genome laid out vertically in a column.
 */
public class Chromosome
{
	// these variables all pertain to drawing the chromosome on a canvas
	// chromosome length in pixels
	int length;
	//chromosome width in pixels
	int width;
	// chromosome x position -- upper left corner
	int xPosition;
	// chromosome y position -- upper left corner
	int yPosition;
	//the index of the chromosome in the genome, starting at 0
	int genomeIndex;
	
	public Chromosome(int length, int width, int xPosition, int yPosition,int genomeIndex)
	{
		this.length = length;
		this.width = width;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.genomeIndex = genomeIndex;
	}

}
