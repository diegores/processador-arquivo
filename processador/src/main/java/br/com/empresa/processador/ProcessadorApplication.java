package br.com.empresa.processador;

import br.com.empresa.processador.util.WatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessadorApplication implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessadorApplication.class);

	@Autowired
	private WatcherService watcherService;

	public static void main(String[] args) {
		SpringApplication.run(ProcessadorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Inicializando o WatcherService");
		watcherService.watch();
	}

}
