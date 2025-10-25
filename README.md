# 자동차 경주

> 우아한테크코스 프리코스 2주차 과제

## 과제 목표 및 기능 요구 사항

초간단 자동차 경주 게임을 구현한다.
- 주어진 횟수 동안 n대의 자동차는 `전진` 또는 `멈출` 수 있다.
- 각 자동차에 이름을 부여할 수 있다. 전진하는 자동차를 출력할 때 `자동차 이름`을 같이 출력한다.
- 자동차 이름은 쉼표`,`를 기준으로 구분하며 이름은 `5자 이하`만 가능하다.
- 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 `0에서 9 사이`에서 무작위 값을 구한 후 무작위 값이 `4 이상`일 경우이다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.
- 우승자가 여러 명일 경우 쉼표`,`를 이용하여 구분한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

## Contact
- GitHub: jynoh00
- Email: wndus123sh@naver.com
- Blog: https://jynoh00.github.io/
---

## 명시되지 않은 요구사항에 대한 구현
- 입력값 중 시행 횟수는 양의 정수로 한다.
- 자동차의 이름이 단일 공백 문자` `이거나, 빈 값일 경우 에러 처리한다.
- 자동차의 이름의 앞 뒤에 공백 문자` `가 존재하는 경우 해당 부분을 제거하고 이름으로 설정한다.
- 자동차의 수는 `int` 범위(-2,147,483,648 ~ 2,147,483,647)를 넘지 않는다.
- 시행 횟수는 `int` 범위(-2,147,483,648 ~ 2,147,483,647)를 넘지 않는다.
- 범위를 넘어가는 시행 횟수를 입력할 경우 에러 처리한다.
- 쉼표`,`는 자동차의 이름이 될 수 없다.
- 자동차가 전진을 할 경우 `-`를 `1`개 추가한다. (이동 거리는 1씩 증가한다)
- 우승자 선정 기준은 가장 전진 횟수가 많은 차량(차량들)으로 한다.
- 우승자가 여러 차량들일 경우, 사용자가 이름 입력을 먼저한 순서로 우승자가 출력된다.
- 중복된 차량의 이름이 있을 경우, 처음 입력한 순서를 가지는 하나의 차량으로 설정한다.

*시행 횟수: 몇 번의 이동을 할 것인지 사용자가 입력한 값

---

## 기능 구현 목록

### 1. 사용자 입력 받기
- 자동차 이름 입력 (쉼표`,`를 기준으로 구분, 5자 이하)
- 이동 시행 횟수 입력

### 2. 출력 기능
- 사용자 입력 시: 실행 결과 예시의 형식을 따라 출력한다.
- 각 시행 시: 시행마다 현재 차량들의 이동 상태를 출력한다.
- 모든 시행 종료 시: 최종 우승자를 쉼표`,`를 기준으로 출력한다.

### 3. 입력값 형식 검증
- 자동차 이름 입력 문자열이 올바른 형식인지 확인한다.
- 시행 횟수 입력값이 올바른 형식인지 확인한다.

### 4. 차량별 동작(정지, 전진) 기능
- 랜덤 정수 값 기준으로 차량별 이번 시행의 동작을 설정하고 움직인다.

### 5. 최종 우승자 선정 기능
- 각 자동차의 이동 거리를 확인한다.
- 가장 큰 이동 거리 값을 가지는 차량(차량들)을 우승자로 한다.

*차량: 자동차<br>
*이동 거리: 각 차량별 전진을 선택한 횟수

---

## 실행 예시

### 입력
- 경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)
```
pobi,woni,jun
```
- 시도할 횟수
```
5
```

### 출력
- 차수별 실행 결과
```
pobi : --
woni : ----
jun : ---
```
- 단독 우승자 안내 문구
```
최종 우승자 : pobi
```

- 공동 우승자 안내 문구
```
최종 우승자 : pobi, jun
```

### 실행 결과 예시
```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
pobi,woni,jun
시도할 횟수는 몇 회인가요?
5

실행 결과
pobi : -
woni :
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : -----

최종 우승자 : pobi, jun
```
---

## 입력값 예시

### 자동차 이름 입력값

```
정상 실행

"pobi,woni,jun" // 기본 입력
-> [pobi, woni, jun]

"pobi,woni,jun, woni" // 중복 입력
-> [pobi, woni, jun]

"  pobi  ,woni , jun" // 개별 자동차 이름 전후 공백 문자열 존재
-> [pobi, woni, jun]

"p obi, wo ni, ju  n" // 개별 자동차 이름 내 공백 문자열 존재
-> [p obi, wo ni, ju  n]

표준 예외 처리

"" // 빈 문자열 입력
-> IllegalArgumentException: [ERROR] 자동차 이름 입력은 빈 값일 수 없습니다.
  
" " // 공백 문자 입력
-> IllegalArgumentException: [ERROR] 자동차 이름 입력은 공백만으로 구성될 수 없습니다.

"po  bi, woni,jun " // 개별 자동차 이름 (내부 공백 포함) 길이 초과 입력
-> IllegalArgumentException: [ERROR] 자동차 이름은 5글자를 초과할 수 없습니다.

"pobi,,jun" // 개별 자동차 이름 빈값 입력
"pobi,"
"pobi,,"
"pobi, ,asd"
-> IllegalArgumentException: [ERROR] 자동차 이름은 빈 값일 수 없습니다.
 
```

### 시행 횟수 입력값

```
정상 실행

"10"
"101"
"1999"

표준 예외 처리

"0" // 0 입력
"-1" // 공백 문자 입력
-> IllegalArgumentException: [ERROR] 시도 횟수는 양수여야 합니다.
  
"" // 빈값 입력
-> IllegalArgumentException: [ERROR] 시도 횟수는 빈 값일 수 없습니다.

" " // 올바르지 않은 형식들
"asd"
" 30"
"5 "
"*^&*"
"99999999999" // int 범위를 벗어난 경우
-> IllegalArgumentException: [ERROR] 시도 횟수는 올바른 숫자 형식이어야 합니다.
 
```

---

## 구현 상세

자동차 경주 게임을 구현한 Java 애플리케이션 

사용자로부터 자동차 이름과 시도 횟수를 입력받아, 각 라운드마다 랜덤 값에 따라 자동차를 전진시키고 최종 우승자를 출력한다.

**주요 기능:**
- 쉼표`,`로 구분된 여러 자동차 이름 입력 (최대 5자)
- 사용자 지정 라운드 수만큼 경주 진행
- 랜덤 값(0~9)이 4 이상일 때 자동차 전진
- 매 라운드 종료 시 결과 출력
- 최종 우승자 결정 (공동 우승 지원)

**입력 검증:**
- 자동차 이름: 빈 값, 단일 공백, 5자 초과 방지
- 시도 횟수: 양의 정수만 허용

---

## 코드 아키텍처

### 전체 흐름
```
Application (main)
    ↓
RacingController (전체 흐름 제어)
    ↓
InputView (입력) → RacingController (검증 및 파싱)
    ↓
RacingGame (게임 로직) → RacingCar (개별 차량)
    ↓
OutputView (출력)
```

### 시행 프로세스
```
1. 사용자 입력
   - InputView.readCarNames(): 자동차 이름 입력
   - InputView.readRoundCount(): 시도 횟수 입력

2. 데이터 검증 및 파싱
   - RacingController.parseCarNames(): 쉼표 분리, trim, 유효성 검증
   - InputView.validateInputRoundCountFormat(): 숫자 포맷 및 양수 검증

3. 게임 초기화
   - RacingGame 생성: 입력된 이름으로 RacingCar 인스턴스 생성

4. 라운드 실행 (roundCount만큼 반복)
   - RacingGame.run():
     * 각 자동차에 대해 랜덤 값(0~9) 생성
     * RacingCar.move(): 랜덤 값 >= 4이면 position++
     * OutputView.displayOutput(): 현재 위치 출력 (이름 : ---)

5. 결과 산출
   - RacingGame.getWinner(): 최대 position을 가진 자동차 찾기
   - OutputView.displayResult(): 우승자 출력 (쉼표로 구분)

6. 종료
   - Console.close()
```

### 주요 컴포넌트

**Application**
- 프로그램 진입점
- RacingController 실행

**RacingController**
- MVC 패턴의 Controller 역할
- 입력/출력 View와 게임 로직 연결
- 입력 데이터 검증 및 파싱 책임
- 예외 처리 및 리소스 정리 (Console.close)

**InputView**
- 사용자 입력 담당
- 시도 횟수 포맷 검증 (숫자 형식, 양수)
- 자동차 이름 검증은 Controller에 위임

**OutputView**
- 게임 진행 상황 및 결과 출력
- 매 라운드 차량 위치 시각화 (`-` 사용)
- 최종 우승자 출력

**RacingGame**
- 게임 로직 관리
- RacingCar 인스턴스 리스트 보유
- 한 라운드 실행 (모든 차량 이동 로직 시행)
- 우승자 결정 로직 (최대 position 탐색, 공동 우승 처리)

**RacingCar**
- 개별 자동차 도메인 모델
- name(이름), position(위치) 상태 관리
- move() 메서드: 전진 조건 판단 (랜덤 값 >= 4)

---

## 사용 기술 스택

- **Language:** Java
- **External Libraries:**
    - `camp.nextstep.edu.missionutils.Console`: 콘솔 입력 유틸리티
    - `camp.nextstep.edu.missionutils.Randoms`: 랜덤 값 생성 유틸리티
- **Design Pattern:** MVC (Model-View-Controller)
    - Model: RacingCar, RacingGame
    - View: InputView, OutputView
    - Controller: RacingController
- **Java Features:**
    - Stream API (입력 파싱)
    - Exception Handling (IllegalArgumentException)
    - Collections (ArrayList, List)

---