package com.manpowergroup.cn.icloud;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.PropertiesLoader;


public class Test {
	public static void main(String[] args) {
		PropertiesLoader p = new PropertiesLoader(ICloudConst.EXCEL_EXPORT);
		String sss1 = p.getProperty("icloud.platform.fileDir");
		String sss2 = p.getProperty("template.excel.SsbExcelTemplate");
		String filePath = sss1+sss2;
		System.out.println(sss1);
		System.out.println(sss2);
		System.out.println(filePath);
		
		System.out.println(1000*60*10);
		
		
		/*try {
            URL url = new URL("http://10.86.36.22:8080/iCloud/webService/export/exportSsbDecalre/2012-03,%E6%B7%B1%E5%9C%B3%E5%B8%82%E9%94%90%E6%97%97%E5%8A%B3%E5%8A%A1%E6%B4%BE%E9%81%A3%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8,%E6%B7%B1%E5%9C%B3");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            Scanner scanner;
            String response;
            if (conn.getResponseCode() != 200) {
                scanner = new Scanner(conn.getErrorStream());
                response = "Error From Server \n\n";
            } else {
                scanner = new Scanner(conn.getInputStream());
                response = "Response From Server \n\n";
            }
            scanner.useDelimiter("\\Z");
            System.out.println(response + scanner.next());
            scanner.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	}

}
