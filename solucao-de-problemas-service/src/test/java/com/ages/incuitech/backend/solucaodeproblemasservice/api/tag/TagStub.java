package com.ages.incuitech.backend.solucaodeproblemasservice.api.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

public class TagStub {
	   private TagStub() {
	    }

	    public static Tag getModelStub() {
	        return Tag.builder()
	                .id(1L)
	                .nome("Fulano")	                       
	                .build();
	    }
}
