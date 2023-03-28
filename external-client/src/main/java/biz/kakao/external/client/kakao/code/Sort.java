package biz.kakao.external.client.kakao.code;

public enum Sort {

    ACCURACY("accuracy", "정확도순"),
    RECENCY("recency", "최신순");

    private final String name;
    private final String value;

    Sort(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

}
