package utils;

public class User {
    private static String user_id = "";
    private static String user_fullname = "";
    private static String user_username = "";
    private static String user_role ="";
    
    //Encapsulation
    //Method setter dan getter adalah dua method yang tugasnya untuk mengambil 
    //dan mengisi data ke dalam objek.
    
    //Method setter tidak memiliki nilai kembalian void (kosong). 
    //Karena tugasnya hanya untuk mengisi data ke dalam atribut.
    //Sedangkan method getter memiliki nilai kembalian sesuai dengan tipe data yang akan diambil.
    
    //method getter
    public String getUser_id() {
        return user_id;
    }

    //method setter
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //method getter
    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }
    
    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
