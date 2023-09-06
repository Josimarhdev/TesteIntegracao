package br.com.lembretes;

import br.com.lembretes.controller.LembretesController;
import br.com.lembretes.controller.PessoasController;
import br.com.lembretes.entity.Lembretes;
import br.com.lembretes.repository.PessoasRepository;
import br.com.lembretes.repository.LembretesRepository;
import br.com.lembretes.entity.Pessoas;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class LembretesApplicationTests {

	@MockBean
	PessoasRepository pessoaRep;

	@MockBean
	LembretesRepository lembreteRep;



	private final PessoasController controller = new PessoasController();

	private final LembretesController controllerL = new LembretesController();

	private List<Pessoas> pessoaList;


	@BeforeEach
	void injectDataPessoas() {

		Optional<Pessoas> pessoa = Optional.of(new Pessoas(1L, "Willian"));

		pessoaList = new ArrayList<>();

		Pessoas pessoa1 = new Pessoas(1L, "Marcão");
		Pessoas pessoa2 = new Pessoas(2L, "Valder");

		pessoaList.add(pessoa1);
		pessoaList.add(pessoa2);


		Long id = 1L;
		Mockito.when(pessoaRep.findById(id)).thenReturn(pessoa);
		Mockito.when(pessoaRep.findAll()).thenReturn(pessoaList);
		Mockito.when(pessoaRep.findByNome("Marcão")).thenReturn(pessoaList);


		// Injete a instância mock no controlador
		ReflectionTestUtils.setField(controller, "pessoasRep", pessoaRep);

	}

	@BeforeEach
	void injectDataLembretes() {

		Optional<Lembretes> lembrete = Optional.of(new Lembretes(1L, "Lembrete"));


		Long id = 1L;
		Mockito.when(lembreteRep.findById(id)).thenReturn(lembrete);





		ReflectionTestUtils.setField(controllerL, "lembretesRep", lembreteRep);

	}

	@Test
	void testPessoaGet() {
		var pessoaController = controller.findById(1L);
		Long id = pessoaController.getBody().getId().longValue();
		System.out.println(id);
		Assert.assertEquals(1L, id, 0);
	}



	@Test
	void testPessoaList() {
		ResponseEntity<List<Pessoas>> pessoaController = controller.ListaCompleta();
		List<Pessoas> pessoaListController = pessoaController.getBody();

		Assert.assertNotNull(pessoaListController);
		Assert.assertEquals(pessoaList.size(), pessoaListController.size());

		for (int i = 0; i < pessoaList.size(); i++) {
			Assert.assertEquals(pessoaList.get(i), pessoaListController.get(i));
		}

	}

	@Test
	void testPessoaNome(){
		ResponseEntity<List<Pessoas>> pessoaController = controller.buscarPorNome("Marcão");
		List<Pessoas> pessoaListController = pessoaController.getBody();

		Assert.assertNotNull(pessoaListController);
		Assert.assertEquals(pessoaList.size(), pessoaListController.size());
		Assert.assertEquals(pessoaList.get(1), pessoaListController.get(1));
	}


	@Test
	void testLembreteGet() {
		var lembreteController = controllerL.findById(1L);
		Long id = lembreteController.getBody().getId().longValue();
		System.out.println(id);
		Assert.assertEquals(1L, id, 0);
	}







}