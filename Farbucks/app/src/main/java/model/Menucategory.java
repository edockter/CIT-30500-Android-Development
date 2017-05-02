package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ericd on 3/25/2017.
 */

@Entity
public class Menucategory {

    @Id
    private Long Id;

    @NotNull
    private String name;

    @Generated(hash = 1699591470)
    public Menucategory(Long Id, @NotNull String name) {
        this.Id = Id;
        this.name = name;
    }

    @Generated(hash = 674417935)
    public Menucategory() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
