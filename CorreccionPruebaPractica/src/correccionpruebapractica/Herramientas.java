package correccionpruebapractica;

public class Herramientas {
    private boolean hayDestornillador = true;
    private boolean hayTaladro = true;
    private boolean hayAlicates = true;
    
    public synchronized void obtenerHerramientas(Agente agente, boolean necesitaDestornillador, boolean necesitaTaladro, boolean necesitaAlicates) throws InterruptedException {
        while ((necesitaDestornillador && !hayDestornillador) ||
                (necesitaTaladro && !hayTaladro) ||
                (necesitaAlicates && !hayAlicates)) {
            System.out.printf("--- El agente %s espera para la actividad %s. Necesita %s.\n",
                    agente.getNombre(), agente.getActividad(), agente.getHerramientas());
            wait();
        }
        if (necesitaDestornillador) hayDestornillador = false;
        if (necesitaTaladro) hayTaladro = false;
        if (necesitaAlicates) hayAlicates = false;
        System.out.printf(">>> El agente %s inicia la actividad %s. Utiliza %s Trabaja %d segundos.\n",
                    agente.getNombre(), agente.getActividad(), agente.getHerramientas(), agente.getTiempoTrabajo());
        
    }
    
    public synchronized void devolverHerramientas(Agente agente, boolean necesitaDestornillador, boolean necesitaTaladro, boolean necesitaAlicates)  {
        if (necesitaDestornillador) hayDestornillador = true;
        if (necesitaTaladro) hayTaladro = true;
        if (necesitaAlicates) hayAlicates = true;
        System.out.printf("<<< El agente %s finaliza la actividad %s. Devuelve %s Descansa %d segundos.\n",
                    agente.getNombre(), agente.getActividad(), agente.getHerramientas(), agente.getTiempoEspera());
        
        notifyAll();
    }
}
