# 스터디 약속 지킴이

#### 문제 제시 : 오프라인 스터디가 다시 활성화되는 요즘 시간 약속을 지키지 않은 사람들이 많아서 오프라인 스터디가 원활하게 진행되지 못 하고 있다. 😅
#### 문제 접근 : 사람들이 시간 약속을 잘 지키게하고 동기부여를 할 수 있는 방법은 없을까?🤔
#### 해결 방법 : 약속 시간에 정해진 장소에 없을시 벌금을 부과하고, 벌점을 부과하는 건 어떨까? 또한 적극적인 참여를 위해 해당 벌금은 참여율이 높은 사람에게 분배한다면?👍🏻

## Description
오프라인 스터디가 늘어가고 있는 요즘 시간 약속을 지키지 않는 사람이 많아 스터디 그룹 활동에 영향을 미치고 있다.
이를 해결하기위해 보증금을 관리자가 관리하여 지각이나 미참여시 보증금을 차감하는 식으로 운영하기도 하지만 그룹 회원으로서는 믿기 힘들 수도 있고 매번 관리자가 따로 관리하는 것은 효율적이지 않다.
따라서 회원들의 지각과 미참여 여부를 관리하고 벌금, 벌점을 관리하는 웹 어플리케이션을 만드려고한다.

## 시스템 아키텍처(추가 예정)


## 주요기능
| Feature  | 내용 |
| -------------------| ---------------- |
|Location|스터디그룹의 미팅시간부터 정해놓은 지각시간까지 사용자의 위치를 정해놓은 시간마다 체크하여 사용자의 지각 여부, 미참여 여부를 확인한다.|
| Chat | 1:1 채팅, 1:N 채팅이 존재한다. 1:1 채팅은 그룹 가입 문의전 회원과 관리자의 채팅이고 1:N 채팅은 그룹 채팅, 실시간 그룹 채팅이 존재한다. 1:1 채팅과 그룹채팅은 1주일의 기간을 가지고 저장이되고, 실시간 그룹 채팅은 휘발성 채팅이다. |
| Authorization | 회원에 관한 인가는 회원, 관리자, 이메일 인증이 되지 않은자가 있다. 그룹에는 그룹 관리자, 그룹 멤버, 그룹 가입 대기, 그룹 가입 거절이 있다.|
| Penalty |그룹에 가입할 때 정해진 보증금을 카카오페이 API로 보증금을 내고 관리자가 정해놓은 규칙대로 지각, 미참여시 해당 보증금에서 정해진 금액만큼 차감한다.|
| Reward |정해진 출석률에 대해서 기간별로 통계를 내어 정해놓은 규칙대로 벌금을 거둬 보상금을 지급하는 방식이다.|

## ERD<수정 예정>
<img width="727" alt="image" src="https://github.com/JIUNG9/study-group-meeting/assets/60885635/6a613099-cb1f-452d-b4ba-1b3427adfa6c">

## Docs

### API Docs
https://www.notion.so/b01fa21ff51d4fa38f1084299cfe9c12?v=4eae4fff07e147889831f60cb051c433&pvs=4
