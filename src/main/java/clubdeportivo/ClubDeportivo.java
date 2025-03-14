
package clubdeportivo;

import java.util.StringJoiner;


public class ClubDeportivo {

    private String nombre;
    private int ngrupos;
    private Grupo[] grupos;
    private static final int TAM = 10;

    public ClubDeportivo(String nombre) throws ClubException {
        this(nombre, TAM);
    }

    public ClubDeportivo(String nombre, int n) throws ClubException {
        if (n <= 0) {
            throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
        }
        this.nombre = nombre;
        grupos = new Grupo[n];
    }

    private int buscar(Grupo g) {
        int i = 0;
        while (i < ngrupos && !g.equals(grupos[i])) {
            i++;
        }
        if (i == ngrupos) {
            i = -1;
        }
        return i;
    }

    public void anyadirActividad(String[] datos) throws ClubException {

        try {
            int plazas = Integer.parseInt(datos[2]);
            int matriculados = Integer.parseInt(datos[3]);
            double tarifa = Double.parseDouble(datos[4]);
            Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
            anyadirActividad(g);
        } catch (NumberFormatException e) {
            throw new ClubException("ERROR: formato de número incorrecto");
        }
    }

    public void anyadirActividad(Grupo g) throws ClubException {
        if (g == null) { // ADDME: anaydido para comprobar los grupos nulos
            throw new ClubException("ERROR: el grupo es nulo");
        }
        int pos = buscar(g);
        if (pos == -1) { // El grupo es nuevo
            //ERROR DETECTADO:
            //Al intentar anyadir un grupo a un array de grupos no estabamos comprobando si ya estaba lleno
            //pudiendo asi causar un ArrayIndexOutOfBoundsException

            //SOLUCION PROPUESTA:
            //Comprobar si el array de grupos esta lleno y si lo esta lanzar una excepcion
            if (ngrupos == grupos.length) {
                throw new ClubException("ERROR: el club no puede tener mas grupos");
            }
            grupos[ngrupos] = g;
            ngrupos++;
        } else { // El grupo ya existe --> modificamos las plazas
            grupos[pos].actualizarPlazas(g.getPlazas());
        }
    }

    public int plazasLibres(String actividad) {
        int p = 0;
        int i = 0;

        //añdido para solucionar
        if (actividad == null) {
            return 0;
        }

        while (i < ngrupos) {
            //ERROR DETECTADO:
            // if (grupos[i].getActividad().equals(actividad)) {
            //     p += grupos[i].plazasLibres();
            // }
            //Al iterar sobre los grupos no comprobabamos si la actividad del grupo era nula, si hay posiciones vacias se generaria un NullPointerException

            //SOLUCION PROPUESTA:
            if (grupos[i] != null && grupos[i].getActividad().equals(actividad)) {
                p += grupos[i].plazasLibres();
            }
            i++;
        }
        return p;
    }

    public void matricular(String actividad, int npersonas) throws ClubException {
        int plazas = plazasLibres(actividad);
        if (plazas < npersonas) {
            throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
        }
        int i = 0;
        while (i < ngrupos && npersonas > 0) {
            if (actividad.equals(grupos[i].getActividad())) {
                int plazasGrupo = grupos[i].plazasLibres();
                if (npersonas >= plazasGrupo) {
                    grupos[i].matricular(plazasGrupo);
                    npersonas -= plazasGrupo;
                } else {
                    grupos[i].matricular(npersonas);
                }
            }
            i++;
        }
    }

    public double ingresos() {
        double cantidad = 0.0;
        int i = 0;
        while (i < ngrupos) {
            cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
            i++;
        }
        return cantidad;
    }

    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
        int i = 0;
        while (i < ngrupos) {
            sj.add(grupos[i].toString());
            i++;
        }
        return nombre + " --> " + sj.toString();
    }

}
