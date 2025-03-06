package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Comprobar la obtención del código")
    public void getCodigo_DevuelveElCodigo() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        // Act
        String codigo = grupo.getCodigo();
        // Assert
        assertEquals("G01", codigo);
    }

    @Test
    @DisplayName("Comprobar la obtención de la actividad")
    public void getActividad_DevuelveLaActividad() throws ClubException {

        //Arrange
        Grupo grupo = new Grupo("G02", "Pilates", 20, 10, 30.0);

        //Act
        String actividad = grupo.getActividad();

        //Assert
        assertEquals("Pilates", actividad); 
    }


}
