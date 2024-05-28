package main

import (
	pb "client/calculator"
	"context"
	"fmt"
	"google.golang.org/grpc"
	"log"
)

func main() {
	// gRPC 서버에 연결
	conn, err := grpc.Dial("localhost:50051", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("could not connect: %v", err)
	}
	defer conn.Close()

	// gRPC 클라이언트 생성
	client := pb.NewCalculatorClient(conn)

	// Add RPC 호출
	addResponse, err := client.Add(context.Background(), &pb.AddRequest{
		Num1: 10,
		Num2: 5,
	})
	if err != nil {
		log.Fatalf("Add RPC failed: %v", err)
	}
	fmt.Printf("Add result: %d\n", addResponse.GetResult())

	// Subtract RPC 호출
	subtractResponse, err := client.Subtract(context.Background(), &pb.SubtractRequest{
		Num1: 10,
		Num2: 5,
	})
	if err != nil {
		log.Fatalf("Subtract RPC failed: %v", err)
	}
	fmt.Printf("Subtract result: %d\n", subtractResponse.GetResult())
}
