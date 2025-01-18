package Classes;


public class Parcel_Dimensions 
{
    private double height;
    private double width;
    private double length;
    private double weight;

    public Parcel_Dimensions(double width, double height, double length, double weight)
    {
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
    }



    public double getLength()
	{
		return length;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

    public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

    public double [] getDimensions()
    {
        return new double[] {this.width, this.height, this.length};

    }

    public double getVolume()
    {
        return (this.width * this.height * this.length);
    }

    public double getCostMulitplier()
    {
        return (this.width * this.height * this.length * this.weight);
    }

	@Override
	public String toString() 
	{
    	return "Dimensions[Height=" + height + 
           "m, Width=" + width + 
           "m, Length=" + length + 
           "m, Weight=" + weight + "kg]";
	}

    
}

