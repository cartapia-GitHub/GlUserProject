package com.glusers.api;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.glusers.api.repository.PhoneRepository;
import com.glusers.api.repository.UserRepository;

@SpringBootTest
@WebMvcTest
class GlProjectH2UsersApplicationTests {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepository;
	//@MockBean
	//PhoneRepository phoneRepository;
	
	@Test
	void contextLoads() throws Exception{
		Mockito.when(userRepository.findAll()).thenReturn(
				Collections.emptyList()
				);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/all/")
					   .accept(MediaType.APPLICATION_JSON)
				).andReturn();
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(userRepository).findAll();
		
	}
	
	

}
