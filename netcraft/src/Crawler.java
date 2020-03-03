import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Crawler {
  private static final int MAX_PAGE_SEARCH_COUNT = 100;
  private Set<String> visitedPages = new HashSet<>();
  private Queue<String> nextPages = new PriorityQueue<>();

  private String nextUrlToVisit() {
    String next;

    do {
      next = this.nextPages.poll();
    } while (this.visitedPages.contains(next));

    this.visitedPages.add(next);

    return next;
  }

  public void search(String url) {
    while(this.visitedPages.size() < MAX_PAGE_SEARCH_COUNT) {
      String curUrl;
      PageCrawl pageCrawl = new PageCrawl();

      if (this.visitedPages.isEmpty()) {
        curUrl = url;
        this.visitedPages.add(url);
      } else {
        curUrl = this.nextUrlToVisit();
      }
      pageCrawl.crawl(curUrl);

      this.nextPages.addAll(pageCrawl.getLinks());
    }
  }

  public void printLinks() {
    System.out.println("\n\n" + MAX_PAGE_SEARCH_COUNT + " links found, Displaying Below:");
    for (String url : visitedPages) {
      System.out.println(url);
    }
  }
}

