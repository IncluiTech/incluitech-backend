package com.ages.incuitech.backend.solucaodeproblemasservice.api.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.TagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository repository;


    @Test
    public void saveTagShouldReturnTag() {
        // arrange
        Tag tag = TagStub.getModelStub();
        //Pode isso?
        Tag tagresponse=tagService.salvar(tag.getNome());
        assertEquals(tagresponse.getId(), tag.getId());
        /*
        when(repository.findAll()).thenReturn(Lists.newArrayList(tag));

        // act
        List<Tag> tags = tagService.
        // assert
        Optional<SolucionadorResponse> response = solucionadores.stream().filter(solucionadorResponse -> solucionadorResponse.getId().equals(solucionador.getId())).findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), solucionador.getEmail());
        assertEquals(response.get().getNome(), solucionador.getNome());
        assertEquals(response.get().getTelefone(), solucionador.getTelefone());
        assertEquals(response.get().getStatusCadastro(), solucionador.getStatusCadastro());
        verify(repository).findAll();
*/
    }
}
