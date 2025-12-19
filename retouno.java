package retouno;

import java.util.Scanner;


public class retouno {

	public static void main(String[] args) {
		//variables que necesito 
		// Inizializo las variables necesarias
		int diasdeventas;
		double montoventastotales=0;
		int diasmasdemil=0;
		int diasmenosdequinientos=0;
		double media_ventas=0;
		boolean haysubidas = false;
		boolean haybajadas = false;
		boolean iguales = false;
		boolean todasventasmayoresoigualesquinientos = true;
		String tendencia = "";
		String rendimientoString = "";
		// TODO Auto-generated method stub
		//Inizializo el scanner y solicito por pantalla al usuario que dias de ventas a tenido
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los dias que han tenido ventas");
		//Guardo la informacion recibida en la variable diasdeventas
		diasdeventas = sc.nextInt();
		//Creo una lista array de valores double de la dimension de la cantidad de dias con ventas
		double[] listaventasdiarias = new double[diasdeventas];
		//Para cada día, leer el total de ventas en euros.
		//Validar que las ventas sean números positivos.
		//Si el valor introducido es negativo, mostrar un mensaje de error y repetir la entrada.
		
		//Hago creo una iteracion for para recorrer los dias de ventas
		for (int i = 0; i < diasdeventas; i++) {
			System.out.println("Introduce las ventas del dia "+(i+1));
			//Guardo el monto de la venta diaria en la lista de ventas 
			listaventasdiarias[i] = sc.nextDouble();
			
			//Si la venta introducida es negativa solicito que la vuelva a introducir
			if (listaventasdiarias[i]<0) {
				System.out.println("Introduce las ventas del dia en numero positivo"+(i+1));
				listaventasdiarias[i] = sc.nextDouble();
			}
			
		}
		for (int i = 0; i < diasdeventas; i++) {
			System.out.println("Venta del dia "+(i+1)+" es " +listaventasdiarias[i]);
		}
		
		//A medida que se registran las ventas, calcular:
		//El total acumulado de ventas.
		//La media de ventas diaria.
		//Cuántos días tuvieron ventas superiores a 1000 €.
		//Cuántos días tuvieron ventas inferiores a 500 €.
		//Determinar si todas las ventas fueron mayores o iguales a 500 €.
		
		
		//Creo una iteracion for para recorrer el monto de las ventas diarias
		for (int i = 0; i < listaventasdiarias.length; i++) {
			//Por cada vuelta voy guardando su monto en una variable para calcular
			//la suma de todas las ventas
			montoventastotales = montoventastotales + listaventasdiarias[i];
			
			//Si la venta es mayor a mil sumo un numero a la variable diasmasdemil
			//para saber cuantos dias tuvieron ventas superiores a mil
			if (listaventasdiarias[i]>1000) {
				diasmasdemil++;
			}
			//Si por el contrario la venta es menor a 500 sumo un numero en la variable
			//diasmenosdequinientos para contabilizar cuantos dias tienen ventas menores a 500
			else if (listaventasdiarias[i]<500) {
				diasmenosdequinientos++;		
			}
		}
		
		// Creo otro bucle for y dentro un if para saber si todas las ventas realizadas son 
		//iguales o mayores a quinientos
		for (int j = 0; j < listaventasdiarias.length; j++) {
			if (listaventasdiarias[j]<=500) {
				//Si alguna venta es igual o menor a 500 la varible booleana se convierte en false
				todasventasmayoresoigualesquinientos = false;
			}
	
		}
		//Calculo la media de ventas totales
		media_ventas = montoventastotales / diasdeventas;
		
		//Detectar si hubo una racha de días consecutivos donde las ventas fueron:
			//Crecientes (cada día mayor que el anterior).
			//Decrecientes (cada día menor que el anterior).
			//O si hubo altibajos (mezcla de aumentos y descensos).
			//O si las ventas se mantuvieron estables (mismos valores consecutivos).
		
		//Creo una iteracion for para recorrer cada venta
		for (int i = 1; i < listaventasdiarias.length; i++) {
			//Creo un condicional para valorar si hay subidas, bajadas o se mantiene igual 
			if (listaventasdiarias[i] > listaventasdiarias[i-1]) {
				
				haysubidas = true;	
			}
			else if (listaventasdiarias[i]<listaventasdiarias[i-1]) {
				haybajadas = true;
			}
			else {
				iguales = true;
			}
		}
		//Si solo hay subidas, no hay bajas y no hay iguales 
		if (haysubidas && !haybajadas && !iguales) {
			//Modifico el string a creciente
			tendencia = "Creciente";
		}
		//Si solo hay bajadas, no hay subidas y no hay iguales
		else if (haybajadas && !haysubidas && !iguales) {
			//Modifico el string tendencia a bajista
			tendencia = "Bajista";
		}
		//Si solo hay iguales, no hay bajadas ni subidas
		else if (iguales && !haybajadas && !haysubidas) {
			//Modifico el string a tofos los dias son iguales
			tendencia = "Todos los dias son iguales";
		}
		else {
			//Si hay mas de una variable true significa que la tendencia tiene altibajos
			// y lo comunico con el string tendencia
			tendencia = "La tendencia tiene altibajos";
		}
		//Clasificar el rendimiento del grupo de días según la media de ventas:
		// “Excelente”: todas las ventas ≥ 500 €
		 //“Regular”: alguna venta < 500 € pero promedio ≥ 700 €
		 //“Mala”: promedio < 500 €
		
		//Recorro el monto de las ventas diarias con un for
		for (int i = 0; i < listaventasdiarias.length; i++) {
			//Si en algun momento el condional de media de ventas igual o mayor a 700 
			// se cumple y la venta en posicion i es menor a 500 tambien se cumple
			// el string rendimientostring coje el valor de regular y salgo con un break
			if (media_ventas >= 700 && listaventasdiarias[i]<500) {
				rendimientoString = "Regular";
				break;
			}
		}
		//Creo un condicional de si todas las ventas fueron mayores a 500 y si lo es 
		//el rendimiento es excelente
		if (todasventasmayoresoigualesquinientos) {
			rendimientoString = "Excelente";
		}
		//Si la media de ventas es menor a 500 elrendimiento es malo
		if (media_ventas < 500) {
			rendimientoString="Malo";
		}
		//Al finalizar, mostrar un resumen que incluya:
		//Total acumulado y promedio de ventas.
		//Días con ventas > 1000 € y < 500 €
		//Muestro por pantalla los datos solicitados
		System.out.print("El total acumulado de ventas en euros es: "+montoventastotales+"€ con un promedio de ventas de "
				+media_ventas+" Los dias con ventas mayores a mil euros son "+diasmasdemil+" Y los dias con menos de 500 son "+diasmenosdequinientos+
				" El rendimiento fue: "+rendimientoString);
		
		
	
	}
	
	
}









