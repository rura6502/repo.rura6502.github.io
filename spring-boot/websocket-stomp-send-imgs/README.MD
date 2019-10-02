# send image server to client using websocket
html5 기술 중 하나인 websocket을 이용해서 base64 형태의 image string을 전송하여 자연스럽게 보일 수 있는지 테스트
## description
spring boot run > localhost:8080 > connect > go
src/main/resources/test_image 에 있는 image를 base64로 변환하여 Thread.sleep 수치에 따라 socket으로 전송
## used
spring boot, websocket, stomp, sockjs
