# Practica_Final_LDH
Práctica final de Laboratorio de Herramientas: Simulador de seguimiento de objetos en el hogar. Realizado por Jesús Navarro Hernández y José Antonio García Fuentes



# **Links de interés**

**[Link de SonarCloud](https://sonarcloud.io/dashboard?id=PruebasUnitarias)**

**[Link de la Documentación](https://susolopolis.github.io/Practica_Final_LDH/)**

# **Configuración de la aplicación**
## **Doxygen**
Para proceder a la documentación del proyecto se ha escogido la herramienta **[Doxygen](https://www.doxygen.nl/manual/starting.html)**, cuya principal forma de operar es usando una sintaxis similar al conocido **JavaDoc**, comentando cada uno de los atributos, métodos, parámetros y clases que conformarán el proyecto. Un ejemplo directo de su uso:

```java
/**
* Método para mostrar por consola una cadena de texto.
*
* En este método se muestra a través de la consola una cadena de texto de prueba. En este caso
* la cadena String a mostrar se trata de "Hola, Mundo", y solo será mostrada en caso de que el 
* parámetro booleano que se indica al método sea "true". Además, se retornará la cadena correspondiente.
*
* @param mostrar Un booleano. El estado que permitirá mostrar la cadena por consola sea true o false.
* @return La cadena vacía o "Hola, Mundo" en función de si se desea mostrar o no.
*/
public String mostrarHolaMundo(boolean mostrar){
  String mostrar = "";

  if(mostrar) {
    mostrar = "Hola, Mundo";
    System.out.println("Hola, Mundo");
  }
  return mostrar;
}

```

Una vez realizada la documentación, ésta se generará utilizando la configuración del archivo **Doxyfile** que se puede encontrar en el repositorio. 

No obstante, dada la infraestructura del proyecto, se desea generar la documentación en el último elemento de la cadena, que será con la herramienta de integración continua Travis. 

## **JUnit 5**
La sección de pruebas unitarias de la aplicación se implementó por medio de **JUnit** en su versión 5, cuya documentación puede encontrarse **[aquí](https://junit.org/junit5/docs/current/user-guide/)**. Principalmente para su uso se emplearán los métodos de aserción además de las anotaciones pertenecientes a la librería.

Si se desea testar el ejemplo anterior:

```java
@Test
public void testMostrarHolaMundo(){
  ClaseEjemplo cE = new ClaseEjemplo();
  assertEquals("", cE.mostrarHolaMundo(false));
  assertEquals("Hola, Mundo", cE.mostrarHolaMundo(true));
}
```

Nuevamente, se pretende comprobar la correcta ejecución de todos los test por medio de Maven una vez se haga uso del sistema de integración continua.

### Nota importante
Cabe destacar que en este proyecto en particular, al tratar de someter a casos de prueba todo el código posible, al disponer esta aplicación de una interfaz gráfica de usuario también se probaron los elementos de ésta. Los test corren de manera satisfactoria de forma local en una máquina estándar con un sistema operativo y software estándar (en este caso se desarrolló empleando el IDE de JetBrains, IntelliJ IDEA bajo Windows 10). 

Surgirán problemas no obstante en el momento de correr esta sección de test de la IGU a la hora de correrlos bajo Travis CI, por lo que es importante tener en cuenta el sistema operativo bajo el que correrá la máquina remota y tener en cuenta aspectos como la propiedad _headless_ de Java a la hora de emplear este tipo de pruebas unitarias.

Más información en: **[Using xvfb to Run Tests That Require a GUI](https://docs.travis-ci.com/user/gui-and-headless-browsers/#using-xvfb-to-run-tests-that-require-a-gui)** así como **[Using Headless Mode in the Java SE Platform](https://www.oracle.com/technical-resources/articles/javase/headless.html)**

## **Maven**
Para una automatización del proceso de construcción del proyecto se optó por la tecnología de **[Maven](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)**, que permitirá una alta configuración por medio de su fichero de configuración **"pom.xml"**. En este caso la configuración se ha basado en la vinculación del proyecto con SonarCloud, de tal forma que se ha de hacer uso de los nombres de la organización en el sitio web; la clave del proyecto; la URL; así como rutas donde se generarán los informes.

Además, se indicarán los plugins necesarios para la ejecución de los test así como las dependencias correspondientes: **maven-surefire-plugin**, **jacoco-maven-plugin**; **junit-jupiter-api**, **junit-jupiter-engine**.

## SonarCloud
Como análisis de código en la nube se ha empleado **[SonarCloud](https://sonarcloud.io/documentation)**, de tal forma que además de la automatización empleada por medio de Maven, también se ha de crear y adaptar el fichero de configuración **"sonar-project.properties"** de tal forma que se indican los parámetros básicos del nombre de la organización; la clave; y la URL. 

También se ha de indicar la ruta relativa donde se encuentra el código fuente _(en este caso "./src/main")_ así como la ruta relativa al fichero sonar-project.properties para los binarios del proyecto _("./target/classes")_.

Finalmente, siempre que se desee que SonarCloud actúe y escanee el proyecto, bastará con ejecutar la orden correspondiente _sonar-scanner_ sobre el propio directorio donde se encuentra el fichero de configuración, o bien realizarlo también a través de nuestra herramienta de integración continua seleccionada como es Travis CI.

## git y GitHub
Para el sistema de control de versiones se optó por las dos opciones más estándar como son **[git](https://git-scm.com/doc)** para el control de versiones, y **[GitHub](https://docs.github.com/en/free-pro-team@latest/github/getting-started-with-github)** para su uso en remoto, de tal forma que ambos miembros del equipo pudieran colaborar y actualizar de manera más eficiente los cambios necesarios para el proyecto, además de hacer uso de las herramientas que se integran automáticamente con la cuenta de GitHub como son **SonarCloud** y **TravisCI**, ya que ambas permiten acceder a su página vinculando una cuenta de GitHub para posteriormente poder incorporar los repositorios que se deseen ser analizados.

## Travis CI
Como fase final de toda la cadena de herramientas empleada, se recurre al sistema de integración continua **Travis CI** (**[documentación](https://docs.travis-ci.com/user/languages/java/)**). Para que Travis pueda detectar las operaciones que se desean generar de forma automática cada vez que existan cambios en el repositorio deseado, se usa el fichero de configuración **".travis.yml"**, y en este caso, los elementos que lo conforman son:

```
os: linux
dist: xenial
services:
  - xvfb

# Project JDK
language: java
jdk:
  - openjdk11
 
  
addons:
  sonarcloud:
    organization: "susolopolis" # the key of the org you chose at step #3
    token:
      secure: $SONAR_TOKEN # encrypted value of your token
      
  apt:
    packages:
      - doxygen
      
script:
  # other script steps might be done before running the actual analysis
 - mvn clean verify sonar:sonar -Pcoverage -Dsonar.login=84350fe6a3e3b577c66d5e3aefe27e64e529baf6
 - doxygen Doxyfile

deploy:
  provider: pages
  skip_cleanup: true
  local_dir: docs/html
  github_token: $GH_REPO_TOKEN
  on:
    branch: main
```

Básicamente se puede comprobar que se establecen los parámetros para los cuales se configurará la máquina remota sobre la que se ejecutará todo lo deseado. Primeramente se establece que el sistema operativo a utilizar será Linux, y la distribución empleada Xenial (se trata de la versión 16.0.4 LTS de Ubuntu). 

Además, se indica uno de los servicios a utilizar: **xvfb**, el cual será necesario para poder **ejecutar pruebas [sobre la IGU](https://docs.travis-ci.com/user/gui-and-headless-browsers/#using-xvfb-to-run-tests-that-require-a-gui) de la aplicación** y un kit de desarrollo para Java como puede ser openjdk11.

Como añadidos, se indica la instalación de las operaciones para *sonarcloud* con la clave de la organización así como el token para el proyecto. Además, en apt también se incluirá la orden para generar toda la documentación asociada al proyecto con **doxygen**.

Posteriormente, se establecen las acciones que la máquina ejecutará, por medio del _script_: **Maven** con la orden _sonar_ ; y generación de la documentación **doxygen** usando el fichero _Doxyfile_.

Como última parte del fichero se indica dónde se indexará toda la documentación generada. En este caso, se alojará en una subrama creada (llamada _gh-pages_) en este mismo repositorio de GitHub. Estas indicaciones están basadas en la información indicada en **[Doxygen with Travis CI to gh-pages](https://gist.github.com/bmegli/e22ba525dda0b197b2d4440d08f43ae2)**.

Con todo lo explicado en este informe, la aplicación se podrá ejecutar y analizar correctamente de una manera muy práctica, eficiente, además de muy completa.
