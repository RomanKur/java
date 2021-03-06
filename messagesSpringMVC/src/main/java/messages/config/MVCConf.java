package messages.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import messages.dao.Dao;
import messages.dao.DaoImpl;

@Configuration
@EnableWebMvc
public class MVCConf extends WebMvcConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSource dataSource() {

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("db/sql/create-db.sql")
				// .addScript("db/sql/insert-data.sql")
				.build();
		return db;
	}

	@Bean
	public Dao getCommentDao() {
		return new DaoImpl(dataSource());
	}

}
