package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GrupoTest {

    @Test
    @DisplayName("Comprobar el get")
    public void getCodigo_DevuelveElCodigoDelGrupo() throws ClubException {
        // Arrange
        Grupo grupo = new Grupo("G01", "Pilates", 20, 10, 30.0);
        // Act
        String codigo = grupo.getCodigo();
        // Assert
        assertEquals("G01", codigo);
    }
}
