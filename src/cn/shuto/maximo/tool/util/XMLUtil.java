package cn.shuto.maximo.tool.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtil {
	/**
	 * 将字符串转换为XML Document
	 * 
	 * @param xmlStr
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document String2XML(String xmlStr) {
		try {
			StringReader sr = new StringReader(xmlStr);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Node selectSingleNode(String express, Object source) {// 查找节点，并返回第一个符合条件节点
		Node result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (Node) xpath.evaluate(express, source, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static NodeList selectNodes(String express, Object source) {// 查找节点，返回符合条件的节点集。
		NodeList result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String XML2String(Node node) {
		try {
			StringWriter writer = new StringWriter();
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			Source source = new DOMSource(node);
			Result result = new StreamResult(writer);
			trans.transform(source, result);
			return writer.toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void replaceNode(Node oldNode, Node newNode, Node source) {
		source.replaceChild(newNode, oldNode);
	}

}
