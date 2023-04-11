package com.khaoula.livres.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khaoula.livres.entities.Livre;
import com.khaoula.livres.service.LivreService;

@Controller
public class LivreController {
	@Autowired
	LivreService livreService;
	@RequestMapping("/showCreate")
	public String showCreate()
	{
	return "createLivre";
	}
	
	@RequestMapping("/saveLivre")
	public String saveLivre(@ModelAttribute("livre") Livre livre, 
	 @RequestParam("date") String date,
	 ModelMap modelMap) throws
	ParseException 
	{
	//conversion de la date 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date datePublication = dateformat.parse(String.valueOf(date));
	 livre.setDatePublication(datePublication);
	 
	Livre saveLivre = livreService.saveLivre(livre);
	String msg ="livre enregistré avec Id "+saveLivre.getIdLivre();
	modelMap.addAttribute("msg", msg);
	return "createLivre";
	}
	@RequestMapping("/ListeLivres")
	public String listeLivres(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
	{
		Page<Livre> lvres = livreService.getAllLivresParPage(page, size);
		modelMap.addAttribute("livres", lvres);
		modelMap.addAttribute("pages", new int[lvres.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeLivres";
	}
	@RequestMapping("/supprimerLivre")
	public String supprimerProduit(@RequestParam("id") Long id,
	 ModelMap modelMap,
	 @RequestParam (name="page",defaultValue = "0") int page,
	 @RequestParam (name="size", defaultValue = "2") int size)

	{ 
	livreService.deleteLivreById(id);
	Page<Livre> lvres = livreService.getAllLivresParPage(page, 
			size);
			modelMap.addAttribute("livres", lvres);
			modelMap.addAttribute("pages", new int[lvres.getTotalPages()]);
			modelMap.addAttribute("currentPage", page);
			modelMap.addAttribute("size", size);
	return "listeLivres";
	}
	@RequestMapping("/modifierLivre")
	public String editerLivre(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Livre l= livreService.getLivre(id);
	modelMap.addAttribute("livre", l);
	return "editerLivre";
	}
	@RequestMapping("/updateLivre")
	public String updateLivre(@ModelAttribute("livre") Livre livre,
	@RequestParam("date") String date,
	ModelMap modelMap) throws ParseException 
	{
	//conversion de la date 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date datePublication= dateformat.parse(String.valueOf(date));
	 livre.setDatePublication(datePublication);
	 
	livreService.updateLivre(livre);
	 List<Livre> lvres = livreService.getAllLivres();
	 modelMap.addAttribute("livres", lvres);
	return "listeLivres";
	}
}
