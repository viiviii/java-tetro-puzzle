# TODO

> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요
---

### Offsets
- translate 정리

---

## Block
### Block
- 위치
- BlockShape

---

## Board
### Board
- 정사각형이다
- [x] ~~현재 사이즈는 모두 9*9이다~~
  - 추후 바뀔지 내가 알 수 없는 상태
- 보드엔 빈 칸이 있고 블럭을 채울 수 있다
- 값
  - [x] ~~비어있는 위치 목록: EmptySpace emptySpace~~
    [x] ~~정렬되어 있어서 읽기만 했으면 좋겠음~~ -> Offsets 클래스 생성됨
  - [x] ~~채울 수 없는 보드: static final Board UNFIT~~
- 메서드
  - [x] ~~보드가 모두 채워져있는지: boolean isFull()~~
  - 보드를 채우는 블럭의 조합 목록: List combinationsOfBlockToFit?()
  - 보드를 블럭으로 채우기: fit(block) -> 새로운 Board를 생성해서 리턴해야 됨?
### EmptySpace
- [ ] `Offsets` 사용하도록 리팩토링
- 메서드
  - [x] ~~해당 위치들이 비어있는지: boolean containsAll?(Offset...)~~

---

### 원하는 모양새
- Q. List<Set<Block>> Combinations 같은 클래스 필요?YN
- Offsets가 아닌 Holes 같은 클래스 필요?YN
- fill은 언제고 fit은 언제쓰는 단어임?동일하게 맞추던가
- 마지막엔 블럭 다 안돌텐데 lazy?YN
```
public List<Set<Block>> combinationsFillOf(Offsets holes) {
    List<Set<Block>> result = [];
    if(holes.size() == 0) return result;
    List<Block> blocks = Blocks.fromPosition(firstHole);
    for(block: blocks) {
        Offsets remainingHoles = holes.fit(block);
        
        if(remainingHoles.size() == 0) {
            // 마지막 결과는 무조건 1개이므로 루프를 더 돌지 않고 바로 리턴
            return result.add(Set.of(block));
        }
        
        boolean unfittedHoles = (remainingHoles == holes); //predicate?
        if(unfittedHoles) continue;
        
        List<Set<Block>> combinations = combinationsFillOf(remainingHoles);
        if(combinations.isEmpty) continue; 
        
        for(combination : combinations) {
            combination.add(block);
        }
        result.addAll(combinations);
    }
    return result;
}
```

---

### ???
- 직렬화는 왜 하는 걸까, awt.Point는 왜 직렬화 했을까
- UnmodifiableSet vs ImmutableSet 차이
