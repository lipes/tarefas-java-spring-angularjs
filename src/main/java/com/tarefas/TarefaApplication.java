package com.tarefas;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration	
public class TarefaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefaApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}
}
