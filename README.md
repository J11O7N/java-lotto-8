# java-lotto-precourse
# 3주차 미션 - 로또

---
## 🎯 목표
로또 번호 불변성 설계
로또 번호가 외부에서 변경되지 않도록 Collections.unmodifiableList()를 사용해 완전한 불변 객체로 만들었습니다.
또한, 번호 정렬 책임을 View가 아닌 도메인 내부에 두어 일관성을 유지했습니다.
---
## 기능 목록

### 입력값 검증

- 구입 금액을 입력받는다.
예) 8000 → 로또 8개 발행
[예외] 금액이 1000원 단위가 아니면 IllegalArgumentException 발생
[예외] 음수나 0 입력 시 예외 발생

- 당첨 번호를 입력받는다.
예) 1,2,3,4,5,6
[예외] 중복된 숫자, 범위(1~45) 벗어난 숫자 입력 시 예외 발생

- 보너스 번호를 입력받는다.
[예외] 기존 당첨 번호와 중복 시 예외 발생

## 로또 발행

- 한 장의 로또는 1~45 사이의 서로 다른 6개의 숫자를 가진다.
- Lotto 클래스에서 번호 개수, 중복 여부, 범위 검증 수행
- Tickets(일급컬렉션)으로 여러 장의 로또를 관리한다.
- 로또 번호는 생성 시 오름차순으로 정렬된다.
- 무작위 번호는 Randoms.pickUniqueNumbersInRange(1, 45, 6) 을 사용

## 당첨 결과 계산

- WinningNumbers 클래스가 당첨 번호(6개)와 보너스 번호를 관리한다.
- Rank enum을 통해 등수(3,4,5,5+보너스,6 일치)와 상금, 출력 문자열을 관리한다.
- 각 로또의 일치 개수를 계산하여 Rank로 변환한다.
- 모든 결과를 result에서 집계한다.

## 출력 결과

- 구입한 로또 번호를 모두 출력한다.
- 당첨 내역과 당첨 금액별 개수를 출력한다.
- 최종 수익률을 소수점 둘째 자리까지 출력한다.

---
## 파일구조

```text
lotto/
├── Application.java                   # 프로그램 시작점 (main)
│
├── controller/
│   └── LottoController.java           # 흐름 제어 (입력 → 로또 발행 → 당첨 결과 → 출력)
│
├── service/
│   └── LottoService.java              # 도메인 객체 조립 + 비즈니스 로직 실행
│
├── domain/
│   ├── ticket/                        # 로또 티켓 및 집합
│   │   ├── Lotto.java                 # 한 장의 로또 (6개 번호)
│   │   └── Tickets.java               # 여러 장의 로또 (일급 컬렉션)
│   │
│   ├── winning/                       # 당첨 관련
│   │   ├── WinningNumbers.java        # 당첨 번호 + 보너스 번호 관리 및 평가 
│   │
│   ├── rank/                          # 등수 판별
│   │   └── Rank.java                  # enum (일치 개수, 보너스 여부, 상금, 순위)
│   │
│   ├── generator/                     # 난수 생성 관련
│   │   ├── LottoNumberGenerator.java  # 인터페이스 (번호 생성 규칙 정의)
│   │   └── RandomLottoGenerator.java  # 실제 구현체 (1~45 중 랜덤 6개)
│   │
│   └── result/                        # 결과 및 수익 계산
│       ├── WinningResult.java         # 당첨 내역 (Rank별 개수, 총 상금)
│       └── ProfitRate.java            # 수익률 (총 상금 / 구매 금액)
│
├── view/                              # 입출력 담당
│   ├── InputView.java                 # 로또 구입 금액, 당첨 번호, 보너스 번호 입력
│   └── OutputView.java                # 로또 내역, 당첨 통계, 수익률 출력
│
└── error/                             # 예외 관리
├── InvalidInputException.java     # 입력 형식 오류
├── InvalidLottoNumberException.java # 로또 번호 유효성 오류
└── DuplicateNumberException.java  # 중복 번호 예외
```

⚙️ 구현 이슈 및 설계

```text
1️⃣ enum을 활용한 Rank 설계

등수 계산을 하드코딩하지 않고 Rank enum 내부에 등수, 상금, 조건을 정의했다.
Rank.of(matches, bonusMatch) 정적 메서드를 통해 매칭 개수와 보너스 여부로 명확하게 등수를 판별한다.
이로써 if-else나 switch 없이 명시적 의미가 있는 상수로 관리할 수 있었다.
EnumMap<Rank, Integer>를 사용해 각 등수별 당첨 개수를 저장함으로써 키 안정성과 성능을 모두 확보했다.
💡 하드코딩 대신 enum과 EnumMap을 이용해 "변하지 않는 규칙은 상수로, 변하는 값은 데이터로" 분리했다.

2️⃣ 상수화 및 하드코딩 제거

상금, 등수 기준, 범위(1~45) 등의 값을 코드에 직접 쓰지 않고 Rank, Lotto, WinningNumbers 내부의 상수로 선언했다.
이로써 유지보수 시 실수 가능성을 줄이고 의미를 가진 이름으로 코드 가독성을 높였다.

private static final int LOTTO_SIZE = 6;
private static final int MIN_NUMBER = 1;
private static final int MAX_NUMBER = 45;

3️⃣ 일급컬렉션(First-Class Collection) 개념 이해 및 적용

2주차에서는 단순히 Cars 안에 List<Car>를 주입하며 사용했다.
스터디를 통해 이 구조가 일급컬렉션이라는 것을 알게 되었고, 이번에는 그 의도를 명확히 이해하고 적용했다.
Tickets 클래스는 List<Lotto>를 감싸는 일급컬렉션이다.
로또 목록 관리, 크기 반환, 반복 처리 등의 책임을 모두 Tickets 내부로 옮겨 응집도를 높였다.
외부에서는 리스트에 직접 접근할 수 없으며, 불변 컬렉션(Collections.unmodifiableList)로 보호된다.

4️⃣ 불변 객체(Immutable Object) 설계

Lotto, WinningNumbers, Tickets 모두 불변 객체로 설계했다.
생성 시 검증 → 정렬 → 불변 컬렉션으로 래핑 후 저장
이후 내부 상태 변경 불가
이로써 예측 가능한 동작과 안정적인 테스트가 가능해졌다.

예시: 
this.numbers = Collections.unmodifiableList(Numbers);

5️⃣ Set을 이용한 중복 검증

로또 번호와 당첨 번호의 중복 검증에 Set 자료구조를 활용했다.
중복된 값이 존재하면 Set.add()가 false를 반환하므로 명확하고 효율적인 예외 검증이 가능했다.
```