/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caguaicorp.e.parser.bin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import com.caguaicorp.e.parser.model.SharableContentObject;
import com.caguaicorp.e.parser.model.XMLTag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.caguaicorp.e.parser.utiility.ExcelReader;
import com.caguaicorp.e.parser.utiility.FilesUtility;
import com.caguaicorp.e.parser.utiility.XMLUtility;
import static com.caguaicorp.e.parser.utiility.XMLUtility.AddNodeList2Node;
import static com.caguaicorp.e.parser.utiility.XMLUtility.ChangeNode;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author hangarita
 */
public class ExcelFormat {

    private final ExcelReader filExcel;
    private HashMap<String, HashMap<String, String>> arrObjects = new HashMap<>();
    Document doc = null;

    String[][] arrNames
            = {
                {"identifier", "Nomenclatura"},
                {"date", "Fecha"},
                {"title", "Título"},
                {"description", "Descripción"},
                {"keyword", "Palabras Claves"},
                {"status", "Estatus"},
                {"version", "Ciclo de Vida"},
                {"learningGoal", "Objetivo de Aprendizaje\n" + " (Learning Goal)"},
                {"triggerQuestion", "Pregunta Detonante\n" + "(Trigger Question)"},
                {"pedagogicalAspect", "Aspectos Pedagógicos \n" + "(Pedagogical Aspects)"},
                {"recommendedUse", "Sugerencia de Uso\n" + "(Recommended Use)"},
                {"contribute", "Contribuyentes"}
            };

    private Node getNodeContribute() throws ParserConfigurationException, SAXException, IOException {

        String strLyfeCicle = ""
                + "<contribute>\n"
                + "<role schema=\"CEM\"></role>\n"
                + "<entity entityForm=\"\" type=\"\" src=\"\" institution=\"\" country=\"\">"
                + "DATA"
                + "</entity>\n"
                + "<date></date>\n"
                + "</contribute>\n";

        Node ndContribute = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new ByteArrayInputStream(strLyfeCicle.getBytes(StandardCharsets.UTF_8))).getDocumentElement();

        ndContribute.normalize();

        ndContribute.cloneNode(true);
        return ndContribute;
    }

    public String getName() {
        return filExcel.getStrName();
    }

    public ExcelFormat(ExcelReader filExcel) throws ParserConfigurationException, SAXException, IOException {
        this.filExcel = filExcel;
        doc = FilesUtility.XmlFormatBase();
        ExcelParser();
    }

    private void ExcelParser() {
        for (String strNameSheet : filExcel.getArrSheetNames()) {
            Sheet readedSheet = filExcel.ReadSheetbyName(strNameSheet);
            //filExcel.arrSheetNames.remove(0);
            HashMap<String, String> objSheet = ExcelReader.turnSheetToObject(readedSheet);
            arrObjects.put(objSheet.get(arrNames[0][1]), objSheet);
        }
    }

    public HashMap<String, HashMap<String, String>> getArrObjects() {
        return arrObjects;
    }

    public Document SharableContentObjectCompleter(SharableContentObject scoSco) throws ParserConfigurationException, SAXException, IOException {
        Document docu = (Document) this.doc.cloneNode(true);
        Node staff = docu.getElementsByTagName(docu.getDocumentElement().getNodeName()).item(0);
        NodeList list = staff.getChildNodes();

        HashMap<String, String> objObjeto = arrObjects.get(scoSco.getStrID());
        HashMap<String, String> objAttributes = new HashMap<>();
        HashMap<String, String> objDate = new HashMap<>();

        objAttributes.put("lang", scoSco.getStrID().substring(0, 2));
        
        objDate.put("date", objObjeto.get(arrNames[1][1]));

        ArrayList arrResp;

        for (String[] arrName : arrNames) {
            if (objObjeto.containsKey(arrName[1])) {
                switch (arrName[0]) {
                    //<editor-fold defaultstate="collapsed" desc="comment">
                    case "title":
                        arrResp = new ArrayList<>(Arrays.asList("general", "title"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        scoSco.setStrNombre(objObjeto.get(arrName[1]));
                        break;
                    case "description":
                        arrResp = new ArrayList<>(Arrays.asList("general", "description"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        scoSco.setStrDesc(objObjeto.get(arrName[1]));
                        break;
                    case "identifier":
                        arrResp = new ArrayList<>(Arrays.asList("general", "identifier", "catalog"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), null);
                        scoSco.setStrID(objObjeto.get(arrName[1]));
                        break;
                    case "keyword":
                        String[] arrKeyWords = objObjeto.get(arrName[1]).split(",");
                        arrResp = new ArrayList<>(Arrays.asList("general", "keyword"));
                        list = ChangeNode(list, arrResp, null, (HashMap) objAttributes.clone());
                        list = AddNodeList2Node(list, docu, arrResp, arrKeyWords, new XMLTag("li"));
                        break;
                    case "learningGoal":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "learningGoal"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        break;
                    case "triggerQuestion":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "triggerQuestion"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        break;
                    case "pedagogicalAspect":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "pedagogicalAspect"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        break;
                    case "recommendedUse":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "recommendedUse"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        break;
                    //</editor-fold>
                    case "status":
                        arrResp = new ArrayList<>(Arrays.asList("lifeCycle", "status"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objAttributes.clone());
                        scoSco.setStrNombre(objObjeto.get(arrName[1]));
                        break;
                    case "version":
                        arrResp = new ArrayList<>(Arrays.asList("lifeCycle", "status"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), (HashMap) objDate.clone());
                        scoSco.setStrNombre(objObjeto.get(arrName[1]));
                        break;
                    case "contribute":
                        String[] arrContributes = objObjeto.get(arrName[1]).split(";");

                        for (String arrContribute : arrContributes) {
                            HashMap<String, String> mapContAttributes = new HashMap<>();
                            String[] arrData = arrContribute.split(",");
                            Node ndData = getNodeContribute();
                            NodeList ndListTemp = ndData.getChildNodes();
                            String contributeName = "";

                            //<editor-fold defaultstate="collapsed" desc="Read line contribute">
                            for (int i = 0; i < arrData.length; i++) {
                                String string = arrData[i].trim();

                                switch (i) {
                                    case 0:
                                        XMLUtility.ChangeNode(ndListTemp,
                                                new ArrayList(Arrays.asList(new String[]{"role"})),
                                                string,
                                                null);
                                        break;
                                    case 1:
                                        mapContAttributes.put("entityForm", string);
                                        mapContAttributes.put("institution", "eDistribution SAS");
                                        mapContAttributes.put("src", "contacto@edistribution.co");
                                        mapContAttributes.put("type", "Persona");
                                        mapContAttributes.put("country", "CO");
                                        /*XMLUtility.ChangeNode(ndListTemp,
                                        new ArrayList(Arrays.asList(new String[]{"role"})),
                                        string,
                                        null);*/

                                        break;
                                    case 2:
                                        contributeName += string;
                                        break;
                                    case 3:
                                        contributeName += " , " + string;
                                        XMLUtility.ChangeNode(ndListTemp,
                                                new ArrayList(Arrays.asList(new String[]{"entity"})),
                                                contributeName,
                                                mapContAttributes);

                                        XMLUtility.ChangeNode(ndListTemp,
                                                new ArrayList(Arrays.asList(new String[]{"date"})),
                                                objDate.get("date"),
                                                null);
                                        break;
                                }

                            }
                            //</editor-fold>

                            docu.adoptNode(ndData);
                            Node ndPrincipalNode = XMLUtility.ReadNode(list, new ArrayList(Arrays.asList(new String[]{"lifeCycle"})));
                            ndPrincipalNode.appendChild(ndData);
                        }
                        break;
                }
            }
        }

        scoSco.setDocXML(docu);
        return docu;
    }
}
