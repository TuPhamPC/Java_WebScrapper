package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.SuKien;

public class SuKienScraper implements Scraper {

	@Override
	public void scrape() throws IOException {

    	Document doc=  Jsoup.connect("https://vi.wikibooks.org/wiki/Nh%E1%BB%AFng_s%E1%BB%B1_ki%E1%BB%87n_ch%C3%ADnh_c%E1%BB%A7a_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam_t%E1%BB%AB_th%E1%BA%BF_k%E1%BB%89_10_%C4%91%E1%BA%BFn_19").get();
		int i=0;
		Document doc2=Jsoup.connect("https://ccedu.vn/doc-tin/tong-hop-kien-thuc-lich-su-12/").get();

		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
		doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));
		
		ArrayList<SuKien> listSukien=new ArrayList<SuKien>();
		
		 
		for(Element row : doc.select("table.wikitable tr")) {
			i++;
			if(i==1) continue;
			else {
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else { 
					String nam=row.select("td:nth-of-type(1)").text();  nam=nam.replaceAll("\\[\\S*\\]","");
					String suKien=row.select("td:nth-of-type(2)").text(); suKien=suKien.replaceAll("\\[\\S*\\]","");
					
					
					listSukien.add(new SuKien(suKien,nam));
				
					
				}
			}
		}
		i=0;
		 
		for(Element row : doc2.select("table tr")) {
			i++;
			if(i==1) continue;
			else {
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else {
					String nam=row.select("td:nth-of-type(1)").text();
					String suKien=row.select("td:nth-of-type(2)").text();
					
					if(nam=="Thời gian"&& suKien=="Nội dung sự kiện") continue;
					else {

						listSukien.add(new SuKien(suKien,nam));
						System.out.println(suKien+" "+ nam +" " );
					}
					
				}
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(listSukien);
		FileOutputStream fos=null;
		fos=new FileOutputStream("SuKien.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}

}
