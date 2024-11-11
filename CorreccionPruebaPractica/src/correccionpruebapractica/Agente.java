package correccionpruebapractica;

import java.util.Random;


public class Agente implements Runnable {
    private final Herramientas herramientas;
    private final String nombre;
    private final String actividad;
    private final boolean necesitaDestornillador;
    private final boolean necesitaTaladro;
    private final boolean necesitaAlicates;
    private final Random random = new Random();
    private final int tiempoTrabajo = 50 + random.nextInt(151);
    private final int tiempoEspera = 100 + random.nextInt(101);

    public Agente(Herramientas herramientas, String nombre, String actividad, boolean necesitaDestornillador, boolean necesitaTaladro, boolean necesitaAlicates) {
        this.herramientas = herramientas;
        this.nombre = nombre;
        this.actividad = actividad;
        this.necesitaDestornillador = necesitaDestornillador;
        this.necesitaTaladro = necesitaTaladro;
        this.necesitaAlicates = necesitaAlicates;
    }

    public String getActividad() {
        return actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public int getTiempoTrabajo() {
        return tiempoTrabajo;
    }
    
    
    public String getHerramientas(){
        String resultado = "";
        if (necesitaDestornillador) resultado += "destornillador, ";
        if (necesitaTaladro) resultado += "taladro, ";
        if (necesitaAlicates) resultado += "alicates ";
        return resultado;
    }
    
    @Override
    public void run(){
        while (true){
            try {
                System.out.printf("El agente %s quiere realizar la actividad %s y solicita %s. \n",
                        this.getNombre(), this.actividad, this.getHerramientas());
                herramientas.obtenerHerramientas(this, necesitaDestornillador, necesitaTaladro, necesitaAlicates);
                Thread.sleep(tiempoTrabajo);
                
                herramientas.devolverHerramientas(this, necesitaDestornillador, necesitaTaladro, necesitaAlicates);
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
