package com.arquitetura.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorRelatorio {
	
//	public static byte[] gerarRelatorio(List lista, Map<String, Object> parametros, String nomeRelatorio, ServletContext context){
	public static byte[] gerarRelatorio(List lista, String nomeRelatorio){	
	try {
			InputStream fonte = GeradorRelatorio.class.getResourceAsStream("/rel/teste.jrxml");
			JasperReport report = JasperCompileManager.compileReport(fonte);
			JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(lista));
			return JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	
	public void geraRelatorio(List list) throws JRException {

		
		InputStream fonte = GeradorRelatorio.class.getResourceAsStream("/rel/teste.jrxml");
		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(list));
		JasperViewer.viewReport(print,false);
		
		
//		String caminho_jasper = "/rel/teste.jasper";
//		InputStream relJasper = getClass().getResourceAsStream(caminho_jasper);
//
//		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
//
//		Map parametros = new HashMap();
//		JasperPrint impressao = null;
//		try {
//			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
//			JasperViewer viwer = new JasperViewer(impressao, true);
//			viwer.setVisible(true);
//		} catch (JRException e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
	}
}
