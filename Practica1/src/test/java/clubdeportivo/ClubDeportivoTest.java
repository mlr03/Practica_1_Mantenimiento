package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {

    @Test
    @DisplayName("Creacion de un club con un tamaño de negativo")
    public void ClubDeportivo_TamanoNegativoDeClub_ThrowsExcepcion() throws ClubException {
        //Arrange, Act, Assert
        assertThrows(ClubException.class, () -> {
            ClubDeportivo club = new ClubDeportivo("ClubConTamanoNegativo", -12);
        });
    }

    @Test
    @DisplayName("Creacion de un club con un tamaño de 0")
    public void ClubDeportivo_TamanoDelClub0_ThrowsExcepcion() {
        //Arrange, Act, Assert
        assertThrows(ClubException.class, () -> {
            ClubDeportivo club = new ClubDeportivo("ClubConTamano0", 0);
        });
    }

    @Test
    @DisplayName("Añadir actividad nula")
    public void anyadirActividad_GrupoNulo_ThrowsExcepcion() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividadNula", 10);
        Grupo g = null;

        //Act, Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(g);
        });
    }

    //hay q comprobar q los grupos estan dentros de los grupos del club
    // @Test
    // @DisplayName("Añadir actividad nueva al club deportivo")
    // public void anyadirActividad_GrupoNuevo_AnyadeActividadAlClub() throws ClubException {
    //     //Arrange
    //     ClubDeportivo club = new ClubDeportivo("ClubConActividadNueva", 10);
    //     Grupo g = new Grupo("G1", "ActividadNueva", 10, 0, 10.0);
    //     //Act
    //     club.anyadirActividad(g);
    //     //Assert
    // }
    @Test
    @DisplayName("Añadir actividad que ya existe en el club deportivo")
    public void anyadirActividad_GrupoExistente_ActualizaPlazasDeLaActividad() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividadExistente", 10);
        Grupo g = new Grupo("G1", "ActividadExistente", 10, 0, 10.0);

        //Act
        club.anyadirActividad(g);

        //Assert
        assertTrue(club.plazasLibres("ActividadExistente") == g.getPlazas());
    }

    @Test
    @DisplayName("Comprobrar ingresos cuando no hay grupos devuelve 0")
    public void ingresos_ClubSinGrupos_Return0() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubSinGrupos", 10);

        //Act
        double ingresos = club.ingresos();

        //Assert
        assertEquals(0, ingresos);
    }

    @Test
    @DisplayName("Comprobar ingresos del club cuando tiene un grupo")
    public void ingresos_ClubConUnGrupo_ReturnIngresosDelClub() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConUnGrupo", 10);
        Grupo g = new Grupo("G1", "Actividad", 10, 5, 10.0);
        club.anyadirActividad(g);

        //Act
        double ingresos = club.ingresos();

        //Assert
        assertEquals(50, ingresos);
    }

    // @Test
    // @DisplayName("Comprobar ingresos del club cuando tiene varios grupos")
    // public void ingresos_ClubConVariosGrupos_ReturnIngresosDelClub() throws ClubException {
    //     //Arrange
    //     ClubDeportivo club = new ClubDeportivo("ClubConUnGrupo", 10);
    //     Grupo g = new Grupo("G1", "Actividad", 10, 5, 10.0);
    //     club.anyadirActividad(g);
    //     //Act
    //     double ingresos = club.ingresos();
    //     //Assert
    //     assertEquals(50, ingresos);
    // }
}
