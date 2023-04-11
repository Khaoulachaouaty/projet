package com.khaoula.livres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.khaoula.livres.entities.Livre;
import com.khaoula.livres.repos.LivreRepository;
@Service
public class LivreServiceImpl implements LivreService{
	
	@Autowired
	LivreRepository livreRepository;

	@Override
	public Livre saveLivre(Livre l) {
		return livreRepository.save(l);
	}

	@Override
	public Livre updateLivre(Livre l) {
		return livreRepository.save(l);
	}

	@Override
	public void deleteLivre(Livre l) {
		livreRepository.delete(l);

	}

	@Override
	public void deleteLivreById(Long id) {
		livreRepository.deleteById(id);
	}

	@Override
	public Livre getLivre(Long id) {
		return livreRepository.findById(id).get();
	}

	@Override
	public List<Livre> getAllLivres() {
		return livreRepository.findAll();
	}
	@Override
	public Page<Livre> getAllLivresParPage(int page, int size) {
	return livreRepository.findAll(PageRequest.of(page, size));
	}

	
}
