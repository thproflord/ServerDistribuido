package com.company.Servidor;

import com.company.Servidor.Libro.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lector {
    private String path;
    private Document doc;
    private List<Libro> biblioteca;
    public Lector(String path){
        this.path = path;
    }
    public void open() {
        try {
            File file = new File(path);
            //an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("book");
            Node node;
            biblioteca = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    biblioteca.add(new Libro(eElement.getElementsByTagName("author").item(0).getTextContent(),eElement.getElementsByTagName("title").item(0).getTextContent()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBooksbyAuthor(String author){
        String response = "";
        for(Libro libro : biblioteca){
            if(libro.getAutor().equalsIgnoreCase(author)){
               response = response + libro.getTitulo() + "; ";
            }
        }
        if(response != ""){
            response = author + ": " + response;
        }else{
            response = "404";
        }
        return response;

    }
    public String getBookbyTitle(String title){
        String response = "";
        for(Libro libro : biblioteca){
            if(libro.getTitulo().equalsIgnoreCase(title)){
                response = response + libro.getAutor() + "; ";
            }
        }
        if(response != ""){
            response = title + ": " + response;
        }else{
            response = "404";
        }
        return response;

    }



}
