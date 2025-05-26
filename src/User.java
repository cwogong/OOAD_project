public class User {
    // ID, Password, Address (private)
    private String userID;
    private String userPW;
    private String address;

    // 생성자함수 (id, pw ,address 초기화)
    public User(String id, String pw, String add) {
        this.userID = id;
        this.userPW = pw;
        this.address = add;
    }
    public User(String id, String pw) {
        this.userID = id;
        this.userPW = pw;
    }
    public User() {
    }

    // Getter 함수
    public String getID(){
        return this.userID;
    }
    public String getPW(){
        return this.userPW;
    }
    public String getaddress() {
        return this.address;
    }

    // Setter 함수
    public void setID(String id) {
        this.userID = id;
    }
    public void setPW(String pw) {
        this.userPW = pw;
    }
    public void setAddress(String add) {
        this.address = add;
    }
};