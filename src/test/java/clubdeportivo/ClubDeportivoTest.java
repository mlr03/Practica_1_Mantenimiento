package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {

    @Test
    @DisplayName("Creacion de un club con un tamaño de negativo lanza ClubException")
    public void ClubDeportivo_TamanoNegativoDeClub_LanzaClubException() throws ClubException {
        //Arrange, Act, Assert
        assertThrows(ClubException.class, () -> {
            ClubDeportivo club = new ClubDeportivo("ClubConTamanoNegativo", -12);
        });
    }

    @Test
    @DisplayName("Creacion de un club con un tamaño de 0 lanza ClubException")
    public void ClubDeportivo_TamanoDelClub0_LanzaClubException() {
        //Arrange, Act, Assert
        assertThrows(ClubException.class, () -> {
            ClubDeportivo club = new ClubDeportivo("ClubConTamano0", 0);
        });
    }

    @Test
    @DisplayName("Añadir actividad con datos válidos se añade correctamente al club")
    public void anyadirActividad_DatosValidos_SeAñadeCorrectamente() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club Ejemplo");
        String[] datosValidos = {"G01", "Pilates", "20", "10", "30.0"};

        //Act
        club.anyadirActividad(datosValidos);

        //Assert
        assertTrue(club.toString().contains("Pilates"));
    }

    @Test
    @DisplayName("Añadir actividad con menos datos de los esperados a un club lanza ClubException")
    public void anyadirActividad_DatosInsuficientes_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club Ejemplo");
        String[] datosInsuficientes = {"G01", "Pilates", "20", "10"};

        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            club.anyadirActividad(datosInsuficientes);
        });
    }

    @Test
    @DisplayName("Añadir actividad con plazas no numericas a un club lanza ClubException")
    public void anyadirActividad_PlazasNoNumericas_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club_Ejemplo");
        String[] datosInvalidos = {"G01", "Pilates", "XX", "10", "30.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    @Test
    @DisplayName("Añadir actividad con matriculados no numerico a un club lanza ClubException")
    public void anyadirActividad_MatriculadosNoNumerico_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club_Ejemplo");
        String[] datosInvalidos = {"G01", "Pilates", "20", "YY", "30.0"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    @Test
    @DisplayName("Añadir actividad con tarifa no numerica a un club lanza ClubException")
    public void anyadirActividad_TarifaNoNumerica_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("Club_Ejemplo");
        String[] datosInvalidos = {"G01", "Pilates", "20", "10", "ZZ"};

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datosInvalidos);
        });
    }

    @Test
    @DisplayName("Añadir actividad nula al club deportivo lanza ClubException")
    public void anyadirActividad_ActividadNula_LanzaClubExcepcion() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividadNula", 10);
        Grupo g = null;

        //Act, Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(g);
        });
    }

    @Test
    @DisplayName("Añadir actividad nueva al club deportivo añade la actividad al club")
    public void anyadirActividad_ActividadNueva_AnyadeActividadAlClub() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividadNueva");
        Grupo g = new Grupo("G1", "ActividadNueva", 10, 0, 10.0);

        //Act
        club.anyadirActividad(g);

        //Assert
        assertEquals(10, club.plazasLibres("ActividadNueva"));
    }

    @Test
    @DisplayName("Añadir actividad que ya existe en el club deportivo actualiza las plazas de la actividad")
    public void anyadirActividad_ActividadExistente_ActualizaPlazasDeLaActividad() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividadExistente");
        Grupo grupoOriginal = new Grupo("G1", "ActividadExistente", 10, 0, 10.0);
        club.anyadirActividad(grupoOriginal);
        Grupo grupoActualizado = new Grupo("G1", "ActividadExistente", 20, 0, 10.0); //mismo codigo y actividad pero distintas plazas

        //Act
        club.anyadirActividad(grupoActualizado);

        //Assert
        assertEquals(20, club.plazasLibres("ActividadExistente"));
    }

    @Test
    @DisplayName("Añadir actividad cuando el club esta lleno lanza ClubException")
    public void anyadirActividad_ClubLleno_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("ClubLleno", 1);
        club.anyadirActividad(new Grupo("G1", "Pilates", 10, 5, 15.0));

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(new Grupo("G2", "Crossfit", 15, 5, 25.0));
        });
    }

    @Test
    @DisplayName("Comprobar las plazas libres de una actividad que no existe en el club devuelve 0")
    public void plazasLibres_ActividadNoExisteEnClub_Return0() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubSinActividades");

        //Act
        int plazas = club.plazasLibres("ActividadNoExistente");

        //Assert
        assertEquals(0, plazas);
    }

    @Test
    @DisplayName("Comprobar las plazas libres de una actividad null devuelve 0")
    public void plazasLibres_ActividadNull_Return0() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividades");

        // Act
        int plazas = club.plazasLibres(null);

        // Assert
        assertEquals(0, plazas);
    }

    @Test
    @DisplayName("Comprobar las plazas libres de una actividad que existe en el club devuelve las plazas libres")
    public void plazasLibres_ActividadExisteEnClub_ReturnPlazasLibres() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividades");
        Grupo g = new Grupo("G1", "Actividad", 10, 2, 10.0);
        club.anyadirActividad(g);

        //Act
        int plazas = club.plazasLibres("Actividad");

        //Assert
        assertEquals(8, plazas);
    }

    @Test
    @DisplayName("Matricular a un numero de personas mayor que las plazas libres de una actividad lanza ClubException")
    public void matricular_PersonasAMatricularMayorQuePlazasLibres_LanzaClubException() throws ClubException {
        //Arrange
        ClubDeportivo club = new ClubDeportivo("ClubConActividades");
        Grupo g = new Grupo("G1", "Actividad", 10, 2, 10.0);
        club.anyadirActividad(g);

        //Act, Assert
        assertThrows(ClubException.class, () -> {
            club.matricular("Actividad", 10);
        });
    }



    //////////////////////////////ME QUEDE POR EL METODO MATRICULAR, necesitamos añadir mas test
//sin revisar
    @Test
    @DisplayName("Matricular en una actividad que no existe en el club lanza ClubException")
    public void matricular_ActividadNoExisteEnClub_LanzaClubException() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("ClubEjemplo");

        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular("Yoga", 3),
                "Si la actividad no existe en el club, debería lanzar una excepción.");
    }

    @Test
    @DisplayName("Matricular menos personas que plazas disponibles en un grupo")
    void matricular_MenosPersonasQuePlazas() throws ClubException {
        // Arrange
            ClubDeportivo club = new ClubDeportivo("ClubEjemplo",3);

            Grupo grupo_2 = new Grupo("G1", "Yoga", 4, 0, 10.0);
            Grupo grupo_1 = new Grupo("G2", "Yoga", 3, 0, 10.0);

            club.anyadirActividad(grupo_1);
            club.anyadirActividad(grupo_2);
        
        // Act
            club.matricular("Yoga", 3);

        // Assert
            assertEquals(4, club.plazasLibres("Yoga"));
    }

    @Test
    @DisplayName("Matricular exactamente el número de plazas de un solo grupo")
    public void matricular_ExactamentePlazasDeUnGrupo() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("ClubEjemplo", 3);

        Grupo grupo_2 = new Grupo("G1", "Yoga", 4, 0, 10.0); 
        Grupo grupo_1 = new Grupo("G2", "Yoga", 3, 0, 10.0); 

        club.anyadirActividad(grupo_1);
        club.anyadirActividad(grupo_2);

        // Act
        club.matricular("Yoga", 5);  

        // Assert
        assertEquals(2, club.plazasLibres("Yoga")); 
    }

    
    
    @Test
    @DisplayName("Distribuir correctamente los estudiantes entre grupos con plazas disponibles")
    public void matricular_DistribuyeCorrectamenteEntreGrupos() throws ClubException {
        // Arrange
        ClubDeportivo club = new ClubDeportivo("ClubEjemplo", 3);
        
        Grupo grupo_2 = new Grupo("G1", "Yoga", 4, 0, 10.0);
        Grupo grupo_1 = new Grupo("G2", "Yoga", 3, 0, 10.0); 

        club.anyadirActividad(grupo_1);
        club.anyadirActividad(grupo_2);

        // Act
        club.matricular("Yoga", 5); 

        // Assert
        assertEquals(2, club.plazasLibres("Yoga"));  
       
    }

    // @Test
    // @DisplayName("Debe matricular personas cuando la actividad coincide con la del grupo")
    // public void testMatricularConActividadCoincidente() throws ClubException {
    //     // Arrange
    //     ClubDeportivo club = new ClubDeportivo("ClubEjemplo", 3);
    //     Grupo grupo = new Grupo("G1", "Yoga", 10, 0, 10.0); 
    //     club.anyadirActividad(grupo);
    
    //     // Act
    //     club.matricular("Yoga", 5);  
    
    //     // Assert
    //     assertEquals(5, grupo.getMatriculados()); 
    //     assertEquals(5, grupo.plazasLibres());    
    // }

    // @Test
    // @DisplayName("Debe matricular personas cuando la actividad es nula en ambos casos")
    // public void testMatricularConActividadNula() throws ClubException {
    //     // Arrange
    //     ClubDeportivo club = new ClubDeportivo("ClubEjemplo", 3);
    //     Grupo grupoConActividadNula = new Grupo("G1", null, 10, 0, 10.0); 
    //     club.anyadirActividad(grupoConActividadNula);
    
    //     // Act
    //     club.matricular(null, 5); 
    
    //     // Assert
    //     assertEquals(5, grupoConActividadNula.getMatriculados()); 
    //     assertEquals(5, grupoConActividadNula.plazasLibres());  
    
    // }
  

    @Test
    @DisplayName("Comprobar ingresos cuando no hay grupos devuelve 0")
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
    // 

   
    



}

