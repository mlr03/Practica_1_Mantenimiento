package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Creacion de grupo con numero de plazas negativas lanza ClubException")
    public void Grupo_NumeroPlazasNegativas_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", -20, 10, 30.0);
        });
    }

    @Test
    @DisplayName("Creacion de grupo con numero de plazas igual a 0 lanza ClubException")
    public void Grupo_NumeroPlazasCero_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", 0, 10, 30.0);
        });
    }

    @Test
    @DisplayName("Creacion de grupo con numero de matriculados menor que 0 lanza ClubException")
    public void Grupo_MatriculadosNegativos_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", 20, -1, 30.0);
        });
    }

    @Test
    @DisplayName("Creacion de grupo con tarifa negativa lanza ClubException")
    public void Grupo_TarifaNegativa_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", 20, 10, -30.0);
        });
    }

    @Test
    @DisplayName("Creacion de grupo con tarifa igual a 0 lanza ClubException")
    public void Grupo_TarifaCero_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", 20, 10, 0.0);
        });
    }

    @Test
    @DisplayName("Creacion de grupo con numero de matriculados mayor que el numero de plazas lanza ClubException")
    public void Grupo_MatriculadosMayorQueNPlazas_LanzaClubException() {
        // Act y Assert
        assertThrows(ClubException.class, () -> {
            new Grupo("G01", "Pilates", 20, 30, 30.0);
        });
    }

    @Test
    @DisplayName("Obtener el codigo del grupo devuelve el codigo correcto")
    public void getCodigo_DevuelveElCodigo() throws ClubException { //SE PONE THROWS PUES LANZA UNA EXCEPCIÓN EL CONSTRUCTOR EN CASO DE NO SER CORRECTO PUES ES UN ATRIBUTO DEL CONSTRUCTOR

        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        // Act
        String codigo = grupo.getCodigo();

        // Assert
        assertEquals("G01", codigo);

    }

    @Test
    @DisplayName("Obtener la actividad devuelve la actividad correcta")
    public void getActividad_DevuelveLaActividad() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        String actividad = grupo.getActividad();

        //Assert
        assertEquals("Pilates", actividad);

    }

    @Test
    @DisplayName("Obtener el numero de plazas devuelve el numero de plazas correcto")
    public void getPlazas_DevuelveElNumeroDePlazas() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int plazas = grupo.getPlazas();

        //Assert
        assertEquals(20, plazas);
    }

    @Test
    @DisplayName("Obtener el numero de matriculados devuelve el numero de matriculados correcto")
    public void getMatriculados_DevuelveElNumeroDeMatriculados() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int nMatriculados = grupo.getMatriculados();

        //Assert
        assertEquals(10, nMatriculados);
    }

    @Test
    @DisplayName("Obtener la tarifa devuelve la tarifa correcta")
    public void getTarifa_DevuelveLaTarifa() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        double tarifa = grupo.getTarifa();

        //Assert
        assertEquals(30.0, tarifa);
    }

    @Test
    @DisplayName("Obtener el numero de plazas libres devuelve el numero de plazas libres correcto")
    public void plazasLibres_DevuelveNumeroPlazasLibres() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act
        int nPlazasLibres = grupo.plazasLibres();

        //Assert
        assertEquals(10, nPlazasLibres);

    }

    @Test
    @DisplayName("Actualizar plazas con un valor mayor que 0 y mayor igual que los matriculados actualiza las plazas")
    public void actualizarPlazas_Correctamente() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        int n = 15;

        //Act
        grupo.actualizarPlazas(n);

        //Assert
        assertEquals(n, grupo.getPlazas());
    }

    @Test
    @DisplayName("Actualizar plazas con un valor negativo lanza ClubException")
    public void actualizarPlazas_ValorNegativo_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(-5);
        });
    }

    @Test
    @DisplayName("Actualizar plazas con un valor igual a 0 lanza ClubException")
    public void actualizarPlazas_ValorCero_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(0);
        });
    }

    @Test
    @DisplayName("Actualizar plazas con un valor menor que los matriculados lanza ClubException")
    public void actualizarPlazas_ValorMenorAMatriculados_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(5);
        });
    }

    @Test
    @DisplayName("Matricular a un numero valido de personas a un grupo actualiza el numero de matriculados")
    public void matricular_NumeroValido_ActualizaElNumeroDeMatriculados() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        int n = 2;

        //Act
        grupo.matricular(n);

        //Assert
        assertEquals(12, grupo.getMatriculados());
    }

    @Test
    @DisplayName("Matricular a un numero negativo de personas a un grupo lanza ClubException")
    public void matricular_NumeroNegativo_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.matricular(-5);
        });
    }

    @Test
    @DisplayName("Matricular a un numero de personas igual a 0 lanza ClubException")
    public void matricular_NumeroCero_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.matricular(0);
        });
    }

    @Test
    @DisplayName("Matricular a un numero de personas mayor que las plazas libres lanza ClubException")
    public void matricular_NumeroMayorAPlazasLibres_LanzaClubException() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        //Act y Assert
        assertThrows(ClubException.class, () -> {
            grupo.matricular(12);
        });
    }

    @Test
    @DisplayName("Probar que se imprime el grupo con el formato correcto")
    public void toString_DevuelveFormatoStringCorrecto() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        String esperado = "(G01 - Pilates - 30.0 euros - P:20 - M:10)";
        /*
        HEMOS CREADO UN STRING QUE ES EL ESPERADO
         */

        //Act
        String resultado = grupo.toString();

        //Assert
        assertEquals(esperado, resultado);

        /*
        COMPARAMOS EL STRING ESPERADO Y EL GENERADO
         */
    }

    @Test
    @DisplayName("Comprobación de grupos iguales devuelve true")
    public void equals_GrupoIgual_DevuelveTrue() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G01", "Pilates", 20, 10, 30.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertTrue(res);

        /* 
         PONEMOS assertTrue PUES LOS GRUPOS SON IGUALES Y QUEREMOS COMPROBAR DICHA CASUÍSTICA
         */
    }

    @Test
    @DisplayName("Comprobación de grupos diferentes devuelve false")
    public void equals_GrupoDiferente_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G02", "Judo", 30, 5, 25.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertFalse(res);

        /* 
         PONEMOS assertFalse PUES LOS GRUPOS SON DIFERENTES Y QUEREMOS COMPROBAR DICHA CASUÍSTICA
         */
    }

    @Test
    @DisplayName("Comprobación de grupos con el mismo codigo pero diferente actividad devuelve false")
    public void equals_MismoCodigoDistintaActividad_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G01", "Judo", 30, 5, 25.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertFalse(res);

    }

    @Test
    @DisplayName("Equals con un objeto nulo devuelve false")
    public void equals_ObjetoNulo_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);

        // Act
        boolean res = grupo.equals(null);

        // Assert
        assertFalse(res);

    }

    @Test
    @DisplayName("Equals con un objeto de otra clase devuelve false")
    public void equals_OtroObjeto_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Object obj = new Object();

        // Act
        boolean res = grupo.equals(obj);

        // Assert
        assertFalse(res);

    }

    @Test
    @DisplayName("Equals con un objeto de la misma clase pero diferente codigo devuelve false")
    public void equals_MismaActividadDistintoCodigo_DevuelveFalse() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G02", "Pilates", 20, 10, 30.0);

        // Act
        boolean res = grupo_1.equals(grupo_2);

        // Assert
        assertFalse(res);

    }

    @Test
    @DisplayName("Hashcode de dos grupos iguales devuelve el mismo hash")
    public void hashCode_GruposIguales_DevuelveElMismoHash() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G01", "Pilates", 20, 10, 30.0);

        // Act
        int hash_1 = grupo_1.hashCode();
        int hash_2 = grupo_2.hashCode();

        // Assert
        assertEquals(hash_1, hash_2);

    }

    @Test
    @DisplayName("Hashcode de dos grupos diferentes devuelve diferente hash")
    public void hashCode_GruposDiferentes_DevuelveDiferenteHash() throws ClubException {
        // Arrange
        Grupo grupo_1 = new Grupo("G01", "Pilates", 20, 10, 30.0);
        Grupo grupo_2 = new Grupo("G02", "Judo", 30, 5, 25.0);

        // Act
        int hash_1 = grupo_1.hashCode();
        int hash_2 = grupo_2.hashCode();

        // Assert
        assertFalse(hash_1 == hash_2);

    }
}
