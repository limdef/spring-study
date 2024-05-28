backend to backend  
server : java  
client : go

### 동작 이해

#### 서버

1. grpc Proto (calculator.proto) 로 인터페이스 정의.
2. protoc과 grpc java plugin이 필요하다.
    1. protobuf은 brew 로 설치
    2. grpc java는 cpu 아키텍처 문제로 쉽게 얻을 수 없어서 직접 빌드하여 설치한다.
   > git clone https://github.com/grpc/grpc-java.git  
   > cd grpc-java/compiler  
   > ../gradlew java_pluginExecutable -PskipAndroid=true  
   > build 디렉토리에 protoc-gen-grpc-java 실행 파일이 빌드가 된다!
3. grpc 컴파일러 protobuf 로 각 언어에 맞게 코드 생성.
> protoc --java_out={$output dir path} {$proto file}  
> -> protoc --java_out=. --grpc-java_out=. --plugin=protoc-gen-grpc-java=./protoc-gen-grpc-java calculator.proto  
> CalculatorGrpc.java 와 CalculatorProto.java 가 생성이 된다.
4. server 에서 proto 로 정의한 함수들을 구현.
5. grpc 서버 실행.


#### 클라이언트

1. 정의된 Proto로 go 코드 생성. go_package option 필요.
> protoc --go_out=. --go-grpc_out=. calculator.proto
2. 생성된 go 코드를 import 한다.
> go mod {source directory}  
> go mod tidy
3. 서버에 연결한 후에 함수들을 호출한다.


##### 정리
- thrift와 매우 유사하다.
- thrift는 여러 프로토콜 제공하고, grpc는 http/2 프로토콜만 제공
- thrift에 비해 버전 호환성과 디펜던시가 매우 까다로움.
    - protobuf로 컴파일 하는 버전과 서버를 구현할 때 필요한 디펜던시의 버전을 맞춰줘야 한다.
- 성능은 우수한 편이라고 한다.
    - grpc는 netty 와 같은 고성능의 네트워크 동작을 기본으로 제공한다.
    - thrift는 핸들러만 구현하고 netty로 동작하게 하려면 직접 구현해야한다.? (예상)

#### 참고

- net.devh:grpc-server-spring-boot-starter 는 뭔가 호환이 안 된다.
- 아래 디펜던시는 꽤 최신 버전이 필요하다... protobuf는 3.25.x 가 안 되었고, io.grpc 는 1.35.x 가 안 되었음.
```
implementation group: 'com.google.protobuf', name: 'protobuf-java', version: '4.27.0'
implementation 'io.grpc:grpc-netty-shaded:1.64.0'
implementation 'io.grpc:grpc-protobuf:1.64.0'
implementation 'io.grpc:grpc-stub:1.64.0'
```

