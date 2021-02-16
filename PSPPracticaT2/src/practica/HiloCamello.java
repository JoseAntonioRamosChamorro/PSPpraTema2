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

			System.out.println("El Camello " + nombre + " avanza " + (Principal.recorrido - camello.getposicionActual() + " posiciones del líder"));
		}
		Thread.interrupted();
	}

	//Muestra el avance de la carrera
	public synchronized void proceso()
	{
		int posicion = tirada();
		int avance = avanceCamello(posicion);
		camello.SetPosicion(avance);
		String carrera= "";
		if(fin == false)
		{
			carrera = "El Camello " + nombre + " avanza " + posicion + " y lleva " + camello.getposicionActual()+ " posiciones";
			camelloEnCabeza();
			//diferenciar el primero del resto
			if(camello.getposicionActual() == primero)
			{//si es el primero entrara aqui
				carrera = carrera + "y va en primera posición.";
			}
			else
			{//si es otro que no sea el primero entrara aqui
				carrera = carrera + "a " + (primero - camello.getposicionActual()) + " posiciones del primero";
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
				System.out.println("El Camello " + nombre + " ha ganado la carrera");
				System.out.println("El ranking ha quedado del siguiente modo:");
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
