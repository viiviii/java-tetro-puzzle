# TODO

> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요
      기존 Block은 다른 이름으로 변경하고 BlockShape -> Block으로 변경
    - beforeEach blockShapeData 이 부분 리팩토링
    - Offsets of 가변인자 제거하여 생겨난 toOffsets 이 부분 해결

- 접근제어자
- assert null 체크?
- 퍼즐 결과가 0일 때 예외처리 누가?
- 무슨 Block 글자만 들어가면 다 block 패키지로 할거여?
- Offsets, BlockShape, EmptyGrids -> Grids 생성YN
- List<Set<Block>> Combinations 같은 클래스 필요?YN
    - 나 컬렉션을 너무 감싸고 있나?
- 마지막엔 블럭 다 안돌텐데 지금처럼 가능한 블록 먼저 만들어?

---

### Offsets
- translate 정리

---

### FitBlock
- 클래스 및 메서드명
- Block `block`
- Offset `position`
- int rotation

---

### Block
- BlockType `type`
- Offsets `shape`

- 예시
    - 회전한 `ㅓ 모양의 T 블록`과 `ㅗ 모양의 T 블록`은 같은 블록인가? --> true
        - Block(type: BlockType.T, rotation: 1) equals Block(type: BlockType.T, rotation: 2)
        -  `Block`이 shape 를 가질 경우 `Offsets`가 다르기 때문에 false 일 것임 

---

### Board
- 정사각형이다
- [x] ~~현재 사이즈는 모두 9*9이다~~
  - 추후 바뀔지 내가 알 수 없는 상태
- 보드엔 빈 칸이 있고 블럭을 채울 수 있다

- 생성 시 emptyGrids length가 0일 때 예외 처리
---

### ???
- 직렬화는 왜 하는 걸까, awt.Point는 왜 직렬화 했을까
- UnmodifiableSet vs ImmutableSet 차이
