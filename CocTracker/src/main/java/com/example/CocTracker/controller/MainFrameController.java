package com.example.CocTracker.controller;

public class MainFrameController {

    public void initialize() {
    }

//    private String get() {
//        try {
//            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjM3ZmY4YWI3LTVhY2EtNDAzOS04Y2M1LWYyY2IwZTZjYWRhMCIsImlhdCI6MTcxMjE1MjMxMiwic3ViIjoiZGV2ZWxvcGVyLzk2ZjQxN2YyLWU0ZDgtMmQyYS1iZjBkLWQzYzQ2YzE3NzIwNyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjQ1Ljc5LjIxOC43OSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.BNe6M5YlQpeRK_8Nv7xStG7GHENvJtqb0CCxhp5kSRcH62QbQjInZqSKcgWTvarro4ciZsDhCX3IPQvey8TJBA";
//
//            URL url = new URI("https://cocproxy.royaleapi.dev/v1/clans/%2322YUCPVJV").toURL();
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.addRequestProperty("Authorization", "Bearer " + token);
//            conn.setRequestMethod("GET");
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//                    StringBuilder response = new StringBuilder();
//
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//
//                    System.out.println(response);
//
//                    return response.toString();
//                }
//            } else {
//                System.out.println("GET request failed " + responseCode);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
