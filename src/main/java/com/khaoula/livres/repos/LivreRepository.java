package com.khaoula.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khaoula.livres.entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}
