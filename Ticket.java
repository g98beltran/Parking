import iipUtil.Instant;
/** Classe Ticket: defineix les dades d'estacionament 
 *  d'un vehicle en un parking: matricula, hora d'entrada 
 *  i localitzacio (numero de planta i numero de lloc).
 *  @author IIP
 *  @version Curs 2016/2017
 */
public class Ticket {
    private String matricula;
    private Instant entrada;    
    private int planta;
    private int lloc;
    
    /** Crea un Ticket per a un vehicle amb una matricula i 
     *  una hora d'entrada donades, sense localitzacio assignada.
     *  @param m String, la matricula.
     *  @param h Instante, l'hora d'entrada al parking.      
     */
    public Ticket(String mat, Instant hEnt) {
        matricula = mat;     
        entrada = hEnt;   
        planta = -1;
        lloc = -1;
    }

    /** Torna la matricula.
     *  @return String, la matricula. 
     */
    public String getMatricula() { return matricula; }

    /** Torna l'hora d'entrada.
     *  @return Instante, l'hora d'entrada. 
     */
    public Instant getEntrada() { return entrada; }    

    /** Torna la planta.
     *  @return int, la planta. 
     */
    public int getPlanta() { return planta; }
    
    /** Torna el lloc.
     *  @return int, el lloc. 
     */
    public int getLloc() { return lloc; }
        
    /** Actualitza la planta.
     *  @param plt int, la planta.
     */
    public void setPlanta(int plt) { planta = plt; }
    
    /** Actualitza el lloc.
     *  @param plz int, el lloc.
     */
    public void setLloc(int plz) { lloc = plz; }
    
    /** Torna un String representant les dades del Ticket 
     *  en el seguent format: <br>
     *  - si el ticket NO te localitzacio: <pre>
     *    "Matricula: MATRICULA - Entrada: ENTRADA" 
     *    </pre>
     *  - si el ticket SI te localitzacio: <pre>
     *    "Matricula: MATRICULA - Entrada: ENTRADA - Planta: PLANTA - Lloc: LLOC"
     *    </pre>
     *  @return String, la representacio del Ticket.
     */
    public String toString() {
        String res = "Matricula: " + matricula 
            + " - Entrada: " + entrada;
        if(planta >= 0 || lloc >= 0){
        res = res + " - Planta: " + planta + " - Lloc: " + lloc;
        }
        return res;
    }
}
