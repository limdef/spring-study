backend to backend  
server : java  
client :  go

### 동작 이해

#### 서버

1. thrift IDL (Calculator.thrift) 로 인터페이스 정의.
2. thrift 컴파일러로 각 언어에 맞게 인터페이스를 포함한 코드를 생성. 
> thrift --gen java Calculator.thrift
3. server에서 IDL로 정의한 함수들을 구현.
4. 구현한 핸들러를 포함시켜 thrfit 서버 실행.


#### 클라이언트

1. 서버와 마찬가지로 thrift 컴파일러로 go 코드 생성.
> thrift --gen go Calculator.thrift
2. thrift 서버 연결 후 client 객체로 서버의 함수 호출.


#### 정리
- 서버와 클라가 공통의 thrift IDL만 공유하기만 하면 된다.
- 서버와 클라가 각각 thrift로 코드 생성 후 프로젝트에 포함시켜야 한다.
- 서버는 핸들러를 구현하고 제공해줘야 하고 클라는 사용만 하면 된다.
- IDL이 정의 된 이후에 구현 내용을 변경해도 문제는 없다.  
