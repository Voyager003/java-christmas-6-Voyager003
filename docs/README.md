# 크리스마스 프로모션

---

### 3주차 피드백

- 현재 객체의 상태를 보기 위한 로그 메시지 성격이 강하다면 toString()을 통해 구현한다.
- View에서 사용할 데이터라면 getter 메서드를 통해 데이터를 전달한다.
- 연관성이 있는 상수는 static final 대신 enum을 활용한다.
- final 키워드를 사용해 값의 변경을 막는다.
- 객체에서 데이터를 꺼내지(get)않고 메시지를 던지도록 구조를 바꿔 데이터를 가지는 객체가 일하도록 한다.
- 인스턴스 변수의 수를 줄이기 위해 노력한다.
- 테스트 코드도 반복적인 작업이 중복되지 않도록 리팩토링한다.
- 테스트를 위한 편의 메서드를 구현 코드에 구현하지 않는다.
- 단위 테스트하기 어려운 코드를 단위 테스트한다.
- private 함수를 테스트 하고 싶다면 클래스(객체) 분리를 고려한다.

---

### 기능 목록

1. 프로그램 흐름

- 미션 시작
- 메뉴 등록 (애피타이저, 메인, 디저트, 음료의 정보를 메뉴판(MenuRepository)에 담는다.)
- 아래의 정보를 기반으로, 주문 정보(Order)를 생성
  - 방문 날짜(VisitDate) 입력
  - 주문 메뉴(SelectionMenu)와 갯수를 입력하고 SelectionMenu에 담는다.
  - 할인 전 총 주문 금액(OrderAmount)을 계산
- 이벤트 혜택을 적용
  - 증정 이벤트: 할인 전 총 주문 금액이 12만원 이상인 경우 샴페인을 증정
- 주문 메뉴와 할인 전 총 주문금액, 증정 메뉴를 출력

- 혜택 금액을 계산
  - 크리스마스 디데이 할인: Order의 방문 날짜를 확인하여 크리스마스 디데이인지 확인
  - 평일 할인: Order의 방문 날짜와 SelectionMenu의 디저트 개수를 확인
  - 특별 할인: 별이 있는 이벤트날(3,10,17,24,25,31)을 확인하여 총 주문 금액에서 1000원 할인
- 총혜택 금액을 계산
- 할인 후의 예상 결제 금액을 계산
- 이벤트 배지를 부여: 총 혜택 금액

2. 프로젝트 구조

- Config
  - 애플리케이션 시작 시, MenuRepository에 메뉴 정보를 등록

- Controller
  - Planner: 프로그램 전체 흐름을 제어 

- Domain
  - Order(주문 정보)
    - VisitDate: 방문 날짜
      - visitDate: 방문 날짜(일) 
      - Weekday: 요일(enum)
    - SelectionMenu: 주문 메뉴
    - OrderAmount: 주문 금액
  - Menu: 메뉴
    - 애피타이저, 메인, 디저트, 음료(enum)
  - DiscountPolicy: 할인 정책
    - 크리스마스 디데이 할인 
    - 평일 할인
    - 특별 할인
  - Event: 이벤트
    - 증정 이벤트
    - 이벤트 배지

- Service
  - MenuService
    - 메뉴 등록 
    - 메뉴 조회

- Dao
  - MenuRepository: 메뉴 정보를 저장

- Util
  - StringConverter: 문자열을 원하는 타입으로 변환
    - convertToInt: 문자열을 정수로 변환
    - convertToMap: 문자열 입력을 Map으로 변환

- View
  - InputView: 입력을 담당
  - OutputView: 출력을 담당
