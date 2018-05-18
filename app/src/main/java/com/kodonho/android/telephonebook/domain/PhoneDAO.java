package com.kodonho.android.telephonebook.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {
    // 주소록 가져오기
    public static List<Phone> getPhoneList(Context context){
        // 전화번호 : ID, 전화번호, 이름
        List<Phone> result = new ArrayList<>();
        // 0. 컨텐트리졸버 생성
        ContentResolver resolver = context.getContentResolver();
        // 1. 데이터 주소(Uri) 정의
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // 2. 가져올 데이터 컬럼 정의
        String projections[] = {
                //ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,   // 주소록 아이디 (중복)
                ContactsContract.CommonDataKinds.Phone.NUMBER,       // 전화번호 (데이터)
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME // 이름
        };
        // 3. 쿼리
        Cursor cursor = resolver.query(uri, projections, null, null, null);
        // 4. 결과값 반복문으로 처리
        if(cursor != null){
            Log.d("PhoneBook","phone count="+cursor.getCount());
            while(cursor.moveToNext()){
                Phone phone = new Phone();
                int index = cursor.getColumnIndex(projections[0]);
                phone.id = cursor.getString(index);
                index = cursor.getColumnIndex(projections[1]);
                phone.phoneNumber = cursor.getString(index);
                index = cursor.getColumnIndex(projections[2]);
                phone.name = cursor.getString(index);
                result.add(phone);
            }
            cursor.close(); // 습관적으로.. 데이터를 가져오거나, 파일, 네트웍을 다룰경우. 꼭 호출
        }
        return result;
    }
}
