package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ClubDeportivoAltoRendimientoTest {

    @Test
    @DisplayName("Creacion de un club con un tamaño de negativo lanza ClubException")
    public void ClubDeportivoAltoRendimiento_TamanoNegativoDeClub_LanzaClubException() throws ClubException {
        
        // Act & Assert
        assertThrows(ClubException.class, () -> { 
            new ClubDeportivoAltoRendimiento("clubEjemplo", 10, 20, -5.0);
        }); 

        /*
            PUEDE SER QUE NO SE TENGA QUE INSTANCIAR 
            NINGUN OBJETO EN ARRANGE Y QUE NO HAYA (PARA PROBAR EN LOS CONSTRUCTORES VALORES 
            INVÁLIDOS DE ALGÚN ATRIBUTO POR EJEMPLO USAR LA ESTRUCRTURA DE PRUEBA ANTERIOR)
        */

        /*
            SI HAY QUE PROBAR VALORES INVÁLIDOS (NEGATIVOS O CERO) 
            PROBAR EN PRUEBAS DIFERENTES
        */
    }

    @Test
    @DisplayName("Creacion de un club con un tamaño de 0 lanza ClubException")
    public void ClubDeportivoAltoRendimiento_TamanoDelClub0_LanzaClubException() throws ClubException {
        
        // Act & Assert
        assertThrows(ClubException.class, () -> { 
            new ClubDeportivoAltoRendimiento("clubEjemplo", 10, 0, 15.0);
        });

    }

    

    @Test
    @DisplayName("Debe lanzar una excepción si faltan datos al añadir una actividad")
    public void AnyadirActividad_FaltanDatos_LanzaClubExcepcion() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Alto Rendimiento", 20, 10.0);
        String[] datosFaltantes = {"G1", "CrossFit", "25"}; // Falta tarifa y matriculados

        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datosFaltantes));
    }

    @Test
    @DisplayName("Debe calcular los ingresos correctamente con incremento aplicado")
    public void Ingresos_conIncremento_ReturnIngresosClub() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Alto Rendimiento", 20, 10.0);
        String[] datos1 = {"G1", "Natación", "10", "5", "30.0"}; // 5 matriculados
        String[] datos2 = {"G2", "Futbol", "15", "10", "25.0"}; // 10 matriculados
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);

        double ingresosEsperados = (5 * 30.0) + (10 * 25.0); // Sin incremento
        double ingresosConIncremento = ingresosEsperados + ingresosEsperados * (10.0 / 100); // Con incremento del 10%

        // Act
        double ingresos = club.ingresos();

        // Assert
        assertEquals(ingresosConIncremento, ingresos, 0.01); // Comparar con margen de error
    }

  

}



