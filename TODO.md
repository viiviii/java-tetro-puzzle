# TODO

> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요

- 접근제어자
- assert 추가? ex) null 체크, ...etc
- 퍼즐 결과가 0일 때 예외처리 누가?
- Offsets, BlockShape, EmptyGrids -> Grids 생성YN
- List<Set<Block>> Combinations 같은 클래스 필요?YN
    - 나 컬렉션을 너무 감싸고 있나?
- 마지막엔 블럭 다 안돌텐데 지금처럼 가능한 블록 먼저 만들어?
---

### Offsets
- translate 정리

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
