package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

public class TagStub {

	    public static Tag buildTagStub(Long id, String name) {
	        return Tag.builder()
					.id(id)
	                .nome(name)
	                .build();
	    }
}
