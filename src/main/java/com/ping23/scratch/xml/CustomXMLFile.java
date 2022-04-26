package com.ping23.scratch.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CustomXMLFile {

    private static final Logger LOG = LoggerFactory.getLogger(CustomXMLFile.class);

    public static void main(String[] args) {

        File file = new File("src/main/resources/xml/simple0.xml");

        LOG.info("file: " + file.toString());

        Document parsedDoc = parseFile(file);

        LOG.info("parsedDoc: " + parsedDoc.toString());

    }

    /**
     * Parses XML file and returns XML document.
     * 
     * @param file String representation of XML file to parse
     * @return XML document or <B>null</B> if error occured
     */
    private static Document parseFile(File file) {
        DocumentBuilder docBuilder;
        Document doc = null;

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactoryImpl.newInstance();
            docBuilderFactory.setIgnoringElementContentWhitespace(true);
            docBuilder = docBuilderFactory.newDocumentBuilder();

            doc = docBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            LOG.error("Error parsing template file, " + file.getName() + ".", e);
        } catch (SAXException e) {
            LOG.error("Error parsing template file, " + file.getName() + ".", e);
        } catch (IOException e) {
            LOG.error("Error parsing template file, " + file.getName() + ".", e);
        }

        return doc;
    }

    @Test
    public void whenGetElementByTag_thenSuccess() {
        File file = new File("src/main/resources/xml/test1.xml");

        LOG.info("file: " + file.toString());

        Document parsedDoc = parseFile(file);

        NodeList networkNodeList = parsedDoc.getElementsByTagName("network");
        Node firstNetwork = networkNodeList.item(0);

        assertEquals(1, networkNodeList.getLength());
        assertEquals(Node.ELEMENT_NODE, firstNetwork.getNodeType());
        assertEquals("network", firstNetwork.getNodeName());
        assertEquals("NBC", firstNetwork.getChildNodes().item(0).getNodeValue());
    }
}
