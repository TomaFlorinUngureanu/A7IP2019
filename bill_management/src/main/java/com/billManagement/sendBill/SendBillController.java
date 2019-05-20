package com.billManagement.sendBill;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.billManagement.model.BillInfo;
import com.itextpdf.text.DocumentException;

@CrossOrigin("http://localhost:4200")
@RestController
public class SendBillController {

	@Autowired
	
	SendBillService billService;
	
	 @RequestMapping(method=RequestMethod.POST,value="/bill/sendBill")
	 @ResponseBody
	 public String sendBill(@RequestBody BillInfo billInfo) throws FileNotFoundException, MessagingException, DocumentException {
		 return billService.sendBill(billInfo);
	 }	 
	
}
