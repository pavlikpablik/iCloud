/**
 * 
 */
package com.manpowergroup.cn.icloud.sys.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.PropertiesLoader;
import com.manpowergroup.cn.core.web.BaseAction;

public class ImportErrorExcelAction extends BaseAction {
	
	private static final long serialVersionUID = -8767904881034472428L;
	
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 下载导入错误数据
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception{
		response.setCharacterEncoding("utf-8");
		PropertiesLoader p = new PropertiesLoader(ICloudConst.EXCEL_EXPORT);
		String filePath = p.getProperty("icloud.platform.fileDir") + fileName;
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				File file = new File(filePath);
				response.setContentType(new MimetypesFileTypeMap().getContentType(file));
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String("问题数据.xlsx".getBytes("GBK"),
										"iso8859-1"));
				in = new FileInputStream(file);
				out = response.getOutputStream();
				IOUtils.copy(in, out);
			} finally {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException("读取模板发生错误,请联系系统管理员。"); 
		}
		return null;
	}
	
}
