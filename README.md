# Tarea2_ACV_BKO
Resolucion de la Tarea 2 por *Andrés Calderón Villarroel* y *Bastián Kramarenko Olave*

Se utilizó JDK 23. Y el entorno de desarrollo integrado utilizado fue IntelliJ IDEA Ultimate 2024.1.

**Indicaciones**
1. Para iniciar la evaluación, se debe presionar el botón 'Iniciar', donde este lo llevará a la primera pregunta.
2. Dentro de las preguntas podrá ir avanzando y retrocediendo entre ellas con los botones 'Siguiente' y 'Anterior' respectivamente.
3. Una vez llegado a la última pregunta y con sus respuestas ya listas, para finalizar la prueba, debe presionar el botón 'Finalizar', el cual le llevará a el resumen detallado de la prueba.
4. Junto al resumen existe un botón el cual permite ir a revisar cada pregunta por si misma y ver en cuales se respondieron bien y cuales mal.
___
**Limitaciones**
1. Sólo se puede cargar un archivo de tipo comma-separated values (csv) con el nombre 'evaluacion'. Es decir, sólo se podrán revisar archivos cuyo nombre sea 'evalucion.csv'.
2. El archivo se carga de forma automáticamente al inicializar el programa, para esto debe estar dentro de la carpeta principal del programa.
3. El contenido del 'evaluacion.csv' debe tener un formato para la lectura de datos específica, esta debe lucir así:
   
>  Tipo de Pregunta;Enunciado;Nivel de Taxonomía;Duración en Minutos;Respuesta Correcta;Respuestas

Donde cada elemento debe ser separado por ';', y cada respuesta agregada se debe separar con '::'.  Por ejemplo:

>  trueFalse;Son los zorros geniales?;analizar;3;2;Verdadero::Falso

En cuanto a las opciones para 'Tipo de Pregunta' y 'Nivel de Taxonomía', se deben escribir de forma específica sus valores. Estos son los valores para cada uno:

**Tipo de Pregunta**

>  trueFalse = Pregunta de verdadero y falso<br />
>  multChoice = Pregunta de opción múltiple

**Nivel de Taxonomía**
>  recordar = Nivel de taxonomía 'recordar'<br />
>  entender = Nivel de taxonomía 'entender'<br />
>  aplicar = Nivel de taxonomía 'aplicar'<br />
>  analizar = Nivel de taxonomía 'analizar'<br />
>  evaluar = Nivel de taxonomía 'evaluar'<br />
>  crear = Nivel de taxonomía 'crear'

4. Para responder una pregunta se debe marcar una opción, si quieres cambiar de opción, debes desmarcar y marcar otra opción. Si marca más de una opción al pasar de página, se guardará la última opción en haber sido marcada.
5. Para la cantidad de opciones hay un límite sugerido de seis opciones. Pero el programa es capaz de agregar una cantidad indefinida de respuestas para responder.
6. Las preguntas de verdadero y falso, a pesar de ser de dos opciones, son capaces de agregar más opciones de las que deberían ser.
