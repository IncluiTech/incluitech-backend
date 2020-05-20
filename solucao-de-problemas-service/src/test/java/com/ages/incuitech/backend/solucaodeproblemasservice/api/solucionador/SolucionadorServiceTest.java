package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tag.TagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SolucionadorServiceTest {

	@InjectMocks
	private SolucionadorService solucionadorService;

	@Mock
	private SolucionadorRepository repository;
	@Mock
	private TagService tagService;
	@Mock
	private TagSolucionadorService tagSolucionadorService;

	@Test
	public void findAllSolucionadoresShouldReturnSolucionador() {
		// arrange
		Solucionador solucionador = SolucionadorStub.getModelStub();
		when(repository.findAll()).thenReturn(Lists.newArrayList(solucionador));

		// act
		List<SolucionadorResponse> solucionadores = solucionadorService.findAllSolucionadores();

		// assert
		Optional<SolucionadorResponse> response = solucionadores.stream()
				.filter(solucionadorResponse -> solucionadorResponse.getId().equals(solucionador.getId())).findFirst();
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), solucionador.getEmail());
		assertEquals(response.get().getNome(), solucionador.getNome());
		assertEquals(response.get().getTelefone(), solucionador.getTelefone());
		assertEquals(response.get().getStatusCadastro(), solucionador.getStatusCadastro());
		verify(repository).findAll();

	}


	@Test
	public void testeSalvarSolucionadorDeveRetornarSolucionadorResponse() {
		SolucionadorRequest solucionador = SolucionadorStub.getSolucionadorRequest();
		when(repository.save(any())).thenReturn(SolucionadorMapper.mapToModel(solucionador));
		when(tagService.salvar(any())).thenReturn(TagStub.getModelStub());

		SolucionadorResponse solucionadorSalvo = solucionadorService.salvar(solucionador);
		assertEquals(solucionadorSalvo.getNome(), solucionador.getNome());
	}

	private Solucionador capturarSolucionadorSalva() {
		ArgumentCaptor<Solucionador> captor = ArgumentCaptor.forClass(Solucionador.class);
		Mockito.verify(repository, Mockito.atLeastOnce()).save(captor.capture());
		return captor.getValue();
	}

}