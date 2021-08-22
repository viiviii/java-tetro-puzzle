# TODO
> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

### Board
- 정사각형이다
- 현재 사이즈는 모두 9*9이다
    - 추후 바뀔지 내가 알 수 없는 상태
- 보드엔 빈 칸이 있고 블럭으로 채울 수 있다
- 값
  - 비어있는 위치 목록: EmptySpace emptySpace
    - 정렬되어 있어서 읽기만 했으면 좋겠음
  - 채울 수 없는 보드: static final Board UNFIT_BOARD
- 메서드
    - 보드가 모두 채워져있는지: boolean isFull()
    - 보드를 채우는 블럭의 조합 목록: List combinationsOfBlockToFit?()
    - 보드를 블럭으로 채우기: fit(block) -> 새로운 Board를 생성해서 리턴해야 됨

### Block
- 블록의 방향 별로 따로 만들 것이므로 갯수는 19개이다
- Block class
    - BlockShape shape
    - `Set<Offset>` offsets: 보드 위의 블록 위치
        - Offset offsetFromOrigin 원점에서부터의 거리를 받아서 계산
- BlockShape enum
    - BlockType type
    - `Set<Offset>` offsets: 블록 모양(블록 타입의 방향별 모양)
    - 시작 위치를 받아 Block 객체 리스트를 lazy하게 만들어주는 메서드가 있었으면 좋겠음
- BlockType enum
    - I, O, T, S, Z, J, L (7종류)

### EmptySpace
- 값
-   비어있는 위치 목록: `Set<Offset>` offsets
- Offset 집합은 순서가 있고 중복을 허용하지 않는다 -> Comparable, Set
  - 순서: 가장 좌측상단부터
- 메서드
  - 해당 위치들이 비어있는지: boolean containsAll?(Offset...)
  - 가장 처음 비어있는 위치 구하기: Offset first()
    - throw NoSuchElementException
  
### Offset
- 좌표의 x, y 위치를 나타낸다

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

### ???
- 직렬화는 왜 하는 걸까, awt.Point는 왜 직렬화 했을까