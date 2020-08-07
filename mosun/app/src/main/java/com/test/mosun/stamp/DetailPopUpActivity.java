package com.test.mosun.stamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.mosun.R;

import java.util.zip.Inflater;

//tour api 사용해서 코스 정보 띄우기 or description
public class DetailPopUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_popup);

        Intent secondIntent = getIntent();

        String distance = String.valueOf(secondIntent.getStringExtra("distance"));
        String predictionNumber = String.valueOf(secondIntent.getStringExtra("predictionNumber"));
        String todayNumber = String.valueOf(secondIntent.getStringExtra("todayNumber"));

        Log.d("string", String.valueOf(secondIntent.getStringExtra("key")));

        TextView title = findViewById(R.id.popupTitle);
        ImageView imageView = findViewById(R.id.popupImage);
        TextView distance1 = findViewById(R.id.popupdistance);
        TextView exception = findViewById(R.id.popupException);
        TextView today = findViewById(R.id.popupToday);
        TextView description = findViewById(R.id.popupdescription);

        if(secondIntent.getStringExtra("key").equals("국립중앙박물관")){
            title.setText("국립중앙박물관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("경복궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("서울시립미술관")){
            title.setText("서울시립미술관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("경복궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("서울대공원")){
            title.setText("서울대공원");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("서울대공원 설명");
        } else if(secondIntent.getStringExtra("key").equals("서대문형무소역사관")){
            title.setText("서대문형무소역사관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("경복궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("아쿠아리움")){
            title.setText("아쿠아리움");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
        } else if(secondIntent.getStringExtra("key").equals("경복궁")){
            title.setText("경복궁");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("경복궁 설명");

        } else if(secondIntent.getStringExtra("key").equals("서울스카이")){
            title.setText("서울스카이");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("서울스카이 설명");
        } else if(secondIntent.getStringExtra("key").equals("창덕궁")){
            title.setText("창덕궁");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("창덕궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("종묘")){
            title.setText("종묘");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("종묘 설명");
        } else if(secondIntent.getStringExtra("key").equals("세종대왕기념관")){
            title.setText("세종대왕기념관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("세종대왕기념관 설명");
        } else if(secondIntent.getStringExtra("key").equals("덕수궁")){
            title.setText("덕수궁");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("덕수궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("창경궁")){
            title.setText("창경궁");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("창경궁 설명");
        } else if(secondIntent.getStringExtra("key").equals("남산골한옥마을")){
            title.setText("남산골한옥마을");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("남산골한옥마을 설명");
        } else if(secondIntent.getStringExtra("key").equals("선릉")){
            title.setText("선릉");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("남산골한옥마을 설명");
        } else if(secondIntent.getStringExtra("key").equals("태릉")){
            title.setText("태릉");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("태릉 설명");
        } else if(secondIntent.getStringExtra("key").equals("정릉")){
            title.setText("정릉");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("정릉 설명");
        } else if(secondIntent.getStringExtra("key").equals("의릉")){
            title.setText("의릉");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("의릉 설명");
        } else if(secondIntent.getStringExtra("key").equals("헌릉")){
            title.setText("헌릉");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("헌릉 설명");
        }else if(secondIntent.getStringExtra("key").equals("영희원")){
            title.setText("영희원");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("영희원 설명");
        }
        else if(secondIntent.getStringExtra("key").equals("경기전")){
            title.setText("경기전");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("경기전 설명");
        } else if(secondIntent.getStringExtra("key").equals("국립전주박물관")){
            title.setText("국립전주박물관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("국립전주박물관 설명");
        } else if(secondIntent.getStringExtra("key").equals("스파라쿠아")){
            title.setText("스파라쿠아");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("스파라쿠아 설명");
        } else if(secondIntent.getStringExtra("key").equals("전주 동물원")){
            title.setText("전주 동물원");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("전주 동물원 설명");
        } else if(secondIntent.getStringExtra("key").equals("한벽문화관")){
            title.setText("한벽문화관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("한벽문화관 설명");
        } else if(secondIntent.getStringExtra("key").equals("한옥레일바이크")){
            title.setText("한옥레일바이크");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("한옥레일바이크 설명");
        } else if(secondIntent.getStringExtra("key").equals("한국도로공사수목원")){
            title.setText("한국도로공사수목원");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("한국도로공사수목원 설명");
        } else if(secondIntent.getStringExtra("key").equals("강천산")){
            title.setText("강천산");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("강천산 설명");
        } else if(secondIntent.getStringExtra("key").equals("황토열매마을")){
            title.setText("황토열매마을");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("황토열매마을 설명");
        } else if(secondIntent.getStringExtra("key").equals("고추장익는마을")){
            title.setText("고추장익는마을");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("고추장익는마을 설명");
        } else if(secondIntent.getStringExtra("key").equals("장류체험관")){
            title.setText("장류체험관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("장류체험관 설명");
        } else if(secondIntent.getStringExtra("key").equals("산림박물관")){
            title.setText("산림박물관");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("산림박물관 설명");
        } else if(secondIntent.getStringExtra("key").equals("발효소스토굴")){
            title.setText("발효소스토굴");
            imageView.setImageResource(R.drawable.kgoung);
            exception.setText("오늘 예상 총 사람 수" + predictionNumber+"명");
            today.setText("오늘 약"+todayNumber+"명 다녀감");
            distance1.setText("현재 위치에서 약"+distance+"m 떨어짐");
            description.setText("발효소스토굴 설명");
        }

    }
}