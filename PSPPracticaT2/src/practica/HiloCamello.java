package practica;
import java.util.Random;

public class HiloCamello extends Thread 
{
	private Camello camello;
	private int nombre;
	public static int primero = 0;
	private static boolean fin = false;

	public HiloCamello(int n, Camello c)
	{
		nombre = n;
		camello = c;
	}
	@Override
	public void run()
	{
		//fin = false
		//actualiza el progreso y espera 1segundo
		while(fin == false)
		{
			proceso();
			try
			{
				//1000 = 1 seg
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		//fin = true
		//muestra la posicion a la que esta el camello del primero
		if(fin == true)
		{

			System.out.println("El Camello " + nombre + " a " + (Principal.recorrido - camello.getposicionActual() + " puntos del primero"));
		}
		Thread.interrupted();
	}

	//Muestra el avance de la carrera
	public synchronized void proceso()
	{
		int puntos = tirada();
		int avance = avanceCamello(puntos);
		camello.SetPosicion(avance);
		String carrera= "";
		if(fin == false)
		{
			carrera = "El Camello " + nombre + " gana " + puntos + " y lleva " + camello.getposicionActual()+ " puntos";
			camelloEnCabeza();
			if(camello.getposicionActual() == primero)
			{
				carrera = carrera + "y va en primera posición.";
			}
			else
			{
				carrera = carrera + "a " + (primero - camello.getposicionActual()) + " puntos del primero";
			}
			System.out.println(carrera);
		}

		if(camello.getposicionActual() >= Principal.recorrido)
		{

			fin = true;		
			//Muestra el camello ganador y a continuación el ranking
			try
			{
				Thread.sleep(500);
				System.out.println("META");
				System.out.println("El Camello " + nombre + " ganó");
				System.out.println("Ranking:");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			Thread.interrupted();
		}
		Thread.interrupted();
	}

	//Calcular el avance 
	public static int tirada()
	{
		int proceso = 0;
		Random rnd = new Random();
		int numeroTirada = rnd.nextInt(100);

		//Calcula el 10% de en rojo
		if (numeroTirada >= 0 && numeroTirada <= 9)
		{
			proceso = 3;
		}
		//Calcula el 20% de caer en azul
		else if(numeroTirada >= 10 && numeroTirada <= 30)
		{
			proceso = 2;
		}
		//Calcula el 40% de caer en amarillo
		else if(numeroTirada >= 30 && numeroTirada <= 70)
		{
			proceso = 1;
		}
		//Calcula el 30% de caer en ningun color
		else 
		{
			proceso = 0;
		}
		return proceso;	
	}

	//Calcular avance de los camellos
	public int avanceCamello(int proceso)
	{
		int procesoCamello = camello.getposicionActual() + proceso;
		return procesoCamello;
	}

	//Comprobar primer camello
	public void camelloEnCabeza() 
	{
		if(camello.getposicionActual() > primero)
		{
			primero = camello.getposicionActual();
		}
	}




}
