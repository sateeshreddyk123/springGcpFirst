/**
 * 
 */
package com.example.standalone.springbootgcp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tpurusot
 *
 */
@Controller
public class ContactUsController {

	@RequestMapping(value="/contactus", method = RequestMethod.GET)
	public String showContactUsPage(ModelMap model){
		return "contactus";
	}
}
