import iipUtil.Instant;
import java.util.Scanner;
import java.util.Locale;
/** 
 *  Clase GestorParking: gestor d'un parking.
 *  @author IIP
 *  @version Curso 2016/2017
 */
public class GestorParking {
    
    /**
     * Mostra un menu d'opcions per pantalla i
     * llegeix des de teclat una opcio valida.
     * @param tec Scanner, representa el teclat.
     * @return int, opcio valida.
     */ 
    private static int menu(Scanner tec) {
        int op;
        do {
            System.out.println("\nGestor del parking");
            System.out.println("==================");
            System.out.println("1. Estacionar");
            System.out.println("2. Retirar");
            System.out.println("3. Buscar");
            System.out.println("4. Mostrar ocupacio");
            System.out.println("5. Buidar parking");
            System.out.println("0. Eixir");
            System.out.println();
            System.out.print("Tria una opcio: ");
            op = tec.nextInt();
        } while (op < 0 || op > 5);
        tec.nextLine();
        return op;
    }
  
    /**
     * Lectura des de teclat d'un instant valid.
     * @param tec Scanner, representa el teclat.
     * @return Instant, hora valida.
     */
    private static Instant llegirInstant(Scanner tec) {
        int h, m;
        do {
            System.out.println("Dis-me una hora valida:"); 
            System.out.print("  Hores: "); h = tec.nextInt();
            System.out.print("  Minuts: "); m = tec.nextInt();
        } while (h < 0 || h > 23 || m < 0 || m > 59);
        tec.nextLine();
        return new Instant(h, m);
    }
    
    /**
     * Lectura des de teclat d'una matricula.
     * @param tec Scanner, representa el teclat.
     * @return String, la matricula.
     */
    private static String llegirMatricula(Scanner tec) {
        System.out.print("Dis-me una matricula: "); 
        String mat = tec.nextLine();        
        return mat;
    }

    /**
     * Metode principal.
     * @param args String[].     
     */
    public static void main(String [] args) {
        final String MSG_NO_ESTA = "El vehicle no esta al parking";
        final String MSG_SI_ESTA = "El vehicle ja esta al parking";
        final String MSG_PLE = "No queden llocs lliures al parking";
        
        Scanner tec = new Scanner(System.in).useLocale(Locale.US);
        // Parking p = new Parking(4, 0.015);
        Parking p = new Parking("parkingIIP.txt");
        int op, pref;
        String matricula;
        Instant h;
        Ticket t;

        do {
            op = menu(tec);
            switch(op) {
                case 1: // Estacionar
                    if (!p.estaPle()) {                       
                        matricula = llegirMatricula(tec);
                        t = p.buscarTicket(matricula);
                        if (t == null) {
                            h = llegirInstant(tec);
                            do {
                                System.out.print("Dis-me una planta ");
                                System.out.print("de preferencia (0-");
                                System.out.print((p.getNumPlantes() - 1));
                                System.out.print("): ");
                                pref = tec.nextInt();
                            } while (pref < 0 || pref >= p.getNumPlantes());
                            t = new Ticket(matricula, h);
                            p.estacionar(t, pref);
                            System.out.println(t);
                        }
                        else { System.out.print(MSG_SI_ESTA); }
                    }
                    else { System.out.print(MSG_PLE); }
                    break;
                    
                case 2: // Retirar                    
                    matricula = llegirMatricula(tec);
                    t = p.buscarTicket(matricula);
                    if (t == null) { System.out.println(MSG_NO_ESTA); }
                    else {                        
                        do {
                            System.out.print("El vehicle ha entrat a les ");
                            System.out.print(t.getEntrada());
                            System.out.print(" i ha d'eixir "); 
                            System.out.println("despres d'aquesta hora.");
                            h = llegirInstant(tec);
                        } while (t.getEntrada().aMinuts() >= h.aMinuts());
                        System.out.println(t);                        
                        System.out.printf(Locale.US, 
                            "Import: %.2f euros\n", p.retirar(t, h));
                    }
                    break;
                    
                case 3: // Buscar                    
                    matricula = llegirMatricula(tec);
                    t = p.buscarTicket(matricula);
                    if (t == null) { System.out.println(MSG_NO_ESTA); }
                    else { System.out.println(t); }
                    break;
                    
                case 4: // Mostrar ocupacio 
                    System.out.println(p);
                    break;
                    
                case 5: // Buidar parking 
                    System.out.print("Paking buit. Import total restant: ");
                    System.out.printf(Locale.US, "%.2f\n", p.buidarParking());
                    break;
                    
                case 0: // Acabar
                    System.out.println("Bye!"); 
                    break;
                    
                default: // Checkstyle
                    break;
            }
        } while (op != 0);
    }
}
