# TODO
> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

### Project
- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요
- Offset compareTo
    - 테스트 케이스 분리 및 더 다양하게 작성
    - 지금 실패하는 테스트 고치기
- BlockShape
    - offset 범위 compareTo 사용하기
- Shape
    - Offsets 하나로 변경할 수 있는가?
      - EmptySpace도 Offset으로 변경할 수 있는가? 
    - 주 생성자의 하는 일을 최소한으로 변경할 수 있는가?

### ???
- 직렬화는 왜 하는 걸까, awt.Point는 왜 직렬화 했을까
- UnmodifiableSet vs ImmutableSet 차이

### Board
- 정사각형이다
- [x] ~~현재 사이즈는 모두 9*9이다~~
    - 추후 바뀔지 내가 알 수 없는 상태
- 보드엔 빈 칸이 있고 블럭으로 채울 수 있다
- 값
  - [x] ~~비어있는 위치 목록: EmptySpace emptySpace~~
    - 정렬되어 있어서 읽기만 했으면 좋겠음
  - [x] ~~채울 수 없는 보드: static final Board UNFIT~~
- 메서드
    - [x] ~~보드가 모두 채워져있는지: boolean isFull()~~
    - 보드를 채우는 블럭의 조합 목록: List combinationsOfBlockToFit?()
    - 보드를 블럭으로 채우기: fit(block) -> 새로운 Board를 생성해서 리턴해야 됨?

### Block
- 블럭은 타입이 있다(O, J, L, ...)
- 블럭은 모양이 있다
    - 블럭은 회전한다 -> 타입 & 회전 = 총 19개
      - 블럭은 위치를 갖는다 
        - 원점에서부터의 거리
- 회전한 블럭
    - 타입 & 회전 = 총 19개
    - 이 값은 많이 쓰이고 최종 결과엔 필요 없다
    - 개별로 필요하지 않고 항상 전체를 사용한다
- 블록을 회전한 모양은 항상 같으니 한번만 생성해놓고 여기에 거리를 받아서 Block 객체를 만들고 싶음
- BlockType enum
    - [x] ~~I, O, T, S, Z, J, L (7종류)~~
    - o1x, o1y 부분 별로임ㅠㅠ
- [x] ~~Offset이 직관적으로 보이게 toString~~

### EmptySpace
- 값
- [x] ~~비어있는 위치 목록: `Set<Offset>` offsets~~
- [x] ~~Offset 집합은 순서가 있고 중복을 허용하지 않는다~~
  - [x] ~~순서: 가장 좌측상단부터~~
- 메서드
  - [x] ~~해당 위치들이 비어있는지: boolean containsAll?(Offset...)~~
  - [x] ~~가장 처음 비어있는 위치 구하기: Offset first()~~
    - throw NoSuchElementException
  
### Offset
- [x] ~~좌표의 x, y 위치를 나타낸다~~

### 원하는 시나리오
- `List<Set<Block>>` combinationsOfBlockToFit();

```
List<Set<Block>> result = [];
for(block: Blocks) {
    Board newBoard  = board.fit(block);
    if(newBoard == UNFIT_BOARD) continue; 
    if(newBoard.isFull) { 
        // 마지막 조각의 결과는 무조건 1개이므로 바로 리턴
        return result.add(new { block });
    }
    List<Set<Block>> combinations = newBoard.combinationsOfBlockToFit();
    if(combinations.isEmpty) continue;
    for(combination : combinations) {
        combination.add(block);
    }
    result.addAll(combinations);
}
return result;
```
