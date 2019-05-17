package com.kgate.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectAttributeController {
	@RequestMapping(value = "test/{id}")
	public ModelAndView handleTestRequest(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
		if (!id.matches("\\d+")) {
			model.addAttribute("msg", "id should only have digits");
			return new ModelAndView("error-page");
		} else {
			ra.addAttribute("attr", "attrVal");
			ra.addFlashAttribute("flashAttr", "flashAttrVal");
			return new ModelAndView("redirect:/test2/{id}");
		}
	}

	@RequestMapping("test2/{id}")
	public ModelAndView handleRequest(@PathVariable("id") String id, @RequestParam("attr") String attr,
			@ModelAttribute("flashAttr") String flashAttr, Model model) {

		model.addAttribute("id", id);
		model.addAttribute("attr", attr);
		model.addAttribute("flashAttr", flashAttr);
		return new ModelAndView("my_page");
	}
}
