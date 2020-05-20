package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

public class TagStub {

	    public static Tag getModelStub() {
	        return Tag.builder()
					.id(1L)
	                .nome("Fulano")	                       
	                .build();
	    }
}
