package com.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		// gRPC 서버를 시작
		Server server = ServerBuilder.forPort(50051)
				.addService(new CalculatorService())
				.build()
				.start();
		System.out.println("Server started, listening on " + server.getPort());
		server.awaitTermination();
	}

}
