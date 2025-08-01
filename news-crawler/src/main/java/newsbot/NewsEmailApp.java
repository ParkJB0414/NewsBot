package newsbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewsEmailApp {

    public static void main(String[] args) {
    	  try {
              List<NewsItem> newsList = NewsCrawler.crawlNews();

              if (newsList.isEmpty()) {
                  System.out.println("크롤링 결과 없음. 메일 발송 생략.");
                  return;
              }

              // 오늘 날짜 자동 삽입
              LocalDate today = LocalDate.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
              String formattedDate = today.format(formatter);

              String subject = "[뉴스봇] " + formattedDate + " IT/과학 주요 뉴스";
              StringBuilder content = new StringBuilder();
              content.append("오늘의 주요 기사입니다.\n\n");

              int index = 1;
              for (NewsItem item : newsList) {
                  content.append(index++)
                         .append(". ")
                         .append(item.getTitle())
                         .append("\n   ")
                         .append(item.getLink())
                         .append("\n\n");
              }

              EmailSender.sendEmail(subject, content.toString());

          } catch (Exception e) {
              System.out.println("뉴스 크롤링 또는 이메일 발송 중 오류");
              e.printStackTrace();
          }
    }
    
}
