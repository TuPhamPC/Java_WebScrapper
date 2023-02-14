package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.Vua;

public class VuaScraper implements Scraper {

	@Override
	public void scrape() throws IOException {
		Document doc=  Jsoup.connect("https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam").get();
		int i=0;
		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
		
		ArrayList<Vua> listVua=new ArrayList<Vua>();
		doc.select("br").append("\\n");
		 
		for(Element row : doc.select("table[cellpadding=\"0\"]:not([width]) tr")) {
			
				if(row.select("td:nth-of-type(2)").text().equals("")) continue;
				else {
					String Ten=row.select("td:nth-of-type(2)").text(); Ten = Ten.replaceAll("\\[\\S*\\]","");
					Ten=Ten.replaceAll(" \\\\n",", ");
					String mieuHieu=row.select("td:nth-of-type(3)").text();  mieuHieu=mieuHieu.replaceAll("\\[\\S*\\]","");
					mieuHieu=mieuHieu.replaceAll(" \\\\n",", ");
					String thuyHieu=row.select("td:nth-of-type(4)").text();  thuyHieu=thuyHieu.replaceAll("\\[\\S*\\]","");
					String nienHieu=row.select("td:nth-of-type(5)").text(); nienHieu=nienHieu.replaceAll("\\[\\S*\\]","");
					nienHieu=nienHieu.replaceAll(" \\\\n",", ");
					String tenHuy=row.select("td:nth-of-type(6)").text();    tenHuy=tenHuy.replaceAll("\\[\\S*\\]","");
					tenHuy=tenHuy.replaceAll(" \\\\n",", ");
					String theThu=row.select("td:nth-of-type(7)").text();    theThu=theThu.replaceAll("\\[\\S*\\]","");
					String triVi=row.select("td:nth-of-type(8)").text();     
					triVi=triVi.concat(row.select("td:nth-of-type(9)").text()); 
					triVi=triVi.concat(row.select("td:nth-of-type(10)").text());triVi=triVi.replaceAll("\\[\\S*\\]","");
					
					
					
					
					listVua.add(new Vua(Ten, mieuHieu, thuyHieu, nienHieu, tenHuy, theThu, triVi));
				}
				
			
		}
		Gson gson=new Gson();
		String json=gson.toJson(listVua);
		FileOutputStream fos=null;
        fos=new FileOutputStream("Vua.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}
	
}
