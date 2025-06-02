package Tarea2.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

public class Evaluacion extends Observable {
    private ArrayList<Item> itemes;

    public Evaluacion() {
        this.itemes = new ArrayList<>();
    }

    public ArrayList<Item> getItemes() {
        return itemes;
    }

    public void setItems(ArrayList<Item> itemes) {
        this.itemes = itemes;
    }

    public void loadFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("evaluacion.csv"));
            String textLine = reader.readLine();
            while (textLine != null){
                String[] data = textLine.split(";");
                String[] answers = data[5].split("::");
                Item newItem = new Item(
                        data[0],
                        data[1],
                        data[2],
                        Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]),
                        answers);
                this.itemes.add(newItem);
                textLine = reader.readLine();
            }

            reader.close();
            this.setChanged();
            this.notifyObservers();
        }
        catch(Exception ex){
            System.out.println("El archivo no ha podido ser cargado correctamente.");
        }
    }

    public int CantTiempo(){
        int sum=0;
        for (int i = 0; i < this.itemes.size(); i++) {
            sum= sum + this.itemes.get(i).getDuration();
        }
        return sum;
    }

    public int CantPreguntas(){
        return itemes.toArray().length;
    }

    // ** Porcentaje de respuestas correctas desglosadas según tipo de ítem
    // Niveles de taxonomía:
    // - recordar
    // - entender
    // - aplicar
    // - analizar
    // - evaluar
    // - crear

    // ** Porcentaje de preguntas de tipo opción múltiple
    public float typePercentageMC(){
        float correctCountMC = 0;
        float totalCountMC = 0;

        for (Item iteme : this.itemes) {
            if (iteme.getQuestionType().equals("multChoice")) {
                totalCountMC++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountMC++;
                }
            }
        }
        if (totalCountMC == 0){
            totalCountMC = 1;
        }

        return (float) Math.round(100*(correctCountMC / totalCountMC));
    }

    // * Porcentaje de preguntas de tipo verdadero y falso
    public float typePercentageTF(){
        float correctCountTF = 0;
        float totalCountTF = 0;

        for (Item iteme : itemes) {
            if (iteme.getQuestionType().equals("trueFalse")) {
                totalCountTF++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountTF++;
                }
            }
        }
        if (totalCountTF == 0){
            totalCountTF = 1;
        }
        return (float) Math.round(100 * (correctCountTF/totalCountTF));
    }

    public float recordarPercentage(){
        float correctCountR= 0;
        float totalCountR = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("recordar")) {
                totalCountR++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountR++;
                }
            }
        }
        if (totalCountR == 0){
            totalCountR = 1;
        }
        return (float) Math.round(100 * (correctCountR/totalCountR));
    }

    // * Porcentaje de taxonomía "entender"
    public float entenderPercentage() {
        float correctCountEntender = 0;
        float totalCountEntender = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("entender")) {
                totalCountEntender++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountEntender++;
                }
            }
        }
        if (totalCountEntender == 0){
            totalCountEntender = 1;
        }
        return (float) Math.round(100 * (correctCountEntender/totalCountEntender));
    }

    // * Porcentaje de taxonomía "aplicar"
    public float aplicarPercentage() {
        float correctCountAplicar = 0;
        float totalCountAplicar = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("aplicar")) {
                totalCountAplicar++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountAplicar++;
                }
            }
        }
        if (totalCountAplicar == 0){
            totalCountAplicar = 1;
        }
        return (float) Math.round(100 * (correctCountAplicar/totalCountAplicar));
    }

    // * Porcentaje de taxonomía "analizar"
    public float analizarPercentage() {
        float correctCountAnalizar = 0;
        float totalCountAnalizar = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("analizar")) {
                totalCountAnalizar++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountAnalizar++;
                }
            }
        }
        if (totalCountAnalizar == 0){
            totalCountAnalizar = 1;
        }
        return (float) Math.round(100 * (correctCountAnalizar/totalCountAnalizar));
    }

    // * Porcentaje de taxonomía "evaluar"
    public float evaluarPercentage() {
        float correctCountEvaluar = 0;
        float totalCountEvaluar = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("evaluar")) {
                totalCountEvaluar++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountEvaluar++;
                }
            }
        }
        if (totalCountEvaluar == 0){
            totalCountEvaluar = 1;
        }
        return (float) Math.round(100 * (correctCountEvaluar/totalCountEvaluar));
    }

    // * Porcentaje de taxonomía "crear"
    public float crearPercentage() {
        float correctCountCrear = 0;
        float totalCountCrear = 0;

        for (Item iteme : itemes) {
            if (iteme.getTaxLevel().equals("crear")) {
                totalCountCrear++;
                if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                    correctCountCrear++;
                }
            }
        }
        if (totalCountCrear == 0){
            totalCountCrear = 1;
        }
        return (float) Math.round(100 * (correctCountCrear/totalCountCrear));
    }
    // * Porcentaje de taxonomía "crear"
    public float preguntaCorrecta() {
        float correctCount = 0;
        float totalCount = 0;

        for (Item iteme : itemes) {
            totalCount++;
            if (iteme.getAnswer() == iteme.getCorrectChoice()) {
                correctCount++;
            }
        }
        return (float) Math.round(100 * (correctCount/totalCount));
    }
}