package practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal 
{
	public static int camellos, recorrido = 0;
	
	public static void main(String[] args) throws IOException
	{
		// Preguntar número de camellos y la distancia
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Número de camellos: ");
		camellos = Integer.parseInt(br.readLine());
		System.out.println("Distancia del recorrido: ");
		recorrido = Integer.parseInt(br.readLine());
			
		//Marcar los hilos
		for(int i = 0; i < camellos ; i++)
		{
			new Thread(new HiloCamello(i+1, new Camello())).start();
			
		}
		br.close();
	}

}