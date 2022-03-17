package com.ryu.mypptbe.api.handler;


import com.ryu.mypptbe.domain.store.Address;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class AddressHandler {

    public Address getCoordination(String postCode, String street, String detailStreet) throws Exception {

        String encodeAddress = "";  // 한글 주소는 encoding 해서 날려야 함
        try { encodeAddress = URLEncoder.encode(street, "UTF-8" ); }
        catch ( UnsupportedEncodingException e ) { e.printStackTrace(); }

        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query="
                + encodeAddress;
        String auth = "KakaoAK " + "f61863f43f12004b6f171c5970b10f8c";

        URL url = new URL( apiUrl );
        HttpsURLConnection conn = ( HttpsURLConnection ) url.openConnection();
        conn.setRequestMethod( "GET" );
        conn.setRequestProperty( "Authorization", auth );

        BufferedReader br;

        int responseCode = conn.getResponseCode();
        if( responseCode == 200 ) {  // 호출 OK
            br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8") );
        } else {  // 에러
            br = new BufferedReader( new InputStreamReader(conn.getErrorStream(), "UTF-8") );
        }

        String jsonString = new String();
        String stringLine;
        while ( ( stringLine= br.readLine()) != null ) {
            jsonString += stringLine;
        }
        JSONObject jObject = new JSONObject(jsonString);
        JSONArray jArray = jObject.getJSONArray("documents");

        JSONObject obj = jArray.getJSONObject(0);

        double xPos = Double.parseDouble(obj.getString("x"));
        double yPos = Double.parseDouble(obj.getString("y"));

        return Address.builder()
                .postcode(postCode)
                .street(street)
                .detailStreet(detailStreet)
                .xPos(xPos)
                .yPos(yPos)
                .build();
    }

}
