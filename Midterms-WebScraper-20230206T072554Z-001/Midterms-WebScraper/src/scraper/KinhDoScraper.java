package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.KinhDo;

public class KinhDoScraper implements Scraper {

	@Override
	public void scrape() throws IOException {
    	Document doc=  Jsoup.connect("http://www.atabook.com/nguon-goc-tu-ngu/danh-sach-cac-kinh-do-hoac-thu-do-viet-nam-qua-cac-thoi-ky").get();
		int i=0;
		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
		
		ArrayList<KinhDo> listKinhdo=new ArrayList<KinhDo>();
		
		for(Element row : doc.select("table tr")) {
			i++;
			if(i==1) continue;
			else if(i==2) continue;
			else {
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else {
					String kinhDo=row.select("td:nth-of-type(1)").text();  kinhDo=kinhDo.replaceAll("\\[\\S*\\]","");
					String thoiKy=row.select("td:nth-of-type(2)").text();  thoiKy=thoiKy.replaceAll("\\[\\S*\\]","");
					String thoiGian=row.select("td:nth-of-type(3)").text();	thoiGian=thoiGian.replaceAll("\\[\\S*\\]","");
					
					listKinhdo.add(new KinhDo(kinhDo, thoiKy, thoiGian));
				}
			}
			
		}
		Gson gson=new Gson();
		String json=gson.toJson(listKinhdo);
		FileOutputStream fos = null;
		fos = new FileOutputStream("KinhDo.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}

}
