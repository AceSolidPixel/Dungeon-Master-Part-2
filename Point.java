/**
class Point - The Point class allows points to be referenced and set, including when points that have to be moved
*/
public class Point
{
	/** x - X coordinate of point	*/
	private int x;
	/** y - Y coordinate of point	*/
	private int y;

	/**
	* Point - Constructor that passes in the x and y values of the coordinate to be set
	* @param initX - X value of coordinate
	* @param initY - Y value of coordinate
	*/
	public Point(int initX, int initY)
	{
		x = initX;
		y = initY;
	}
	/**
	* getX - method that gets the x coordinate of a point
	* @return Returns the x coordinate
	*/
	public int getX()
	{
		return x;
	}
	/**
	* getY - method that gets the y coordinate of a point
	* @return - Returns the y coordinate
	*/
	public int getY()
	{
		return y;
	}
	/**
	* translate - Method that allows points to be moved or translated by amount dictated by x and y parameters passed in
	* @param dx - amount that will be applied to x coordinate
	* @param dy - amount that will be applied to y coordinate
	*/
	public void translate(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
}