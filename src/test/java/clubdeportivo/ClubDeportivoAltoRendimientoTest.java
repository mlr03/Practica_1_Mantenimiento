package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {


    @Test
    @DisplayName("Creación de ClubDeportivoAltoRendimiento con valores correctos y tamaño especificado se inicializa correctamente")
    public void ClubDeportivoAltoRendimiento_ValoresCorrectosConTamano_SeInicializaCorrectamente() throws ClubException {
        // Arrange, Act
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 10, 20, 10.0);

        // Assert
        assertNotNull(club);
    }


    @Test
    @DisplayName("Creacion de un club con un maximo negativo o cero lanza ClubException")
    public void ClubDeportivoAltoRendimiento_MaximoNegativoOCero_LanzaClubException() {
        // Act & Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("clubEjemplo", -20, 5.0);
        });
    }

    @Test
    @DisplayName("Creacion de un club con un incremento negativo o cero lanza ClubException")
    public void ClubDeportivoAltoRendimiento_IncrementoNegativoOCero_LanzaClubException() throws ClubException {
        // Act & Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("clubEjemplo", 20, -5.0);
        });

    }

    @Test
    @DisplayName("Creacion de un club con un maximo negativo o cero con tamaño valido lanza ClubException")
    public void ClubDeportivoAltoRendimiento_MaximoNegativoOCeroConTamanio_LanzaClubException() {
        // Act & Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("clubEjemplo", 10, -20, 5.0);
        });
    }

    @Test
    @DisplayName("Creacion de un club con un incremento negativo o cero con tamaño valido lanza ClubException")
    public void ClubDeportivoAltoRendimiento_IncrementoNegativoOCeroConTamanio_LanzaClubException() throws ClubException {
        // Act & Assert
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("clubEjemplo", 10, 20, -5.0);
        });

    }

    //CREO Q NO HACE FALTA PQ SE PRUEBA EN EL CLUB NORMAL
    @Test
    @DisplayName("Añadir actividad con datos válidos se añade correctamente")
    public void anyadirActividad_DatosValidos_SeAñadeCorrectamente() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosValidos = {"G01", "Crossfit", "15", "5", "40.0"};
        // Act
        club.anyadirActividad(datosValidos);
        // Assert
        assertEquals(10, club.plazasLibres("Crossfit"));
    }

    @Test
    @DisplayName("Añadir actividad con datos insuficientes lanza ClubException")
    public void anyadirActividad_DatosInsuficientes_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosInsuficientes = {"G01", "Crossfit", "15"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInsuficientes);
        });
    }

    @Test
    @DisplayName("Añadir actividad con plazas no numéricas lanza ClubException")
    public void anyadirActividad_PlazasNoNumericas_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosInvalidos = {"G01", "Crossfit", "XX", "5", "40.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    @Test
    @DisplayName("Añadir actividad con matriculados no numéricos lanza ClubException")
    public void anyadirActividad_MatriculadosNoNumerico_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosInvalidos = {"G01", "Crossfit", "15", "YY", "40.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    @Test
    @DisplayName("Añadir actividad con tarifa no numérica lanza ClubException")
    public void anyadirActividad_TarifaNoNumerica_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosInvalidos = {"G01", "Crossfit", "15", "5", "ZZ"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    //NO SE SI SE DEBEN COMPROBAR CON ESOS ASSERT O CON EL STRING?
    @Test
    @DisplayName("Añadir actividad con plazas mayores al máximo permitido ajusta al máximo")
    public void anyadirActividad_PlazasMayoresQueMaximo_AjustaAlMaximo() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosSuperanMaximo = {"G01", "Crossfit", "50", "5", "40.0"};

        // Act
        club.anyadirActividad(datosSuperanMaximo);

        // Assert
        assertEquals(15, club.plazasLibres("Crossfit")); // 20 - 5 = 15 plazas libres
    }

    @Test
    @DisplayName("Añadir actividad con plazas dentro del límite añade la actividad")
    public void anyadirActividad_PlazasDentroDelMaximo_SeAñadenCorrectamente() throws ClubException {

        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datosDentroMaximo = {"G01", "Crossfit", "18", "5", "40.0"};

        // Act
        club.anyadirActividad(datosDentroMaximo);

        // Assert
        assertEquals(13, club.plazasLibres("Crossfit")); // 18 - 5 = 13 plazas libres
    }

    //NO SE SI FALTAN MAS TESTS DE INGRESOS
    @Test
    @DisplayName("Calcular los ingresos correctamente con incremento aplicado")
    public void ingresos_conIncremento_ReturnIngresosCorrectos() throws ClubException {

        // Arrange
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubElite", 20, 10.0);
        String[] datos = {"G1", "Natacion", "10", "5", "30.0"};
        club.anyadirActividad(datos);

        // Act
        double ingresos = club.ingresos();

        // Assert
        assertEquals(165.0, ingresos, 0.01);
    }

}
