package com.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Created by jesus on 16/04/2017.
 */

public class XMLReader {

    public static Integer[] getScore() {

        SAXBuilder builder = new SAXBuilder();

        try {
            Document doc = builder.build(new FileInputStream("Configuracion/datos.xml"));
            Element raiz = doc.getRootElement();
            Element element = raiz.getChild("leaderboard");
            List<Element> scores = element.getChildren();
            Integer[] leaderboard = new Integer[scores.size()];
            int i = 0;
            for (Element score : scores) {
                leaderboard[i++] = Integer.parseInt(score.getText());
            }

            return leaderboard;

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Tuple<Integer, Integer, Boolean> getConfiguration() {

        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(new FileInputStream("Configuracion/datos.xml"));
            Element raiz = doc.getRootElement();
            Element settings = raiz.getChild("configuracion");
            String dificulty = settings.getChildText("dificultad");
            String music = settings.getChildText("music");
            String sound = settings.getChildText("sound");

            return new Tuple<Integer, Integer, Boolean>(Integer.parseInt(dificulty),
                    Integer.parseInt(music), Boolean.parseBoolean(sound));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setDificulty(int level) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(new FileInputStream("Configuracion/datos.xml"));
            Element raiz = doc.getRootElement();
            Element settings = raiz.getChild("configuracion");
            settings.getChild("dificultad").setText(level + "");

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("Configuracion\\datos.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public static void changeSound() {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(new FileInputStream("Configuracion/datos.xml"));
            Element raiz = doc.getRootElement();
            Element settings = raiz.getChild("configuracion");
            Element dificulty = settings.getChild("sound");
            dificulty.setText(Boolean.parseBoolean(dificulty.getText()) ? "false" : "true");
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("Configuracion\\datos.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public static void changeMusic() {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(new FileInputStream("Configuracion/datos.xml"));
            Element raiz = doc.getRootElement();
            Element settings = raiz.getChild("configuracion");
            Element dificulty = settings.getChild("music");
            dificulty.setText(Integer.toString(1 - Integer.parseInt(dificulty.getText())));
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("Configuracion\\datos.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public static void addScore(int points) {

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("Configuracion\\datos.xml");

            Document doc = builder.build(xmlFile);
            Element raiz = doc.getRootElement();
            Element leaderboard = raiz.getChild("leaderboard");
            Element score = new Element("score").setText(Integer.toString(points));
            leaderboard.addContent(score);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("Configuracion\\datos.xml"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public static void setScore(Integer[] scores) {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("Configuracion\\datos.xml");

            Document doc = builder.build(xmlFile);
            Element raiz = doc.getRootElement();
//            Element leaderboard = raiz.getChild("leaderboard");
            raiz.removeChild("leaderboard");
            Element leaderboard = new Element("leaderboard");
            for (int score : scores) {
                Element scoreChild = new Element("score").setText(Integer.toString(score));
                leaderboard.addContent(scoreChild);
            }
            raiz.addContent(leaderboard);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("Configuracion\\datos.xml"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}
