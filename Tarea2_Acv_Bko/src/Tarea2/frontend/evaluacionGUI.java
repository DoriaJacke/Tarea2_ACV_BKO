package Tarea2.frontend;

// * Importando clases del 'backend'
import Tarea2.backend.Item;
import Tarea2.backend.Evaluacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class evaluacionGUI extends JFrame implements Observer {
    private JPanel window;
    private JPanel Cartas;
    private JButton Iniciar = new JButton("Iniciar");
    private JLabel Instruccion= new JLabel("Presione el borton para iniciar la prueba",JLabel.CENTER    );
    private JLabel Tiempo_Estimado= new JLabel("",JLabel.CENTER    );
    private JLabel Titulo = new JLabel("Bienvenido a la prueba",JLabel.CENTER );
    private JLabel CantPreguntas= new JLabel("",JLabel.CENTER );
    private JButton Siguiente = new JButton("Siguiente");
    private JButton Anterior = new JButton("Anterior");
    private JButton Finalizar = new JButton("Finalizar");
    private JButton Resumen = new JButton("Resumen");
    private Evaluacion backend;

    public evaluacionGUI(Evaluacion backend) {
        this.CrearPantallaInicio();
        this.backend = backend;
        this.backend.loadFromFile();
        this.CrearPantallaCartas();
        this.backend.addObserver(this);
        this.setContentPane(this.window);


        CantPreguntas.setText("Cantida de preguntas en la prueba: " + backend.CantPreguntas());
        Tiempo_Estimado.setText("Tiempo estimado para la evaluacion: " + backend.CantTiempo()+" minutos");


        // Finalización
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();


    //Listener para los distintos botones para navegar la prueba
        this.Iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) Cartas.getLayout()).next(Cartas);
            }
        });
        this.Siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) Cartas.getLayout()).next(Cartas);
            }
        });
        this.Anterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) Cartas.getLayout()).previous(Cartas);
            }
        });

        this.Finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearResumen();
                revisionPreguntas();
                ((CardLayout) Cartas.getLayout()).next(Cartas);
            }
        });
        this.Resumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout1= (CardLayout) Cartas.getLayout();
                cardLayout1.show(Cartas, "Resumen");
            }
        });
    }
    //Metodo que permite crear la pantalla principal del GUI
    public void CrearPantallaInicio() {
        //Se crea el tipo de Layout y el panel correspondiente
        BorderLayout border = new BorderLayout();
        GridLayout inicio = new GridLayout(4,1);
        JPanel Cont = new JPanel(border);

        //Se agregan elementos al panel de
        Cont.add(Iniciar, BorderLayout.SOUTH);
        JPanel Textos= new JPanel(inicio);
        Textos.add(Titulo);
        Textos.add(CantPreguntas);
        Textos.add(Tiempo_Estimado);
        Textos.add(Instruccion);
        Cont.add(Textos, BorderLayout.CENTER);

        // Agregar la tarjeta del inicio a las tarjetas
        Cartas.add(Cont);
    }

    //Funcion que por codigo crea los distintos paneles con cada pregunta y sus respectivas respuestas
    public void CrearPantallaCartas() {
        int i = 1;
        for (Item a : this.backend.getItemes()) {
            //Se obtiene el layout de la ventana principal
            CardLayout cardLayout1= (CardLayout) Cartas.getLayout();

            //Se genera los distintos layouts y paneles que se agregarana cada tarjeta de preguntas
            BorderLayout border = new BorderLayout();
            GridLayout options = new GridLayout(2,3);
            GridLayout panel = new GridLayout(1,2);
            JPanel ContP2 = new JPanel(panel);
            JPanel Contenedor = new JPanel(border);
            GridLayout gridbag = new GridLayout(3,1);
            JPanel ContP = new JPanel(gridbag);
            JPanel Textos= new JPanel(gridbag);

            //Se agrega el tipo de pregunta y la pregunta en si a la ventana
            Textos.add(new JLabel("Tipo de Pregunta: "+a.getQuestionType()));
            Textos.add(new JLabel(a.getQuestion()));
            ContP.add(Textos);

            //Se agregan los botones dependiendo de cuantas respuestas posibles haya
            JPanel Opciones = new JPanel(options);
            for (int j = 0; j<a.getChoices().length; j++) {
                JRadioButton button = new JRadioButton(a.getChoices()[j]);
                int finalJ1 = j+1;
                button.addActionListener(e -> a.setAnswer(finalJ1));
                Opciones.add(button);
            }
            ContP.add(Opciones);

            //Dependiendo del lugar de la tarjeta se agregan los botones de siguiente, anterior y finalizar
            if (i==1){
                Contenedor.add(Siguiente, BorderLayout.SOUTH);

            }
            else if (i!=1 && i!=backend.CantPreguntas()){
                JButton Next = new JButton("Siguiente");
                JButton last = new JButton("Anterior");
                Next.addActionListener(e -> cardLayout1.next(Cartas));
                last.addActionListener(e -> cardLayout1.previous(Cartas));
                ContP2.add(last);
                ContP2.add(Next);
                Contenedor.add(ContP2, BorderLayout.SOUTH);

            }
            else if(i==backend.CantPreguntas() ){
                ContP2.add(Anterior);
                ContP2.add(Finalizar);
                Contenedor.add(ContP2, BorderLayout.SOUTH);
            }
            Contenedor.add(ContP);

            // Agregar la tarjeta
            Cartas.add("Pregunta "+ i,Contenedor);
            i=i+1;
        }
    }

    public void CrearResumen() {
        BorderLayout border = new BorderLayout();
        GridLayout Porcentajes = new GridLayout(4,1);
        GridLayout resumen = new GridLayout(2,1);
        GridLayout Taxonomia = new GridLayout(4,2);
        JPanel Tax = new JPanel(Taxonomia);
        JPanel Texto  = new JPanel(resumen);
        JPanel ContRes = new JPanel(Porcentajes);
        JPanel Contenedor = new JPanel(border);

        Contenedor.add(new JLabel("Resumen"), BorderLayout.NORTH);

        ContRes.add(new JLabel("Porcentaje por tipo de Pregunta:"));
        ContRes.add(new JLabel("Porcentaje respuestas correctas: "+ backend.preguntaCorrecta()+"%"));
        ContRes.add(new JLabel("Seleccion Multiple: " + backend.typePercentageMC()+"%"));
        ContRes.add(new JLabel("Verdadero y falso: " + backend.typePercentageTF()+"%"));
        Texto.add(ContRes);

        // ** Porcentaje de respuestas correctas desglosadas según tipo de ítem
        Tax.add(new JLabel("Porcentaje por tipo de Taxonomia:"));
        Tax.add(new JLabel(""));
        Tax.add(new JLabel("Recordar: "+ backend.recordarPercentage()+"%"));
        Tax.add(new JLabel("Entender: "+ backend.entenderPercentage()+"%"));
        Tax.add(new JLabel("Aplicar: "+ backend.aplicarPercentage()+"%"));
        Tax.add(new JLabel("Analizar: "+ backend.analizarPercentage()+"%"));
        Tax.add(new JLabel("Evaluar: "+ backend.evaluarPercentage()+"%"));
        Tax.add(new JLabel("Crear: "+ backend.crearPercentage()+"%"));
        Texto.add(Tax);

        Contenedor.add(Texto, BorderLayout.CENTER);

        CardLayout cardLayout2 = (CardLayout) Cartas.getLayout();
        JButton Review = new JButton("Revisar Preguntas");
        Review.addActionListener(e -> cardLayout2.next(Cartas));

        Contenedor.add(Review, BorderLayout.SOUTH);
        Cartas.add("Resumen",Contenedor);
    }


    public void revisionPreguntas() {
        int i = 1;
        for (Item a : this.backend.getItemes()) {
            //Se obtiene el layout de la ventana principal
            CardLayout cardLayout1= (CardLayout) Cartas.getLayout();

            //Se genera los distintos layouts y paneles que se agregarana cada tarjeta de preguntas
            BorderLayout border = new BorderLayout();
            GridLayout panel = new GridLayout(1,3);
            JPanel ContP2 = new JPanel(panel);
            JPanel Contenedor = new JPanel(border);
            GridLayout gridbag = new GridLayout(6,1);
            GridLayout insert = new GridLayout(2,1);
            JPanel Texto= new JPanel(insert);
            JPanel ContP = new JPanel(gridbag);

            //Se agrega el tipo de pregunta y la pregunta en si a la ventana
            ContP.add(new JLabel("Tipo de Pregunta: "+a.getQuestionType()));
            ContP.add(new JLabel("Nivel Taxonomico: "+a.getTaxLevel()));
            ContP.add(new JLabel(a.getQuestion()));
            if(a.getAnswer()==-1){
                ContP.add(new JLabel("Sin contestar"));
            }
            else {
                ContP.add(new JLabel("Su respuesta: " + a.getChoices()[a.getAnswer() - 1]));
            }
            if(a.getCorrectChoice()==a.getAnswer()){
                ContP.add(new JLabel("Su respuesta es Correcta "));
            }
            else{
                ContP.add(new JLabel("Su respuesta es Incorrecta "));
                ContP.add(new JLabel("La respuesta correcta es: "+a.getChoices()[a.getCorrectChoice()-1]));
            }
            Texto.add(ContP);
            Contenedor.add(Texto, BorderLayout.CENTER);

            if (i==1){
                ContP2.add(Resumen);
                ContP2.add(Siguiente);
                Contenedor.add(ContP2, BorderLayout.SOUTH);

            }
            else if (i!=1 && i!=backend.CantPreguntas()){
                JButton Next = new JButton("Siguiente");
                JButton last = new JButton("Anterior");
                JButton volverResumen = new JButton("Volver Resumen");
                Next.addActionListener(e -> cardLayout1.next(Cartas));
                last.addActionListener(e -> cardLayout1.previous(Cartas));
                volverResumen.addActionListener(e -> cardLayout1.show(Cartas, "Resumen"));
                ContP2.add(last);
                ContP2.add(volverResumen);
                ContP2.add(Next);
                Contenedor.add(ContP2, BorderLayout.SOUTH);

            }
            else if(i==backend.CantPreguntas() ){
                ContP2.add(Anterior);
                JButton volverResumen = new JButton("Volver Resumen");
                volverResumen.addActionListener(e -> cardLayout1.show(Cartas, "Resumen"));
                ContP2.add(volverResumen);
                Contenedor.add(ContP2, BorderLayout.SOUTH);
            }

            // Agregar la tarjeta
            Cartas.add("Review "+ i,Contenedor);
            i=i+1;
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        int i = 1;
        for (Item a : this.backend.getItemes()) {
            i++;
        }
    }
}
