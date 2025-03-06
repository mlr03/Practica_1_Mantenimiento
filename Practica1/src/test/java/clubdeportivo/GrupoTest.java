package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Comprobar la obtención del código")
    public void getCodigo_DevuelveElCodigo() throws ClubException { //SE PONE THROWS PUES LANZA UNA EXCEPCIÓN EL CONSTRUCTOR EN CASO DE NO SER CORRECTO PUES ES UN ATRIBUTO DEL CONSTRUCTOR
        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        // Act
        String codigo = grupo.getCodigo();
        // Assert
        assertEquals("G01", codigo);

        /*
          SE PONE THROWS PUES LANZA UNA EXCEPCIÓN EL CONSTRUCTOR EN CASO DE NO 
          SE PUEDA CREAR EL OBJETO DE Arrange CORRECTAMENTE
        */
    }

    @Test
    @DisplayName("Comprobar la obtención de la actividad")
    public void getActividad_DevuelveLaActividad() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        String actividad = grupo.getActividad();

        //Assert
        assertEquals("Pilates", actividad); 

    }

    @Test
    @DisplayName("Comprobar la obtención del número de plazas")
    public void getPlazas_DevuelveElNumeroDePlazas() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int plazas = grupo.getPlazas();

        //Assert
        assertEquals(20, plazas); 
    }

    @Test
    @DisplayName("Comprobar la obtención del número de matriculados")
    public void getMatriculados_DevuelveElNumeroDeMatriculados() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int nMatriculados = grupo.getMatriculados();

        //Assert
        assertEquals(10, nMatriculados); 
    }

    @Test
    @DisplayName("Comprobar la obtención de la tarifa")
    public void getTarida_DevuelveLaTarifa() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        double tarifa= grupo.getTarifa();

        //Assert
        assertEquals(30.0, tarifa); 
    }

    @Test
    @DisplayName("Devuelve el número de plazas libres")
    public void getNumeroPlazasLibres() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int nPlazasLibres=grupo.plazasLibres();

        //Assert
        assertEquals(10, nPlazasLibres);

    }

    @Test
    @DisplayName("Comprobar que las plazas se actualizan correctamente pues se recibe un valor positivo y mayor que el número de matriculados")
    public void actualizarPlazas_Correctamente() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        int n=15;

        //Act
        grupo.actualizarPlazas(n);

        //Assert
        assertEquals(n,grupo.getPlazas());
    }

    @Test
    @DisplayName("Lanza una excepción pues se recibe un valor negativo")
    public void actualizarPlazas_Erroneamente_Negativo() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () ->grupo.actualizarPlazas(-5));  
    }

    @Test
    @DisplayName("Lanza una excepción pues se recibe un valor menor que los matriculados")
    public void actualizarPlazas_Erroneamente_Menor_Matriculados() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () ->grupo.actualizarPlazas(5));  
    }

    @Test
    @DisplayName("Comprobar que se actualiza correctamente el número de matriculados ")
    public void matricular_Correctamente() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        int n=2;

        //Act
        grupo.matricular(n);

        //Assert
        assertEquals(12,grupo.getMatriculados());
    }

    @Test
    @DisplayName("Lanza una excepción pues introduce un número negativo de matriculados")
    public void matricular_Erroneamente_Negativo() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
    
        //Act y Assert
        assertThrows(ClubException.class, () ->grupo.matricular(-5));  
    }

    @Test
    @DisplayName("Lanza una excepción pues no hay plazas disponibles")
    public void matricular_Erroneamente_No_Plazas_Libres() throws ClubException{

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () ->grupo.matricular(12));  
    }

    @Test
    @DisplayName("Probar que se imprime el resultado correcto")
    public void testToString() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        String esperado = "(G01 - Pilates - 30.0 euros - P:20 - M:10)";

        /*
        HEMOS CREADO UN STRING QUE ES EL ESPERADO
        */

        //Assert
        assertEquals(esperado, grupo.toString(), "El método toString() no funciona correctamente");

        /*
        COMPARAMOS EL STRING ESPERADO Y EL GENERADO
         */

    }

    @Test
    @DisplayName("Comprobación de grupos iguales")
    public void testEquals_GruposIguales() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G01", "Pilates", 20, 10, 30.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertTrue(res, "Los grupos deberían ser iguales");

        /* 
         PONEMOS assertTrue PUES LOS GRUPOS SON IGUALES Y QUEREMOS COMPROBAR DICHA CASUÍSTICA
         */
    }

    @Test
    @DisplayName("Comprobación de grupos diferentes")
    public void testEquals_GruposDiferentes() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G02", "Judo", 30, 5, 25.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertFalse(res, "Los grupos NO deberían ser iguales");

        /* 
         PONEMOS assertFalse PUES LOS GRUPOS SON DIFERENTES Y QUEREMOS COMPROBAR DICHA CASUÍSTICA
         */
    }


}
