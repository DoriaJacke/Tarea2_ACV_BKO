import Tarea2.backend.Evaluacion;
import Tarea2.frontend.evaluacionGUI;

public class Main {
    public static void main(String[] args) {

        // ** Backend
        Evaluacion miEvaluacion = new Evaluacion();

        // ** Frontend
        evaluacionGUI frontend = new evaluacionGUI(miEvaluacion);
        frontend.setVisible(true);
        System.out.println("Arregla el code");
    }
}