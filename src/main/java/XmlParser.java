/**
 * Created by kasun on 10/21/15.
 */

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.util.List;

public class XmlParser {
    public Document readFromXml(String filePath, String fileName) {

        Document document = null;
        try {
            SAXBuilder builder = new SAXBuilder();
            document = (Document) builder.build(filePath + "/" + fileName);

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    public String createIssueFormat(Document document) {
        String issue = null;
        String createIssueData = "{\"fields\": {\"project\": {\"key\":\"PROD\"},\"summary\":\"REST Test\"," +
                "\"description\": \"Creating of an issue using project keys and issue type names using the REST API\"," +
                "\"issuetype\":{\"name\":\"Bug\"}}}";

        try {


            //Element rootNode = document.getRootElement();
            Element rootNode=new Element("alerts");
            List list = rootNode.getChildren();
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                System.out.println(node.getName());
//                System.out.println("First Name : " + node.getChildText("firstname"));
//                System.out.println("Last Name : " + node.getChildText("lastname"));
//                System.out.println("Nick Name : " + node.getChildText("nickname"));
//                System.out.println("Salary : " + node.getChildText("salary"));

            }
        }catch (Exception e){

        }

        return issue;
    }
}
