package com.caguaicorp.e.parser.utiility;

import com.caguaicorp.e.parser.bin.MetadataParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FilesUtility {

    public static String strRoot = System.getProperty("user.home");
    private static final long unixTime = System.currentTimeMillis() / 1000L;
    private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime());
    public static final String actualDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    public static String strFile2Change = "metadata.xml";

    public static Document XmlFormatBase() throws ParserConfigurationException, SAXException, IOException {
        String text = ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
                + "<cem:cem xmlns:cem=\"http://ltsc.ieee.org/xsd/CEM\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://ltsc.ieee.org/xsd/LOM lomCustom.xsd\" xsi:type=\"cem:cem\">\n"
                + "     <general>\n"
                + "		<identifier>\n"
                + "			<catalog catName=\"edistribution\" catSource=\"http://www.edistribution.co/\"/>\n"
                + "		</identifier>\n"
                + "		<title lang=\"\" subtitle=\"\"></title>\n"
                + "		<description lang=\"\"/>\n"
                + "		<keyword lang=\"\"/>\n"
                + "		<structure schema=\"CEM\"/>\n"
                + "		<aggregationLevel schema=\"CEM\"/>\n"
                + "	</general>\n"
                + "	<lifeCycle>\n"
                + "		<version date=\"" + actualDate + "\">1.0</version>\n"
                + "		<status>Publicado</status>\n"
                //<editor-fold defaultstate="collapsed" desc="TODO:Contribuyentes que luego deberán ser leídos">

                + "             <contribute>\n"
                + "			<role schema=\"CEM\">Implementador técnico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1020748674\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Ospina Cabrera, Mariana</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Implementador técnico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80871315\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Pikieris Caicedo, Andrei</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Implementador técnico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"41962305\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Urrea Macías, María Paula</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Implementador técnico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1010207397\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Valenzuela López, Daniel Felipe</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Implementador técnico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1015430677\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Escucha Hilarion, Anlly Vanesa</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Productor ejecutivo</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79786415\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Díaz Ochoa, Gustavo Andrés</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1018460318\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Meneses Alvarez, Brenda Stefania</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1030565346\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Rincón Carreño, Diana Carolina</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80833469\" institution=\"eDistribution SAS\" sr=\"contacto@edistribution.co\" type=\"Persona\">Nova Beltrán, Jorge Alberto</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1049627441\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Avella Herrera, Cristian Fernando</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1010172291\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Cortés Salazar, Jose Fernando</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1013620707\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Pereira Duran, Andrés Guillermo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\" 1012380085\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gonzalez Garcia, Andres Felipe</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Desarrollador web</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1018441645\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Muñoz Londoño, Julián Felipe</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Productor ejecutivo</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1016013975\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Paez Rodriguez, Cristian David</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Productor</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80218248\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Villafradez Peña, Jorge Enrique</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1020796302\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Sombrerero Barrantes, Angie Milena</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1023878922\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Cervantes Bermudez, Marlon Andres</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1140422168\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Torres Escobar, Jesus Eduardo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1070018736\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Castro, Camilo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1016049594\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">García Sánchez, Elkin Jair</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1074188519\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Bermúdez Díaz, Yuly Fernanda</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"75076254\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Rodriguez, Carlos</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"13279916\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Salamanca, Harold</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"574148\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Flores Barrios, Lorena</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1033805912\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Florez Correa, Xiomara Alejandra</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1192791831\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Nascimiento Florez, Gabriel Jose</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1033772095\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Hernandez Ortiz, Maria Fernanda</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1115856287\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Ascanio Sierra, Polina</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1032451547\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Lozano Moreno, Fabio</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79720952\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Toapanta Quiranza, Alexis Joffre</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Diseñador audiovisual</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1015467045\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gómez Castellanos, Geovany Hernando</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Experto pedagógico</role>\n"
                + "			<entity country=\"CO\" entityForm=\"51867177\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Rodriguez Pulido, Adriana</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1019080399\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Castro Castillo, Ana Milena</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80895737\" institution=\"eDistribution SAS\" sr=\"contacto@edistribution.co\" type=\"Persona\">Ramirez Santana, Andres Camilo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1015426962\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Moreno González, Aura Margarita</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80111422\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Zamudio Lozano, Andres Alfonso</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1019050269\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gutiérrez Ramírez, Arnol Favian</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1018455111\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Forero Galvis, Carlos Mauricio</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80232761\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Troncoso Salgar, Daniel</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1031122333\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Lozano Zabala, David Alejandro</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1019043199\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Mejia Morales, David Ricardo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"53167487\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gomez Arias, Diana Teresa</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1020713308\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Moya Morales, Diego Armando</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"53103631\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Meneses Góngora, Eliana Patricia</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79882453\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Ceballos Ramirez, Felix Andres</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79761196\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Hernandez Sanchez, Francisco Javier</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute><contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1019076441\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Reyes Gonzalez, Gabriela</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80882899\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Jaramillo Sánchez, Ivan Dario</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80112176\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Baez Ortiz, Jean Jairo</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1030624499\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Benavides Bonilla, Johanna Paola</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute><contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79756440\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Murcia Martínez, John Freddy</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1023925501\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Rojas Betancourt, Jorge Daniel</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1020786322\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gonzalez Vargas, Juan David</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1022389729\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Gil Sepulveda, Juan David</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1014193016\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Alfonso Parra, July Stephanie</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1032449411\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Ospina Godoy, Laura Camila</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"79967340\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Escobar Diaz, Leonardo Alberto</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1069098612\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Suárez Gómez, Mayra Alejandra</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1070923117\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Muñoz Sanchez, Sara Estefania</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1014263325\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Ballen Vargas, Stephania</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1012834832\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Sierra Angulo, Stefania</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1032450851\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Mesa, Victoria Andrea</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1019025999\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Guerrero Torres, Viviana Andrea</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"1010176860\" institution=\"eDistribution SAS\" src=\"contacto@edistribution.co\" type=\"Persona\">Zarate Carreño, Yessica Paola</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                + "		<contribute>\n"
                + "			<role schema=\"CEM\">Proveedor de contenidos</role>\n"
                + "			<entity country=\"CO\" entityForm=\"80888256\" institution=\"eDistribution SAS\" sr=\"contacto@edistribution.co\" type=\"Persona\">Blanco, Cesar Augusto</entity>\n"
                + "			<date>" + actualDate + "</date>\n"
                + "		</contribute>\n"
                //*/
                //</editor-fold>
                + "	</lifeCycle>\n"
                + "	<metaMetadata/>\n"
                + "	<technical/>\n"
                + "	<educational>\n"
                + "		<description>\n"
                + "			<recommendedUse lang=\"\"/>\n"
                + "			<triggerQuestion lang=\"\"/>\n"
                + "			<pedagogicalAspect lang=\"\"/>\n"
                + "			<learningGoal lang=\"\"/>\n"
                + "		</description>\n"
                + "	</educational>\n"
                + "	<rights/>\n"
                + "	<classification>\n"
                + "	</classification>\n"
                + "</cem:cem>";
        Document ndXml = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));

        return ndXml;
    }

    public static void copyFolder(File src, File dest)
            throws IOException {
        if (src.isDirectory()) {

            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from " + src + "  to " + dest);
            }

            String[] files = src.list();

            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyFolder(srcFile, destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            Object out = new FileOutputStream(dest);

            byte[] buffer = new byte['Ѐ'];

            int length;

            while ((length = in.read(buffer)) > 0) {
                ((OutputStream) out).write(buffer, 0, length);
            }

            in.close();
            ((OutputStream) out).close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }

    public static FileStore getPathFilesystem(String path) throws URISyntaxException, IOException {
        URI rootURI = new URI("file:///");
        Path rootPath = Paths.get(rootURI);
        Path dirPath = rootPath.resolve(path);
        FileStore dirFileStore = Files.getFileStore(dirPath);
        return dirFileStore;
    }

    public static void DrivesnDesc() {
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] paths = File.listRoots();

        for (File path : paths) {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
        }
    }

    public static String PathRootDesc(String strFilePath) {
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File newFile = new File(strFilePath);
        newFile = GetFullParent(newFile);

        return fsv.getSystemTypeDescription(newFile);
    }

    public static File GetFullParent(File file) {
        try {
            file = GetFullParent(new File(file.getParent()));
        } catch (NullPointerException ex) {
            return file;
        }

        return file;
    }

    public static File CopyFolder(String strPathFolder) {

        File baseFileDirectory = new File(strPathFolder);

        File newFileDirectory
                = new File(baseFileDirectory.getParent() + File.separator
                        + "eFIXED_" + FilesUtility.unixTime + " " + FilesUtility.timeStamp);

        newFileDirectory.mkdirs();
        try {
            FilesUtility.copyFolder(baseFileDirectory, newFileDirectory);
        } catch (IOException ex) {
            Logger.getLogger(MetadataParser.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            System.exit(9);
        }

        return newFileDirectory;
    }

    public static void printFileStore(FileStore filestore, String path) throws IOException {
        System.out.println("Name: " + filestore.name());
        System.out.println("\tPath: " + path);
        System.out.println("\tSize: " + filestore.getTotalSpace());
        System.out.println("\tUnallocated: " + filestore.getUnallocatedSpace());
        System.out.println("\tUsable: " + filestore.getUsableSpace());
        System.out.println("\tType: " + filestore.type());
    }
}
