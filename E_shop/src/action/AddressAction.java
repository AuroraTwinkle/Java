package action;

import service.AddressImpl;
import bean.Account;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kinthon on 17-3-31.
 */
public class AddressAction extends ActionSupport {

    private String name;
    private String pass;
    private String login;
    @Autowired
    private AddressImpl address;

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getPass(){
        return pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {

        return name;
    }

    public String add()
    {
        Account al = new Account();
        al.setName(getName());
        al.setPass(getPass());
        al.setLogin(getLogin());
        address.add(al);
        return SUCCESS;
    }

    public void setAddress(AddressImpl address) {
        this.address = address;
    }
}
