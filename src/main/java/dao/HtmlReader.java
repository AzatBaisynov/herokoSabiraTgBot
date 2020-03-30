package dao;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import repo.Article;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlReader {


    public static List<Article> getOfficialTable() {
        List<Article> articles = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.worldometers.info/coronavirus/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int country = 0;
        int totalCases = 1;
        int newCases = 2;
        int totalDeaths = 3;
        int newDeaths = 4;
        int totalRecovered = 5;
        int activeCases = 6;
        int firstCase = 10;

        while (0 < 1) {
            articles.add(new Article(
                                 doc.getElementsByTag("td").get(country).text(),
                    checkForNull(doc.getElementsByTag("td").get(totalCases).text()),
                    checkForNull(doc.getElementsByTag("td").get(newCases).text()),
                    checkForNull(doc.getElementsByTag("td").get(totalDeaths).text()),
                    checkForNull(doc.getElementsByTag("td").get(newDeaths).text()),
                    checkForNull(doc.getElementsByTag("td").get(totalRecovered).text()),
                    checkForNull(doc.getElementsByTag("td").get(activeCases).text()),
                                 doc.getElementsByTag("td").get(firstCase).text()));
            country += 11;
            totalCases += 11;
            newCases += 11;
            totalDeaths += 11;
            newDeaths += 11;
            totalRecovered += 11;
            activeCases += 11;
            firstCase += 11;
            if (doc.getElementsByTag("td").get(country).text().contains("Total:")){
                break;
            }
        }
        for (Article a: articles){
            System.out.println(a);
        }
        return articles;
    }

    public static Article kyrgystanStat(){
        for (Article a : getOfficialTable()){
            if (a.getCountry().equals("Kyrgyzstan"))
                return a;
        }
        return null;
    }
    public static String checkForNull(String str){
        if (str.equals("")){
            return "0";
        }
        return str;
    }
}
