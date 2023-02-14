package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.TrieuDai;

public class TrieuDaiScraper implements Scraper {

	@Override
	public void scrape() throws IOException {
		Document doc=  Jsoup.connect("https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam").get();
		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));

		ArrayList<TrieuDai> listTrieudai=new ArrayList<TrieuDai>();
		int i=0;
		 
		for(Element row : doc.select("table.wikitable:not([style]) tr")) {
			    i++;
			    if(i==1) continue;
			    else {
			
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else {
					String Ten=row.select("td:nth-of-type(1)").text(); Ten=Ten.replaceAll("\\[\\S*\\]","");
					String nguoiSanglap=row.select("td:nth-of-type(2)").text(); nguoiSanglap=nguoiSanglap.replaceAll("\\[\\S*\\]","");
					String queHuong=row.select("td:nth-of-type(3)").text();   queHuong=queHuong.replaceAll("\\[\\S*\\]","");
					String kinhDo=row.select("td:nth-of-type(4)").text();  kinhDo=kinhDo.replaceAll("\\[\\S*\\]","");
				
					
					
					listTrieudai.add(new TrieuDai(Ten, nguoiSanglap, queHuong, kinhDo));
				}
				
			    }
			    
			
		}
		
		Gson gson=new Gson();
		String json=gson.toJson(listTrieudai);
		FileOutputStream fos=null;
        fos=new FileOutputStream("TrieuDai.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}

}
