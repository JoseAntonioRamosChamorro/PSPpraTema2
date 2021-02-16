package practica;

public class Camello 
{
	private int nombre;
	private int posicionActual;

	public Camello()
	{
		nombre = 0;
		posicionActual = 0;
	}
	public Camello(int n, int p)
	{
		nombre = n;
		posicionActual = p;
	}
	public int getNombre(int n)
	{
		return nombre;
	}
	public int getposicionActual() 
	{
		return posicionActual; 
	}
	public void setNombreCamello(int n)
	{
		nombre = n;
	}
	public void SetPosicion(int p)
	{
		posicionActual = p;
	}
}