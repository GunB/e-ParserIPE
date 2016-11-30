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
import static com.caguaicorp.e.parser.utiility.XMLUtility.AddNodeList2Node;
import static com.caguaicorp.e.parser.utiility.XMLUtility.ChangeNode;
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
                {"title", "Título"},
                {"description", "Descripción"},
                {"keyword", "Palabras Claves"},
                {"learningGoal", "Objetivo de Aprendizaje\n" + "\n (Learning Goal)"},
                {"triggerQuestion", "Pregunta Detonante\n" + "\n(Trigger Question)"},
                {"pedagogicalAspect", "Aspectos Pedagógicos \n" + "\n(Pedagogical Aspects)"},
                {"recommendedUse", "Sugerencia de Uso\n" + "\n(Recommended Use)"},};

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
        Node staff = docu.getElementsByTagName("lom").item(0);
        NodeList list = staff.getChildNodes();

        HashMap<String, String> objObjeto = arrObjects.get(scoSco.getStrID());
        HashMap<String, String> objAttributes = new HashMap<>();
        
        objAttributes.put("lang", scoSco.getStrID().substring(2));
        
        ArrayList arrResp;

        for (String[] arrName : arrNames) {
            if (objObjeto.containsKey(arrName[1])) {
                switch (arrName[0]) {
                    case "title":
                        arrResp = new ArrayList<>(Arrays.asList("general", "title"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
                        scoSco.setStrNombre(objObjeto.get(arrName[1]));
                        break;
                    case "description":
                        arrResp = new ArrayList<>(Arrays.asList("general", "description"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
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
                        list = AddNodeList2Node(list, docu, arrResp, arrKeyWords, new XMLTag("li"));
                        list = ChangeNode(list, arrResp, null, objAttributes);
                        break;
                    case "learningGoal":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "learningGoal"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
                        break;
                    case "triggerQuestion":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "triggerQuestion"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
                        break;
                    case "pedagogicalAspect":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "pedagogicalAspect"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
                        break;
                    case "recommendedUse":
                        arrResp = new ArrayList<>(Arrays.asList("educational", "description", "recommendedUse"));
                        list = ChangeNode(list, arrResp, objObjeto.get(arrName[1]), objAttributes);
                        break;
                }
            }
        }

        scoSco.setDocXML(docu);
        return docu;
    }
}
