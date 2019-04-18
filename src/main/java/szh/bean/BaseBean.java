package szh.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Calendar;

@MappedSuperclass
public class BaseBean implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@GenericGenerator(name="system-uuid",strategy="uuid")
    private Long id;

    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String uid;

    private Calendar addtime;

    private Calendar updatetime;

    private boolean checkDel = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Calendar getAddtime() {
        return addtime;
    }

    public void setAddtime(Calendar addtime) {
        this.addtime = addtime;
    }

    public Calendar getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Calendar updatetime) {
        this.updatetime = updatetime;
    }

    public boolean isCheckDel() {
        return checkDel;
    }

    public void setCheckDel(boolean checkDel) {
        this.checkDel = checkDel;
    }
}
