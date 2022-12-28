public class LoginVo {
    private String name;
    private String id;
    private String pw;
    private String gender;
    LoginVo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LoginVo(String name, String id, String pw, String gender){
        super();
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.gender = gender;
    }

}
