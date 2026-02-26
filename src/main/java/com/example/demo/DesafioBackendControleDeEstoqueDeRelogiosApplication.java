package com.example.demo;

import com.example.demo.entity.Relogio;
import com.example.demo.repository.RelogioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DesafioBackendControleDeEstoqueDeRelogiosApplication implements CommandLineRunner {

	private final RelogioRepository relogioRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendControleDeEstoqueDeRelogiosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Relogio> relogios = relogioRepository.findAll();
		if (relogios.isEmpty()){

		}
	}
}
