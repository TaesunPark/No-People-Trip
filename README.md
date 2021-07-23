# 🙅🏼 No People Trip

protoType/TeamProject

Tools/AndroidStudio

Tools/Jupyter Notebook

Language/Java

Language/Python

예측 인구 밀집도 및 실시간 이용객 집계를 통해 시민들에게 

안전한 공공서비스를 제공하는 "No People Trip"

서울시와 함께하는 2020 ICT 콕 AI공모전 최종 본선 진출

![Untitled](https://user-images.githubusercontent.com/59998914/126845776-e5c7da8b-1f7a-4db5-a2d2-2deee1bafd6b.png)

## 💡 Background

- 코로나 19로 인해 언택트 시대 도래
- 사회적 거리두기, 마스크 착용 등 위생 수칙 중요성 극대화
- 인구 밀집도에 따른 관광지 혼잡도 예측 → 집단 감염 방지

## **📚 Stack & Library**

- Android/Java
- ML/Python
- Server/Aws
- Server Language/Javascript
- Javascript Runtime-Engine/Node.js
- Jupyter Notebook
- Facebook Prophet : 실시간 이용객 예측 모델을 위해 사용
- Tensorflow : 모델 값을 더 정확하게 값을 추출하기 위해 사용
- TFLite : 마스크 검증 모델, 실시간 이용객 예측 모델을 안드로이드에 적용하기 위해 사용.
- SQLite : 안드로이드 로컬 DB 사용
- Google Map API : 지도 API 구현하기 위해 사용

## 👩‍💻 Project Features


## 1. QR코드 및 마스크 인증

현재 관광지에서 권장되는  QR체크인 기능

스탬프를 찍고 마스크 착용 유도 및 검증 기능

## 2. 관광지 및 스탬프 확인

원하는 지역을 누르면 혼잡도 순으로 정렬되어 관광지 추천 기능.

사용자 현재 위치에서 떨어진 거리 순으로도 정렬 가능.

관광지를 누르면 관광지에 대한 설명 확인 가능.

관광지에 대한 QR코드를 찍으면 색깔 변화로 스탬프 확인 가능.

## 3. 지도

지도 클릭 시 빨간색 마커로 관광지를 보여주는 기능.

다녀간 관광지는 발자국 마커로 발자취를 남기는 기능.

## 4. 마이페이지

마스크 착용에 따른 보상을 얻을 수 있는 레벨 시스탬 기능.

## 🎞️ Service UI

![_1](https://user-images.githubusercontent.com/59998914/126845779-90f99003-f23a-4154-bdee-4ba12934630b.png)

![_2](https://user-images.githubusercontent.com/59998914/126845782-0f64b9ed-b20e-4a17-905b-20b53507cfa1.png)

![_3](https://user-images.githubusercontent.com/59998914/126845783-eda1700b-c67d-440e-a8e2-2d6c9b244284.png)

![_4](https://user-images.githubusercontent.com/59998914/126845784-034ec554-d851-496e-be39-6c826e8c6a6a.png)

![Untitled 1](https://user-images.githubusercontent.com/59998914/126845785-8062657f-71af-41b0-a1c9-2704c15d86ee.png)

## 💭 I Learned

- 스탬프 관련 기능 설계 및 구현을 담당했습니다.
- 전반적인 UI/UX 설계를 담당했습니다.
- 관광지 사람 수를 예측하기 위해 시계열 모델 및 선형 회귀 모델을 사용했습니다.

    아래 링크는 프로젝트에 활용한 facebook prophet 모델을 공부하고 적용한 내용입니다.

    [https://www.notion.so/Facebook-Prophet-cff28b3646454cae840270bd60c2632b](https://www.notion.so/Facebook-Prophet-cff28b3646454cae840270bd60c2632b)
