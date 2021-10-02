# TODO

> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요

- 접근제어자
- assert 추가? ex) null 체크, ...etc
- Board 생성 시 emptyGrids length가 0일 때 예외 처리
- 최종 경우의 수가 0일 때 예외처리
- List<Set<Block>> Combinations 같은 클래스 필요?YN
    - 나 컬렉션을 너무 감싸고 있나?
- 마지막엔 블럭 다 안돌텐데 지금처럼 가능한 블록 먼저 만들어?
- inner class test 다들 어떻게 하는지 -> 현재 테스트 클래스 따로 분리했음
- GridString Grid에 합칠까?
- 테스트 리팩토링 및 테스트 추가 필요
---

### Offsets
- translate 정리

---

### ???
- 직렬화는 왜 하는 걸까, awt.Point는 왜 직렬화 했을까
- UnmodifiableSet vs ImmutableSet 차이
