package com.example.test1.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {
	@RequestMapping("/result.do") 
    public String result(Model model) throws Exception{
        return "/result"; 
    }
	
	@RequestMapping("/upload.do") 
    public String upload(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		request.setAttribute("map", map);
        return "/upload"; 
    }
	
	@RequestMapping("/testUpload.dox")
	public String test(@RequestParam("file1") MultipartFile multi, @RequestParam("boardNo") int boardNo, HttpServletRequest request,HttpServletResponse response, Model model)
	{
		String url = null;
		String path="c:\\img";
		try {

			//String uploadpath = request.getServletContext().getRealPath(path);
			String uploadpath = path;
			String originFilename = multi.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."),originFilename.length());
			long size = multi.getSize();
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("uploadpath : " + uploadpath);
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			String path2 = System.getProperty("user.dir");
			System.out.println("Working Directory = " + path2 + "\\src\\webapp\\img");
			if(!multi.isEmpty())
			{
				File file = new File(path2 + "\\src\\main\\webapp\\test", originFilename);
				multi.transferTo(file);
				
				
				model.addAttribute("filename", multi.getOriginalFilename());
				model.addAttribute("uploadPath", file.getAbsolutePath());
				
				return "redirect:/result.do";
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return "redirect:list.do";
	}
	
	static public String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
}
