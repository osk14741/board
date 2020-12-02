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
