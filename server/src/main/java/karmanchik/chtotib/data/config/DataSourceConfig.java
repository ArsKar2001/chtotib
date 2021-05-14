package karmanchik.chtotib.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "karmanchik/chtotib/data/entity")
@EnableJpaRepositories(basePackages = "karmanchik.chtotib.data.daos")
public class DataSourceConfig {
}
