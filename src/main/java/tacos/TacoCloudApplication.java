package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import tacos.domain.Ingredient;

@Slf4j
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadIngredients(MongoTemplate mongoTemplate) {
		return args -> {
			log.info("Clearing existing ingredients...");
			mongoTemplate.dropCollection(Ingredient.class);

			log.info("Loading initial ingredients into MongoDB...");
			mongoTemplate.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
			mongoTemplate.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
			mongoTemplate.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
			mongoTemplate.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
			mongoTemplate.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
			mongoTemplate.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
			mongoTemplate.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
			mongoTemplate.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
			mongoTemplate.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
			mongoTemplate.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

			long count = mongoTemplate.count(new Query(), Ingredient.class);
			log.info("Successfully loaded {} ingredients into MongoDB.", count);
		};
	}

}
