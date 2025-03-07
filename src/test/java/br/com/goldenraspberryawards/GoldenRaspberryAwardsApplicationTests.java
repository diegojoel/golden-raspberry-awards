package br.com.goldenraspberryawards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GoldenRaspberryAwardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnAwardIntervals() throws Exception {
		mockMvc.perform(get("/v1/awards/intervals"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.min").isArray())
				.andExpect(jsonPath("$.max").isArray())
				.andExpect(jsonPath("$.min[*].interval").value(1))
				.andExpect(jsonPath("$.max[*].interval").value(13));
	}
}
