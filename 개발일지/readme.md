### BOARD 개발일지

2020-12-01

- 한 것
  - 게시판 List 뽑기
  - 게시판 쓰기 페이지(smart editor2 사용)

2020-12-02

- 문제
  - forward 방식으로 보내면 새로고침 시 db에 insert가 반복되는 문제 발견

    - redirect로 해결, forward는 조회 등에만 쓰자.

    - ```java
      modelAndView.setViewName("redirect:~~~~");
      ```

  - ModelAndView 방식으로 한글을 보낼 때(url로) 한글이 깨지는 문제 발견

    - ```java
      URLEncoder.encode(요소, "UTF-8");
      ```

    - 인코딩 문제임.

- 배운 점

  - 항상 변수로 뭐가 왔다갔다 거리는지 확인 잘 하자.

- 한 것

  - 게시글 클릭 시 상세보기 기능
  - 게시글 수정하기

2020-12-03

- 배운 점

  - mybatis

    - ```sql
      <trim prefix="AND (" suffix=")" prefixOverrides="OR">
      					<foreach item='searchDiv' collection="typeArr">
      						<trim prefix="OR">
      							<choose>
      								<when test="searchDiv == 'T'.toString()">
      									title like '%'||#{searchWord}||'%'
      								</when>
      								<when test="searchDiv == 'C'.toString()">
      									content like '%'||#{searchWord}||'%'
      								</when>
      								<when test="searchDiv == 'W'.toString()">
      									reg_id like '%'||#{searchWord}||'%'
      								</when>
      							</choose>
      						</trim>
      					</foreach>		        
      		        </trim>
      ```

    - 페이징 mysql은 더욱 간단하게 <limit> 등으로 하고 성능도 rownum으로 한것보다 성능이 좋다더라.

    - rownum을 between으로 하면 쭉 다 돌고 거기서 골라냄. 그래서 rownum 시작 끝으로 구분해서 하면 거기만 뽑아옴. 근데 Count를 한번 더 날려야 하지만 board DB 갯수가 늘어나면 첫번째가 더 오래걸릴 수 있을 것 같다.

- 한 것
  - 게시글 삭제
  - 페이징 시작
  
- 알아볼 것

  - seq가 index로 잡혀있는데 insert 하면 왜 중간중간에 들어가지?
  - order by를 해결할 방법을 찾아야 할 것 같다.

2020-12-05

- 배운 점
  - 보낼 데이터, 받을 데이터 명확히 구분을 해놓아야 나중에 편안하다.
  - 데이터 조작은 컨트롤러에서, 출력은 뷰에서. 괜히 jsp 에서 데이터 조작하려 하지 말자.
- 한 것
  - 보드 페이징

2020-12-06

- 배운 점

  - 아무리 써봐도 반복문 대단한 기능이다.

  - ```sql
    <selectKey keyProperty="seq" resultType="Integer" order="AFTER">
    	SELECT comment_seq.currval FROM dual
    </selectKey>
    ```

    하면 parameterType으로 들어왔던 그 객체의 getter/setter를 이용해서 그 객체에 포함시켜 준다.

- 한 것
  
  - 댓글 리스트
  - 댓글 쓰기

2020-12-08

- 배운 점
  - 엥간하면 서로 값을 같게 해서 헷갈리지 않게 하자.(쓸데없이 숫자를 붙인다던지 의미없는 모호한 id, name으로 정하지 말자.)

- 한 것
  - 게시판 검색
  - 채널 관리페이지(리스트, 삭제) 시작