import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class PageCrawl {
  private static final String USER_AGENT = "Mozilla/5.0 (Macintosh;" +
   " Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) " +
    "Chrome/70.0.3538.102 Safari/537.36";
  private List<String> links = new LinkedList<>();

  public boolean crawl(String next){
    try {

      Connection connection = Jsoup.connect(next).userAgent(USER_AGENT);
      Document htmlDoc = connection.get();

      if(connection.response().statusCode() == 200) {
        System.out.print(".");
      }

      if(!connection.response().contentType().contains("text/html")) {
        return false;
      }

      Elements pageLinks = htmlDoc.select("a[href]");

      for (Element link : pageLinks) {
        this.links.add(link.absUrl("href"));
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<String> getLinks() {
    return this.links;
  }

}
