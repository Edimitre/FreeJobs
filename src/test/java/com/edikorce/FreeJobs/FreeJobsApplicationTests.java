package com.edikorce.FreeJobs;

import com.edikorce.FreeJobs.model.Item;
import com.edikorce.FreeJobs.model.Job;
import com.edikorce.FreeJobs.repository.ItemRepo;
import com.edikorce.FreeJobs.repository.JobRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class FreeJobsApplicationTests {

	@Autowired
	public JobRepo jobRepo;

	@Autowired
	public ItemRepo itemRepo;
	@Test
	public void getAllArticles(){

		List<Job> jobList = jobRepo.getAllJobsByUserId(2L);

		Assertions.assertThat(jobList.isEmpty()).isFalse();

	}

	@Test
	public void getAllItemsByUserId(){

		List<Item> itemList = itemRepo.getAllItemsByUserId(2L);

		Assertions.assertThat(itemList.isEmpty()).isFalse();

	}

}
