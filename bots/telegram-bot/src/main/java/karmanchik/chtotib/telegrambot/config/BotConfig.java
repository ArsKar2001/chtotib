package karmanchik.chtotib.telegrambot.config;

import karmanchik.chtotib.data.config.DataSourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DataSourceConfig.class})
public class BotConfig {
}
