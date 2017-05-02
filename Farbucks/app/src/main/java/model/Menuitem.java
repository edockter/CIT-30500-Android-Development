package model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ericd on 3/21/2017.
 */

@Entity
public class Menuitem {

    @Id
    private Long Id;

    private String categoryId;
    private String name;
    private String size;
    private String price;
    private String menuImage;
    @Generated(hash = 64331798)
    public Menuitem(Long Id, String categoryId, String name, String size,
            String price, String menuImage) {
        this.Id = Id;
        this.categoryId = categoryId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.menuImage = menuImage;
    }
    @Generated(hash = 990251744)
    public Menuitem() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getCategoryId() {
        return this.categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSize() {
        return this.size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getMenuImage() {
        return this.menuImage;
    }
    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
}
