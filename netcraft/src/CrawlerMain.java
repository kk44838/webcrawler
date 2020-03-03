public class CrawlerMain {
  public static void main(String[] args) {
    System.out.println("Running Web Crawler...");
    Crawler crawler = new Crawler();
    crawler.search(args[0]);
    crawler.printLinks();
  }
}
