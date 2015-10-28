/**
 * Created by kasun on 10/28/15.
 */


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XmlDomParser {


    String createIssueData,summary,description,type,priority;

    public void parseXmlDoc(String projectKey){
        try {
            File inputFile = new File("res/sample.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("alertitem");
            NodeList alerts = doc.getElementsByTagName("instance");


            for (int temp = 0; temp < nList.getLength(); temp++) { //loop through alerts
                Node nNode = nList.item(temp);
                Element alert=(Element) nNode;
                System.out.println("alert :" +alert.getElementsByTagName("alert").item(0).getTextContent());
                for (int i = 0; i < alerts.getLength(); i++) { //loop through instances
//                    System.out.println("\nCurrent Element :"
//                            + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        System.out.println("URL : "
                                + eElement.getElementsByTagName("uri").item(0).getTextContent());
//                    System.out.println("First Name : "
//                            + eElement
//                            .getElementsByTagName("firstname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Last Name : "
//                            + eElement
//                            .getElementsByTagName("lastname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Nick Name : "
//                            + eElement
//                            .getElementsByTagName("nickname")
//                            .item(0)
//                            .getTextContent());
//                    System.out.println("Marks : "
//                            + eElement
//                            .getElementsByTagName("marks")
//                            .item(0)
//                            .getTextContent());
                    }


                    createIssueData = "{\"fields\": {\"project\": {\"key\":\"" + projectKey + "\"}," +
                            "\"summary\":" + "\"" + summary + "\"" + ",\"description\":" + "\"" + description + "\"" + "," +
                            "\"issuetype\":{\"name\":\"" + type + "\"},\"priority\":{\"name\":\"" + priority + "\"}}}";
                }
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}