import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by kausn on 10/22/15.
 */
public class HtmlParser {

    public Document ReadHtmldoc(String filePath, String fileName) {

        File input = new File(filePath + "/" + fileName);
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return doc;

    }

    public String[] CreateIssueList(Document doc) {

        String[] isuueList = new String[1000];
        String summary = null;
        String description = null;
        String type = null;
        String priority = null;

        Elements tables = doc.select("table");

        for (int j = 1; j < tables.size(); j++) {

            Element table = doc.select("table").get(j); //select the first table (skipping the first table)
            Elements rows = table.select("tr"); //select all rows

            for (int i = 0; i < rows.size(); i++) {
                if (i == 0) { //track the alert level and summary from the first row
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String temp=cols.get(0).text();
                    priority=temp.substring(0,temp.indexOf(" "));
                    summary = cols.get(1).text();
//                    System.out.println(summary);

                } else if (i==1) { // get the description from the first row
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    description = cols.get(1).text();
                    //System.out.println(description);
                } else {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
//                    System.out.println(cols.text());
                }

            }
            String createIssueData = "{\"fields\": {\"project\": {\"key\":\"PROD\"}," +
                    "\"summary\":" + "\"" + summary + "\"" + ",\"description\":" + "\"" + description + "\"" + "," +
                    "\"issuetype\":{\"name\":\"" + type + "\"},\"priority\":{\"name\":\"" + priority + "\"}}}";
            //System.out.println(createIssueData);
            //create and add the issu to the array from here
            isuueList[j]=createIssueData;
        }

        for (int i=0; i<isuueList.length;i++){
            System.out.println(isuueList[i]);
        }


        return isuueList;
    }
}
