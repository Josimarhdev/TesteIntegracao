package br.com.lembretes.repository;

import br.com.lembretes.entity.Lembretes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembretesRepository extends JpaRepository<Lembretes, Long> {

}
