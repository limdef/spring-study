package main

import (
	"client/calculator"
	"context"
	"fmt"

	"github.com/apache/thrift/lib/go/thrift"
)

func main() {
	transport, err := thrift.NewTSocket("localhost:9090") // Thrift 서버의 주소와 포트
	if err != nil {
		fmt.Println("Error opening socket:", err)
		return
	}
	defer transport.Close()

	protocolFactory := thrift.NewTBinaryProtocolFactoryDefault()
	client := calculator.NewCalculatorClientFactory(transport, protocolFactory)

	if err := transport.Open(); err != nil {
		fmt.Println("Error opening socket to server:", err)
		return
	}
	defer transport.Close()

	// 서버의 메서드 호출 예시
	result, err := client.Add(context.Background(), 10, 5)
	if err != nil {
		fmt.Println("Error calling Add:", err)
		return
	}
	fmt.Println("10 + 5 =", result)
}
