package com.study.thrift.studythrfit;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudyThrfitApplication {

	public static void main(String[] args) {
		try {
			Calculator.Processor<CalculatorHandler> processor = new Calculator.Processor<>(new CalculatorHandler());
			TServerTransport serverTransport = new TServerSocket(9090);
			TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
			serverArgs.processor(processor);
			serverArgs.protocolFactory(new TBinaryProtocol.Factory());

			TThreadPoolServer server = new TThreadPoolServer(serverArgs);
			System.out.println("Starting the Thrift server on port 9090...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
