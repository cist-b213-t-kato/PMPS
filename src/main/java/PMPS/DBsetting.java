package PMPS;

public class DBsetting {

    public static final String URL = "jdbc:postgresql://localhost:5432/pmps";

    public static final String USER = "postgres";

    public static final String PASSWORD = "postgres";

    //設定用のstaticな値を管理するため、インスタンス化できないクラスにする
    private DBsetting(){
        throw new AssertionError("インスタンス化してはいけない");
    }
}
