package br.com.lembretes.service;

import br.com.lembretes.entity.Pessoas;
import br.com.lembretes.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoasService {

    @Autowired
    private PessoasRepository pessoasRepository;


}
