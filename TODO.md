# TODO

> 보드의 비어있는 모든 부분을 블록으로 채우는 경우의 수 구하기 

- 주 생성자의 역할은 심플하게 변경
- test
    - 메서드명 패턴 통일하기
    - 테스트 당 하나의 assert만 가지도록 분리하기
    - 참고하던 문서 나머지 부분 + 다른 글들도 읽고 추가 필요
      기존 Block은 다른 이름으로 변경하고 BlockShape -> Block으로 변경

---

### Offsets
- translate 정리

---

### UndecidedName
- Block `block`
- Offset `position`

---

### Block
- BlockType `type`
- Offsets `shape`

---

### Board
- 정사각형이다
- [x] ~~현재 사이즈는 모두 9*9이다~~
  - 추후 바뀔지 내가 알 수 없는 상태
- 보드엔 빈 칸이 있고 블럭을 채울 수 있다

---

### 원하는 모양새
- Q. List<Set<Block>> Combinations 같은 클래스 필요?YN
- Offsets가 아닌 Holes 같은 클래스 필요?YN
- fill은 언제고 fit은 언제쓰는 단어임?동일하게 맞추던가
- 마지막엔 블럭 다 안돌텐데 lazy?YN 
- UndecidedName: Block과 보드 위 블럭의 위치를 갖고있는 클래스의 이름
```
class Board {
    public List<Set<UndecidedName>> combinationsFilled(){
        List<Set<UndecidedName>> result = combinationsFillOf(this.holes);
        if(result.size == 0) throws new Exception(); -- 이건 무조건 있어야되는거 아닌가
        return result;
    }
}

public Set<Block> fillableBlocks(Offsets holes) {
    Set result = {};
    for(block : blocks) {
        boolean fitted = holes.containAll(block.shape.translate(holes.first())); // 분리
        if(fitted) result.add(block);
    }
    return result;
}

public List<Set<UndecidedName>> combinationsFillOf(Offsets holes) {
    List<Set<UndecidedName>> result = [];
    if(holes.size() == 0) return result;
    Offset firstHole = holes.first();
    Set<Block> fillableBlocks = fillableBlocks(holes);
    
    for(fillableBlock : fillableBlocks) {
        Offsets remainingHoles = holes.fit(fillableBlock);
        List<Set<UndecidedName>> combinations = combinationsFillOf(remainingHoles);
        if(combinations.isEmpty) continue; 
        
        for(combination : combinations) {
            combination.add(new UndecidedName(fillableBlock, firstHole));
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
