package com.khaoula.livres;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.khaoula.livres.entities.Livre;
import com.khaoula.livres.repos.LivreRepository;
import com.khaoula.livres.service.LivreService;


@SpringBootTest
class LivresApplicationTests {
@Autowired
private LivreRepository livreRepository;
@Autowired
private LivreService livreService;
@Test
public void testCreateLivre() {
Livre l = new Livre("Al-Ayyam Al-Tawila",10.000,new Date());
livreRepository.save(l);
}

@Test
public void testFindLivre()
{
Livre p = livreRepository.findById(1L).get(); 
System.out.println(p);
}
@Test
public void testUpdateLivre()
{
Livre l = livreRepository.findById(2L).get();
l.setPrixLivre(2000.0);
livreRepository.save(l);
System.out.println(l);
}

@Test
public void testDeleteProduit()
{
livreRepository.deleteById(1L);;
}
 
@Test
public void testListerTousProduits()
{
List<Livre> prods = livreRepository.findAll();
for (Livre p : prods)
{
System.out.println(p);
}
}
@Test
public void testFindByNomLivreContains()
{
Page<Livre> prods = livreService.getAllLivresParPage(0,2);
System.out.println(prods.getSize());
System.out.println(prods.getTotalElements());
System.out.println(prods.getTotalPages());
prods.getContent().forEach(p -> {System.out.println(p.toString());
 });
/*ou bien
for (Produit p : prods)
{
System.out.println(p);
} */
}



}