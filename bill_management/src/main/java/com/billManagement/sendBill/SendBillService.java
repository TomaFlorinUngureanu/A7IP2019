package com.billManagement.sendBill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.billManagement.model.BillInfo;
import com.billManagement.model.JwtUser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.minidev.json.JSONObject;



@Component
public class SendBillService {
	
    @Autowired
    private JavaMailSender emailSender;
	
   
	
	public String sendBill(BillInfo billInfo) throws MessagingException, FileNotFoundException, DocumentException {
		
		Document pdfDoc = new Document(PageSize.A4);
		PdfWriter.getInstance(pdfDoc, new FileOutputStream("src/main/resources/Bill.pdf"))
				.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		pdfDoc.open();
		Font myfont = new Font();
		myfont.setStyle(Font.NORMAL);
		myfont.setSize(11);
		pdfDoc.add(new Paragraph("\n"));
		
		Paragraph para = new Paragraph("Amount: "+billInfo.getAmount(), myfont);
		para.setAlignment(Element.ALIGN_JUSTIFIED);
		pdfDoc.add(para);
		
		para = new Paragraph("Currency: "+billInfo.getCurrency(), myfont);
		para.setAlignment(Element.ALIGN_JUSTIFIED);
		pdfDoc.add(para);
		
		para = new Paragraph("ChargeId: "+billInfo.getChargeId(), myfont);
		para.setAlignment(Element.ALIGN_JUSTIFIED);
		pdfDoc.add(para);
		
		
		pdfDoc.close();
		
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
        helper.setSubject("Bill Informations");
        helper.setText("You can billing details in atachment.  ");
        helper.setTo(billInfo.getEmail());
        helper.setFrom("12345zornet123452@gmail.com");

        helper.addAttachment("Bill.pdf", new ClassPathResource("Bill.pdf"));
        
        emailSender.send(message);
        
		//smtpMailSender.send(JwtUser.getUserName(), "Your iUber password", "Your password is:  "+password ,password);
		
		JSONObject json = new JSONObject();
		json.put("message", "Success");
		return json.toString();
	}

}
